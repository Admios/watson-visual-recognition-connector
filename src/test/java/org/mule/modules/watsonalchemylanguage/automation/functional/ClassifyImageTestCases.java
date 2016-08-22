package org.mule.modules.watsonalchemylanguage.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.admios.connector.watsonvisualrecognition.WatsonVisualRecognitionConnector;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

public class ClassifyImageTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector> {

	public ClassifyImageTestCases() {
		super(WatsonVisualRecognitionConnector.class);
	}

	@Test
	public void testWithURL() {
		VisualClassification classification = getConnector().classifyImage(
				TestDataBuilder.TEST_URL_IMAGE, null, null, null);
		assertVisualClassification(classification);
	}

	@Test
	public void testWithFile() {
		VisualClassification classification = getConnector().classifyImage(
				null, TestDataBuilder.TEST_IMAGE_GROUP, null, null);
		assertVisualClassification(classification);
	}

	public void assertVisualClassification(VisualClassification classification) {
		assertNotNull(classification);
		assertEquals(classification.getImages().size(), 1);
		assertEquals(classification.getImages().get(0).getClassifiers().get(0).getClasses().get(0).getName(),
				TestDataBuilder.TEST_CLASS_GROUP_CLASS);
	}
}
