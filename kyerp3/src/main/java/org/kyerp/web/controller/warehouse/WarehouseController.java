package org.kyerp.web.controller.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.ExtTreeNode;
import org.kyerp.domain.common.view.ExtTreeRecursion;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Warehouse;
import org.kyerp.service.warehouse.IWarehouseService;
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
@RequestMapping("/warehouse/Warehouse/")
public class WarehouseController extends BaseController{
	@Autowired
	IWarehouseService	warehouseService;

	@RequestMapping("jsonList.html")
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
			wherejpql.append(" and parentWarehouse.id=?").append(queryParams.size() + 1);
			queryParams.add(parentId);
		}
		QueryResult<Warehouse> queryResult = warehouseService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<WarehouseExtGridRow> rows = new ArrayList<WarehouseExtGridRow>();
		for (Warehouse o : queryResult.getResultlist()) {
			WarehouseExtGridRow n = new WarehouseExtGridRow();
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
			if(null != o.getParentWarehouse()) {
				n.setParentWarehouseId(o.getParentWarehouse().getId());
				n.setParentWarehouseName(o.getParentWarehouse().getName());
			} else {
				n.setParentWarehouseId(0);
				n.setParentWarehouseName("顶级分类");
			}
			rows.add(n);
		}
		;
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("jsonTree.html")
	public String tree(Model model) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<Warehouse> queryResult = warehouseService.getScrollData(orderby);
		List<ExtTreeNode> extTreeList = new ArrayList<ExtTreeNode>();
		if(queryResult.getResultlist().size() == 0) {
			Warehouse warehouse = new Warehouse();
			warehouse.setName("仓库");
			warehouseService.save(warehouse);
			model.addAttribute("jsonText", "[{id:1,text:'仓库',leaf:true}]");
		} else {
			for (Warehouse d : queryResult.getResultlist()) {
				ExtTreeNode node = new ExtTreeNode();
				node.setId(String.valueOf(d.getId()));
				node.setText(d.getName());
				if(null != d.getParentWarehouse() && d.getParentWarehouse().getId() > 0) {
					node.setParentId(String.valueOf(d.getParentWarehouse().getId()));
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
	@RequestMapping("jsonSave.html")
	public String save(WarehouseExtGridRow warehouseRow, ModelMap model) {
		Warehouse warehouse = new Warehouse();
		if(null != warehouseRow.getId() && warehouseRow.getId() > 0) {
			warehouse = warehouseService.find(warehouseRow.getId());
		}
		warehouse.setName(warehouseRow.getName());
		// 设置父类
		if(warehouseRow.getParentWarehouseId() != 0) {
			warehouse.setParentWarehouse(warehouseService.find(warehouseRow.getParentWarehouseId()));
		}
		// 设置note
		if(null != warehouseRow.getNote()) {
			warehouse.setNote(warehouseRow.getNote());
		}
		// 设置序号
		if(null != warehouseRow.getSerialNumber()) {
			warehouse.setSerialNumber(warehouseRow.getSerialNumber());
		}
		if(null != warehouseRow.getId() && warehouseRow.getId() > 0) {
			warehouseService.update(warehouse);
		} else {
			warehouseService.save(warehouse);
		}
		long id = warehouse.getId() > 0 ? warehouse.getId() : warehouseService.findLast().getId();
		model.addAttribute("success", true);
		model.addAttribute("id", id);
		return "jsonView";
	}

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		warehouseService.delete((Serializable[]) ids);
		model.addAttribute("success", true);
		return "jsonView";
	}
}
