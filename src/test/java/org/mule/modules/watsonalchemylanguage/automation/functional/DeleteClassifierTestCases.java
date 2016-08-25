package org.mule.modules.watsonalchemylanguage.automation.functional;

import org.junit.Test;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.admios.connector.watsonvisualrecognition.WatsonVisualRecognitionConnector;
import com.ibm.watson.developer_cloud.service.exception.NotFoundException;

public class DeleteClassifierTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector> {

	public DeleteClassifierTestCases() {
		super(WatsonVisualRecognitionConnector.class);
	}

	@Test(expected = NotFoundException.class)
	public void testDeleteClassifierWithInvalidId() {
		getConnector().deleteClassifier("test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteClassifierWithNull() {
		getConnector().deleteClassifier(null);
	}
}
