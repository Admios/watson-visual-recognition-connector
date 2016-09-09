package org.mule.modules.watsonvisualrecognition.model;

import java.io.File;

import org.mule.api.annotations.param.Optional;

/**
 * Class with all the options for a image request.
 * 
 * @author Admios
 */
public class ImageRequest {

	/**
	 * The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 */
	@Optional
	private String url;

	/**
	 * The image file (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of images in a
	 * .zip file is limited to 10. <b>If the URL is set the image will be ignored.</b>
	 */
	@Optional
	private File image;

	public ImageRequest() {
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
	 * .zip file is limited to 10. <b>If the URL is set the image will be ignored.</b>
	 * 
	 * @return the image
	 */
	public File getImage() {
		return image;
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
