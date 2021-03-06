/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifyImageRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class ClassifyImageTestCases extends AbstractTestCases {

	private ClassifyImageRequest request;

	@Test
	@Ignore("The URL image changes too often")
	public void testWithURL() throws VisualRecognitionException {
		request = TestDataBuilder.buildClassifyImageRequest();
		request.setUrl(TestDataBuilder.TEST_GROUP_URL_IMAGE);
		VisualClassification classification = getConnector().classifyImage(request);
		assertVisualClassification(classification);
	}

	@Test
	public void testWithFile() throws VisualRecognitionException {
		request = TestDataBuilder.buildClassifyImageRequest();
		request.setImageAsFile(TestDataBuilder.TEST_IMAGE_GROUP);
		VisualClassification classification = getConnector().classifyImage(request);
		assertVisualClassification(classification);
	}

	public void assertVisualClassification(VisualClassification classification) {
		assertEquals(classification.getImages().size(), 1);

		boolean containsClass = false;
		for (VisualClassifier.VisualClass classifier : classification.getImages().get(0).getClassifiers().get(0).getClasses()) {
			if (classifier.getName().equals(TestDataBuilder.TEST_GROUP_IMAGE_CLASS)) {
				containsClass = true;
				break;
			}
		}
		assertTrue(containsClass);
	}
}
