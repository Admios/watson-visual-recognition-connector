package org.mule.modules.watsonvisualrecognition.automation.functional;

import org.junit.Test;

import com.ibm.watson.developer_cloud.service.exception.NotFoundException;

public class RetrieveClassifierDetailsTestCases extends AbstractTestCases {

	@Test(expected = NotFoundException.class)
	public void testRetrieveClassifierWithInvalidId() {
		getConnector().retrieveClassifierDetails("test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRetrieveClassifierWithNull() {
		getConnector().retrieveClassifierDetails(null);
	}
}
