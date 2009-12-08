package org.kyerp.web.controller.warehouse;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Brand;
import org.kyerp.service.warehouse.IBrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class BrandController {
	@Resource(name = "brandService")
	IBrandService	brandService;

	@RequestMapping("/warehouse/Brand/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<Brand> pageView = new PageView<Brand>(12, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		QueryResult<Brand> qureyResult = brandService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/warehouse/Brand/add.html")
	public String add(ModelMap model) {
		return "redirect:input.html";

	}

	@RequestMapping("/warehouse/Brand/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/Brand/save.html")
	public String save(Brand brand, ModelMap model) {
		brandService.save(brand);
		return "redirect:index.html";

	}
}
