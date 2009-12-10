package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.BuyerOfEnteringMaterial;
import org.kyerp.service.warehouse.IBuyerOfEnteringMaterialService;
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

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/add.html")
	public String add(ModelMap model) {
		return "foward:input.html";

	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/save.html")
	public String save(BuyerOfEnteringMaterial buyerOfEnteringMaterial,
			ModelMap model) {
		buyerOfEnteringMaterialService.save(buyerOfEnteringMaterial);
		return "redirect:index.html";

	}

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/del.html")
	public String del(Long[] ids) {
		buyerOfEnteringMaterialService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}
}
