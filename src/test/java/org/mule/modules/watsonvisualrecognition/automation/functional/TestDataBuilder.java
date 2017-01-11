/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.functional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class TestDataBuilder {

	public static final String TEST_GROUP_URL_IMAGE;
	public static final InputStream TEST_IMAGE_GROUP;
	public static final String TEST_PERSON_URL_IMAGE;
	public static final InputStream TEST_PERSON_IMAGE;
	public static final String TEST_TEXT_URL_IMAGE;
	public static final InputStream TEST_IMAGE_TEXT;
	public static final String TEST_GROUP_IMAGE_CLASS;
	public static final Integer TEST_PERSON_IMAGE_RECOGNIZED_FACES;
	public static final String TEST_TEXT_IMAGE_TEXT;
	public static final String TEST_SAMPLE_ZIP_FILE;
	public static final String TEST_NEGATIVE_ZIP_FILE;
	public static final String TEST_NEGATIVE_CAT_FILE;
	public static final String TEST_HUSKY_FILE;
	public static final String TEST_GOLDEN_FILE;
	public static final String TEST_BEAGLE_FILE;
	public static final String TEST_NEGATIVE_MORE_CATS_FILE;
	public static final String TEST_DALMATION_FILE;

	static {
		Properties constants = new Properties();
		try {
			constants.load(TestDataBuilder.class.getResourceAsStream("/constants.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		TEST_GROUP_URL_IMAGE = constants.getProperty("url_group_image");
		TEST_PERSON_URL_IMAGE = constants.getProperty("url_person_image");
		TEST_GROUP_IMAGE_CLASS = constants.getProperty("group_image_class1");
		TEST_PERSON_IMAGE_RECOGNIZED_FACES = Integer.valueOf(constants.getProperty("person_image_reconigzed_faces"));
		TEST_TEXT_URL_IMAGE = constants.getProperty("url_text_image");
		TEST_TEXT_IMAGE_TEXT = constants.getProperty("text_image_text");
		TEST_SAMPLE_ZIP_FILE = constants.getProperty("sample_zip_file");
		TEST_NEGATIVE_ZIP_FILE = constants.getProperty("negative_zip_file");
		TEST_NEGATIVE_CAT_FILE = constants.getProperty("negative_cat_file");
		TEST_HUSKY_FILE = constants.getProperty("husky_file");
		TEST_GOLDEN_FILE = constants.getProperty("golden_file");
		TEST_BEAGLE_FILE = constants.getProperty("beagle_file");
		TEST_NEGATIVE_MORE_CATS_FILE = constants.getProperty("negative_morecats_file");
		TEST_DALMATION_FILE = constants.getProperty("dalmation_file");

		TEST_IMAGE_GROUP = TestDataBuilder.class.getResourceAsStream("/images/Team2016.jpg");
		TEST_IMAGE_TEXT = TestDataBuilder.class.getResourceAsStream("/images/text.jpg");
		TEST_PERSON_IMAGE = TestDataBuilder.class.getResourceAsStream("/images/person.jpg");
	}

	public static String sampleZipPath() {
		String[] path = { "src", "test", "resources", TEST_SAMPLE_ZIP_FILE };
		return StringUtils.join(path, File.separator);
	}

	public static String negativeSampleZipPath() {
		String[] path = { "src", "test", "resources", TEST_NEGATIVE_ZIP_FILE };
		return StringUtils.join(path, File.separator);
	}

	public static String negativeCatExamplePath() {
		String[] path = { "src", "test", "resources", TEST_NEGATIVE_CAT_FILE };
		return StringUtils.join(path, File.separator);
	}

	public static String positiveHuskyExamplePath() {
		String[] path = { "src", "test", "resources", TEST_HUSKY_FILE };
		return StringUtils.join(path, File.separator);
	}

	public static String positiveGoldenExamplePath() {
		String[] path = { "src", "test", "resources", TEST_GOLDEN_FILE };
		return StringUtils.join(path, File.separator);
	}

	public static String positiveBeagleExamplePath() {
		String[] path = { "src", "test", "resources", TEST_BEAGLE_FILE };
		return StringUtils.join(path, File.separator);
	}

	public static String negativeMoreCatsExamplePath() {
		String[] path = { "src", "test", "resources", TEST_NEGATIVE_MORE_CATS_FILE };
		return StringUtils.join(path, File.separator);
	}

	public static String positiveDalmationsExamplePath() {
		String[] path = { "src", "test", "resources", TEST_DALMATION_FILE };
		return StringUtils.join(path, File.separator);
	}
}
