var enablepersist="on" //是否使用Cookie来保存结构的状态 (on/off)
var memoryduration="7" //保存多少天

var contractsymbol='../Library/images/switch_minus_jian.gif' //开状态图片路径
var expandsymbol='../Library/images/switch_minus_jia.gif' //闭合状态图片路径

/////下面的代码无须改动 //////////////////////////

function getElementbyClass(rootobj, classname){
var temparray=new Array()
var inc=0
var rootlength=rootobj.length
for (i=0; i<rootlength; i++){
if (rootobj[i].className==classname)
temparray[inc++]=rootobj[i]
}
return temparray
}

function sweeptoggle(ec){
var inc=0
while (ccollect[inc]){
ccollect[inc].style.display=(ec=="contract")? "none" : ""
inc++
}
revivestatus()
}


function expandcontent(curobj, cid){
if (ccollect.length>0){
document.getElementById(cid).style.display=(document.getElementById(cid).style.display!="none")? 

"none" : ""
curobj.src=(document.getElementById(cid).style.display=="none")? expandsymbol : contractsymbol
}
}

function revivecontent(){
selectedItem=getselectedItem()
selectedComponents=selectedItem.split("|")
for (i=0; i<selectedComponents.length-1; i++)
document.getElementById(selectedComponents[i]).style.display="none"
}

function revivestatus(){
var inc=0
while (statecollect[inc]){
if (ccollect[inc].style.display=="none")
statecollect[inc].src=expandsymbol
else
statecollect[inc].src=contractsymbol
inc++
}
}

function get_cookie(Name) { 
var search = Name + "="
var returnvalue = "";
if (document.cookie.length > 0) {
offset = document.cookie.indexOf(search)
if (offset != -1) { 
offset += search.length
end = document.cookie.indexOf(";", offset);
if (end == -1) end = document.cookie.length;
returnvalue=unescape(document.cookie.substring(offset, end))
}
}
return returnvalue;
}

function getselectedItem(){
if (get_cookie(window.location.pathname) != ""){
selectedItem=get_cookie(window.location.pathname)
return selectedItem
}
else
return ""
}

function saveswitchstate(){
var inc=0, selectedItem=""
while (ccollect[inc]){
if (ccollect[inc].style.display=="none")
selectedItem+=ccollect[inc].id+"|"
inc++
}
if (get_cookie(window.location.pathname)!=selectedItem){ 
var expireDate = new Date()
expireDate.setDate(expireDate.getDate()+parseInt(memoryduration))
document.cookie = window.location.pathname+"="+selectedItem+";path=/;expires=" + 

expireDate.toGMTString()
}
}

function do_onload(){
uniqueidn=window.location.pathname+"firsttimeload"
var alltags=document.all? document.all : document.getElementsByTagName("*")
ccollect=getElementbyClass(alltags, "switchcontent")
statecollect=getElementbyClass(alltags, "showstate")
if (enablepersist=="on" && get_cookie(window.location.pathname)!="" && ccollect.length>0)
revivecontent()
if (ccollect.length>0 && statecollect.length>0)
revivestatus()
}

if (window.addEventListener)
window.addEventListener("load", do_onload, false)
else if (window.attachEvent)
window.attachEvent("onload", do_onload)
else if (document.getElementById)
window.onload=do_onload

if (enablepersist=="on" && document.getElementById)
window.onunload=saveswitchstate