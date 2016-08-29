package com.admios.connector.watsonvisualrecognition.handler.implementation;

import com.admios.connector.watsonvisualrecognition.handler.CommonHandler;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
//import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CreateClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class UpdateClassifierHandler extends CommonHandler<VisualClassifier> {

	//private UpdateCla.Builder builder = new CreateClassifierOptions.Builder();
	
	public UpdateClassifierHandler(VisualRecognition service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VisualClassifier execute() {
		// TODO Auto-generated method stub
		//return service.createClassifier(builder.build()).execute();
		return null;
		
	}

}
