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
	public void testWithNullFile() throws VisualRecognitionException {
		getConnector().createClassifier(null, "test_class", "Test Classifier", null);
	}

	@Test
	public void testWithFile() {
		VisualClassifier classifier = null;
		try {
			classifier = getConnector().createClassifier(new File(TestDataBuilder.sampleZipPath()), "test_class", "Test Classifier", new File(TestDataBuilder.negativeSampleZipPath()));
		} catch (VisualRecognitionException e) {
			fail(e.getMessage());
		}

		assertNotNull(classifier);
		assertEquals(classifier.getName(),"Test Classifier");
		assertEquals(classifier.getStatus().toString(),"TRAINING");
	}

}
