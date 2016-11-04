/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.handler.implementation;

import java.util.List;

import org.mule.modules.watsonvisualrecognition.handler.CommonHandler;

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
