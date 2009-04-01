<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>名片-业务管理</title>
<script type="text/javascript" src="MPListGridPanel.js">
	
</script>
<script type="text/javascript">
	Ext.onReady( function() {
				Ext.QuickTips.init();

				Ext.BLANK_IMAGE_URL = '../Library/js/ext/resources/images/default/s.gif';

				new MPListGridPanel();
				
			});
</script>
</head>
<body>
<div id="divMPListGridPanel"></div>
</body>
</html>
