package org.mule.modules.watsonalchemylanguage.automation.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

import com.admios.connector.watsonvisualrecognition.WatsonVisualRecognitionConnector;

@RunWith(Suite.class)
@SuiteClasses({})
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