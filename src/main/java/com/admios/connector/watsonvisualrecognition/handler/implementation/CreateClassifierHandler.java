package com.admios.connector.watsonvisualrecognition.handler.implementation;

import com.admios.connector.watsonvisualrecognition.handler.CommonHandler;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CreateClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class CreateClassifierHandler extends CommonHandler<VisualClassifier> {

	private CreateClassifierOptions options;
	
	public CreateClassifierOptions getOptions() {
		return options;
	}

	public void setOptions(CreateClassifierOptions options) {
		this.options = options;
	}

	public CreateClassifierHandler(VisualRecognition service) {
		super(service);
	}
	
	@Override
	public VisualClassifier execute() {
		return service.createClassifier(options).execute();
		
	}

}
