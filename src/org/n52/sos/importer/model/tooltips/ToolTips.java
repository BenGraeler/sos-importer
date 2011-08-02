package org.n52.sos.importer.model.tooltips;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ToolTips {
	private static final String BUNDLE_NAME = "org.n52.sos.importer.model.tooltips.tooltips"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private ToolTips() {
	}

	public static String get(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
