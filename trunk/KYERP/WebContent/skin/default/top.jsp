<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table cellspacing="0" cellpadding="0" width="100%" align="center"
	border="0">
	<tr>
		<td>
		<table class="bj-bcf2" cellspacing="0" cellpadding="0" width="100%"
			border="0">
			<tr>
				<td align="left"><img
					src="${pageContext.request.contextPath}/skin/newBlue/images/logo.jpg" /></td>
				<td align="right"><img
					src="${pageContext.request.contextPath}/skin/newBlue/images/logo-end.jpg" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table cellspacing="0" cellpadding="0" width="100%"
	background="${pageContext.request.contextPath}/skin/default/images/dh_di.gif"
	border="0" bgcolor="#E5ECF9">
	<tr>
		<td>
		<div id="bluemenu" class="bluetabs">
		<ul>
			<li><a href="${pageContext.request.contextPath}" title="Home"><span>首页</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/Client/index.action"
				class="head" rel="client"><span>客户管理</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/OPE/index.action"
				class="head" rel="ope"><span>业务管理</span></a></li>
			<li><a href="${pageContext.request.contextPath}/MA/index.action"
				class="head" rel="ma"><span>生产管理</span></a></li>
			<li><a href="${pageContext.request.contextPath}/MR/"
				class="head" rel="mr"><span>物资管理</span></a></li>
			<li><a href="${pageContext.request.contextPath}/FA/"
				class="head" rel="fa"><span>财务管理</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/User/index.action"
				class="head" rel="user"><span>用户管理</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/User/getAllEmployee.action"
				class="head" rel="employee"><span>人员管理</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/User/listRole.action"
				class="head" rel="auth"><span>权限管理</span></a></li>
		</ul>
		</div>
		<!--客户管理子菜单 -->
		<div id="client" class="dropmenudiv_b"><a
			href="${pageContext.request.contextPath}/Client/addCompany.action">增加合作单位</a>
		<a href="${pageContext.request.contextPath}/Client/addClient.action">增加客户/印制员</a>
		<a href="${pageContext.request.contextPath}/Client/index.action">客户资料维护</a></div>
		<!--业务管理子菜单 -->
		<div id="ope" class="dropmenudiv_b"><a
			href="${pageContext.request.contextPath}/OPE/TentYW.jsp">业务员产值统计</a>
		<a href="${pageContext.request.contextPath}/OPE/TentMonth.jsp">产值月报</a>
		<a href="${pageContext.request.contextPath}/OPE/TentClient.jsp">应收款统计</a>
		<a href="${pageContext.request.contextPath}/OPE/ListAF.action">任务单列表</a>
		<a href="${pageContext.request.contextPath}/OPE/addAF.action"
			target="_blank">开生产任务单</a></div>
		<!--生产管理子菜单 -->
		<div id="ma" class="dropmenudiv_b"><a
			href="${pageContext.request.contextPath}/MA/index.action">生产科</a> <a
			href="${pageContext.request.contextPath}/ZP/index.action">照排车间</a> <a
			href="${pageContext.request.contextPath}/PM/index.action">制版车间</a> <a
			href="${pageContext.request.contextPath}/PL/index.action">印刷车间</a> <a
			href="${pageContext.request.contextPath}/BL/index.action">装订车间</a> <a
			href="${pageContext.request.contextPath}/DL/index.action">发行</a></div>
		<!--物资管理子菜单 -->
		<div id="mr" class="dropmenudiv_b"><a href="#">增加新物资</a> <a
			href="#">资料维护</a></div>
		<!--财务管理子菜单 -->
		<div id="fa" class="dropmenudiv_b"><a
			href="${pageContext.request.contextPath}/FA/MoneyIn.action">收款</a> <a
			href="${pageContext.request.contextPath}/FA/MoneyTent.jsp">往来账管理</a></div>
		<!--用户管理子菜单 -->
		<div id="user" class="dropmenudiv_b"><s:if
			test="#session.user==null">
			<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
		</s:if> <s:else>
			<a href="${pageContext.request.contextPath}/User/addUser.jsp">新增用户</a>
			<a href="${pageContext.request.contextPath}/User/index.jsp">个人信息</a>
			<a href="${pageContext.request.contextPath}/User/ListUser.action">浏览用户</a>
			<a href="${pageContext.request.contextPath}/logout.action">注销</a>
		</s:else></div>
		<!--人员管理子菜单 -->
		<div id="employee" class="dropmenudiv_b"><s:if
			test="#session.user==null">
			<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
		</s:if> <s:else>
			<a href="${pageContext.request.contextPath}/User/addEmployee.action">新增人员</a>
			<a
				href="${pageContext.request.contextPath}/User/getAllEmployee.action">人员列表</a>
		</s:else></div>
		<!--权限管理子菜单 -->
		<div id="auth" class="dropmenudiv_b"><a
			href="${pageContext.request.contextPath}/User/addRole.action">新增角色</a>
		<a href="${pageContext.request.contextPath}/User/listRole.action">浏览角色</a>
		<a href="${pageContext.request.contextPath}/User/ListUser.action">浏览用户</a>
		</div>
		<script type="text/javascript">
//SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
tabdropdown.init("bluemenu")
</script></td>
	</tr>
</table>
