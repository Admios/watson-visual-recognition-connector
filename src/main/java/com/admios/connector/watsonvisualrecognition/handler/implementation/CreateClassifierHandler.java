package com.admios.connector.watsonvisualrecognition.handler.implementation;

import static com.admios.connector.watsonvisualrecognition.util.VisualRecognitionUtils.isValidZipFile;

import java.io.File;

import com.admios.connector.watsonvisualrecognition.handler.CommonHandler;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CreateClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class CreateClassifierHandler extends CommonHandler<VisualClassifier> {

	private CreateClassifierOptions.Builder builder = new CreateClassifierOptions.Builder();

	public CreateClassifierHandler(VisualRecognition service) {
		super(service);
	}

	@Override
	public VisualClassifier execute() {
		//TODO Check if has not null values before send.
		return service.createClassifier(builder.build()).execute();
		
	}

	public CreateClassifierHandler addPositiveExamples(String classname, File file) {
		if(isValidZipFile(file)) {
			builder.addClass("className", file);
		}
		return this;
	}

	public CreateClassifierHandler addNegativeExamples(File file) {
		if(isValidZipFile(file)) {
			builder.negativeExamples(file);
		}
		return this;
	}
}
