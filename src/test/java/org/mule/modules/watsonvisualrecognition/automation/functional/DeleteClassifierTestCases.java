package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

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
		ClassifierRequest cr = buildRequest();
		VisualClassifier dummyClassifier = null;
		try {
			dummyClassifier = getConnector().createClassifier(cr);
			getConnector().deleteClassifier(dummyClassifier.getId());
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}
	}
	
	private ClassifierRequest buildRequest() {
		ClassifierRequest cr = new ClassifierRequest();
		
		String rvalue = String.valueOf(new Date().getTime());
		cr.setClassifierNameOrId("dogs" + rvalue);
		
		File negativeExamples = new File(TestDataBuilder.TEST_NEGATIVE_CAT_FILE); 
		Map<String, File> positiveExamples = new HashMap<>();
		
		positiveExamples.put("golden", new File(TestDataBuilder.positiveGoldenExamplePath()));
		positiveExamples.put("beagle", new File(TestDataBuilder.positiveBeagleExamplePath()));
		positiveExamples.put("husky", new File(TestDataBuilder.positiveHuskyExamplePath()));
		
		
		cr.setNegativeExamples(negativeExamples);
		
		cr.setPositiveExamples(positiveExamples);
		return cr;
	}
}
