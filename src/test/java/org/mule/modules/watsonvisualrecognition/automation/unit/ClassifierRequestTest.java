package org.mule.modules.watsonvisualrecognition.automation.unit;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

public class ClassifierRequestTest {

	@Test
	public void testPojo() {
		ClassifierRequest pojo = new ClassifierRequest();
		pojo.setPositiveExamples(new File("path/positive"));
		pojo.setNegativeExamples(new File("path/negative"));
		pojo.setClassName("Class Name");
		pojo.setClassifierId("Classifier Id");
		
		assertEquals("Class Name", pojo.getClassName());
		assertEquals("Classifier Id", pojo.getClassifierId());
		assertNotNull(pojo.getPositiveExamples());
		assertEquals("path/positive", pojo.getPositiveExamples().getPath());
		assertNotNull(pojo.getNegativeExamples());
		assertEquals("path/negative", pojo.getNegativeExamples().getPath());
	}
}
