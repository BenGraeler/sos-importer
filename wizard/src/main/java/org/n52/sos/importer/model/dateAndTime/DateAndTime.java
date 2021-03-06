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
 *
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk J&uuml;rrens</a>
 */
package org.n52.sos.importer.model.dateAndTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.n52.sos.importer.model.Combination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateAndTime extends Combination {

    private static final String FROM = " from ";
    private static final String REMOVE = "Remove ";
    private static final String TO = " to ";
    private static final String ADD = "Add ";

    private static final Logger LOG = LoggerFactory.getLogger(DateAndTime.class);

    private Year year;
    private Month month;
    private Day day;
    private Hour hour;
    private Minute minute;
    private Second second;
    private TimeZone timeZone;

    /**
     * <p>Getter for the field <code>year</code>.</p>
     *
     * @return a {@link org.n52.sos.importer.model.dateAndTime.Year} object.
     */
    public Year getYear() {
        return year;
    }

    /**
     * <p>Setter for the field <code>year</code>.</p>
     *
     * @param year a {@link org.n52.sos.importer.model.dateAndTime.Year} object.
     */
    public void setYear(final Year year) {
        if (getGroup() != null) {
            if (year != null) {
                LOG.info(ADD + year + TO + this);
            } else {
                LOG.info(REMOVE + this.year + FROM + this);
            }
        }
        this.year = year;
    }

    /**
     * <p>Getter for the field <code>month</code>.</p>
     *
     * @return a {@link org.n52.sos.importer.model.dateAndTime.Month} object.
     */
    public Month getMonth() {
        return month;
    }

    /**
     * <p>Setter for the field <code>month</code>.</p>
     *
     * @param month a {@link org.n52.sos.importer.model.dateAndTime.Month} object.
     */
    public void setMonth(final Month month) {
        if (getGroup() != null) {
            if (month != null) {
                LOG.info(ADD + month + TO + this);
            } else {
                LOG.info(REMOVE + this.month + FROM + this);
            }
        }
        this.month = month;
    }

    /**
     * <p>Getter for the field <code>day</code>.</p>
     *
     * @return a {@link org.n52.sos.importer.model.dateAndTime.Day} object.
     */
    public Day getDay() {
        return day;
    }

    /**
     * <p>Setter for the field <code>day</code>.</p>
     *
     * @param day a {@link org.n52.sos.importer.model.dateAndTime.Day} object.
     */
    public void setDay(final Day day) {
        if (getGroup() != null) {
            if (day != null) {
                LOG.info(ADD + day + TO + this);
            } else {
                LOG.info(REMOVE + this.day + FROM + this);
            }
        }
        this.day = day;
    }

    /**
     * <p>Getter for the field <code>hour</code>.</p>
     *
     * @return a {@link org.n52.sos.importer.model.dateAndTime.Hour} object.
     */
    public Hour getHour() {
        return hour;
    }

    /**
     * <p>Setter for the field <code>hour</code>.</p>
     *
     * @param hour a {@link org.n52.sos.importer.model.dateAndTime.Hour} object.
     */
    public void setHour(final Hour hour) {
        if (getGroup() != null) {
            if (hour != null) {
                LOG.info(ADD + hour + TO + this);
            } else {
                LOG.info(REMOVE + this.hour + FROM + this);
            }
        }
        this.hour = hour;
    }

    /**
     * <p>Getter for the field <code>minute</code>.</p>
     *
     * @return a {@link org.n52.sos.importer.model.dateAndTime.Minute} object.
     */
    public Minute getMinute() {
        return minute;
    }

    /**
     * <p>Setter for the field <code>minute</code>.</p>
     *
     * @param minute a {@link org.n52.sos.importer.model.dateAndTime.Minute} object.
     */
    public void setMinute(final Minute minute) {
        if (getGroup() != null) {
            if (minute != null) {
                LOG.info(ADD + minute + TO + this);
            } else {
                LOG.info(REMOVE + this.minute + FROM + this);
            }
        }
        this.minute = minute;
    }

    /**
     * <p>getSeconds.</p>
     *
     * @return a {@link org.n52.sos.importer.model.dateAndTime.Second} object.
     */
    public Second getSeconds() {
        return second;
    }

    /**
     * <p>Setter for the field <code>second</code>.</p>
     *
     * @param second a {@link org.n52.sos.importer.model.dateAndTime.Second} object.
     */
    public void setSecond(final Second second) {
        if (getGroup() != null) {
            if (second != null) {
                LOG.info(ADD + second + TO + this);
            } else {
                LOG.info(REMOVE + this.second + FROM + this);
            }
        }
        this.second = second;
    }

    /**
     * <p>Getter for the field <code>timeZone</code>.</p>
     *
     * @return a {@link org.n52.sos.importer.model.dateAndTime.TimeZone} object.
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * <p>Setter for the field <code>timeZone</code>.</p>
     *
     * @param timeZone a {@link org.n52.sos.importer.model.dateAndTime.TimeZone} object.
     */
    public void setTimeZone(final TimeZone timeZone) {
        if (getGroup() != null) {
            if (timeZone != null) {
                LOG.info(ADD + timeZone + TO + this);
            } else {
                LOG.info(REMOVE + this.timeZone + FROM + this);
            }
        }
        this.timeZone = timeZone;
    }

    @Override
    public Object parse(final String s) {
        Date dateTime = null;
        final String currentPattern = getPattern();
        final SimpleDateFormat formatter =
                new SimpleDateFormat(currentPattern);
        try {
            dateTime = formatter.parse(s);
        } catch (final ParseException e) {
            throw new NumberFormatException(e.getLocalizedMessage());
        }
        return dateTime;
    }

    @Override
    public String format(final Object o) {
        final Date date = (Date) o;
        final SimpleDateFormat formatter =
                new SimpleDateFormat(getPattern());
        final String dateString = formatter.format(date);

        return dateString;
    }

    @Override
    public String toString() {
        if (getGroup() == null) {
            return "Date and Time(" + year + ", " + month + ", "
                    + day + ", " + hour + ", " + minute + ", "
                    + second + ", " + timeZone + ")";
        } else {
            return "Date&Time group " + getGroup();
        }
    }

    /**
     * @return true, if this is a unix time {@link DateAndTime}, else false.
     */
    public boolean isUnixTime() {
        return year == null &&
                month == null &&
                day == null &&
                hour == null &&
                minute == null &&
                second == null &&
                timeZone == null;
    }
}
