Ext.override(Ext.form.ComboBox, {
			setValue : function(node) {
				if (typeof node == "object") {
					// 当node为object对象时 node和tree里面的对应
					this.lastSelectionText = node.text;
					// 设置显示文本为node的text
					this.setRawValue(node.text);
					if (this.hiddenField) {
						// 设置隐藏值为node的id
						this.hiddenField.value = node.id;
					}
					this.value = node.id;
					return this;
				} else {
					// 当node为文本时 这段代码是从combo的源码中拷贝过来的 具体作用不细说了
					var text = node;
					if (this.valueField) {
						var r = this.findRecord(this.valueField, node);
						if (r) {
							text = r.data[this.displayField];
						} else if (Ext.isDefined(this.valueNotFoundText)) {
							text = this.valueNotFoundText;
						}
					}
					this.lastSelectionText = text;
					if (this.hiddenField) {
						this.hiddenField.value = node;
					}
					Ext.form.ComboBox.superclass.setValue.call(this, text);
					this.value = node;
					return this;
				}
			}
		})