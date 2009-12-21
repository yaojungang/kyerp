package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.kyerp.domain.base.views.ExtTreeNode;
import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class MaterialCategoryController {
	@Resource(name = "materialCategoryService")
	IMaterialCategoryService	materialCategoryService;

	@RequestMapping("/warehouse/MaterialCategory/index.html")
	public void index(ModelMap model, Integer page, Long id) {
		page = null == page || page < 1 ? 1 : page;
		PageView<MaterialCategory> pageView = new PageView<MaterialCategory>(
				12, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "asc");
		orderby.put("createTime", "asc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if (id != null && id > 0) {
			jpql.append(" and o.parentMaterialCategory.id=?"
					+ (params.size() + 1));
			params.add(id);
			model.addAttribute("navMcs", materialCategoryService
					.getParentMaterialCategories(id));
		} else {
			jpql.append(" and 1=1");
		}
		QueryResult<MaterialCategory> qureyResult = materialCategoryService
				.getScrollData(pageView.getFirstResult(), pageView
						.getMaxresult(), jpql.toString(), params.toArray(),
						orderby);
		pageView.setQueryResult(qureyResult);
		model.addAttribute("parentId", id);
		model.addAttribute("pageView", pageView);
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

	@RequestMapping("/warehouse/MaterialCategory/jsonMaterialCategoryList.html")
	public String jsonMaterialCategoryList(ModelMap model, Long node) {
		List<ExtTreeNode> treeNodes = new ArrayList<ExtTreeNode>();
		for (MaterialCategory mc : materialCategoryService
				.getMaterialCategoriesByParentId(node)) {
			ExtTreeNode treeNode = new ExtTreeNode();
			treeNode.setId(mc.getId().toString());
			treeNode.setText(mc.getName());
			treeNode
					.setHref("http://localhost:8080/kyerp3/warehouse/Material/index.html?id="
							+ mc.getId());
			// treeNode.setHrefTarget("_blank");
			if (mc.getChildMaterialCategories().size() > 0) {
				treeNode.setLeaf(false);
			} else {
				treeNode.setLeaf(true);
			}
			treeNodes.add(treeNode);
		}
		JSONArray jsonObject = JSONArray.fromObject(treeNodes);
		String text = "";
		try {
			text = jsonObject.toString();
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}

	@RequestMapping("/warehouse/MaterialCategory/add.html")
	public String add(ModelMap model, Long parentId) {
		model
				.addAttribute("materialCategories", this
						.getMaterialCategoryList());
		MaterialCategory entry = new MaterialCategory();
		if (null != parentId) {
			entry.setParentMaterialCategory(materialCategoryService
					.find(parentId));
		}
		model.addAttribute("entry", entry);
		return "forward:input.html";

	}

	@RequestMapping("/warehouse/MaterialCategory/edit.html")
	public String edit(ModelMap model, Long id) {
		model.addAttribute("entry", materialCategoryService.find(id));
		model
				.addAttribute("materialCategories", this
						.getMaterialCategoryList());
		return "forward:input.html";

	}

	@RequestMapping("/warehouse/MaterialCategory/input.html")
	public void input(ModelMap model) {

	}

	@RequestMapping("/warehouse/MaterialCategory/save.html")
	public String save(MaterialCategory category, Long id, Long parentId,
			ModelMap model) {
		System.out.println("categoryID:" + category.getId());
		if (parentId > 0) {
			category.setParentMaterialCategory(materialCategoryService
					.find(parentId));
		}
		if (null != id) {
			MaterialCategory c = materialCategoryService.find(id);
			c.setParentMaterialCategory(category.getParentMaterialCategory());
			c.setSerialNumber(category.getSerialNumber());
			c.setName(category.getName());
			c.setNote(category.getNote());
			materialCategoryService.update(c);
		} else {
			materialCategoryService.save(category);
		}

		return "redirect:index.html?id=" + parentId;

	}

	@RequestMapping("/warehouse/MaterialCategory/del.html")
	public String del(Long[] ids) {
		materialCategoryService.delete((Serializable[]) ids);
		return "redirect:index.html";
	}
}
