package com.tyopf.action;

import java.io.File;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileUpload extends ActionSupport {
	File doc;

	String docContentType;

	String docFileName;

	String message;
	
	String fileurl;
	
	private static final String FILE_SAVE_PATH="D:/";

	@Override
	public String execute() throws Exception {

		String savaPath=FILE_SAVE_PATH;
		//if(docContentType.substring(0,5).equals("image")) savaPath += "images/";
		//if(docContentType.equals("image/pgif")) savaPath += "images/";
		doc.renameTo(new File(savaPath,doc.getName()));
		
		fileurl = savaPath + docFileName;
		message = savaPath + docFileName;
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public String getDocContentType() {
		return docContentType;
	}

	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

}
