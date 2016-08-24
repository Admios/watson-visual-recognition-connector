package com.admios.connector.watsonvisualrecognition.handler.implementation;

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
		return service.createClassifier(builder.build()).execute();
		
	}

	public CreateClassifierHandler addPositiveExamples(String classname, File file) {
		//TODO Check if the file has content, that the reference already exist, not just is not null.
		if(file != null){
			builder.addClass("className", file);
		}
		return this;
	}

	public CreateClassifierHandler addNegativeExamples(File file) {
		//TODO Check if the file has content, that the reference already exist, not just is not null.
		if(file != null) {
			builder.negativeExamples(file);
		}
		return this;
	}
}
