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
package org.n52.sos.importer.feeder.model;

import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.n52.sos.importer.feeder.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link TimeSeriesRepository} is a management type for some {@link TimeSeries}.
 *
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk J&uuml;rrens</a>
 */
public class TimeSeriesRepository {

    private static final Logger LOG = LoggerFactory.getLogger(TimeSeriesRepository.class);

    private final Map<TimeSeriesId, TimeSeries> repo;

    private Configuration configuration;

    public TimeSeriesRepository(Configuration configuration) {
        repo = Maps.newHashMap();
        this.configuration = configuration;
    }

    public void addObservations(final InsertObservation[] ios) {
        for (InsertObservation io : ios) {
            final TimeSeriesId timeSeriesId = new TimeSeriesId(io);
            if (!repo.containsKey(timeSeriesId)) {
                repo.put(timeSeriesId, new TimeSeries());
            }
            repo.get(timeSeriesId).addObservation(io);
        }
    }

    public Collection<TimeSeries> getTimeSeries() {
        return repo.values();
    }

    /**
     * <p>getInsertSensor.</p>
     *
     * @param sensorURI the URI of the Sensor
     * @return A {@link org.n52.sos.importer.feeder.model.InsertSensor} using
     *          <ul>
     *              <li>ObservedProperty</li>
     *              <li>MeasureValueType</li>
     *              <li>UnitOfMeasurement</li>
     *              <li>ReferenceValues</li>
     *          </ul>
     *          from all timeseries in this {@link org.n52.sos.importer.feeder.model.TimeSeriesRepository}
     *          having the Sensor with given URI <code>sensorURI</code>.
     * @throws java.lang.IllegalArgumentException if no timeseries for the given
     *          <code>sensorURI</code> are available in this repository.
     */
    public InsertSensor getInsertSensor(final String sensorURI) {
        // get all time series of sensor
        List<TimeSeries> timeseries = new ArrayList<>(repo.values().size());
        for (TimeSeries series : getTimeSeries()) {
            if (series.getSensorURI().equals(sensorURI)) {
                timeseries.add(series);
            }
        }
        if (timeseries.isEmpty()) {
            throw new IllegalArgumentException("Could not find any time series for sensor '" + sensorURI + "'");
        }
        final InsertObservation io = timeseries.get(0).getFirst();

        return new InsertSensor(io,
                getObservedProperties(timeseries),
                getMeasuredValueTypes(timeseries),
                getUnitsOfMeasurement(timeseries),
                configuration.getReferenceValues(io.getSensorURI()));
    }

    private Map<ObservedProperty, String> getUnitsOfMeasurement(List<TimeSeries> timeseries) {
        LOG.trace("getUnitsOfMeasurement(...)");
        final Map<ObservedProperty, String> unitsOfMeasurement =
                new HashMap<>(timeseries.size());
        for (final TimeSeries ts : timeseries) {
            unitsOfMeasurement.put(ts.getObservedProperty(), ts.getUnitOfMeasurementCode());
        }
        return unitsOfMeasurement;
    }

    private Map<ObservedProperty, String> getMeasuredValueTypes(List<TimeSeries> timeseries) {
        LOG.trace("getMeasuredValueTypes(...)");
        final Map<ObservedProperty, String> measuredValueTypes =
                new HashMap<>(timeseries.size());
        for (final TimeSeries ts : timeseries) {
            measuredValueTypes.put(ts.getObservedProperty(), ts.getMeasuredValueType());
        }
        return measuredValueTypes;
    }

    private Collection<ObservedProperty> getObservedProperties(List<TimeSeries> timeseries) {
        LOG.trace("getObservedProperties(...)");
        final Set<ObservedProperty> observedProperties = new HashSet<>(timeseries.size());
        for (final TimeSeries ts : timeseries) {
            observedProperties.add(ts.getObservedProperty());
        }
        return observedProperties;
    }

    /**
     * <p>isEmpty.</p>
     *
     * @return <code>true</code>, in the case of containing only empty time series
     */
    public boolean isEmpty() {
        for (final TimeSeries timeSeries : getTimeSeries()) {
            if (!timeSeries.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
