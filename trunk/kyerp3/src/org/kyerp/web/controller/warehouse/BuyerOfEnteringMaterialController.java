package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.BuyerOfEnteringMaterial;
import org.kyerp.domain.warehouse.Supplier;
import org.kyerp.service.warehouse.IBuyerOfEnteringMaterialService;
import org.kyerp.service.warehouse.ISupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class BuyerOfEnteringMaterialController {
	@Resource(name = "buyerOfEnteringMaterialService")
	IBuyerOfEnteringMaterialService	buyerOfEnteringMaterialService;
	@Resource(name = "supplierService")
	ISupplierService				supplierService;

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
	public void addUI(ModelMap model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("nameSpell", "asc");
		orderby.put("createTime", "desc");
		List<Supplier> suppliers = supplierService.getScrollData(orderby)
				.getResultlist();
		model.addAttribute("suppliers", suppliers);
	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/add.html")
	public String add(BuyerOfEnteringMaterial buyerOfEnteringMaterial,
			Long supplierId, ModelMap model) {
		buyerOfEnteringMaterial.setSupplier(supplierService.find(supplierId));
		buyerOfEnteringMaterialService.save(buyerOfEnteringMaterial);
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
