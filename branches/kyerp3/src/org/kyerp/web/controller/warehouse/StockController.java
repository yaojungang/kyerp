package org.kyerp.web.controller.warehouse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
@SessionAttributes("mCategoryId")
public class StockController{
	@Autowired
	IStockService	stockService;

	@RequestMapping("/warehouse/Stock/jsonList.html")
	public String list(Model model, Integer start, Integer limit, Long mCategoryId, String query) {
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
		if(null != mCategoryId) {
			wherejpql.append(" and o.material.materialCategory.id=?").append(queryParams.size() + 1);
			queryParams.add(mCategoryId);
		}
		// set query
		if(null != query && !query.equals("") && query.trim().length() > 0) {
			wherejpql.append(" and (o.material.name like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// material's serialNumber
			wherejpql.append(" or o.material.serialNumber like ?").append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		System.out.println("jpql:" + wherejpql);
		QueryResult<Stock> queryResult = stockService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);

		List<StockExtGridRow> rows = new ArrayList<StockExtGridRow>();
		for (Stock o : queryResult.getResultlist()) {
			StockExtGridRow n = new StockExtGridRow();
			n.setId(o.getId());
			/** 建立时间 */
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if(null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			/** 物料 */
			if(null != o.getMaterial()) {
				System.out.println("o.getMaterial().getId():" + o.getMaterial().getId());
				n.setMaterialId(o.getMaterial().getId());
				n.setMaterialName(o.getMaterial().getName());
			}
			/** 总数量 */
			n.setTotalAmount(o.getTotalAmount());
			/** 单位 */
			if(null != o.getUnit()) {
				n.setUnitId(o.getUnit().getId());
				n.setUnitName(o.getUnit().getName());
			}
			/** 价格 */
			n.setPrice(o.getPrice());
			/** 总金额 */
			n.setCost(o.getCost());

			/** 明细 */
			if(null != o.getStockDetails() && o.getStockDetails().size() > 0) {
				List<StockDetailExtGridRow> itemRows = new ArrayList<StockDetailExtGridRow>();
				for (StockDetail detail : o.getStockDetails()) {
					StockDetailExtGridRow row = new StockDetailExtGridRow();
					/** id */
					row.setId(detail.getId());
					/** 时间 */
					row.setCreateTime(DateFormatUtils.format(detail.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					/** 修改时间 */
					if(null != detail.getUpdateTime()) {
						row.setUpdateTime(DateFormatUtils.format(detail.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
					}
					/** 库存单 */
					row.setStockId(o.getId());
					row.setStockSerialNumber(o.getSerialNumber());
					/** 物料 */
					if(null != o.getMaterial()) {
						row.setMaterialId(o.getMaterial().getId());
						row.setMaterialName(o.getMaterial().getName());
					}
					/** 仓库 */
					if(null != detail.getWarehouse()) {
						row.setWarehouseId(detail.getWarehouse().getId());
						row.setWarehouseName(detail.getWarehouse().getName());
					}
					/** 批次号 */
					if(null != detail.getBatchNumber()) {
						row.setBatchNumber(detail.getBatchNumber());
					}
					/** 单位 */
					if(null != detail.getUnit()) {
						row.setUnitId(detail.getUnit().getId());
						row.setUnitName(detail.getUnit().getName());
					}
					/** 数量 */
					row.setAmount(detail.getAmount());
					/** 金额 */
					row.setCost(detail.getCost());
					/** 价格 */
					if(null != detail.getPrice()) {
						row.setPrice(detail.getPrice());
					}
					/** 备注 */
					row.setRemark(detail.getRemark());
					itemRows.add(row);
				}
				JSONArray rowArrayItems = new JSONArray();
				rowArrayItems = JSONArray.fromObject(itemRows);
				n.setDetails(rowArrayItems.toString());
			}

			rows.add(n);
		}
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}
}
