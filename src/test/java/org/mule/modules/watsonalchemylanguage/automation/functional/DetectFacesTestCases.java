package org.mule.modules.watsonalchemylanguage.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.admios.connector.watsonvisualrecognition.WatsonVisualRecognitionConnector;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

public class DetectFacesTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector> {

	public DetectFacesTestCases() {
		super(WatsonVisualRecognitionConnector.class);
	}

	@Test
	public void testWithURL() {
		DetectedFaces detectedFaces = getConnector().detectFaces(TestDataBuilder.TEST_GROUP_URL_IMAGE, null);
		assertVisualClassification(detectedFaces);
	}

	@Test
	public void testWithFile() {
		DetectedFaces detectedFaces = getConnector().detectFaces(null, TestDataBuilder.TEST_IMAGE_GROUP);
		assertVisualClassification(detectedFaces);
	}

	public void assertVisualClassification(DetectedFaces detectedFaces) {
		assertNotNull(detectedFaces);
		assertEquals(detectedFaces.getImages().size(), 1);
		assertEquals((Integer) detectedFaces.getImages().get(0).getFaces().size(),
				TestDataBuilder.TEST_GROUP_IMAGE_RECOGNIZED_FACES);
	}

}
