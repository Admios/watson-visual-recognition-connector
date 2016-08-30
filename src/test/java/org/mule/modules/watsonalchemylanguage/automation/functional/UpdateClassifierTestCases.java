package org.mule.modules.watsonalchemylanguage.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

import com.admios.connector.watsonvisualrecognition.WatsonVisualRecognitionConnector;
import com.admios.connector.watsonvisualrecognition.exceptions.VisualRecognitionException;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector>{
	
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
	
	/**
	 * Test with not null file for UpdateClassifierTestCases class
	 */
	public void testWithFile() {
		VisualClassifier classifier = null;
		try {
			File negativeExamples = new File(TestDataBuilder.sampleZipPath());
			classifier = getConnector().updateClassifier(null, "test_class", "test", negativeExamples);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}

		assertNotNull(classifier);
		assertEquals(classifier.getName(),"Test Classifier");
		assertEquals(classifier.getStatus().toString(),"TRAINING");
	}
}
