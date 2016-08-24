package org.mule.modules.watsonalchemylanguage.automation.unit;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VisualRecognitionUtilsTest {

	private File directory;
	private File emptyFile;
	private String[] pathHierarchy = {"src", "test", "resources", "testFolder"};
	private String testPath = StringUtils.join(pathHierarchy, File.separator) + File.separator;
	
	// Create files to test methods.
	@Before
	public void setupFiles() throws IOException{
		// Create directory
		directory = new File(testPath);
		directory.mkdir();
		
		// Create empty file
/*		emptyFile = new File(testPath + "empty");
		emptyFile.createNewFile();
		
		// Create empty zip
		emptyFile = new File(testPath + "empty");
		emptyFile.createNewFile();*/
	}
	
	@Test
	public void sampleTest(){
		
	}
	// Delete files.
	@After
	public void deleteFiles(){
		directory.delete();
	}
}
