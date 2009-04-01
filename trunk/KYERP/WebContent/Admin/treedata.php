<?php
//============================
//	Filename: filename.php
//	Version : 0.0.1
//	Author  : leo
//	Update  : 2007-11-01
//	Content : PHP by Editplus
//============================
//echo '[{id: 1,text: \'子节点1\',leaf: true},{id: 2,text: \'儿子节点2\',children: [{id: 3,text: \'孙子节点\',leaf: true}]}]';


require('tree.cfg.php');
require('JSON.php');
$result = $trees[$_REQUEST['node']];
$json = new Services_JSON();
echo $json->encode($result);
?>