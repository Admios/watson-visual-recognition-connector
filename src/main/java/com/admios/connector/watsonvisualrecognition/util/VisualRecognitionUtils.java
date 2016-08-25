package com.admios.connector.watsonvisualrecognition.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;

public class VisualRecognitionUtils {

	public static VisualRecognitionOptions.Builder addSource(VisualRecognitionOptions.Builder options,
			String url, File image) {

		if (url != null) {
			options.url(url);
		} else if (isValidFile(image)) {
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
		} else if (isValidFile(image)) {
			options.images(image);
		} else {
			throw new IllegalArgumentException("You have to specify a URL or a File");
		}
		return options;
	}

	public static boolean isValidFile(File file) {
		return (file != null && file.exists() && file.isFile());
	}

	@SuppressWarnings("resource")
	public static boolean isValidZipFile(File zipFile) {
		System.out.println(zipFile.getPath());
		if(isValidFile(zipFile)) {
			try{
				ZipInputStream zip = new ZipInputStream(new FileInputStream(zipFile));
				if(zip.getNextEntry() != null){
					return true;
				}
				return false;
			} catch (IOException e){
				return false;
			}
		}
		return false;
	}

}
