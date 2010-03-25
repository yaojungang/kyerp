// 统一的对loadexception的错误处理，主要针对grid
if (Ext.data.Store) {
	var _constructorFn = Ext.data.Store.prototype.constructor;
	Ext.data.Store.prototype.constructor = function(A) {
		_constructorFn.call(this, A);
		if (!this.hasListener('loadexception')) {
			//alert("has loadexception");
			this.on('loadexception', showExtLoadException);
		};
	}
};

// 统一的对loadexception的错误处理，主要针对tree
if (Ext.tree.TreeLoader) {
	var _constructorL = Ext.tree.TreeLoader.prototype.constructor;
	Ext.tree.TreeLoader.prototype.constructor = function(A) {
		_constructorL.call(this, A);
		if (!this.hasListener('loadexception')) {
			this.on('loadexception', showExtLoadException);
		}
	}
};

// 对应的处理函数

var debug = false;

function showExtLoadException(This, options, response, error) {
	//alert(response.responseText);
	if (debug) {
		if (error) {
			top.Ext.Msg.alert("错误", "解析数据时发生错误:" + error.message);
			return;
		}
	}

	var status = response.status;
	var text = response.responseText;

	switch (status) {
		case 404 :
			top.Ext.MessageBox.alert("错误", "加载数据时发生错误:请求url不可用");
			break;
		case 200 :
			if (text.length > 0) {
				var data = Ext.decode(text);
				if (data && data.error) {
					top.Ext.MessageBox.alert("错误", "加载数据时发生错误1:<br/>"
									+ text);
				} else {
					top.Ext.MessageBox.alert("错误", "加载数据时发生错误2:<br/>" + text);
				}
			}
			break;
		case 0 :
			top.Ext.MessageBox.alert("错误", "加载数据时发生错误:<br/>" + "远程服务器无响应");
			break;
		default :
			var data = Ext.decode(text);
			if (data && data.error) {
				top.Ext.MessageBox.alert("错误", "加载数据时发生错误3<br/>错误码:" + status
								+ "<br/>错误信息:" + text);
			} else {
				top.Ext.MessageBox.alert("错误", "加载数据时发生错误4<br/>错误码:" + status
								+ "<br/>错误信息:" + text);
			}

			break;
	}

}