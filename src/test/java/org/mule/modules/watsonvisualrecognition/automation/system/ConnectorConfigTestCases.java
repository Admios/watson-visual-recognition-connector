/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.system;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mule.api.ConnectionException;
import org.mule.modules.watsonvisualrecognition.config.Config;
import org.mule.tools.devkit.ctf.configuration.util.ConfigurationUtils;

public class ConnectorConfigTestCases {

	private Properties validCredentials;
	private static String API_KEY;
	private static String VERSION_DATE;
	private static final String BAD_API_KEY = "123456789";

	private Config config;

	@Before
	public void setup() throws Exception {
		validCredentials = ConfigurationUtils.getAutomationCredentialsProperties();
		API_KEY = validCredentials.getProperty("config.apiKey");
		VERSION_DATE = validCredentials.getProperty("config.versionDate");
		config = new Config();
	}

	@Test
	public void testConnect() throws ConnectionException {
		config.connect(API_KEY, VERSION_DATE);
		assertTrue("Connect should success", true);
	}

	@Test(expected = ConnectionException.class)
	public void testConnectFail() throws ConnectionException {
		config.connect(BAD_API_KEY, "");
		fail("Connect should fail due to bad Api Key");
	}

	@Test(expected = ConnectionException.class)
	public void testConnectWithNullKey() throws ConnectionException {
		config.connect(null, null);
		fail("Connect should fail due to bad Api Key");
	}

}
