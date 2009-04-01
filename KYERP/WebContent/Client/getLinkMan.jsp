<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['Client']" name="client" />
[
<s:iterator value="#client.ClientLm">

["<s:property value="CLmLinkman" />"],

</s:iterator>
[" "] ]
