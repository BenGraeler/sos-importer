package org.n52.sos.importer.model;

import org.n52.sos.importer.model.position.Position;
import org.n52.sos.importer.model.resources.FeatureOfInterest;

public class Step6cModel {
	
	private FeatureOfInterest featureOfInterest;

	private Position position;
	
	public Step6cModel(FeatureOfInterest featureOfInterest) {
		this.featureOfInterest = featureOfInterest;
		position = new Position();
	}
	
	public void setFeatureOfInterest(FeatureOfInterest featureOfInterest) {
		this.featureOfInterest = featureOfInterest;
	}

	public FeatureOfInterest getFeatureOfInterest() {
		return featureOfInterest;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}
}