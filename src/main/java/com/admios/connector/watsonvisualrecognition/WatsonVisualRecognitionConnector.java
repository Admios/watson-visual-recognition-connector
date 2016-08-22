package com.admios.connector.watsonvisualrecognition;

import java.io.File;
import java.util.List;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.licensing.RequiresEnterpriseLicense;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

import com.admios.connector.watsonvisualrecognition.config.ConnectorConfig;
import com.admios.connector.watsonvisualrecognition.handler.implementation.ClassifyImageHandler;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

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

	/**
	 * Upload images or URLs to identify built-in classifiers by default. To identify custom classifiers, include the
	 * classifier_ids parameters. Images must be in .jpeg, or .png format.
	 * 
	 * API Doc: {@see http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?java#classify_an_image}
	 *
	 * {@sample.xml ../../../doc/watson-visual-recognition-connector.xml.sample watson-visual-recognition:classifyImage}
	 *
	 * @param url The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 * @param image The image file (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of
	 *            images in a .zip file is limited to 20, and limited to 5 MB.
	 * @param classifierIds An array of classifier IDs to classify the images against.
	 * @param threshold A floating point value that specifies the minimum score a class must have to be displayed in the
	 *            response.
	 * 
	 * @return return {@link VisualClassification}
	 */
	@Processor
	public VisualClassification classifyImage(@Default("#[payload]") String url, @Optional File image,
			@Optional List<String> classifierIds, @Optional Double threshold) {
		return new ClassifyImageHandler(config.getService())
				.addSource(url, image)
				.addClassifierId(classifierIds)
				.addThreshold(threshold)
				.execute();
	}

}