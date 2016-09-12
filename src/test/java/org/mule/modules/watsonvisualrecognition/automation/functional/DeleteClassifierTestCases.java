package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;

import com.ibm.watson.developer_cloud.service.exception.NotFoundException;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class DeleteClassifierTestCases extends AbstractTestCases {
	
	@Test(expected = NotFoundException.class)
	public void testDeleteClassifierWithInvalidId() {
		getConnector().deleteClassifier("test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteClassifierWithNull() {
		getConnector().deleteClassifier(null);
	}
	
	@Test
	public void testEfectiveDeleteClassifier() {
		VisualClassifier dummyClassifier = null;
		try {
			dummyClassifier = getConnector().createClassifier(new File(TestDataBuilder.sampleZipPath()), 
					"test_class", "Test Classifier", 
					new File(TestDataBuilder.negativeSampleZipPath()));
			getConnector().deleteClassifier(dummyClassifier.getId());
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}
	}
}
