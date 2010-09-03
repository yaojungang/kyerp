package org.kyerp.web.controller.warehouse.print;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.print.PaperOfMaterial;
import org.kyerp.service.warehouse.IBrandService;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.kyerp.service.warehouse.IPaperOfMaterialService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IUnitService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.kyerp.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
@SessionAttributes("paper")
@RequestMapping("/warehouse/print/PaperOfMaterial/")
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

	@RequestMapping("jsonList.html")
	public String list(String query, Long materialCategoryId, Integer start, Integer limit, Model model, HttpServletRequest request) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		jpql.append(" 1=?").append((params.size() + 1));
		params.add(1);
		// 类型ID
		if(null != materialCategoryId && materialCategoryId > 0) {
			if(params.size() > 0) {
				jpql.append(" and ");
			}
			jpql.append(" o.materialCategory.id=?").append((params.size() + 1));
			params.add(materialCategoryId);
		}
		// 名称关键字
		if(StringUtils.hasText(query)) {
			jpql.append(" and (o.name like ?").append(params.size() + 1);
			params.add("%" + query.trim() + "%");
			jpql.append(" or o.serialNumber like ?").append(params.size() + 1).append(")");
			params.add("%" + query.trim() + "%");

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
			rr.setMaterialName(m.getMaterialName());
			rr.setSpecification(m.getSpecification());
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
			/** 纸张名称 */
			rr.setPaperName(m.getPaperName());
			/** 纸长(mm) */
			rr.setPaperHeight(m.getPaperHeight());
			/** 纸宽(mm) */
			rr.setPaperWidth(m.getPaperWidth());
			/** 纸张克重 */
			rr.setPaperWeight(m.getPaperWeight());
			/** 纸张吨价 */
			rr.setTonnePrice(m.getTonnePrice());
			/** 每平米价格 */
			rr.setSquareMetrePrice(m.getSquareMetrePrice());
			/** 每张价格 */
			rr.setPricePrePage(m.getPricePrePage());
			rows.add(rr);
		}
		model.addAttribute("start", limit);
		model.addAttribute("limit", limit);
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("jsonSave.html")
	public String save(PaperOfMaterialExtGridRow materialRow, ModelMap model) throws Exception {
		PaperOfMaterial material = new PaperOfMaterial();
		if(null != materialRow.getId() && materialRow.getId() > 0) {
			material = paperOfMaterialService.find(materialRow.getId());
		}
		/** 纸张名称 */
		if(null != materialRow.getPaperName()) {
			material.setPaperName(materialRow.getPaperName());
		}
		/** 纸长(mm) */
		material.setPaperHeight(materialRow.getPaperHeight());
		/** 纸宽(mm) */
		material.setPaperWidth(materialRow.getPaperWidth());
		/** 纸张克重 */
		material.setPaperWeight(materialRow.getPaperWeight());
		/** 纸张吨价 */
		material.setTonnePrice(materialRow.getTonnePrice());
		/** 纸张价格（令价） */
		material.setPrice(PrintUtils.getPaperLinPriceByTonePrice(materialRow.getTonnePrice(), materialRow.getPaperWeight(), materialRow.getPaperHeight(), materialRow.getPaperWidth()));
		/** 每平米价格 */
		/** 每张价格 */
		material.setPricePrePage(PrintUtils.getPaperPagePriceByTonePrice(materialRow.getTonnePrice(), materialRow.getPaperWeight(), materialRow.getPaperHeight(), materialRow.getPaperWidth()));
		if(null != materialRow.getMaterialCategoryId()) {
			material.setMaterialCategory(materialCategoryService.find(materialRow.getMaterialCategoryId()));
		}
		if(null != materialRow.getBrandId()) {
			material.setBrand(brandService.find(materialRow.getBrandId()));
		}
		if(null != materialRow.getSupplierId()) {
			material.setSupplier(supplierService.find(materialRow.getSupplierId()));
		}
		if(null != materialRow.getUnitId()) {
			material.setUnit(unitService.find(materialRow.getUnitId()));
		}
		if(null != materialRow.getId() && materialRow.getId() > 0) {
			paperOfMaterialService.update(material);
		} else {
			paperOfMaterialService.save(material);
		}
		long id = material.getId() > 0 ? material.getId() : paperOfMaterialService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		paperOfMaterialService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
