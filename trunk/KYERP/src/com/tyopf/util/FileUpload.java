package com.tyopf.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUpload {

	public Boolean UploadFile(File uploadFile,String targetDirectory,String targetFileName){
		File target = new File(targetDirectory, targetFileName);
		try {
			FileUtils.copyFile(uploadFile, target);
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}			
		
		return false;
	}

}
