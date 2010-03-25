package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.PaperOfMaterial;
import org.kyerp.service.warehouse.IBrandService;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.kyerp.service.warehouse.IPaperOfMaterialService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IUnitService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
@SessionAttributes("paper")
public class PapaerOfMaterialController{
	@Autowired
	IMaterialCategoryService	materialCategoryService;
	@Autowired
	IPaperOfMaterialService		paperOfMaterialService;
	@Autowired
	IBrandService				brandService;
	@Autowired
	ISupplierService			supplierService;
	@Autowired
	IWarehouseService			warehouseService;
	@Autowired
	IUnitService				unitService;

	@RequestMapping("/warehouse/PaperOfMaterial/jsonList.html")
	public String list(String query, Long mCategoryId, Integer start, Integer limit, Model model, HttpServletRequest request) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		jpql.append(" 1=?").append((params.size() + 1));
		params.add(1);
		// 类型ID
		if(null != mCategoryId && mCategoryId > 0) {
			if(params.size() > 0) {
				jpql.append(" and ");
			}
			jpql.append(" o.materialCategory.id=?").append((params.size() + 1));
			params.add(mCategoryId);
		}
		// 名称关键字
		if(null != query && query.trim().length() > 0) {
			// searchKey = all 表示不过滤
			if(!"all".equals(query.trim())) {
				if(params.size() > 0) {
					jpql.append(" and ");
				}
				jpql.append(" o.name like ?").append((params.size() + 1));
				params.add("%" + query.trim() + "%");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<PaperOfMaterial> queryResult = paperOfMaterialService.getScrollData(start, limit, jpql.toString(), params.toArray(), orderby);
		// logger.info("jpql:" + jpql.toString());
		// logger.info("param:" + params.toString());
		List<PaperOfMaterialExtGridRow> rows = new ArrayList<PaperOfMaterialExtGridRow>();
		for (PaperOfMaterial m : queryResult.getResultlist()) {
			PaperOfMaterialExtGridRow rr = new PaperOfMaterialExtGridRow();
			rr.setId(m.getId());
			rr.setName(m.getName());
			rr.setAmount(m.getAmount());
			rr.setSerialNumber(m.getSerialNumber());
			if(null != m.getMaterialCategory()) {
				rr.setMaterialCategoryId(m.getMaterialCategory().getId());
				rr.setMaterialCategoryName(m.getMaterialCategory().getName());
			}
			if(null != m.getBrand()) {
				rr.setBrandId(m.getBrand().getId());
				rr.setBrandName(m.getBrand().getName());
			}
			if(null != m.getUnit()) {
				rr.setUnitId(m.getUnit().getId());
				rr.setUnitName(m.getUnit().getName());
			}
			rr.setPrice(m.getPrice());
			if(null != m.getSupplier()) {
				rr.setSupplierId(m.getSupplier().getId());
				rr.setSupplierName(m.getSupplier().getName());
			}
			if(null != m.getWarehouse()) {
				rr.setWarehouseId(m.getWarehouse().getId());
				rr.setWarehouseName(m.getWarehouse().getName());
			}
			/** 纸张名称 */
			rr.setPaperName(m.getPaperName());
			/** 纸张规格：正度、大度 */
			rr.setPaperType(m.getPaperType());
			/** 纸长(mm) */
			rr.setPaperSize(m.getPaperSize());
			/** 纸宽(mm) */
			rr.setPaperWidth(m.getPaperWidth());
			/** 纸张大小：全开、对开、四开 */
			rr.setPaperSize(m.getPaperSize());
			/** 纸张克重 */
			rr.setPaperWeight(m.getPaperWeight());
			/** 纸张吨价 */
			rr.setTonnePrice(m.getTonnePrice());
			/** 每平米价格 */
			rr.setSquareMetrePrice(m.getSquareMetrePrice());

			rows.add(rr);
		}
		model.addAttribute("start", limit);
		model.addAttribute("limit", limit);
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/PaperOfMaterial/jsonSave.html")
	public String save(PaperOfMaterialExtGridRow materialRow, ModelMap model) {
		PaperOfMaterial material = new PaperOfMaterial();
		/** 纸张名称 */
		if(null != materialRow.getPaperName()) {
			material.setPaperName(materialRow.getPaperName());
		}
		/** 纸张规格：正度、大度 */
		if(null != materialRow.getPaperType()) {
			material.setPaperType(materialRow.getPaperType());
		}
		/** 纸长(mm) */
		material.setPaperHeight(materialRow.getPaperHeight());
		/** 纸宽(mm) */
		material.setPaperWidth(materialRow.getPaperWidth());
		/** 纸张大小：全开、对开、四开 */
		material.setPaperSize(materialRow.getPaperSize());
		/** 纸张克重 */
		material.setPaperWeight(materialRow.getPaperWeight());
		/** 纸张吨价 */
		material.setTonnePrice(materialRow.getTonnePrice());
		/** 每平米价格 */
		material.setSquareMetrePrice(materialRow.getSquareMetrePrice());
		if(null != materialRow.getId() && materialRow.getId() > 0) {
			material = paperOfMaterialService.find(materialRow.getId());
		}
		if(null != materialRow.getMaterialCategoryId()) {
			material.setMaterialCategory(materialCategoryService.find(materialRow.getMaterialCategoryId()));
		}
		if(null != materialRow.getBrandId()) {
			material.setBrand(brandService.find(materialRow.getBrandId()));
		}
		if(null != materialRow.getSupplierId()) {
			material.setSupplier(supplierService.find(materialRow.getSupplierId()));
		}
		if(null != materialRow.getWarehouseId()) {
			material.setWarehouse(warehouseService.find(materialRow.getWarehouseId()));
		}
		material.setName(materialRow.getName());
		material.setSerialNumber(materialRow.getSerialNumber());
		material.setSpecification(materialRow.getSpecification());
		material.setPrice(materialRow.getPrice());
		if(null != materialRow.getUnitId()) {
			material.setUnit(unitService.find(materialRow.getUnitId()));
		}
		if(null != materialRow.getId() && materialRow.getId() > 0) {
			paperOfMaterialService.update(material);
		} else {
			paperOfMaterialService.savePaper(material);
		}
		long id = material.getId() > 0 ? material.getId() : paperOfMaterialService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/PaperOfMaterial/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		paperOfMaterialService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
