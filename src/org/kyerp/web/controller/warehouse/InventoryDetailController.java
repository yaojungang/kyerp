package org.kyerp.web.controller.warehouse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.InventoryDetail;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.service.warehouse.IInventoryDetailService;
import org.kyerp.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class InventoryDetailController extends BaseController{
	@Autowired
	IInventoryDetailService	inventoryDetailService;

	@RequestMapping("/warehouse/InventoryDetail/jsonList.html")
	public String list(Model model, Integer start, Integer limit, Long materialId, String query) {
		QueryResult<InventoryDetail> queryResult = getList(model, start, limit, materialId, query);

		List<InventoryDetailExtGridRow> rows = new ArrayList<InventoryDetailExtGridRow>();
		for (InventoryDetail o : queryResult.getResultlist()) {
			InventoryDetailExtGridRow n = new InventoryDetailExtGridRow();
			n.setId(o.getId());
			/** 建立时间 */
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if(null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 物料 */
			if(null != o.getMaterial()) {
				n.setMaterialId(o.getMaterial().getId());
				n.setMaterialName(o.getMaterial().getName());
			}
			/** 批次号 */
			n.setBatchNumber(o.getBatchNumber());
			/** 单据号 */
			if(o instanceof InStockDetail) {
				InStockDetail inStockDetail = (InStockDetail) o;
				n.setSerialNumber(inStockDetail.getInStock().getSerialNumber());
				// 收发类型
				n.setInOutType(inStockDetail.getInStock().getInOutType().getName());
			}
			if(o instanceof OutStockDetail) {
				OutStockDetail outStockDetail = (OutStockDetail) o;
				n.setSerialNumber(outStockDetail.getOutStock().getSerialNumber());
				// 生产任务单号
				n.setPressworkNo(outStockDetail.getPressworkNo());
				// 收发类型
				n.setInOutType(outStockDetail.getOutStock().getInOutType().getName());
			}

			/** 入库数量 */
			n.setInStockCount(o.getInStockCount());
			/** 出库数量 */
			n.setOutStockCount(o.getOutStockCount());
			/** 仓库 */
			if(null != o.getWarehouse()) {
				n.setWarehouseId(o.getWarehouse().getId());
				n.setWarehouseName(o.getWarehouse().getName());
			}

			/** 单位 */
			if(null != o.getUnit()) {
				n.setUnitId(o.getUnit().getId());
				n.setUnitName(o.getUnit().getName());
			}
			/** 价格 */
			n.setPrice(o.getPrice());
			/** 总金额 */
			n.setCost(o.getCost());

			rows.add(n);
		}
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	private QueryResult<InventoryDetail> getList(Model model, Integer start, Integer limit, Long materialId, String query) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		// build where jpql
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set parent id
		if(null != materialId) {
			wherejpql.append(" and o.material.id=?").append(queryParams.size() + 1);
			queryParams.add(materialId);
		}
		// set query
		if(null != query && !query.equals("") && query.trim().length() > 0) {
			wherejpql.append(" and (o.material.name like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// material's serialNumber
			wherejpql.append(" or o.material.serialNumber like ?").append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		// logger.debug("jpql:" + wherejpql);
		QueryResult<InventoryDetail> queryResult = inventoryDetailService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);

		return queryResult;
	}
}
