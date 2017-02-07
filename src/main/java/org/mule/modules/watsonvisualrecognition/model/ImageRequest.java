/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.mule.api.annotations.param.Optional;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionFileException;
import org.mule.modules.watsonvisualrecognition.util.FileUtils;

/**
 * Class that contains the image to be use for a image request.
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
	private InputStream image;

	/**
	 * The URL of an image (.jpg, or .png). Redirects are followed, so you can use shortened URLs.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * The image input stream (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of images
	 * in a .zip file is limited to 10. <b>If the URL is set the image will be ignored.</b>
	 * 
	 * @return the image
	 */
	public InputStream getImage() {
		return image;
	}

	/**
	 * The image file (.jpg, or .png) or compressed (.zip) file of images to classify. The max number of images in a
	 * .zip file is limited to 10. <b>If the URL is set the image will be ignored.</b>
	 * 
	 * @return the image file if the image input string was set
	 * @throws VisualRecognitionException When the connector can't process the image input stream.
	 */
	public File getImageAsFile() throws VisualRecognitionFileException {
		try {
			return image != null ? FileUtils.inputStreamToFile(image, "png") : null;
		} catch (IOException e) {
			throw new VisualRecognitionFileException(e);
		}
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
	public void setImage(InputStream image) {
		this.image = image;
	}

}
