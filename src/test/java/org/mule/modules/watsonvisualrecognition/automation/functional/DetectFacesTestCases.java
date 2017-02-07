/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionFileException;
import org.mule.modules.watsonvisualrecognition.model.ImageRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

public class DetectFacesTestCases extends AbstractTestCases {

	private ImageRequest request;

	@Test
	@Ignore("The URL image changes too often")
	public void testWithURL() throws VisualRecognitionFileException, VisualRecognitionException {
		request = TestDataBuilder.buildImageRequest();
		request.setUrl(TestDataBuilder.TEST_PERSON_URL_IMAGE);
		DetectedFaces detectedFaces = getConnector().detectFaces(request);
		assertVisualClassification(detectedFaces);
	}

	@Test
	public void testWithFile() throws VisualRecognitionFileException, VisualRecognitionException {
		request = TestDataBuilder.buildImageRequest();
		request.setImage(TestDataBuilder.TEST_PERSON_IMAGE);
		DetectedFaces detectedFaces = getConnector().detectFaces(request);
		assertVisualClassification(detectedFaces);
	}

	public void assertVisualClassification(DetectedFaces detectedFaces) {
		assertEquals(detectedFaces.getImages().size(), 1);
		assertEquals(TestDataBuilder.TEST_PERSON_IMAGE_RECOGNIZED_FACES,
				(Integer) detectedFaces.getImages().get(0).getFaces().size());
	}

}
