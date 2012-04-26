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
package org.n52.sos.importer.view.i18n;

import java.io.File;
import java.util.Locale;

import org.n52.sos.importer.view.utils.Constants;

/**
 * @author e.h.juerrens@52north.org
 * This class contains all String used by the GUI in German
 */
public class De extends Lang{
	
	private final static Locale locale = Locale.GERMAN;
	
	@Override
	public String altitude() {
		return "Höhe";
	}
	
	@Override
	public String and() {
		return "und";
	}

	/**
	 * @return Back
	 */
	public String backButtonLabel() {
		return "Zurück";
	}

	@Override
	public String column() {
		return "Spalte";
	}

	@Override
	public String day() {
		return "Tag";
	}

	@Override
	public String epsgCode() {
		return "EPSG-Kennzahl";
	}

	@Override
	public String epsgCodeWarningDialogNaturalNumber() {
		return "Die " + this.epsgCode() + " muss eine natürliche Zahl sein.";
	}

	@Override
	public String epsgCodeWarningDialogOutOfRange() {
		return "Die " + this.epsgCode() + " sollte größer als 0 und kleiner als 32767 sein.";
	}

	@Override
	public String error() {
		return "Fehler";
	}

	/**
	 * @return Error
	 */
	public String errorDialogTitle() {
		return "Fehler";
	}

	@Override
	public String example() {
		return "Beispiel";
	}

	/**
	 * @return Do you really want to exit?\n
	 */
	public String exitDialogQuestion() { 
		return "Wollen Sie das Programm wirklich beenden?\n";
	}

	/**
	 * @return Exit
	 */
	public String exitDialogTitle() { return "Beenden"; }

	/**
	 * @return Feature of Interest
	 */
	public String featureOfInterest() {
		return "Feature of Interest";
	}

	/**
	 * @return file
	 */
	public String file() {
		return "Datei";
	}

	/**
	 * @return Finish
	 */
	public String finishButtonLabel() {
		return "Beenden";
	}

	@Override
	public String format() {
		return "Format";
	}
	
	@Override
	public Locale getLocale() {
		return De.locale;
	}

	@Override
	public String group() {
		return "Gruppe";
	}

	@Override
	public String heightWarningDialogDecimalNumber() {
		return "Die " + this.altitude() + " kann zur Zeit nur als Dezimalzahl angegeben werden.";
	}

	@Override
	public String hours() {
		return "Stunden";
	}

	/**
	 * @return Info
	 */
	public String infoDialogTitle() {
		return "Information";
	}

	@Override
	public String latitudeDialogDecimalValue() {
		return "Der " + this.latitudeNorthing() + " kann zur Zeit nur als Dezimalzahl angegeben werden.";
	}

	@Override
	public String latitudeNorthing() {
		return "Breitengrad / Hochwert";
	}

	@Override
	public String longitudeDialogDecimalValue() {
		return "Der " + this.longitudeEasting() + " kann zur Zeit nur als Dezimalzahl angegeben werden.";
	}

	@Override
	public String longitudeEasting() {
		return "Längengrad / Rechtswert";
	}

	/**
	 * @return measured value
	 */
	public String measuredValue() {
		return "Messwert";
	}

	@Override
	public String minutes() {
		return "Minuten";
	}

	@Override
	public String month() {
		return "Monat";
	}

	/**
	 * @return Next
	 */
	public String nextButtonLabel() {
		return "Weiter";
	}

	/**
	 * @return Decimal separator
	 */
	public String numValuePanelDecimalSeparator() {
		return "Dezimal-Separator";
	}

	/**
	 * @return	Thousands separator
	 */
	public String numValuePanelThousandsSeparator() {
		return "Tausender-Trennzeichen";
	}

	public String observation() {
		return "Beobachtung";
	}

	/**
	 * @return Observed Property
	 */
	public String observedProperty() {
		return "Observed Property";
	}

	/**
	 * @return path
	 */
	public String path() {
		return "Pfad";
	}
	
	/**
	 * @return Position
	 */
	public String position() {
		return "Position";
	}

	@Override
	public String referenceSystem() {
		return "Referenzsystem";
	}

	@Override
	public String row() {
		return "Zeile";
	}

	@Override
	public String seconds() {
		return "Sekunden";
	}

	/**
	 * @return Sensor
	 */
	public String sensor() {
		return "Sensor";
	}

	@Override
	public String sosURL() {
		return "SOS-Web-Adresse (inkl. Endpoint, z.B. ../sos)";
	}

	/**
	 * @return Space
	 */
	public String spaceString() {
		return "Leerzeichen";
	}

	public String step() {
		return "Schritt";
	}

	/**
	 * @return Browse
	 */
	public String step1BrowseButton() {
		return "Datendatei auswählen";
	}

	/**
	 * @return Step 1: Choose CSV file
	 */
	public String step1Description() {
		return "Schritt 1: Wählen Sie die CSV-Datei aus";
	}

	/**
	 * @return CSV File
	 */
	public String step1File() {
		return "CSV-Datei";
	}
	
	@Override
	public String step1SelectLanguage() {
		return "Bitte wählen Sie die Sprache aus";
	}

	/**
	 * @return Column separator
	 */
	public String step2ColumnSeparator() {
		return "Spalten-Trenner";
	}

	/**
	 * @return Comment indicator
	 */
	public String step2CommentIndicator() {
		return "Kommentar-Indikator";
	}

	@Override
	public String step2DataPreviewLabel() {
		return "Datendatei-Vorschau";
	}

	/**
	 * @return Step 2: Import CSV file
	 */
	public String step2Description() {
		return this.step() + " 2: CSV-Datei importieren";
	}

	/**
	 * @return First Line with data
	 */
	public String step2FirstLineWithData() {
		return "Erste Zeile mit Daten";
	}

	/**
	 * @return Parse Header
	 */
	public String step2ParseHeader() {
		return "Kopfzeile auswerten";
	}

	/**
	 * @return Text qualifier
	 */
	public String step2TextQualifier() {
		return "Text-Qualifier";
	}

	/**
	 * @return Step 3a: Choose Metadata for the selected column
	 */
	public String step3aDescription() {
		return this.step() + " 3a: Wählen Sie die Metainformationen für die aktuelle Spalte";
	}

	@Override
	public String step3aMeasureValueColMissingDialogMessage() {
		return "Es muss mindestens 1 " + this.measuredValue() + "-Spalte definiert werden!";
	}

	@Override
	public String step3aMeasureValueColMissingDialogTitle() {
		return this.measuredValue() + "-Spalte fehlt!";
	}

	@Override
	public String step3aParseTest1Failed() {
		return "1 Wert nicht einlesbar.";
	}

	@Override
	public String step3aParseTestAllOk() {
		return "Alle Werte einlesbar.";
	}

	@Override
	public String step3aParseTestNFailed(int n) {
		return n + " Werte nicht einlesbar.";
	}

	@Override
	public String step3aSelectedColTypeUndefinedMsg() {
		return "Der Typ für die Spalte ist \"" + 
				this.step3ColTypeUndefined() + 
				"\".\nBitte wählen Sie einen anderen Typ. Sollten Sie diese" +
				"Spalte überspringen (= nicht exportieren) wollen, dann " +
				"wählen Sie bitte als Type \n\"" +
				this.step3ColTypeDoNotExport() + 
				"\".";
	}

	@Override
	public String step3aSelectedColTypeUndefinedTitle() {
		return "Spalten-Typ ist \"" + 
				this.step3ColTypeUndefined() + 
				"\"";
	}

	/**
	 * @return Step 3b: Choose metadata for rows
	 */
	public String step3bDescription() {
		return this.step() + " 3b: Wählen Sie die Metainformationen für die aktuelle Zeile";
	}

	/**
	 * @return Date & Time
	 */
	public String step3ColTypeDateTime() {
		return "Datum & Zeit";
	}

	/**
	 * @return Do not export
	 */
	public String step3ColTypeDoNotExport() {
		return "Nicht exportieren";
	}

	/**
	 * @return Measured Value
	 */
	public String step3ColTypeMeasuredValue() {
		return this.measuredValue();
	}

	/**
	 * @return Undefined
	 */
	public String step3ColTypeUndefined() {
		return "Unbekannt";
	}

	/**
	 * @return Combination
	 */
	public String step3DateAndTimeCombination() {
		return "Kombination";
	}

	/**
	 * @return UNIX time
	 */
	public String step3DateAndTimeUnixTime() {
		return "UNIX-Zeit";
	}

	/**
	 * @return Boolean
	 */
	public String step3MeasuredValBoolean() {
		return "Boolean";
	}

	/**
	 * @return Count
	 */
	public String step3MeasuredValCount() {
		return "Count";
	}

	/**
	 * @return Numeric Value
	 */
	public String step3MeasuredValNumericValue() {
		return "Numeric Value";
	}

	/**
	 * @return Text
	 */
	public String step3MeasuredValText() {
		return "Text";
	}

	/**
	 * @return {@linkplain org.n52.sos.importer.view.i18n.En.step3DateAndTimeCombination()}
	 * 	<br />:= Combination
	 */
	public String step3PositionCombination() {
		return this.step3DateAndTimeCombination();
	}

	/**
	 * @return Step 4a: Solve Date & Time ambiguities
	 */
	public String step4aDescription() {
		return this.step() + " 4a: Kläre Datum & Zeit Unklarheit";
	}

	/**
	 * @return Date and Time are already set for this <code>En.measuredValue()</code>.
	 * @see {@link org.n52.sos.importer.view.i18n.En.measuredValue()}
	 */
	public String step4aInfoDateAndTime() {
		return "Datum und Zeit sind schon gesetzt für diesen " + this.measuredValue() + ".";
	}

	/**
	 * @param element 
	 * @return This is not a <code>En.measuredValue()</code>.
	 * @see {@link org.n52.sos.importer.view.i18n.En.measuredValue()}
	 */
	public String step4aInfoMeasuredValue() {
		return "Dies ist kein " + this.measuredValue() + ".";
	}

	/**
	 * @param stringReplacer
	 * @return Select all measured value <code>Constants.STRING_REPLACER</code>s where the marked Date & Time group corresponds to.
	 */
	public String step4aModelDescription() {
		return "Wähle alle " + this.measuredValue() + " " + Constants.STRING_REPLACER + "n " +
				", die zur markierten Datum-Zeit-Gruppe gehören.";
	}

	/**
	 * @return Step 4b: Solve ambiguities
	 */
	public String step4bDescription() {
		return this.step() + " 4b: Käre Unklarheiten";
	}

	/**
	 * @return This is not a <code>En.measuredValue()</code>.
	 * @see {@link org.n52.sos.importer.view.i18n.En.measuredValue()}
	 * @see {@link org.n52.sos.importer.view.i18n.En.step4aInfoMeasuredValue()}
	 * 
	 */
	public String step4bInfoNotMeasuredValue() {
		return this.step4aInfoMeasuredValue();
	}

	@Override
	protected String step4bInfoResourceAlreadySetText() {
		return " schon gesetzt für ";
	}

	@Override
	public String step4bModelDescription() {
		return "Bitte klicken Sie in die " + 
				Constants.STRING_REPLACER + 
			" (nicht auf die Titel), die die Messwerte enthält, die zu der " +
			"hervorgehobenen " + 
			Constants.STRING_REPLACER +
			"-" +
			Constants.STRING_REPLACER + 
			" gehört. Wenn mehrere Messwert-" +
			Constants.STRING_REPLACER + 
			"n dazugehören, wählen Sie sie mit gedrückter Strg-Taste aus.";
	}

	/**
	 * @return Step 5a: Complete time data
	 */
	public String step5aDescription() {
		return this.step() + " 5a: Zeit-Informationen vervollständigen";
	}

	/**
	 * @return Complete missing information for the marked date and time.
	 */
	public String step5aModelDescription() {
		return "Vervollständige die fehlenden Zeit-Datum-Informationen für die markierten Elemente.";
	}

	/**
	 * @return Step 5c: Complete position data
	 */
	public String step5cDescription() {
		return this.step() + " 5c: Positionsdaten vervollständigen";
	}

	/**
	 * @return Complete missing information for the marked position.
	 */
	public String step5cModelDescription() {
		return "Vervollständige die fehlenden Positions-Informationen für die markierten Elemente.";
	}

	/**
	 * @return Step 6a: Add missing dates and times
	 */
	public String step6aDescription() {
		return this.step() + " 6a: Fehlende Zeiten und Daten hinzufügen";
	}

	/**
	 * @return Step 6b: Add missing metadata
	 */
	public String step6bDescription() {
		return this.step() + " 6b: Fehlende Metainformationen hinzufügen";
	}

	/**
	 * Replacements: Resource &rarr; Orientation
	 * @return &lt;html&gt;What is the &lt;u&gt;<code>Constants.STRING_REPLACER</code>&lt;/u&gt; for the marked measured value <code>Constants.STRING_REPLACER</code>?&lt;/html&gt;
	 */
	public String step6bModelDescription() {
		return "<html>Welche(r/s) <u>" + 
				Constants.STRING_REPLACER + 
				"</u> gehört zur markierten " + this.measuredValue() + " " +
				Constants.STRING_REPLACER + 
				"?</html>";
	}

	/**
	 * @return Step 6b (Special): Add missing sensors
	 */
	public String step6bSpecialDescription() {
		return this.step() + " 6b (Spezial): Fehlende " + this.sensor() + "en hinzufügen";
	}

	/**
	 * @return What is the sensor for
	 */
	public String step6bSpecialModelDescription() {
		return "Welches ist der " + this.sensor() + " für";
	}

	/**
	 * @return Step 6c: Add missing positions
	 */
	public String step6cDescription() {
		return this.step() + " 6c: Fehlende " + this.position() + "s-Angaben hinzufügen";
	}

	/**
	 * @return What is the position of
	 */
	public String step6cModelDescription() {
		return "Wie ist die " + this.position() + "s-Angabe von";
	}

	@Override
	public String step7ConfigDirNotDirOrWriteable(String folder) {
		return "Auf das Verzeichnis \n\"" + 
				folder + "\"\n kann nicht zugegriffen werden";
	}

	@Override
	public String step7ConfigFileButton() {
		return "Verzeichnis wählen";
	}

	@Override
	public String step7ConfigFileDialogTitel() {
		return "Bitte das Verzeichnis für die Konfigurations-Datei wählen";
	}

	@Override
	public String step7ConfigFileLabel() {
		return "Konfigurations-Dateiname und -verzeichnis";
	}

	/**
	 * @return Step 7: Choose Sensor Observation Service
	 */
	public String step7Description() {
		return this.step() + " 7: Geben Sie den " + this.sos() + " an";
	}

	@Override
	public String step7DirectImport() {
		return "Im nächsten Schritt die Daten in den SOS importieren";
	}

	@Override
	public String step7SaveConfig() {
		return "Konfiguration in XML-Datei speichern";
	}

	/**
	 * @param strURL
	 * @param responseCode
	 * @return Could not connect to Sensor Observation Service: <code>strURL</code> . HTTP Response Code: <code>responseCode</code>
	 */
	public String step7SOSconnectionFailed(String strURL,
			int responseCode) {
		return "Could not connect to " + this.sos() + ": "
        		+ strURL + 
        		". HTTP Response Code: " 
        		+ responseCode;
	}

	/**
	 * @param strURL
	 * @param message
	 * @return Connection to Sensor Observation Service <code>strURL</code> failed. Reason: <code>message</code>
	 */
	public String step7SOSConnectionFailedException(String strURL,
			String message) {
		return "Connection to " + this.sos() + " " + strURL + " failed. Reason: " + message;
	}

	@Override
	public String step8ConfigFileButton() {
		return "Konfigurationsdatei öffnen";
	}

	/**
	 * @return Step 8: Register Sensors and Insert Observations into Sensor Observation Service
	 */
	public String step8Description() {
		return this.step() + " 8: Registriere " + this.sensor() + "en und lade " + this.observation() + "en in den " + this.sos();
	}

	/**
	 * @param i
	 * @return Errors: <code>i</code>
	 */
	public String step8ErrorLable(int i) {
		return "Fehler: " + i;
	}

	/**
	 * @param i
	 * @return Insert <code>i</code> Observations...
	 */
	public String step8InsertObservationLabel(int i) {
		return "Füge " + i + " " + this.observation() + "en hinzu...";
	}

	/**
	 * @return Check log file
	 */
	public String step8LogFileButton() {
		return "Prüfen Sie die Logdatei";
	}

	/**
	 * @param i
	 * @return Register <code>i</code> Sensors...
	 */
	public String step8RegisterSensorLabel(int i) {
		return "Registriere " + i + " " + this.sensor() + "(en)...";
	}

	@Override
	public String step8SaveModelFailed(File f, String exceptionText) {
		return "Die Konfiguration konnte nicht in der Datei\n\"" +
				f.getAbsolutePath() +
				"\"\ngespeichert werden.\nEin Fehler ist aufgetreten:\n" +
				exceptionText +
				"Für weitere Informationen bitte die Logdatei konsultieren.";
	}

	/**
	 * @param i
	 * @return Successful: <code>i</code>
	 */
	public String step8SuccessLabel(int i) {
		return "Erfolgreich: " + i;
	}

	@Override
	public String time() {
		return "Zeit";
	}
	
	@Override
	public String timeZone() {
		return "Zeitzone (UTC-Abstand)";
	}

	@Override
	public String unit() {
		return "Einheit";
	}

	/**
	 * @return Unit of Measurement
	 */
	public String unitOfMeasurement() {
		return "Unit of Measurement";
	}

	/**
	 * @return Warning
	 */
	public String warningDialogTitle() {
		return "Warnung";
	}

	@Override
	public String year() {
		return "Jahr";
	}

}