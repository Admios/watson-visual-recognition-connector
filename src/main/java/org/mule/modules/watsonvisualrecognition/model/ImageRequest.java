package org.mule.modules.watsonvisualrecognition.model;

import java.io.File;

import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

public class ImageRequest {

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
	
	
}
