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
package org.n52.sos.importer.model.dateAndTime;

import java.util.GregorianCalendar;

import org.n52.sos.importer.model.Combination;
import org.n52.sos.importer.model.table.TableElement;
import org.n52.sos.importer.view.MissingComponentPanel;
import org.n52.sos.importer.view.dateAndTime.MissingTimeZonePanel;

/**
 * UTC offset
 *
 * @author Raimund
 * @version $Id: $Id
 */
public class TimeZone extends DateAndTimeComponent {

    /**
     * <p>Constructor for TimeZone.</p>
     *
     * @param tableElement a {@link org.n52.sos.importer.model.table.TableElement} object.
     * @param pattern a {@link java.lang.String} object.
     */
    public TimeZone(TableElement tableElement, String pattern) {
        super(tableElement, pattern);
    }

    /**
     * <p>Constructor for TimeZone.</p>
     *
     * @param value a int.
     */
    public TimeZone(int value) {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public int getGregorianCalendarField() {
        return GregorianCalendar.ZONE_OFFSET;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Timezone" + super.toString();
    }

    /** {@inheritDoc} */
    @Override
    public MissingComponentPanel getMissingComponentPanel(Combination c) {
        return new MissingTimeZonePanel((DateAndTime) c);
    }
}
