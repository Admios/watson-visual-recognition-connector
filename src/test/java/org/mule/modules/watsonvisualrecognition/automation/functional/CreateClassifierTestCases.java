package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class CreateClassifierTestCases extends AbstractTestCases {

	@Test(expected=IllegalArgumentException.class)
	public void testWithNullFile() throws VisualRecognitionException {
		getConnector().createClassifier(null, "test_class", "Test Classifier", null);
	}

	@Test
	public void verifyCreation() {
		VisualClassifier dummyClassifier = null;
		VisualClassifier expectedClassifier = null;
		try {
			dummyClassifier = getConnector().createClassifier(new File(TestDataBuilder.sampleZipPath()), 
					"test_class", "Test Classifier", 
					new File(TestDataBuilder.negativeSampleZipPath()));
			String classifierId = dummyClassifier.getId();
			expectedClassifier = getConnector().retrieveClassifierDetails(classifierId);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}
		
		assertEquals(dummyClassifier.getId(), expectedClassifier.getId());
	}
}
