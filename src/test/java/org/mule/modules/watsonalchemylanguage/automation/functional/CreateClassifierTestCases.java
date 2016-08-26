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

public class CreateClassifierTestCases extends AbstractTestCase<WatsonVisualRecognitionConnector> {

	public CreateClassifierTestCases() {
		super(WatsonVisualRecognitionConnector.class);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testWithNullFile() {
		
		try {
			getConnector().createClassifier(null, "test-class", "Test Classifier", null);
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testWithFile() {
		VisualClassifier classifier = null;
		try {
			classifier = getConnector().createClassifier(new File(TestDataBuilder.sampleZipPath()), "test-class", "Test Classifier", new File(TestDataBuilder.negativeSampleZipPath()));
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}

		assertNotNull(classifier);
		assertEquals(classifier.getName(),"Test Classifier");
		assertEquals(classifier.getOwner(),"e0043f60-a9d1-48a7-b3ea-f383f3cd78de");
		assertEquals(classifier.getStatus().toString(),"TRAINING");
	}

}
