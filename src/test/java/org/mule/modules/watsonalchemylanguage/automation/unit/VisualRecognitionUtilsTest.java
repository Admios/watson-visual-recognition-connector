package org.mule.modules.watsonalchemylanguage.automation.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.admios.connector.watsonvisualrecognition.util.VisualRecognitionUtils;

public class VisualRecognitionUtilsTest {

	private File directory;
	private File emptyFile;
	private String[] pathHierarchy = { "src", "test", "resources", "testFolder" };
	private String testPath = StringUtils.join(pathHierarchy, File.separator) + File.separator;

	// Create files to test methods.
	@Before
	public void setupFiles() throws IOException {
		// Create directory
		directory = new File(testPath);
		directory.mkdir();

		// Create empty file
		emptyFile = new File(testPath + "empty");
		emptyFile.createNewFile();

		// Create empty zip
		// emptyFile = new File(testPath + "emptyZip");
		// emptyFile.createNewFile();
	}

	@Test
	public void isValiFileWithNullValue() {
		assertFalse(VisualRecognitionUtils.isValidFile(null));
	}

	@Test
	public void isValiFileWithAnFalseName() {
		File falseFile = new File(testPath + "falseName");
		assertFalse(VisualRecognitionUtils.isValidFile(falseFile));
	}

	@Test
	public void isValiFileWhenDirectory() {
		assertFalse(VisualRecognitionUtils.isValidFile(directory));
	}

	@Test
	public void isValiFileWithNormalFile() {
		assertTrue(VisualRecognitionUtils.isValidFile(emptyFile));
	}

	// Delete files.
	@After
	public void deleteFiles() {
		emptyFile.delete();
		directory.delete();
	}
}
