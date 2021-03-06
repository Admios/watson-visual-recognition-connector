/**
 * (c) Copyright 2016 Admios. The software in this package is published under the terms of the Apache License Version 2.0, a copy of which has been included with this distribution in the LICENSE.md file.
 */
package org.mule.modules.watsonvisualrecognition.automation.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import org.mule.modules.watsonvisualrecognition.automation.functional.TestDataBuilder;
import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.util.FileUtils;

public class FileUtilsTest {

	private File directory, emptyFile, zipFile, textFile;
	private String[] pathHierarchy = { "src", "test", "resources", "testFolder" };
	private final String testPath = StringUtils.join(pathHierarchy, File.separator) + File.separator;
	private final File sampleZip = new File(TestDataBuilder.sampleZipPath());

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
	}

	@Test
	public void isValidFileWithNullValue() {
		assertFalse(FileUtils.isValidFile(null));
	}

	@Test
	public void isValidFileWithAnFalseName() {
		File falseFile = new File(testPath + "falseName");
		assertFalse(FileUtils.isValidFile(falseFile));
	}

	@Test
	public void isValidFileWhenDirectory() {
		assertFalse(FileUtils.isValidFile(directory));
	}

	@Test
	public void isValidFileWithNormalFile() {
		assertTrue(FileUtils.isValidFile(emptyFile));
	}

	// ------ isValidZipFile() Success cases ---------------
	@Test
	public void isValidZipFile() {
		try {
			assertTrue(FileUtils.isValidZipFile(sampleZip, 10, -1));
		} catch (VisualRecognitionException e) {
			fail("This is a zip file. But has less image than required. " + e.getMessage());
		}
	}

	@Test
	public void isValidZipFileWithExtraImages() throws IOException {
		// File with more than minimum and less than maximum but evaluated in two different ways.
		zipFile = createZipFile("moreThanMinimum.zip", 12);
		try {
			assertTrue(FileUtils.isValidZipFile(zipFile, 10, -1));
			assertTrue(FileUtils.isValidZipFile(zipFile, -1, 15));
		} catch (VisualRecognitionException e) {
			fail("This is a zip file. But has less image than required. " + e.getMessage());
		}
	}

	@Test
	public void isValidZipFileWithExactMaximum() throws IOException {
		// File with the exact maximum.
		zipFile = createZipFile("ExaxtMaximum.zip", 15);
		try {
			assertTrue(FileUtils.isValidZipFile(zipFile, -1, 15));
		} catch (VisualRecognitionException e) {
			fail("This is a zip file. But has less image than required. " + e.getMessage());
		}
	}

	// ------ isValidZipFile() Fail cases ---------------
	@Test
	public void isValidZipFileWithANormalFile() {
		try {
			assertFalse(FileUtils.isValidZipFile(textFile, 10, -1));
		} catch (VisualRecognitionException e) {
			fail("This is a non zip file. Should return false.");
		}
	}

	@Test
	public void isValidZipFileWithNull() {
		try {
			assertFalse(FileUtils.isValidZipFile(null, -1, -1));
		} catch (VisualRecognitionException e) {
			fail("Should send false with null reference");
		}
	}

	@Test(expected = VisualRecognitionException.class)
	public void isValidZipFileWithLessImagesInside() throws IOException, VisualRecognitionException {
		zipFile = createZipFile("lessimage.zip", 9);
		FileUtils.isValidZipFile(zipFile, 10, -1);
	}

	@Test(expected = VisualRecognitionException.class)
	public void isValidZipFileExceedsImages() throws IOException, VisualRecognitionException {
		zipFile = createZipFile("exceeds.zip", 26);
		FileUtils.isValidZipFile(zipFile, -1, 25);
	}

	@Test(expected = VisualRecognitionException.class)
	public void isValidZipOutOfRange() throws IOException, VisualRecognitionException {
		zipFile = createZipFile("outOfRange.zip", 5);
		FileUtils.isValidZipFile(zipFile, 10, 25);
	}

	// Delete files.
	@After
	public void deleteFiles() {
		emptyFile.delete();
		textFile.delete();
		if (zipFile != null) {
			zipFile.delete();
		}
		directory.delete();
	}

	private File createZipFile(final String name, final int maximum) throws FileNotFoundException, IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("Test String");

		File f = new File(testPath + name);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
		int counter = 0;
		while (counter < maximum) {
			ZipEntry e = new ZipEntry("mytext" + counter + ".txt");
			out.putNextEntry(e);

			byte[] data = sb.toString().getBytes();
			out.write(data, 0, data.length);
			out.closeEntry();
			counter++;
		}

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
