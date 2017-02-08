/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.handler.implementation;

import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.handler.CommonHandler;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;

public class DeleteClassifierHandler extends CommonHandler<Void> {

	protected String classifierId;

	public DeleteClassifierHandler(VisualRecognition service, String classifierId) {
		super(service);
		this.classifierId = classifierId;
	}

	@Override
	public Void execute() throws VisualRecognitionException {
		try {
			return service.deleteClassifier(classifierId).execute();
		} catch (Exception e) {
			throw new VisualRecognitionException(e);
		}
	}
}