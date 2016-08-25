package org.mule.modules.watsonalchemylanguage.automation.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.admios.connector.watsonvisualrecognition.util.VisualRecognitionUtils;

public class VisualRecognitionUtilsTest {

	private File directory, emptyFile, zipFile, textFile;
	private String[] pathHierarchy = { "src", "test", "resources", "testFolder" };
	private final String testPath = StringUtils.join(pathHierarchy, File.separator) + File.separator;

	// Create files to test methods.
	@Before
	public void setupFiles() throws IOException {
		// Create directory
		directory = new File(testPath);
		directory.mkdir();

		// Create empty file
		emptyFile = new File(testPath + "empty");
		emptyFile.createNewFile();

		// Create text file
		textFile = createATextFile();

		// Create empty zip
		zipFile = createZipFile();
		// emptyFile.createNewFile();
	}

	@Test
	public void isValidFileWithNullValue() {
		assertFalse(VisualRecognitionUtils.isValidFile(null));
	}

	@Test
	public void isValidFileWithAnFalseName() {
		File falseFile = new File(testPath + "falseName");
		assertFalse(VisualRecognitionUtils.isValidFile(falseFile));
	}

	@Test
	public void isValidFileWhenDirectory() {
		assertFalse(VisualRecognitionUtils.isValidFile(directory));
	}

	@Test
	public void isValidFileWithNormalFile() {
		assertTrue(VisualRecognitionUtils.isValidFile(emptyFile));
	}

	@Test
	public void isValidZipFileWithANormalFile() {
		assertFalse(VisualRecognitionUtils.isValidZipFile(textFile));
	}
	
	@Test
	public void isValidZipFileWithAZipFile() {
		assertTrue(VisualRecognitionUtils.isValidZipFile(zipFile));
	}

	// Delete files.
	@After
	public void deleteFiles() {
		emptyFile.delete();
		textFile.delete();
		zipFile.delete();
		directory.delete();
	}
	
	
	private File createZipFile() throws FileNotFoundException, IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("Test String");

		File f = new File(testPath + "test.zip");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
		ZipEntry e = new ZipEntry("mytext.txt");
		out.putNextEntry(e);

		byte[] data = sb.toString().getBytes();
		out.write(data, 0, data.length);
		out.closeEntry();

		out.close();
		
		return f;
	}
	
	private File createATextFile() throws IOException {
		List<String> lines = Arrays.asList("The first line", "The second line");
		Path file = Paths.get(testPath + "text-file.txt");
		Files.write(file, lines, Charset.forName("UTF-8"));
		return file.toFile();
	}
}
