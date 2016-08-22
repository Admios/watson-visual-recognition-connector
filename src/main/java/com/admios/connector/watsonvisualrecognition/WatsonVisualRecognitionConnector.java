package com.admios.connector.watsonvisualrecognition;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.licensing.RequiresEnterpriseLicense;

import com.admios.connector.watsonvisualrecognition.config.ConnectorConfig;

/**
 * Visual Recognition allows users to understand the contents of an image or video frame, answering the question: “What
 * is in this image?” Submit an image, and the service returns scores for relevant classifiers representing things such
 * as objects, events and settings.
 * 
 * @author Admios
 */
@RequiresEnterpriseLicense(allowEval = true)
@Connector(name = "watson-visual-recognition", friendlyName = "Watson Visual Recognition", minMuleVersion = "3.6.0")
public class WatsonVisualRecognitionConnector {

	@Config
	ConnectorConfig config;

	public ConnectorConfig getConfig() {
		return config;
	}

	public void setConfig(ConnectorConfig config) {
		this.config = config;
	}

}