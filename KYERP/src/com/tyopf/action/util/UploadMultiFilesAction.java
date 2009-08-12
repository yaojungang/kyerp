package com.tyopf.action.util;

import java.io.File;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadMultiFilesAction extends ActionSupport {
	private java.util.List<File> uploads;
	private java.util.List<String> fileNames;
	private java.util.List<String> uploadContentTypes;
	
	public java.util.List<String> getUploadFileName() {
		return fileNames;
	}
	
	public void setUploadFileName(java.util.List<String> fileNames) {
		this.fileNames = fileNames;
	}
	
	public java.util.List<File> getUpload() {
		return uploads;
	}
	
	public void setUpload(java.util.List<File> uploads) {
		this.uploads = uploads;
	}
	
	public void setUploadContentType(java.util.List<String> contentTypes) {
		this.uploadContentTypes = contentTypes;
	}
	
	public java.util.List<String> getUploadContentType() {
		return this.uploadContentTypes;
	}
	
	public String execute() throws Exception {
		if (uploads != null) {
			int i = 0;
			for (; i < uploads.size(); i++) {
				java.io.InputStream is = new java.io.FileInputStream(uploads.get(i));
				java.io.OutputStream os = new java.io.FileOutputStream("d:\\" + fileNames.get(i));
				byte buffer[] = new byte[8192];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					os.write(buffer, 0, count);
				}
				os.close();
				is.close();
			}
		}
		return SUCCESS;
	}
}
