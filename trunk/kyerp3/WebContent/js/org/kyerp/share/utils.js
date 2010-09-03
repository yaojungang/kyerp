// 修正小数点
Number.prototype.toFixed = function(d) {
	var s = this + "";
	if (!d)
		d = 0;
	if (s.indexOf(".") == -1)
		s += ".";
	s += new Array(d + 1).join("0");
	if (new RegExp("^(-|\\+)?(\\d+(\\.\\d{0," + (d + 1) + "})?)\\d*$").test(s)) {
		var s = "0" + RegExp.$2, pm = RegExp.$1, a = RegExp.$3.length, b = true;
		if (a == d + 2) {
			a = s.match(/\d/g);
			if (parseInt(a[a.length - 1]) > 4) {
				for (var i = a.length - 2; i >= 0; i--) {
					a[i] = parseInt(a[i]) + 1;
					if (a[i] == 10) {
						a[i] = 0;
						b = i != 1;
					} else
						break;
				}
			}
			s = a.join("").replace(new RegExp("(\\d+)(\\d{" + d + "})\\d$"),
					"$1.$2");
		}
		if (b)
			s = s.substr(1);
		return (pm + s).replace(/\.$/, "");
	}
	return this + "";
};
//http://efreedom.com/Question/1-1287584/Force-Display-Decimal-ExtJS-NumberField-Certain-Precision
/*
Ext.override(Ext.form.NumberField, {
    setValue : function(v) {
        v = typeof v == 'number' ? v : String(v).replace(this.decimalSeparator,
                ".");
        v = isNaN(v) ? '' : String(v).replace(".", this.decimalSeparator);
        return Ext.form.NumberField.superclass.setValue.call(this, v);
    },
    fixPrecision : function(value) {
        var nan = isNaN(value);
        if (!this.allowDecimals || this.decimalPrecision == -1 || nan || !value) {
            return nan ? '' : value;
        }
        return parseFloat(value).toFixed(this.decimalPrecision);
    }
});
*/
// 根据单据状态显示不同的颜色
statusRenderer = function(value) {
	switch (value) {
		case '编制' :
			return "<font color=blue>" + value + "</font>";
		case '等待审核' :
			return "<font color=purple>" + value + "</font>";
		case '已审核' :
			return "<font color=black>" + value + "</font>";
		case '已冲销' :
			return "<font color=red>" + value + "</font>";
		default :
			return value;
	}
};

// 窗口大小
/*
 * 网页可见区域宽：document.body.clientWidth 网页可见区域高：document.body.clientHeight
 * 网页可见区域宽：document.body.offsetWidth (包括边线的宽) 网页可见区域高：document.body.offsetHeight
 * (包括边线的宽) 网页正文全文宽：document.body.scrollWidth 网页正文全文高：document.body.scrollHeight
 * 网页被卷去的高：document.body.scrollTop 网页被卷去的左：document.body.scrollLeft
 * 网页正文部分上：window.screenTop 网页正文部分左：window.screenLeft
 * 屏幕分辨率的高：window.screen.height 屏幕分辨率的宽：window.screen.width
 * 屏幕可用工作区高度：window.screen.availHeight 屏幕可用工作区宽度：window.screen.availWidth var s =
 * ""; s += "\r\n网页可见区域宽："+ document.body.clientWidth; s += "\r\n网页可见区域高："+
 * document.body.clientHeight; s += "\r\n网页可见区域高："+ document.body.offsetWeight +"
 * (包括边线的宽)"; s += "\r\n网页可见区域高："+ document.body.offsetHeight +" (包括边线的宽)"; s +=
 * "\r\n网页正文全文宽："+ document.body.scrollWidth; s += "\r\n网页正文全文高："+
 * document.body.scrollHeight; s += "\r\n网页被卷去的高："+ document.body.scrollTop; s +=
 * "\r\n网页被卷去的左："+ document.body.scrollLeft; s += "\r\n网页正文部分上："+
 * window.screenTop; s += "\r\n网页正文部分左："+ window.screenLeft; s +=
 * "\r\n屏幕分辨率的高："+ window.screen.height; s += "\r\n屏幕分辨率的宽："+
 * window.screen.width; s += "\r\n屏幕可用工作区高度："+ window.screen.availHeight; s +=
 * "\r\n屏幕可用工作区宽度："+ window.screen.availWidth; alert(s);
 */
ExtWindowResize = function(window) {
	/*
	 * if(!window.rendered){ var browerWidth = kyerpApp.viewport.getWidth(); var
	 * browerHeight = kyerpApp.viewport.getHeight(); var browerTop =
	 * window.screenTop; var browerLeft = window.screenLeft;
	 * window.setWidth(browerWidth - 100); window.setHeight(browerHeight - 100);
	 * window.setPosition(10,10); //window.doLayout();
	 */
	// }
	// window.center();
	// alert(Ext.encode(kyerpApp.viewport.getPosition()));
	// alert("browerWidth:"+browerWidth+"\nbrowerHeight:"+browerHeight);
};

