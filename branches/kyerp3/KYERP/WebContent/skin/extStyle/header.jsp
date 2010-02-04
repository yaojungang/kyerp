<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/skin/newBlue/DropDownTab/bluetabs.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/skin/newBlue/DropDownTab/dropdowntabs.js"></script>
</head>
<s:set value="#session['privilegesList']" name="privilegesList"
	id="privilegesList" />
<s:set value="#session['user']" name="user" id="user" />
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
	background="${pageContext.request.contextPath}/skin/newBlue/images/menuBackground.gif"
	border="0" bgcolor="#E5ECF9">
	<tr>
		<td align="left" valign="middle">
		<div id="bluemenu" class="bluetabs">
		<ul>
			<s:if test="#session.user==null">
				<li><a href="${pageContext.request.contextPath}" title="Home"><span>首页</span></a></li>
				<li><a href="${pageContext.request.contextPath}"
					title="jianjie"><span>系统简介</span></a></li>
				<li><a href="${pageContext.request.contextPath}"
					title="Contact"><span>帮助</span></a></li>
			</s:if>
			<s:else>
				<li><a href="${pageContext.request.contextPath}/" title="Home"><span>首页</span></a></li>
				<s:if
					test="#user.userType.equals('Admin') or 'OPEUser' in #privilegesList">
					<li><a
						href="${pageContext.request.contextPath}/Client/index.action"
						class="head" rel="client"><span>客户管理</span></a></li>
					<li><a
						href="${pageContext.request.contextPath}/OPE/index.action"
						class="head" rel="ope"><span>业务管理</span></a></li>
				</s:if>
				<s:if
					test="#user.userType.equals('Admin') or 'MAUser' in #privilegesList">
					<li><a
						href="${pageContext.request.contextPath}/MA/index.action"
						class="head" rel="ma"><span>生产管理</span></a></li>
					<li><a href="${pageContext.request.contextPath}/MR/index.jsp"
						class="head" rel="mr"><span>物资管理</span></a></li>
				</s:if>
				<s:if
					test="#user.userType.equals('Admin') or 'FAUser' in #privilegesList">
					<li><a
						href="${pageContext.request.contextPath}/FA/index.action"
						class="head" rel="fa"><span>财务管理</span></a></li>
				</s:if>
				<s:if
					test="#user.userType.equals('Admin') or 'UserAdmin' in #privilegesList">
					<li><a
						href="${pageContext.request.contextPath}/User/getAllEmployee.action"
						class="head" rel="employee"><span>人员管理</span></a></li>
					<li><a
						href="${pageContext.request.contextPath}/User/index.action"
						class="head" rel="user"><span>用户管理</span></a></li>
				</s:if>
			</s:else>
		</ul>
		</div>
		<s:if test="#session.user==null"></s:if> <s:else>
			<s:if
				test="#user.userType.equals('Admin') or 'OPEUser' in #privilegesList">
				<!--客户管理子菜单 -->
				<div id="client" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/Client/index.action">客户列表</a>
				<a href="${pageContext.request.contextPath}/Client/addClient.action">增加客户</a>
				<a
					href="${pageContext.request.contextPath}/Client/tjClientHK.action">统计客户回款情况</a>
				<a href="${pageContext.request.contextPath}/Client/index.action">客户资料维护</a></div>
				<!--业务管理子菜单 -->
				<div id="ope" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/OPE/TentAF.action">产值统计</a>
				<a href="${pageContext.request.contextPath}/OPE/TentMonth.jsp">产值月报</a>
				<a href="${pageContext.request.contextPath}/OPE/TentClient.jsp">回款统计</a>
				<a href="${pageContext.request.contextPath}/OPE/ListAF.action">任务单列表</a>
				<a href="${pageContext.request.contextPath}/OPE/addAF.action"
					target="_blank">开生产任务单</a></div>
			</s:if>
			<s:if
				test="#user.userType.equals('Admin') or 'MAUser' in #privilegesList">
				<!--生产管理子菜单 -->
				<div id="ma" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/MA/index.action">生产科</a> <a
					href="${pageContext.request.contextPath}/ZP/index.action">照排车间</a>
				<a href="${pageContext.request.contextPath}/PM/index.action">制版车间</a>
				<a href="${pageContext.request.contextPath}/PL/index.action">印刷车间</a>
				<a href="${pageContext.request.contextPath}/BL/index.action">装订车间</a>
				<a href="${pageContext.request.contextPath}/DL/index.action">发行</a></div>
				<!--物资管理子菜单 -->
				<div id="mr" class="dropmenudiv_b"><a href="#">增加新物资</a> <a
					href="#">资料维护</a></div>
			</s:if>
			<s:if
				test="#user.userType.equals('Admin') or 'FAUser' in #privilegesList">
				<!--财务管理子菜单 -->
				<div id="fa" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/FA/MoneyIn.action">收款</a>
				<a href="${pageContext.request.contextPath}/FA/JZKP_input.jsp">结账开票</a>
				<a
					href="${pageContext.request.contextPath}/FA/MoneyIn_BAT_input.action">批量收款</a>
				<a href="${pageContext.request.contextPath}/FA/MoneyTent.jsp">往来账管理</a></div>
			</s:if>
			<s:if
				test="#user.userType.equals('Admin') or 'UserAdmin' in #privilegesList">
				<!--人员管理子菜单 -->
				<div id="employee" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/User/addEmployee.action">增加人员</a>
				<a
					href="${pageContext.request.contextPath}/User/getAllEmployee.action">人员列表</a>
				</div>
				<!--用户管理子菜单 -->
				<div id="user" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/User/addUser.jsp">新增用户</a>
				<a href="${pageContext.request.contextPath}/User/index.jsp">个人信息</a>
				<a href="${pageContext.request.contextPath}/User/ListUser.action">浏览用户</a>
				<a href="${pageContext.request.contextPath}/User/addRole.action">新增角色</a>
				<a href="${pageContext.request.contextPath}/User/listRole.action">浏览角色</a>
				<a href="${pageContext.request.contextPath}/logout.action">注销</a></div>
			</s:if>
		</s:else> <script type="text/javascript">
//SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
tabdropdown.init("bluemenu")
</script></td>
	</tr>
</table>
