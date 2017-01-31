/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition;

import java.io.IOException;
import java.util.List;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.licensing.RequiresEnterpriseLicense;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.RefOnly;
import org.mule.modules.watsonvisualrecognition.config.Config;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.handler.implementation.ClassifyImageHandler;
import org.mule.modules.watsonvisualrecognition.handler.implementation.CreateClassifierHandler;
import org.mule.modules.watsonvisualrecognition.handler.implementation.DeleteClassifierHandler;
import org.mule.modules.watsonvisualrecognition.handler.implementation.DetectFacesHandler;
import org.mule.modules.watsonvisualrecognition.handler.implementation.RecognizeTextHandler;
import org.mule.modules.watsonvisualrecognition.handler.implementation.RetrieveClassifierDetailsHandler;
import org.mule.modules.watsonvisualrecognition.handler.implementation.RetrieveListClassifiersHandler;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;
import org.mule.modules.watsonvisualrecognition.model.ClassifyImageRequest;
import org.mule.modules.watsonvisualrecognition.model.ImageRequest;

import com.ibm.watson.developer_cloud.service.exception.ServiceUnavailableException;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.RecognizedText;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

/**
 * Visual Recognition allows users to understand the contents of an image or video frame, answering the question: “What
 * is in this image?” Submit an image, and the service returns scores for relevant classifiers representing things such
 * as objects, events and settings.
 * 
 * @author Admios
 */
@RequiresEnterpriseLicense(allowEval = true)
@ReconnectOn(exceptions = { ServiceUnavailableException.class })
@Connector(name = "watson-visual-recognition", friendlyName = "Watson Visual Recognition", minMuleVersion = "3.6.0")
public class WatsonVisualRecognitionConnector {

	@org.mule.api.annotations.Config
	private Config config;

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	/**
	 * Upload images or URLs to identify built-in classifiers by default. To identify custom classifiers, include the
	 * classifier_ids parameters. Images must be in .jpeg, or .png format.
	 * 
	 * <a href="http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#classify_an_image">API Doc</a>
	 *
	 * @param request Request object that contains the image and the classifier to be use.
	 * 
	 * @return A object containing the list of detected classes in the image.
	 * @throws IOException When the connector can't process the image input stream.
	 */
	@Processor(friendlyName = "Classify an Image")
	public VisualClassification classifyImage(@RefOnly @Default("#[payload]") ClassifyImageRequest request)
			throws IOException {
		return new ClassifyImageHandler(config.getService())
				.addSource(request.getUrl(), request.getImageAsFile())
				.addClassifierId(request.getClassifierIds())
				.addThreshold(request.getThreshold())
				.execute();
	}

	/**
	 * Analyze faces in images and get data about them, such as estimated age and gender. Images must be in .jpeg, or
	 * .png format.
	 * 
	 * <a href="http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#classify_an_image">API Doc</a>
	 *
	 * @param request Request object that contains the image to be use.
	 * 
	 * @return A object containing the list of detected faces, age, gender and position in the image.
	 * @throws IOException When the connector can't process the image input stream.
	 */
	@Processor(friendlyName = "Detect Faces")
	public DetectedFaces detectFaces(@RefOnly @Default("#[payload]") ImageRequest request) throws IOException {
		return new DetectFacesHandler(config.getService())
				.addSource(request.getUrl(), request.getImageAsFile())
				.execute();
	}

	/**
	 * Recognizes text in images. This is a beta function of the Visual Recognition service that supports only English
	 * language text identification.
	 * 
	 * <a href="http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#recognize_text">API Doc</a>
	 *
	 * @param request Request object that contains the image to be use.
	 * 
	 * @return The text recognized in the image.
	 * @throws IOException When the connector can't process the image input stream.
	 */
	@Processor(friendlyName = "Recognize Text")
	public RecognizedText recognizeText(@RefOnly @Default("#[payload]") ImageRequest request) throws IOException {
		return new RecognizeTextHandler(config.getService())
				.addSource(request.getUrl(), request.getImageAsFile())
				.execute();
	}

	/**
	 * Retrieve a list of user-created classifiers.
	 * 
	 * <a href="http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#create_a_classifier">API
	 * Doc</a>
	 * 
	 * @return A list of classifiers associated with your API Key.
	 */
	@Processor(friendlyName = "Retrieve the List of Classifiers")
	public List<VisualClassifier> retrieveListOfClassifiers() {
		return new RetrieveListClassifiersHandler(config.getService()).execute();
	}

	/**
	 * Retrieve information about a specific classifier.
	 * 
	 * <a href="http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#retrieve_classifier_details">
	 * API Doc</a>
	 * 
	 * @param classifierId The ID of the classifier for which you want details.
	 * 
	 * @return A classifier associated with your API Key.
	 */
	@Processor(friendlyName = "Retrieve Classifier Details")
	public VisualClassifier retrieveClassifierDetails(@Default("#[payload]") String classifierId) {
		return new RetrieveClassifierDetailsHandler(config.getService(), classifierId).execute();
	}

	/**
	 * Delete a custom classifier with the specified classifier ID.
	 * 
	 * <a href="http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#delete_a_classifier">API
	 * Doc</a>
	 * 
	 * @param classifierId The ID of the classifier you want to delete.
	 * 
	 */
	@Processor(friendlyName = "Delete a Classifier")
	public void deleteClassifier(@Default("#[payload]") String classifierId) {
		new DeleteClassifierHandler(config.getService(), classifierId).execute();
	}

	/**
	 * Train a new multi-faceted classifier on the uploaded image data. A new custom classifier can be trained by
	 * several compressed (.zip) files, including files containing positive or negative images (.jpg, or .png). You must
	 * supply at least two compressed files, either two positive example files or one positive and one negative example
	 * file.
	 * 
	 * <a href="http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?java#create_a_classifier">API
	 * Doc</a>
	 * 
	 * @param request Request object that contains the classifier name, the positive examples and the negative examples
	 *            to be use during the creation of the classifier.
	 * 
	 * @return The classifier that was created.
	 * @throws VisualRecognitionException When amount of items inside the zip is less than 10.
	 */
	@Processor(friendlyName = "Create a Classifier")
	public VisualClassifier createClassifier(@RefOnly @Default("#[payload]") ClassifierRequest request)
			throws VisualRecognitionException {
		return new CreateClassifierHandler(config.getService())
				.addPositiveExamples(request.getPositiveExamples())
				.addNegativeExamples(request.getNegativeExamples())
				.addClassifierId(request.getClassifierNameOrId())
				.execute();
	}
}