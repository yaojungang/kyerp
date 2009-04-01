/*
| -------------------------------------------------------------------
| Dialog 浮动提示框 发布版本3 【功能说明】
| -------------------------------------------------------------------
| 支持浏览器 FF, IE, OP
| 支持拖动，自定义标题，宽度，高度，内容。
| 自定义按钮是否显示，确定，取消，关闭。
| 支持打开新窗口，支持嵌入网页。
| IE,下对Select完整遮罩
|
| -------------------------------------------------------------------
| Dialog 浮动提示框 发布版本3 【使用方法】
| -------------------------------------------------------------------
| 在需要使用的页面中，最下面</body>前加入脚本
| <script type="text/javascript">new dialog().init();</script>
| 目前暂时只能这么做，背景遮罩将失去效果
|
| 调用提示框的脚本是 new dialog().event();
|
*/

//检测浏览器
var userAgent = navigator.userAgent.toLowerCase();
var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
var is_moz = (navigator.product == 'Gecko') && userAgent.substr(userAgent.indexOf('firefox') + 8, 3);
var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera) && userAgent.substr(userAgent.indexOf('msie') + 5, 3);

function dialog(){
	//设置提示框默认标题,宽度，高度，边框颜色
	var title      = '系统提示信息';
	var width      = 520;
	var height     = 320;
	var bodercolor = "#000";
	
	//设置提示框标题 背景颜色，字体颜色，字体形状
	var titlebgcolor    = "#000";
	var titlefontcolor  = "#fff";
	var titlefontweight = "bold";
	
	//设置提示框内容 背景颜色，字体颜色
	var bodybgcolor   = "#fff"; 
	var bodyfontcolor = "#000";
	
	//默认阴影颜色,透明度(0-100),阴影宽度，高度
	var ShadowColor   = "#000";
	var ShadowOpacity = 60;
	var ShadowWdith   = 6;
	var ShadowHeight  = 6;
	
	//背景遮罩层,最小透明度(0-100)、最大透明度(0-100)、增加减少数(0-100),运行速度(1000=1秒)
	var ScreenOverSwitch       = 1;
	var ScreenOverBgColor      = "#000";
	var ScreenOverOpacityMin   = 0;
	var ScreenOverOpacityMax   = 40;
	var ScreenOverOpacitySpeed = 5;
	var ScreenOverOpacitySleep = 50;
	
	//图片浏览盒最小宽度、高度
	var ImageViewBoxWidthMin  = 500;
	var ImageViewBoxHeightMin = 500;
	
	//页面默认滚动条状态
	var bodyoverflow = "hidden";
	
	//展示图片状态
	var zoomobj = Array()
	
	//默认按钮 设置（确认，取消）
	var sFunc  = '<input id="dialogOk" type="button" style="background:#fff;width:60px;height:20px;border:#000 1px solid;line-height:20px;" value="确认" onclick="new dialog().reset();" />  <input id="dialogCancel" type="button" style="background:#fff;width:60px;height:20px;border:#000 1px solid;line-height:20px;" value="取消" onclick="new dialog().reset();" /> ';
	
	//默认关闭按钮
	var sClose = '<input type="image" id="dialogBoxClose" onclick="new dialog().reset();" src="CloseOut.gif" border="0" width="17" height="17" onmouseover="this.src=\'CloseOver.gif\';" onmouseout="this.src=\'CloseOut.gif\';" align="absmiddle" />';
	
	//设置初始提示框内容
	var sBody = '\
		<table id="dialogBodyBox" border="0" align="center" cellpadding="0" cellspacing="0">\
			<tr height="10"><td colspan="4"></td></tr>\
			<tr>\
				<td width="10"></td>\
				<td id="dialogMsg" style="font-size:12px;color:'+bodyfontcolor+';"></td>\
				<td width="10"></td>\
			</tr>\
			<tr height="10"><td colspan="3" align="center"></td></tr>\
			<tr><td id="dialogFunc" colspan="3" align="center">' + sFunc + '</td></tr>\
			<tr height="10"><td colspan="3" align="center"></td></tr>\
		</table>\
	';
	
	//设置初始提示框框架
	var sBox = '\
		<table id="dialogBox" width="' + width + '" border="0" cellpadding="0" cellspacing="0" style="border:1px solid '+bodercolor+';display:none;z-index:100;">\
			<tr height="1" style="background:#fff;"><td></td></tr>\
			<tr height="25" style="background:'+titlebgcolor+';">\
				<td>\
					<table onselectstart="return false;" style="-moz-user-select:none;" width="100%" border="0" cellpadding="0" cellspacing="0">\
						<tr>\
							<td width="6"></td>\
							<td id="dialogBoxTitle" onmousedown="new dialog().moveStart(event, \'dialogBox\')" style="color:'+titlefontcolor+';cursor:move;font-size:12px;font-weight:'+titlefontweight+';">'+title+'</td>\
							<td id="dialogClose" width="27" align="right" valign="middle">\
								' + sClose + '\
							</td>\
							<td width="6"></td>\
						</tr>\
					</table>\
				</td>\
			</tr>\
			<tr id="dialogHeight" style="height:' + height + '">\
				<td id="dialogBody" style="background:'+bodybgcolor+';color:'+bodyfontcolor+';">' + sBody + '</td>\
			</tr>\
		</table>\
		<div id="dialogBoxHideImageViewBox" style="visibility:hidden;display:none!important;display:block;"><img id="HideImage" /></div>\
		<div id="dialogBoxHideIFrame" style="display:block;z-index:98;"><iframe id="HideIFrame" src="about:blank" scrolling="no" frameborder="0" style="width:100%;height:100%;"></iframe></div>\
		<div id="dialogBoxShadow" style="display:block;z-index:99;"><iframe id="ShadowHideIFrame" src="about:blank" scrolling="no" frameborder="0" style="width:100%;height:100%;"></div>\
	';
	
	//自定义$函数，方便使用 document.getElementById()
	//参数一(_sId)：对象ID名称
	function $(_sId){return document.getElementById(_sId)}
	
	//显示提示框
	//无参数
	this.show = function(){
		//判断提示框是否存在，不存在则初始化
		$('dialogBodyBox') ? function(){} : this.init();
		
		if(ScreenOverSwitch){
			//显示背景遮罩
			this.showSO();
		}else{
			this.showSO(1,1);
		}
		
		//显示提示框
		this.middle('dialogBox');
		
		//显示提示框阴影
		this.shadow();
	}
	
	//重置提示框内容，隐藏遮罩
	//无参数
	this.reset = function(){
		//关闭提示框
		$('dialogBox').style.display='none';
		
		//关闭阴影
		$('dialogBoxShadow').style.display = "none";
		
		//关闭IE屏蔽,用来屏蔽SELECT
		$('dialogBoxHideIFrame').style.display = "none";
		
		//初始化提示框内容
		$('dialogBody').innerHTML = sBody;
		
		if(ScreenOverSwitch){
			//隐藏背景遮罩
			this.hideSO();
		}else{
			this.hideSO(1,1);
		}
		
		//设置提示框按钮，1显示，0隐藏
		$('dialogOk').style.display = 'block';
		$('dialogCancel').style.display = 'block';
		$('dialogBoxClose').style.display = 'block';
	}
	
	//设置提示框内容,刷新提示框
	//参数一(_sHtml)：提示框内容可以是HTML
	this.html = function(_sHtml){
		//设置提示框内容
		$("dialogBody").innerHTML = _sHtml;
		
		//显示提示框(重复使用，等于刷新)
		this.show();
	}
	
	//初始化提示框
	//无参数
	this.init = function(){
		//检测是否存在提示框容器，存在则移除
		$('dialogCase') ? $('dialogCase').parentNode.removeChild($('dialogCase')) : function(){};
		
		//创建提示框容器
		var oDiv = document.createElement('span');
		
		//设置容器ID
		oDiv.id = "dialogCase";
		
		//放入提示框框架
		oDiv.innerHTML = sBox;
		
		//将容器+如页面
		document.body.appendChild(oDiv);
		
		//重置提示框内容
		this.reset();
	}
	
	//显示提示框阴影
	//无参数
	this.shadow = function(){
		//获取阴影层属性
		var oShadow = $('dialogBoxShadow');
		
		//获取提示框属性
		var oDialog = $('dialogBox');
		
		//获取IE屏蔽层属性
		var oIFrame = $('dialogBoxHideIFrame');
		
		//设置阴影属性
		oShadow['style']['position'] = "absolute";
		oShadow['style']['background']	= ShadowColor;
		oShadow['style']['display']	= "";
		oShadow['style']['opacity']	= parseInt(ShadowOpacity)/100;
		oShadow['style']['filter'] = "alpha(opacity="+ShadowOpacity+")";
		oShadow['style']['top'] = oDialog.offsetTop + ShadowHeight + "px";
		oShadow['style']['left'] = oDialog.offsetLeft + ShadowWdith + "px";
		oShadow['style']['width'] = oDialog.offsetWidth + "px";
		oShadow['style']['height'] = oDialog.offsetHeight + "px";
		
		//设置IE屏蔽层属性
		oIFrame['style']['display']  = "";
		oIFrame['style']['position'] = "absolute";
		oIFrame['style']['top'] = oDialog.offsetTop + "px";
		oIFrame['style']['left'] = oDialog.offsetLeft + "px";
		oIFrame['style']['width'] = oDialog.offsetWidth + "px";
		oIFrame['style']['height'] = oDialog.offsetHeight + "px";
		
		//设置阴影层背景颜色
		var editor;
		editor = $("ShadowHideIFrame").contentWindow;
		
		// 针对IE浏览器, 可编辑
		editor.document.designMode = 'on';
		editor.document.contentEditable = true;
		
		 // 兼容 FireFox, 打开并写入
		editor.document.open();
		editor.document.writeln('<body style="background:'+ShadowColor+';margin:0px;"></body>');
		editor.document.close();
	}
	
	//嵌入打开网页
	//参数一(_sUrl)： 嵌入网页地址
	//参数二(_sMode)：滚动条模式（值: no, yes, auto）
	//参数三()：      提示框标题
	//参数四()：      提示框宽度
	//参数五()：      提示框高度
	this.open = function(_sUrl, _sMode){
		//获取隐藏参数内容，标题，宽度，高度，如果空，则使用默认
		var _sTitle=arguments[2]?arguments[2]:title;
		var _sWidth=arguments[3]?arguments[3]:width;
		var _sHeight=arguments[4]?arguments[4]:height;
		
		//设置提示框属性
		this.set('title',_sTitle);
		this.set('width',_sWidth);
		this.set('height',_sHeight);
		
		//显示提示框
		this.show();
		
		//嵌入网页
		$("dialogBody").innerHTML = "<iframe id='dialogFrame' width='"+_sWidth+"' height='"+_sHeight+"' frameborder='0' scrolling='" + _sMode + "'></iframe>";
		
		//设置网页地址
		$("dialogFrame").src = _sUrl;
	}
	
	//打开新窗口(居中且只有标题栏，不含工具栏，菜单栏等)
	//参数一(_sUrl)：   网页地址
	//参数一(_iWidth)： 窗口宽度
	//参数一(_iHeight)：窗口高度
	//参数四(_sMode)：  滚动条模式（值: no, yes, auto）
	this.showWindow = function(_sUrl, _iWidth, _iHeight, _sMode){
		var oWindow;
		//获取窗口居中位置
		var sLeft = (screen.width) ? (screen.width - _iWidth)/2 : 0;
		var sTop = (screen.height) ? (screen.height - _iHeight)/2 : 0;
		
		//打开新窗口
		oWindow = window.open(_sUrl, '', 'height=' + _iHeight + ', width=' + _iWidth + ', top=' + sTop + ', left=' + sLeft + ', toolbar=no, menubar=no, scrollbars=' + _sMode + ', resizable=no,location=no, status=no');
	}
	
	//调用提示框
	//参数一(_sMsg)：   提示信息
	//参数二(_sOk)：    是否显示“确认”按钮(显示：1，不显示:0)
	//参数三(_sCancel)：是否显示“取消”按钮(显示：1，不显示:0)
	//参数四(_sClose)： 是否显示“关闭”按钮(显示：1，不显示:0)
	//参数五()：        提示框标题
	//参数六()：        提示框宽度
	//参数七()：        提示框高度
	this.event = function(_sMsg, _sOk, _sCancel, _sClose){
		//写入 确认，取消按钮
		$('dialogFunc').innerHTML = sFunc;
		
		//写入 关闭按钮
		$('dialogClose').innerHTML = sClose;
		
		//如果提示信息层不存在，则写入提示信息层
		$('dialogBodyBox') == null ? $('dialogBody').innerHTML = sBody : function(){};
		
		//写入提示信息
		$('dialogMsg') ? $('dialogMsg').innerHTML = _sMsg  : function(){};
		
		//获取隐藏参数内容，标题，宽度，高度，如果空，则使用默认
		var _sTitle=arguments[4]?arguments[4]:title;
		var _sWidth=arguments[5]?arguments[5]:width;
		var _sHeight=arguments[6]?arguments[6]:height;
		
		//设置提示框
		this.set('title',_sTitle);
		this.set('width',_sWidth);
		this.set('height',_sHeight);
		
		//设置提示框按钮，1显示，0隐藏
		_sOk ? $('dialogOk').focus() : $('dialogOk').style.display = 'none';
		_sCancel ? function(){} : $('dialogCancel').style.display = 'none';
		_sClose ? function(){} : $('dialogBoxClose').style.display = 'none';
		
		//显示提示框
		this.show();
	}
	
	//嵌入打开网页
	//参数一(_sImage)： 图片ID
	//参数二()：        图片名称
	this.imageview = function(_sImage){
		$("HideImage").src = _sImage.src;
		
		//获取隐藏参数内容，标题，宽度，高度，如果空，则使用默认
		var _sTitle=arguments[1]?arguments[1]:"&nbsp;";		
		var _sWidth=$("HideImage").width;
		var _sHeight=$("HideImage").height;
		
		if(_sHeight>document.body.clientHeight){ 
			_sHeight = document.body.clientHeight-30-ShadowHeight;
		}
		
		//设置提示框属性
		this.set('title',_sTitle);
		this.set('width',_sWidth);
		this.set('height',_sHeight);
		
		//显示提示框
		this.show();
		
		//嵌入图片
		$("dialogBody").innerHTML = '<img id="ViewImage" src="'+_sImage.src+'" height="'+_sHeight+'" />';
		this.set('width',$("ViewImage").width);
		
		//获取图片浏览最大值
		zoomobj['zimginfo'] = [parseInt($("ViewImage").width), parseInt($("ViewImage").height)]
		
		//检测浏览器，赋予滚轮事件
		if(is_ie){
			$('ViewImage').onmousewheel = this.imgzoom;
		} else {
			$('ViewImage').addEventListener("DOMMouseScroll", this.imgzoom, false);
		}
	}
	
	//图片预览大小缩放
	this.imgzoom = function(event){
		//获取窗口事件
		oEvent = window.event ? window.event : event;
		
		//检测滚轮
		if(oEvent.wheelDelta <= 0 || oEvent.detail > 0) {
			if(parseInt($('ViewImage').height) <= parseInt(ImageViewBoxHeightMin)) {
				$('ViewImage').height = parseInt(ImageViewBoxHeightMin);
			}else{
				$('ViewImage').height -= parseInt(zoomobj['zimginfo'][1] / 5);
			}
		} else {
			if(parseInt($('ViewImage').height) >= parseInt(zoomobj['zimginfo'][1])) {
				$('ViewImage').height = parseInt(zoomobj['zimginfo'][1]);
			}else{
				$('ViewImage').height += parseInt(zoomobj['zimginfo'][1] / 5);
			}
		}
		
		//获取阴影层，提示框，提示框高度，IE遮罩层
		var oShadow = $('dialogBoxShadow');
		var oDialog = $('dialogBox');
		var oHeight = $('dialogHeight');
		var oHFrame = $('dialogBoxHideIFrame');
		
		//重置提示框属性
		oDialog['style']['width'] = parseInt($('ViewImage').width)+'px';
		oHeight['style']['height'] = parseInt($('ViewImage').height)+'px';
		
		//重置提示框位置
		oDialog['style']['position'] = "absolute";
		oDialog['style']['left'] = (document.body.clientWidth / 2) - (oDialog.offsetWidth / 2) + "px";
		oDialog['style']['top'] = (document.body.clientHeight / 2 + document.body.scrollTop) - (oDialog.offsetHeight / 2) + "px";
		
		//重置阴影位置
		oShadow['style']['opacity'] = parseInt(ShadowOpacity)/100;
		oShadow['style']['filter']  = "alpha(opacity="+ShadowOpacity+")";
		oShadow['style']['top']     = parseInt(oDialog.offsetTop) + ShadowHeight + "px";
		oShadow['style']['left']    = parseInt(oDialog.offsetLeft) + ShadowWdith + "px";
		oShadow['style']['width']   = parseInt(oDialog.offsetWidth) + "px";
		oShadow['style']['height']  = parseInt(oDialog.offsetHeight) + "px";
		
		//重置IE屏蔽层位置
		oHFrame['style']['left']    = parseInt(oDialog.offsetLeft) + "px";
		oHFrame['style']['top']     = parseInt(oDialog.offsetTop) + "px";
		oHFrame['style']['width']   = parseInt(oDialog.offsetWidth) + "px";
		oHFrame['style']['height']  = parseInt(oDialog.offsetHeight) + "px";
		
	}
	
	//提示框设置
	//参数一(_oAttr)：   设置项名称
	//参数二(_sVal)：    设置内容
	this.set = function(_oAttr, _sVal){
		
		//获取阴影层，提示框，提示框高度，IE遮罩层
		var oShadow = $('dialogBoxShadow');
		var oDialog = $('dialogBox');
		var oHeight = $('dialogHeight');
		var oHFrame = $('dialogBoxHideIFrame')

		//设置内容不为空
		if(_sVal != ''){
			//判断项名称
			switch(_oAttr){
				//设置标题
				case 'title':
					$('dialogBoxTitle').innerHTML = _sVal;
					title = _sVal;
					break;
					
				//设置提示框宽
				case 'width':
					oDialog['style']['width'] = _sVal+'px';
					width = _sVal;
					break;
					
				//设置提示框高
				case 'height':
					oHeight['style']['height'] = _sVal+'px';
					height = _sVal;
					break;
				
				//设置遮罩层状态
				case 'ScreenOverSwitch':
					ScreenOverSwitch = _sVal;
					break;
					
				//设置提示框遮罩层颜色
				case 'ScreenOverBgColor':
					ScreenOverBgColor = _sVal;
					break;
					
				//设置阴影颜色
				case 'shadowcolor':
					oShadow['style']['background']	= _sVal;
					//设置阴影层背景颜色
					var editor;
					editor = $("ShadowHideIFrame").contentWindow;

					// 针对IE浏览器, 可编辑
					editor.document.designMode = 'on';
					editor.document.contentEditable = true;

					 // 兼容 FireFox, 打开并写入
					editor.document.open();
					editor.document.writeln('<body style="background:'+_sVal+';margin:0px;"></body>');
					editor.document.close();
					ShadowColor = _sVal;
					break;
				
				//设置阴影透明度
				case 'shadowopacity':
					ShadowOpacity = _sVal;
					break;
				
				//设置阴影宽度
				case 'shadowwdith':
					ShadowWdith = _sVal;
					break;
				
				//设置阴影高度
				case 'shadowheight':
					ShadowHeight = _sVal;
					break;
			}
		}
		
		//刷新提示框
		this.middle('dialogBox');
		
		//重置阴影位置
		oShadow['style']['opacity'] = parseInt(ShadowOpacity)/100;
		oShadow['style']['filter']  = "alpha(opacity="+ShadowOpacity+")";
		oShadow['style']['top']     = oDialog.offsetTop + ShadowHeight + "px";
		oShadow['style']['left']    = oDialog.offsetLeft + ShadowWdith + "px";
		oShadow['style']['width']   = oDialog.offsetWidth + "px";
		oShadow['style']['height']  = oDialog.offsetHeight + "px";
		
		//重置IE屏蔽层位置
		oHFrame['style']['left']    = oDialog.offsetLeft + "px";
		oHFrame['style']['top']     = oDialog.offsetTop + "px";
		oHFrame['style']['width']   = oDialog.offsetWidth + "px";
		oHFrame['style']['height']  = oDialog.offsetHeight + "px";
	}
	
	//拖动提示框
	//参数一(event)：   点击事件，使用的时候赋值event就可以
	//参数二(_sId)：    拖动对象ID
	this.moveStart = function (event, _sId){
		//获取拖动对象
		var oObj = $(_sId);
		
		//赋予对象拖动操作
		oObj.onmousemove = mousemove;
		oObj.onmouseup = mouseup;
		
		//捕获鼠标移动
		oObj.setCapture ? oObj.setCapture() : function(){};
		
		//获取窗口事件
		var oEvent = window.event ? window.event : event;
		
		//保留鼠标位置
		var dragData = {x : oEvent.clientX, y : oEvent.clientY};
		
		//保留对象位置
		var backData = {x : parseInt(oObj.style.top), y : parseInt(oObj.style.left)};
		
		//鼠标移动
		function mousemove(event){
			//获取窗口事件
			oEvent = window.event ? window.event : event;
			
			//计算提示框位置
			var iLeft = oEvent.clientX - dragData["x"] + parseInt(oObj.style.left);
			var iTop = oEvent.clientY - dragData["y"] + parseInt(oObj.style.top);
			
			//设置提示框位置
			oObj.style.left = iLeft + "px";
			oObj.style.top = iTop + "px";
			
			//设置IE屏蔽层位置
			$('dialogBoxHideIFrame').style.left = iLeft + "px";
			$('dialogBoxHideIFrame').style.top = iTop + "px";
			
			//设置阴影位置
			$('dialogBoxShadow').style.left = iLeft + ShadowWdith + "px";
			$('dialogBoxShadow').style.top = iTop + ShadowHeight + "px";
			
			//刷新保留鼠标位置
			dragData = {x: oEvent.clientX, y: oEvent.clientY};
		}
		
		//鼠标键抬起
		function mouseup(event){
			//获取窗口事件
			oEvent = window.event ? window.event : event;
			
			//清除对象拖动操作
			oObj.onmousemove = null;
			oObj.onmouseup = null;
			
			//如果鼠标是否超出浏览器范围
			if(oEvent.clientX < 1 || oEvent.clientY < 1 || oEvent.clientX > document.body.clientWidth || oEvent.clientY > document.body.clientHeight){
				//还原提示框位置
				oObj.style.left = backData.y + "px";
				oObj.style.top = backData.x + "px";
				
				//还原IE屏蔽层位置
				$('dialogBoxHideIFrame').style.left =  backData.y + "px";
				$('dialogBoxHideIFrame').style.top = backData.x + "px";
				
				//还原阴影位置
				$('dialogBoxShadow').style.left = backData.y + ShadowWdith + "px";
				$('dialogBoxShadow').style.top = backData.x + ShadowHeight + "px";
			}
			
			//关闭捕获鼠标移动 
			oObj.releaseCapture ? oObj.releaseCapture() : function(){};
		}
	}
	
	//显示背景遮罩层
	//参数一()：    设置最小透明度，空则使用默认(0-100)
	//参数二()：    设置最大透明度，空则使用默认(0-100)
	//参数三()：    设置逐步增加数值(0-100)
	//参数四()：    设置运行速度(1000=1秒)
	this.showSO = function(){
		//获取背景遮罩层属性
		var objScreen = $("ScreenOver");
		
		//如果背景遮罩层不存在则创建
		if(!objScreen) var objScreen = document.createElement("div");
		
		//设置背景遮罩层ID
		objScreen.id = "ScreenOver";
		
		//获取背景遮罩层样式
		var oS = objScreen.style;
		
		//隐藏页面滚动条
		document.body.style.overflow="hidden";
		
		//获取屏幕宽度，高度
		if (document.body.clientHeight)	{
			var ww = document.body.clientWidth + "px";
			var wh = document.body.clientHeight + "px";
		}else if (window.innerHeight){
			var ww = window.innerWidth + "px";
			var wh = window.innerHeight + "px";
		}else{
			var ww = "100%";
			var wh = "100%";
		}
		
		//设置背景遮罩曾
		oS.display = "block";
		oS.top = oS.left = oS.margin = oS.padding = "0px";
		oS.width = ww;
		oS.height = wh;
		oS.position = "absolute";
		oS.zIndex = "98";
		oS.top=document.body.scrollTop + "px";
		oS.background = ScreenOverBgColor;
		oS.filter = "alpha(opacity=0)";
		oS.opacity = 0;
		oS.MozOpacity = 0;
		
		//加入页面
		document.body.appendChild(objScreen);
		
		//获取所有select控件
		var allselect = document.getElementsByTagName("select");
		var i=0;
		
		//设置所有select控件无效，变暗
		for (i=0; i<allselect.length; i++){
			allselect[i].disabled = true;
			allselect[i].style.background="#aaa";
		}
		
		//获取隐藏参数，最小透明度(0-100)、最大透明度(0-100)、增加减少数(0-100),运行速度(1000=1秒)
		var opacity_min = arguments[0]?arguments[0]:ScreenOverOpacityMin;
		var opacity_max = arguments[1]?arguments[1]:ScreenOverOpacityMax;
		var opacity_speed = arguments[2]?arguments[2]:ScreenOverOpacitySpeed;
		var opacity_sleep = arguments[3]?arguments[3]:ScreenOverOpacitySleep;
		var opacity = parseInt(opacity_min);
		
		//显示背景遮罩层
		function showscreenover(){
			//设置背景遮罩层透明度
			$('ScreenOver').style.filter="alpha(opacity="+opacity+")";
			$('ScreenOver').style.opacity=opacity/100;
			$('ScreenOver').style.MozOpacity=opacity/100;
			
			//如果没有到最大透明度
			if(opacity<parseInt(opacity_max)){
				//减低透明，间隔后再运行
				opacity=opacity+parseInt(opacity_speed);
				setTimeout(showscreenover,parseInt(opacity_sleep));
			}
		}
		showscreenover();
	}
	
	//隐藏背景遮罩层
	//参数一()：    设置最小透明度，空则使用默认(0-100)
	//参数二()：    设置最大透明度，空则使用默认(0-100)
	//参数三()：    设置逐步增加数值(0-100)
	//参数四()：    设置运行速度(1000=1秒)
	this.hideSO = function(){
		//获取隐藏参数，最小透明度(0-100)、最大透明度(0-100)、增加减少数(0-100),运行速度(1000=1秒)
		var opacity_min = arguments[0]?arguments[0]:ScreenOverOpacityMin;
		var opacity_max = arguments[1]?arguments[1]:ScreenOverOpacityMax;
		var opacity_speed = arguments[2]?arguments[2]:ScreenOverOpacitySpeed;
		var opacity_sleep = arguments[3]?arguments[3]:ScreenOverOpacitySleep;
		var opacity = parseInt(opacity_max);
		
		//获取背景遮罩层属性
		var objScreen=$("ScreenOver");
		
		//如果背景遮罩层不存在则建立
		if(!objScreen) var objScreen = document.createElement("div");
		
		//设置背景遮罩层ID 
		objScreen.id = "ScreenOver";
		
		//加入页面
		document.body.appendChild(objScreen);
		
		//隐藏背景遮罩层
		function hiddenscreenover(){
			//设置背景遮罩层透明度
			$('ScreenOver').style.filter="alpha(opacity="+opacity+")";
			$('ScreenOver').style.opacity=opacity/100;
			$('ScreenOver').style.MozOpacity=opacity/100;
			
			//如果未达到最小透明度
			if(opacity>parseInt(opacity_min)){
				//加大透明，间隔后再运行
				opacity=opacity-parseInt(opacity_speed);
				setTimeout(hiddenscreenover,parseInt(opacity_sleep));
			}else{
				//如果达到最小透明度
				//移除背景遮罩层
				document.body.removeChild(objScreen);
				
				//恢复页面滚动条状态
				document.body.style.overflow=bodyoverflow;
				
				//获取所有select控件
				var allselect = document.getElementsByTagName("select");
				for (var i=0; i<allselect.length; i++){
					//设置所有select控件有效,还原颜色
					allselect[i].disabled = false;
					allselect[i].style.background="#fff";
				}
			}
		}
		hiddenscreenover();
	}
	
	//设置提示框属性
	this.middle = function(_sId){
		//设置提示框属性，并调整位置，居中
		$(_sId)['style']['display'] = '';
		$(_sId)['style']['position'] = "absolute";
		$(_sId)['style']['left'] = (document.body.clientWidth / 2) - ($(_sId).offsetWidth / 2) + "px";
		$(_sId)['style']['top'] = (document.body.clientHeight / 2 + document.body.scrollTop) - ($(_sId).offsetHeight / 2) + "px";
	}
}