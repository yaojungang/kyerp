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
				<li><a href="${pageContext.request.contextPath}/" title="Home"><span>首页</span></a></li>
				<li><a href="${pageContext.request.contextPath}"
					title="jianjie"><span>系统简介</span></a></li>
				<li><a href="${pageContext.request.contextPath}"
					title="Contact"><span>帮助</span></a></li>
			</s:if>
			<s:else>
				<li><a href="${pageContext.request.contextPath}/" title="Home"><span>首页</span></a></li>
				<s:if
					test="#user.userType.equals('Admin') or 'CM-Basic' in #userSystemFunctionList">
					<li><a
						href="${pageContext.request.contextPath}/Client/index.action"
						class="head" rel="client"><span>客户管理</span></a></li>
				</s:if>
				<s:if
					test="#user.userType.equals('Admin') or 'OM-Basic' in #userSystemFunctionList">
					<li><a
						href="${pageContext.request.contextPath}/OPE/index.action"
						class="head" rel="ope"><span>业务管理</span></a></li>
				</s:if>
				<s:if
					test="#user.userType.equals('Admin') or 'PM-Basic' in #userSystemFunctionList">
					<li><a
						href="${pageContext.request.contextPath}/MA/index.action"
						class="head" rel="ma"><span>生产管理</span></a></li>
				</s:if>
				<s:if
					test="#user.userType.equals('Admin') or 'QC-Basic' in #userSystemFunctionList">
					<li><a href="#" class="head" rel="qc"><span>质量管理</span></a></li>
				</s:if>
				<s:if
					test="#user.userType.equals('Admin') or 'MM-Basic' in #userSystemFunctionList">
					<li><a
						href="${pageContext.request.contextPath}/WareHouse/index.action"
						class="head" rel="WareHouse"><span>物料管理</span></a></li>
				</s:if>
				<s:if
					test="#user.userType.equals('Admin') or 'FM-Basic' in #userSystemFunctionList">
					<li><a
						href="${pageContext.request.contextPath}/FA/index.action"
						class="head" rel="fa"><span>财务管理</span></a></li>
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
				<li><a href="#" rel="web">网络资源</a></li>
				
			</s:else>
		</ul>
		</div>
		<s:if test="#session.user==null"></s:if> <s:else>
			<s:if test="#user.userType.equals('Admin') or 'CM-Basic' in #userSystemFunctionList">
				<!--客户管理子菜单 -->
				<div id="client" class="dropmenudiv_b">
				<a href="${pageContext.request.contextPath}/Client/index.action">客户列表</a>
				<a href="${pageContext.request.contextPath}/Client/addClient.action">增加客户</a>
				<a href="${pageContext.request.contextPath}/Client/tjClientHK.action">统计客户回款情况</a>
				<a href="${pageContext.request.contextPath}/Client/index.action">客户资料维护</a></div>
				</s:if>
			<s:if test="#user.userType.equals('Admin') or 'OM-Basic' in #userSystemFunctionList">
				<!--业务管理子菜单 -->
				<div id="ope" class="dropmenudiv_b">
				<a href="${pageContext.request.contextPath}/OPE/TentAF.action">产值统计</a>
				<a href="${pageContext.request.contextPath}/OPE/TentMonth.jsp">产值月报</a>
				<a href="${pageContext.request.contextPath}/OPE/TentClient.jsp">回款统计</a>
				<a href="${pageContext.request.contextPath}/OPE/ListAF.action">任务单列表</a>
				<a href="${pageContext.request.contextPath}/OPE/newAF.action">新建任务单</a>
				<a href="${pageContext.request.contextPath}/OPE/getMyAF.action">我的任务单</a></div>
			</s:if>
			<s:if test="#user.userType.equals('Admin') or 'PM-Basic' in #userSystemFunctionList">
				<!--生产管理子菜单 -->
				<div id="ma" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/MA/index.action">生产科</a> <a
					href="${pageContext.request.contextPath}/ZP/index.action">照排车间</a>
				<a href="${pageContext.request.contextPath}/PM/index.action">制版车间</a>
				<a href="${pageContext.request.contextPath}/PL/index.action">印刷车间</a>
				<a href="${pageContext.request.contextPath}/BL/index.action">装订车间</a>
				<a href="${pageContext.request.contextPath}/DL/index.action">发行</a>
				<a href="${pageContext.request.contextPath}/MA/TjInput.action">生产统计</a>
				
				</div>
			</s:if>
			<s:if test="#user.userType.equals('Admin') or 'QC-Basic' in #userSystemFunctionList">
				<!--质量管理子菜单 -->
				<div id="qc" class="dropmenudiv_b">
				<a href="${pageContext.request.contextPath}/QC/index.action">质量问题记录</a>				
				<a href="${pageContext.request.contextPath}/QC/index.action">巡检记录</a>				
				<a href="${pageContext.request.contextPath}/QC/index.action">送检记录</a>				
				</div>
			</s:if>
			<s:if test="#user.userType.equals('Admin') or 'MM-Basic' in #userSystemFunctionList">
				<!--物资管理子菜单 -->
				<div id="WareHouse" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/WareHouse/getPapers.action">纸张报价表</a>
				<a
					href="${pageContext.request.contextPath}/WareHouse/PaperSupply.action">纸张发放记录</a></div>
			</s:if>
			<s:if test="#user.userType.equals('Admin') or 'FM-Basic' in #userSystemFunctionList">
				<!--财务管理子菜单 -->
				<div id="fa" class="dropmenudiv_b"><a
					href="${pageContext.request.contextPath}/FA/MoneyIn.action">收款</a>
				<a href="${pageContext.request.contextPath}/FA/JZKP_input.jsp">结账开票</a>
				<a href="${pageContext.request.contextPath}/FA/MoneyIn_BAT_input.action">批量收款</a>
				<a href="${pageContext.request.contextPath}/FA/MoneyTent.jsp">往来账管理</a>
				<a href="${pageContext.request.contextPath}/FA/TentAF_QYJYYJ.jsp">企业经营业绩统计</a>
				</div>
			</s:if>
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
				<!--网络资源子菜单 -->
				<div id="web" class="dropmenudiv_b">
				<a href="http://www.tyopf.com" title="www Home" target="_blank"><span>企业网站</span></a>
				<a href="http://portal.tyopf.com/c/portal/login" title="Protal Home" target="_blank"><span>企业门户</span></a>
				<a href="https://mail.tyopf.com/" title="Mail Home" target="_blank"><span>电子邮件</span></a>
				</div>
		</s:else> <script type="text/javascript">
	//SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
	tabdropdown.init("bluemenu")
</script></td>
	</tr>
</table>
