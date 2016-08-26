package com.admios.connector.watsonvisualrecognition.handler.implementation;

import com.admios.connector.watsonvisualrecognition.handler.CommonHandler;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;

public class DeleteClassifierHandler extends CommonHandler<Void> {

	protected String classifierId;

	public DeleteClassifierHandler(VisualRecognition service, String classifierId) {
		super(service);
		this.classifierId = classifierId;
	}

	@Override
	public Void execute() {
		return service.deleteClassifier(classifierId).execute();
	}
}