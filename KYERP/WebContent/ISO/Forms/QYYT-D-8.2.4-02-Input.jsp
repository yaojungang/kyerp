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
  <p>名称：（自动生成）</p>
  <p>选择项目：
    <label>
      <input type="radio" name="radio" id="收集" value="收集" />
    </label>
  收集 
  <label>
    <input type="radio" name="radio" id="检查合格" value="检查合格" />
  </label>
  检查合格 
  <label>
    <input type="radio" name="radio" id="检查不合格" value="检查不合格" />
  </label>
  检查合格</p>
  <p>备注：
    <label>
      <textarea name="备注" id="备注" cols="45" rows="5"></textarea>
    </label>
  </p>
  <p>负责人：（自动生成）</p>
  <p>日期：（自动生成）</p>
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