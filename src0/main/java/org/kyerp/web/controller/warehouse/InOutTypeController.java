package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.ExtTreeNode;
import org.kyerp.domain.common.view.ExtTreeRecursion;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.InOutType;
import org.kyerp.service.warehouse.IInOutTypeService;
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
public class InOutTypeController extends BaseController{
	@Autowired
	IInOutTypeService	inOutTypeService;

	@RequestMapping("/warehouse/InOutType/jsonList.html")
	public String list(Long parentId, Long inOutTypeId, Integer start, Integer limit, Model model) {
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
			wherejpql.append(" and parentInOutType.id=?").append(queryParams.size() + 1);
			queryParams.add(parentId);
		}
		QueryResult<InOutType> queryResult = inOutTypeService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<InOutTypeExtGridRow> rows = new ArrayList<InOutTypeExtGridRow>();
		for (InOutType o : queryResult.getResultlist()) {
			InOutTypeExtGridRow n = new InOutTypeExtGridRow();
			n.setId(o.getId());
			n.setName(o.getName());
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if(null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 申请单号 */
			n.setSerialNumber(o.getSerialNumber());
			/** 进出标记 */
			n.setInOutMark(o.getInOutMark());
			n.setNote(o.getNote());
			/** 父类 */
			if(null != o.getParentInOutType()) {
				n.setParentInOutTypeId(o.getParentInOutType().getId());
				n.setParentInOutTypeName(o.getParentInOutType().getName());
			} else {
				n.setParentInOutTypeId(0);
				n.setParentInOutTypeName("顶级分类");
			}
			rows.add(n);
		}
		;
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/warehouse/InOutType/jsonTree.html")
	public String tree(Model model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<InOutType> queryResult = inOutTypeService.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(queryResult.getResultlist().size() == 0) {
			InOutType inOutType = new InOutType();
			inOutType.setName("收发类别");
			inOutTypeService.save(inOutType);
			model.addAttribute("jsonText", "[{id:1,text:'收发类别',leaf:true}]");
		} else {
			for (InOutType d : queryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(d.getId()));
				node.setText(d.getName());
				if(null != d.getParentInOutType() && d.getParentInOutType().getId() > 0) {
					node.setParentId(String.valueOf(d.getParentInOutType().getId()));
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
	@RequestMapping("/warehouse/InOutType/jsonSave.html")
	public String save(InOutTypeExtGridRow inOutTypeRow, ModelMap model) {
		InOutType inOutType = new InOutType();
		if(null != inOutTypeRow.getId() && inOutTypeRow.getId() > 0) {
			inOutType = inOutTypeService.find(inOutTypeRow.getId());
		}
		inOutType.setName(inOutTypeRow.getName());
		// 设置父类
		if(inOutTypeRow.getParentInOutTypeId() != 0) {
			inOutType.setParentInOutType(inOutTypeService.find(inOutTypeRow.getParentInOutTypeId()));
		}
		/** 进出标记 */
		if(null != inOutTypeRow.getInOutMark()) {
			inOutType.setInOutMark(inOutTypeRow.getInOutMark());
		}
		// 设置note
		if(null != inOutTypeRow.getNote()) {
			inOutType.setNote(inOutTypeRow.getNote());
		}
		// 设置序号
		if(null != inOutTypeRow.getSerialNumber()) {
			inOutType.setSerialNumber(inOutTypeRow.getSerialNumber());
		}
		if(null != inOutTypeRow.getId() && inOutTypeRow.getId() > 0) {
			inOutTypeService.update(inOutType);
		} else {
			inOutTypeService.save(inOutType);
		}
		long id = inOutType.getId() > 0 ? inOutType.getId() : inOutTypeService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/InOutType/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		inOutTypeService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
