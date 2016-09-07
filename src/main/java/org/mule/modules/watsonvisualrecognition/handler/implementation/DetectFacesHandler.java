package org.mule.modules.watsonvisualrecognition.handler.implementation;

import java.io.File;

import org.mule.modules.watsonvisualrecognition.handler.CommonHandler;
import org.mule.modules.watsonvisualrecognition.util.VisualRecognitionUtils;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;

public class DetectFacesHandler extends CommonHandler<DetectedFaces> {

	VisualRecognitionOptions.Builder options = new VisualRecognitionOptions.Builder();

	public DetectFacesHandler(VisualRecognition service) {

		super(service);
	}

	public DetectFacesHandler addSource(String url, File image) {
		VisualRecognitionUtils.addSource(options, url, image);
		return this;
	}

	@Override
	public DetectedFaces execute() {
		return service.detectFaces(options.build()).execute();
	}
}