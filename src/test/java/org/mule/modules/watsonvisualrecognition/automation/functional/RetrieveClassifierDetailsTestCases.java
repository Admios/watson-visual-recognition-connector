package org.mule.modules.watsonvisualrecognition.automation.functional;

import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.WatsonVisualRecognitionConnector;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.ibm.watson.developer_cloud.service.exception.NotFoundException;

public class RetrieveClassifierDetailsTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector> {

	public RetrieveClassifierDetailsTestCases() {
		super(WatsonVisualRecognitionConnector.class);
	}

	@Test(expected = NotFoundException.class)
	public void testRetrieveClassifierWithInvalidId() {
		getConnector().retrieveClassifierDetails("test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRetrieveClassifierWithNull() {
		getConnector().retrieveClassifierDetails(null);
	}
}
