package com.tyopf.util.excel;

import java.io.File;

import edu.npu.fastexcel.FastExcel;
import edu.npu.fastexcel.Sheet;
import edu.npu.fastexcel.Workbook;

public class TestFastExcel {

	public void testFastExcel(String strPath) throws Exception {
		
		Workbook workBook = FastExcel.createReadableWorkbook(new File(strPath));
		workBook.open();

		Sheet s = workBook.getSheet(0);
		
		String[] row;
		String cell;
		
		for (int i = s.getFirstRow(); i <= s.getLastRow(); i++) {
			row = s.getRow(i);
			for (int j = s.getFirstColumn(); j <= s.getLastColumn(); j++) {
				cell = s.getCell(i, j);
			}
		}
		
		workBook.close();
	}

}
