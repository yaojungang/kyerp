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
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<div id="MainForm">
<form action="QualityProblem_save.action" class="cssform"
	method="post"><input type="hidden" name="afId"
	value="<s:property value="#af.afId" />" />
<input type="hidden" name=afqp.id
	value="<s:property value="#afqp.id" />" />
<p><label>车间</label> 	
	<select name="afqp.workshop">
				<s:iterator value="#request['DeptTree']" status="st">
					<option value="<s:property value="id"/>"
						<s:if test="#afqp.workshop == id" >selected="selected"</s:if>><s:property
						value="name" /></option>
				</s:iterator>
			</select>
</p>

<p>
  <label for="moneyFact">责任人</label> <input type="text"
	style="color: red;" name="afqp.personLiable" id="personLiable"
	value='<s:property value="#afqp.personLiable" />' />
</p>

<p><label>问题描述</label> <textarea
	name="afqp.description" cols="58" rows="8"><s:property
	value="#afqp.description" /></textarea></p>
<p><label>原因分析</label> <textarea
	name="afqp.reason" cols="58" rows="8"><s:property
	value="#afqp.reason" /></textarea></p>
<p><label>解决办法</label> <textarea
	name="afqp.solution" cols="58" rows="8"><s:property
	value="#afqp.solution" /></textarea></p>
<p><label>损失</label> <textarea
	name="afqp.loss" cols="58" rows="8"><s:property
	value="#afqp.loss" /></textarea></p>
<p><label>处置</label> <textarea
	name="afqp.disposal" cols="58" rows="8"><s:property
	value="#afqp.disposal" /></textarea></p>
 <p><label>当事人意见</label> <textarea
	name="afqp.personOpinion" cols="58" rows="8"><s:property
	value="#afqp.personOpinion" /></textarea></p>
<div style="margin-left: 150px;"><input type="submit" value="提交" />
<input type="reset" value="重置" /></div>
</form>
</div>
</body>
</html>
