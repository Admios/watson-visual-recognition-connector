/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.exceptions;

public class VisualRecognitionException extends Exception {

	private static final long serialVersionUID = 1L;

	public VisualRecognitionException() {
		super();
	}
	
	public VisualRecognitionException(String message){
		super(message);
	}

}
