package org.mule.modules.watsonvisualrecognition.automation.unit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;

public class ClassifierRequestTest {

	@Test
	public void testPojo() {
		ClassifierRequest pojo = new ClassifierRequest();
		Map<String, File> positiveExamples = new HashMap<String, File>();
		positiveExamples.put("Class Name", new File("path/positive"));
		pojo.setPositiveExamples(positiveExamples);
		pojo.setNegativeExamples(new File("path/negative"));
		
		pojo.setClassifierNameOrId("Classifier Id");
		assertEquals("Classifier Id", pojo.getClassifierNameOrId());
		assertNotNull(pojo.getPositiveExamples());
		assertEquals("path/positive", pojo.getPositiveExamples().get("Class Name").getPath());
		assertNotNull(pojo.getNegativeExamples());
		assertEquals("path/negative", pojo.getNegativeExamples().getPath());
	}
}
