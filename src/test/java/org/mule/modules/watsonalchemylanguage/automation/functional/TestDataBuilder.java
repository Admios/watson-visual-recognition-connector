package org.mule.modules.watsonalchemylanguage.automation.functional;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class TestDataBuilder {

	public static final String TEST_GROUP_URL_IMAGE;
	public static final File TEST_IMAGE_GROUP;
	public static final String TEST_TEXT_URL_IMAGE;
	public static final File TEST_IMAGE_TEXT;
	public static final String TEST_GROUP_IMAGE_CLASS;
	public static final Integer TEST_GROUP_IMAGE_RECOGNIZED_FACES;
	public static final String TEST_TEXT_IMAGE_TEXT;
	public static final String TEST_SAMPLE_ZIP_FILE;

	static {
		Properties constants = new Properties();
		try {
			constants.load(TestDataBuilder.class.getResourceAsStream("/constants.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		TEST_GROUP_URL_IMAGE = constants.getProperty("url_group_image");
		TEST_GROUP_IMAGE_CLASS = constants.getProperty("group_image_class1");
		TEST_GROUP_IMAGE_RECOGNIZED_FACES = Integer.valueOf(constants.getProperty("group_image_reconigzed_faces"));
		TEST_TEXT_URL_IMAGE = constants.getProperty("url_text_image");
		TEST_TEXT_IMAGE_TEXT = constants.getProperty("text_image_text");
		TEST_SAMPLE_ZIP_FILE = constants.getProperty("sample_zip_file");
		
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
	
	public static String sampleZipPath() {
		String[] path = { "src", "test", "resources", TEST_SAMPLE_ZIP_FILE};
		return StringUtils.join(path, File.separator);
	}
}
