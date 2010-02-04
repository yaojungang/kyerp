package com.tyopf.util.excel;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestCreateExcel {
	private HSSFWorkbook workbook = new HSSFWorkbook();   
	  
    public HSSFWorkbook getWorkbook() {   
        return workbook;   
    }   
  
    public void setWorkbook(HSSFWorkbook workbook) {   
        this.workbook = workbook;   
    }   
    public void exportExcel(){   
           
        try {   
            FileOutputStream fOut = new FileOutputStream("D:\\工作表编码.xls");   
               
            HSSFSheet sheet1 = getWorkbook().createSheet();   
            HSSFSheet sheet2 = getWorkbook().createSheet();   
               
            getWorkbook().setSheetName(0,"工作表1",(short)1);   
            getWorkbook().setSheetName(1,"工作表2",HSSFWorkbook.ENCODING_UTF_16);   
               
            HSSFRow row1 = sheet1.createRow(0);   
            HSSFRow row2 = sheet2.createRow(0);    
               
            HSSFCell cell1 = row1.createCell((short)0);   
            HSSFCell cell2 = row1.createCell((short)1);   
            HSSFCell cell3 = row2.createCell((short)0);   
               
            cell1.setEncoding((short)1);   
            cell2.setEncoding((short)1);   
            cell3.setEncoding(HSSFWorkbook.ENCODING_UTF_16);   
               
            cell1.setCellValue("中文那");   
            cell2.setCellValue("还是中文那");   
            cell3.setCellValue("还是中文好");   
               
            workbook.write(fOut);   
            fOut.flush();   
            fOut.close();   
            System.out.println("Excel文件已生成...");   
  
        } catch (Exception e) {   
            System.out.println("Excel文件生成失败: " + e);   
        }   
    }   
       
    public static void main(String args[]){   
    	TestCreateExcel cxls = new TestCreateExcel();   
        cxls.exportExcel();   
    }   

}
