package org.mule.modules.watsonvisualrecognition.automation.unit;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.mule.modules.watsonvisualrecognition.model.ClassifyImageRequest;

public class ClassifyImageRequestTest {

	@Test
	public void testPojo() {
		ClassifyImageRequest pojo = new ClassifyImageRequest();
		pojo.setImage(new File("test/path"));
		pojo.setUrl("http//:www.page.com");
		pojo.setThreshold(1.2);
		pojo.setClassifierIds(new ArrayList<String>());
		pojo.getClassifierIds().add("test");
		
		
		assertEquals("http//:www.page.com", pojo.getUrl());
		assertNotNull(pojo.getImage());
		assertEquals("test/path", pojo.getImage().getPath());
		assertEquals(new Double("1.2"), pojo.getThreshold());
		assertNotNull(pojo.getClassifierIds());
		assertEquals("test", pojo.getClassifierIds().get(0));
	}
}
