/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class RetrieveClassifierDetailsTestCases extends AbstractTestCases {

	private VisualClassifier dummyClassifier;

	@After
	public void deleteClassifier() throws VisualRecognitionException {
		if (dummyClassifier != null) {
			getConnector().deleteClassifier(dummyClassifier.getId());
		}
	}

	@Test(expected = VisualRecognitionException.class)
	public void testRetrieveClassifierWithInvalidId() throws VisualRecognitionException {
		try {
			getConnector().retrieveClassifierDetails("test");
		} catch (Exception e) {
			throw new VisualRecognitionException(e);
		}		
	}

	@Test(expected = VisualRecognitionException.class)
	public void testRetrieveClassifierWithNull() throws VisualRecognitionException {
		getConnector().retrieveClassifierDetails(null);
	}

	@Test
	@Ignore("This tests is very expensive using the Watson API")
	public void testRetrieveClassifier() throws VisualRecognitionException {
		// The classifier creation is not in the @Before to avoid the creation of classifiers in the negative cases
		ClassifierRequest cr = TestDataBuilder.buildClassifierRequest();
		dummyClassifier = getConnector().createClassifier(cr);

		VisualClassifier foundClassifier = getConnector().retrieveClassifierDetails(dummyClassifier.getId());
		assertEquals(foundClassifier.getName(), dummyClassifier.getName());
	}
}
