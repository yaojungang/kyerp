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

//��ȡJSON
   if($_GET['cmd']=="List")
   {
    echo $db->toExtJson($table,$start,$limit,$con);
   }
   
   //����
   if($_GET['cmd']=="Update")
   {
     $sql="update user set Name='".$_POST['Name']."',Password='".$_POST['Password']."',Email='".$_POST['Email']."',Remark='".$_POST['Remark']."' where Id='".$_POST['Id']."'";
     if($db->execute($sql))
	 echo"{success:true,info:'���³ɹ�'}";
	 else
	 echo"{failure:true,info:'����ʧ��,����ԭ��Ϊ:".$db->getError()."'}";
   }
   //����
   if($_GET['cmd']=="Add")
   {
     $sql="insert into course values(null,'".$_POST['cid']."','".$_POST['className']."','".$_POST['order']."','".$_POST['startTime']."','".$_POST['endTime']."','".$_POST['fee1']."','".$_POST['fee2']."','".$_POST['fee3']."','".$_POST['fee4']."','".$_POST['class_is_open']."')";
	 //INSERT INTO `csste_baoming`.`course` (`cid`, `classID`, `order`, `className`, `startTime`, `endTime`, `fee1`, `fee2`, `fee3`, `fee4`, `class_is_open`) VALUES (NULL, 'df', 'dsf', 'dsfds', '2008-11-13 02:20:35', '2008-11-13 02:20:35', 'dsf', 'dsfd', 'sfds', 'fds', '0');
     if($db->execute($sql))
	 echo"{success:true,info:'��ӳɹ�'}";
	 else
	 echo"{failure:true,info:'���ʧ��,����ԭ��Ϊ:".$db->getError()."'}";
   }
   //����ɾ��
   if($_GET['cmd']=="Remove")
   {
    $rows=jsondecode($_GET['json']);
	foreach($rows as $row)
	{
	 $sql="delete from user where Id='".$row."'";
	 if(!$db->execute($sql))
	 {
	  echo"ɾ�����Ϊ��".$row."���û�ʧ��";
	  break;
	 }
	}
	echo"ɾ���ɹ�";
   }

}

   //��JSONתΪ����
   function jsondecode($jsonString)
    {
        require_once("Ajax.php");
        $instance =& new Services_JSON(SERVICES_JSON_LOOSE_TYPE);
        return $instance->decode($jsonString);
    }
?>
