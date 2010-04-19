package org.kyerp.web.controller.warehouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class StockController{
	@Autowired
	IStockService	stockService;

	@RequestMapping("/warehouse/Stock/jsonList.html")
	public String list(Model model, Integer start, Integer limit, Long mCategoryId, String query) {
		QueryResult<Stock> queryResult = getList(model, start, limit, mCategoryId, query);

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

	@RequestMapping("/warehouse/Stock/excel.html")
	public void excel(Model model, Integer start, Integer limit, Long mCategoryId, String query, HttpServletResponse response, HttpServletRequest request) throws IOException {
		QueryResult<Stock> queryResult = this.getList(model, start, limit, mCategoryId, query);

		response.setHeader("Content-disposition", "attachment;" + "filename=" + new String(("库存" + System.currentTimeMillis()).getBytes("GBK"), "ISO_8859_1") + ".xls");
		response.setContentType("application/vnd.ms-excel");
		HSSFWorkbook wbook = new HSSFWorkbook();
		CreationHelper createHelper = wbook.getCreationHelper();
		HSSFSheet dataSheet = wbook.createSheet("库存");
		// Style the cell with borders all around.
		CellStyle style = wbook.createCellStyle();
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLUE.getIndex());
		style.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());

		int rowNumber = 0;
		createHeaderRow(dataSheet, new String[] { "序号", "名称", "单位", "批次", "数量" }, rowNumber, style);

		int stockNumber = 0;
		for (Stock stock : queryResult.getResultlist()) {
			int columnNumber = 0;
			// Create a row and put some cells in it. Rows are 0 based.
			HSSFRow row = dataSheet.createRow((short) ++rowNumber);
			row.createCell(columnNumber++).setCellValue(++stockNumber);
			// row.createCell(columnNumber++).setCellValue(stock.getId());
			row.createCell(columnNumber++).setCellValue(createHelper.createRichTextString(stock.getMaterial().getName()));
			row.createCell(columnNumber++).setCellValue(stock.getUnit().getName());
			row.createCell(columnNumber++).setCellValue("");
			row.createCell(columnNumber++).setCellValue(stock.getTotalAmount().toString());
			for (StockDetail stockDetail : stock.getStockDetails()) {
				columnNumber = 0;
				// Create a row and put some cells in it. Rows are 0 based.
				HSSFRow detailRow = dataSheet.createRow((short) ++rowNumber);
				detailRow.createCell(columnNumber++).setCellValue("");
				// detailRow.createCell(columnNumber++).setCellValue(stockDetail.getId());
				detailRow.createCell(columnNumber++).setCellValue("");
				detailRow.createCell(columnNumber++).setCellValue(stockDetail.getUnit().getName());
				detailRow.createCell(columnNumber++).setCellValue(stockDetail.getBatchNumber());
				detailRow.createCell(columnNumber++).setCellValue(stockDetail.getAmount().toString());
			}
		}
		dataSheet.autoSizeColumn((short) 0);
		dataSheet.autoSizeColumn((short) 1);
		dataSheet.autoSizeColumn((short) 2);
		dataSheet.autoSizeColumn((short) 3);
// 打印设置
		PrintSetup ps = dataSheet.getPrintSetup();
		dataSheet.setAutobreaks(true);
		// ps.setFitHeight((short) 1);
		ps.setFitWidth((short) 1);
// 页眉设置
		HSSFHeader header = dataSheet.getHeader();
		header.setCenter("库存状况报表");
// 页脚设置
		HSSFFooter footer = dataSheet.getFooter();
		footer.setCenter("第  " + HSSFFooter.page() + " 页  共  " + HSSFFooter.numPages() + " 页");
		wbook.write(response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	private QueryResult<Stock> getList(Model model, Integer start, Integer limit, Long mCategoryId, String query) {
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

		return queryResult;
	}

	private void createHeaderRow(HSSFSheet sheet, String[] headerCellTitles, int rowNumber, CellStyle style) {
		HSSFRow headerRow = sheet.createRow((short) rowNumber);
		int headerNumber = 0;
		for (String title : headerCellTitles) {
			Cell cell = headerRow.createCell(headerNumber++);
			cell.setCellValue(title);
			cell.setCellStyle(style);
		}
	}

}
