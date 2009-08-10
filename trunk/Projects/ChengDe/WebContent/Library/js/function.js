//弹出确认对话框 message 表示要确认的内容
function checkit(message) {
	if (confirm(message)) {
		return true;
	} else {
		return false;
	}
}

// 修正小数点位数; a为该小数,n为要保留的位数
function fixfloat0(a, n) {
	var s = a + "";
	var n1 = n1 + 1;
	var str = s.substring(0, s.indexOf(".") + n1);
	// var str = Math.round(a*100)/100
	return str;
}
function fixfloat1(Dight, How) {
	Dight = Math.round(Dight * Math.pow(10, How)) / Math.pow(10, How);
	return Dight;
}
function fixfloat(Dight, How) {
	var n = Dight.toFixed(How)
	return n;
}
function fixfloat2(f, dec) {
	if (dec < 0)
		return "Error:dec<0!";
	result = parseInt(f) + (dec == 0 ? "" : ".");
	f -= parseInt(f);
	if (f == 0)
		for (i = 0; i < dec; i++)
			result += '0';
	else {
		for (i = 0; i < dec; i++)
			f *= 10;
		result += parseInt(Math.round(f));
	}
	return result;
}
// 不同按钮提交到不同的form
// input type="submit" value="修改"
// onclick="subAction('forms','updateMessage.html');"
function subAction(forms, formAction) {
	var targetForm = document.forms;
	targetForm.action = formAction;
	targetForm.submit();
}

// 打印指定DIV中的内容
// 网页部分 <input name="b_print" type="button" class="ipt"
// onClick="printdiv('div_print');" value=" 打印测试 ">
// 打印的内容 <div id="div_print">
// 要打印内容 </div>
function printdiv(printpage) {
	var headstr = "<html><head><title></title></head><body>";
	var footstr = "</body>";
	var newstr = document.all.item(printpage).innerHTML;
	var oldstr = document.body.innerHTML;
	document.body.innerHTML = headstr + newstr + footstr;
	window.print();
	document.body.innerHTML = oldstr;
	return false;
}

// <input type="checkbox" name="test" value="" onclick="if(this.checked==true) {
// checkbox_checkAll('test'); } else { checkbox_clearAll('test'); }" /> 字母全选开关
// <input type="checkbox" name="test" value="a" /> a <input type="checkbox"
// name="test" value="b" /> b
//<input type="checkbox" name="test" value="a" /> a <input type="checkbox" name="test" value="b" /> b <input type="checkbox" name="test" value="c" /> c <input type="checkbox" name="test" value="d" /> d <input type="checkbox" name="test" value="e" /> e <input type="checkbox" name="test" value="f" /> f <input type="checkbox" name="test" value="g" /> 
// <input type="button" value="选择所有的字母" onclick="checkAll('test')" /> <input
// type="button" value="清空选中的字母" onclick="clearAll('test')" />
function checkbox_checkAll(name) {
	var el = document.getElementsByTagName('input');
	var len = el.length;
	for ( var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name)) {
			el[i].checked = true;
		}
	}
}
function checkbox_clearAll(name) {
	var el = document.getElementsByTagName('input');
	var len = el.length;
	for ( var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name)) {
			el[i].checked = false;
		}
	}
}

function popWindowShow(checkbox_list){ 
	checkbox_list = document.getElementById(checkbox_list); 
	checkbox_list.style.display = "block"; 
}

function popWindowHidden(checkbox_list){ 
	checkbox_list = document.getElementById(checkbox_list); 
	checkbox_list.style.display = "none"; 
}

function popWindowSetTxtValue(checkbox_list,ObjID,inputValue){ 
	checkbox_list = document.getElementById(checkbox_list);
	Obj = document.getElementById(ObjID); 
	Obj.value=inputValue;
	//checkbox_list.style.display = "none";
	//checkbox_list.close();
}
function setTab(m,n){
	var tli=document.getElementById("menu"+m).getElementsByTagName("li"); /*获取选项卡的LI对象*/
	var mli=document.getElementById("main"+m).getElementsByTagName("ul"); /*获取主显示区域对象*/
	for(i=0;i<tli.length;i++){
	  tli[i].className=i==n?"hover":""; /*更改选项卡的LI对象的样式，如果是选定的项则使用.hover样式*/
	  mli[i].style.display=i==n?"block":"none"; /*确定主区域显示哪一个对象*/
	}
}

function  trim(str)
{
    for(var  i  =  0  ;  i<str.length  &&  str.charAt(i)=="  "  ;  i++  )  ;
    for(var  j  =str.length;  j>0  &&  str.charAt(j-1)=="  "  ;  j--)  ;
    if(i>j)  return  "";  
    return  str.substring(i,j);  
}