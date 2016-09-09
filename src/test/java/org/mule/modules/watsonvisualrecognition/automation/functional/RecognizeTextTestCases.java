package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
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
	public void testWithURL() {
		request.setUrl(TestDataBuilder.TEST_TEXT_URL_IMAGE);
		RecognizedText recognizedText = getConnector().recognizeText(request);
		assertVisualClassification(recognizedText);
	}

	@Test
	public void testWithFile() {
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
