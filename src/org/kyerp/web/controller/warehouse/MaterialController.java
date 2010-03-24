package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Material;
import org.kyerp.service.warehouse.IBrandService;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IUnitService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.kyerp.utils.StringUtil;
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
	public String list(String searchKey, Long mCategoryId, Integer start, Integer limit, Model model, HttpServletRequest request) {
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
		if(null != searchKey && searchKey.trim().length() > 0) {
			// searchKey = all 表示不过滤
			if(!"all".equals(searchKey.trim())) {
				if(params.size() > 0) {
					jpql.append(" and ");
				}
				jpql.append(" o.name like ?").append((params.size() + 1));
				params.add("%" + searchKey.trim() + "%");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Material> queryResult = materialService.getScrollData(start, limit, jpql.toString(), params.toArray(), orderby);
		// logger.info("jpql:" + jpql.toString());
		// logger.info("param:" + params.toString());
		List<MaterialExtGridRow> rows = new ArrayList<MaterialExtGridRow>();
		for (Material m : queryResult.getResultlist()) {
			MaterialExtGridRow rr = new MaterialExtGridRow();
			rr.setId(m.getId());
			rr.setName(m.getName());
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
			if(null != m.getMaterialBatchs() && m.getMaterialBatchs().size() > 0) {
				Long[] batchIds = new Long[m.getMaterialBatchs().size()];
				String[] batchNames = new String[m.getMaterialBatchs().size()];
				for (int i = 0; i < m.getMaterialBatchs().size(); i++) {
					batchIds[i] = m.getMaterialBatchs().get(i).getId();
					batchNames[i] = m.getMaterialBatchs().get(i).getBatchNumber();
				}
				rr.setBatchIds(StringUtil.Array2String(batchIds));
				rr.setBatchNumbers(StringUtil.Array2String(batchNames));
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
		ExtGridList<MaterialExtGridRow> mGrid = new ExtGridList<MaterialExtGridRow>();
		mGrid.setStart(start);
		mGrid.setLimit(limit);
		mGrid.setTotalProperty(queryResult.getTotalrecord());
		mGrid.setRows(rows);
		JSONObject jsonObject = JSONObject.fromObject(mGrid);

		String text = "";

		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		if(null != mCategoryId) {
			model.addAttribute("mCategoryId", "mCategoryId");
		}
		return "share/jsonTextView";
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
		material.setName(materialRow.getName());
		material.setSerialNumber(materialRow.getSerialNumber());
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
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = material.getId() > 0 ? material.getId() : materialService.findLast().getId();
		jsonObject.put("id", id);
		String text = "";
		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Material/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		materialService.delete((Serializable[]) ids);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		String text = "";
		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}
}
