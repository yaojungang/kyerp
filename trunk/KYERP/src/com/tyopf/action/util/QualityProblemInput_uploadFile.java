package com.tyopf.action.util;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IAFService;
import com.tyopf.service.ISystemService;
import com.tyopf.vo.AfBase;
import com.tyopf.vo.AfQualityProblem;
import com.tyopf.vo.AfQualityProblemAttachment;

@SuppressWarnings("serial")
public class QualityProblemInput_uploadFile extends ActionSupport implements ServletContextAware, SessionAware {
	private java.util.List<File> uploads;
	private java.util.List<String> fileNames;
	private java.util.List<String> uploadContentTypes;
	protected ISystemService systemService;
	protected IAFService afService;
	private ServletContext context;
	private int afQPId;
	private int afQPAttachmentId;
	private int afId;
	protected Map session;
	private String fileName;
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public int getAfQPAttachmentId() {
		return afQPAttachmentId;
	}
	
	public void setAfQPAttachmentId(int afQPAttachmentId) {
		this.afQPAttachmentId = afQPAttachmentId;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getAfId() {
		return afId;
	}
	
	public void setAfId(int afId) {
		this.afId = afId;
	}
	
	public IAFService getAfService() {
		return afService;
	}
	
	public void setAfService(IAFService afService) {
		this.afService = afService;
	}
	
	public Map getSession() {
		return session;
	}
	
	public void setSession(Map session) {
		this.session = session;
	}
	
	public int getAfQPId() {
		return afQPId;
	}
	
	public void setAfQPId(int afQPId) {
		this.afQPId = afQPId;
	}
	
	public ServletContext getContext() {
		return context;
	}
	
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
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
		AfBase af = (AfBase) afService.getAFById(afId);
		String projectRealPath = ServletActionContext.getServletContext().getRealPath("/");
		String uploadPath = systemService.getSystemVarByName("dataPath");
		String targetDirectory = context.getRealPath(uploadPath + "/QualityProblemAttactent/");
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
				File file = new File(projectRealPath, fileNames.get(i));
				String targetFileName = af.getIso() + af.getAfNo() + "-" + afQPId + "-" + System.currentTimeMillis() + "." + com.tyopf.util.FileUtil.getExtension(file);
				File target = new File(targetDirectory, targetFileName);
				FileUtils.copyFile(file, target);
				FileUtils.deleteQuietly(file);
				AfQualityProblemAttachment qpa = new AfQualityProblemAttachment();
				qpa.setAfqpId(afQPId);
				qpa.setFileName(targetFileName);
				qpa.setName(fileNames.get(i));
				afService.saveQpa(qpa);
			}
		}
		AfQualityProblem afqp = (AfQualityProblem) afService.getAFQPById(afQPId);
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		request.put("AFQualityProblem", afqp);
		request.put("pageTitle", "【" + af.getPresswork() + "】质量问题");
		request.put("DeptTree", deptTree);
		request.put("message", "质量问题图片上传成功！");
		return SUCCESS;
	}
	
	
	
	public String delQualityProblemAttachment() throws Exception {
		String uploadPath = systemService.getSystemVarByName("dataPath");
		String targetDirectory = context.getRealPath(uploadPath + "/QualityProblemAttactent/");
		File file = new File(targetDirectory, fileName);
		FileUtils.deleteQuietly(file);
		AfQualityProblemAttachment a = afService.getAFQPAttachmentById(afQPAttachmentId);
		afService.delQPAttachment(a);
		AfQualityProblem afqp = (AfQualityProblem) afService.getAFQPById(afQPId);
		List deptTree = (List) session.get("DeptTree");
		if (null == deptTree) {
			deptTree = systemService.getDeptTree(0);
			session.put("DeptTree", deptTree);
		}
		AfBase af = (AfBase) afService.getAFById(afId);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("AFInfo", af);
		request.put("AFQualityProblem", afqp);
		request.put("pageTitle", "【" + af.getPresswork() + "】质量问题");
		request.put("DeptTree", deptTree);
		request.put("message", "删除成功！");
		return SUCCESS;
	}
}
