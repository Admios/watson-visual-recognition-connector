/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.model;

import java.io.File;
import java.util.Map;

import org.mule.api.annotations.Required;

/**
 * Class with all the options to create or update a classifier.
 * 
 * @author Admios
 */
public class ClassifierRequest {

	/**
	 * The name of the classifier that you want to create or the ID of the classifier that you want to update.
	 */
	@Required
	private String classifierNameOrId;
	
	@Required
	private Map<String, File> positiveExamples;

	/**
	 * A compressed (.zip) file of images that do not depict the visual subject of any of the classes of the new
	 * classifier. Must contain a minimum of 10 images.
	 */
	@Required
	private File negativeExamples;

	public ClassifierRequest() {
		super();
	}

	/**
	 * The name of the classifier that you want to create or the ID of the classifier that you want to update.
	 * 
	 * @return the classifierNameOrId
	 */
	public String getClassifierNameOrId() {
		return classifierNameOrId;
	}

	/**
	 * A compressed (.zip) file of images that depict the visual subject for a class within the new classifier. Must
	 * contain a minimum of 10 images.
	 * 
	 * @return the positiveExamples
	 */
	public Map<String, File> getPositiveExamples() {
		return positiveExamples;
	}


	/**
	 * A compressed (.zip) file of images that do not depict the visual subject of any of the classes of the new
	 * classifier. Must contain a minimum of 10 images.
	 * 
	 * @return the negativeExamples
	 */
	public File getNegativeExamples() {
		return negativeExamples;
	}

	/**
	 * @param classifierNameOrId the classifierNameOrId to set
	 */
	public void setClassifierNameOrId(String classifierNameOrId) {
		this.classifierNameOrId = classifierNameOrId;
	}

	/**
	 * @param positiveExamples the positiveExamples to set
	 */
	public void setPositiveExamples(Map<String, File> positiveExamples) {
		this.positiveExamples = positiveExamples;
	}

	/**
	 * @param negativeExamples the negativeExamples to set
	 */
	public void setNegativeExamples(File negativeExamples) {
		this.negativeExamples = negativeExamples;
	}

}
