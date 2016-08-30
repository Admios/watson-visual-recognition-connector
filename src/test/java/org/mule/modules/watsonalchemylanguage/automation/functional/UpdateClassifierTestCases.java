package org.mule.modules.watsonalchemylanguage.automation.functional;

import org.junit.Test;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.admios.connector.watsonvisualrecognition.WatsonVisualRecognitionConnector;
import com.admios.connector.watsonvisualrecognition.exceptions.VisualRecognitionException;

public class UpdateClassifierTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector>{
	
	public UpdateClassifierTestCases() {
		super(WatsonVisualRecognitionConnector.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWithNullFile() throws VisualRecognitionException {
		getConnector().updateClassifier(null,"test_class", "test", null);
	}
}
