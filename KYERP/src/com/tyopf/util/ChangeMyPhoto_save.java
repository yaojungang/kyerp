package com.tyopf.util;

import java.io.File;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IHRService;
import com.tyopf.vo.Employee;
import com.tyopf.vo.EmployeeFamily;
import com.tyopf.vo.EmployeeResume;

@SuppressWarnings("serial")
public class ChangeMyPhoto_save extends ActionSupport implements
		ServletContextAware {

	private File uploadPhoto;// 实际上传文件
	
	protected IHRService hrService;
	private String uploadContentType; // 文件的内容类型
	private String uploadFileName; // 上传文件名
	private ServletContext context;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		try {
			Map session = ActionContext.getContext().getSession();
			Employee employee = (Employee) session.get("employee");
			int id =employee.getId();
			
			String targetDirectory = context.getRealPath("../uploadData/photos");
			String targetFileName = "employeePhoto"+id+".jpg";
			File target = new File(targetDirectory, targetFileName);
			FileUtils.copyFile(uploadPhoto, target);			
			
			setUploadFileName(target.getPath());//保存文件的存放路径
			
			employee.setPhoto("photos/"+targetFileName);
			hrService.addPhotoToEmployee_save(employee);
			Map request = (Map) ActionContext.getContext().get("request");
			Employee employeeGet = hrService.getEmployeeById(employee.getId());
			employeeGet.getResume().add(new EmployeeResume());
			employeeGet.getResume().add(new EmployeeResume());

			employeeGet.getFamily().add(new EmployeeFamily());
			employeeGet.getFamily().add(new EmployeeFamily());
			Timestamp t = new Timestamp(new GregorianCalendar(TimeZone
					.getTimeZone("GMT")).getTimeInMillis());
			
			request.put("Employee", employeeGet);
			request.put("t", t);
			
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
