package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.ExtTreeNode;
import org.kyerp.domain.common.view.ExtTreeRecursion;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.MaterialCategory;
import org.kyerp.service.warehouse.IMaterialCategoryService;
import org.kyerp.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class MaterialCategoryController extends BaseController{
	@Autowired
	IMaterialCategoryService	materialCategoryService;

	@RequestMapping("/warehouse/MaterialCategory/jsonList.html")
	public String list(Long parentId, Integer start, Integer limit, Model model) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;
		parentId = null == parentId ? 1 : parentId;
		// build order by
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		// build where jpql
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set parent id
		if(null != parentId) {
			wherejpql.append(" and parentMaterialCategory.id=?").append(queryParams.size() + 1);
			queryParams.add(parentId);
		}
		QueryResult<MaterialCategory> queryResult = materialCategoryService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<MaterialCategoryExtGridRow> rows = new ArrayList<MaterialCategoryExtGridRow>();
		for (MaterialCategory o : queryResult.getResultlist()) {
			MaterialCategoryExtGridRow n = new MaterialCategoryExtGridRow();
			n.setId(o.getId());
			n.setName(o.getName());
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if(null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 申请单号 */
			n.setSerialNumber(o.getSerialNumber());
			n.setNote(o.getNote());
			/** 父类 */
			if(null != o.getParentMaterialCategory()) {
				n.setParentMaterialCategoryId(o.getParentMaterialCategory().getId());
				n.setParentMaterialCategoryName(o.getParentMaterialCategory().getName());
			} else {
				n.setParentMaterialCategoryId(new Long(0));
				n.setParentMaterialCategoryName("顶级分类");
			}
			rows.add(n);
		}
		;
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/warehouse/MaterialCategory/jsonTree.html")
	public String tree(Model model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<MaterialCategory> queryResult = materialCategoryService.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(queryResult.getResultlist().size() == 0) {
			MaterialCategory materialCategory = new MaterialCategory();
			materialCategory.setName("物料分类");
			materialCategoryService.save(materialCategory);
			model.addAttribute("jsonText", "[{id:1,text:'物料分类',leaf:true}]");
		} else {
			for (MaterialCategory d : queryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(d.getId()));
				node.setText(d.getName());
				if(null != d.getParentMaterialCategory() && d.getParentMaterialCategory().getId() > 0) {
					node.setParentId(String.valueOf(d.getParentMaterialCategory().getId()));
				}
				if(d.getId() == 1) {
					node.setExpanded(true);
				} else {
					node.setExpanded(false);
				}
				extTreeList.add(node);
			}

			ExtTreeRecursion r = new ExtTreeRecursion();
			if(null != extTreeList && extTreeList.size() > 0) {
				r.recursionFn(extTreeList, extTreeList.get(0));
			}
			String strTreeString = r.modifyStr(r.getReturnStr().toString());

			model.addAttribute("jsonText", strTreeString);
		}
		return "share/jsonTextView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/MaterialCategory/jsonSave.html")
	public String save(MaterialCategoryExtGridRow materialCategoryRow, ModelMap model) {
		MaterialCategory materialCategory = new MaterialCategory();
		if(null != materialCategoryRow.getId() && materialCategoryRow.getId() > 0) {
			materialCategory = materialCategoryService.find(materialCategoryRow.getId());
		}
		materialCategory.setName(materialCategoryRow.getName());
		// 设置父类
		if(materialCategoryRow.getParentMaterialCategoryId() != 0) {
			materialCategory.setParentMaterialCategory(materialCategoryService.find(materialCategoryRow.getParentMaterialCategoryId()));
		}
		// 设置note
		if(null != materialCategoryRow.getNote()) {
			materialCategory.setNote(materialCategoryRow.getNote());
		}
		// 设置序号
		if(null != materialCategoryRow.getSerialNumber()) {
			materialCategory.setSerialNumber(materialCategoryRow.getSerialNumber());
		}
		if(null != materialCategoryRow.getId() && materialCategoryRow.getId() > 0) {
			materialCategoryService.update(materialCategory);
		} else {
			materialCategoryService.save(materialCategory);
		}
		long id = materialCategory.getId() > 0 ? materialCategory.getId() : materialCategoryService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/MaterialCategory/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		materialCategoryService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
