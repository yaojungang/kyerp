<?php

class Db
{
var $conn;

function Db($HOSTNAME,$USERNAME,$PASSWORD,$DATABASENAME)
{
  if(!$this->conn=mysql_connect($HOSTNAME,$USERNAME,$PASSWORD))
	die("无法连接数据库,请检查数据库连接代码:"."\$host=".$HOSTNAME.",\$user=".$USERNAME.",\$pass=".$PASSWORD.",\$db=".$DATABASENAME);
  mysql_select_db($DATABASENAME,$this->conn);
  mysql_query("SET NAMES 'UTF-8'");
}
function execute($sql)
{
   return mysql_query($sql,$this->conn);
}
function findCount($sql)
{
    $result=$this->execute($sql);
    return mysql_num_rows($result);
}
function findBySql($sql)
{
    $array=array();
    $result=mysql_query($sql);
    $i=0;
    while($row=mysql_fetch_assoc($result))
       {
      $array[$i]=$row; 
   $i++;
       }
    return $array;
}
//$con的几种情况
//空：返回全部记录
//array：eg. array('id'=>'1') 返回id=1的记录
//string :eg. 'id=1' 返回id=1的记录
function toExtJson($table,$start="0",$limit="10",$cons="")
{
   $sql=$this->generateSql($table,$cons);
   $totalNum=$this->findCount($sql);
   $result=$this->findBySql($sql." LIMIT ".$start." ,".$limit);
   $resultNum = count($result);//当前结果数
  $str="";
  $str.= "{";
  $str.= "'totalCount':'$totalNum',";
  $str.="'rows':";
  $str.="[";
  for($i=0;$i<$resultNum;$i++){
   $str.="{"; 
   $count=count($result[$i]);
   $j=1;
   foreach($result[$i] as $key=>$val)
   {
   if($j<$count)
   {
   $str.="'".$key."':'".$val."',";
   }
   elseif($j==$count)
   {
   $str.="'".$key."':'".$val."'";
   }
   $j++;
            }
   
   $str.="}";
   if ($i != $resultNum-1) {
             $str.= ",";
         }
  }
  $str.="]";
  $str.="}";
  return $str;  
}
function generateSql($table,$cons)
{
    $sql="";//sql条件
   $sql="select * from ".$table;
   if($cons!="")
   {
   if(is_array($cons))
   {
     $k=0;
     foreach($cons as $key=>$val)
  {
  if($k==0)
  {
  $sql.="where '";
  $sql.=$key;
  $sql.="'='";
  $sql.=$val."'";
  }else
  {
  $sql.="and '";
  $sql.=$key;
  $sql.="'='";
  $sql.=$val."'";
  }
  $k++;
  }
   }else
   {
   $sql.=" where ".$cons;
   }
   }
   return $sql;
}
function toExtXml($table,$start="0",$limit="10",$cons="")
{
   $sql=$this->generateSql($table,$cons);
   $totalNum=$this->findCount($sql);
   $result=$this->findBySql($sql." LIMIT ".$start." ,".$limit);
   $resultNum = count($result);//当前结果数
      header("Content-Type: text/xml");
   $xml="<?xml version=\"1.0\"  encoding=\"utf-8\" ?>\n";
   $xml.="<xml>\n";
   $xml.="\t<totalCount>".$totalNum."</totalCount>\n";
   $xml.="\t<items>\n";
   for($i=0;$i<$resultNum;$i++){
   $xml.="\t\t<item>\n";
   foreach($result[$i] as $key=>$val)
   $xml.="\t\t\t<".$key.">".$val."</".$key.">\n";
   $xml.="\t\t</item>\n";
   }
    $xml.="\t</items>\n";
    $xml.="</xml>\n";
    return $xml;
}
//输出word表格
function toWord($table,$mapping,$fileName)
{
   header('Content-type: application/doc'); 
      header('Content-Disposition: attachment; filename="'.$fileName.'.doc"'); 
      echo '<html xmlns:o="urn:schemas-microsoft-com:office:office" 
       xmlns:w="urn:schemas-microsoft-com:office:word" 
       xmlns="[url=http://www.w3.org/TR/REC-html40]http://www.w3.org/TR/REC-html40[/url]">
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>'.$fileName.'</title>
    </head>
    <body>'; 
    echo'<table border=1><tr>';
    if(is_array($mapping))
    {
      foreach($mapping as $key=>$val)
   echo'<td>'.$val.'</td>';
    }
    echo'</tr>';
    $results=$this->findBySql('select * from '.$table);
    foreach($results as $result)
    {
      echo'<tr>';
      foreach($result as $key=>$val)
   echo'<td>'.$val.'</td>';
   echo'</tr>';
    }
    echo'</table>';
    echo'</body>';
    echo'</html>';
}
function toExcel($table,$mapping,$fileName)
{
  header("Content-type:application/vnd.ms-excel");
     header("Content-Disposition:filename=".$fileName.".xls");
  echo'<html xmlns:o="urn:schemas-microsoft-com:office:office"
       xmlns:x="urn:schemas-microsoft-com:office:excel"
       xmlns="[url=http://www.w3.org/TR/REC-html40]http://www.w3.org/TR/REC-html40[/url]">
       <head>
       <meta http-equiv="expires" content="Mon, 06 Jan 1999 00:00:01 GMT">
       <meta http-equiv=Content-Type content="text/html; charset=iso-8859-1">
       <!--[if gte mso 9]><xml>
       <x:ExcelWorkbook>
       <x:ExcelWorksheets>
               <x:ExcelWorksheet>
               <x:Name></x:Name>
               <x:WorksheetOptions>
                       <x:DisplayGridlines/>
               </x:WorksheetOptions>
               </x:ExcelWorksheet>
       </x:ExcelWorksheets>
       </x:ExcelWorkbook>
       </xml><![endif]-->
       </head>
    <body link=blue vlink=purple leftmargin=0 topmargin=0>'; 
    echo'<table width="100%" border="0" cellspacing="0" cellpadding="0">';
       echo'<tr>';
    if(is_array($mapping))
    {
      foreach($mapping as $key=>$val)
   echo'<td>'.$val.'</td>';
    }
    echo'</tr>';
    $results=$this->findBySql('select * from '.$table);
    foreach($results as $result)
    {
      echo'<tr>';
      foreach($result as $key=>$val)
   echo'<td>'.$val.'</td>';
   echo'</tr>';
    }
    echo'</table>';
    echo'</body>';
    echo'</html>';
}
function Backup($table)
{
  if(is_array ($table))
  {
   $str="";
   foreach($table as $tab)
   $str.=$this->get_table_content($tab);
   return $str;
  }else{
   return $this->get_table_content($table);
  }
}
function Backuptofile($table,$file)
{
  header("Content-disposition: filename=$file.sql");//所保存的文件名
  header("Content-type: application/octetstream");
  header("Pragma: no-cache");
  header("Expires: 0");
  if(is_array ($table))
  {
   $str="";
   foreach($table as $tab)
   $str.=$this->get_table_content($tab);
   echo $str;
  }else{
   echo $this->get_table_content($table);
  }
}
function Restore($table,$file="",$content="")
{
  //排除file，content都为空或者都不为空的情况
  if(($file==""&&$content=="")||($file!=""&&$content!=""))
  echo"参数错误";
  $this->truncate($table);
  if($file!="")
  {
   if($this->RestoreFromFile($file))
   return true;
   else
   return false;
  }
  if($content!="")
  {
   if($this->RestoreFromContent($content))
   return true;
   else
   return false;
  }
}
//清空表，以便恢复数据
function truncate($table)
{
  if(is_array ($table))
  {
   $str="";
   foreach($table as $tab)
   $this->execute("TRUNCATE TABLE $tab");
  }else{
   $this->execute("TRUNCATE TABLE $table");
  }
}
function get_table_content($table)
{
  $results=$this->findBySql("select * from $table");
  $temp = "";
  $crlf="\r\n";
  foreach($results as $result)
  {
   
   /*(";
  foreach($result as $key=>$val)
  {
   $schema_insert .= " `".$key."`,";
  }
  $schema_insert = ereg_replace(",$", "", $schema_insert);
  $schema_insert .= ") 
  */
  $schema_insert = "INSERT INTO  $table VALUES (";
  foreach($result as $key=>$val)
  {
   if($val != "")
   $schema_insert .= " '".addslashes($val)."',";
   else
   $schema_insert .= "NULL,";
  }
  $schema_insert = ereg_replace(",$", "", $schema_insert);
  $schema_insert .= ");$crlf";
  $temp = $temp.$schema_insert ;
  }
  return $temp;
}
function RestoreFromFile($file){
  if (false !== ($fp = fopen($file, 'r'))) {
   $sql_queries = trim(fread($fp, filesize($file)));
   $this->splitMySqlFile($pieces, $sql_queries);
   foreach ($pieces as $query) {
    if(!$this->execute(trim($query)))
    return false;
   }
   return true;
  }
  return false;
}
function RestoreFromContent($content)
{
  $content = trim($content);
  $this->splitMySqlFile($pieces, $content);
  foreach ($pieces as $query) {
   if(!$this->execute(trim($query)))
   return false;
  }
  return true;
}
function splitMySqlFile(&$ret, $sql)
{
  $sql= trim($sql);
  $sql=split(';',$sql);
  $arr=array();
  foreach($sql as $sq)
  {
    if($sq!="");
    $arr[]=$sq;
  }
  $ret=$arr;
  return true;
}
}

// $db=new Db($HOSTNAME,$USERNAME,'',$DATABASENAME);
// echo $db->toExtJson('course'); 
/* 
//toExcel
$map=array('No','Name','Email','Age');//表头
$db->toExcel('test',$map,'档案'); */
//导出word表格
//$db->toWord('test',$map,'档案');
?>
