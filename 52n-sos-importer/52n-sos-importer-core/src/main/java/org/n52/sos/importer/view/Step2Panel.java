/**
 * Copyright (C) 2012
 * by 52North Initiative for Geospatial Open Source Software GmbH
 *
 * Contact: Andreas Wytzisk
 * 52 North Initiative for Geospatial Open Source Software GmbH
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 *
 * This program is free software; you can redistribute and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program (see gnu-gpl v2.txt). If not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA or
 * visit the Free Software Foundation web page, http://www.fsf.org.
 */
package org.n52.sos.importer.view;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.n52.sos.importer.combobox.EditableComboBoxItems;
import org.n52.sos.importer.combobox.EditableJComboBoxPanel;
import org.n52.sos.importer.view.utils.ToolTips;

/**
 * offers settings for parsing the CSV file and 
 * displays a preview of the CSV file
 * @author Raimund, Eike
 *
 */
public class Step2Panel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final EditableJComboBoxPanel columnSeparatorCombobox;
	private final EditableJComboBoxPanel commentIndicatorCombobox;
	private final EditableJComboBoxPanel textQualifierCombobox;
	
	private final JTextArea csvFileTextArea = new JTextArea(7, 70);
	
	private SpinnerNumberModel lineModel;
	private JSpinner firstDataJS;
	private final JLabel firstDataJL;
	
	public Step2Panel(final int csvFileRowCount) {
		super();
		
		EditableComboBoxItems items = EditableComboBoxItems.getInstance();
		columnSeparatorCombobox = new EditableJComboBoxPanel(items.getColumnSeparators(), "Column separator", ToolTips.get("ColumnSeparator"));
		commentIndicatorCombobox = new EditableJComboBoxPanel(items.getCommentIndicators(), "Comment indicator", ToolTips.get("CommentIndicator"));
		textQualifierCombobox = new EditableJComboBoxPanel(items.getTextQualifiers(), "Text qualifier", ToolTips.get("TextQualifier"));
		
		lineModel = new SpinnerNumberModel(0, 0, csvFileRowCount-1, 1);
		firstDataJS = new JSpinner(lineModel);
		/*
		firstDataJS.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int number = lineModel.getNumber().intValue();
				if(number < 0) {
					lineModel.setValue(0);
				} else if (number > (csvFileRowCount-1)){
					lineModel.setValue((csvFileRowCount-1));
				}
			}
		});
		*/
		firstDataJL = new JLabel("First Line with data:");
		
		JPanel firstLineWithDataJPanel = new JPanel();
		firstLineWithDataJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		firstLineWithDataJPanel.add(firstDataJL);
		firstLineWithDataJPanel.add(firstDataJS);
		
		csvFileTextArea.setEditable(false);		
		
		JPanel csvSettingsPanel = new JPanel();
		csvSettingsPanel.setLayout(new GridLayout(4,1));
		csvSettingsPanel.add(columnSeparatorCombobox);
		csvSettingsPanel.add(commentIndicatorCombobox);
		csvSettingsPanel.add(textQualifierCombobox);
		csvSettingsPanel.add(firstLineWithDataJPanel);
		this.add(csvSettingsPanel);
		
		JScrollPane scrollPane = new JScrollPane(csvFileTextArea);
		this.add(scrollPane);
	}
	
	/**
	 * @return user input or <code>-1</code> if invalid input is defined
	 */
	public int getFirstLineWithData() {
		return lineModel.getNumber().intValue();
	}
	
	public String getSelectedColumnSeparator() {
		return (String) columnSeparatorCombobox.getSelectedItem();
	}
	
	public String getSelectedCommentIndicator() {
		return (String) commentIndicatorCombobox.getSelectedItem();
	}
	
	public String getSelectedTextQualifier() {
		return (String) textQualifierCombobox.getSelectedItem();
	}
	
	public void setFirstLineWithData(int firstLineWithData) {
		lineModel.setValue(firstLineWithData);
	}
	
	public void setSelectedColumnSeparator(String columnSeparator) {
		columnSeparatorCombobox.setSelectedItem(columnSeparator);
	}
	
	public void setSelectedCommentIndicator(String commentIndicator) {
		commentIndicatorCombobox.setSelectedItem(commentIndicator);
	}
	
	public void setSelectedTextQualifier(String textQualifier) {
		textQualifierCombobox.setSelectedItem(textQualifier);
	}
		
	public String getCSVFileContent() {
		return csvFileTextArea.getText();
	}
	
	public void setCSVFileContent(String content) {
		csvFileTextArea.setText(content);
		csvFileTextArea.setCaretPosition(0);	
	}
}