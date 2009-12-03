/**
 * 
 */
package org.kyerp.web.controller.print;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.PaperOfMaterial;
import org.kyerp.service.print.IPressworkService;
import org.kyerp.service.warehouse.IBrandService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.IPaperOfMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-11-28上午12:15:46
 */
@Controller
public class PaperOfMaterialController {
	@Resource(name = "pressworkService")
	IPressworkService		pressworkService;
	@Resource(name = "paperOfMaterialService")
	IPaperOfMaterialService	paperOfMaterialService;
	@Resource(name = "brandService")
	IBrandService			brandService;
	@Resource(name = "materialService")
	IMaterialService		materialService;

	@RequestMapping("/Print/PaperOfMaterial/list.html")
	public void list(ModelMap model, Integer page) {
		page = page == null || page < 1 ? 1 : page;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");

		PageView<PaperOfMaterial> pageView = new PageView<PaperOfMaterial>(20,
				page);
		QueryResult<PaperOfMaterial> materialList = paperOfMaterialService
				.getScrollData(pageView.getFirstResult(), pageView
						.getMaxresult(), orderby);
		pageView.setQueryResult(materialList);

		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/Print/PaperOfMaterial/del.html")
	public String del(Long[] ids) {
		paperOfMaterialService.delete(ids);
		return "redirect:/Print/PaperOfMaterial/list.html";
	}

}
