/**
 * 
 */
package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Warehouse;
import org.kyerp.service.warehouse.IWarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午04:25:46
 */
@Controller
public class WarehouseController {

	@Resource(name = "warehouseService")
	IWarehouseService	warehouseService;

	@RequestMapping("/warehouse/index.html")
	public void index() {
	}

	@RequestMapping("/warehouse/Warehouse/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<Warehouse> pageView = new PageView<Warehouse>(12, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "asc");
		orderby.put("createTime", "desc");
		QueryResult<Warehouse> qureyResult = warehouseService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/warehouse/Warehouse/add.html")
	public String add(ModelMap model) {
		return "redirect:input.html";

	}

	@RequestMapping("/warehouse/Warehouse/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/Warehouse/save.html")
	public String save(Warehouse warehouse, ModelMap model) {
		warehouseService.save(warehouse);
		return "redirect:index.html";

	}

	@RequestMapping("/warehouse/Warehouse/del.html")
	public String del(Long[] ids) {
		warehouseService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}

}
