package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Brand;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.domain.warehouse.PaperOfMaterial;
import org.kyerp.domain.warehouse.Supplier;
import org.kyerp.domain.warehouse.Warehouse;
import org.kyerp.service.warehouse.IBrandService;
import org.kyerp.service.warehouse.IMaterialCategoryService;
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
@SessionAttributes("paper")
public class PapaerOfMaterialController {
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

	@RequestMapping("/warehouse/PaperOfMaterial/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<PaperOfMaterial> pageView = new PageView<PaperOfMaterial>(12,
				page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		QueryResult<PaperOfMaterial> qureyResult = paperOfMaterialService
				.getScrollData(pageView.getFirstResult(), pageView
						.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@ModelAttribute("materialCategories")
	public List<MaterialCategory> getMaterialCategoryList() {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "asc");
		orderby.put("createTime", "asc");
		List<MaterialCategory> materialCategories = materialCategoryService
				.getScrollData(orderby).getResultlist();
		return materialCategories;
	}

	@RequestMapping("/warehouse/PaperOfMaterial/addUI.html")
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

		PaperOfMaterial paperOfMaterial = new PaperOfMaterial();
		MaterialCategory materialCategory = new MaterialCategory();
		paperOfMaterial.setMaterialCategory(materialCategory);
		Brand brand = new Brand();
		paperOfMaterial.setBrand(brand);
		Supplier supplier = new Supplier();
		paperOfMaterial.setSupplier(supplier);
		Warehouse warehouse = new Warehouse();
		paperOfMaterial.setWarehouse(warehouse);
		model.addAttribute("paper", paperOfMaterial);
	}

	@RequestMapping("/warehouse/PaperOfMaterial/add.html")
	public String add(PaperOfMaterial paper, ModelMap model,
			HttpServletRequest request) {
		paper.setMaterialCategory(materialCategoryService.find(paper
				.getMaterialCategory().getId()));
		paper.setBrand(brandService.find(paper.getBrand().getId()));
		paper.setWarehouse(warehouseService.find(paper.getWarehouse().getId()));
		paper.setSupplier(supplierService.find(paper.getSupplier().getId()));
		paperOfMaterialService.save(paper);
		return "redirect:index.html";
	}

	@RequestMapping("/warehouse/PaperOfMaterial/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/PaperOfMaterial/save.html")
	public String save(PaperOfMaterial paper, Long brandId, Long supplierId,
			ModelMap model) {
		paper.setBrand(brandService.find(brandId));
		paperOfMaterialService.save(paper);
		return "redirect:index.html";

	}

	@RequestMapping("/warehouse/PaperOfMaterial/del.html")
	public String del(Long[] ids) {
		paperOfMaterialService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}
}
