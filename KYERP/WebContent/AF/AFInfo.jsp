<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
<s:set value="#session['userSystemFunctionList']" name="userSystemFunctionList"
	id="userSystemFunctionList" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>清华园胶印厂生产任务单-<s:property value="#af.iso" /><s:property
	value="#af.afNo" /></title>
<script src="${pageContext.request.contextPath}/Library/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/function.js"></script>
    <link rel="stylesheet" id='skin' type="text/css" href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<link href="<%=request.getContextPath()%>/Library/css/AFinfo.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
function updateFilmPlace(afEId,form) {
	var divNode = document.getElementById("div" + afEId);
	var filmPlaceVal = document.getElementById("filmPlace"+afEId).value;
	$.get("noSkin_filmPlaceInput.action?afEId="+afEId+"&filmPlace="+filmPlaceVal, null, function(data) {
		//divNode.innerText = filmPlaceVal;
		$('#div'+afEId+'').html("<b>"+filmPlaceVal+"</b>");
		ymPrompt.succeedInfo({message:"软片存放位置修改成功！",width:250,height:180,handler:null});
		$('#'+form+'').hide("slow");
	});

};
function show(id){
	var divform = document.getElementById(id);
	$('#'+id+'').show("slow");
};
</script>
</head>
<body>
<div id="AFmenu" class="noprint">
<ul>
	<li><a target="_blank"
		href='<%=request.getContextPath()%>/AF/AFInfo_print.action?afId=<s:property value="#af.afId" />'>打印任务单</a></li>
	<s:if
		test="#user.userType.equals('Admin') or 'OM-AF-ZK' in #userSystemFunctionList">
		<li><a target="_blank"
			href='<%=request.getContextPath()%>/OPE/ZK.action?AFNo=<s:property value="#af.iso" /><s:property value="#af.afNo" />'>转开任务单</a></li>
	</s:if>
	<s:if test="#af.moneyStatus == 0">
		<s:if test="#user.userType.equals('Admin') or 'FM-AF-ViewMoneyFact' in #userSystemFunctionList">
			<li><a
				href='<%=request.getContextPath()%>/OPE/CalAF.action?afId=<s:property value="#af.afId" />'
				target="_blank">实收金额:<s:property value="#af.moneyFact" />元,已结清!</a></li>
		</s:if>
	</s:if>
	<s:else>
		
			<s:if test="#af.moneyShould > 0">
				<s:if test="#user.userType.equals('Admin') or 'FM-AF-ViewMoneyShould' in #userSystemFunctionList">
					<li><a
						href='<%=request.getContextPath()%>/OPE/CalAF.action?afId=<s:property value="#af.afId" />'
						target="_blank">应收金额:<s:property value="#af.moneyShould" />元</a></li>				
				</s:if>
			</s:if>
			<s:else>
				<s:if test="#user.userType.equals('Admin') or 'OM-AF-CalAF' in #userSystemFunctionList">
					<li><a href='<%=request.getContextPath()%>/OPE/CalAF_input.action?afId=<s:property value="#af.afId" />' >计价</a></li>
				</s:if>
			</s:else>
		<s:if test="#af.moneyFact > 0">
					<s:if
						test="#user.userType.equals('Admin') or 'OM-AF-MoneyInput' in #userSystemFunctionList">
						<li><a
							href='<%=request.getContextPath()%>/OPE/CalAF_MoneyInput.action?afId=<s:property value="#af.afId" />'
							target="_blank">实收金额:<s:property value="#af.moneyFact" />元,未结清!</a></li>
					</s:if>
				</s:if>
				<s:else>
					<s:if test="#user.userType.equals('Admin') or 'OM-AF-MoneyInput' in #userSystemFunctionList">
						<li><a
							href='<%=request.getContextPath()%>/OPE/CalAF_MoneyInput.action?afId=<s:property value="#af.afId" />' >收款</a></li>
					</s:if>
				</s:else>
		<s:if test="#user.userType.equals('Admin') or #af.afStatus != 0">
			<s:if
				test="#user.userType.equals('Admin') or 'OM-AF-Edit' in #userSystemFunctionList">
				<li><a
					href='<%=request.getContextPath()%>/OPE/editAF.action?afId=<s:property value="#af.afId" />' >修改任务单</a></li>
				<li><a
					href='<%=request.getContextPath()%>/OPE/addElement.action?afId=<s:property value="#af.afId" />' >任务单-添加元件</a></li>
							<li><a
					href='<%=request.getContextPath()%>/OPE/addDispose.action?afId=<s:property value="#af.afId" />' >任务单-添加特殊加工项目</a></li>
			</s:if>
		</s:if>
		<s:if test="#user.userType.equals('Admin') or 'OM-AF-Del' in #userSystemFunctionList">
			<li><a
				href='<%=request.getContextPath()%>/OPE/delAF.action?afId=<s:property value="#af.afId" />'
				target="_blank" onClick="return checkit('删除后不能恢复,您确认要删除这个任务单吗?')">删除</a></li>
		</s:if>
	</s:else>
	<s:if test="#user.userType.equals('Admin') or 'QC-QualityProblemInput' in #userSystemFunctionList">
			<li><a
				href='<%=request.getContextPath()%>/QC/QualityProblemInput.action?afId=<s:property value="#af.afId" />'
				target="_blank" >质量问题记录</a></li>
		</s:if>
</ul>
</div>
<div id="AFInfo">
<div class="height30"></div>
<div style="position: relative; left: 0px; top: 0px"><s:if
	test="#af.timeRank==1">
	<div style="position: absolute; left: 30px; top: 10px"><img
		src="<%=request.getContextPath()%>/Library/images/ji.gif"></div>
</s:if> <s:if test="#af.ourbinding==1">
	<div style="position: absolute; left: 0px; top: 40px"><img
		src="<%=request.getContextPath()%>/Library/images/zhuang.gif"></div>
</s:if>
<table width="750" border="0" align="center" cellpadding="3"
	cellspacing="0">
	<tr>
		<td height="50" align="right" valign="middle"><img
			src="<%=request.getContextPath() %>/Library/images/title_rwd.jpg"></td>
		<td width="185" rowspan="2" align="center" valign="bottom"><img
			src='<%=request.getContextPath() %>/barcode?height=10&msg=<s:property value="#af.iso" /><s:property value="#af.afNo" />'
			width=160px /></td>
	</tr>
	<tr>
		<td align="left">
		<div style="margin-left: 20px; float: left;"><span class="item"><s:if
			test="#af.iso.equals('SK')"> QYYT-D-7.5.1-01</s:if></span></div>
		<div style="margin-right: 50px; float: right;"><span
			class="text"><s:date name="#af.ad" nice="false" /></span></div>
		</td>
	</tr>
</table>
<table width="750" border="1" align="center" cellpadding="3"
	cellspacing="0" bordercolor="#000000" bgcolor="#000000"
	style="border-collapse: collapse">
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">委印单位</span></td>
		<td colspan="5" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.client" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">印单号</span></td>
		<td colspan="2" bgcolor="#FFFFFF" width="18%"><span class="text"><s:property
			value="#af.pcAf" /></span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">印品名称</span></td>
		<td colspan="5" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.presswork" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">经手人</span></td>
		<td colspan="2" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.linkman" /></span></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">丛书名称</span></td>
		<td colspan="5" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.seriesName" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">电话</span></td>
		<td colspan="2" bgcolor="#FFFFFF" class="text"><s:property
			value="#af.tel" /></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">书号</span></td>
		<td colspan="3" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.isbn" /></span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="item">版次</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.edition" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">印数</span></td>
		<td colspan="2" bgcolor="#FFFFFF" class="text"><s:property
			value="#af.amount" /></td>
	</tr>
	<tr>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">成品尺寸</span></td>
		<td bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.fps" /></span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">开本</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.format" /></span></td>
		<td width="8%" align="center" bgcolor="#FFFFFF"><span
			class="item">订法</span></td>
		<td align="center" bgcolor="#FFFFFF"><span class="text"><s:property
			value="#af.bm" /></span></td>
		<td width="10%" align="center" bgcolor="#FFFFFF"><span
			class="item">总印张</span></td>
		<td colspan="2" bgcolor="#FFFFFF"><span class="text"> <s:set
			name="teps" value="0" /> <s:iterator value="#af.AfElement">
			<s:if test="EPs">
				<s:set name="teps" value="#teps=#teps+EPs" />
			</s:if>
		</s:iterator> <s:property value="#teps" /> </span></td>
	</tr>
	<tr>
		<td colspan="9" align="center" bgcolor="#FFFFFF"><s:iterator
			value="#af.AfElement">
			<table width="100%" border="1" align="center" cellpadding="3"
				cellspacing="0" bordercolor="#000000" bgcolor="#000000"
				style="border-collapse: collapse">
				<tr>
					<td width="3%" rowspan="4" align="center" bgcolor="#FFFFFF"><span
						class="etype"> <s:if test="EType.equals('BB')">正<br />
						<br />
                  文</s:if> <s:if test="EType.equals('Cover')">封<br />
						<br />
                  面</s:if> <s:if test="EType.equals('CI')">插<br />
						<br />
                  页</s:if> </span></td>
					<td width="10%" align="center" bgcolor="#FFFFFF"><span
						class="item">机型</span></td>
					<td width="12%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="EMachine" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">印张</span></td>
					<td width="10%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="EPs" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">墨色</span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="EColorFrontN" />+<s:property
						value="EColorBackN" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">用纸</span></td>
					<td align="center" bgcolor="#FFFFFF"><span class="text">
					<s:property value="EPaper" /> </span></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFFFFF"><span class="item">用纸规格</span></td>
					<td width="12%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="ESs" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">开本</span></td>
					<td width="10%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="EFormat" /> </span></td>
					<td align="center" bgcolor="#FFFFFF"><span class="item">P数</span></td>
					<td width="6%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="EP" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">来源</span></td>
					<td align="center" bgcolor="#FFFFFF"><span class="text">
					<s:property value="EPaperFrom" /> </span></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFFFFF"><span class="item">制版日期</span></td>
					<td width="12%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:date name="EPlanPm" format="MM月dd日"
						nice="false" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">正色</span></td>
					<td width="10%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="EColorFront" /> </span></td>
					<td align="center" bgcolor="#FFFFFF"><span class="item">印版</span></td>
					<td width="6%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="EPlateType" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">令数</span></td>
					<td align="center" bgcolor="#FFFFFF"><span class="text">
					<s:property value="EReam" /> + <s:property value="EOvers" /> </span></td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFFFFF"><span class="item">印刷日期</span></td>
					<td width="12%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:date name="EPlanPress" format="MM月dd日"
						nice="false" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">背色</span></td>
					<td width="10%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="EColorBack" /> </span></td>
					<td align="center" bgcolor="#FFFFFF"><span class="item">版数</span></td>
					<td width="6%" align="center" bgcolor="#FFFFFF"><span
						class="text"> <s:property value="EPlateAmount" /> </span></td>
					<td width="8%" align="center" bgcolor="#FFFFFF"><span
						class="item">总计</span></td>
					<td align="center" bgcolor="#FFFFFF"><span class="text"><SCRIPT
						type="text/javascript">document.write(fixfloat(<s:property value="EReam+EOvers" />,3));</SCRIPT></span></td>
				</tr>
			</table>
		</s:iterator></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">其它项目</span></td>
		<td colspan="8" align="center" bgcolor="#FFFFFF">
		<table width="100%" border="1" align="center" cellpadding="3"
			cellspacing="0" bordercolor="#000000" bgcolor="#000000"
			style="border-collapse: collapse">
			<tr>
				<td align="center" bgcolor="#FFFFFF"><span class="item">工艺类型</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">工艺名称</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">数量</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">工厂</span></td>
				<td align="center" bgcolor="#FFFFFF"><span class="item">备注</span></td>
			</tr>
			<s:iterator value="#af.AfDispose">
				<tr>
					<td bgcolor="#FFFFFF"><span class="text"> <s:property
						value="afEType" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <s:property
						value="afDItem" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <s:property
						value="afDAmount" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <s:property
						value="afDFactory" /> </span></td>
					<td bgcolor="#FFFFFF"><span class="text"> <s:property
						value="afDRemark" /> </span></td>
				</tr>
			</s:iterator>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送样书时间</span></td>
		<td colspan="2" align="center" bgcolor="#FFFFFF"><span
			class="text"> <s:date name="#af.planSendSample"
			format="yyyy年MM月dd日" nice="false" /> </span></td>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">备注</span></td>
		<td colspan="5" align="left" bgcolor="#FFFFFF"><span class="text">
		<s:property value="#af.remarkSendSample" /> </span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">送货时间</span></td>
		<td colspan="2" align="center" bgcolor="#FFFFFF"><span
			class="text"> <s:date name="#af.planDeliver"
			format="yyyy年MM月dd日" nice="false" /> </span></td>
		<td width="12%" align="center" bgcolor="#FFFFFF"><span
			class="item">送货地点</span></td>
		<td colspan="5" align="left" bgcolor="#FFFFFF"><span class="text">
		<s:property value="#af.deliverAddress" /> </span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">印装次序
		</span></td>
		<td colspan="8" align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.plateMakeOrder" /> </span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item">质量要求</span></td>
		<td colspan="8" align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.oq" /> </span></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF"><span class="item"><br />
		备 注<br />
		<br />
		</span></td>
		<td colspan="8" align="left" valign="top" bgcolor="#FFFFFF"><span
			class="text"> <s:property value="#af.remark" /> </span></td>
	</tr>
</table>
<table width="750" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td><span class="bottomtext">接洽人: <s:property
			value="#af.cp" /></span></td>
		<td><span class="bottomtext">开单人: <s:property
			value="#af.fmp" /></span></td>
		<td><span class="bottomtext">审核人: <s:property
			value="#af.auditer" /></span></td>
		<td><span class="bottomtext">审核时间: <s:date name="#af.auditTime" format="yyyy-MM-dd HH:MM:SS" nice="false" /></span></td>
	</tr>
</table>
</div>
</div>
<s:if test="#user.userType.equals('Admin') or 'PM-AF-FilmInput' in #userSystemFunctionList">
<table width="750" border="1" align="center" cellpadding="6" cellspacing="0" bordercolor="#000000" style="BORDER-COLLAPSE: collapse;">
  <tr>
    <td colspan="3" bgcolor="#FFFFCC">软片存放记录</td>
  </tr>
  <tr>
    <td width="80"><div align="center">类型</div></td>
    <td>位置编号</td>
    <td width="80"><div align="center">修改</div></td>
  </tr>
  <s:iterator value="#af.AfElement">
  <tr>
    <td><s:if test="EType.equals('BB')">正文</s:if>
    <s:if test="EType.equals('Cover')">封面</s:if>
    <s:if test="EType.equals('CI')">插页</s:if></td>
    <td><div id="div<s:property value='afEId' />"><s:property value="filmPlace" /></div>
    <div id="form<s:property value='afEId' />" style="display: none">
    <input type="text" id="filmPlace<s:property value='afEId' />" name="filmPlace" value="<s:property value='filmPlace' />" />
    <input type="button" value="确定" onclick="updateFilmPlace('<s:property value='afEId' />','form<s:property value='afEId' />')"/></div>
    </td>
    <td align="center">
    <input type="button" value="修改" onclick="show('form<s:property value='afEId' />');" />
    </td>
  </tr>
  </s:iterator>
</table>
</s:if>
<br />
<s:if test="#user.userType.equals('Admin') or 'QC-ViewAll' in #userSystemFunctionList">
<table width="750" border="1" align="center" cellpadding="6" cellspacing="0" bordercolor="#000000" style="BORDER-COLLAPSE: collapse;">
  <tr>
    <td colspan="3" bgcolor="#FFFFCC">质量问题记录</td>
  </tr>
  <tr>
    <td width="80"><div align="center">责任人</div></td>
    <td>问题描述</td>
    <td width="80"><div align="center">修改</div></td>
  </tr>
  <s:iterator value="#af.afQualityProblem">
  <tr>
    <td><div align="center"><s:property value="personLiable" /></div></td>
    <td><s:property value="description" /></td>
    <td align="center"><a href="${pageContext.request.contextPath}/QC/QualityProblemInfo.action?afId=<s:property value="#af.afId" />&id=<s:property value="id" />"
				target="_blank">修改</a> 
				<a href="${pageContext.request.contextPath}/QC/delQualityProblem.action?afId=<s:property value="#af.afId" />&id=<s:property value="id" />">删除</a></td>
  </tr>
  </s:iterator>
</table>
</s:if>
</body>
</html>