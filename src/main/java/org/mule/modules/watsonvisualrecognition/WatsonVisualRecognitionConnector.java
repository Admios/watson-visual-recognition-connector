/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.mule.modules.watsonvisualrecognition.handler.implementation.UpdateClassifierHandler;
import org.mule.modules.watsonvisualrecognition.model.ClassifierRequest;
import org.mule.modules.watsonvisualrecognition.model.ClassifyImageRequest;
import org.mule.modules.watsonvisualrecognition.model.ImageRequest;
import org.mule.modules.watsonvisualrecognition.util.FileUtils;

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
	 * @param request Request with all the options for the classify an image operation.
	 * 
	 * @return A list of detected classes in the image.
	 */
	@Processor
	public VisualClassification classifyImage(@RefOnly @Default("#[payload]") ClassifyImageRequest request) {
		return new ClassifyImageHandler(config.getService())
				.addSource(request.getUrl(), request.getImage())
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
	 * @param request Request with all the options for the detect faces operation.
	 * 
	 * @return A list of detected faces, his age, gender and position in the image.
	 */
	@Processor
	public DetectedFaces detectFaces(@RefOnly @Default("#[payload]") ImageRequest request) {
		return new DetectFacesHandler(config.getService())
				.addSource(request.getUrl(), request.getImage())
				.execute();
	}

	/**
	 * Recognizes text in images. This is a beta function of the Visual Recognition service that supports only English
	 * language text identification.
	 * 
	 * <a href="http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#recognize_text">API Doc</a>
	 *
	 * @param request Request with all the options for the recognize text operation.
	 * 
	 * @return The text recognized in the image.
	 */
	@Processor
	public RecognizedText recognizeText(@RefOnly @Default("#[payload]") ImageRequest request) {
		return new RecognizeTextHandler(config.getService())
				.addSource(request.getUrl(), request.getImage())
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
	@Processor
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
	@Processor
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
	@Processor
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
	 * @param request Request with all the options for the create classifier operation.
	 * 
	 * @return The classifier that was created.
	 * @throws VisualRecognitionException When amount of items inside the zip is less than 10.
	 */
	@Processor
	public VisualClassifier createClassifier(@RefOnly @Default("#[payload]") ClassifierRequest request)
			throws VisualRecognitionException {
		return new CreateClassifierHandler(config.getService())
				.addPositiveExamples(request.getPositiveExamples())
				.addNegativeExamples(request.getNegativeExamples())
				.addClassifierId(request.getClassifierNameOrId())
				.execute();
	}

	/**
	 * Update an existing classifier by adding new classes, or by adding new images to existing classes
	 * 
	 * <a href="https://www.ibm.com/watson/developercloud/visual-recognition/api/v3/#update_a_classifier">API Doc</a>
	 * 
	 * @param request Request with all the options for the update classifier operation.
	 * 
	 * @return The classifier that was updated.
	 * @throws VisualRecognitionException When some of the zip files are empty
	 */
	@Processor
	public VisualClassifier updateClassifier(@RefOnly @Default("#[payload]") ClassifierRequest request)
			throws VisualRecognitionException {
		return new UpdateClassifierHandler(config.getService(), request.getClassifierNameOrId())
				.addPositiveSamples(request.getPositiveExamples())
				.addNegativeSamples(request.getNegativeExamples())
				.execute();
	}

	/**
	 * Convert an array of bytes into a File object to use it in the other operations.
	 *
	 * @param data The array of byte to convert.
	 * @param extension The extension of the file you want to create.
	 * 
	 * @return return A temporal file with the contend of the byte array.
	 * @throws IOException If there is any problem creating the temporal file.
	 * @throws IllegalArgumentException When the data parameter is not a byte[].
	 */
	@Processor
	public File byteArrayToFile(@Default("#[payload]") Object data, String extension) throws IOException {
		if (!(data instanceof byte[])) {
			throw new IllegalArgumentException("The data parameter should be a byte[] but was " + data.getClass());
		}
		return FileUtils.byteArrayToFile((byte[]) data, extension);
	}

	/**
	 * Convert an array of bytes into a File object to use it in the other operations.
	 *
	 * @param data The InputStream to convert.
	 * @param extension The extension of the file you want to create.
	 * 
	 * @return return A temporal file with the contend of the InputStream.
	 * @throws IOException If there is any problem creating the temporal file.
	 */
	@Processor
	public File inputStreamToFile(@Default("#[payload]") InputStream data, String extension) throws IOException {
		return FileUtils.inputStreamToFile(data, extension);
	}
}