/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.handler.implementation;

import java.io.File;

import org.mule.modules.watsonvisualrecognition.handler.CommonHandler;
import org.mule.modules.watsonvisualrecognition.util.BuilderUtils;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;

public class DetectFacesHandler extends CommonHandler<DetectedFaces> {

	VisualRecognitionOptions.Builder options = new VisualRecognitionOptions.Builder();

	public DetectFacesHandler(VisualRecognition service) {

		super(service);
	}

	public DetectFacesHandler addSource(String url, File image) {
		BuilderUtils.addSource(options, url, image);
		return this;
	}

	@Override
	public DetectedFaces execute() {
		return service.detectFaces(options.build()).execute();
	}
}
