package org.kyerp.web.controller.warehouse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IStockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
@SessionAttributes("mCategoryId")
public class StockDetailController{
	@Autowired
	IStockDetailService	stockDetailService;

	@RequestMapping("/warehouse/StockDetail/jsonList.html")
	public String list(Integer start, Integer limit, Long mCategoryId,String query, Long stockId, Model model) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		// build where jpql
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set 库存 id
		if(null != stockId) {
			wherejpql.append(" and o.stock.id=?").append(queryParams.size() + 1);
			queryParams.add(stockId);
		}
		// set 物料分类 id
		if(null != mCategoryId) {
			wherejpql.append(" and o.stock.material.materialCategory.id=?").append(queryParams.size() + 1);
			queryParams.add(mCategoryId);
		}
		// set query
		if(null != query && !query.equals("") && query.trim().length() > 0) {
			wherejpql.append(" and (o.stock.material.name like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// batchNumber
			wherejpql.append(" or o.batchNumber like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// material's serialNumber
			wherejpql.append(" or o.stock.material.serialNumber like ?").append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		QueryResult<StockDetail> queryResult = stockDetailService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);

		List<StockDetailExtGridRow> rows = new ArrayList<StockDetailExtGridRow>();
		for (StockDetail o : queryResult.getResultlist()) {
			StockDetailExtGridRow n = new StockDetailExtGridRow();
			n.setId(o.getId());
			/** 建立时间 */
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if(null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 物料 */
			if(null != o.getStock().getMaterial()) {
				n.setMaterialId(o.getStock().getMaterial().getId());
				n.setMaterialName(o.getStock().getMaterial().getName());
			}
			/** 批次号 */
			n.setBatchNumber(o.getBatchNumber());
			n.setStockDetailName(o.getBatchNumber() + o.getStock().getMaterial().getName());
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
			/** 备注 */
			n.setRemark(o.getRemark());
			rows.add(n);
		}
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}

	@RequestMapping("/warehouse/StockDetail/jsonSave.html")
	public String save(StockDetailExtGridRow row, ModelMap model) {
		StockDetail sd = new StockDetail();
		if(null != row.getId() && row.getId() > 0) {
			sd = stockDetailService.find(row.getId());
		}
		sd.setRemark(row.getRemark());

		if(null != row.getId() && row.getId() > 0) {
			stockDetailService.update(sd);
		}
		model.addAttribute("success", true);
		return "jsonView";
	}
}
