/**
 * Copyright (C) 2011-2016 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.importer.feeder.importer;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.xmlbeans.XmlException;
import org.n52.sos.importer.feeder.Configuration;
import org.n52.sos.importer.feeder.model.InsertObservation;
import org.n52.sos.importer.feeder.model.TimeSeries;
import org.n52.sos.importer.feeder.model.TimeSeriesRepository;
import org.n52.sos.importer.feeder.model.Timestamp;
import org.n52.svalbard.encode.exception.EncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SweArrayObservationWithSplitExtensionImporter extends ImporterSkeleton {

    protected static final Lock ONE_IMPORTER_LOCK = new ReentrantLock(true);

    private static final Logger LOG = LoggerFactory.getLogger(SweArrayObservationWithSplitExtensionImporter.class);

    private TimeSeriesRepository timeSeriesRepository;

    private int currentHunk;

    // Identified on localhost on development system
    // Default value: 5000
    // Max possible value: 12500
    private int hunkSize = 5000;

    @Override
    public void startImporting() {
        super.startImporting();
        hunkSize = configuration.getHunkSize();
        LOG.debug("Using hunkSize '{}'", hunkSize);
        LOG.info("Using {}ms timeout buffer during insert observation requests. "
                + "Change "
                + "<SosImportConfiguration><SosMetadata><InsertSweArrayObservationTimeoutBuffer>"
                + " if required.",
                configuration.getTimeoutBuffer());
    }

    @Override
    public synchronized void addObservationForImporting(InsertObservation... insertObservations) throws Exception {
        if (insertObservations == null) {
            return;
        }
        if (configuration == null) {
            throw new IllegalStateException("Field 'configuration' MUST NOT be null! Call setConfiguration() before.");
        }
        if (timeSeriesRepository == null) {
            timeSeriesRepository = new TimeSeriesRepository(configuration);
        }
        timeSeriesRepository.addObservations(insertObservations);
        if (currentHunk == hunkSize) {
            currentHunk = 0;
            insertAllTimeSeries(timeSeriesRepository);
            timeSeriesRepository = new TimeSeriesRepository(configuration);
        } else {
            currentHunk += insertObservations.length;
        }
    }

    protected void insertAllTimeSeries(final TimeSeriesRepository timeSeriesRepository)
            throws XmlException, IOException, EncodingException {
        LOG.trace("insertAllTimeSeries()");
        ONE_IMPORTER_LOCK.lock();
        try {
            Timestamp newLastUsedTimestamp = null;
            insertObservationForATimeSeries:
                for (final TimeSeries timeSeries : timeSeriesRepository.getTimeSeries()) {
                    // check if sensor is registered
                    if (!sosClient.isSensorRegistered(timeSeries.getSensorURI()) &&
                            !failedSensorInsertions.contains(timeSeries.getSensorURI())) {
                        final String assignedSensorId = sosClient.insertSensor(
                                timeSeriesRepository.getInsertSensor(timeSeries.getSensorURI())).getKey();
                        if (assignedSensorId == null || assignedSensorId.equalsIgnoreCase("")) {
                            LOG.error(String.format(
                                    "Sensor '%s'[%s] could not be registered at SOS. "
                                            + "Skipping insert observation for this timeseries '%s'.",
                                            timeSeries.getSensorName(),
                                            timeSeries.getSensorURI(),
                                            timeSeries));
                            failedObservations.addAll(timeSeries.getInsertObservations());
                            failedSensorInsertions.add(timeSeries.getSensorURI());
                            continue insertObservationForATimeSeries;
                        }
                    }
                    // insert observation
                    final String observationId = sosClient.insertSweArrayObservation(timeSeries);
                    if (observationId == null || observationId.equalsIgnoreCase("")) {
                        LOG.error(String.format("Insert observation failed for sensor '%s'[%s]. Store: %s",
                                timeSeries.getSensorName(),
                                timeSeries.getSensorURI(),
                                timeSeries));
                        failedObservations.addAll(timeSeries.getInsertObservations());
                    } else if (observationId.equals(Configuration.SOS_OBSERVATION_ALREADY_CONTAINED)) {
                        LOG.debug(String.format("TimeSeries '%s' was already contained in SOS.",
                                timeSeries));
                    } else if (configuration.isUseLastTimestamp()) {
                        newLastUsedTimestamp = timeSeries.getYoungestTimestamp();
                    }
                }
            if (context.shouldUpdateLastUsedTimestamp(newLastUsedTimestamp)) {
                context.setLastUsedTimestamp(newLastUsedTimestamp);
            }
        } finally {
            ONE_IMPORTER_LOCK.unlock();
        }
    }

    @Override
    public synchronized void stopImporting() throws Exception {
        if (timeSeriesRepository != null && !timeSeriesRepository.isEmpty()) {
            insertAllTimeSeries(timeSeriesRepository);
        }
    }

}
