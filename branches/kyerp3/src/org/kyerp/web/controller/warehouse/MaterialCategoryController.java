package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.kyerp.domain.base.views.ExtGridList;
import org.kyerp.domain.base.views.ExtTreeNode;
import org.kyerp.domain.base.views.ExtTreeRecursion;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 部门管理模块定义
 * 
 * @author y109 2009-12-31上午09:52:00
 */
@Controller
public class MaterialCategoryController {
	@Autowired
	IMaterialCategoryService	materialCategoryService;

	@RequestMapping("/warehouse/MaterialCategory/jsonTree.html")
	public String tree(Model model) {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<MaterialCategory> queryResult = materialCategoryService
				.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		for (MaterialCategory d : queryResult.getResultlist()) {
			ExtTreeNode node = new ExtTreeNode();
			node.setId(new Integer(d.getId().toString()));
			node.setText(d.getName());
			if (null != d.getParentMaterialCategory()
					&& d.getParentMaterialCategory().getId() > 0) {
				node.setParentId(new Integer(d.getParentMaterialCategory()
						.getId().toString()));
			}
			if (d.getId() == 1) {
				node.setExpanded(true);
			} else {
				node.setExpanded(false);
			}
			extTreeList.add(node);
		}
		ExtTreeRecursion r = new ExtTreeRecursion();
		if (null != extTreeList && extTreeList.size() > 0) {
			r.recursionFn(extTreeList, extTreeList.get(0));
		}

		model
				.addAttribute("jsonText", r.modifyStr(r.getReturnStr()
						.toString()));
		System.out.println("jsonText"
				+ r.modifyStr(r.getReturnStr().toString()));
		return "share/jsonTextView";
	}

	@RequestMapping("/warehouse/MaterialCategory/jsonList.html")
	public String list(Model model) {

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<MaterialCategory> queryResult = materialCategoryService
				.getScrollData(orderby);
		List<MaterialCategoryExtGridRow> rows = new ArrayList<MaterialCategoryExtGridRow>();
		for (MaterialCategory d : queryResult.getResultlist()) {
			MaterialCategoryExtGridRow r = new MaterialCategoryExtGridRow();
			r.setId(d.getId());
			r.setName(d.getName());
			r.setNote(d.getNote());
			if (null != d.getParentMaterialCategory()) {
				r.setParentMaterialCategoryId(d.getParentMaterialCategory()
						.getId());
				r.setParentMaterialCategoryName(d.getParentMaterialCategory()
						.getName());
			}
			rows.add(r);
		}
		ExtGridList<MaterialCategoryExtGridRow> materialCategoryGrid = new ExtGridList<MaterialCategoryExtGridRow>();
		materialCategoryGrid.setRows(rows);
		JSONObject jsonObject = JSONObject.fromObject(materialCategoryGrid);

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

	@RequestMapping("/warehouse/MaterialCategory/jsonSave.html")
	public String save(MaterialCategoryExtGridRow me, ModelMap model) {
		MaterialCategory mCategory = new MaterialCategory();
		if (null != me.getId() && me.getId() > 0) {
			mCategory = materialCategoryService.find(me.getId());
		}
		mCategory.setName(me.getName());
		if (null != me.getParentMaterialCategoryId()) {
			mCategory.setParentMaterialCategory(materialCategoryService.find(me
					.getParentMaterialCategoryId()));
		}
		if (null != me.getId() && me.getId() > 0) {
			materialCategoryService.update(mCategory);
		} else {
			materialCategoryService.save(mCategory);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		long id = null != me.getId() && me.getId() > 0 ? me.getId()
				: materialCategoryService.findLast().getId();
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

	@RequestMapping("/warehouse/MaterialCategory/jsonTreeDelete.html")
	public String delete(ModelMap model, Long[] id) {
		materialCategoryService.delete((Serializable[]) id);
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
