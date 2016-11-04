/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ByteArrayToFileTestCases extends AbstractTestCases {

	@Test
	public void testByteArrayToFile() throws IOException {
		byte[] data = Files.readAllBytes(Paths.get(TestDataBuilder.TEST_IMAGE_TEXT.getAbsolutePath()));
		File dataConverted = getConnector().byteArrayToFile(data, "jpg");

		assertEquals(TestDataBuilder.TEST_IMAGE_TEXT.length(), dataConverted.length());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalParameter() throws IOException {
		getConnector().byteArrayToFile(new Object(), "jpg");
	}

}
