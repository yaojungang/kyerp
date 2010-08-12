package org.kyerp.web.controller.warehouse.print;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.time.DateFormatUtils;
import org.kyerp.domain.common.view.ExcelTitleCell;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Material;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.domain.warehouse.print.PaperOfMaterial;
import org.kyerp.service.warehouse.IStockService;
import org.kyerp.utils.ExcelExportor;
import org.kyerp.utils.MathTools;
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
@RequestMapping("/warehouse/print/PaperStock/")
public class PaperStockController extends BaseController {
	@Autowired
	IStockService stockService;

	@RequestMapping("jsonList.html")
	public String list(Model model, Integer start, Integer limit,
			Long mCategoryId, Long ownerId, String query) {
		QueryResult<Stock> queryResult = getList(model, start, limit,
				ownerId,mCategoryId,  query);

		List<PaperStockExtGridRow> rows = new ArrayList<PaperStockExtGridRow>();
		for (Stock o : queryResult.getResultlist()) {
			PaperStockExtGridRow n = new PaperStockExtGridRow();
			n.setId(o.getId());
			/** 建立时间 */
			n.setCreateTime(DateFormatUtils.format(o.getCreateTime(),
					"yyyy-MM-dd HH:mm:ss"));
			/** 修改时间 */
			if (null != o.getUpdateTime()) {
				n.setUpdateTime(DateFormatUtils.format(o.getUpdateTime(),
						"yyyy-MM-dd HH:mm:ss"));
			}
			/** 所有者 */
			if (null != o.getOwner()) {
				n.setOwnerId(o.getOwner().getId());
				n.setOwnerName(o.getOwner().getName());
			}
			/** 物料 */
			if (null != o.getMaterial()) {
				n.setMaterialId(o.getMaterial().getId());
				n.setMaterialName(o.getMaterial().getName());
			}
			/** 总数量 */
			n.setTotalAmount(o.getTotalAmount());
			/** 单位 */
			if (null != o.getUnit()) {
				n.setUnitId(o.getUnit().getId());
				n.setUnitName(o.getUnit().getName());
			}
			/** 价格 */
			n.setPrice(o.getPrice());
			/** 总金额 */
			n.setCost(o.getCost());
			/** 备注 **/
			n.setRemark(o.getRemark());
			/** 明细 */
			if (null != o.getStockDetails() && o.getStockDetails().size() > 0) {
				List<PaperStockDetailExtGridRow> itemRows = new ArrayList<PaperStockDetailExtGridRow>();
				for (StockDetail detail : o.getStockDetails()) {
					PaperStockDetailExtGridRow row = new PaperStockDetailExtGridRow();
					/** id */
					row.setId(detail.getId());
					/** 时间 */
					row.setCreateTime(DateFormatUtils.format(
							detail.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					/** 修改时间 */
					if (null != detail.getUpdateTime()) {
						row.setUpdateTime(DateFormatUtils.format(
								detail.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
					}
					/** 库存单 */
					row.setStockId(o.getId());
					row.setStockSerialNumber(o.getSerialNumber());
					/** 物料 */
					if (null != o.getMaterial()) {
						row.setMaterialId(o.getMaterial().getId());
						row.setMaterialName(o.getMaterial().getName());
					}
					/** 仓库 */
					if (null != detail.getWarehouse()) {
						row.setWarehouseId(detail.getWarehouse().getId());
						row.setWarehouseName(detail.getWarehouse().getName());
					}
					/** 批次号 */
					if (null != detail.getBatchNumber()) {
						row.setBatchNumber(detail.getBatchNumber());
					}
					/** 单位 */
					if (null != detail.getUnit()) {
						row.setUnitId(detail.getUnit().getId());
						row.setUnitName(detail.getUnit().getName());
					}
					/** 数量 */
					if (null != detail.getAmount()) {
						row.setAmount(detail.getAmount());
					}
					
					/** 金额 */
					if (null != detail.getCost()) {
						row.setCost(detail.getCost());
					}
					
					/** 价格 */
					if (null != detail.getPrice()) {
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

	@RequestMapping("excel.html")
	public void excel(Model model, Integer start, Integer limit,
			Long mCategoryId, Long ownerId, String query,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		ExcelExportor excel = new ExcelExportor("库存状况表");
		int rowNumber = 0;
		List<ExcelTitleCell> titleCells = new ArrayList<ExcelTitleCell>();
		titleCells.add(new ExcelTitleCell("序号", 1500));
		titleCells.add(new ExcelTitleCell("名称", 9000));
		titleCells.add(new ExcelTitleCell("批次", 4000));
		titleCells.add(new ExcelTitleCell("单位", 1500));
		titleCells.add(new ExcelTitleCell("单价", 2500));
		titleCells.add(new ExcelTitleCell("数量", 3500));
		titleCells.add(new ExcelTitleCell("金额", 3500));
		titleCells.add(new ExcelTitleCell("备注", 2500));	
		// 建立标题行
		excel.createTitleRow("清华园胶印厂库存状况表", rowNumber, rowNumber, 0, (titleCells.size()-1));
		// 建立字段标题行
		excel.createNameRow(++rowNumber, titleCells);		
		// 填充主体数据
		QueryResult<Stock> queryResult = getList(model, start, limit, ownerId, mCategoryId, query);
		int stockNumber = 0;
		for (Stock stock : queryResult.getResultlist()) {
			excel.createRow(++rowNumber);
			excel.setCell(0, ++stockNumber);
			excel.setCell(1, stock.getMaterial().getName());
			excel.setCell(2, "");
			excel.setCell(3, stock.getUnit().getName());
			excel.setCell(4, Double.parseDouble(MathTools.removeTailZero(stock.getMaterial().getPrice())));
			excel.setCell(5, Double.parseDouble(MathTools.removeTailZero(stock.getTotalAmount())));
			excel.setCell(6, Double.parseDouble(MathTools.removeTailZero(stock.getCost())));
			excel.setCell(7, stock.getRemark());
						
			for (StockDetail stockDetail : stock.getStockDetails()) {
				excel.createRow(++rowNumber);
				excel.setCell(0, "");
				excel.setCell(1, "");
				excel.setCell(2, stockDetail.getBatchNumber());
				excel.setCell(3, stockDetail.getUnit().getName());
				excel.setCell(4, Double.parseDouble(MathTools.removeTailZero(stockDetail.getPrice())));
				excel.setCell(5, Double.parseDouble(MathTools.removeTailZero(stockDetail.getAmount())));
				excel.setCell(6, Double.parseDouble(MathTools.removeTailZero(stockDetail.getCost())));
				excel.setCell(7, stockDetail.getRemark());
			}
		}
		excel.exportXLS(response, "库存状况表-" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	}

	private QueryResult<Stock> getList(Model model, Integer start,
			Integer limit, Long ownerId, Long mCategoryId, String query) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;
		//ownerId = null == ownerId ? 2 : ownerId;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("material.name", "desc");
		// build where jpql
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set owner id
		if (null != ownerId) {
			wherejpql.append(" and o.owner.id=?").append(
					queryParams.size() + 1);
			queryParams.add(ownerId);
		}
		// set parent id
		if (null != mCategoryId) {
			wherejpql.append(" and o.material.materialCategory.id=?").append(
					queryParams.size() + 1);
			queryParams.add(mCategoryId);
		}
		// set query
		if (null != query && !query.equals("") && query.trim().length() > 0) {
			// 物料名称
			wherejpql.append(" and (o.material.name like ?").append(
					queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");

			// material's serialNumber
			wherejpql.append(" or o.material.serialNumber like ?")
					.append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim().toUpperCase() + "%");
		}
		logger.debug("查询库存:\njpql=" + wherejpql.toString()+"\n参数="+queryParams.toString());
		QueryResult<Stock> queryResult = stockService.getScrollData(start,
				limit, wherejpql.toString(), queryParams.toArray(), orderby);
		List<Stock> stocks = new ArrayList<Stock>();
		for (Stock stock : queryResult.getResultlist()) {
			Material material = stock.getMaterial();
			 if (material instanceof PaperOfMaterial) {
				stocks.add(stock);
			}
		}
		queryResult.setResultlist(stocks);
		return queryResult;
	}

	@RequestMapping("jsonSave.html")
	public String save(PaperStockExtGridRow row, ModelMap model) {
		Stock stock = new Stock();
		if (null != row.getId() && row.getId() > 0) {
			stock = stockService.find(row.getId());
		}
		stock.setRemark(row.getRemark());

		if (null != row.getId() && row.getId() > 0) {
			stockService.update(stock);
		}
		model.addAttribute("success", true);
		return "jsonView";
	}

	/**
	 * 重新计算库存数量
	 * 
	 * @throws Exception
	 */
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping("resetStockAmount.html")
	public String resetStockAmount(ModelMap model) throws Exception {
		try {
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("id", "desc");
			List<Stock> list = stockService.getScrollData(null, null, orderby)
					.getResultlist();
			for (Stock stock : list) {
				stockService.updateAmountPriceAndCost(stock.getId());
			}

			model.addAttribute("msg", "重新计算库存数量成功!");
			model.addAttribute("success", true);
		} catch (Exception e) {
			model.addAttribute("msg", "重新计算库存数量失败!");
			model.addAttribute("success", false);
		}

		return "jsonView";
	}
}
