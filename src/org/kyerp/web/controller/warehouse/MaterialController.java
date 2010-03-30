package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Material;
import org.kyerp.service.warehouse.IBrandService;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IUnitService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.kyerp.web.controller.BaseController;
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
@SessionAttributes("mCategoryId")
public class MaterialController extends BaseController{
	@Resource(name = "materialService")
	IMaterialService			materialService;
	@Resource(name = "materialCategoryService")
	IMaterialCategoryService	materialCategoryService;
	@Resource(name = "brandService")
	IBrandService				brandService;
	@Resource(name = "supplierService")
	ISupplierService			supplierService;
	@Resource(name = "unitService")
	IUnitService				unitService;
	@Resource(name = "warehouseService")
	IWarehouseService			warehouseService;

	@RequestMapping("/warehouse/Material/jsonList.html")
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
		QueryResult<Material> queryResult = materialService.getScrollData(start, limit, jpql.toString(), params.toArray(), orderby);
		List<MaterialExtGridRow> rows = new ArrayList<MaterialExtGridRow>();
		for (Material m : queryResult.getResultlist()) {
			MaterialExtGridRow rr = new MaterialExtGridRow();
			rr.setId(m.getId());
			rr.setName(m.getName());
			rr.setMaterialName(m.getMaterialName());
			rr.setAmount(m.getAmount());
			rr.setSpecification(m.getSpecification());
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
			rows.add(rr);
		}
		model.addAttribute("start", limit);
		model.addAttribute("limit", limit);
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Material/jsonSave.html")
	public String save(MaterialExtGridRow materialRow, ModelMap model) {
		Material material = new Material();
		if(null != materialRow.getId() && materialRow.getId() > 0) {
			material = materialService.find(materialRow.getId());
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
		material.setMaterialName(materialRow.getMaterialName());
		material.setSpecification(materialRow.getSpecification());
		material.setPrice(materialRow.getPrice());
		if(null != materialRow.getUnitId()) {
			material.setUnit(unitService.find(materialRow.getUnitId()));
		}
		if(null != materialRow.getId() && materialRow.getId() > 0) {
			materialService.update(material);
		} else {
			materialService.saveMaterial(material);
		}
		long id = material.getId() > 0 ? material.getId() : materialService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Material/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		materialService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
