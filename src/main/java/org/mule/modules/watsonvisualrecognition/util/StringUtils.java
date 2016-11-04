/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
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
