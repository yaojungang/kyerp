package org.kyerp.web.controller.warehouse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.warehouse.Stock;
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
public class StockController {
	@Autowired
	IStockService	stockService;

	@RequestMapping("/warehouse/Stock/jsonList.html")
	public String list(Model model, Integer start, Integer limit,
			Long mCategoryId, String query) {
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
		if (null != mCategoryId) {
			wherejpql.append(" and o.material.materialCategory.id=?").append(
					queryParams.size() + 1);
			queryParams.add(mCategoryId);
		}
		// set query
		if (null != query && !query.equals("")) {
			wherejpql.append(" and (o.material.name like ?").append(
					queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// material's brand name
			wherejpql.append(" or o.material.brand.name like ?").append(
					queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");
			// material's serialNumber
			wherejpql.append(" or o.material.serialNumber like ?").append(
					queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim() + "%");
		}
		QueryResult<Stock> queryResult = stockService.getScrollData(start,
				limit, wherejpql.toString(), queryParams.toArray(), orderby);

		List<StockExtGridRow> rows = new ArrayList<StockExtGridRow>();
		for (Stock o : queryResult.getResultlist()) {
			StockExtGridRow n = new StockExtGridRow();
			n.setId(o.getId());
			/** 建立时间 */
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(),
					"yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if (null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(),
						"yyyy-MM-dd HH:mm:ss"));
			}
			/** 编码 */
			n.setSerialNumber(o.getSerialNumber());
			/** 物料 */
			if (null != o.getMaterial()) {
				n.setMaterialId(o.getMaterial().getId());
				n.setMaterialName(o.getMaterial().getName());
			}
			/** 仓库 */
			if (null != o.getWarehouse()) {
				n.setWarehouseId(o.getWarehouse().getId());
				n.setWarehouseName(o.getWarehouse().getName());
			}
			/** 数量 */
			n.setAmount(o.getAmount());
			/** 单位 */
			if (null != o.getUnit()) {
				n.setUnitId(o.getUnit().getId());
				n.setUnitName(o.getUnit().getName());
			}
			/** 价格 */
			n.setPrice(o.getPrice());
			/** 金额 */
			n.setCost(o.getCost());
			rows.add(n);
		}
		model.addAttribute("totalProperty", queryResult.getTotalrecord());
		model.addAttribute("rows", rows);
		return "jsonView";
	}
}
