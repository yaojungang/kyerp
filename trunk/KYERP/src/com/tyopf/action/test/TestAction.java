package com.tyopf.action.test;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tyopf.service.IHRService;
import com.tyopf.service.ISystemService;
import com.tyopf.vo.Employee;

@SuppressWarnings("serial")
public class TestAction extends ActionSupport {
	private ISystemService systemService;
	private IHRService hrService;
	private int upDeptId = 0;
	

	public IHRService getHrService() {
		return hrService;
	}


	public void setHrService(IHRService hrService) {
		this.hrService = hrService;
	}


	public ISystemService getSystemService() {
		return systemService;
	}


	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}


	public int getUpDeptId() {
		return upDeptId;
	}


	public void setUpDeptId(int upDeptId) {
		this.upDeptId = upDeptId;
	}


	public String getEmpNo() throws Exception{
		System.out.println(hrService.getEmpNo(22));
		List<Employee> allEmployee = hrService.getEmployeeListByWorkStatus(0, 1, 10000);
		for(Employee e:allEmployee){
			e.setEmpNo(hrService.getEmpNo(e.getId()));
			hrService.saveEmployee(e);
		}
		return SUCCESS;
	}
}