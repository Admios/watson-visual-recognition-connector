package org.mule.modules.watsonvisualrecognition.automation.functional;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class RetrieveListOfClassifiersTestCases extends AbstractTestCases {

	@Test
	public void testRetrieveListOfClassifiers() {
		List<VisualClassifier> classifiers = getConnector().retrieveListOfClassifiers();
		assertNotNull(classifiers);
	}
}
