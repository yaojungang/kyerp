<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<select id="peopleList" onChange="document.getElementById('operators').value=document.getElementById('operators').value+this.options[this.selectedIndex].value">
	<option>---</option>
	<s:iterator value="#request['peopleList']" status="st">
		<option value="<s:property value="realname"/>"><s:property
			value="realname" /></option>
	</s:iterator>
</select>