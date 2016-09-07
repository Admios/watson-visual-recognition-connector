package org.mule.modules.watsonvisualrecognition.model;

import java.io.File;

import org.mule.api.annotations.param.Default;

public class ClassifierRequest {

	@Default("#[payload]") 
	private File positiveExamples;
	private String className;
	private String classifierId;
	private File negativeExamples;
	
	public File getPositiveExamples() {
		return positiveExamples;
	}
	public void setPositiveExamples(File positiveExamples) {
		this.positiveExamples = positiveExamples;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassifierId() {
		return classifierId;
	}
	public void setClassifierId(String classifierId) {
		this.classifierId = classifierId;
	}
	public File getNegativeExamples() {
		return negativeExamples;
	}
	public void setNegativeExamples(File negativeExamples) {
		this.negativeExamples = negativeExamples;
	}
}
