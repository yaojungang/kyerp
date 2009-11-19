<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-07-产成品检验记录</title>

<link href="../../Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p align="center" class="formTitle">产成品检验记录</p>
    <p class="formText">QYYT-D-8.2.4-07 </p></td>
  </tr>
</table>
<table width="750" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">名称</span></td>
    <td height="40" colspan="5" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="96" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">卡号</span></td>
    <td width="62" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td width="39" height="40" align="center" valign="middle" bgcolor="#FFFFFF">数量</td>
    <td width="86" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">样本数</span></td>
    <td width="118" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="112" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">不合格判定数</span></td>
    <td width="144" height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">不合格品数</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">序号</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">项目</span></td>
    <td height="40" colspan="4" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">质量要求</span></td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">检验结果</span></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">1</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">墨  色</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">印刷幅面墨色均匀。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">2</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">文字印迹</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">精细产品字迹清楚完整，一般产品无明显缺笔断画。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">3</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">套  印</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">印刷书页版面正反套印准确，其套印允差为：精细产品≤2.0mm,一般产品≤3.0mm。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">4</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">外  观</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">印刷书面版面干净，无明显脏迹和糊版、破口。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">5</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">封面、插页</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">墨色均匀，层次清楚，套印准确，套印允差为：精细产品≤0.1mm，一般产品≤0.2 mm。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">6</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">书页与书贴</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">书帖页码和版面顺序正确，无明显八字皱折、死折、折角、残页和套帖，相连页之间页码位置允差≤4.0 mm，全书页码位置允差≤7.0 mm。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">7</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">胶 粘  钉</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">粘合剂粘度适当，粘合牢固，侧胶宽为3.0 mm-7.0 mm。无粘合剂溢出。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">8</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">铁丝平钉</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">无坏锯，漏钉、钉脚平服牢固，盖住钉痕，粘口4.0 mm-7.0mm。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">9</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">骑 马  钉</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">无坏钉、漏钉、重钉，书册平服整齐、干净，钉脚平整牢固，钉锯均钉在折缝线上，书贴歪斜≤2 .0mm。钉锯外钉眼距书芯长上下各1/4处，允差±3.0 mm</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">10</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">成  品</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">书背字居中，书脊平直，无空泡，无皱折。成品裁切允差≤1.5 mm，无严重刀花，无连刀页，无严重破头。</td>
    <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">检验</span><br /><span class="formText">结论</span></p></td>
    <td height="40" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF"><p>&nbsp;</p>
    <p class="formText">检验员：</p></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">备注</span><span class="formText"></span></p></td>
    <td height="40" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
</table>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="80" align="left"><p>注：<br />①抽检数量500册（含）以下抽13册；501-1200册抽20册；1201-10000册抽32册。 <br />
      ②检验结果一栏合格打“√”，不合格打“×”。有数据要求的填实测数。 <br />
    ③检验结论一栏，判定合格或不合格。 </p></td>
  </tr>
</table>
</body>
</html>