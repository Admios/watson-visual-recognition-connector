/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierTestCases extends AbstractTestCases {
	
	private ClassifierRequest cr;
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateClassifierWithNull() {
		cr = new ClassifierRequest();
		File negativeExamples = new File(TestDataBuilder.negativeCatExamplePath()); 
		Map<String, File> positiveExamples = new HashMap<>();
		positiveExamples.put("golden", new File(TestDataBuilder.positiveGoldenExamplePath()));
		cr.setNegativeExamples(negativeExamples);
		cr.setPositiveExamples(positiveExamples);
	
		try {
			getConnector().updateClassifier(cr);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore("This tests is very expensive using the Watson API")
	public void testSuccessUpdate() {
		VisualClassifier dummyClassifier = null;
		try {
			buildCreateRequest();
			dummyClassifier = getConnector().createClassifier(cr);
			cr.setClassifierNameOrId(dummyClassifier.getId());
			buildUpdateRequest();
			Thread.sleep(1000); //We need to wait a while to make the update because the classifier is in training status
			dummyClassifier = getConnector().updateClassifier(cr);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
		getConnector().deleteClassifier(dummyClassifier.getId());
	}
	
	private void buildCreateRequest() {
	    cr = new ClassifierRequest();
		
		String rvalue = String.valueOf(new Date().getTime());
		cr.setClassifierNameOrId("dogs" + rvalue);
		
		File negativeExamples = new File(TestDataBuilder.negativeCatExamplePath()); 
		Map<String, File> positiveExamples = new HashMap<>();
		
		positiveExamples.put("golden", new File(TestDataBuilder.positiveGoldenExamplePath()));
		positiveExamples.put("beagle", new File(TestDataBuilder.positiveBeagleExamplePath()));
		positiveExamples.put("husky", new File(TestDataBuilder.positiveHuskyExamplePath()));
		
		
		cr.setNegativeExamples(negativeExamples);
		
		cr.setPositiveExamples(positiveExamples);
	}
	
	private void buildUpdateRequest() {
		File negativeExamples = new File(TestDataBuilder.negativeMoreCatsExamplePath());
		Map<String, File> positiveExamples = new HashMap<>();
		positiveExamples.put("dalmations", new File(TestDataBuilder.positiveDalmationsExamplePath()));
		cr.setNegativeExamples(negativeExamples);
		cr.setPositiveExamples(positiveExamples);
	}
}
