package org.mule.modules.watsonvisualrecognition.model;

import java.io.File;
import java.util.List;

import org.mule.api.annotations.param.Optional;

/**
 * Class with all the options to create or update a classifier.
 * 
 * @author Admios
 */
public class ClassifyImageRequest {

	/**
	 * An array of classifier IDs to classify the images against.
	 */
	@Optional 
	private List<String> classifierIds;
	
	/**
	 * threshold A floating point value that specifies the minimum score a class must have to be displayed in the response.
	 */
	@Optional
	private Double threshold;

	/**
	 * The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 */
	@Optional
	private String url;
	
	/**
	 * The image file (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of 
	 * images in a .zip file is limited to 20, and limited to 5 MB. <b>If the URL is set the image will be
	 * ignored.</b>
	 */
	@Optional
	private File image;
	
	/**
	 * The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 */
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * The image file (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of
	 * images in a .zip file is limited to 20, and limited to 5 MB. <b>If the URL is set the image will be
	 * ignored.</b>
	 * @return {@link File}
	 */
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	
	/**
	 * An array of classifier IDs to classify the images against.
	 * @return {@link List}
	 */
	public List<String> getClassifierIds() {
		return classifierIds;
	}

	public void setClassifierIds(List<String> classifierIds) {
		this.classifierIds = classifierIds;
	}

	/**
	 * threshold A floating point value that specifies the minimum score a class must have to be displayed in the response.
	 */
	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

}
