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
  <p>车间：
    <select name="任务单2">
    </select>
  </p>
  <p>
    <label>
      不合格数量：
        <input name="textfield" type="text" id="textfield" />
      <br />
      <br />
      <br />
    </label>
  </p>
  <p>报告人陈述：
    <label>
      <textarea name="备注" id="备注" cols="45" rows="5"></textarea>
    </label>
  </p>
  <p>签字：
    <input name="textfield5" type="text" id="textfield5" value="自动" />
  </p>
  <p>日期：
    <input name="textfield4" type="text" id="textfield4" value="自动" />
  </p>
  <p>车间意见：
    <label>
      <textarea name="备注2" id="备注2" cols="45" rows="5"></textarea>
    </label>
  </p>
  <p>签字：
    <input name="textfield6" type="text" id="textfield6" value="自动" />
  </p>
  <p>日期：
    <input name="textfield6" type="text" id="textfield7" value="自动" />
  </p>
  <p></p>
  <p>技术质量科意见：
    <label>
      <textarea name="备注3" id="备注3" cols="45" rows="5"></textarea>
    </label>
  </p>
  <p>签字：
    <input name="textfield7" type="text" id="textfield8" value="自动" />
  </p>
  <p>日期：
    <input name="textfield7" type="text" id="textfield9" value="自动" />
  </p>
  <p></p>
  <p>备注：
    <label>
      <textarea name="备注4" id="备注4" cols="45" rows="5"></textarea>
    </label>
  </p>
  <p>&nbsp;</p>
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