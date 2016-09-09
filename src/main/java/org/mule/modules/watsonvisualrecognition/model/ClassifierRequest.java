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
	 * A compressed (.zip) file of images that depict the visual subject for a class within the new classifier. 
	 */
	@Required
	private File positiveExamples;
	
	/** 
	 * The name of the class
	 */
	@Required
	private String className;
	
	/**
	 * The ID of the classifier that you want to update
	 */
	@Required
	private String classifierId;
	
	/**
	 * A compressed (.zip) file of images that do not depict the visual subject of any of the classes of the new classifier.
	 */
	@Required
	private File negativeExamples;
	
	/**
	 * A compressed (.zip) file of images that depict the visual subject for a class within the new classifier.
	 */
	public File getPositiveExamples() {
		return positiveExamples;
	}
	public void setPositiveExamples(File positiveExamples) {
		this.positiveExamples = positiveExamples;
	}
	
	/** 
	 * The name of the class
	 */
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	/**
	 * The ID of the classifier that you want to update
	 */
	public String getClassifierId() {
		return classifierId;
	}
	public void setClassifierId(String classifierId) {
		this.classifierId = classifierId;
	}
	
	/**
	 * A compressed (.zip) file of images that do not depict the visual subject of any of the classes of the new classifier.
	 */
	public File getNegativeExamples() {
		return negativeExamples;
	}
	public void setNegativeExamples(File negativeExamples) {
		this.negativeExamples = negativeExamples;
	}
}
