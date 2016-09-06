package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.WatsonVisualRecognitionConnector;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector>{
	
	private String className;
	private String classifierName;
	private VisualClassifier classifier;
	
	/**
	 * Basic constructor for the class UpdateClassifierTestCases 
	 */
	public UpdateClassifierTestCases() {
		super(WatsonVisualRecognitionConnector.class);
	}
	
	/**
	 * Test with null file for UpdateClassifierTestCases class
	 * @throws VisualRecognitionException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testWithNullFile() throws VisualRecognitionException {
		getConnector().updateClassifier(null,"test_class", "test", null);
	}
	
	@BeforeClass
	public void setupClassifier() {
		className = UUID.randomUUID().toString();
		classifierName = UUID.randomUUID().toString();
		try {
			classifier = getConnector().createClassifier(new File(TestDataBuilder.sampleZipPath()), "test_class", "Test Classifier", new File(TestDataBuilder.negativeSampleZipPath()));
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public void tearDown() {
		getConnector().deleteClassifier("test");
	}
	
	
	/**
	 * Test with not null file for UpdateClassifierTestCases class
	 */
	public void testWithFile() {
		VisualClassifier classifier = null;
		try {
			File negativeExamples = new File(TestDataBuilder.sampleZipPath());
			classifier = getConnector().updateClassifier(null, className, classifierName, negativeExamples);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}

		assertNotNull(classifier);
		assertEquals(classifier.getName(),"Test Classifier");
		assertEquals(classifier.getStatus().toString(),"TRAINING");
	}
	
}
