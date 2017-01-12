/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierTestCases extends AbstractTestCases {

	private ClassifierRequest cr;
	private VisualClassifier dummyClassifier;

	@After
	public void deleteClassifier() {
		if (dummyClassifier != null) {
			getConnector().deleteClassifier(dummyClassifier.getId());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateClassifierWithNull() throws VisualRecognitionException {
		cr = new ClassifierRequest();
		File negativeExamples = new File(TestDataBuilder.negativeCatExamplePath());
		Map<String, File> positiveExamples = new HashMap<>();
		positiveExamples.put("golden", new File(TestDataBuilder.positiveGoldenExamplePath()));
		cr.setNegativeExamples(negativeExamples);
		cr.setPositiveExamples(positiveExamples);

		getConnector().updateClassifier(cr);
	}

	@Test
	@Ignore("This tests is very expensive using the Watson API")
	public void testSuccessUpdate() throws VisualRecognitionException, InterruptedException {
		cr = TestDataBuilder.buildClassifierRequest();
		dummyClassifier = getConnector().createClassifier(cr);

		cr = TestDataBuilder.buildUpdateRequest();
		cr.setClassifierNameOrId(dummyClassifier.getId());
		Thread.sleep(5000); // We need to wait a while to make the update because the classifier is in training status
		dummyClassifier = getConnector().updateClassifier(cr);
		assertTrue(dummyClassifier.getStatus() != VisualClassifier.Status.TRAINING);
	}
}
