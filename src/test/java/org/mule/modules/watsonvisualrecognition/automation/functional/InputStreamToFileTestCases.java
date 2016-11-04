/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class InputStreamToFileTestCases extends AbstractTestCases {

	@Test
	public void testInputStreamToFile() throws IOException {
		InputStream data = new FileInputStream(TestDataBuilder.TEST_IMAGE_TEXT);
		File dataConverted = getConnector().inputStreamToFile(data, "jpg");
		data.close();

		assertEquals(TestDataBuilder.TEST_IMAGE_TEXT.length(), dataConverted.length());
	}

}
