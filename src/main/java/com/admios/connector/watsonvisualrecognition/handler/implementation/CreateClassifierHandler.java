package com.admios.connector.watsonvisualrecognition.handler.implementation;

import static com.admios.connector.watsonvisualrecognition.util.VisualRecognitionUtils.isValidZipFile;

import java.io.File;

import com.admios.connector.watsonvisualrecognition.exceptions.VisualRecognitionException;
import com.admios.connector.watsonvisualrecognition.handler.CommonHandler;
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

	public CreateClassifierHandler addPositiveExamples(String classname, File file) throws VisualRecognitionException {
		if(isValidZipFile(file)) {
			builder.addClass(classname, file);
		}
		return this;
	}

	public CreateClassifierHandler addNegativeExamples(File file) throws VisualRecognitionException {
		if(isValidZipFile(file)) {
			builder.negativeExamples(file);
		}
		return this;
	}

	public CreateClassifierHandler addName(String classifierName) {
		builder.classifierName(classifierName);
		return this;
	}
}
