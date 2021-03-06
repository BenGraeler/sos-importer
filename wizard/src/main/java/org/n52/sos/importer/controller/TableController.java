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
package org.n52.sos.importer.controller;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.n52.sos.importer.model.CsvData;
import org.n52.sos.importer.model.table.Cell;
import org.n52.sos.importer.model.table.Column;
import org.n52.sos.importer.model.table.Row;
import org.n52.sos.importer.view.TablePanel;
import org.n52.sos.importer.view.i18n.Lang;

/**
 * provides all necessary functions for modifying the table
 *
 * @author Raimund
 */
public final class TableController {

    /** Constant <code>COLUMNS=1</code> */
    public static final int COLUMNS = 1;
    /** Constant <code>ROWS=2</code> */
    public static final int ROWS = 2;
    /** Constant <code>CELLS=3</code> */
    public static final int CELLS = 3;

    private static TableController instance;


    private int firstLineWithData = -1;

    private final TablePanel tableView;

    private final JTable table;

    private final ColoredTableCellRenderer tableMarker;

    private final Color markingColor = Color.lightGray;

    private SingleSelectionListener singleSelectionListener;

    private MultipleSelectionListener multipleSelectionListener;

    private int tableSelectionMode;

    private int orientation = COLUMNS;

    private TableController(final int firstLineWithData) {
        this.firstLineWithData = firstLineWithData;
        tableView = TablePanel.getInstance();
        table = tableView.getTable();
        tableMarker = new ColoredTableCellRenderer();
        //
        table.setDefaultRenderer(Object.class, null);
        table.setDefaultRenderer(Object.class, tableMarker);
        table.getSelectionModel().addListSelectionListener(new RowSelectionListener());
        table.getColumnModel().getSelectionModel()
            .addListSelectionListener(new ColumnSelectionListener());
        allowMultipleSelection();
    }

    /**
     * <p>Getter for the field <code>instance</code>.</p>
     *
     * @return a {@link org.n52.sos.importer.controller.TableController} object.
     */
    public static TableController getInstance() {
        if (instance == null) {
            instance = new TableController(-1);
        }
        return instance;
    }

    /**
     * <p>setContent.</p>
     *
     * @param content a {@link org.n52.sos.importer.model.CsvData} object.
     */
    public void setContent(final CsvData content) {
        final DefaultTableModel dtm = new EditableTableModel(false);

        final int rows = content.getRowCount();
        final int columns = content.getColumnCount();
        dtm.setColumnCount(columns);

        final String[] columnIdentifiers = new String[columns];
        for (int i = 0; i < columnIdentifiers.length; i++) {
            columnIdentifiers[i] = "n/a";
        }
        dtm.setColumnIdentifiers(columnIdentifiers);

        for (int i = 0; i < rows; i++) {
            dtm.addRow(content.getLine(i));
        }
        table.setModel(dtm);
    }

    /**
     * <p>setColumnHeading.</p>
     *
     * @param column a int.
     * @param heading a {@link java.lang.String} object.
     */
    public void setColumnHeading(final int column, final String heading) {
        table.getColumnModel().getColumn(column).setHeaderValue(heading);
    }

    /**
     * <p>allowSingleSelection.</p>
     */
    public void allowSingleSelection() {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * <p>allowMultipleSelection.</p>
     */
    public void allowMultipleSelection() {
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    /**
     * <p>Setter for the field <code>tableSelectionMode</code>.</p>
     *
     * @param tableSelectionMode a int.
     */
    public void setTableSelectionMode(final int tableSelectionMode) {
        this.tableSelectionMode = tableSelectionMode;

        switch (tableSelectionMode) {
            case ROWS:
                table.setColumnSelectionAllowed(false);
                table.setRowSelectionAllowed(true);
                table.setShowVerticalLines(false);
                table.setShowHorizontalLines(true);
                break;
            case COLUMNS:
                table.setColumnSelectionAllowed(true);
                table.setRowSelectionAllowed(false);
                table.setShowVerticalLines(true);
                table.setShowHorizontalLines(false);
                break;
            case CELLS:
                table.setColumnSelectionAllowed(true);
                table.setRowSelectionAllowed(true);
                table.setShowVerticalLines(true);
                table.setShowHorizontalLines(true);
                break;
            default:
                break;
        }
    }

    /**
     * <p>Getter for the field <code>tableSelectionMode</code>.</p>
     *
     * @return a int.
     */
    public int getTableSelectionMode() {
        return tableSelectionMode;
    }

    /**
     * <p>selectColumn.</p>
     *
     * @param number a int.
     */
    public void selectColumn(final int number) {
        table.addColumnSelectionInterval(number, number);
    }

    /**
     * <p>selectRow.</p>
     *
     * @param number a int.
     */
    public void selectRow(final int number) {
        table.addRowSelectionInterval(number, number);
    }

    /**
     * <p>deselectColumn.</p>
     *
     * @param number a int.
     */
    public void deselectColumn(final int number) {
        table.removeColumnSelectionInterval(number, number);
    }

    /**
     * <p>deselectRow.</p>
     *
     * @param number a int.
     */
    public void deselectRow(final int number) {
        table.removeRowSelectionInterval(number, number);
    }

    /**
     * <p>deselectAllColumns.</p>
     */
    public void deselectAllColumns() {
        final int columns = table.getColumnCount() - 1;
        table.removeColumnSelectionInterval(0, columns);
    }

    /**
     * <p>deselectAllRows.</p>
     */
    public void deselectAllRows() {
        final int rows = table.getRowCount() - 1;
        table.removeColumnSelectionInterval(0, rows);
    }

    /**
     * <p>turnSelectionOff.</p>
     */
    public void turnSelectionOff() {
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setFocusable(false);
    }

    /**
     * <p>turnSelectionOn.</p>
     */
    public void turnSelectionOn() {
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setFocusable(true);
    }

    /**
     * <p>getSelectedColumns.</p>
     *
     * @return an array of int.
     */
    public int[] getSelectedColumns() {
        return table.getSelectedColumns();
    }

    /**
     * <p>getSelectedColumn.</p>
     *
     * @return a int.
     */
    public int getSelectedColumn() {
        return table.getSelectedColumn();
    }

    /**
     * <p>getSelectedRows.</p>
     *
     * @return an array of int.
     */
    public int[] getSelectedRows() {
        return table.getSelectedRows();
    }

    /**
     * <p>getSelectedRow.</p>
     *
     * @return a int.
     */
    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    /**
     * <p>getMarkedValues.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<String> getMarkedValues() {
        final ArrayList<String> values = new ArrayList<>();

        switch (tableSelectionMode) {
            case COLUMNS:
                final int rowCount = table.getRowCount();
                for (final Column c: tableMarker.getColumns()) {
                    for (int i = 0; i < rowCount; i++) {
                        values.add((String) table.getValueAt(i, c.getNumber()));
                    }
                }
                break;

            case ROWS:
                final int columnCount = table.getColumnCount();
                for (final Row r: tableMarker.getRows()) {
                    for (int i = 0; i < columnCount; i++) {
                        values.add((String) table.getValueAt(r.getNumber(), i));
                    }
                }
                break;

            case CELLS:
                for (final Cell c: tableMarker.getCells()) {
                    values.add(getValueAt(c));
                }
                break;

            default:
                break;
        }
        return values;
    }

    /**
     * <p>getSelectedCellValue.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSelectedCellValue() {
        final int column = table.getSelectedColumn();
        final int row = table.getSelectedRow();
        return (String) table.getValueAt(row, column);
    }

    /**
     * <p>getValueAt.</p>
     *
     * @param c a {@link org.n52.sos.importer.model.table.Cell} object.
     * @return a {@link java.lang.String} object.
     */
    public String getValueAt(final Cell c) {
        return (String) table.getValueAt(c.getRow(), c.getColumn());
    }

    /**
     * <p>getValueAt.</p>
     *
     * @param row a int.
     * @param column a int.
     * @return a {@link java.lang.String} object.
     */
    public String getValueAt(final int row, final int column) {
        return (String) table.getValueAt(row, column);
    }

    /**
     * <p>getRowCount.</p>
     *
     * @return a int.
     */
    public int getRowCount() {
        return table.getRowCount();
    }

    /**
     * <p>getColumnCount.</p>
     *
     * @return a int.
     */
    public int getColumnCount() {
        return table.getColumnCount();
    }

    /**
     * <p>mark.</p>
     *
     * @param c a {@link org.n52.sos.importer.model.table.Column} object.
     */
    public void mark(final Column c) {
        tableMarker.addColumn(c);
    }

    /**
     * <p>mark.</p>
     *
     * @param r a {@link org.n52.sos.importer.model.table.Row} object.
     */
    public void mark(final Row r) {
        tableMarker.addRow(r);
    }

    /**
     * <p>mark.</p>
     *
     * @param c a {@link org.n52.sos.importer.model.table.Cell} object.
     */
    public void mark(final Cell c) {
        tableMarker.addCell(c);
    }

    /**
     * <p>clearMarkedTableElements.</p>
     */
    public void clearMarkedTableElements() {
        tableMarker.removeTableElements();
    }

    /**
     * <p>addSingleSelectionListener.</p>
     *
     * @param additionalSingleSelectionListener
     *          a {@link org.n52.sos.importer.controller.TableController.SingleSelectionListener} object.
     */
    public void addSingleSelectionListener(final SingleSelectionListener additionalSingleSelectionListener) {
        this.singleSelectionListener = additionalSingleSelectionListener;
    }

    /**
     * <p>addMultipleSelectionListener.</p>
     *
     * @param additionalMultipleSelectionListener
     *          a {@link org.n52.sos.importer.controller.TableController.MultipleSelectionListener} object.
     */
    public void addMultipleSelectionListener(final MultipleSelectionListener additionalMultipleSelectionListener) {
        this.multipleSelectionListener = additionalMultipleSelectionListener;
    }

    /**
     * <p>removeMultipleSelectionListener.</p>
     */
    public void removeMultipleSelectionListener() {
        multipleSelectionListener = null;
    }

    /**
     * <p>Setter for the field <code>orientation</code>.</p>
     *
     * @param orientation a int.
     */
    public void setOrientation(final int orientation) {
        this.orientation = orientation;
    }

    /**
     * <p>Getter for the field <code>orientation</code>.</p>
     *
     * @return a int.
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * <p>getOrientationString.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOrientationString() {
        switch (orientation) {
            case ROWS:
                return Lang.l().row();
            case COLUMNS:
            default:
                return Lang.l().column();
        }
    }

    /**
     * <p>Getter for the field <code>firstLineWithData</code>.</p>
     *
     * @return the firstLineWithData
     */
    public int getFirstLineWithData() {
        return firstLineWithData;
    }

    /**
     * <p>Setter for the field <code>firstLineWithData</code>.</p>
     *
     * @param firstLineWithData the firstLineWithData to set
     */
    public void setFirstLineWithData(final int firstLineWithData) {
        this.firstLineWithData = firstLineWithData;
    }

    /**
     * <p>getAllColumnHeadings.</p>
     *
     * @return an array of {@link java.lang.String} objects.
     */
    public String[] getAllColumnHeadings() {
        return getColumnHeadingsFiltered(null, false);
    }

    /**
     * <p>getUsedColumnHeadings.</p>
     *
     * @return an array of {@link java.lang.String} objects.
     */
    public String[] getUsedColumnHeadings() {
        return getColumnHeadingsFiltered(Lang.l().step3ColTypeDoNotExport(), false);
    }

    /**
     * <p>getColumnHeadingsFiltered.</p>
     *
     * @param typeToLeaveOut a {@link java.lang.String} object.
     * @param withColId a boolean.
     * @return an array of {@link java.lang.String} objects.
     */
    public String[] getColumnHeadingsFiltered(final String typeToLeaveOut, final boolean withColId) {
        final int colCount = table.getColumnCount();
        final TableColumnModel tcm = table.getColumnModel();
        // Check for null and empty strings
        final boolean filter = typeToLeaveOut == null ?
                false : !typeToLeaveOut.equalsIgnoreCase("");
        final ArrayList<String> headings = new ArrayList<>(colCount);
        final String colon = ": ";
        if (!filter) {
            for (int i = 0; i < colCount; i++) {
                String tmp = tcm.getColumn(i).getHeaderValue().toString();
                if (withColId) {
                    tmp = i + colon + tmp;
                }
                headings.add(tmp);
            }
        } else {
            for (int i = 0; i < colCount; i++) {
                String tmp = tcm.getColumn(i).getHeaderValue().toString();
                if (!tmp.equalsIgnoreCase(typeToLeaveOut)) {
                    if (withColId) {
                        tmp = i + colon + tmp;
                    }
                    headings.add(tmp);
                }
            }
        }
        headings.trimToSize();
        return headings.toArray(new String[headings.size()]);
    }

    /**
     * <p>getUsedColumnHeadingsWithId.</p>
     *
     * @return an array of {@link java.lang.String} objects.
     */
    public String[] getUsedColumnHeadingsWithId() {
        return getColumnHeadingsFiltered(Lang.l().step3ColTypeDoNotExport(), true);
    }

    private class ColoredTableCellRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        private final HashSet<Column> columns;

        private final HashSet<Row> rows;

        private final HashSet<Cell> cells;

        ColoredTableCellRenderer() {
            columns = new HashSet<>();
            rows = new HashSet<>();
            cells = new HashSet<>();
        }

        public void addColumn(final Column c) {
            columns.add(c);
        }

        public HashSet<Column> getColumns() {
            return columns;
        }

        public void addRow(final Row r) {
            rows.add(r);
        }

        public HashSet<Row> getRows() {
            return rows;
        }

        public void addCell(final Cell c) {
            cells.add(c);
        }

        public HashSet<Cell> getCells() {
            return cells;
        }

        public void removeTableElements() {
            columns.clear();
            rows.clear();
            cells.clear();
        }

        @Override
        public Component getTableCellRendererComponent (
                final JTable aTable,
                final Object value,
                final boolean selected,
                final boolean focused,
                final int row,
                final int column) {
            setEnabled(aTable == null || aTable.isEnabled());

            if (rows.contains(new Row(row)) ||
                    columns.contains(new Column(column, firstLineWithData)) ||
                    cells.contains(new Cell(row, column))) {
                setBackground(markingColor);
            } else {
                setBackground(null);
            }

            super.getTableCellRendererComponent(aTable, value, selected, focused, row, column);
            return this;
        }
    }

    private static class EditableTableModel extends DefaultTableModel {

        private static final long serialVersionUID = 1L;

        private final boolean editable;

        EditableTableModel(final boolean editable) {
            super();
            this.editable = editable;
        }

        @Override
        public boolean isCellEditable(final int row, final int column) {
            return editable;
        }
    }

    private class ColumnSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(final ListSelectionEvent arg0) {
            if (table.getColumnSelectionAllowed() && arg0.getValueIsAdjusting()) {
                if (table.getSelectionModel().getSelectionMode() == ListSelectionModel.SINGLE_SELECTION &&
                        singleSelectionListener != null) {
                    singleSelectionListener.columnSelectionChanged(table.getSelectedColumn());
                } else if (table.getSelectionModel().getSelectionMode() ==
                        ListSelectionModel.MULTIPLE_INTERVAL_SELECTION &&
                        multipleSelectionListener != null) {
                    multipleSelectionListener.columnSelectionChanged(table.getSelectedColumns());
                }
            }
        }
    }

    private class RowSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(final ListSelectionEvent arg0) {
            if (table.getRowSelectionAllowed() && arg0.getValueIsAdjusting()) {
                if (table.getSelectionModel().getSelectionMode() == ListSelectionModel.SINGLE_SELECTION &&
                        singleSelectionListener != null) {
                    singleSelectionListener.rowSelectionChanged(table.getSelectedRow());
                } else if (table.getSelectionModel().getSelectionMode() ==
                        ListSelectionModel.MULTIPLE_INTERVAL_SELECTION &&
                        multipleSelectionListener != null) {
                    multipleSelectionListener.rowSelectionChanged(table.getSelectedRows());
                }
            }
        }
    }

    public interface SingleSelectionListener {
        void columnSelectionChanged(int selectedColumn);

        void rowSelectionChanged(int selectedRow);
    }

    public interface MultipleSelectionListener {
        void columnSelectionChanged(int[] selectedColumns);

        void rowSelectionChanged(int[] selectedRows);
    }
}
