package com.jzland.ChengDe.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jzland.ChengDe.dao.IPlate;
import com.jzland.ChengDe.service.IPlateService;
import com.jzland.ChengDe.vo.Employee;
import com.jzland.ChengDe.vo.Plate;
import com.opensymphony.xwork2.ActionContext;

public class PlateService implements IPlateService {
	private IPlate plateDAO;

	public IPlate getPlateDAO() {
		return plateDAO;
	}

	public void setPlateDAO(IPlate plateDAO) {
		this.plateDAO = plateDAO;
	}

	@Override
	public List<Plate> getAllPlate() {
		System.out.println("Service Get All Plate");
		return plateDAO.getAllPlate();
	}

	@Override
	public Plate getPlateById(int id) {
		return plateDAO.getPlateById(id);
	}

	@Override
	public void addPlate(Plate plate) {
		plateDAO.addPlate(plate);
	}

	@Override
	public List<Plate> getPlates(int currentPage, int pageSize) {
		return plateDAO.getPlates(currentPage, pageSize);
	}

	@Override
	public int getCountofAllPlate() {
		return plateDAO.getCountofAllPlate();
	}

	@Override
	public void delPlate(int id) {
		plateDAO.delPlate(id);
	}

	@Override
	public void editPlate(Plate plate) {
		Plate plate0 = plateDAO.getPlateById(plate.getId());
		plate.setUseLogs(plate0.getUseLogs());
		Map session = ActionContext.getContext().getSession();
		Employee employee = (Employee) session.get("employee");
		plate.setStartMan(employee.getRealname());
		plate.setStartTime(new Date());
		plate.setLastChangeMan(employee.getRealname());
		plate.setLastChangeTime(new Date());
		plate.setViewTimes(plate0.getViewTimes());
		plateDAO.editPlate(plate);
	}

	@Override
	public int getCountofExpPlate() {
		return plateDAO.getCountofExpPlate();
	}

	@Override
	public List<Plate> getExpPlates(int currentPage, int pageSize) {
		return plateDAO.getExpPlates(currentPage, pageSize);
	}

	@Override
	public List<Plate> searchPlate(String searchOption, String searchValue) {
		return plateDAO.searchPlate(searchOption, searchValue);
	}
	
}
