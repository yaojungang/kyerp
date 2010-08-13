/** ***************************************************************************** */
/*
 * 查询并选择供应商 依赖库：SearchField.js, ComboBoxTree.js
 * 
 * selectSupplier = new SelectSupplier({ el:'select-Supplier',
 * onSelect:function(rec){ alert(rec.data.short_name) selectSupplier.hide(); } })
 */
org.kyerp.warehouse.supplierStore = new Ext.data.Store({
			autoLoad : {
				baseParams : {
					limit : 20
				}
			},
			autoLoad : true,
			url : org.kyerp.warehouse.SupplierPanel_STORE_URL,
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						idProperty : "id"
					}, new Ext.data.Record.create([{
								name : "id",
								type : "int"
							}, {
								name : "name",
								type : "string"
							}, {
								name : "nameSpell",
								type : "string"
							}, {
								name : "fullName",
								type : "string"
							}, {
								name : "qualified",
								type : 'boolean'
							}]))
		});
/** ***************************************************************************** */
org.kyerp.warehouse.SelectSupplierWindow = Ext.extend(Ext.Window, {
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				this.grid = new Ext.grid.GridPanel({
							store : org.kyerp.warehouse.supplierStore,
							columns : [new Ext.grid.RowNumberer(), {
										header : "ID",
										dataIndex : "id",
										align : "center",
										width : 50,
										menuDisabled : true
									}, {
										header : "名称",
										dataIndex : "name",
										menuDisabled : true
									}, {
										header : "简拼",
										dataIndex : "nameSpell",
										width : 50,
										menuDisabled : true
									}, {
										header : "全称",
										dataIndex : "fullName",
										width : 150,
										menuDisabled : true
									}, {
										header : "合格供方",
										dataIndex : "qualified",
										menuDisabled : true
									}],
							bbar : new Ext.PagingToolbar({
										pageSize : 20,
										store : org.kyerp.warehouse.supplierStore,
										displayInfo : true
									}),
							border : false
						});
				org.kyerp.warehouse.SelectSupplierWindow.superclass.constructor
						.call(this, {
									title : '选择供应商',
									width : 600,
									height : 400,
									closeAction : 'hide',
									modal : true,
									layout : 'fit',
									items : this.grid,
									buttons : [{
										text : '确定',
										handler : function() {
											var rec = this.grid
													.getSelectionModel()
													.getSelected();
											if (rec && _cfg['onSelect']) {
												_cfg['onSelect'](rec, this);
											}
										},
										scope : this
									}, {
										text : '取消',
										handler : function() {
											this.hide()
										},
										scope : this
									}]
								});
			}
		});

/** ***************************************************************************** */