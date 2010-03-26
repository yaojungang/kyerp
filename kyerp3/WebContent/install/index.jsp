<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"
%>
<%@ include file="/WEB-INF/jsp/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>许可协议--KYERP3安装向导</title>
<link href="img/style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
function formSubmit() {
	if(document.getElementById('license_agree').checked==false){
		alert('请同意我们的协议');
		return false;
	}
	document.getElementById('license_form').submit();
}
</script>
</head>

<body>
<table width="900" align="center" style="border:#106DBA 1px solid; margin-top:10px;">
  <tr>
    <td bgcolor="#D1E9FA" align="center"><h1>安装KYERP3</h1></td>
  </tr>
  <tr><td bgcolor="#D1E9FA"><h3>1、用户许可协议</h3></td></tr>
  <tr>
    <td height="300" bgcolor="#F0F8FD">
	<textarea name="textarea" cols="82" rows="23">                            --==KYERP3用户许可协议==--
	
  版权所有 (c)2007-2010。

  感谢您使用KYERP3，KYERP3是一款基于JAVA企业级平台研发的印刷企业管理系统，依托企业级JAVA的高效、安全、稳定等优势，开创国内JAVA版开源ERP之先河。数据库使用MYSQL，并支持ORACLE、DB2、SqlServer等主流数据库。
无论您的使用目的为何，均请先仔细阅读本许可协议，如果您安装、使用、修改或分发本软件，则表示您已经完全接受本许可协议的所有条款。

一、许可
  1.1 如果您是个人用户，可将本软件用于非商业用途，而不必支付软件授权许可费。
  1.2 您可以在本协议的许可范围内，修改源代码和界面风格以适应您的网站要求。
  1.3 您拥有使用本软件构建的网站全部内容所有权，并独立承担与这些内容的相关法律义务。 
  1.4 在获得商业授权之后，您可以将本软件用于商业用途。
  1.5 商业授权用户享有反映和提出意见的权力，并被优先考虑，但没有一定被采纳的承诺或保证。

二、测试版（beta）
  2.1 测试版“软件”其性能和兼容性均未能达到最终全面发布版本的级别，将来也存在对“软件”进行重大更改的可能，其仅供测试“软件”之用，请勿用于正式建站。

三、约束和限制
  3.1 在未获得商业授权之前，任何单位或个人不得将本软件用于商业用途（包括但不限于企业网站、政府单位网站、经营性网站、以盈利为目的的网站）和任何非个人所有的项目中。如果您需要购买商业授权，请登录了解详细说明。
  3.2 未经官方许可，禁止修改本软件的整体或任何部分用于重新发布第三方版本。
  3.3 不得对本软件或与之关联的商业授权进行出租、出售、抵押或发放子许可证。

四、免责声明
  4.1 用户完全自愿使用本软件，您必须了解使用本软件的风险，且愿意承担使用本软件的风险。
  4.2 任何情况下，我们不就因使用或不能使用本软件所发生的特殊的、意外的、直接或间接的损失承担赔偿责任（包括但不限于，资料损失，资料执行不精确，或因由您或第三人承担的损失，或本程序无法与其他程序运作等）。即使已经被事先告知该损害发生的可能性。
    </textarea></td>
  </tr>
  <tr>
    <td align="right" bgcolor="#D1E9FA">
	<form id="license_form" action="install_params.jsp" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="27" height="30" align="center"><input type="checkbox" id="license_agree" name="license_agree"/></td>
        <td width="273" align="left">我已经阅读并同意此协议</td>
        <td width="292" align="center">
		<input type="button" class="btn" onclick="formSubmit();" value="下一步 >"/></td>
      </tr>
    </table>
	</form>	</td>
  </tr>
</table>
</body>
</html>