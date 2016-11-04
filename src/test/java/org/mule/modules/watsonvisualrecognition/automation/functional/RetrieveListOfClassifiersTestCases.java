/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
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
