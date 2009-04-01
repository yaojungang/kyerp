<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产值统计</title>
<link rel="stylesheet" type="text/css"
	href="../Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="../Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../Library/js/ext/ext-all.js"></script>
<script type="text/javascript"
	src="../Library/js/ext/source/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	Ext.BLANK_IMAGE_URL = '../Library/js/ext/resources/images/default/s.gif';
	
    var storeYW = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url:'../OPE/jsongetAllYW.action'}),
        reader: new Ext.data.ArrayReader({},[
            {name:'value'}
        ])
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
        readOnly: true,
		applyTo: 'comboYW'
    });
    
    var comboYW1 = new Ext.form.ComboBox({
        store: storeYW,
        emptyText: '请选择接洽业务员',
        allowBlank: false,
        blankText: '必须选择业务员',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'value',
        readOnly: true,
		applyTo: 'comboYW1'
    });
    var comboYW2 = new Ext.form.ComboBox({
        store: storeYW,
        emptyText: '',
        allowBlank: true,
        blankText: '必须选择业务员',
        width: 180,
        mode: 'local',
        triggerAction: 'all',
        valueField: 'value',
        displayField: 'value',
        readOnly: true,
		applyTo: 'comboYW2'
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
<div align="center"><span class="pageTitle">产值统计</span> <s:form
	action="/OPE/getMoneyByNoRange.action" method="get" target="_blank">
	<h2 align="left">按任务单号范围统计产值</h2>
	<table width="90%" border="0" cellspacing="4" cellpadding="4">
		<tr>
			<td width="180" align="right">业务类型：</td>
			<td align="left"><select name="AFType" id="AFType">
				<option value="SK">受控</option>
				<option value="LH">零活</option>
			</select></td>
		</tr>
		<tr>
			<td align="right">任务单号范围：</td>
			<td align="left"><input type="text" name="StartAFNo"
				id="StartAFNo" value="20081301"> — <input type="text"
				name="EndAFNo" id="EndAFNo" value="20081550"></td>
		</tr>
		<tr>
			<td align="right">&nbsp;</td>
			<td align="left"><input type="submit" value="统计"></td>
		</tr>
	</table>
</s:form> <br>
<s:form action="getMoneyByYWandType" namespace="/OPE" method="post"
	target="_blank">
	<h2 align="left">按业务员统计产值</h2>
	<table width="90%" border="0" cellspacing="4" cellpadding="4">
		<tr>
			<td width="180" align="right">业务员：</td>
			<td align="left"><input name="YWName" id="comboYW1" type="text" /></td>
		</tr>
		<tr>
			<td width="180" align="right">业务类型：</td>
			<td align="left"><select name="AFType" id="AFType">
				<option value="SK">受控</option>
				<option value="LH" selected="selected">零活</option>
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
</s:form> <br>
<s:form action="getMoneyByClientandType" namespace="/OPE" method="post"
	target="_blank">
	<h2 align="left">按客户统计产值</h2>
	<table width="90%" border="0" cellspacing="4" cellpadding="4">
		<tr>
			<td width="180" align="right">客户单位名称：</td>
			<td align="left"><input name="YWName" id="comboYW" type="text" />
			<input name="client" id="comboClient" type="text" /></td>
		</tr>
		<tr>
			<td width="180" align="right">业务类型：</td>
			<td align="left"><select name="AFType" id="AFType">
				<option value="SK">受控</option>
				<option value="LH" selected="selected">零活</option>
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
</s:form> <br>
<s:form action="getMoneyByChejian" namespace="/OPE" method="post"
	target="_blank">
	<h2 align="left">按车间统计产值</h2>
	<table width="90%" border="0" cellspacing="4" cellpadding="4">
		<tr>
			<td width="180" align="right">车间：</td>
			<td align="left"><select name="ChejianName" id="ChejianName">
				<option value="">全部车间</option>
				<option value="照排">照排</option>
				<option value="制版车间">制版车间</option>
				<option value="四色">四色</option>
				<option value="双面">双面</option>
				<option value="05">05</option>
				<option value="02">02</option>
				<option value="小制版">小制版</option>
				<option value="装订">装订</option>
				<option value="库房">库房</option>
				<option value="外加工">外加工</option>
				<option value="其它">其它</option>
			</select></td>
		</tr>
		<tr>
			<td width="180" align="right">业务员：</td>
			<td align="left"><input name="YWName" id="comboYW2" type="text" />
			</td>
		</tr>
		<tr>
			<td width="180" align="right">业务类型：</td>
			<td align="left"><select name="AFType" id="AFType">
				<option value="SK">受控</option>
				<option value="LH" selected="selected">零活</option>
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
</s:form> <br>
<br>
<h2>各月任务单号参考范围</h2>
<table width="90%" border="1" cellpadding="6" cellspacing="0"
	bordercolor="#000000">
	<tr>
		<td width="120" align="center">&nbsp;</td>
		<td width="40%">受控任务单(SK)</td>
		<td width="40%">零活任务单(LH)</td>
	</tr>
	<tr>
		<td align="center">08年五月</td>
		<td>20080801 — 20081000</td>
		<td>20080546 — 20080670</td>
	</tr>
	<tr>
		<td align="center">08年六月</td>
		<td>20081001 — 20081300</td>
		<td>20080671 — 20080800</td>
	</tr>
	<tr>
		<td align="center">08年七月</td>
		<td>20081301 — 20081550</td>
		<td>20080801 — 20080910</td>
	</tr>
	<tr>
		<td align="center">08年八月</td>
		<td>20081551 — 2008</td>
		<td>20080911 — 2008</td>
	</tr>
	<tr>
		<td align="center">08年九月</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">08年十月</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">08年十一月</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">08年十二月</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
相关数据来自业务室<br>
</div>
</html>
