package com.tyopf.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import edu.npu.fastexcel.FastExcel;
import edu.npu.fastexcel.Sheet;
import edu.npu.fastexcel.Workbook;

public class TestExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String strPath = "d:/test.xls";
		
		try {
			// 构造 HSSFWorkbook 对象，strPath 传入文件路径   
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(strPath));   
			// 读取文件中的第一张表格   
			HSSFSheet sheet = wb.getSheetAt(0);   
			// 定义 row、cell   
			HSSFRow row;   
			// HSSFCell cell;   
			HSSFCell cell;
			// 循环输出表格中的内容   
			for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {   
			    row = sheet.getRow(i);   
			    for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {   
			        // 推荐通过 row.getCell(j).toString() 获取单元格内容，   
			    	cell = row.getCell((short) j);  
			        System.out.print(cell + "\t");   
			    }   
			    System.out.println("");   
			}
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		System.out.println("-----------------------------Start FastExcel --------------------------------------------------------");
		//FastExcel
		try    {
			Workbook workBook = FastExcel.createReadableWorkbook(new File(strPath));
		workBook.open();

		Sheet s = workBook.getSheet(0);
		
		String[] row;
		String cell;
		
		for (int i = s.getFirstRow(); i <= s.getLastRow(); i++) {
			row = s.getRow(i);
			for (int j = s.getFirstColumn(); j <= s.getLastColumn(); j++) {
				cell = s.getCell(i, j);
				System.out.print(cell + "\t");
			}
			System.out.println(i);
		}
		
		workBook.close();
	       }   catch  (Exception e)   {   
	           System.out.println(e);   
	       }    

	}

}
