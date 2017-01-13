/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.watsonvisualrecognition.automation.unit.ClassifierRequestTest;
import org.mule.modules.watsonvisualrecognition.automation.unit.ClassifyImageRequestTest;
import org.mule.modules.watsonvisualrecognition.automation.unit.ImageRequestTest;
import org.mule.modules.watsonvisualrecognition.automation.unit.FileUtilsTest;

@RunWith(Suite.class)
@SuiteClasses({
		ClassifierRequestTest.class,
		ClassifyImageRequestTest.class,
		ImageRequestTest.class,
		FileUtilsTest.class
})
public class UnitTestSuite {

}
