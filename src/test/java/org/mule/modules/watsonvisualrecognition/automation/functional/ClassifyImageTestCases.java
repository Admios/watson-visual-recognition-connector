package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.model.ClassifyImageRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

public class ClassifyImageTestCases extends AbstractTestCases {

	private ClassifyImageRequest request;
	
	@Before
	public void createRequest(){
		request = new ClassifyImageRequest();
	}
	@Test
	public void testWithURL() {
		request.setUrl(TestDataBuilder.TEST_GROUP_URL_IMAGE);
		VisualClassification classification = getConnector().classifyImage(request);
		assertVisualClassification(classification);
	}

	@Test
	public void testWithFile() {
		request.setImage(TestDataBuilder.TEST_IMAGE_GROUP);
		VisualClassification classification = getConnector().classifyImage(request);
		assertVisualClassification(classification);
	}

	public void assertVisualClassification(VisualClassification classification) {
		assertNotNull(classification);
		assertEquals(classification.getImages().size(), 1);
		assertEquals(classification.getImages().get(0).getClassifiers().get(0).getClasses().get(0).getName(),
				TestDataBuilder.TEST_GROUP_IMAGE_CLASS);
	}
}
