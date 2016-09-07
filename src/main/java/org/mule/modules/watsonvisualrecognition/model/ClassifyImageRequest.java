package org.mule.modules.watsonvisualrecognition.model;

import java.io.File;
import java.util.List;

import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

public class ClassifyImageRequest {

	@Optional private List<String> classifierIds;
	@Optional private Double threshold;
	@Default("#[payload]")
	private String url;
	
	@Optional
	private File image;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	
	public List<String> getClassifierIds() {
		return classifierIds;
	}

	public void setClassifierIds(List<String> classifierIds) {
		this.classifierIds = classifierIds;
	}

	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

}
