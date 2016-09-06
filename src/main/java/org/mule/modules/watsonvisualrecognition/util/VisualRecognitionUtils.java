package org.mule.modules.watsonvisualrecognition.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;

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

	/**
	 * Check if the file is a valid Zip file and check if contains the minimum and maximum quantity of files.
	 * @param zipFile The file to check
	 * @param minimum Minimum quantity inside the zip file. Send -1 to ignore minimum
	 * @param maximum Maximum quantity inside the zip file. Send -1 to ignore maximum
	 * @return Return true if is a valid existing file, with a content between the range of minimum and maximum.
	 * @throws VisualRecognitionException
	 */
	@SuppressWarnings("resource")
	public static boolean isValidZipFile(File zipFile, final int minimum, final int maximum)
			throws VisualRecognitionException {
		if (isValidFile(zipFile)) {
			try {
				ZipFile zf = new ZipFile(zipFile);
				Enumeration<? extends ZipEntry> e = zf.entries();
				int count = 0;
				int stop = maximum == -1 ? minimum : maximum;
				while (e.hasMoreElements() && count < (stop + 1)) {
					e.nextElement();
					count++;
				}

				if (count == 0) { 
					return false;
				}

				if (maximum == -1 && minimum != -1 && count < minimum) {
					throw new VisualRecognitionException("Must be " + minimum + " or more images.");

				} else if (minimum == -1 && maximum != -1 && count > maximum) {
					throw new VisualRecognitionException("Must be less than " + (maximum + 1) + " images.");

				} else if ((maximum != -1 && minimum != -1) && (count < minimum || count > maximum)) {
					throw new VisualRecognitionException("zip content is out of range.");

				} else {
					return true;
				}

			} catch (IOException e) {
				return false;
			}
		}
		return false;
	}

}
