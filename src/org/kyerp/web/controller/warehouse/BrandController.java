package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Brand;
import org.kyerp.service.warehouse.IBrandService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class BrandController {
	@Resource(name = "brandService")
	IBrandService	brandService;

	@RequestMapping("/warehouse/Brand/jsonList.html")
	public String list(Model model, Integer start, Integer limit) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Brand> queryResult = brandService.getScrollData(start,
				limit, orderby);
		List<BrandExtGridRow> rows = new ArrayList<BrandExtGridRow>();
		for (Brand b : queryResult.getResultlist()) {
			BrandExtGridRow nb = new BrandExtGridRow();
			nb.setId(b.getId());
			nb.setName(b.getName());
			nb.setNameSpell(b.getNameSpell());
			nb.setVisable(b.getVisible().toString());
			rows.add(nb);
		}
		ExtGridList<BrandExtGridRow> mGrid = new ExtGridList<BrandExtGridRow>();
		mGrid.setStart(start);
		mGrid.setLimit(limit);
		mGrid.setTotalProperty(queryResult.getTotalrecord());
		mGrid.setRows(rows);
		JSONObject jsonObject = JSONObject.fromObject(mGrid);

		String text = "";

		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Brand/jsonSave.html")
	public String save(BrandExtGridRow brandRow, ModelMap model) {
		Brand brand = new Brand();
		if (null != brandRow.getId() && brandRow.getId() > 0) {
			brand = brandService.find(brandRow.getId());
		}
		brand.setName(brandRow.getName());
		brand.setNameSpell(brandRow.getNameSpell());
		if (null != brandRow.getId() && brandRow.getId() > 0) {
			brandService.update(brand);
		} else {
			brandService.save(brand);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = brand.getId() > 0 ? brand.getId() : brandService.findLast()
				.getId();
		jsonObject.put("id", id);
		String text = "";
		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Brand/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		brandService.delete((Serializable[]) ids);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		String text = "";
		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}
}
