package org.mule.modules.watsonvisualrecognition.automation.unit;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.mule.modules.watsonvisualrecognition.model.ImageRequest;

public class ImageRequestTest {

	@Test
	public void testPojo() {
		ImageRequest pojo = new ImageRequest();
		pojo.setImage(new File("test/path"));
		pojo.setUrl("http//:www.page.com");

		assertEquals("http//:www.page.com", pojo.getUrl());
		assertNotNull(pojo.getImage());
		assertEquals("test/path", pojo.getImage().getPath());
	}
}
