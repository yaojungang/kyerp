package org.kyerp.web.controller.warehouse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.util.CellRangeAddress;
import org.kyerp.domain.common.view.ExcelTitleColumn;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IStockService;
import org.kyerp.utils.WebUtil;
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
				// System.out.println("o.getMaterial().getId():" + o.getMaterial().getId());
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
	public void excel(Model model, Long mCategoryId, String query, HttpServletResponse response, HttpServletRequest request) throws Exception {

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
		// System.out.println("jpql:" + wherejpql);
		QueryResult<Stock> queryResult = stockService.getScrollData(wherejpql.toString(), queryParams.toArray(), orderby);

		response.setHeader("Content-disposition", "attachment;" + "filename=" + new String(("库存" + System.currentTimeMillis()).getBytes("GBK"), "ISO_8859_1") + ".xls");
		response.setContentType("application/vnd.ms-excel");

		// 新建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();
		// 新建一个Excel工作表
		HSSFSheet sheet = workbook.createSheet("库存");

		int rowNumber = 0;

		// 建立标题
		createTitleRow(workbook, sheet, "库存状况报表", rowNumber, 0, 7);
		// 建立字段标题行
		List<ExcelTitleColumn> excelTitleColumns = new ArrayList<ExcelTitleColumn>();
		excelTitleColumns.add(new ExcelTitleColumn("序号", 1500));
		excelTitleColumns.add(new ExcelTitleColumn("名称", 9000));
		excelTitleColumns.add(new ExcelTitleColumn("批次", 3550));
		excelTitleColumns.add(new ExcelTitleColumn("单位", 1500));
		excelTitleColumns.add(new ExcelTitleColumn("单价", 2500));
		excelTitleColumns.add(new ExcelTitleColumn("数量", 3500));
		excelTitleColumns.add(new ExcelTitleColumn("金额", 2000));
		excelTitleColumns.add(new ExcelTitleColumn("备注", 2000));
		createHeaderRow(workbook, sheet, excelTitleColumns, ++rowNumber);

		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12); // 字体高度
		font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
		// font.setFontName("黑体"); // 字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setItalic(false); // 是否使用斜体
		font.setStrikeout(false); // 是否使用划线
		// cellstyle
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		// 填充主体数据
		int stockNumber = 0;
		for (Stock stock : queryResult.getResultlist()) {
			int columnNumber = 0;
			// Create a row and put some cells in it. Rows are 0 based.
			HSSFRow row = sheet.createRow((short) ++rowNumber);
			// row.setHeight((short) 500);
			Cell cell01 = row.createCell(columnNumber++);
			cell01.setCellValue(++stockNumber);
			cell01.setCellStyle(cellStyle);
			Cell cell02 = row.createCell(columnNumber++);
			cell02.setCellValue(createHelper.createRichTextString(stock.getMaterial().getName()));
			cell02.setCellStyle(cellStyle);
			Cell cell03 = row.createCell(columnNumber++);
			cell03.setCellValue("");
			cell03.setCellStyle(cellStyle);

			Cell cell04 = row.createCell(columnNumber++);
			cell04.setCellValue(stock.getUnit().getName());
			cell04.setCellStyle(cellStyle);

			Cell cellPrice01 = row.createCell(columnNumber++);
			cellPrice01.setCellValue(stock.getMaterial().getPrice().toString());
			cellPrice01.setCellStyle(cellStyle);

			Cell cell05 = row.createCell(columnNumber++);
			cell05.setCellValue(stock.getTotalAmount().toString());
			cell05.setCellStyle(cellStyle);

			Cell money01 = row.createCell(columnNumber++);
			// System.out.println("stock.getCost().toString()" + stock.getCost().toString());
			money01.setCellValue(stock.getCost().toString());
			money01.setCellStyle(cellStyle);

			Cell cell06 = row.createCell(columnNumber++);
			cell06.setCellValue(stock.getRemark());
			cell06.setCellStyle(cellStyle);
			for (StockDetail stockDetail : stock.getStockDetails()) {
				columnNumber = 0;
				// Create a row and put some cells in it. Rows are 0 based.
				HSSFRow detailRow = sheet.createRow((short) ++rowNumber);
				Cell cell1 = detailRow.createCell(columnNumber++);
				cell1.setCellValue("");
				cell1.setCellStyle(cellStyle);
				Cell cell2 = detailRow.createCell(columnNumber++);
				cell2.setCellValue("");
				cell2.setCellStyle(cellStyle);
				Cell cell3 = detailRow.createCell(columnNumber++);
				cell3.setCellValue(stockDetail.getBatchNumber());
				cell3.setCellStyle(cellStyle);
				Cell cell4 = detailRow.createCell(columnNumber++);
				cell4.setCellValue(stockDetail.getUnit().getName());
				cell4.setCellStyle(cellStyle);

				Cell cellPrice = detailRow.createCell(columnNumber++);
				cellPrice.setCellValue(stockDetail.getPrice().toString());
				cellPrice.setCellStyle(cellStyle);

				Cell cell5 = detailRow.createCell(columnNumber++);
				cell5.setCellValue(stockDetail.getAmount().toString());
				cell5.setCellStyle(cellStyle);

				Cell money1 = detailRow.createCell(columnNumber++);
				// System.out.println("stockDetail.getCost().toString()" + stockDetail.getCost().toString());
				money1.setCellValue(stockDetail.getCost().toString());
				money1.setCellStyle(cellStyle);

				Cell cell6 = detailRow.createCell(columnNumber++);
				cell6.setCellValue(stockDetail.getRemark());
				cell6.setCellStyle(cellStyle);
			}
		}
// sheet.autoSizeColumn((short) 0);
// sheet.autoSizeColumn((short) 1);
// sheet.autoSizeColumn((short) 2);
// sheet.autoSizeColumn((short) 3);
// 工作表打印设置
		PrintSetup ps = sheet.getPrintSetup();
		// 缩放
		// ps.setScale((short) 100);
		// 适应宽度
		ps.setFitWidth((short) 1);
		ps.setFitHeight((short) 0);

		// 纸张大小
		ps.setPaperSize(PrintSetup.A4_PAPERSIZE);
		// 页眉
		ps.setHeaderMargin(0.2);
		// 页脚(F)
		ps.setFooterMargin(0.2);
		// 页面距
		sheet.setMargin(HSSFSheet.TopMargin, 0.4);
		sheet.setMargin(HSSFSheet.BottomMargin, 0.6);
		sheet.setMargin(HSSFSheet.LeftMargin, 0.4);
		sheet.setMargin(HSSFSheet.RightMargin, 0.4);

		// 居中方式
		sheet.setHorizontallyCenter(true);
		sheet.setVerticallyCenter(false);

		sheet.setAutobreaks(true);

		// 仅仅设置顶端标题行：
// HSSFWorkbook#setRepeatingRowsAndColumns(intsheetIndex,
// intstartColumn,
// intendColumn,
// intstartRow,
// intendRow);
		workbook.setRepeatingRowsAndColumns(0, -1, -1, 0, 1);

// 工作表页眉设置
		// HSSFHeader header = sheet.getHeader();
		// header.setCenter("库存状况报表");
// 工作表页脚设置
		HSSFFooter footer = sheet.getFooter();
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		footer.setCenter("操作员：" + WebUtil.getCurrentEmployee().getName() + "  导出时间：" + df.format(date));
		footer.setRight("第" + HSSFFooter.page() + "页,共" + HSSFFooter.numPages() + "页");

		workbook.write(response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	private QueryResult<Stock> getList(Model model, Integer start, Integer limit, Long mCategoryId, String query) {
		start = null == start ? 0 : start;
		limit = null == limit ? 20 : limit;

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("material.name", "desc");
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
			// 物料名称
			wherejpql.append(" and (o.material.name like ?").append(queryParams.size() + 1);
			queryParams.add("%" + query.trim() + "%");

			// material's serialNumber
			wherejpql.append(" or o.material.serialNumber like ?").append(queryParams.size() + 1).append(")");
			queryParams.add("%" + query.trim().toUpperCase() + "%");
		}
		// System.out.println("jpql:" + wherejpql);
		QueryResult<Stock> queryResult = stockService.getScrollData(start, limit, wherejpql.toString(), queryParams.toArray(), orderby);
		// 批次号
// wherejpql.append(" or o.material.name like ?").append(queryParams.size() + 1);
// queryParams.add("%" + query.trim() + "%");
// for (Stock stock : queryResult.getResultlist()) {
//			
// }
		return queryResult;
	}

	private void createTitleRow(HSSFWorkbook workbook, HSSFSheet sheet, String title, int rowNumber, int firstColumn, int lastColoumn) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 16); // 字体高度
		font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
		font.setFontName("黑体"); // 字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setItalic(false); // 是否使用斜体
		font.setStrikeout(false); // 是否使用划线

		// 定义标题行样式
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

		HSSFRow titleRow = sheet.createRow(rowNumber);
		titleRow.setHeight((short) 600);
		Cell cell = titleRow.createCell(firstColumn);
		cell.setCellValue(title);
		cell.setCellStyle(cellStyle);
		// 指定合并区域
		sheet.addMergedRegion(new CellRangeAddress(rowNumber, // first row (0-based)
			rowNumber, // last row (0-based)
			firstColumn, // first column (0-based)
			lastColoumn // last column (0-based)
			));
	};

	private void createHeaderRow(HSSFWorkbook workbook, HSSFSheet sheet, List<ExcelTitleColumn> excelTitleColumns, int rowNumber) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12); // 字体高度
		font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
		// font.setFontName("黑体"); // 字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setItalic(false); // 是否使用斜体
		font.setStrikeout(false); // 是否使用划线
		// 定义字段标题行样式
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		HSSFRow headerRow = sheet.createRow(rowNumber);
		for (int i = 0; i < excelTitleColumns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(excelTitleColumns.get(i).getName());
			sheet.setColumnWidth(i, excelTitleColumns.get(i).getWidth());
			// System.out.println("width:" + i + " - " + excelTitleColumns.get(i).getWidth());
			// sheet.autoSizeColumn((short) headerNumber);
		}
	}

	@RequestMapping("/warehouse/Stock/jsonSave.html")
	public String save(StockExtGridRow row, ModelMap model) {
		Stock stock = new Stock();
		if(null != row.getId() && row.getId() > 0) {
			stock = stockService.find(row.getId());
		}
		stock.setRemark(row.getRemark());

		if(null != row.getId() && row.getId() > 0) {
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
	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/Stock/resetStockAmount.html")
	public String resetStockAmount(ModelMap model) throws Exception {
		try {
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("id", "desc");
			List<Stock> list = stockService.getScrollData(null, null, orderby).getResultlist();
			for (Stock stock : list) {
				stockService.resetStockAmount(stock.getId());
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
