<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>calendar</title>
</head>
<body>
<s:property value="date" />
<div style="float: right; margin-left: 1em; margin-bottom: 1em;"
	id="calendar-container"></div>
<script type="text/javascript">
  function dateChanged(calendar) {
    if (calendar.dateClicked) {
      var y = calendar.date.getFullYear();
      var m = calendar.date.getMonth()+1;     // integer, 0..11
      var d = calendar.date.getDate();      // integer, 1..31
      // redirect...
      // window.location = "/" + y + "/" + m + "/" + d + "/index.php";
      window.location="${pageContext.request.contextPath}/AF/getAFByDate.action?date=" + y + "-" + m + "-" + d + ";"
    }
  };

  Calendar.setup(
    {
      // date   	   : 2007-01-01,
      flat         : "calendar-container", // ID of the parent element
      flatCallback : dateChanged,           // our callback function
      showOthers   : true,
      firstDay	   : 1
    }
  );
</script>
</body>
</html>
