package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Material;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.kyerp.service.warehouse.IMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class MaterialController {
	@Resource(name = "materialService")
	IMaterialService			materialService;
	@Resource(name = "materialCategoryService")
	IMaterialCategoryService	materialCategoryService;

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

	@RequestMapping("/warehouse/Material/add.html")
	public String add(ModelMap model) {
		model
				.addAttribute("materialCategories", this
						.getMaterialCategoryList());
		return "forward:input.html";

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

	@RequestMapping("/warehouse/Material/input.html")
	public void input(ModelMap model) {

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
