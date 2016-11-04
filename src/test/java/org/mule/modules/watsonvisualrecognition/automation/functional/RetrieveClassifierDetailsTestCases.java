/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
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
