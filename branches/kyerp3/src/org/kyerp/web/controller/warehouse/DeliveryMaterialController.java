package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.DeliveryMaterial;
import org.kyerp.service.warehouse.IDeliveryMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class DeliveryMaterialController {
	@Resource(name = "deliveryMaterialService")
	IDeliveryMaterialService	deliveryMaterialService;

	@RequestMapping("/warehouse/DeliveryMaterial/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<DeliveryMaterial> pageView = new PageView<DeliveryMaterial>(
				12, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		QueryResult<DeliveryMaterial> qureyResult = deliveryMaterialService
				.getScrollData(pageView.getFirstResult(), pageView
						.getMaxresult(), orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/warehouse/DeliveryMaterial/add.html")
	public String add(ModelMap model) {
		return "foward:input.html";

	}

	@RequestMapping("/warehouse/DeliveryMaterial/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/DeliveryMaterial/save.html")
	public String save(DeliveryMaterial deliveryMaterial, ModelMap model) {
		deliveryMaterialService.save(deliveryMaterial);
		return "redirect:index.html";

	}

	@RequestMapping("/warehouse/DeliveryMaterial/del.html")
	public String del(Long[] ids) {
		deliveryMaterialService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}
}
