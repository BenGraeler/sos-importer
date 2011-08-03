package org.n52.sos.importer.model.position;

import java.text.MessageFormat;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.n52.sos.importer.interfaces.Combination;

public class Position extends Combination {

	private static final Logger logger = Logger.getLogger(Position.class);
	
	private Latitude latitude;
	
	private Longitude longitude;
	
	private Height height;
	
	private EPSGCode epsgCode;

	private String group;
	
	public Position() {
		super();
	}
	
	public Position(Latitude latitude, Longitude longitude, Height height,
			EPSGCode epsgCode) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
		this.epsgCode = epsgCode;
	}

	public Height getHeight() {
		return height;
	}

	public void setHeight(Height height) {
		if (getGroup() != null)
			if (height != null)
				logger.info("Add " + height + " to " + this);
			else
				logger.info("Remove " + this.height + " from " + this);
		this.height = height;
	}

	public EPSGCode getEPSGCode() {
		return epsgCode;
	}

	public void setEPSGCode(EPSGCode epsgCode) {
		if (getGroup() != null)
			if (epsgCode != null)
				logger.info("Add " + epsgCode + " to " + this);
			else
				logger.info("Remove " + this.epsgCode + " from " + this);
		this.epsgCode = epsgCode;
	}

	public void setLongitude(Longitude longitude) {
		if (getGroup() != null)
			if (longitude != null)
				logger.info("Add " + longitude + " to " + this);
			else
				logger.info("Remove " + this.longitude + " from " + this);
		this.longitude = longitude;
	}

	public Longitude getLongitude() {
		return longitude;
	}

	public void setLatitude(Latitude latitude) {
		if (getGroup() != null)
			if (latitude != null)
				logger.info("Add " + latitude + " to " + this);
			else
				logger.info("Remove " + this.latitude + " from " + this);
		this.latitude = latitude;
	}

	public Latitude getLatitude() {
		return latitude;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroup() {
		return group;
	}

	@Override
	public String format(Object o) {
		Position p = (Position) o;
		String positionString = getPattern();
		positionString = positionString.replaceAll("LAT", p.getLatitude().getValue() + p.getLatitude().getUnit());
		positionString = positionString.replaceAll("LON", p.getLongitude().getValue() + p.getLongitude().getUnit());
		positionString = positionString.replaceAll("ALT", p.getHeight().getValue() + p.getHeight().getUnit());
		positionString = positionString.replaceAll("EPSG", p.getEPSGCode().getValue() + "");
		return positionString;
	}

	@Override
	public Position parse(String s) {
		String pattern = getPattern();
		
		pattern = pattern.replaceAll("LAT", "{0}");
		pattern = pattern.replaceAll("LON", "{1}");
		pattern = pattern.replaceAll("ALT", "{2}");
		pattern = pattern.replaceAll("EPSG", "{3}");
		
		MessageFormat mf = new MessageFormat(pattern);
		Object[] o = null;
		try {
			o = mf.parse(s);
		} catch (ParseException e) {
			throw new NumberFormatException();
		}
		
		if (o == null)
			throw new NumberFormatException();
			
		Latitude latitude = null;
		Longitude longitude = null;
		Height height = null;
		EPSGCode epsgCode = null;
			
		if (o.length > 0 && o[0] != null) 
			latitude = Latitude.parse((String) o[0]);
		if (o.length > 1 && o[1] != null) 
			longitude = Longitude.parse((String)o[1]);
		if (o.length > 2 && o[2] != null) 
			height = Height.parse((String)o[2]);
		if (o.length > 3 && o[3] != null)
			epsgCode = EPSGCode.parse((String)o[3]);
		return new Position(latitude, longitude, height, epsgCode);
	}

	@Override
	public String toString() {
		if (getGroup() == null)
			return "Position (" + latitude + ", " + longitude + ", " 
			+ height + ", " + epsgCode + ")";
		else
			return "Position group " + getGroup();
	}
}
