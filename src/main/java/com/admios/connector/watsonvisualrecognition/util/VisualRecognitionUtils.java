package com.admios.connector.watsonvisualrecognition.util;

import java.io.File;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;

public class VisualRecognitionUtils {

	public static VisualRecognitionOptions.Builder addSource(VisualRecognitionOptions.Builder options,
			String url, File image) {

		if (url != null) {
			options.url(url);
		} else if (image != null) {
			options.images(image);
		} else {
			throw new IllegalArgumentException("You have to specify a URL or a File");
		}
		return options;
	}

	public static ClassifyImagesOptions.Builder addSource(ClassifyImagesOptions.Builder options,
			String url, File image) {

		if (url != null) {
			options.url(url);
		} else if (image != null) {
			options.images(image);
		} else {
			throw new IllegalArgumentException("You have to specify a URL or a File");
		}
		return options;
	}

}
