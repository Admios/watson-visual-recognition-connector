package org.mule.modules.watsonalchemylanguage.automation.functional;

import org.junit.Test;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.admios.connector.watsonvisualrecognition.WatsonVisualRecognitionConnector;
import com.ibm.watson.developer_cloud.service.exception.NotFoundException;

public class RetrieveClassifierDetailsTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector> {

	public RetrieveClassifierDetailsTestCases() {
		super(WatsonVisualRecognitionConnector.class);
	}

	@Test(expected = NotFoundException.class)
	public void testRetrieveClassifier() {
		getConnector().retrieveClassifierDetails("test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRetrieveClassifierWithNull() {
		getConnector().retrieveClassifierDetails(null);
	}
}
