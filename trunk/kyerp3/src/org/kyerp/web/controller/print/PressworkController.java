/**
 * 
 */
package org.kyerp.web.controller.print;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.print.Presswork;
import org.kyerp.domain.warehouse.Brand;
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
public class PressworkController {
	@Resource(name = "pressworkService")
	IPressworkService		pressworkService;
	@Resource(name = "paperOfMaterialService")
	IPaperOfMaterialService	paperOfMaterialService;
	@Resource(name = "brandService")
	IBrandService			brandService;
	@Resource(name = "materialService")
	IMaterialService		materialService;

	@RequestMapping("/Print/Presswork/list.html")
	public void list(ModelMap model, Integer page) {
		page = page == null || page < 1 ? 1 : page;
		Presswork presswork = new Presswork();
		presswork.setName("印品名称");
		presswork.setAmount(1000);
		pressworkService.save(presswork);

		Brand brand = new Brand();
		brand.setName("晨鸣");
		brandService.save(brand);

		PaperOfMaterial paper = new PaperOfMaterial();
		paper.setName("纸的名字");
		paper.setBrand(brand);
		paper.setPaperHeight(1000);
		paper.setPrice(new BigDecimal("12.01"));
		paperOfMaterialService.save(paper);

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");

		PageView<PaperOfMaterial> pageView = new PageView<PaperOfMaterial>(20,
				page);
		QueryResult<PaperOfMaterial> materialList = paperOfMaterialService
				.getScrollData(pageView.getFirstResult(), pageView
						.getMaxresult());
		pageView.setQueryResult(materialList);

		model.addAttribute("presswork", presswork);
		model.addAttribute("paper", paper);
		model.addAttribute("pageView", pageView);
	}

	@RequestMapping("/Print/Presswork/del.html")
	public String del(Long[] ids) {
		paperOfMaterialService.delete(ids);
		return "redirect:/Print/Presswork/list.html";
	}

}
