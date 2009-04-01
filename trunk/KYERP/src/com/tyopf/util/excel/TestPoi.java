package com.tyopf.util.excel;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestPoi {

	public void testPoiExcel(String strPath) throws Exception {

		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(strPath));

		HSSFSheet sheet = wb.getSheetAt(0);

		HSSFRow row;
		HSSFCell cell;

		for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				cell = row.getCell((short) j);
			}
		}
	}
}
