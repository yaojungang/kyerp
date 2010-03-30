<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生产统计</title>
<script type="text/javascript"
	src="<s:url value="/Library/js/Validator.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/ext-all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
 Ext.QuickTips.init();
 Ext.form.Field.prototype.msgTarget = 'side';
 Ext.BLANK_IMAGE_URL = '../Library/js/ext/resources/images/default/s.gif';
 
 var storeYW = new Ext.data.SimpleStore({
 	url:'../OPE/jsongetAllYW.action',
 	fields:['value']
 });
    var storeClient = new Ext.data.JsonStore({
     url:'../OPE/jsongetClientByYW.action',
     fields:['CCCom','CCDa','clientLm']
    });
 
    var comboYW = new Ext.form.ComboBox({
        store: storeYW,
        emptyText: '请选择接洽业务员',
        allowBlank: false,
        blankText: '必须选择业务员',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'value',
        readOnly: false,
  applyTo: 'comboYW'
    });
    
    
    var comboClient = new Ext.form.ComboBox({
        store: storeClient,
        emptyText: '请选择委印单位',
        allowBlank: false,
        blankText: '必须选择一个系统中已有的委印单位',
        mode: 'local',
        width: 380,
        triggerAction: 'all',
        valueField: 'CCCom',
        displayField: 'CCCom',
        readOnly: true,
        resizable:true,
        minListWidth:380,
        pageSize: 5,
  applyTo: 'comboClient'
    });

    storeYW.load();
    comboYW.on('select', function(comboBox){
        var value = comboBox.getValue();
        storeClient.load({params:{YWName:value}});
    });
    
});
</script>
</head>
<body>
<span class="pageTitle">生产统计</span>
<br />
<br />
请输入任务单号范围:
<br />
<br />
<form name="form1" method="post" action="Tj.action">任务单类型:
<select name="AFType" id="AFType">
	<option value="SK">受控(SK)</option>
	<option value="LH">零活(LH)</option>
	<option value="FP">快印(FP)</option>
</select> 从 <input name="StartAFNo" type="text" id="StartAFNo" value="20080001">
到 <input name="EndAFNo" type="text" id="EndAFNo" value="20080123">
<input type="submit" name="button" id="button" value="提交"></form>
<br>
<br>
<form action="getAFEByEMachine.action" method="post"
 target="_blank">
 <h2 align="left">按客户和机型统计产值</h2>
 <table width="90%" border="0" cellspacing="4" cellpadding="4">
  <tr>
   <td width="180" align="right">客户单位名称：</td>
   <td align="left"><input name="YWName" id="comboYW" type="text" />
   <input name="clientName" id="comboClient" type="text" /></td>
  </tr>
  <tr>
   <td width="180" align="right">机型：</td>
   <td align="left"><select name="EMachine" id="EMachine">
    <option value="四色">四色</option>
    <option value="双面">双面</option>
    <option value="05">05</option>

   </select></td>
  </tr>
  <tr>
   <td width="180" align="right">业务类型：</td>
   <td align="left"><select name="AFType" id="AFType">
   <option value="SK">受控(SK)</option>
	<option value="LH">零活(LH)</option>
	<option value="FP">快印(FP)</option>
   </select></td>
  </tr>
  <tr>
   <td align="right">任务单号范围：</td>
   <td align="left"><input name="StartAFNo" type="text"
    id="StartAFNo" value="20080801"> — <input name="EndAFNo"
    type="text" id="EndAFNo" value="20080910"></td>
  </tr>
  <tr>
   <td align="right">&nbsp;</td>
   <td align="left"><input name="提交" type="submit" value="统计"></td>
  </tr>
 </table>
</form> <br>
<br>
<br>
请输入任务单号范围:
<br>
<s:form action="Tj_getAFbyAFNoList.action" method="post"
	target="_blank">
	<textarea rows="10" cols="70" name="AFNoList"></textarea>
	<br>

请在输入框中输入需要生成的任务单号列表,单号之间用逗号（,）隔开。 例如：          SK20080001,SK20080234,SK20080235,SK20080236,LH20080234,LH200801122,SK20080145
<br>
	<input type="submit" name="button" id="button" value="提交">
</s:form>
</body>
</html>
