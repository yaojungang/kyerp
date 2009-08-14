<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<select id="peopleList" name="peoples[0].id">
	<option>---</option>
	<s:iterator value="#request['peopleList']" status="st">
		<option value="<s:property value="realname"/>"><s:property
			value="realname" /></option>
	</s:iterator>
</select>