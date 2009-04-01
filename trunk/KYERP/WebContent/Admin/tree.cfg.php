<?php
$trees['root'][] = array('id'=>'100000','text'=>'2008报名系统','leaf'=>false, 'cls'=>'folder');
	$trees['100000'][] = array('id'=>'100001','text'=>'报名列表','leaf'=>true, 'cls'=>'file','model'=>'user','action'=>'baomingList');
	$trees['100000'][] = array('id'=>'100002','text'=>'修改我的密码','leaf'=>true, 'cls'=>'file','model'=>'user','action'=>'modpass');
	$trees['100000'][] = array('id'=>'100003','text'=>'我的工作','leaf'=>true, 'cls'=>'file','model'=>'task','action'=>'my');
	$trees['100000'][] = array('id'=>'100004','text'=>'个人消息','leaf'=>true, 'cls'=>'file','model'=>'message','action'=>'');
	$trees['100000'][] = array('id'=>'100010','text'=>'我的信息','leaf'=>false, 'cls'=>'folder');	
		$trees['100010'][] = array('id'=>'100011','text'=>'我的出售房源','leaf'=>true, 'cls'=>'file','model'=>'sale','action'=>'my');
		$trees['100010'][] = array('id'=>'100012','text'=>'我的求购房源','leaf'=>true, 'cls'=>'file','model'=>'buy','action'=>'my');
		$trees['100010'][] = array('id'=>'100013','text'=>'我的客户','leaf'=>true, 'cls'=>'file','model'=>'client','action'=>'my');
$trees['root'][] = array('id'=>'200000','text'=>'系统设置','leaf'=>false, 'cls'=>'folder');
	$trees['200000'][] = array('id'=>'200010','text'=>'课程设置','leaf'=>false, 'cls'=>'folder');
		$trees['200010'][] = array('id'=>'200011','text'=>'课程列表','leaf'=>true, 'cls'=>'file','model'=>'sale','action'=>'');
		$trees['200010'][] = array('id'=>'200012','text'=>'添加出售房源','leaf'=>true, 'cls'=>'file','model'=>'sale','action'=>'add');
		$trees['200010'][] = array('id'=>'200013','text'=>'房源回收站','leaf'=>true, 'cls'=>'file','model'=>'sale','action'=>'recycle');
	$trees['200000'][] = array('id'=>'200020','text'=>'用户管理','leaf'=>false, 'cls'=>'folder');
		$trees['200020'][] = array('id'=>'200021','text'=>'所有求购房源','leaf'=>true, 'cls'=>'file','model'=>'buy','action'=>'');
		$trees['200020'][] = array('id'=>'200022','text'=>'添加求购房源','leaf'=>true, 'cls'=>'file','model'=>'buy','action'=>'add');
		$trees['200020'][] = array('id'=>'200023','text'=>'求购回收站','leaf'=>true, 'cls'=>'file','model'=>'buy','action'=>'recycle');
	$trees['200000'][] = array('id'=>'200030','text'=>'人员管理','leaf'=>false, 'cls'=>'folder');
		$trees['200030'][] = array('id'=>'200031','text'=>'所有人员','leaf'=>true, 'cls'=>'file','model'=>'uptown','action'=>'');
		$trees['200030'][] = array('id'=>'200032','text'=>'权限管理','leaf'=>true, 'cls'=>'file','model'=>'uptown','action'=>'add');
	$trees['200000'][] = array('id'=>'200040','text'=>'安利客户管理','leaf'=>false, 'cls'=>'folder');
		$trees['200040'][] = array('id'=>'200041','text'=>'所有客户','leaf'=>true, 'cls'=>'file','model'=>'client','action'=>'');
		$trees['200040'][] = array('id'=>'200042','text'=>'添加客户','leaf'=>true, 'cls'=>'file','model'=>'client','action'=>'add');
	
?>