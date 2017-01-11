/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.unit;

import java.io.ByteArrayInputStream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.mule.modules.watsonvisualrecognition.model.ImageRequest;

public class ImageRequestTest {

	@Test
	public void testPojo() {
		ImageRequest pojo = new ImageRequest();
		pojo.setImage(new ByteArrayInputStream(new byte[] { 0, 1 }));
		pojo.setUrl("http//:www.page.com");

		assertEquals("http//:www.page.com", pojo.getUrl());
		assertNotNull(pojo.getImage());
	}
}
