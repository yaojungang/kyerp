package org.kyerp.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.util.CellRangeAddress;
import org.kyerp.domain.common.view.ExcelTitleCell;

/**
 * 生成导出Excel文件对象
 * 
 * @author John.Zhu
 * 
 */
public class ExcelExportor {
	// 定制日期格式
	private static String DATE_FORMAT = "m/d/yy"; // "m/d/yy h:mm"
	// 定制浮点数格式
	private static String NUMBER_FORMAT = " #,##0.000 ";
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;	
	/**
	 * 初始化Excel
	 * 
	 * @param fileName
	 *   导出文件名
	 * @throws Exception 
	 */
	public ExcelExportor(String sheetName) throws Exception {
		this.workbook = new HSSFWorkbook();
		this.sheet = workbook.createSheet(sheetName);
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
		footer.setCenter("操作员：" + WebUtil.getCurrentEmployee().getName()
				+ "  导出时间：" + df.format(date));
		footer.setRight("第" + HSSFFooter.page() + "页,共" + HSSFFooter.numPages()
				+ "页");
	}
	/**
	 * 导出Excel文件
	 * 
	 * @throws XLSException
	 */
	public void exportXLS(String fileName) throws Exception {
		FileOutputStream outputStream = new FileOutputStream(fileName);
		exportXLS(outputStream);
	}
	/**
	 * 导出Excel文件
	 * 
	 * @throws XLSException
	 */
	public void exportXLS(HttpServletResponse response,String fileName) throws Exception {
		response.setHeader(
				"Content-disposition",
				"attachment;filename="
						+ new String((fileName).getBytes("GBK"), "ISO_8859_1") + ".xls");
		response.setContentType("application/vnd.ms-excel");
		OutputStream outputStream = response.getOutputStream();
		exportXLS(outputStream);		
	}

	private  void exportXLS(OutputStream outputStream) throws Exception {
		try {
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			throw new Exception(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new Exception(" 写入Excel文件出错! ", e);
		}

	}
	/**
	 * 增加一行
	 * 
	 * @param rowNumber
	 *            行号
	 */
	public void createRow(int rowNumber) {
		this.row = this.sheet.createRow(rowNumber);
	}
	/**
	 * 建立标题行
	 * @param title
	 * @param firstRowNumber
	 * @param lastRowNumber
	 * @param firstColumn
	 * @param lastColoumn
	 */
	public void createTitleRow(String title,int firstRowNumber,int lastRowNumber, int firstColumn, int lastColoumn) {
		// 设置字体
		HSSFFont font = this.workbook.createFont();
		font.setFontHeightInPoints((short) 16); // 字体高度
		font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
		font.setFontName("黑体"); // 字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setItalic(false); // 是否使用斜体
		font.setStrikeout(false); // 是否使用划线

		// 定义标题行样式
		CellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

		HSSFRow titleRow = this.sheet.createRow(firstRowNumber);
		titleRow.setHeight((short) 600);
		Cell cell = titleRow.createCell(firstColumn);
		cell.setCellValue(title);
		cell.setCellStyle(cellStyle);
		// 指定合并区域
		sheet.addMergedRegion(new CellRangeAddress(firstRowNumber,lastRowNumber,firstColumn,lastColoumn));
	};
	/**
	 * 建立列标题行
	 * @param sheet
	 * @param excelCells
	 * @param rowNumber
	 */
	public void createNameRow(int rowNumber,List<ExcelTitleCell> titles) {
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
		for (int i = 0; i < titles.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(titles.get(i).getTitle());
			sheet.setColumnWidth(i, titles.get(i).getWidth());
		}
	}
	/**
	 * 设置单元格
	 * 
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, String value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellStyle(defaultcellStyle());
		cell.setCellValue(value);
	}

	
	/**
	 * 设置单元格
	 * 
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, Calendar value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellValue(value.getTime());
		cell.setCellStyle(defaultcellStyle());
		HSSFCellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT)); // 设置cell样式为定制的日期格式
		cell.setCellStyle(cellStyle); // 设置该cell日期的显示格式
	}

	
	/**
	 * 设置单元格
	 * 
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, int value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(defaultcellStyle());
		cell.setCellValue(value);
	}

	
	/**
	 * 设置单元格
	 * 
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, double value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyleFloat()); // 设置该cell浮点数的显示格式
	}
	/**
	 * 设置单元格
	 * @param index
	 * @param value
	 */
	public void setCell(int index, BigDecimal value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value.floatValue());
		cell.setCellStyle(cellStyleFloat()); // 设置该cell浮点数的显示格式
	}
	/**
	 * 默认正文字体
	 */
	public HSSFFont defaultFont(){
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12); // 字体高度
		font.setColor(HSSFFont.COLOR_NORMAL); // 字体颜色
		// font.setFontName("黑体"); // 字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setItalic(false); // 是否使用斜体
		font.setStrikeout(false); // 是否使用划线
		return font;
	}
	/**
	 * 默认正文样式
	 */
	public CellStyle defaultcellStyle(){
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(defaultFont());
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return cellStyle;
	}
	/**
	 * 带小数点的样式
	 */
	public CellStyle cellStyleFloat(){
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(defaultFont());
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		// 设置小数位数
		HSSFDataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); // 3位小数
		return cellStyle;
	}
}