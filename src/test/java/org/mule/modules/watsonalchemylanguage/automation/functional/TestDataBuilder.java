package org.mule.modules.watsonalchemylanguage.automation.functional;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class TestDataBuilder {

	public static final String TEST_GROUP_URL_IMAGE;
	public static final File TEST_IMAGE_GROUP;
	public static final String TEST_TEXT_URL_IMAGE;
	public static final File TEST_IMAGE_TEXT;
	public static final String TEST_GROUP_IMAGE_CLASS;
	public static final String TEST_TEXT_IMAGE_TEXT;

	static {
		Properties contants = new Properties();
		try {
			contants.load(TestDataBuilder.class.getResourceAsStream("/constants.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		TEST_GROUP_URL_IMAGE = contants.getProperty("url_group_image");
		TEST_GROUP_IMAGE_CLASS = contants.getProperty("group_image_class1");
		TEST_TEXT_URL_IMAGE = contants.getProperty("url_text_image");
		TEST_TEXT_IMAGE_TEXT = contants.getProperty("text_image_text");
		File group_image = null;
		File logo_image = null;
		try {
			group_image = new File(TestDataBuilder.class.getResource("/images/Team2016.jpg").toURI());
			logo_image = new File(TestDataBuilder.class.getResource("/images/text.jpg").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		TEST_IMAGE_GROUP = group_image;
		TEST_IMAGE_TEXT = logo_image;
	}
}
