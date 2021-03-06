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
package org.n52.sos.importer.model.xml;


import org.junit.Assert;
import org.junit.Test;
import org.n52.sos.importer.model.Step2Model;
import org.x52North.sensorweb.sos.importer.x05.SosImportConfigurationDocument.SosImportConfiguration;

/**
 * <p>Step2ModelHandlerTest class.</p>
 *
 * @author <a href="mailto:e.h.juerrens@52north.org">Eike Hinderk J&uuml;rrens</a>
 * @since 0.5.0
 */
public class Step2ModelHandlerTest {

    private static final String TEST_REGEX = "test-regex";
    private static final String TEST_REGEX_2 = "test-regex-2";
    private static final String TEST_PATTERN = "test-pattern";

    /**
     * <p>shouldSetSampleBasedStartRegEx.</p>
     */
    @Test
    public void shouldSetSampleBasedStartRegEx() {
        final String sampleBasedStartRegEx = TEST_REGEX;
        final Step2Model stepModel = new Step2Model("", 2)
                .setSampleBased(true)
                .setSampleBasedStartRegEx(sampleBasedStartRegEx);
        final SosImportConfiguration importConf = SosImportConfiguration.Factory.newInstance();
        new Step2ModelHandler().handleModel(stepModel, importConf);

        Assert.assertThat(importConf.getDataFile().isSetSampleStartRegEx(), org.hamcrest.CoreMatchers.is(true));
        Assert.assertThat(
                importConf.getDataFile().getSampleStartRegEx(),
                org.hamcrest.CoreMatchers.is(sampleBasedStartRegEx));
    }

    /**
     * <p>shouldSetSampleBasedDateOffset.</p>
     */
    @Test
    public void shouldSetSampleBasedDateOffset() {
        final int dateOffset = 25;
        final Step2Model stepModel = new Step2Model("", 2)
                .setSampleBased(true)
                .setSampleBasedStartRegEx(TEST_REGEX)
                .setSampleBasedDateOffset(dateOffset);
        final SosImportConfiguration importConf = SosImportConfiguration.Factory.newInstance();
        new Step2ModelHandler().handleModel(stepModel, importConf);

        Assert.assertThat(importConf.getDataFile().isSetSampleDateOffset(), org.hamcrest.CoreMatchers.is(true));
        Assert.assertThat(importConf.getDataFile().getSampleDateOffset(), org.hamcrest.CoreMatchers.is(dateOffset));
    }

    /**
     * <p>shouldSetSampleBasedDateExtractionRegEx.</p>
     */
    @Test
    public void shouldSetSampleBasedDateExtractionRegEx() {
        final String dateExtractionRegEx = TEST_REGEX_2;
        final Step2Model stepModel = new Step2Model("", 2)
                .setSampleBased(true)
                .setSampleBasedStartRegEx(TEST_REGEX)
                .setSampleBasedDateOffset(25)
                .setSampleBasedDateExtractionRegEx(dateExtractionRegEx);
        final SosImportConfiguration importConf = SosImportConfiguration.Factory.newInstance();
        new Step2ModelHandler().handleModel(stepModel, importConf);

        Assert.assertThat(
                importConf.getDataFile().isSetSampleDateExtractionRegEx(),
                org.hamcrest.CoreMatchers.is(true));
        Assert.assertThat(
                importConf.getDataFile().getSampleDateExtractionRegEx(),
                org.hamcrest.CoreMatchers.is(dateExtractionRegEx));
    }

    /**
     * <p>shouldSetSampleBasedDatePattern.</p>
     */
    @Test
    public void shouldSetSampleBasedDatePattern() {
        final String datePattern = TEST_REGEX_2;
        final Step2Model stepModel = new Step2Model("", 2)
                .setSampleBased(true)
                .setSampleBasedStartRegEx(TEST_REGEX)
                .setSampleBasedDateOffset(25)
                .setSampleBasedDateExtractionRegEx(TEST_REGEX_2)
                .setSampleBasedDatePattern(datePattern);
        final SosImportConfiguration importConf = SosImportConfiguration.Factory.newInstance();
        new Step2ModelHandler().handleModel(stepModel, importConf);

        Assert.assertThat(importConf.getDataFile().isSetSampleDatePattern(), org.hamcrest.CoreMatchers.is(true));
        Assert.assertThat(importConf.getDataFile().getSampleDatePattern(), org.hamcrest.CoreMatchers.is(datePattern));
    }

    /**
     * <p>shouldSetSampleBasedDataOffset.</p>
     */
    @Test
    public void shouldSetSampleBasedDataOffset() {
        final int dataOffset = 42;
        final Step2Model stepModel = new Step2Model("", 2)
                .setSampleBased(true)
                .setSampleBasedStartRegEx(TEST_REGEX)
                .setSampleBasedDateOffset(25)
                .setSampleBasedDateExtractionRegEx(TEST_REGEX_2)
                .setSampleBasedDatePattern(TEST_PATTERN)
                .setSampleBasedDataOffset(dataOffset);
        final SosImportConfiguration importConf = SosImportConfiguration.Factory.newInstance();
        new Step2ModelHandler().handleModel(stepModel, importConf);

        Assert.assertThat(importConf.getDataFile().isSetSampleDataOffset(), org.hamcrest.CoreMatchers.is(true));
        Assert.assertThat(importConf.getDataFile().getSampleDataOffset(), org.hamcrest.CoreMatchers.is(dataOffset));
    }

    /**
     * <p>shouldSetSampleBasedSampleSizeOffset.</p>
     */
    @Test
    public void shouldSetSampleBasedSampleSizeOffset() {
        final int sampleSizeOffset = 42;
        final Step2Model stepModel = new Step2Model("", 2)
                .setSampleBased(true)
                .setSampleBasedStartRegEx(TEST_REGEX)
                .setSampleBasedDateOffset(25)
                .setSampleBasedDateExtractionRegEx(TEST_REGEX_2)
                .setSampleBasedDatePattern(TEST_PATTERN)
                .setSampleBasedDataOffset(6)
                .setSampleBasedSampleSizeOffset(sampleSizeOffset);
        final SosImportConfiguration importConf = SosImportConfiguration.Factory.newInstance();
        new Step2ModelHandler().handleModel(stepModel, importConf);

        Assert.assertThat(importConf.getDataFile().isSetSampleSizeOffset(), org.hamcrest.CoreMatchers.is(true));
        Assert.assertThat(
                importConf.getDataFile().getSampleSizeOffset(),
                org.hamcrest.CoreMatchers.is(sampleSizeOffset));
    }

    /**
     * <p>shouldSetSampleBasedSampleSizeRegEx.</p>
     */
    @Test
    public void shouldSetSampleBasedSampleSizeRegEx() {
        final String sampleSizeRegEx = TEST_REGEX;
        final Step2Model stepModel = new Step2Model("", 2)
                .setSampleBased(true)
                .setSampleBasedStartRegEx(TEST_REGEX)
                .setSampleBasedDateOffset(25)
                .setSampleBasedDateExtractionRegEx(TEST_REGEX_2)
                .setSampleBasedDatePattern(TEST_PATTERN)
                .setSampleBasedDataOffset(6)
                .setSampleBasedSampleSizeOffset(42)
                .setSampleBasedSampleSizeRegEx(sampleSizeRegEx);
        final SosImportConfiguration importConf = SosImportConfiguration.Factory.newInstance();
        new Step2ModelHandler().handleModel(stepModel, importConf);

        Assert.assertThat(importConf.getDataFile().isSetSampleSizeRegEx(), org.hamcrest.CoreMatchers.is(true));
        Assert.assertThat(
                importConf.getDataFile().getSampleSizeRegEx(),
                org.hamcrest.CoreMatchers.is(sampleSizeRegEx));
    }

}
