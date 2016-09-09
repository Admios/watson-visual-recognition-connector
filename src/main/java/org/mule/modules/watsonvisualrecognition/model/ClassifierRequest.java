package org.mule.modules.watsonvisualrecognition.model;

import java.io.File;

import org.mule.api.annotations.Required;

/**
 * Class with all the options for a classifier request.
 * 
 * @author Admios
 */
public class ClassifierRequest {

	/**
	 * The name of the classifier that you want to create or the ID of the classifier that you want to update.
	 */
	@Required
	private String classifierNameOrId;

	/**
	 * A compressed (.zip) file of images that depict the visual subject for a class within the new classifier. Must
	 * contain a minimum of 10 images.
	 */
	@Required
	private File positiveExamples;

	/**
	 * Name of the positive examples.
	 */
	@Required
	private String className;

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
	public File getPositiveExamples() {
		return positiveExamples;
	}

	/**
	 * Name of the positive examples.
	 * 
	 * @return the className
	 */
	public String getClassName() {
		return className;
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
	public void setPositiveExamples(File positiveExamples) {
		this.positiveExamples = positiveExamples;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @param negativeExamples the negativeExamples to set
	 */
	public void setNegativeExamples(File negativeExamples) {
		this.negativeExamples = negativeExamples;
	}

}
