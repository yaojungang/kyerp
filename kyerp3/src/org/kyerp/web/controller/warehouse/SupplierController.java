package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Supplier;
import org.kyerp.service.warehouse.IBrandService;
import org.kyerp.service.warehouse.ISupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class SupplierController {
	@Resource(name = "brandService")
	IBrandService		brandService;
	@Resource(name = "supplierService")
	ISupplierService	supplierService;

	@RequestMapping("/warehouse/Supplier/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<Supplier> pageView = new PageView<Supplier>(12, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("nameSpell", "asc");
		orderby.put("createTime", "desc");
		QueryResult<Supplier> qureyResult = supplierService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/warehouse/Supplier/add.html")
	public String add(ModelMap model) {
		return "redirect:input.html";

	}

	@RequestMapping("/warehouse/Supplier/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/Supplier/save.html")
	public String save(Supplier supplier, ModelMap model) {
		supplierService.save(supplier);
		return "redirect:index.html";

	}

	@RequestMapping("/warehouse/Supplier/del.html")
	public String del(Long[] ids) {
		supplierService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}
}
