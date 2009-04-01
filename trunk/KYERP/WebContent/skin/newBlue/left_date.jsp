<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>calendar</title>
</head>
<body>
<style type="text/css">
.center {
	text-align: center;
}

.red {
	color: #Ff00;
}

#calendar_container {
	width: 100%;
	border: 1px solid #6088FF;
}

#calendar {
	border-collapse: collapse;
	background-color: #FFF;
	width: 100%;
	height: 120px;
	margin: 0px auto;
	cursor: default;
}

#calendar td {
	font-size: 12px;
	text-align: center;
	vertical-align: middle;
	font-family: "宋体";
}

#calendar thead {
	background-color: #a1a1a1;
	color: #FFF;
}

#calendar caption {
	background-color: #6088FF;
}

#calendar a {
	color: #ffffff;
	margin: 0 5px;
	text-decoration: none;
}

#calendar #prev_month,#calendar #next_month {
	font-size: 12px;
	margin: 0;
}

#calendar #c_today {
	background-color: #036;
	color: #FFF;
}

#calendar .over {
	background-color: #CCC;
}

#calendar .keydate {
	color: #06F;
}
</style>
<script type="text/javascript" defer="defer">
window.onload = function(){
	var j = new JCalendar(<s:if test="date"><s:date name="date" format="yyyy,MM,dd" nice="false"/></s:if>);
	j.setYears(2006,2020);
	document.getElementById("calendar_container").innerHTML = j.toString();
	// j.setKeyDate([1,8,12,26]);
	// JCalendar.onclick=function(){location="getAFByDate.action?date=" +year + "-" + month + "-" + date};
	//JCalendar.onupdate=function(){location="http://bbs.blueidea.com"};
}
</script>
<div id="calendar_container"></div>
</body>
</html>
