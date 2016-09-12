package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierTestCases extends AbstractTestCases {
	
	private String className;
	private String classifierName;
	private String classifierId;
	private VisualClassifier classifier;
	
//	/**
//	 * Test with null file for UpdateClassifierTestCases class
//	 * @throws VisualRecognitionException
//	 */
//	@Test(expected=IllegalArgumentException.class)
//	public void testWithNullFile() throws VisualRecognitionException {
//		getConnector().updateClassifier(null,"test_class", "test", null);
//	}
	
	private void setupClassifier() {
		className = "cats";
		classifierName = UUID.randomUUID().toString();
		try {
			//classifier = getConnector().createClassifier(new File(TestDataBuilder.sampleZipPath()), className, classifierName, new File(TestDataBuilder.negativeSampleZipPath()));
			classifierId = "dogs_662895153";
		} catch (Exception e) { //change to VisualRecognitionException
			fail(e.getMessage());
		}
	}
	
	private void tearDown() {
		//getConnector().deleteClassifier(classifierName);
	}
	
	/**
	 * Test with not null file for UpdateClassifierTestCases class for free version API key
	 */
	@Test
	public void testWithFile() {
		setupClassifier();
		classifier = null;
		try {
			File negativeExamples = new File(TestDataBuilder.negativeCatExamplePath());
			classifier = getConnector().updateClassifier(null, className, classifierId, negativeExamples);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}

		assertNotNull(classifier);
		tearDown();
	}
	
}
