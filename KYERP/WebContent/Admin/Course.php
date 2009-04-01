<?php
require_once("../connect.inc.php");
require_once("db2x.inc.php");

$db=new Db($HOSTNAME,$USERNAME,'',$DATABASENAME);
$table='course';

$con="";
$start=isset($_POST['start'])?$_POST['start']:0;
$limit=isset($_POST['limit'])?$_POST['limit']:10;
if(isset($_POST['name']))
$con="className like %'".$_POST['className']."'% ";
$sql="";

if(isset($_GET['cmd']))
{

//获取JSON
   if($_GET['cmd']=="List")
   {
    echo $db->toExtJson($table,$start,$limit,$con);
   }
   
   //更新
   if($_GET['cmd']=="Update")
   {
     $sql="update user set Name='".$_POST['Name']."',Password='".$_POST['Password']."',Email='".$_POST['Email']."',Remark='".$_POST['Remark']."' where Id='".$_POST['Id']."'";
     if($db->execute($sql))
	 echo"{success:true,info:'更新成功'}";
	 else
	 echo"{failure:true,info:'更新失败,错误原因为:".$db->getError()."'}";
   }
   //新增
   if($_GET['cmd']=="Add")
   {
     $sql="insert into course values(null,'".$_POST['cid']."','".$_POST['className']."','".$_POST['order']."','".$_POST['startTime']."','".$_POST['endTime']."','".$_POST['fee1']."','".$_POST['fee2']."','".$_POST['fee3']."','".$_POST['fee4']."','".$_POST['class_is_open']."')";
	 //INSERT INTO `csste_baoming`.`course` (`cid`, `classID`, `order`, `className`, `startTime`, `endTime`, `fee1`, `fee2`, `fee3`, `fee4`, `class_is_open`) VALUES (NULL, 'df', 'dsf', 'dsfds', '2008-11-13 02:20:35', '2008-11-13 02:20:35', 'dsf', 'dsfd', 'sfds', 'fds', '0');
     if($db->execute($sql))
	 echo"{success:true,info:'添加成功'}";
	 else
	 echo"{failure:true,info:'添加失败,错误原因为:".$db->getError()."'}";
   }
   //批量删除
   if($_GET['cmd']=="Remove")
   {
    $rows=jsondecode($_GET['json']);
	foreach($rows as $row)
	{
	 $sql="delete from user where Id='".$row."'";
	 if(!$db->execute($sql))
	 {
	  echo"删除编号为：".$row."的用户失败";
	  break;
	 }
	}
	echo"删除成功";
   }

}

   //将JSON转为数组
   function jsondecode($jsonString)
    {
        require_once("Ajax.php");
        $instance =& new Services_JSON(SERVICES_JSON_LOOSE_TYPE);
        return $instance->decode($jsonString);
    }
?>
