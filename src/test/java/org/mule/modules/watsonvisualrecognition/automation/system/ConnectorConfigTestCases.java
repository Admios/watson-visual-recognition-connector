package org.mule.modules.watsonvisualrecognition.automation.system;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mule.api.ConnectionException;
import org.mule.modules.watsonvisualrecognition.config.ConnectorConfig;
import org.mule.tools.devkit.ctf.configuration.util.ConfigurationUtils;

public class ConnectorConfigTestCases {

	private Properties validCredentials;
	private static String API_KEY;
	private static String VERSION_DATE;
	private static final String BAD_API_KEY = "123456789";

	private ConnectorConfig config;

	@Before
	public void setup() throws Exception {
		validCredentials = ConfigurationUtils.getAutomationCredentialsProperties();
		API_KEY = validCredentials.getProperty("watson-visual-config.apiKey");
		VERSION_DATE = validCredentials.getProperty("watson-visual-config.versionDate");
		config = new ConnectorConfig();
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
