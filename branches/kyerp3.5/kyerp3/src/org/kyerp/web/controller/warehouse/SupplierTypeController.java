package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.ExtTreeNode;
import org.kyerp.domain.common.view.ExtTreeRecursion;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.SupplierType;
import org.kyerp.service.warehouse.ISupplierTypeService;
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
public class SupplierTypeController extends BaseController{
	@Autowired
	ISupplierTypeService	supplierTypeService;

	@RequestMapping("/warehouse/SupplierType/jsonList.html")
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
			wherejpql.append(" and parentSupplierType.id=?").append(queryParams.size() + 1);
			queryParams.add(parentId);
		}
		QueryResult<SupplierType> queryResult = supplierTypeService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<SupplierTypeExtGridRow> rows = new ArrayList<SupplierTypeExtGridRow>();
		for (SupplierType o : queryResult.getResultlist()) {
			SupplierTypeExtGridRow n = new SupplierTypeExtGridRow();
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
			if(null != o.getParentSupplierType()) {
				n.setParentSupplierTypeId(o.getParentSupplierType().getId());
				n.setParentSupplierTypeName(o.getParentSupplierType().getName());
			} else {
				n.setParentSupplierTypeId(0);
				n.setParentSupplierTypeName("顶级分类");
			}
			rows.add(n);
		}
		;
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/warehouse/SupplierType/jsonTree.html")
	public String tree(Model model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<SupplierType> queryResult = supplierTypeService.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(queryResult.getResultlist().size() == 0) {
			SupplierType supplierType = new SupplierType();
			supplierType.setName("供应商分类");
			supplierTypeService.save(supplierType);
			model.addAttribute("jsonText", "[{id:1,text:'供应商分类',leaf:true}]");
		} else {
			for (SupplierType d : queryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(d.getId()));
				node.setText(d.getName());
				if(null != d.getParentSupplierType() && d.getParentSupplierType().getId() > 0) {
					node.setParentId(String.valueOf(d.getParentSupplierType().getId()));
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
	@RequestMapping("/warehouse/SupplierType/jsonSave.html")
	public String save(SupplierTypeExtGridRow supplierTypeRow, ModelMap model) {
		SupplierType supplierType = new SupplierType();
		if(null != supplierTypeRow.getId() && supplierTypeRow.getId() > 0) {
			supplierType = supplierTypeService.find(supplierTypeRow.getId());
		}
		supplierType.setName(supplierTypeRow.getName());
		// 设置父类
		if(supplierTypeRow.getParentSupplierTypeId() != 0) {
			supplierType.setParentSupplierType(supplierTypeService.find(supplierTypeRow.getParentSupplierTypeId()));
		}
		// 设置note
		if(null != supplierTypeRow.getNote()) {
			supplierType.setNote(supplierTypeRow.getNote());
		}
		// 设置序号
		if(null != supplierTypeRow.getSerialNumber()) {
			supplierType.setSerialNumber(supplierTypeRow.getSerialNumber());
		}
		if(null != supplierTypeRow.getId() && supplierTypeRow.getId() > 0) {
			supplierTypeService.update(supplierType);
		} else {
			supplierTypeService.save(supplierType);
		}
		long id = supplierType.getId() > 0 ? supplierType.getId() : supplierTypeService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/SupplierType/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		supplierTypeService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
