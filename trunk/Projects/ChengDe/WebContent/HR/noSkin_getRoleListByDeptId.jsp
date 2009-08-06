<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<select id="roleList" name="roles[0].id">
	<option>---</option>
	<s:iterator value="#request['roleList']" status="st">
		<option value="<s:property value="id"/>"><s:property
			value="rname" /></option>
	</s:iterator>
</select>