/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.handler.implementation;

import static org.mule.modules.watsonvisualrecognition.util.VisualRecognitionUtils.isValidZipFile;

import java.io.File;
import java.util.Map;

import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.handler.CommonHandler;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class CreateClassifierHandler extends CommonHandler<VisualClassifier> {

	private ClassifierOptions.Builder builder = new ClassifierOptions.Builder();


	public CreateClassifierHandler(VisualRecognition service) {
		super(service);
	}

	@Override
	public VisualClassifier execute() {
		// The builder validate the parameters.
		return service.createClassifier(builder.build()).execute();
	}

	public CreateClassifierHandler addPositiveExamples(Map<String, File> positiveExamples) throws VisualRecognitionException {
		for(String key: positiveExamples.keySet()) {
			File file = positiveExamples.get(key);
			if(isValidZipFile(file, 10, -1)) {
				builder.addClass(key, file);
			}
		}
		
		return this;
	}

	public CreateClassifierHandler addNegativeExamples(File file) throws VisualRecognitionException {
		if(isValidZipFile(file, 10, -1)) {
			builder.negativeExamples(file);
		}
		return this;
	}

	public CreateClassifierHandler addClassifierId(String classifierId) {
		builder.classifierName(classifierId);
		return this;
	}
}
