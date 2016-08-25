package com.admios.connector.watsonvisualrecognition.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.admios.connector.watsonvisualrecognition.exceptions.VisualRecognitionException;
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
	public static boolean isValidZipFile(File zipFile) throws VisualRecognitionException {
		System.out.println(zipFile.getPath());
		if(isValidFile(zipFile)) {
			try{
				ZipFile zf = new ZipFile(zipFile);
				Enumeration<? extends ZipEntry> e = zf.entries();
				int count = 0;
				while(e.hasMoreElements()){
					e.nextElement();
					count++;
				}
				
				if (count == 0) { return false; }
				
				if(count < 10){
					throw new VisualRecognitionException("Must be 10 or more images.");
				}
				return true;
			} catch (IOException e){
				return false;
			}
		}
		return false;
	}

}
