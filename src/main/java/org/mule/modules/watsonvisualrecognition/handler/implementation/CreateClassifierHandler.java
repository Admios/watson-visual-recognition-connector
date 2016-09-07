package org.mule.modules.watsonvisualrecognition.handler.implementation;

import static org.mule.modules.watsonvisualrecognition.util.VisualRecognitionUtils.isValidZipFile;

import java.io.File;

import org.mule.modules.watsonvisualrecognition.exceptions.VisualRecognitionException;
import org.mule.modules.watsonvisualrecognition.handler.CommonHandler;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class CreateClassifierHandler extends CommonHandler<VisualClassifier> {

	private ClassifierOptions.Builder builder = new ClassifierOptions.Builder();


	public CreateClassifierHandler(VisualRecognition service) {
		super(service);
	}

	@Override
	public VisualClassifier execute() {
		// The builder validate the parameters.
		return service.createClassifier(builder.build()).execute();
	}

	public CreateClassifierHandler addPositiveExamples(String classname, File file) throws VisualRecognitionException {
		if(isValidZipFile(file,10, -1)) {
			builder.addClass(classname, file);
		}
		return this;
	}

	public CreateClassifierHandler addNegativeExamples(File file) throws VisualRecognitionException {
		if(isValidZipFile(file, 10, -1)) {
			builder.negativeExamples(file);
		}
		return this;
	}

	public CreateClassifierHandler addClassifierId(String classifierId) {
		builder.classifierName(classifierId);
		return this;
	}
}
