package org.mule.modules.watsonvisualrecognition.automation.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.watsonvisualrecognition.automation.unit.ClassifierRequestTest;
import org.mule.modules.watsonvisualrecognition.automation.unit.ClassifyImageRequestTest;
import org.mule.modules.watsonvisualrecognition.automation.unit.ImageRequestTest;
import org.mule.modules.watsonvisualrecognition.automation.unit.VisualRecognitionUtilsTest;

@RunWith(Suite.class)
@SuiteClasses({
		ClassifierRequestTest.class,
		ClassifyImageRequestTest.class,
		ImageRequestTest.class,
		VisualRecognitionUtilsTest.class
})
public class UnitTestSuite {

}
