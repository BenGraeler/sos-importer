package org.n52.sos.importer.bean;

public class SensorName extends Resource {

	@Override
	public void assign(MeasuredValue measuredValue) {
		measuredValue.setSensorName(this);	
	}

	@Override
	public boolean isAssigned(MeasuredValue measuredValue) {
		return measuredValue.getSensorName() != null;
	}
	
	@Override 
	public String toString() {
		return "Sensor Name";
	}
}