<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
<form id="form1" name="form1" method="post" action="">
  <p>选择任务单： 
    <select name="任务单">
    </select>
  </p>
  <p>名称：
    <input name="textfield2" type="text" id="textfield2" value="自动" />
  </p>
  <p>日期：
    <input name="textfield3" type="text" id="textfield3" value="自动" />
  </p>
  <p>操作员：
    <select name="任务单2">
    </select>
  </p>
  <p>代数：
    <input name="textfield5" type="text" id="textfield5" value="自动" />
  </p>
  <p>
  <label>
    数量：
      <input name="textfield" type="text" id="textfield" value="自动" />
      <br />
      <br />
      巡检时间：
      <input name="textfield4" type="text" id="textfield4" />
<br />
    正反套印：
    <input type="radio" name="radio" id="收集" value="收集" />
  </label>
    合格 
    <label>
      <input type="radio" name="radio" id="检查合格" value="检查合格" />
    不</label>合格</p>
  <p>
    <label>墨色：
      <input type="radio" name="radio" id="收集2" value="收集" />
    </label>
合格
<label>
  <input type="radio" name="radio" id="检查合格2" value="检查合格" />
  不</label>
合格</p>
  <p>
    <label>外观：
      <input type="radio" name="radio" id="收集3" value="收集" />
    </label>
合格
<label>
  <input type="radio" name="radio" id="检查合格3" value="检查合格" />
  不</label>
合格</p>
  <p>
    <label>文字：
      <input type="radio" name="radio" id="收集4" value="收集" />
    </label>
合格
<label>
  <input type="radio" name="radio" id="检查合格4" value="检查合格" />
  不</label>
合格</p>
  <p>
    <label>网点：
      <input type="radio" name="radio" id="收集5" value="收集" />
    </label>
合格
<label>
  <input type="radio" name="radio" id="检查合格5" value="检查合格" />
  不</label>
合格</p>
  <p>
    <label>结论：
      <input type="radio" name="radio" id="收集9" value="收集" />
    </label>
合格
<label>
  <input type="radio" name="radio" id="检查合格9" value="检查合格" />
  不</label>
合格</p>
  <p>不合格说明及处置：
    <label>
      <textarea name="备注" id="备注" cols="45" rows="5"></textarea>
    </label>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>
    <label>
      <input type="submit" name="提交" id="提交" value="提交" />
    </label>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>