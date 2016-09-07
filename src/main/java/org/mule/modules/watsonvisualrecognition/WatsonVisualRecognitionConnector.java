package org.mule.modules.watsonvisualrecognition;

import java.io.File;
import java.util.List;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.licensing.RequiresEnterpriseLicense;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
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
	Config config;

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
	 * API Doc: {@see http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#classify_an_image}
	 *
	 * {@sample.xml ../../../doc/watson-visual-recognition-connector.xml.sample watson-visual-recognition:classifyImage}
	 *
	 * @param url The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 * @param image The image file (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of
	 *            images in a .zip file is limited to 20, and limited to 5 MB. <b>If the URL is set the image will be
	 *            ignored.</b>
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

	/**
	 * Analyze faces in images and get data about them, such as estimated age and gender. Images must be in .jpeg, or
	 * .png format.
	 * 
	 * API Doc: {@see http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#classify_an_image}
	 *
	 * {@sample.xml ../../../doc/watson-visual-recognition-connector.xml.sample watson-visual-recognition:detectFaces}
	 *
	 * @param url The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 * @param image The image file (.jpg, or .png) or compressed (.zip) file of images to analyze. The max number of
	 *            images in a .zip file is limited to 15. <b>If the URL is set the image will be ignored.</b>
	 * 
	 * @return return {@link DetectedFaces}
	 */
	@Processor
	public DetectedFaces detectFaces(@Default("#[payload]") String url, @Optional File image) {
		return new DetectFacesHandler(config.getService())
				.addSource(url, image)
				.execute();
	}

	/**
	 * Recognizes text in images. This is a beta function of the Visual Recognition service that supports only English
	 * language text identification.
	 * 
	 * API Doc: {@see http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#recognize_text}
	 *
	 * {@sample.xml ../../../doc/watson-visual-recognition-connector.xml.sample watson-visual-recognition:recognizeText}
	 *
	 * @param url The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 * @param image The image file (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of
	 *            images in a .zip file is limited to 10. <b>If the URL is set the image will be ignored.</b>
	 * 
	 * @return return {@link RecognizedText}
	 */
	@Processor
	public RecognizedText recognizeText(@Default("#[payload]") String url, @Optional File image) {
		return new RecognizeTextHandler(config.getService())
				.addSource(url, image)
				.execute();
	}

	/**
	 * Retrieve a list of user-created classifiers.
	 * 
	 * API Doc: {@see http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#create_a_classifier}
	 *
	 * {@sample.xml ../../../doc/watson-visual-recognition-connector.xml.sample
	 * watson-visual-recognition:retrieveListOfClassifiers}
	 * 
	 * @return return {@link List<VisualClassifier>}
	 */
	@Processor
	public List<VisualClassifier> retrieveListOfClassifiers() {
		return new RetrieveListClassifiersHandler(config.getService()).execute();
	}

	/**
	 * Retrieve information about a specific classifier.
	 * 
	 * API Doc:
	 * {@see http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#retrieve_classifier_details}
	 *
	 * {@sample.xml ../../../doc/watson-visual-recognition-connector.xml.sample
	 * watson-visual-recognition:retrieveClassifierDetails}
	 * 
	 * @param classifierId The ID of the classifier for which you want details.
	 * 
	 * @return return {@link VisualClassifier}
	 */
	@Processor
	public VisualClassifier retrieveClassifierDetails(@Default("#[payload]") String classifierId) {
		return new RetrieveClassifierDetailsHandler(config.getService(), classifierId).execute();
	}

	/**
	 * Delete a custom classifier with the specified classifier ID.
	 * 
	 * API Doc:
	 * {@see http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?curl#delete_a_classifier}
	 *
	 * {@sample.xml ../../../doc/watson-visual-recognition-connector.xml.sample
	 * watson-visual-recognition:deleteClassifier}
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
	 * several compressed (.zip) files, including files containing positive or negative images (.jpg, or .png).
	 * You must supply at least two compressed files, either two positive example files or one positive and one
	 * negative example file.
	 * 
	 * API Doc:
	 * {@see http://www.ibm.com/watson/developercloud/visual-recognition/api/v3/?java#create_a_classifier}
	 * 
	 * {@sample.xml ../../../doc/watson-visual-recognition-connector.xml.sample watson-visual-recognition:createClassifier}
	 * 
	 * @param positiveExamples A compressed (.zip) file of images that depict the visual subject for a class within the new classifier. Must contain a minimum of 10 images.
	 * @param className Name of the positive examples.
	 * @param classifierName The name of the new classifier. Cannot contain spaces or special characters.
	 * @param negativeExamples A compressed (.zip) file of images that do not depict the visual subject of any of the classes of the new classifier. Must contain a minimum of 10 images.
	 * @return return {@link List<VisualClassifier>}
	 * @throws VisualRecognitionException When amount of items inside the zip is less than 10.
	 */
	@Processor
	public VisualClassifier createClassifier(@Default("#[payload]") File positiveExamples, String className, 
			String classifierName, File negativeExamples) throws VisualRecognitionException {
		return new CreateClassifierHandler(config.getService())
				.addPositiveExamples(className, positiveExamples)
				.addNegativeExamples(negativeExamples)
				.addName(classifierName)
				.execute();
	}
	
	/**
	 * Update an existing classifier by adding new classes, or by adding new images to existing classes
	 * 
	 * API Doc:
	 * {@see https://www.ibm.com/watson/developercloud/visual-recognition/api/v3/#update_a_classifier}
	 * 
	 * @param positiveExamples A compressed (.zip) file of images that depict the visual subject for a class within the new classifier.
	 * @param className The name of the class
	 * @param classifierId The ID of the classifier that you want to update
	 * @param negativeExamples A compressed (.zip) file of images that do not depict the visual subject of any of the classes of the new classifier.
	 * @return return {@link List<VisualClassifier>}
	 * @throws VisualRecognitionException When some of the zip files are empty 
	 */
	@Processor
	public VisualClassifier updateClassifier(@Default("#[payload]") File positiveExamples, String className,
			String classifierId, File negativeExamples) throws VisualRecognitionException {
		return new UpdateClassifierHandler(config.getService(), classifierId)
				.addPositiveSamples(className, positiveExamples)
				.addNegativeSamples(negativeExamples)
				.execute();
	}
}