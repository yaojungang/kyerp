package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Brand;
import org.kyerp.domain.warehouse.Material;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.domain.warehouse.Supplier;
import org.kyerp.domain.warehouse.Warehouse;
import org.kyerp.service.warehouse.IBrandService;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.IPaperOfMaterialService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
@SessionAttributes("material")
public class MaterialController {
	@Resource(name = "materialService")
	IMaterialService			materialService;
	@Resource(name = "materialCategoryService")
	IMaterialCategoryService	materialCategoryService;
	@Resource(name = "paperOfMaterialService")
	IPaperOfMaterialService		paperOfMaterialService;
	@Resource(name = "brandService")
	IBrandService				brandService;
	@Resource(name = "supplierService")
	ISupplierService			supplierService;
	@Resource(name = "warehouseService")
	IWarehouseService			warehouseService;

	@RequestMapping("/warehouse/Material/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<Material> pageView = new PageView<Material>(12, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		QueryResult<Material> qureyResult = materialService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/warehouse/Material/addUI.html")
	public void addUI(ModelMap model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("nameSpell", "asc");
		orderby.put("createTime", "desc");
		List<Supplier> suppliers = supplierService.getScrollData(orderby)
				.getResultlist();
		List<Warehouse> warehouses = warehouseService.getScrollData()
				.getResultlist();
		List<Brand> brands = brandService.getScrollData().getResultlist();
		model
				.addAttribute("materialCategories", this
						.getMaterialCategoryList());
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("warehouses", warehouses);
		model.addAttribute("brands", brands);

		Material material = new Material();
		MaterialCategory materialCategory = new MaterialCategory();
		material.setMaterialCategory(materialCategory);
		Brand brand = new Brand();
		material.setBrand(brand);
		Supplier supplier = new Supplier();
		material.setSupplier(supplier);
		Warehouse warehouse = new Warehouse();
		material.setWarehouse(warehouse);
		model.addAttribute("material", material);
	}

	@RequestMapping("/warehouse/Material/add.html")
	public String add(Material material, ModelMap model) {
		material.setMaterialCategory(materialCategoryService.find(material
				.getMaterialCategory().getId()));
		material.setBrand(brandService.find(material.getBrand().getId()));
		material.setWarehouse(warehouseService.find(material.getWarehouse()
				.getId()));
		material.setSupplier(supplierService.find(material.getSupplier()
				.getId()));
		materialService.save(material);
		return "redirect:index.html";

	}

	@ModelAttribute("materialCategories")
	public List<MaterialCategory> getMaterialCategoryList() {
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "asc");
		orderby.put("createTime", "asc");
		List<MaterialCategory> materialCategories = materialCategoryService
				.getScrollData(jpql.toString(), params.toArray(), orderby)
				.getResultlist();
		return materialCategories;
	}

	@RequestMapping("/warehouse/Material/save.html")
	public String save(Material material, Long cateId, ModelMap model) {
		material.setMaterialCategory(materialCategoryService.find(cateId));
		materialService.save(material);
		return "redirect:index.html";

	}

	@RequestMapping("/warehouse/Material/del.html")
	public String del(Long[] ids) {
		materialService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}
}
