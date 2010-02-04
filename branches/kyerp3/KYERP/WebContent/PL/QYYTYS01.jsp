<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
<center><h1>印刷车间操作记录</h1></center>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><p>QYYT-YS-01                                                                                            </p></td>
    <td align="right">  年    月    日 </td>
  </tr>
</table>

<table width="750" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2"><p align="center">设备 <br />
      名称 </p></td>
    <td colspan="6"><p align="center">&nbsp;</p></td>
    <td colspan="3"><p align="center">领机 </p></td>
    <td colspan="3"><p align="center">&nbsp;</p></td>
    <td colspan="3"><p align="center">助手 </p></td>
    <td colspan="3"><p align="center">&nbsp;</p></td>
  </tr>
  <tr>
    <td colspan="2"><p align="center">设备 <br />
      情况 </p></td>
    <td colspan="4"><p>□完好 □自修 <br />
      □报修 □维护 </p></td>
    <td colspan="2"><p align="center">橡皮布 </p></td>
    <td colspan="6"><p>□完好 <br />
      □修补 □更换 </p></td>
    <td colspan="3"><p align="center">设  备 <br />
      日常维护 </p></td>
    <td colspan="3"><ul>
      <li>检查油位并加油 □防护罩 </li>
    </ul>
      <p>□检查叼牙情况 □滚筒清洁 </p></td>
  </tr>
  <tr>
    <td colspan="2"><p align="center">润版液 <br />
      PH值 </p></td>
    <td colspan="4"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">温度 </p></td>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="4"><p align="center">湿度 </p></td>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="3"><p align="center">环境 </p></td>
    <td colspan="3"><p align="center">&nbsp;</p></td>
  </tr>
  <tr>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p>项目 </p></td>
    <td colspan="4"><p align="center">判定 </p></td>
    <td colspan="8"><p align="center">不合格原因 </p></td>
    <td><p align="center">数量 </p></td>
    <td colspan="3"><p align="center">处  理 </p></td>
  </tr>
  <tr>
    <td colspan="2" rowspan="2"><p align="center">纸张 <br />
      版材 </p></td>
    <td colspan="2"><p>印版 </p></td>
    <td colspan="4"><p>□合格 □不合格 </p></td>
    <td colspan="8"><p>&nbsp;</p></td>
    <td><p>&nbsp;</p></td>
    <td colspan="3"><p>□补 □修 </p></td>
  </tr>
  <tr>
    <td colspan="2"><p>纸张 </p></td>
    <td colspan="4"><p>□合格 □不合格 </p></td>
    <td colspan="8"><p>□残 □色差 □裁切误差 </p></td>
    <td><p>&nbsp;</p></td>
    <td colspan="3"><p>□换 </p></td>
  </tr>
  <tr>
    <td colspan="2" rowspan="5"><p align="center">质量 <br />
      自检 </p></td>
    <td colspan="2"><p>墨色 </p></td>
    <td colspan="4"><p>□合格 □不合格 </p></td>
    <td colspan="8"><p>□设备 □纸张 □印版 □人为 </p></td>
    <td><p>&nbsp;</p></td>
    <td colspan="3"><p>&nbsp;</p></td>
  </tr>
  <tr>
    <td colspan="2"><p>套印 </p></td>
    <td colspan="4"><p>□合格 □不合格 </p></td>
    <td colspan="8" valign="top"><p>□设备 □纸张 □印版 □人为 </p></td>
    <td><p>&nbsp;</p></td>
    <td colspan="3"><p>&nbsp;</p></td>
  </tr>
  <tr>
    <td colspan="2"><p>规矩 </p></td>
    <td colspan="4"><p>□合格 □不合格 </p></td>
    <td colspan="8" valign="top"><p>□设备 □纸张 □印版 □人为 </p></td>
    <td><p>&nbsp;</p></td>
    <td colspan="3"><p>&nbsp;</p></td>
  </tr>
  <tr>
    <td colspan="2"><p>粘脏 </p></td>
    <td colspan="4"><p>□合格 □不合格 </p></td>
    <td colspan="8" valign="top"><p>□设备 □纸张 □油墨 □人为 </p></td>
    <td><p>&nbsp;</p></td>
    <td colspan="3"><p>&nbsp;</p></td>
  </tr>
  <tr>
    <td colspan="2"><p>&nbsp;</p></td>
    <td colspan="4"><p>&nbsp;</p></td>
    <td colspan="8" valign="top"><p>&nbsp;</p></td>
    <td><p>&nbsp;</p></td>
    <td colspan="3"><p>&nbsp;</p></td>
  </tr>
  <tr>
    <td><p align="center">任务 <br />
      单号 </p></td>
    <td colspan="4"><p align="center">印品名称 </p></td>
    <td colspan="2"><p align="center">代数 </p></td>
    <td><p align="center">版数 </p></td>
    <td colspan="2"><p align="center">印数 </p></td>
    <td colspan="2"><p align="center">计件数 </p></td>
    <td colspan="4"><p align="center">物  品 </p></td>
    <td colspan="2"><p align="center">未印 <br />
      代数 </p></td>
    <td><p align="center">剩余纸张 </p></td>
    <td><p align="center">备  注 </p></td>
  </tr>
  <tr>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="4"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td colspan="4"><p>□任务单 □样张 <br />
      □墨色样 □样书 </p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
  </tr>
  <tr>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="4"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td colspan="4"><p>□任务单 □样张 <br />
      □墨色样 □样书 </p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
  </tr>
  <tr>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="4"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td colspan="4"><p>□任务单 □样张 <br />
      □墨色样 □样书 </p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
  </tr>
  <tr>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="4"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td colspan="4"><p>□任务单 □样张 <br />
      □墨色样 □样书 </p></td>
    <td colspan="2"><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
    <td><p align="center">&nbsp;</p></td>
  </tr>
  <tr>
    <td colspan="3"><p align="center">交班领机 </p></td>
    <td colspan="9"><p>&nbsp;</p></td>
    <td colspan="3"><p align="center">接班领机 </p></td>
    <td colspan="5"><p>&nbsp;</p></td>
  </tr>
</table>
</body>
</html>