package org.kyerp.web.controller.warehouse;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	IMaterialCategoryService	baseCategoryService;
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
			wherejpql.append(" and parentCategory.id=?").append(queryParams.size() + 1);
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
			n.setRemark(o.getRemark());
			/** 父类 */
			if(null != o.getParentCategory()) {
				n.setParentMaterialCategoryId(o.getParentCategory().getId());
				n.setParentMaterialCategoryName(o.getParentCategory().getName());
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
	public void tree(Long parentId, Model model, HttpServletResponse response) throws IOException {
		// 默认的原材分类的根分类ID为2
		parentId = null == parentId ? 2L : parentId;
		response.getWriter().write(treeString(parentId));
	}

	public String treeString(Long parentId) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<MaterialCategory> queryResult = materialCategoryService.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		ExtTreeNode rootNode = new ExtTreeNode();
		if(queryResult.getResultlist().size() == 0) {
			MaterialCategory c = new MaterialCategory();
			c.setName("原料分类");
			materialCategoryService.save(c);
			return "[{id:" + materialCategoryService.findLast().getId() + ",text:'原料分类',leaf:true}]";
		} else {
			for (MaterialCategory d : queryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(d.getId()));
				node.setText(d.getName());
				if(null != d.getParentCategory() && d.getParentCategory().getId() > 0) {
					node.setParentId(String.valueOf(d.getParentCategory().getId()));
				}
				if(d.getId().compareTo(parentId) == 0) {
					rootNode.setId(String.valueOf(parentId));
					rootNode.setText(d.getName());
					rootNode.setExpanded(true);
					extTreeList.add(rootNode);
				} else {
					node.setExpanded(false);
				}
				extTreeList.add(node);
			}

			ExtTreeRecursion r = new ExtTreeRecursion();
			if(null != extTreeList && extTreeList.size() > 0) {
				r.recursionFn(extTreeList, rootNode);
			}
			String strTreeString = r.modifyStr(r.getReturnStr().toString());

			return strTreeString;
		}

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
			materialCategory.setParentCategory(materialCategoryService.find(materialCategoryRow.getParentMaterialCategoryId()));
		}
		// 设置remark
		materialCategory.setRemark(materialCategoryRow.getRemark());
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
