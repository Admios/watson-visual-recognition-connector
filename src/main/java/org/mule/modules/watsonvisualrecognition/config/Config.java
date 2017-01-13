/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.config;

import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;

import com.ibm.watson.developer_cloud.service.exception.BadRequestException;
import com.ibm.watson.developer_cloud.service.exception.ForbiddenException;
import com.ibm.watson.developer_cloud.service.exception.UnauthorizedException;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;

@ConnectionManagement(friendlyName = "Configuration", configElementName = "config")
public class Config {

	private VisualRecognition service;

	public VisualRecognition getService() {
		return service;
	}

	public void setService(VisualRecognition service) {
		this.service = service;
	}

	/**
	 * Connect, this method will use one api call to validate the api key
	 *
	 * @param apiKey Your API key.
	 * @param versionDate The release date of the version of the API you want to use. Specify dates in YYYY-MM-DD
	 *            format. The current version is 2016-05-20.
	 * @throws ConnectionException If there is any connectivity error like an invalid apiKey.
	 */
	@Connect
	@TestConnectivity
	public void connect(@ConnectionKey String apiKey, @Default("2016-05-20") String versionDate)
			throws ConnectionException {
		validateConnectionArguments(apiKey, versionDate);
		try {
			setService(new VisualRecognition(versionDate, apiKey));
		} catch (IllegalArgumentException e) {
			throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "", e.getMessage(), e);
		}
		testConnectivity();
	}

	/**
	 * Disconnect
	 */
	@Disconnect
	public void disconnect() {
		setService(null);
	}

	/**
	 * Are we connected
	 * 
	 * @return if the connection is open
	 */
	@ValidateConnection
	public boolean isConnected() {
		return getService() != null;
	}

	/**
	 * Get connection id
	 * 
	 * @return connection id
	 */
	@ConnectionIdentifier
	public String connectionId() {
		return "001";
	}

	private void validateConnectionArguments(String apiKey, String versionDate) throws ConnectionException {
		if (apiKey == null || apiKey.isEmpty()) {
			throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "",
					"The API key can't be null or empty");
		}
		if (versionDate == null || versionDate.isEmpty()) {
			throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "",
					"The version date can't be null or empty");
		}
	}

	private void testConnectivity() throws ConnectionException {
		try {
			getService().getClassifiers().execute();
		} catch (UnauthorizedException | ForbiddenException | IllegalArgumentException | BadRequestException e) {
			throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS, "", e.getMessage(), e);
		}
	}
}