package org.mule.modules.watsonvisualrecognition.handler.implementation;

import java.io.File;
import java.util.List;

import org.mule.modules.watsonvisualrecognition.handler.CommonHandler;
import org.mule.modules.watsonvisualrecognition.util.VisualRecognitionUtils;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

public class ClassifyImageHandler extends CommonHandler<VisualClassification> {

	ClassifyImagesOptions.Builder options = new ClassifyImagesOptions.Builder();

	public ClassifyImageHandler(VisualRecognition service) {
		super(service);
	}

	public ClassifyImageHandler addSource(String url, File image) {
		VisualRecognitionUtils.addSource(options, url, image);
		return this;
	}

	public ClassifyImageHandler addClassifierId(List<String> classifierIds) {
		if (classifierIds != null) {
			options.classifierIds(classifierIds);
		}
		return this;
	}

	public ClassifyImageHandler addThreshold(Double threshold) {
		if (threshold != null) {
			options.threshold(threshold);
		}
		return this;
	}

	@Override
	public VisualClassification execute() {
		return service.classify(options.build()).execute();
	}

}
