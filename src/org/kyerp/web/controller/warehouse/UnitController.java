package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.base.views.ExtTreeNode;
import org.kyerp.domain.base.views.ExtTreeRecursion;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Unit;
import org.kyerp.service.warehouse.IUnitService;
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
public class UnitController extends BaseController{
	@Autowired
	IUnitService	unitService;

	@RequestMapping("/warehouse/Unit/jsonList.html")
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
			wherejpql.append(" and parentUnit.id=?").append(queryParams.size() + 1);
			queryParams.add(parentId);
		}
		QueryResult<Unit> queryResult = unitService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<UnitExtGridRow> rows = new ArrayList<UnitExtGridRow>();
		for (Unit o : queryResult.getResultlist()) {
			UnitExtGridRow n = new UnitExtGridRow();
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
			if(null != o.getParentUnit()) {
				n.setParentUnitId(o.getParentUnit().getId());
				n.setParentUnitName(o.getParentUnit().getName());
			} else {
				n.setParentUnitId(0);
				n.setParentUnitName("顶级分类");
			}
			rows.add(n);
		}
		;
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/warehouse/Unit/jsonTree.html")
	public String tree(Model model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Unit> queryResult = unitService.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(queryResult.getResultlist().size() == 0) {
			Unit unit = new Unit();
			unit.setName("计量单位");
			unitService.save(unit);
			model.addAttribute("jsonText", "[{id:1,text:'计量单位',leaf:true}]");
		} else {
			for (Unit d : queryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(d.getId()));
				node.setText(d.getName());
				if(null != d.getParentUnit() && d.getParentUnit().getId() > 0) {
					node.setParentId(String.valueOf(d.getParentUnit().getId()));
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
	@RequestMapping("/warehouse/Unit/jsonSave.html")
	public String save(UnitExtGridRow unitRow, ModelMap model) {
		Unit unit = new Unit();
		if(null != unitRow.getId() && unitRow.getId() > 0) {
			unit = unitService.find(unitRow.getId());
		}
		unit.setName(unitRow.getName());
		// 设置父类
		if(unitRow.getParentUnitId() != 0) {
			unit.setParentUnit(unitService.find(unitRow.getParentUnitId()));
		}
		// 设置note
		if(null != unitRow.getNote()) {
			unit.setNote(unitRow.getNote());
		}
		// 设置序号
		if(null != unitRow.getSerialNumber()) {
			unit.setSerialNumber(unitRow.getSerialNumber());
		}
		if(null != unitRow.getId() && unitRow.getId() > 0) {
			unitService.update(unit);
		} else {
			unitService.save(unit);
		}
		long id = unit.getId() > 0 ? unit.getId() : unitService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Unit/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		unitService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
