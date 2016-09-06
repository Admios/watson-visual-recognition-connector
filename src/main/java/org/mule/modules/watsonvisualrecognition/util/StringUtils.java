package org.mule.modules.watsonvisualrecognition.util;

import java.net.MalformedURLException;
import java.net.URL;

public class StringUtils {

	private StringUtils() {
	}

	public static boolean isURL(final String text) {
		try {
			new URL(text);
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}
}
