<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
<s:set value="#request['AFQualityProblem']" name="afqp" id="afqp" />
<s:set value="#session['user']" name="user" id="user" />
<!DOCTYPE HTML PUBLIC "-//W8C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>质量问题记录</title>
<s:if test="#afqp.id > 0">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/Library/js/dhtmlwindow/windowfiles/dhtmlwindow.css"
		type="text/css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/Library/js/dhtmlwindow/windowfiles/dhtmlwindow.js"></script>
	<script language="javascript"> 
function addComponent() 
{ 
        var uploadHTML = document.createElement( "<input type='file'  name='upload'/>"); 
        document.getElementById("files").appendChild(uploadHTML); 
        uploadHTML = document.createElement( "<p/>"); 
        document.getElementById("files").appendChild(uploadHTML); 
} 
</script>
</s:if>
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<div id="MainForm">
<form action="QualityProblem_save.action" class="cssform" method="post">
<input type="hidden" name="afId" value="<s:property value="#af.afId" />" />
<input type="hidden" name=afqp.id
	value="<s:property value="#afqp.id" />" />
<p><label>车间</label> <select name="afqp.workshop">
	<s:iterator value="#request['DeptTree']" status="st">
		<option value="<s:property value="id"/>"
			<s:if test="#afqp.workshop == id" >selected="selected"</s:if>><s:property
			value="name" /></option>
	</s:iterator>
</select></p>
<p><label for="moneyFact">责任人</label> <input type="text"
	style="color: red;" name="afqp.personLiable" id="personLiable"
	value='<s:property value="#afqp.personLiable" />' /></p>
<p><label>问题描述</label> <textarea name="afqp.description" cols="58"
	rows="3"><s:property value="#afqp.description" /></textarea></p>
<p><label>原因分析</label> <textarea name="afqp.reason" cols="58"
	rows="5"><s:property value="#afqp.reason" /></textarea></p>
<p><label>解决办法</label> <textarea name="afqp.solution" cols="58"
	rows="5"><s:property value="#afqp.solution" /></textarea></p>
<p><label>损失</label> <textarea name="afqp.loss" cols="58" rows="5"><s:property
	value="#afqp.loss" /></textarea></p>
<p><label>处置</label> <textarea name="afqp.disposal" cols="58"
	rows="8"><s:property value="#afqp.disposal" /></textarea></p>
<p><label>当事人意见</label> <textarea name="afqp.personOpinion"
	cols="58" rows="3"><s:property value="#afqp.personOpinion" /></textarea></p>
<p><label>附件</label> <s:if test="#afqp.id > 0">
	<a href="#"
		onClick="divwin=dhtmlwindow.open('divbox', 'div', 'uploaddiv', '上传附件', 'width=450px,height=300px,left=200px,top=150px,resize=1,scrolling=1'); return false"><b>上传附件</b></a>
</s:if><s:else>先保存，然后才能上传附件</s:else>
<div style="left: 150px;"><table width="90%">
	<s:iterator value="#afqp.attachments" status="st">
		<tr>
			<td><a
				href='/uploadData/QualityProblemAttactent/<s:property value="fileName" />'
				target="_blank"><img style="width: 296px;"
					src="/uploadData/QualityProblemAttactent/<s:property value="fileName" />" /></a></td>
			<td><a
				href='/uploadData/QualityProblemAttactent/<s:property value="fileName" />'
				target="_blank"><s:property value="name" /></a>
			&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><a
				href="delQualityProblemAttachment.action?afId=<s:property value="#af.afId" />&afQPId=<s:property value="#afqp.id" />&afQPAttachmentId=<s:property value="id" />&fileName=<s:property value="fileName" />">删除</a><br />
			</td>
		</tr>
	</s:iterator>
</table>
</div>
</p>
<p><label></label><input type="submit" value="保  存" /></p>
</form>
</div>
<s:if test="#afqp.id > 0">
	<div id="uploaddiv" style="display:none;padding: 8px;">
	<p><label>上传附件</label>
	<form onsubmit="return true;"
		action="QualityProblemInput_uploadFile.action" method="post"
		enctype="multipart/form-data"><input type="hidden" name="afId"
		value="<s:property value="#af.afId" />" /> <input type="hidden"
		name="afQPId" value="<s:property value="#afqp.id" />" /> <input
		type="button" onclick="addComponent();" value="添加文件" /> 上传附件前，请先保存
	<p /><span id="files"> <input type='file' name='upload' />
	<p />
	</span> <input type="submit" value="上  传" />
	</form>
	</div>
</s:if>
</body>
</html>
