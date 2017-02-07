/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.handler;

import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;

public abstract class CommonHandler<T> {

	protected final VisualRecognition service;

	public CommonHandler(VisualRecognition service) {
		this.service = service;
	}

	abstract public T execute() throws VisualRecognitionException;
}
