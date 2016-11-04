/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.watsonvisualrecognition.automation.functional.ByteArrayToFileTestCases;
import org.mule.modules.watsonvisualrecognition.automation.functional.ClassifyImageTestCases;
import org.mule.modules.watsonvisualrecognition.automation.functional.CreateClassifierTestCases;
import org.mule.modules.watsonvisualrecognition.automation.functional.DeleteClassifierTestCases;
import org.mule.modules.watsonvisualrecognition.automation.functional.DetectFacesTestCases;
import org.mule.modules.watsonvisualrecognition.automation.functional.InputStreamToFileTestCases;
import org.mule.modules.watsonvisualrecognition.automation.functional.RecognizeTextTestCases;
import org.mule.modules.watsonvisualrecognition.automation.functional.RetrieveClassifierDetailsTestCases;
import org.mule.modules.watsonvisualrecognition.automation.functional.RetrieveListOfClassifiersTestCases;
import org.mule.modules.watsonvisualrecognition.automation.functional.UpdateClassifierTestCases;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

import org.mule.modules.watsonvisualrecognition.WatsonVisualRecognitionConnector;

@RunWith(Suite.class)
@SuiteClasses({
		ClassifyImageTestCases.class,
		RecognizeTextTestCases.class,
		DetectFacesTestCases.class,
		RetrieveListOfClassifiersTestCases.class,
		RetrieveClassifierDetailsTestCases.class,
		DeleteClassifierTestCases.class,
		CreateClassifierTestCases.class,
		UpdateClassifierTestCases.class,
		ByteArrayToFileTestCases.class,
		InputStreamToFileTestCases.class
})
public class FunctionalTestSuite {

	@BeforeClass
	public static void initialiseSuite() {
		ConnectorTestContext.initialize(WatsonVisualRecognitionConnector.class);
	}

	@AfterClass
	public static void shutdownSuite() {
		ConnectorTestContext.shutDown();
	}

}