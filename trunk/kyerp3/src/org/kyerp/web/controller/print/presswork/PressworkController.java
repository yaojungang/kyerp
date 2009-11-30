/**
 * 
 */
package org.kyerp.web.controller.print.presswork;

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

	@RequestMapping("/Print/Presswork.html")
	public String test(ModelMap model) {

		Presswork presswok = new Presswork();
		presswok.setName("印品名称");
		presswok.setAmount(1000);
		pressworkService.save(presswok);
		Brand brand = new Brand();
		brand.setName("晨鸣");
		brandService.save(brand);

		PaperOfMaterial paper = new PaperOfMaterial();
		paper.setName("纸的名字");
		paper.setBrand(brand);
		paper.setPaperHeight(1000);
		paperService.save(paper);
		System.out.println(paper.getBrand().getName());

		model.addAttribute("presswork", presswok);
		model.addAttribute("paper", paper);

		return "Presswork";
	}

}
