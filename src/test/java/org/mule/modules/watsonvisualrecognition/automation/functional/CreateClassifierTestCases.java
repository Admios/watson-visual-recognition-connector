package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class CreateClassifierTestCases extends AbstractTestCases {

	private ClassifierRequest request;
	
	@Before
	public void createRequest() {
		request = new ClassifierRequest();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWithNullFile() throws VisualRecognitionException {
		request.setClassName("test_class");
		request.setClassifierNameOrId("Test Classifier");
		getConnector().createClassifier(request);
	}

	@Test
	public void testWithFile() {
		VisualClassifier classifier = null;
		
		request.setClassName("test_class");
		request.setClassifierNameOrId( "Test Classifier");
		request.setPositiveExamples(new File(TestDataBuilder.sampleZipPath()));
		request.setNegativeExamples(new File(TestDataBuilder.negativeSampleZipPath()));
		try {
			classifier = getConnector().createClassifier(request);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}

		assertNotNull(classifier);
		assertEquals(classifier.getName(),"Test Classifier");
		assertEquals(classifier.getStatus().toString(),"TRAINING");
	}

}
