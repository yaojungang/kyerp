<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['Employee']" name="employee" id="employee" />
<s:set value="#request['roleList']" name="roleList" id="roleList" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人资料</title>
<link rel="stylesheet" id='skin' type="text/css" href="${pageContext.request.contextPath}/Library/js/ymPrompt/skin/qq/ymPrompt.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/Library/js/ymPrompt/ymPrompt.js"></script>
<script src="${pageContext.request.contextPath}/Library/js/jquery.js" type="text/javascript"></script>
</head>
<body>
<s:if test="#session.user==null">您还没有登录,请登录!
<jsp:forward page="/login.jsp" />
</s:if>
<div class="pim2_secondMenu">
<ul>
	<li><input type="button" value="职工信息表" onClick='javascript:window.location.href="MyInfo.action"'></li>
	<s:if test="#employee.infoStatus ==1 "><li><input type="button" value="修改资料" onClick='javascript:window.location.href="ChangeMyInfo.action"'></li></s:if>
	<li><input type="button" value="修改密码" onClick='javascript:window.location.href="ChangeMyPassword.action"'></li>
	<li><input type="button" value="上传照片" onClick="ymPrompt.win(document.getElementById('popWindowPhoto').innerHTML,450,180,'上传照片')" /></li>
</ul>
</div>
<span class="pageTitle">
<s:property value="#request['pageTitle']" />
</span>

<form action='editEmployee_save.action' method="post" id="form1">
  <s:if test="#employee.id>0">
    <s:hidden name="employee.id" value="%{#employee.id}" />
  </s:if>
  <div id="tabs1">
    <div class="menu1box">
      <ul id="menu1">
        <li class="hover" onClick="setTab(1,0)"><a href="#">基本资料</a></li>
        <li onClick="setTab(1,1)"><a href="#">详细资料</a></li>
        <li onClick="setTab(1,2)"><a href="#">个人简历</a></li>
        <li onClick="setTab(1,3)"><a href="#">社会关系</a></li>
        <li onClick="setTab(1,4)"><a href="#">个性化资料</a></li>
        <li onClick="setTab(1,5)"><a href="#">系统选项</a></li>
      </ul>
    </div>
    <div class="main1box">
      <div class="main" id="main1">
        <ul class="block">
          <li>
            <s:if test="#employee.user.id>0">
              <s:hidden name="user.id" value="%{#employee.user.id}" />
            </s:if>
            <table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0">
              <tr>
                <td width="80" align="right">姓名</td>
                <td align="left"><s:property value="#employee.realname" /></td>
              </tr>
              <tr>
                <td width="80" align="right">人员状态</td>
                <td align="left">					
                    <s:if test="#employee.workStatus == 0">在职</s:if><s:if test="#employee.workStatus == 1">试用期</s:if>
                   <s:if test="#employee.workStatus == 2">申请离职</s:if><s:if test="#employee.workStatus == 100">退休</s:if>
                    <s:if test="#employee.workStatus == 101">离职</s:if></td>
              </tr>
              <tr>
                <td width="80" align="right">个人资料状态</td>
                <td align="left"><s:if test="#employee.infoStatus == 0">锁定</s:if><s:if test="#employee.infoStatus == 1">正常</s:if></td>
              </tr>
              <tr>
                <td width="80" align="right">员工编号</td>
                <td align="left"><s:property value="#employee.empNo" /></td>
              </tr>
              <tr>
                <td width="80" align="right">身份证号</td>
                <td align="left"><s:property value="#employee.idcard" /></td>
              </tr>
              <tr>
                <td width="80" align="right">性别</td>
                <td align="left"><s:if test="#employee.sex==1" >男</s:if><s:if test="#employee.sex==0" >女</s:if></td>
              </tr>
              <tr>
                <td width="80" align="right">出生日期</td>
                <td align="left"><s:date format="yyyy-MM-dd" nice="false" name="#employee.birthday" /></td>
              </tr>
              <tr>
                <td width="80" align="right">籍贯</td>
                <td align="left"><s:property value="#employee.nativeplace" /></td>
              </tr>
              <tr>
                <td width="80" align="right">户口所在地</td>
                <td align="left"><s:property value="#employee.rpraddress" /></td>
              </tr>
              <tr>
                <td width="80" align="right">所在部门</td>
                <td align="left"><s:property value="#employee.companyDept.name" /></td>
              </tr>
              <tr>
                <td width="80" align="right" bgcolor="#FFFFFF">职务</td>
                <td align="left" bgcolor="#FFFFFF"><s:iterator value="#employee.user.roles" status="stat">
          	<s:property value="rname" />	
          </s:iterator></td>
              </tr>
              <tr>
                <td width="80" align="right">登陆次数</td>
                <td align="left"><s:property value="#employee.user.loginTimes" /></td>
              </tr>
              <tr>
                <td width="80" align="right">最后登陆时间</td>
                <td align="left"><s:date name="#employee.user.lastLoginTime" format="yyyy-MM-dd HH:MM:SS" nice="false" /></td>
              </tr>
            </table>
          </li>
        </ul>
        <ul>
          <li>
            <table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0">
              <tr>
                <td width="80" align="right">民族</td>
                <td align="left"><s:property value="#employee.nation" /></td>
              </tr>
              <tr>
                <td width="80" align="right">婚姻状况</td>
                <td align="left"><s:property value="#employee.weeding" /></td>
              </tr>
              <tr>
                <td width="80" align="right">健康状况</td>
                <td align="left"><s:property value="#employee.health" /></td>
              </tr>
              <tr>
                <td width="80" align="right">政治面貌</td>
                <td align="left"><s:property value="#employee.polity" /></td>
              </tr>
              <tr>
                <td width="80" align="right">文化程度</td>
                <td align="left"><s:property value="#employee.degree" /></td>
              </tr>
              <tr>
                <td width="80" align="right">专业特长</td>
                <td align="left"><s:property value="#employee.speciality" /></td>
              </tr>
              <tr>
                <td width="80" align="right">办公电话</td>
                <td align="left"><s:property value="#employee.workTel" /></td>
              </tr>
              <tr>
                <td width="80" align="right">手机</td>
                <td align="left"><s:property value="#employee.mobile" /></td>
              </tr>
              <tr>
                <td width="80" align="right">现住址</td>
                <td align="left"><s:property value="#employee.address" /></td>
              </tr>
              <tr>
                <td width="80" align="right">住宅电话</td>
                <td align="left"><s:property value="#employee.tel" /></td>
              </tr>
              <tr>
                <td width="80" align="right">家庭电话</td>
                <td align="left"><s:property value="#employee.rprtel" /></td>
              </tr>
              <tr>
                <td width="80" align="right">进厂时间</td>
                <td align="left"><s:date format="yyyy-MM-dd" nice="false" name="#employee.participateDate" /></td>
              </tr>
              <tr>
                <td width="80" align="right">职称</td>
                <td align="left"><s:property value="#employee.zhiChen" /></td>
              </tr>
              <tr>
                <td width="80" align="right">编制</td>
                <td align="left"><s:property value="#employee.bianZhi" /></td>
              </tr>
              <tr>
                <td width="80" align="right">残疾类型</td>
                <td align="left"><s:property value="#employee.disabilityType" /> 仅残疾员工</td>
              </tr>
              <tr>
                <td width="80" align="right">残疾证编号</td>
                <td align="left"><s:property value="#employee.disabilityIdcard" /> 仅残疾员工</td>
              </tr>
              <tr>
                <td width="80" align="right">合同签订日期</td>
                <td align="left"><s:date format="yyyy-MM-dd" nice="false" name="#employee.contractSignDate" /></td>
              </tr>
              <tr>
                <td width="80" align="right">合同到期日期</td>
                <td align="left"><s:date format="yyyy-MM-dd" nice="false" name="#employee.contractMatureDate" /></td>
              </tr>
              <tr>
                <td width="80" align="right">备注</td>
                <td align="left"><s:property value="#employee.remark" /></td>
              </tr>
            </table>
            <br>
            
          </li>
        </ul>
        <ul>
          <li>
            <s:iterator value="#employee.resume" status="stat"><table width="90%" border="0" align="center" cellpadding="8"
			cellspacing="0">
                <tr>
                  <td align="right" bgcolor="#B5DAFF">个人简历</td>
                  <td align="left" bgcolor="#B5DAFF"></td>
                </tr>
                <tr>
                  <td width="80" align="right">单位名称</td>
                  <td align="left"><s:property value="company" /></td>
                </tr>
                <tr>
                  <td width="80" align="right">职务</td>
                  <td align="left"><s:property value="job" /></td>
                </tr>
                <tr>
                  <td width="80" align="right">开始时间</td>
                  <td align="left"><s:date format="yyyy-MM-dd" nice="false" name="startTime" /></td>
                </tr>
                <tr>
                  <td width="80" align="right">结束时间</td>
                  <td align="left"><s:date format="yyyy-MM-dd" nice="false" name="endTime" /></td>
                </tr>
              </table>
              <br />
            </s:iterator>
           
          </li>
        </ul>
        <ul>
          <li>
            <s:iterator value="#employee.family" status="stat">
             <table width="90%" border="0" align="center" cellpadding="8"
			cellspacing="0">
                <tr>
                  <td align="right" bgcolor="#B5DAFF">家庭成员</td>
                  <td align="left" bgcolor="#B5DAFF"></td>
                </tr>
                <tr>
                  <td width="80" align="right">姓名</td>
                  <td align="left"><s:property value="name" /></td>
                </tr>
                <tr>
                  <td width="80" align="right">关系</td>
                  <td align="left"><s:property value="relation" /></td>
                </tr>
                <tr>
                  <td width="80" align="right">政治面貌</td>
                  <td align="left"><s:property value="polity" /></td>
                </tr>
                <tr>
                  <td width="80" align="right">工作单位</td>
                  <td align="left"><s:property value="company" /></td>
                </tr>
                <tr>
                  <td width="80" align="right">职务</td>
                  <td align="left"><s:property value="job" /></td>
                </tr>
              </table>
              <br />
            </s:iterator>
          </li>
        </ul>
        <ul>
          <li>
            <table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0">
              <tr>
                <td width="80" align="right">照片</td>
                <td align="left"><s:if test="#employee.photo != null"> <img style="width: 96px; height: 96px;"
					src='/uploadData/<s:property value="#employee.photo" />' />
                    <input type="hidden" name="employee.photo"
					value='<s:property value="#employee.photo" />' />
                    <input type="button" value="修改"
					onClick="ymPrompt.win(document.getElementById('popWindowPhoto').innerHTML,450,180,'上传照片')" />
                  </s:if>
                  <s:else>
                    <input type="button" value="上传照片"
					onClick="ymPrompt.win(document.getElementById('popWindowPhoto').innerHTML,450,180,'上传照片')" />
                  </s:else></td>
              </tr>
              <tr>
                <td width="80" align="right">Email</td>
                <td align="left"><s:property value="#employee.email" /></td>
              </tr>
              <tr>
                <td width="80" align="right">QQ</td>
                <td align="left"><s:property value="#employee.qq" /></td>
              </tr>
              <tr>
                <td width="80" align="right">MSN</td>
                <td align="left"><s:property value="#employee.msn" /></td>
              </tr>
              <tr>
                <td width="80" align="right">自我介绍</td>
                <td align="left"><s:property value="#employee.selfDesc" /></td>
              </tr>
            </table>
            <br />
          </li>
        </ul>
        <ul>
          <li>
            <table width="90%" border="0" align="center" cellpadding="8"
		cellspacing="0">
              <tr>
                <td width="80" align="right">用户名</td>
                <td align="left"><s:property value="#employee.user.username" /></td>
              </tr>
              <tr>
                <td width="80" align="right">用户类型</td>
                <td align="left"><s:if test="#employee.user.userType.equals('user')">普通用户</s:if>
                    <s:if test="#employee.user.userType.equals('Admin')">管理员</s:if>
                    <s:if test="#employee.user.userType.equals('Locked')">锁定</s:if></td>
              </tr>
              <tr>
                <td width="80" align="right">用户首页</td>
                <td align="left"><s:property value="#employee.user.url" /></td>
              </tr>
            </table>
          </li>
        </ul>
      </div>
    </div>
  </div>
</form>
<div id="popWindowPhoto" style="display: none;">
  <div class="popWindowBody">
    <form action="ChangeMyPhoto_save.action" method="POST"
	enctype="multipart/form-data">
      上传的照片
      <input type="file"
	name="uploadPhoto" />
      <input type="submit" value="上   传" />
    </form>
    说明：请上传JPEG格式的图片</div>
</div>

</body>
</html>
