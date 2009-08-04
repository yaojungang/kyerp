package com.jzland.ChengDe.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.jzland.ChengDe.service.IPlateService;
import com.jzland.ChengDe.util.Pager;
import com.jzland.ChengDe.vo.Employee;
import com.jzland.ChengDe.vo.Plate;
import com.jzland.ChengDe.vo.PlateUseLog;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PlateAction extends ActionSupport {
	public int currentPage = 1;
	public int pageSize = 100;
	private List<Plate> plateList;
	private IPlateService plateService;
	private Plate plate;
	private int id;
	private String searchOption;
	private String searchValue;	
	
	public int getId() {
		return id;
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}

	public IPlateService getPlateService() {
		return plateService;
	}

	public void setPlateService(IPlateService plateService) {
		this.plateService = plateService;
	}

	public List<Plate> getPlateList() {
		return plateList;
	}

	public void setPlateList(List<Plate> plateList) {
		this.plateList = plateList;
	}

	public String PlateAdmin() {
		plateList = plateService.getPlates(currentPage, pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		Pager pager = new Pager(currentPage, plateService.getCountofAllPlate());
		pager.setPageSize(pageSize);
		request.put("PlateList", plateList);
		request.put("Pager", pager);		
		return SUCCESS;
	}
	public String getPlateById() {
		Plate plate = plateService.getPlateById(id);		
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Plate", plate);
		request.put("pageTitle", "印版信息");
		return SUCCESS;
	}
	public String getExpPlateList() {
		plateList = plateService.getExpPlates(currentPage, pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		Pager pager = new Pager(currentPage, plateService.getCountofAllPlate());
		pager.setPageSize(pageSize);
		request.put("PlateList", plateList);
		request.put("Pager", pager);		
		return SUCCESS;
	}
	public String plateList() {
		plateList = plateService.getAllPlate();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("PlateList", plateList);
		
		return SUCCESS;
	}
	public String searchPlate() {
		plateList = plateService.searchPlate(searchOption, searchValue);
		Pager pager = new Pager(currentPage, plateList.size());
		pager.setPageSize(plateList.size());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("PlateList", plateList);
		request.put("Pager", pager);
		request.put("pageTitle", "搜索："+searchOption+"="+searchValue);
		return SUCCESS;
	}
	public String addPlate() {
		Timestamp t = new Timestamp(new GregorianCalendar(TimeZone.getTimeZone("GMT")).getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(new Date());

		Date todayD = new Date();
		int pYear = Integer.parseInt(sDate.substring(0, 4)) +1;
		int mm = Integer.parseInt(sDate.substring(5, 7));
	    int dd = Integer.parseInt(sDate.substring(8, 10));

		Date NextYearD = java.sql.Date.valueOf(pYear+"-"+mm+"-"+dd);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Today", todayD);
		request.put("NextYearD", NextYearD);
		request.put("pageTitle", "添加印版");
		return SUCCESS;
	}
	public String addPlate_save() {
		Map session = ActionContext.getContext().getSession();
		Employee employee = (Employee) session.get("employee");
		plate.setStartMan(employee.getRealname());
		plate.setStartTime(new Date());
		plate.setLastChangeMan(employee.getRealname());
		plate.setLastChangeTime(new Date());
		plate.setViewTimes(1);
		PlateUseLog firstUseLog=new PlateUseLog();
		firstUseLog.setUseTime(plate.getLastUseDate());
		firstUseLog.setInputMan(employee.getRealname());		
		firstUseLog.setPlate(plate);
		plate.getUseLogs().add(firstUseLog);
		plateService.addPlate(plate);
		plateList = plateService.getPlates(currentPage, pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		Pager pager = new Pager(currentPage, plateService.getCountofAllPlate());
		pager.setPageSize(pageSize);
		request.put("PlateList", plateList);
		request.put("Pager", pager);
		request.put("message", "印版添加成功！");
		request.put("pageTitle", "印版列表");
		return SUCCESS;
	}
	public String delPlate() {
		plateService.delPlate(id);		
		plateList = plateService.getPlates(currentPage, pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		Pager pager = new Pager(currentPage, plateService.getCountofAllPlate());
		pager.setPageSize(pageSize);
		request.put("PlateList", plateList);
		request.put("Pager", pager);
		request.put("message", "印版删除成功！");
		request.put("pageTitle", "印版列表");
		return SUCCESS;
	}
	public String editPlate() {
		Plate plate = plateService.getPlateById(id);		
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("Plate", plate);
		request.put("pageTitle", "修改印版信息");
		return SUCCESS;
	}
	public String editPlate_save() {
		plateService.editPlate(plate);		
		plateList = plateService.getPlates(currentPage, pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		Pager pager = new Pager(currentPage, plateService.getCountofAllPlate());
		pager.setPageSize(pageSize);
		request.put("PlateList", plateList);
		request.put("Pager", pager);
		request.put("message", "印版修改成功！");
		request.put("pageTitle", "印版列表");
		return SUCCESS;
	}
}
