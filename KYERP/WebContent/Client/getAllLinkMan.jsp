<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
[
<s:iterator value="#request['linkmanList']" id="linkman" status="st">

["<<s:property value="#st.count" />
	<s:property value="CLmLinkman" />"],["<s:property value="CLmTel" />"],["<s:property
		value="ClientC.CCCom" />"],<br />
</s:iterator>
[" "] ]
