package com.jzland.ChengDe.util;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

import com.jzland.ChengDe.service.IHRService;
import com.jzland.ChengDe.vo.Employee;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class photoUpload extends ActionSupport implements
		ServletContextAware {

	private File uploadPhoto;// 实际上传文件
	
	private int id;
	protected IHRService hrService;
	private String uploadContentType; // 文件的内容类型
	private String uploadFileName; // 上传文件名
	private ServletContext context;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		try {
			
			String targetDirectory = context.getRealPath("../uploadData/photos");
			String targetFileName = "employeePhoto"+id+".jpg";
			File target = new File(targetDirectory, targetFileName);
			FileUtils.copyFile(uploadPhoto, target);			
			
			setUploadFileName(target.getPath());//保存文件的存放路径
			
			Employee employee = hrService.getEmployeeById(id);
			employee.setPhoto("photos/"+targetFileName);
			hrService.addPhotoToEmployee_save(employee);
			Map request = (Map) ActionContext.getContext().get("request");
			Employee employeeGet = hrService.getEmployeeById(employee.getId());
			
			request.put("Employee", employeeGet);
			request.put("message", "照片上传成功！");
			
		} catch (Exception e) {

			addActionError(e.getMessage());

			return INPUT;
		}

		return SUCCESS;

	}

	public File getUploadPhoto() {
		return uploadPhoto;
	}



	public void setUploadPhoto(File uploadPhoto) {
		this.uploadPhoto = uploadPhoto;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public IHRService getHrService() {
		return hrService;
	}



	public void setHrService(IHRService hrService) {
		this.hrService = hrService;
	}



	public ServletContext getContext() {
		return context;
	}



	public void setContext(ServletContext context) {
		this.context = context;
	}



	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

}
