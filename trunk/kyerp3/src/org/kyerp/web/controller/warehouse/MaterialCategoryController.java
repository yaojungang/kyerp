package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class MaterialCategoryController {
	@Resource(name = "materialCategoryService")
	IMaterialCategoryService	materialCategoryService;

	@RequestMapping("/warehouse/MaterialCategory/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<MaterialCategory> pageView = new PageView<MaterialCategory>(
				12, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "asc");
		QueryResult<MaterialCategory> qureyResult = materialCategoryService
				.getScrollData(pageView.getFirstResult(), pageView
						.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/warehouse/MaterialCategory/add.html")
	public String add(ModelMap model) {
		return "redirect:input.html";

	}

	@RequestMapping("/warehouse/MaterialCategory/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/MaterialCategory/save.html")
	public String save(MaterialCategory brand, ModelMap model) {
		materialCategoryService.save(brand);
		return "redirect:index.html";

	}

	@RequestMapping("/warehouse/MaterialCategory/del.html")
	public String del(Long[] ids) {
		materialCategoryService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}
}
