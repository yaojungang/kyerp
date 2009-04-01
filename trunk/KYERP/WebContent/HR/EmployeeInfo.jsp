<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['Employee']" name="employee" id="employee" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#employee.realname" /></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Library/js/function.js"></script>
<link
	href="${pageContext.request.contextPath}/Library/css/EmployeeInfo.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<span class="pageTitle"><s:property value="#request['pageTitle']" /></span>
<div class="pim2_secondMenu tnoprint">
<ul>
	<li><input name="b_print"
	type="button" class="ipt" onClick="printdiv('pim2_employeeInofTable');"
	value=" 打印 "></li></ul></div>
<div id="pim2_employeeInofTable">
<div class="BigTitle">北京市清华园胶印厂职工信息表</div>
<table width="680" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" class="TableBlock">
      <tr>
            <td class="TableHeader" colspan="6">&nbsp;&nbsp;基本信息</td>
         </tr>
         <tr>
            <td width="80" class="TableData"> <div align="center">姓　　名 </div></td>
           <td width="150" class="TableData"><s:property value="#employee.realname" /></td>
            <td width="80" class="TableData"> <div align="center">员工编号</div></td>
            <td class="TableData"><s:property value="#employee.empNo" /></td>
           <td colspan="2" rowspan="5" align="center" valign="middle" class="TableData"><div align="center"><s:if test="#employee.photo != null"><img border="0" style="width: 128px;" src='/uploadData/<s:property value="#employee.photo" />' /></s:if> <s:else>
             <img border="0" width="128" src="${pageContext.request.contextPath}/Library/images/noavatar.gif">
           </s:else>
           </div></td>
        </tr>
              <tr>
                <td width="80" align="center" class="TableData"><div align="center">性　　别</div></td>
                <td class="TableData"><s:if test="#employee.sex==1" >男</s:if><s:if test="#employee.sex==0" >女</s:if></td>
                <td width="80" align="center" class="TableData"><div align="center">出生日期</div></td>
                <td class="TableData"><s:date format="yyyy-MM-dd" nice="false" name="#employee.birthday" /></td>
              </tr>
              <tr>
                <td width="80" align="center"class="TableData"><div align="center">民　　族</div></td>
                <td height="30"class="TableData"><s:property value="#employee.nation" /></td>
                <td width="80" align="center" class="TableData"><div align="center">籍　　贯</div></td>
                <td class="TableData"><s:property value="#employee.nativeplace" /></td>
        </tr>
              <tr>
                <td align="center"class="TableData"><div align="center">学　　历</div></td>
                <td class="TableData"><s:property value="#employee.degree" /></td>
                <td class="TableData"><div align="center">毕业院校</div></td>
                <td class="TableData"><s:property value="#employee.school" /></td>
              </tr>
              <tr>
                <td width="80" align="center"class="TableData"><div align="center">专　　业</div></td>
                <td class="TableData"><s:property value="#employee.speciality" /></td>
                <td width="80" class="TableData"><div align="center">特长爱好</div></td>
                <td class="TableData"><s:property value="#employee.interest" /></td>
        </tr>
              <tr>
                <td width="80" align="center" class="TableData"><div align="center">毕业时间</div></td>
                <td class="TableData"><s:date format="yyyy-MM-dd" nice="false" name="#employee.graduteDate" /></td>
                <td width="80" class="TableData"><div align="center">健康状况</div></td>
                <td class="TableData"><s:property value="#employee.health" /></td>
                <td width="80" class="TableData" nowrap><div align="center">婚姻状况</div></td>
                <td width="80" class="TableData"><s:property value="#employee.weeding" /></td>
        </tr>
              <tr>
                <td width="80" align="center" nowrap class="TableData"><div align="center">身份证号码</div></td>
                <td class="TableData"><s:property value="#employee.idcard" /></td>
                <td width="80" nowrap class="TableData"><div align="center">户口所在地</div></td>
                <td class="TableData"><s:property value="#employee.rpraddress" /></td>
                <td class="TableData"><div align="center">政治面貌</div></td>
                <td class="TableData"><s:property value="#employee.polity" /></td>
        </tr>
    </table>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
        <table width="100%" class="TableBlock">
             <tr>
              <td class="TableHeader" colspan="4">&nbsp;&nbsp;联系方式</td>
             </tr>
             <tr>
                <td width="80" class="TableData">
                  <div align="center">办公电话</div></td>
               <td class="TableData"><s:property value="#employee.workTel" /></td>
                <td width="80" class="TableData">
                  <div align="center">手　　机</div></td>
               <td class="TableData"><s:property value="#employee.mobile" /></td>
              </tr>
              <tr>
                <td width="80" class="TableData">
                  <div align="center">住宅电话</div></td>
                <td class="TableData"><s:property value="#employee.tel" /></td>
                <td width="80" class="TableData">
                  <div align="center">电子邮箱</div></td>
                <td class="TableData"><s:property value="#employee.email" /></td>
              </tr>
              <tr>
                <td width="80" align="center" class="TableData"><div align="center">现住址</div></td>
                <td class="TableData" colspan="3"><s:property value="#employee.address" /></td>
              </tr>
         </table>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
       <table width="100%" class="TableBlock">
         <tr>
          <td class="TableHeader" colspan="4">&nbsp;&nbsp;工作情况</td>
         </tr>
         <tr>
          <td width="80" class="TableData"><div align="center">所在部门</div></td>
          <td class="TableData"><s:property value="#employee.companyDept.name" /></td>
          <td width="80" class="TableData"><div align="center">编　　制</div></td>
          <td class="TableData"><s:property value="#employee.bianZhi" /></td>
         </tr>
         <tr>
          <td width="80" class="TableData"><div align="center">职　　务</div></td>
          <td class="TableData"><s:property value="#employee.user.roles.rname" />
          <s:iterator value="#employee.user.roles" status="stat">
          	<s:property value="rname" />	
          </s:iterator>
          </td>
          <td width="80" class="TableData"><div align="center">职　　称</div></td>
          <td class="TableData"><s:property value="#employee.zhiChen" /></td>
          </tr>
          <tr>
            <td width="80" class="TableData"><div align="center">进厂时间</div></td>
            <td class="TableData"><s:date format="yyyy-MM-dd" nice="false" name="#employee.participateDate" /></td>
            <td class="TableData"><div align="center">社保缴纳</div></td>
            <td class="TableData"><s:property value="#employee.insurance" /></td>
          </tr>
    </table>    </td>
  </tr>
  
  
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table width="100%" class="TableBlock">
              <tr>
                <td colspan="3" valign="bottom"  class="TableHeader" >&nbsp;&nbsp;个人简历</td>
              </tr>
              <tr>
                <td width="190" align="center" class="TableData"><div align="center">起止时间</div></td>
                <td align="center" class="TableData"><div align="center">单位</div></td>
                <td width="80" align="center" class="TableData"><div align="center">职务</div></td>
              </tr>
              <s:iterator value="#employee.resume" status="stat">
              <tr>
                <td class="TableData"><div align="center"><s:date format="yyyy-MM-dd" nice="false" name="startTime" /> - <s:date format="yyyy-MM-dd" nice="false" name="endTime" /></div></td>
                <td class="TableData"><s:property value="company" /></td>
                <td class="TableData"><s:property value="job" /></td>
              </tr></s:iterator>
            </table>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
       <table width="100%" class="TableBlock">
			 <tr>
                <td colspan="5" valign="bottom"  class="TableHeader" >&nbsp;&nbsp;社会关系</td>
             </tr>
              <tr align="center">
              	<td width="80" class="TableData"><div align="center">关系</div></td>
                <td width="80" class="TableData"><div align="center">姓名</div></td>
                <td width="80" class="TableData"><div align="center">政治面貌</div></td>
                <td class="TableData"><div align="center">工作单位</div></td>
                <td width="80" class="TableData"><div align="center">职务</div></td>
         </tr>
              <s:iterator value="#employee.family" status="stat">
              <tr>
              	<td class="TableData"><div align="center"><s:property value="relation" /></div></td>
                <td class="TableData"><div align="center"><s:property value="name" /></div></td>
                <td class="TableData"><s:property value="polity" /></td>
                <td class="TableData"><s:property value="company" /></td>
                <td class="TableData"><s:property value="job" /></td>
              </tr>
              </s:iterator>
            </table>    </td>
  </tr>
  
   <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
     <table width="100%" class="TableBlock">
        <tr>
          <td colspan="4"  class="TableHeader">&nbsp;&nbsp;劳动合同签订情况</td>
        </tr>
        <tr>
          <td width="80" class="TableData"><div align="center">签订日期</div></td>
          <td class="TableData"><s:date format="yyyy-MM-dd" nice="false" name="#employee.contractSignDate" /></td>
          <td width="80" class="TableData"><div align="center">到期日期</div></td>
          <td class="TableData"><s:date format="yyyy-MM-dd" nice="false" name="#employee.contractMatureDate" /></td>
        </tr>
      </table>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
        <table width="100%" class="TableBlock">
          <tr>
           <td  class="TableHeader">&nbsp;&nbsp;备注</td>
          </tr>
          <tr>
           <td class="TableData">&nbsp;<s:property value="#employee.remark" /></td>
          </tr>
        </table>      </td>
    </tr>
    </table>

</body>
</html>
