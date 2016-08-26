package org.mule.modules.watsonalchemylanguage.automation.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.watsonalchemylanguage.automation.functional.ClassifyImageTestCases;
import org.mule.modules.watsonalchemylanguage.automation.functional.CreateClassifierTestCases;
import org.mule.modules.watsonalchemylanguage.automation.functional.DeleteClassifierTestCases;
import org.mule.modules.watsonalchemylanguage.automation.functional.DetectFacesTestCases;
import org.mule.modules.watsonalchemylanguage.automation.functional.RecognizeTextTestCases;
import org.mule.modules.watsonalchemylanguage.automation.functional.RetrieveClassifierDetailsTestCases;
import org.mule.modules.watsonalchemylanguage.automation.functional.RetrieveListOfClassifiersTestCases;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

import com.admios.connector.watsonvisualrecognition.WatsonVisualRecognitionConnector;

@RunWith(Suite.class)
@SuiteClasses({
		ClassifyImageTestCases.class,
		RecognizeTextTestCases.class,
		DetectFacesTestCases.class,
		RetrieveListOfClassifiersTestCases.class,
		RetrieveClassifierDetailsTestCases.class,
		DeleteClassifierTestCases.class,
		CreateClassifierTestCases.class
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