package org.mule.modules.watsonvisualrecognition.model;

import java.io.File;
import java.util.List;

import org.mule.api.annotations.param.Optional;

/**
 * Class with all the options for the classify an image operation.
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
	 * A floating point value that specifies the minimum score a class must have to be displayed in the response.
	 */
	@Optional
	private Double threshold;

	/**
	 * The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 */
	@Optional
	private String url;

	/**
	 * The image file (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of images in a
	 * .zip file is limited to 20, and limited to 5 MB. <b>If the URL is set the image will be ignored.</b>
	 */
	@Optional
	private File image;

	public ClassifyImageRequest() {
	}

	/**
	 * An array of classifier IDs to classify the images against.
	 * 
	 * @return the classifierIds
	 */
	public List<String> getClassifierIds() {
		return classifierIds;
	}

	/**
	 * A floating point value that specifies the minimum score a class must have to be displayed in the response.
	 * 
	 * @return the threshold
	 */
	public Double getThreshold() {
		return threshold;
	}

	/**
	 * The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * The image file (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of images in a
	 * .zip file is limited to 20, and limited to 5 MB. <b>If the URL is set the image will be ignored.</b>
	 * 
	 * @return the image
	 */
	public File getImage() {
		return image;
	}

	/**
	 * @param classifierIds the classifierIds to set
	 */
	public void setClassifierIds(List<String> classifierIds) {
		this.classifierIds = classifierIds;
	}

	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(File image) {
		this.image = image;
	}

}
