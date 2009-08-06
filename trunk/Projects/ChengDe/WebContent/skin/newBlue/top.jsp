<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#session['userSystemFunctionList']" name="userSystemFunctionList" id="userSystemFunctionList" />
<s:set value="#session['user']" name="user" id="user" />
<table class="bj-bcf2" cellspacing="0" cellpadding="0" width="100%"
			border="0">
			<tr>
				<td align="left"><img
					src="${pageContext.request.contextPath}/skin/newBlue/images/logo.jpg" /></td>
				<td align="right"><img
					src="${pageContext.request.contextPath}/skin/newBlue/images/logo-end.jpg" /></td>
			</tr>
		</table>
<table cellspacing="0" cellpadding="0" width="100%"
	background="${pageContext.request.contextPath}/skin/newBlue/images/menuBackground.gif"
	border="0" bgcolor="#E5ECF9">
	<tr>
		<td align="left" valign="middle">
		<div id="bluemenu" class="bluetabs">
		<ul>
			<s:if test="#session.user==null">
				<li><a href="${pageContext.request.contextPath}" title="Home"><span>首页</span></a></li>
			</s:if>
			<s:else>
				<li><a href="${pageContext.request.contextPath}/" title="Home"><span>首页</span></a></li>
				<s:if test="#user.userType.equals('Admin') or 'Plate-Basic' in #userSystemFunctionList">
					<li><a href="${pageContext.request.contextPath}/Plate/PlateAdmin.action" class="head" rel="plate"><span>印版库管理</span></a></li>
				</s:if>
				<s:if
					test="#user.userType.equals('Admin') or 'HR-Basic' in #userSystemFunctionList">
					<li><a
						href="${pageContext.request.contextPath}/HR/HRAdmin.action"
						class="head" rel="employee"><span>人事管理</span></a></li></s:if>
					<s:if test="#user.userType.equals('Admin') or 'System-Basic' in #userSystemFunctionList">
					<li><a href="${pageContext.request.contextPath}/System/DeptAdmin.action"
						class="head" rel="user"><span>系统管理</span></a></li>
				</s:if>
			</s:else>
		</ul>
		</div>
		<s:if test="#session.user==null"></s:if> <s:else>
			<s:if test="#user.userType.equals('Admin') or 'Plate-Basic' in #userSystemFunctionList">
				<!--人员管理子菜单 -->
				<div id="plate" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/Plate/PlateAdmin.action">印版列表</a>
				<a href="${pageContext.request.contextPath}/Plate/addPlate.action">增加新印版</a>
				<a href="${pageContext.request.contextPath}/Plate/ExpPlateList.action">过期印版</a>
				</div></s:if>
			<s:if test="#user.userType.equals('Admin') or 'HR-Basic' in #userSystemFunctionList">
				<!--人员管理子菜单 -->
				<div id="employee" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/HR/addEmployee.action">增加人员</a>
				<a
					href="${pageContext.request.contextPath}/HR/HRAdmin.action?workStatus=0">在岗人员</a>
				<a
					href="${pageContext.request.contextPath}/HR/HRAdmin.action?workStatus=1">试用期人员</a>
				<a
					href="${pageContext.request.contextPath}/HR/HRAdmin.action?workStatus=2">申请离职人员</a>
				<a
					href="${pageContext.request.contextPath}/HR/HRAdmin.action?workStatus=100">退休人员</a>
				<a
					href="${pageContext.request.contextPath}/HR/HRAdmin.action?workStatus=101">离职人员</a>
				</div></s:if>
			<s:if test="#user.userType.equals('Admin') or 'System-Basic' in #userSystemFunctionList">
				<!--系统管理子菜单 -->
				<div id="user" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/System/DeptAdmin.action">部门管理</a>
				<a href="${pageContext.request.contextPath}/System/RoleAdmin.action">角色管理</a>
				<a
					href="${pageContext.request.contextPath}/System/SystemFunctionsAdmin.action">功能设置</a>
				<s:if test="#user.userType.equals('Admin') or 'System-Admin' in #userSystemFunctionList"><a href="${pageContext.request.contextPath}/System/SystemAdmin.action">系统设置</a></s:if>
				</div>
			</s:if>
		</s:else> <script type="text/javascript">
	//SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
	tabdropdown.init("bluemenu")
</script></td>
	</tr>
</table>
