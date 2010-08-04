/** ***************************************************************************** */
/*
 * Ext.MessageBox.INFO Ext.MessageBox.WARNING Ext.MessageBox.QUESTION
 * Ext.MessageBox.ERROR
 */
/** ***************************************************************************** */
function showMsg(title, msg) {
	// 显示一个使用配置选项的对话框： Show a dialog using config options:
	Ext.MessageBox.show({
		title : title,
		msg : msg,
		icon : Ext.MessageBox.WARNING,
		buttons : Ext.MessageBox.OK,
		modal : true
	});
}
/** ***************************************************************************** */
function showExceptionMsg(title, msg, text) {
	// 显示一个使用配置选项的对话框： Show a dialog using config options:
	Ext.MessageBox.show({
		title : title,
		msg : msg,
		value : text,
		defaultTextHeight : 200,
		icon : Ext.MessageBox.ERROR,
		buttons : Ext.MessageBox.OK,
		maxWidth : 800,
		minWidth : 50,
		width : 680,
		maxHeight : 600,
		minHeight : 50,
		height : 580,
		multiline : true,
		modal : true
	});
}
// 对应的处理函数
var debug = true;
function showExtLoadException(This, options, response, error) {
	if (debug) {
		if (error) {
			top.Ext.Msg.alert("错误", "解析数据时发生错误:" + error.message);
			return;
		}
	}

	var status = response.status;
	var text = response.responseText;

	switch (status) {
	case 404:
		showMsg("错误", "加载数据时发生错误:请求url不可用");
		break;
	case 200:
		if (text.length > 0) {
			var data = Ext.decode(text);
			alert(data.success);
			if (data && data.success == false && data.message) {
				showMsg("警告", "业务逻辑发生错误:<br/>" + data.message);
			} else {
				showExceptionMsg("错误", "业务逻辑发生严重错误，请检查您的操作是否正确!" , text);
			}
		}
		break;
	case 0:
		showMsg("错误", "加载数据时发生错误:<br/>" + "远程服务器无响应");
		break;
	default:
		var data = Ext.decode(text);
		if (data && data.message_exceptionMessage) {
			showExceptionMsg("程序异常",
					"业务逻辑发生错误，请检查您的操作是否正确!"+"<br />错误码:" + status + "<br/>错误信息:<br />" + data.message_exceptionMessage,
					data.message_exceptionStackTrace);

		} else {
			showExceptionMsg("程序异常",
					"程序发生严重错误，请向管理员报告！"+	"<br />错误码:" + status,
					"错误信息:" + text);
		}
		break;
	}
}

Ext.override(Ext.data.Connection, {
	request : Ext.data.Connection.prototype.request.createSequence(function() {
		Ext.get('loading').show();
	}),
	handleResponse : Ext.data.Connection.prototype.handleResponse
			.createSequence(function() {
				Ext.get('loading').hide();
			}),
	doFormUpload : Ext.data.Connection.prototype.doFormUpload
			.createSequence(function() {
				Ext.get('loading').hide();
			}),
	handleFailure : Ext.data.Connection.prototype.handleFailure
			.createSequence(function(response, e) {
				Ext.get('loading').hide();
				var status = response.status;
				var text = response.responseText;
				showExtLoadException(null, null, response, e);
			})
});

// form submit 错误
function onSubmitFailureGetMessage(form, action) {
	if (action.failureType === Ext.form.Action.CONNECT_FAILURE) {
		// action.response.status != 200
		// 用来表示 服务器发生了不可预料的错误。如抛出了未处理的异常
		var result = Ext.decode(action.response.responseText);
		showExceptionMsg("程序异常", "程序发生严重错误，请向管理员报告！<br/>错误码:" + action.response.status,result.message_exceptionMessage);

	}
	if (action.failureType === Ext.form.Action.SERVER_INVALID) {
		// 返回值 success = false
		// action.response.status = 200
		// 用来表示 业务错误
		showMsg('业务错误', action.result.message);
	}
}
/*
 * // 统一的对loadexception的错误处理，主要针对grid if (Ext.data.Store) { var _constructorFn =
 * Ext.data.Store.prototype.constructor; Ext.data.Store.prototype.constructor =
 * function(A) { _constructorFn.call(this, A); if
 * (!this.hasListener('loadexception')) { //alert("has loadexception");
 * this.on('loadexception', showExtLoadException); }; } }; //
 * 统一的对loadexception的错误处理，主要针对tree if (Ext.tree.TreeLoader) { var _constructorL =
 * Ext.tree.TreeLoader.prototype.constructor;
 * Ext.tree.TreeLoader.prototype.constructor = function(A) {
 * _constructorL.call(this, A); if (!this.hasListener('loadexception')) {
 * this.on('loadexception', showExtLoadException); } } };
 * 
 */