function setCaret(textObj){  
  if(textObj.createTextRange){    
    textObj.caretPos=document.selection.createRange().duplicate();    
  }  
}

function insertAtCaret(textObj,textFeildValue){  
  if(document.all){    
    if(textObj.createTextRange&&textObj.caretPos){      
      var caretPos=textObj.caretPos;      
      caretPos.text=caretPos.text.charAt(caretPos.text.length-1)==''?textFeildValue+'':textFeildValue;
    }else {      
      textObj.value=textFeildValue;      
    }    
  }else {    
    if(textObj.setSelectionRange){      
      var rangeStart=textObj.selectionStart;      
      var rangeEnd=textObj.selectionEnd;      
      var tempStr1=textObj.value.substring(0,rangeStart);      
      var tempStr2=textObj.value.substring(rangeEnd);      
      textObj.value=tempStr1+textFeildValue+tempStr2;      
    }else {      
      alert("请升级您的浏览器!");      
    }    
  }  
}  
//用法说明:注意textarea上一定要加上相关代码
//<form id="form1" action="" onsubmit="" method="post" enctype="text/plain">     
//<p>   
//<textarea name="tarea" rows="" cols="" style="width:300px;height:120px;" onselect="setCaret(this);" onclick="setCaret(this);" onkeyup="setCaret(this);">
//Dnew.cn  Dnew.cn
//</textarea>   
//<br/><br/>   
//<input type="text" name="textfield" style="width:220px;" value="插入FireFox"/>   
//<br/>   
//<input type="button" value="插入" onclick="insertAtCaret(this.form.tarea,this.form.textfield.value);"/>   
//</p>   
//</form> 