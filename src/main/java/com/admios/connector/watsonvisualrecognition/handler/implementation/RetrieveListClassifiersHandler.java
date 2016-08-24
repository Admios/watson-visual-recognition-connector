package com.admios.connector.watsonvisualrecognition.handler.implementation;

import java.util.List;

import com.admios.connector.watsonvisualrecognition.handler.CommonHandler;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class RetrieveListClassifiersHandler extends CommonHandler<List<VisualClassifier>> {

	public RetrieveListClassifiersHandler(VisualRecognition service) {
		super(service);
	}

	@Override
	public List<VisualClassifier> execute() {
		return service.getClassifiers().execute();
	}
}
