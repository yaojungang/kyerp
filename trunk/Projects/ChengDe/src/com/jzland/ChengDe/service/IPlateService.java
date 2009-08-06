package com.jzland.ChengDe.service;

import java.util.List;

import com.jzland.ChengDe.vo.Plate;

public interface IPlateService {
	public Plate getPlateById(int id);
	public List<Plate> getAllPlate();
	public List<Plate> getPlates(int currentPage,int pageSize);
	public List<Plate> getExpPlates(int currentPage,int pageSize);
	public void addPlate(Plate plate);
	public void editPlate(Plate plate);
	public void delPlate(int id);
	public int getCountofAllPlate();
	public int getCountofExpPlate();
	public List<Plate> searchPlate(String searchOption,String searchValue);
}
