package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.config.Config;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierTestCases extends AbstractTestCases {
	
	private ClassifierRequest request;
	private String className;
	private String classifierName;
	private VisualClassifier classifier;
	
	/**
	 * Test with null file for UpdateClassifierTestCases class
	 * @throws VisualRecognitionException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testWithNullFile() throws VisualRecognitionException {
		request.setClassName("test_class");
		request.setClassifierId("test");
		getConnector().updateClassifier(request);
	}

	@Before
	public void setupRequest() {
		request = new ClassifierRequest();
	}
	
	public void setupClassifier() {
		className = UUID.randomUUID().toString().replaceAll("-", "");
		classifierName = UUID.randomUUID().toString().replaceAll("-", "");
		ClassifierRequest request = new ClassifierRequest();
		request.setPositiveExamples(new File(TestDataBuilder.sampleZipPath()));
		request.setClassName(className);
		request.setClassifierId(classifierName);
		request.setNegativeExamples( new File(TestDataBuilder.negativeSampleZipPath()));
		try {
			classifier = getConnector().createClassifier(request);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test with not null file for UpdateClassifierTestCases class
	 */
	@Test
	public void testWithFile() {

		setupClassifier();
		try {
			request.setPositiveExamples(new File(TestDataBuilder.sampleZipPath()));
			request.setClassName(className);
			request.setClassifierId(classifierName);
			request.setNegativeExamples( new File(TestDataBuilder.negativeSampleZipPath()));
			classifier = getConnector().updateClassifier(request);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}

		assertNotNull(classifier);
		assertEquals(classifier.getName(),className);
		assertEquals(classifier.getStatus().toString(),classifierName);

		deleteClassifier();

	}

	public void deleteClassifier() {
		getConnector().deleteClassifier(classifierName);
		Config config = new Config();
		config.getService().deleteClassifier(classifierName).execute();
	}
}
