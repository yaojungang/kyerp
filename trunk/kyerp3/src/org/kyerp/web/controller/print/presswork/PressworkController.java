/**
 * 
 */
package org.kyerp.web.controller.print.presswork;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.kyerp.domain.print.Presswork;
import org.kyerp.domain.warehouse.Brand;
import org.kyerp.domain.warehouse.PaperOfMaterial;
import org.kyerp.service.print.IPressworkService;
import org.kyerp.service.warehouse.IBrandService;
import org.kyerp.service.warehouse.IPaperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-11-28上午12:15:46
 */
@Controller
public class PressworkController {
	@Resource(name = "pressworkService")
	IPressworkService	pressworkService;
	@Resource(name = "paperService")
	IPaperService		paperService;
	@Resource(name = "brandService")
	IBrandService		brandService;

	@RequestMapping("/Print/Presswork/Presswork.html")
	public void test(ModelMap model) {

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
		paperService.save(paper);
		System.out.println(paper.getBrand().getName());

		model.addAttribute("presswork", presswork);
		model.addAttribute("paper", paper);
	}

}
