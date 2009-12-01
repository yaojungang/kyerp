<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-07-产成品检验记录</title>
<link href="${pageContext.request.contextPath}/Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/Library/js/Validator.js"></script>
</head>

<body>

<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p align="center" class="表格抬头"><span class="formTitle">产成品检验记录——输入</span></p>
    <p class="表格文字"><span class="formText">QYYT-D-8.2.4-07</span></p></td>
  </tr>
</table>
<form action="${pageContext.request.contextPath}/QC/InspectionRecordsOfFinshedGoods/save.action" method="post" id="form">
<input type="hidden" name="afId" value="${afBase.afId}" />
<input type="hidden" name="ir.id" value="${ir.id}"/>
<table width="800" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">名称</span></td>
    <td height="40" colspan="5" align="center" valign="middle" bgcolor="#FFFFFF">${afBase.presswork}</td>
    <td width="80" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">卡号</span></td>
    <td width="187" height="40" align="center" valign="middle" bgcolor="#FFFFFF">${afBase.iso}${afBase.afNo}</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">总数量</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">${afBase.amount}</td>
    <td width="88" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">样本数</span></td>
    <td width="176" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.sampleAmount" type="text" id="ir.sampleAmount" size="5" value="${ir.sampleAmount}" /></td>
    <td width="59" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">不合格<br />
判定数</span></td>
    <td width="91" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.unqualifiedStandNumber" type="text" id="ir.unqualifiedStandNumber" size="5" value="${ir.unqualifiedStandNumber}" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">不合格数</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.unqualifiedAmount" type="text" id="ir.unqualifiedAmount" size="5" value="${ir.unqualifiedAmount}" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">序号</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">项目</span></td>
    <td height="40" colspan="4" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">质量要求</span></td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">检验结果</span></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">1</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">墨  色</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">印刷幅面墨色均匀。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem01" type="text" size="5" value="${ir.examItem01}"/></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">2</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">文字印迹</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">精细产品字迹清楚完整，一般产品无明显缺笔断画。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem02" type="text" id="examItem02" size="5" value="${ir.examItem02}" /></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">3</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">套  印</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">印刷书页版面正反套印准确，其套印允差为：精细产品≤2.0mm,一般产品≤3.0mm。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem03" type="text" id="examItem03" size="5" value="${ir.examItem03}"/></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">4</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">外  观</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">印刷书面版面干净，无明显脏迹和糊版、破口。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem04" type="text" id="examItem04" size="5" value="${ir.examItem04}" /></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">5</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">封面、插页</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">墨色均匀，层次清楚，套印准确，套印允差为：精细产品≤0.1mm，一般产品≤0.2 mm。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem05" type="text" id="examItem05" size="5" value="${ir.examItem05}" /></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">6</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">书页与书贴</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">书帖页码和版面顺序正确，无明显八字皱折、死折、折角、残页和套帖，相连页之间页码位置允差≤4.0 mm，全书页码位置允差≤7.0 mm。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem06" type="text" id="examItem06" size="5" value="${ir.examItem06}" /></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">7</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">胶 粘  钉</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">粘合剂粘度适当，粘合牢固，侧胶宽为3.0 mm-7.0 mm。无粘合剂溢出。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem07" type="text" id="examItem07" size="5" value="${ir.examItem07}" /></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">8</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">铁丝平钉</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">无坏锯，漏钉、钉脚平服牢固，盖住钉痕，粘口4.0 mm-7.0mm。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem08" type="text" id="examItem08" size="5" value="${ir.examItem08}" /></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">9</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">骑 马  钉</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">无坏钉、漏钉、重钉，书册平服整齐、干净，钉脚平整牢固，钉锯均钉在折缝线上，书贴歪斜≤2 .0mm。钉锯外钉眼距书芯长上下各1/4处，允差±3.0 mm</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem09" type="text" id="examItem09" size="5" value="${ir.examItem09}" /></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">10</span></td>
    <td height="60" align="center" valign="middle" bgcolor="#FFFFFF">成  品</td>
    <td height="60" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">书背字居中，书脊平直，无空泡，无皱折。成品裁切允差≤1.5 mm，无严重刀花，无连刀页，无严重破头。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><input name="ir.examItem10" type="text" id="examItem10" size="5" value="${ir.examItem10}" /></td>
    </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">检验</span><br />
      <span class="formText">结论</span></p></td>
    <td height="40" colspan="3" align="left" valign="middle" bgcolor="#FFFFFF">
      <textarea name="ir.examResult" id="ir.examResult" cols="35" rows="5">${ir.examResult}</textarea>

 </td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">备注</span><span class="formText"></span></p></td>
    <td height="40" colspan="3" align="left" valign="middle" bgcolor="#FFFFFF"><textarea name="ir.remark" id="ir.remark" cols="35" rows="5">${ir.remark }</textarea></td>
  </tr>
</table>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="left"><p class="formText">注：<br />
      ①抽检数量500册（含）以下抽13册；501-1200册抽20册；1201-10000册抽32册。 <br />
②检验结果一栏合格打“√”，不合格打“×”。有数据要求的填实测数。 <br />
③检验结论一栏，判定合格或不合格。 </p></td>
  </tr>
</table>
<center><input type="submit" value="提交" /></center>
</form>
</body>
</html>