/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class DeleteClassifierTestCases extends AbstractTestCases {

	private VisualClassifier dummyClassifier;

	/**
	 * Test case for deleting a non existing classifier
	 * 
	 * @throws VisualRecognitionException
	 */
	@Test(expected = VisualRecognitionException.class)
	public void testDeleteClassifierWithInvalidId() throws VisualRecognitionException {
		try {
			getConnector().deleteClassifier("test");
		} catch (Exception e) {
			throw new VisualRecognitionException(e);
		}
	}

	/**
	 * Test case for illegal arguments
	 * 
	 * @throws VisualRecognitionException
	 */
	@Test(expected = VisualRecognitionException.class)
	public void testDeleteClassifierWithNull() throws VisualRecognitionException {
		getConnector().deleteClassifier(null);
	}

	/**
	 * Test case that verifies if a classifier is eliminated in the proper way
	 * 
	 * @throws VisualRecognitionException
	 */
	@Test(expected = VisualRecognitionException.class)
	@Ignore("This tests is very expensive using the Watson API")
	public void testEffectiveDeleteClassifier() throws VisualRecognitionException {
		// The classifier creation is not in the @Before to avoid the creation of classifiers in the negative cases
		ClassifierRequest cr = TestDataBuilder.buildClassifierRequest();
		dummyClassifier = getConnector().createClassifier(cr);

		getConnector().deleteClassifier(dummyClassifier.getId());
		getConnector().retrieveClassifierDetails(dummyClassifier.getId());
	}
}
