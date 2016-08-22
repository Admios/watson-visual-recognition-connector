package com.admios.connector.watsonvisualrecognition.handler;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;

public abstract class CommonHandler<T> {

	protected final VisualRecognition service;

	public CommonHandler(VisualRecognition service, String param, Object value) {
		this.service = service;
	}

	abstract public T execute();
}
