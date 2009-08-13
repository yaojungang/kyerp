package com.tyopf.action.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.ISystemService;

@SuppressWarnings("serial")
public class UploadMultiFilesAction extends ActionSupport {
	private java.util.List<File> uploads;
	private java.util.List<String> fileNames;
	private java.util.List<String> uploadContentTypes;
	protected ISystemService systemService;
	public java.util.List<String> getUploadFileName() {
		return fileNames;
	}
	
	public java.util.List<File> getUploads() {
		return uploads;
	}

	public void setUploads(java.util.List<File> uploads) {
		this.uploads = uploads;
	}

	public java.util.List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(java.util.List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public java.util.List<String> getUploadContentTypes() {
		return uploadContentTypes;
	}

	public void setUploadContentTypes(java.util.List<String> uploadContentTypes) {
		this.uploadContentTypes = uploadContentTypes;
	}

	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
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
		String projectRealPath= ServletActionContext.getServletContext().getRealPath("/");
		String uploadPath = systemService.getSystemVarByName("dataPath");
		String targetDirectory=uploadPath+"/";
		System.out.println("projectRealPath:"+projectRealPath);
		if (uploads != null) {
			
			int i = 0;
			for (; i < uploads.size(); i++) {
				java.io.InputStream is = new java.io.FileInputStream(uploads.get(i));
				java.io.OutputStream os = new java.io.FileOutputStream(projectRealPath + fileNames.get(i));
				
				byte buffer[] = new byte[8192];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					os.write(buffer, 0, count);
				}
				os.close();
				is.close();
				File target = new File(targetDirectory, fileNames.get(i));
				System.out.println("uploadfile:"+target);
//				FileUtils.copyFile(file, target);
			}
		}
		return SUCCESS;
	}
}
