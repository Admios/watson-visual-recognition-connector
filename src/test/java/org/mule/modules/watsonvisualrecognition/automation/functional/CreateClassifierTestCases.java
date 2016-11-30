/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class CreateClassifierTestCases extends AbstractTestCases {

	private ClassifierRequest request;
	
	/**
	 * Test case for illegal argument request
	 * @throws VisualRecognitionException
	 */
	@Test(expected=IllegalArgumentException.class)
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
	 */
	@Test
	@Ignore("This tests is very expensive using the Watson API")
	public void testSuccessCreation() {
		ClassifierRequest cr = buildRequest();
		VisualClassifier dummyClassifier = null;
		try {
			dummyClassifier = getConnector().createClassifier(cr);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}
		getConnector().deleteClassifier(dummyClassifier.getId());
	}
	
	private ClassifierRequest buildRequest() {
		ClassifierRequest cr = new ClassifierRequest();
		
		String rvalue = String.valueOf(new Date().getTime());
		cr.setClassifierNameOrId("dogs" + rvalue);
		
		File negativeExamples = new File(TestDataBuilder.negativeCatExamplePath()); 
		Map<String, File> positiveExamples = new HashMap<>();
		
		positiveExamples.put("golden", new File(TestDataBuilder.positiveGoldenExamplePath()));
		
		cr.setNegativeExamples(negativeExamples);
		
		cr.setPositiveExamples(positiveExamples);
		return cr;
	}
}
