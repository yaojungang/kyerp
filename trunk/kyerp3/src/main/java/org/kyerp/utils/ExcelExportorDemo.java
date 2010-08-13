 package  org.kyerp.utils;

 import java.util.Calendar;

 /** */ /** 
 * 导出Excel示例
 * 
 *  @author  John.Zhu
 * 
  */ 
  public   class ExcelExportorDemo   {

     public   static   void  main(String[] args) throws Exception   {
        System.out.println( " 导出Excel文件[开始] " );
        ExcelExportor e  =   new  ExcelExportor( "ExcelTest" );
        e.createRow( 0 );
        e.setCell( 0 ,  " 编号 " );
        e.setCell( 1 ,  " 名称 " );
        e.setCell( 2 ,  " 日期 " );
        e.setCell( 3 ,  " 金额 " );
        e.createRow( 1 );
        e.setCell( 0 ,  1 );
        e.setCell( 1 ,  " 工商银行 " );
        e.setCell( 2 , Calendar.getInstance());
        e.setCell( 3 ,  111123.99 );
        e.createRow( 2 );
        e.setCell( 0 ,  2 );
        e.setCell( 1 ,  " 招商银行 " );
        e.setCell( 2 , Calendar.getInstance());
        e.setCell( 3 ,  222456.88 );
         try    {
            e.exportXLS("d:/test.xls");
            System.out.println( " 导出Excel文件[成功] " );
        }   catch  (Exception e1)   {
            System.out.println( " 导出Excel文件[失败] " );
            e1.printStackTrace();
        }finally{
        	System.out.println( " 导出Excel文件[结束] " );
        }
    } 
} 