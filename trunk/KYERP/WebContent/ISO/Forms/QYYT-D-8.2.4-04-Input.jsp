<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QYYT-D-8.2.4-04-装订成品检验记录</title>
<link href="../../Library/css/ISOForm.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p align="center" class="表格抬头"><span class="formTitle">装订成品检验记录——输入</span></p>
    <p class="表格文字"><span class="formText">QYYT-D-8.2.4-04 </span></p></td>
  </tr>
</table>
<table width="750" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#000000" bgcolor="#000000" style="border-collapse: collapse; font-size: 16px;">
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">名称</span></td>
    <td height="40" colspan="5" align="center" valign="middle" bgcolor="#FFFFFF"><form id="form1" name="form1" method="post" action="">
      <label>
        <select name="select" id="select">
        </select>
      </label>
    </form></td>
    <td width="96" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">卡号</span></td>
    <td width="62" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><select name="select2" id="select2">
    </select></td>
  </tr>
  <tr>
    <td width="39" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">类别</span></td>
    <td width="86" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText"><span class="formInput">全检</span></span></td>
    <td width="66" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">总数量</span></td>
    <td width="118" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><form id="form2" name="form2" method="post" action="">
      <label>
        <input name="textfield" type="text" id="textfield" value="自动" size="6" />
      </label>
    </form></td>
    <td width="100" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">正品数</span></td>
    <td width="156" height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield3" type="text" id="textfield3" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">不合格数</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield2" type="text" id="textfield2" size="5" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">序号</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">项目</span></td>
    <td height="40" colspan="4" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">质量要求</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">检验结果</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">日期</span></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">1</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">毛样书</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">全书页码顺序正确</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield4" type="text" id="textfield4" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield15" type="text" id="textfield15" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">2</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">折页</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">书帖页码和版面顺序正确，书贴平服整齐,无明显八字皱折、死折、折角、残页和套帖，相连页之间页码位置允差≤4.0 mm。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield5" type="text" id="textfield5" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield16" type="text" id="textfield16" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">3</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">配页</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">书贴页码和版面顺序正确,无错贴、漏贴、脏迹</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield6" type="text" id="textfield6" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield17" type="text" id="textfield17" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">4</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">胶订</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">粘合剂粘度适当，书背和封面粘合牢固，无粘合剂益出，侧胶宽为3.0 mm-7.0 mm。无粘合剂溢出。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield7" type="text" id="textfield7" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield18" type="text" id="textfield18" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">5</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">铁丝平订</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">无坏锯，漏钉、钉脚平服牢固，盖住钉痕，粘口4.0 mm-7.0mm。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield8" type="text" id="textfield8" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield19" type="text" id="textfield19" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">6</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">骑马钉</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">无坏钉、漏钉、重钉，书册平服整齐、干净，钉脚平整牢固，钉锯均钉在折缝线上，书贴歪斜≤2 .0mm。钉锯外钉眼距书芯长上下各1/4处，允差±3.0 mm</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield9" type="text" id="textfield9" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield20" type="text" id="textfield20" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">7</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">锁线订</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">锁线后书芯各贴应排列正确、整齐，无破损、掉页和脏迹，锁线松紧适当，书芯厚度基本一致。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield10" type="text" id="textfield10" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield21" type="text" id="textfield21" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">8</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">粘页机</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">&nbsp;</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield11" type="text" id="textfield11" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield22" type="text" id="textfield22" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">9</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p>三面切书<br />切纸机</p></td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">成品裁切尺寸允许误差不超过1.5mm，歪斜允差≤1.5mm产品无严重刀花，无连刀，无严重破头，成品每刀上下本裁切尺寸误差≤1.0mm。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield12" type="text" id="textfield12" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield23" type="text" id="textfield23" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><span class="formText">10</span></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF">成品</td>
    <td height="40" colspan="4" align="left" valign="middle" bgcolor="#FFFFFF" class="formText">全书墨色均匀、字迹清楚，无掉字、上脏，页码位置允差≤7.0 mm。书背字居中，书脊平直，无空泡，无皱折。成品裁切允差≤1.5 mm，无严重刀花，无连刀页，无严重破头。</td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield13" type="text" id="textfield13" size="5" /></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield24" type="text" id="textfield24" value="自动" size="6" /></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">综合</span><br /><span class="formText">结论</span></p></td>
    <td height="40" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF"><p>
      <input name="textfield26" type="text" id="textfield26" size="5" />
    </p>
    <p class="formText">检验员：
      <input name="textfield25" type="text" id="textfield25" value="自动" size="6" />
    </p></td>
    <td height="40" align="center" valign="middle" bgcolor="#FFFFFF"><p><span class="formText">不合格品的</span><br /><span class="formText">说明及处理</span></p></td>
    <td height="40" colspan="3" align="center" valign="middle" bgcolor="#FFFFFF"><input name="textfield14" type="text" id="textfield14" size="5" /></td>
  </tr>
</table>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="left"><p class="formText">说明：<br />
      1．质量要求、文字描述的检验结果，符合的“√”，不符合“×”； <br />
      2．有数据要求的一律填实测数据； <br />
      3．检验结论一栏，填是否合格。 </p></td>
  </tr>
</table>
</body>
</html>