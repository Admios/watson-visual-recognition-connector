package org.mule.modules.watsonvisualrecognition.automation.functional;

import org.junit.Test;

import com.ibm.watson.developer_cloud.service.exception.NotFoundException;

public class DeleteClassifierTestCases extends AbstractTestCases {

	@Test(expected = NotFoundException.class)
	public void testDeleteClassifierWithInvalidId() {
		getConnector().deleteClassifier("test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteClassifierWithNull() {
		getConnector().deleteClassifier(null);
	}
}
