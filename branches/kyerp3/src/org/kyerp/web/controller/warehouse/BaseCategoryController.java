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
import org.kyerp.domain.warehouse.BaseCategory;
import org.kyerp.service.warehouse.IBaseCategoryService;
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
public class BaseCategoryController extends BaseController{
	@Autowired
	IBaseCategoryService	baseCategoryService;

	@RequestMapping("/warehouse/BaseCategory/jsonList.html")
	public String list(Long parentId, Integer start, Integer limit, Model model) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;
		parentId = null == parentId ? 1L : parentId;
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
		QueryResult<BaseCategory> queryResult = baseCategoryService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<BaseCategoryExtGridRow> rows = new ArrayList<BaseCategoryExtGridRow>();
		for (BaseCategory o : queryResult.getResultlist()) {
			BaseCategoryExtGridRow n = new BaseCategoryExtGridRow();
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
				n.setParentCategoryId(o.getParentCategory().getId());
				n.setParentCategoryName(o.getParentCategory().getName());
			} else {
				n.setParentCategoryId(new Long(0));
				n.setParentCategoryName("顶级分类");
			}
			rows.add(n);
		}
		;
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/warehouse/BaseCategory/jsonTree.html")
	public void tree(Long parentId, HttpServletResponse response, Model model) throws IOException {
		parentId = null == parentId ? 1L : parentId;
		response.getWriter().write(treeString());
	}

	public String treeString() {
		QueryResult<BaseCategory> queryResult = baseCategoryService.getScrollData();
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		ExtTreeNode rootNode = new ExtTreeNode();
		rootNode.setId("0");
		rootNode.setText("库存分类");
		rootNode.setExpanded(true);
		extTreeList.add(rootNode);
		for (BaseCategory d : queryResult.getResultlist()) {
			ExtTreeNode node = new ExtTreeNode();
			node.setId(String.valueOf(d.getId()));
			node.setText(d.getName());
			if(null != d.getParentCategory() && d.getParentCategory().getId() > 0) {
				node.setParentId(String.valueOf(d.getParentCategory().getId()));
			} else {
				node.setParentId("0");
			}
			extTreeList.add(node);
		}

		ExtTreeRecursion r = new ExtTreeRecursion();
		if(null != extTreeList && extTreeList.size() > 0) {
			r.recursionFn(extTreeList, rootNode);
		}
		return r.modifyStr(r.getReturnStr().toString());
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/BaseCategory/jsonSave.html")
	public String save(BaseCategoryExtGridRow baseCategoryRow, ModelMap model) {
		BaseCategory baseCategory = new BaseCategory();
		if(null != baseCategoryRow.getId() && baseCategoryRow.getId() > 0) {
			baseCategory = baseCategoryService.find(baseCategoryRow.getId());
		}
		baseCategory.setName(baseCategoryRow.getName());
		// 设置父类
		if(baseCategoryRow.getParentCategoryId() != 0) {
			baseCategory.setParentCategory(baseCategoryService.find(baseCategoryRow.getParentCategoryId()));
		}
		// 设置remark
		baseCategory.setRemark(baseCategoryRow.getRemark());
		// 设置序号
		if(null != baseCategoryRow.getSerialNumber()) {
			baseCategory.setSerialNumber(baseCategoryRow.getSerialNumber());
		}
		if(null != baseCategoryRow.getId() && baseCategoryRow.getId() > 0) {
			baseCategoryService.update(baseCategory);
		} else {
			baseCategoryService.save(baseCategory);
		}
		long id = baseCategory.getId() > 0 ? baseCategory.getId() : baseCategoryService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/BaseCategory/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		baseCategoryService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
