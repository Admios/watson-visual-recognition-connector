package org.mule.modules.watsonalchemylanguage.automation.functional;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class TestDataBuilder {

	public static final String TEST_URL_IMAGE;
	public static final File TEST_IMAGE_GROUP;
	public static final String TEST_CLASS_GROUP_CLASS;

	static {
		Properties contants = new Properties();
		try {
			contants.load(TestDataBuilder.class.getResourceAsStream("/constants.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		TEST_URL_IMAGE = contants.getProperty("url_image");
		TEST_CLASS_GROUP_CLASS = contants.getProperty("image_class1");
		File image = null;
		try {
			image = new File(TestDataBuilder.class.getResource("/images/Team2016.jpg").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		TEST_IMAGE_GROUP = image;
	}
}
