package com.admios.connector.watsonvisualrecognition.handler.implementation;

import java.io.File;

import com.admios.connector.watsonvisualrecognition.handler.CommonHandler;
import com.admios.connector.watsonvisualrecognition.util.VisualRecognitionUtils;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.RecognizedText;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;

public class RecognizeTextHandler extends CommonHandler<RecognizedText> {

	VisualRecognitionOptions.Builder options = new VisualRecognitionOptions.Builder();

	public RecognizeTextHandler(VisualRecognition service) {
		super(service);
	}

	public RecognizeTextHandler addSource(String url, File image) {
		VisualRecognitionUtils.addSource(options, url, image);
		return this;
	}

	@Override
	public RecognizedText execute() {
		return service.recognizeText(options.build()).execute();
	}

}
