/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.handler.implementation;

import java.io.File;
import java.util.Map;

import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.handler.CommonHandler;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierHandler extends CommonHandler<VisualClassifier> {

	/**
	 * Basic attribute for options with ClassifierOptions class
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
		ClassifierOptions classifierOptions = builder.build();
		return service.updateClassifier(classifierId, classifierOptions).execute();
	}
	
	/**
	 * Method useful for adding positive examples to a given class
	 * @param positiveExamples This map represents the relation between the class name
	 * and a compressed *.zip file that contains images associated with positive examples
	 * @return UpdateClassifierHandler 
	 * @throws VisualRecognitionException Common exception used in the visual recognition project
	 */
	public UpdateClassifierHandler addPositiveSamples(Map<String, File> positiveExamples) throws VisualRecognitionException {
		for(String key: positiveExamples.keySet()) {
			File file = positiveExamples.get(key);
			builder.addClass(key, file);
		}
		
		return this;
	}
	
	/**
	 * Method useful for adding positive examples to a given class
	 * @param file Compressed *.zip file that contains images associated with positive examples
	 * @return UpdateClassifierHandler
	 * @throws VisualRecognitionException Common exception used in the visual recognition project
	 */
	public UpdateClassifierHandler addNegativeSamples(File file) throws VisualRecognitionException {
		builder.negativeExamples(file);
		return this;
	}

}
