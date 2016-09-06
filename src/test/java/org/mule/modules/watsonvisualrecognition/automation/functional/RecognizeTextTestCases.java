package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.WatsonVisualRecognitionConnector;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.RecognizedText;

public class RecognizeTextTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector> {

	public RecognizeTextTestCases() {
		super(WatsonVisualRecognitionConnector.class);
	}

	@Test
	public void testWithURL() {
		RecognizedText recognizedText = getConnector().recognizeText(TestDataBuilder.TEST_TEXT_URL_IMAGE, null);
		assertVisualClassification(recognizedText);
	}

	@Test
	public void testWithFile() {
		RecognizedText recognizedText = getConnector().recognizeText(null, TestDataBuilder.TEST_IMAGE_TEXT);
		assertVisualClassification(recognizedText);
	}

	public void assertVisualClassification(RecognizedText recognizedText) {
		assertNotNull(recognizedText);
		assertEquals(recognizedText.getImages().size(), 1);
		assertTrue(recognizedText.getImages().get(0).getText().contains(TestDataBuilder.TEST_TEXT_IMAGE_TEXT));
	}
}
