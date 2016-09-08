package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;

import com.ibm.watson.developer_cloud.service.exception.BadRequestException;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierTestCases extends AbstractTestCases {
	
	private String className;
	private String classifierName;
	private VisualClassifier classifier;
	
	/**
	 * Test with null file for UpdateClassifierTestCases class
	 * @throws VisualRecognitionException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testWithNullFile() throws VisualRecognitionException {
		getConnector().updateClassifier(null,"test_class", "test", null);
	}
	
	private void setupClassifier() {
		className = UUID.randomUUID().toString();
		classifierName = UUID.randomUUID().toString();
		try {
			classifier = getConnector().createClassifier(new File(TestDataBuilder.sampleZipPath()), "test_class", "Test Classifier", new File(TestDataBuilder.negativeSampleZipPath()));
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}
	}
	
	private void tearDown() {
		getConnector().deleteClassifier("test");
	}
	
	
	/**
	 * Test with not null file for UpdateClassifierTestCases class for free version API key
	 */
	@Test(expected=BadRequestException.class)
	public void testWithFileFreeVersion() {
		setupClassifier();
		classifier = null;
		try {
			File negativeExamples = new File(TestDataBuilder.sampleZipPath());
			classifier = getConnector().updateClassifier(null, className, classifierName, negativeExamples);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}

		assertNotNull(classifier);
		assertEquals(classifier.getName(),"Test Classifier");
		assertEquals(classifier.getStatus().toString(),"TRAINING");
		tearDown();
	}
	
}
