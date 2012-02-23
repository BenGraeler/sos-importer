package org.n52.sos.importer.test;

import org.n52.sos.importer.controller.MainController;
import org.n52.sos.importer.controller.Step3aController;
import org.n52.sos.importer.controller.TableController;
import org.n52.sos.importer.model.Step3aModel;

public class Step3Test {
	
	public static void main(String[] args) {
		MainController f = MainController.getInstance();
		Object[][] o = {{"a", "(52.2°, 7.4°, 21m)","2011-10-12", "2011-10-13"},
				{"a", "b","12.12", "123.123"},
				{"a", "b","123.123", "32.123"}};
		TableController.getInstance().setContent(o); 

		f.setStepController(new Step3aController(new Step3aModel(0)));
	}
}