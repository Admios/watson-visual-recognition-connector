package com.admios.connector.watsonvisualrecognition.handler.implementation;

import static com.admios.connector.watsonvisualrecognition.util.VisualRecognitionUtils.isValidZipFile;

import java.io.File;

import com.admios.connector.watsonvisualrecognition.exceptions.VisualRecognitionException;
import com.admios.connector.watsonvisualrecognition.handler.CommonHandler;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierHandler extends CommonHandler<VisualClassifier> {

	/**
	 * Basic atribute for options with ClassifierOptions class
	 */
	private ClassifierOptions.Builder builder = new ClassifierOptions.Builder();
	
	/**
	 * classifierId is an unique identifier associated with the classifiers in Watson SDK   
	 */
	protected String classifierId;
	
	public UpdateClassifierHandler(VisualRecognition service, String classifierId) {
		super(service);
		this.classifierId = classifierId;
	}

	@Override
	public VisualClassifier execute() {
		return service.updateClassifier(classifierId, builder.build()).execute();
	}
	
	/**
	 * Method useful for adding positive examples to a given class
	 * @param classname Name of the watson class
	 * @param file Compressed *.zip file that contains images associated with positive examples
	 * @return 
	 * @throws VisualRecognitionException Common exception used in the visual recognition project
	 */
	public UpdateClassifierHandler addPositiveSamples(String classname, File file) throws VisualRecognitionException {
		if(isValidZipFile(file)) {
			builder.addClass(classname, file);
		}
		return this;
	}
	
	/**
	 * Method useful for adding positive examples to a given class
	 * @param file Compressed *.zip file that contains images associated with positive examples
	 * @return
	 * @throws VisualRecognitionException Common exception used in the visual recognition project
	 */
	public UpdateClassifierHandler addNegativeSamples(File file) throws VisualRecognitionException {
		if(isValidZipFile(file)) {
			builder.negativeExamples(file);
		}
		return this;
	}

}
