package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.BuyerOfEnteringMaterial;
import org.kyerp.domain.warehouse.MaterialBatch;
import org.kyerp.domain.warehouse.Supplier;
import org.kyerp.domain.warehouse.Warehouse;
import org.kyerp.service.warehouse.IBuyerOfEnteringMaterialService;
import org.kyerp.service.warehouse.IMaterialBatchService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
@SessionAttributes("buyerOfEnteringMaterial")
public class BuyerOfEnteringMaterialController {
	@Resource(name = "buyerOfEnteringMaterialService")
	IBuyerOfEnteringMaterialService	buyerOfEnteringMaterialService;
	@Resource(name = "supplierService")
	ISupplierService				supplierService;
	@Resource(name = "warehouseService")
	IWarehouseService				warehouseService;
	@Resource(name = "materialService")
	IMaterialService				materialService;
	@Resource(name = "materialBatchService")
	IMaterialBatchService			materialBatchService;

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<BuyerOfEnteringMaterial> pageView = new PageView<BuyerOfEnteringMaterial>(
				12, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		QueryResult<BuyerOfEnteringMaterial> qureyResult = buyerOfEnteringMaterialService
				.getScrollData(pageView.getFirstResult(), pageView
						.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/addUI.html")
	public void addUI(ModelMap model, HttpServletRequest request) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("nameSpell", "asc");
		orderby.put("createTime", "desc");
		List<Supplier> suppliers = supplierService.getScrollData(orderby)
				.getResultlist();
		List<Warehouse> warehouses = warehouseService.getScrollData()
				.getResultlist();
		BuyerOfEnteringMaterial buyerOfEnteringMaterial = (BuyerOfEnteringMaterial) WebUtils
				.getSessionAttribute(request, "buyerOfEnteringMaterial");
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("warehouses", warehouses);
		if (buyerOfEnteringMaterial != null) {
			model.addAttribute("buyerOfEnteringMaterial",
					buyerOfEnteringMaterial);
		}
	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/addToEnteringMaterial.html")
	public String addToEnteringMaterial(Long id, ModelMap model,
			HttpServletRequest request) {
		BuyerOfEnteringMaterial buyerOfEnteringMaterial = (BuyerOfEnteringMaterial) WebUtils
				.getSessionAttribute(request, "buyerOfEnteringMaterial");
		if (buyerOfEnteringMaterial != null) {
			MaterialBatch mb = new MaterialBatch();
			mb.setMaterial(materialService.find(id));
			buyerOfEnteringMaterial.getMaterialBatchs().add(mb);
			model.addAttribute("buyerOfEnteringMaterial",
					buyerOfEnteringMaterial);
		} else {
			buyerOfEnteringMaterial = new BuyerOfEnteringMaterial();
			buyerOfEnteringMaterial.setWarehouse(new Warehouse());
			List<MaterialBatch> materialBatchs = new ArrayList<MaterialBatch>();
			MaterialBatch mb = new MaterialBatch();
			mb.setMaterial(materialService.find(id));
			materialBatchs.add(mb);
			buyerOfEnteringMaterial.setMaterialBatchs(materialBatchs);
		}

		model.addAttribute("buyerOfEnteringMaterial", buyerOfEnteringMaterial);
		return "forward:addUI.html";
	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/add.html")
	public String add(BuyerOfEnteringMaterial buyerOfEnteringMaterial,
			ModelMap model, HttpServletRequest request, SessionStatus status) {

		buyerOfEnteringMaterial.setWarehouse(warehouseService
				.find(buyerOfEnteringMaterial.getWarehouse().getId()));
		buyerOfEnteringMaterialService.save(buyerOfEnteringMaterial);

		for (MaterialBatch mb : buyerOfEnteringMaterial.getMaterialBatchs()) {
			mb.setMaterial(materialService.find(mb.getMaterial().getId()));
			mb.setSupplier(supplierService.find(mb.getSupplier().getId()));
			mb.setEnteringMaterial(buyerOfEnteringMaterial);
			materialBatchService.save(mb);
		}
		status.setComplete();

		return "forward:index.html";
	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/input.html")
	public void input(ModelMap model) {
	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/save.html")
	public String save(BuyerOfEnteringMaterial buyerOfEnteringMaterial,
			ModelMap model) {
		buyerOfEnteringMaterialService.save(buyerOfEnteringMaterial);
		return "forward:index.html";

	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/del.html")
	public String del(Long[] ids) {
		buyerOfEnteringMaterialService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}
}
