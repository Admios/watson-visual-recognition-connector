/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class CreateClassifierTestCases extends AbstractTestCases {

	private ClassifierRequest request;
	private VisualClassifier dummyClassifier;

	@After
	public void deleteClassifier() {
		if (dummyClassifier != null) {
			getConnector().deleteClassifier(dummyClassifier.getId());
		}
	}

	/**
	 * Test case for illegal argument request
	 * 
	 * @throws VisualRecognitionException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithNullFile() throws VisualRecognitionException {

		request = new ClassifierRequest();
		Map<String, File> positiveExamples = new HashMap<>();
		positiveExamples.put("test_class", null);
		request.setPositiveExamples(positiveExamples);
		request.setClassifierNameOrId("Test Classifier");
		getConnector().createClassifier(request);
	}

	/**
	 * Test case for success case of creating a classifier
	 * 
	 * @throws VisualRecognitionException
	 */
	@Test
	@Ignore("This tests is very expensive using the Watson API")
	public void testSuccessCreation() throws VisualRecognitionException {
		ClassifierRequest cr = TestDataBuilder.buildClassifierRequest();
		dummyClassifier = getConnector().createClassifier(cr);

		assertEquals(cr.getClassifierNameOrId(), dummyClassifier.getName());
		assertEquals("golden", dummyClassifier.getClasses().get(0).getName());
		assertEquals("TRAINING", dummyClassifier.getStatus().name());
	}
}
