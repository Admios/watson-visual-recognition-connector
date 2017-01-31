/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;

public class FileUtils {

	private FileUtils() {

	}

	public static boolean isValidFile(File file) {
		return file != null && file.exists() && file.isFile();
	}

	/**
	 * Check if the file is a valid Zip file and check if contains the minimum and maximum quantity of files.
	 * 
	 * @param zipFile The file to check
	 * @param minimum Minimum quantity inside the zip file. Send -1 to ignore minimum
	 * @param maximum Maximum quantity inside the zip file. Send -1 to ignore maximum
	 * @return Return true if is a valid existing file, with a content between the range of minimum and maximum.
	 * @throws VisualRecognitionException If the the file is invalid
	 */
	public static boolean isValidZipFile(File zipFile, final int minimum, final int maximum)
			throws VisualRecognitionException {

		boolean isValid = false;
		if (isValidFile(zipFile)) {
			try (ZipFile zf = new ZipFile(zipFile)) {

				Enumeration<? extends ZipEntry> e = zf.entries();
				int count = 0;
				int stop = maximum == -1 ? minimum : maximum;
				while (e.hasMoreElements() && count < (stop + 1)) {
					e.nextElement();
					count++;
				}

				if (count != 0) {
					isValid = isImageCountValid(count, minimum, maximum);
				}

			} catch (IOException e) {
				Logger.getLogger(BuilderUtils.class.getCanonicalName())
						.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return isValid;
	}

	private static boolean isImageCountValid(int count, int minimum, int maximum) throws VisualRecognitionException {
		boolean isValid = false;
		if (maximum == -1 && minimum != -1 && count < minimum) {
			throw new VisualRecognitionException("Must be " + minimum + " or more images.");

		} else if (isMaximun(count, minimum, maximum)) {
			throw new VisualRecognitionException("Must be less than " + (maximum + 1) + " images.");

		} else if (isMinimumn(count, minimum, maximum)) {
			throw new VisualRecognitionException("zip content is out of range.");

		} else {
			isValid = true;
		}
		return isValid;
	}

	private static boolean isMaximun(int count, int minimum, int maximum) {
		return minimum == -1 && maximum != -1 && count > maximum;
	}

	private static boolean isMinimumn(int count, int minimum, int maximum) {
		return (maximum != -1 && minimum != -1) && (count < minimum || count > maximum);
	}

	/**
	 * Create a temporal file with the content of data.
	 * 
	 * @param data The content to put in the file.
	 * @param extension The Extension of the file.
	 * @return return A temporal file with the contend of the InputStream.
	 * @throws IOException If there is any problem creating the temporal file.
	 */
	public static File inputStreamToFile(InputStream data, String extension) throws IOException {
		File file = File.createTempFile("Watson-VR-" + UUID.randomUUID(), "." + extension);
		org.apache.commons.io.FileUtils.copyInputStreamToFile(data, file);
		return file;
	}

}
