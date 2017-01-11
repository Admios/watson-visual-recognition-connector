/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.model.ImageRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.RecognizedText;

public class RecognizeTextTestCases extends AbstractTestCases {

	private ImageRequest request;

	@Before
	public void createRequest(){
		request = new ImageRequest();
	}
	
	@Test
	@Ignore("The URL image changes too often")
	public void testWithURL() throws IOException {
		request.setUrl(TestDataBuilder.TEST_TEXT_URL_IMAGE);
		RecognizedText recognizedText = getConnector().recognizeText(request);
		assertVisualClassification(recognizedText);
	}

	@Test
	public void testWithFile() throws IOException {
		request.setImage(TestDataBuilder.TEST_IMAGE_TEXT);
		RecognizedText recognizedText = getConnector().recognizeText(request);
		assertVisualClassification(recognizedText);
	}

	public void assertVisualClassification(RecognizedText recognizedText) {
		assertNotNull(recognizedText);
		assertEquals(recognizedText.getImages().size(), 1);
		assertTrue(recognizedText.getImages().get(0).getText().contains(TestDataBuilder.TEST_TEXT_IMAGE_TEXT));
	}
}
