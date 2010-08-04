/** ***************************************************************************** */
org.kyerp.warehouse.InventoryOwnerStore = new Ext.data.Store( {
	autoLoad : {
		baseParams : {
			limit : 20
		}
	},
	url : org.kyerp.warehouse.InventoryOwnerPanel_STORE_URL,
	reader : new Ext.data.JsonReader( {
		totalProperty : "totalProperty",
		root : "rows",
		idProperty : "id"
	}, new Ext.data.Record.create( [ {
		name : "id",
		type : "int"
	}, {
		name : "name",
		type : "string"
	} ]))
});
/** ***************************************************************************** */
org.kyerp.warehouse.StockDetailStore = new Ext.data.Store({
			reader : new Ext.data.JsonReader({}, new Ext.data.Record.create([{
								name : "id",
								type : "int"
							}, {
								name : "batchNumber",
								type : "string"
							}, {
								name : "createTime",
								type : "date",
								dateFormat : "Y-m-d H:i:s"
							}, {
								name : "updateTime",
								type : "date",
								dateFormat : "Y-m-d H:i:s"
							}, {
								name : "amount",
								type : 'float'
							}, {
								name : "materialId",
								type : "int"
							}, {
								name : "materialName",
								type : "string"
							}, {
								name : 'unitId',
								type : 'int'
							}, {
								name : 'unitName',
								type : 'string'
							}, {
								name : 'warehouseId',
								type : 'int'
							}, {
								name : 'warehoueName',
								type : 'string'
							}, {
								name : 'price',
								type : 'string'
							}, {
								name : 'cost'
							}]))
		});
/** ***************************************************************************** */
org.kyerp.warehouse.WarehouseStore = new Ext.data.Store({
			autoLoad : true,
			proxy : new Ext.data.HttpProxy({
						url : org.kyerp.warehouse.Warehouse_ALL_LIST_URL
					}),
			reader : new Ext.data.JsonReader({
						totalProperty : "totalProperty",
						root : "rows",
						id : "id"
					}, ['id', 'createTime', 'updateTime', 'name',
							'serialNumber', 'note', 'childWarehouseIds',
							'childWarehouseNames', 'parentWarehouseId',
							'parentWarehouseName'])
		});
/** ***************************************************************************** */
org.kyerp.warehouse.OutStockItemsEditorGridPanel = Ext.extend(
		Ext.grid.EditorGridPanel, {
			inserted : [],
			materialCombo : null,
			stockDetailCombo : null,
			warehouseCombo : null,
			selectMaterialWindow : null,
			conn : new Ext.data.Connection(),
			constructor : function(_cfg) {
				if (_cfg == null)
					_cfg = {};
				Ext.apply(this, _cfg);
				this.selectMaterialWindow = new org.kyerp.warehouse.SelectMaterialWindow(
						{
							onSelect : function(rec, win) {
								win.hide();
								var _detailsGrid = Ext.WindowMgr.getActive().form.detailsGrid;
								var _rs = new Ext.data.Record({
											id : '',
											materialId : '',
											materialName : '请输入物料名称',
											warehouseId : 4,
											batchNumber : '',
											unitId : '',
											unitName : '',
											price : 0,
											billCount : '',
											pressworkNo : 'SK2010',
											remark : ''
										});
								_rs.set("materialId", rec.data.id);
								_rs.set("materialName", rec.data.name);
								_rs.set("unitName", rec.data.unitName);
								_rs.set("price", rec.data.price);

								_detailsGrid.inserted.push(_rs);
								_detailsGrid.getStore().add(_rs);
								// 选中加入的行
								_detailsGrid.getSelectionModel()
										.selectRow(_detailsGrid.getStore()
												.getCount()
												- 1);
								_detailsGrid.fireEvent('rowclick',
										_detailsGrid, _detailsGrid.getStore()
												.getCount()
												- 1);
								_detailsGrid.startEditing(_detailsGrid
												.getStore().getCount()
												- 1, 0);
							}
						});
				this.ownerCombo =  new Ext.form.ComboBox({
					hiddenName : 'ownerId',
					typeAhead : true,
					lazyRender : true,
					pageSize : 20,
					listWidth : 360,
					valueField : 'id',
					displayField : 'name',
					mode : 'local',
					selectOnFocus : true,
					allowBlank : false,
					emptyText : '请选择',
					triggerAction : 'all',
					store : org.kyerp.warehouse.InventoryOwnerStore
				});
				this.materialCombo = new Ext.form.ComboBox({
							hiddenName : 'materialId',
							typeAhead : true,
							lazyRender : true,
							pageSize : 20,
							listWidth : 360,
							valueField : 'materialId',
							displayField : 'materialName',
							mode : 'remote',
							selectOnFocus : true,
							allowBlank : false,
							emptyText : '请选择',
							triggerAction : 'all',
							store : org.kyerp.warehouse.StockStore,
							loadingText : '正在载入数据,请稍候！',
							minChars : 2,
							queryDelay : 300,
							queryParam : 'query',
							listeners : {
								beforequery : function(qe){
									var store = org.kyerp.warehouse.StockStore;
									var combo = qe.combo;
									store.setBaseParam("ownerId", this.ownerCombo.getValue());
								},
								select : function(comboBox) {
									var value = comboBox.getValue();
									var _rs = this.getSelectionModel()
											.getSelected();
									var dataIndex = comboBox.store.find(
											'materialId', value, 0, false,
											false);
									_data = comboBox.store.getAt(dataIndex).data;
									_rs.set('unitName', _data.unitName);
									_rs.set('price', _data.price);
									_rs.set('warehouseId', _data.warehouseId);
									_rs.set('materialName', _data.materialName);
									org.kyerp.warehouse.StockDetailStore
											.loadData(Ext.util.JSON.decode(
													_data.details, false));
								},
								scope : this
							}
						});
				this.stockDetailCombo = new Ext.form.ComboBox({
							hiddenName : 'batchNumber',
							typeAhead : true,
							lazyRender : true,
							pageSize : 20,
							listWidth : 360,
							valueField : 'batchNumber',
							displayField : 'batchNumber',
							mode : 'local',
							selectOnFocus : true,
							allowBlank : false,
							emptyText : '请选择',
							triggerAction : 'all',
							store : org.kyerp.warehouse.StockDetailStore,
							listeners : {
								select : function(comboBox) {
									var value = comboBox.getValue();
									var _rs = this.getSelectionModel()
											.getSelected();
									var dataIndex = comboBox.store.find(
											'batchNumber', value, 0, false,
											false);
									_data = comboBox.store.getAt(dataIndex).data;
									_rs.set('unitName', _data.unitName);
									_rs.set('price', _data.price);
									_rs.set('warehouseId', _data.warehouseId);
								},
								scope : this
							}
						});
				this.warehouseCombo = new Ext.form.ComboBox({
							hiddenName : 'warehouseId',
							typeAhead : true,
							lazyRender : true,
							pageSize : 20,
							listWidth : 360,
							valueField : 'id',
							displayField : 'name',
							mode : 'remote',
							selectOnFocus : true,
							allowBlank : false,
							emptyText : '请选择',
							triggerAction : 'all',
							store : org.kyerp.warehouse.WarehouseStore
						});
				
				org.kyerp.warehouse.OutStockItemsEditorGridPanel.superclass.constructor
						.call(this, {
							store : new Ext.data.Store({
										reader : new Ext.data.JsonReader({},
												new Ext.data.Record.create([{
															name : "id",
															type : "int"
														}, {
															name : "billCount",
															type : "float"
														}, {
															name : "billCost",
															type : "float"
														}, {
															name : "materialId",
															type : "int"
														}, {
															name : "materialName",
															type : "string"
														}, {
															name : 'batchNumber',
															type : 'string'
														}, {
															name : "warehouseId",
															type : "int"
														}, {
															name : "warehouseName",
															type : "string"
														}, {
															name : "unitId",
															type : "int"
														}, {
															name : "unitName",
															type : "string"
														}, {
															name : "price",
															type : "float"
														}, {
															name : "remark",
															type : "string"
														}, {
															name : 'pressworkNo',
															type : 'string'
														}, {
															name : "ownerId",
															type : "int"
														}, {
															name : "ownerName",
															type : "string"
														}])),
										listeners : {
											update : this.updateBillCost,
											scope : this
										}
									}),
							autoScroll : true,
							sm : new Ext.grid.RowSelectionModel({
										singleSelect : true
									}),
							tbar : [{
										text : "保存",
										handler : this.onSaveButtonClick,
										hidden : true,
										scope : this
									}, {
										text : "添加一行",
										iconCls : 'icon-utils-s-add',
										handler : this.onInsertButtonClick,
										hidden : false,
										scope : this
									}, "-", {
										text : "加入物料",
										iconCls : 'icon-add-form',
										handler : function() {
											this.selectMaterialWindow.show();
										},
										scope : this
									}, "-", {
										text : "删除物料",
										iconCls : 'icon-utils-s-delete',
										handler : this.onRemoveButtonClick,
										scope : this
									}, '->', '双击表格可以修改资料'],
							columns : [new Ext.grid.RowNumberer(), {
								header : '所有者',
								width : 60,
								dataIndex : "ownerId",
								renderer : Ext.ux.renderer.Combo(this.ownerCombo),
								editor : this.ownerCombo
							},{
								header : '品名型号',
								width : 250,
								dataIndex : "materialId",
								renderer : function(value, metadate,
												record, colIndex, rowIndex) {
											// 要显示的数据
											var result = record.get("materialName");
											return result;
										},
								editor : this.materialCombo
							}, {
								header : '生产任务单号',
								dataIndex : "pressworkNo",
								editor : new Ext.form.TextField()
							}, {
								header : "批次号",
								width : 90,
								dataIndex : 'batchNumber',
								editor : this.stockDetailCombo
							}, {
								header : "数量",
								width : 70,
								dataIndex : "billCount",
								editor : new Ext.form.NumberField({
											decimalPrecision : 4,
											allowBlank : false
										})
							}, {
								header : '单位',
								width : 40,
								dataIndex : "unitName"
							}, {
								header : "单价",
								width : 50,
								dataIndex : "price",
								editor : new Ext.form.NumberField({
											allowBlank : false,
											minValue : 0
										})
							}, {
								header : '库房',
								width : 150,
								dataIndex : "warehouseId",
								renderer : Ext.ux.renderer
										.Combo(this.warehouseCombo),
								editor :this.warehouseCombo}, {
								header : "金额",
								width : 80,
								dataIndex : "billCost"
							}, {
								header : '备注',
								dataIndex : "remark",
								editor : new Ext.form.TextArea()
							}]
						});
			},
			updateBillCost : function() {
				var _rs = this.getSelectionModel().getSelected();
				_rs.set('billCost', _rs.get('price') * _rs.get('billCount'));
			},
			onSaveButtonClick : function() {
				var _m = this.getStore().modified;
				var _temp = [];
				for (var _i = 0; _i < _m.length; _i++) {
					if (_m[_i].get("id") == "")
						continue;
					var _data = {};
					var _j = "";
					for (_j in _m[_i].modified)
						_data[_j] = _m[_i].get(_j);
					_temp.push(Ext.apply(_data, {
								id : _m[_i].get("id")
							}));

				}
				for (var _i = 0; _i < this.inserted.length; _i++)
					_temp.push(this.inserted[_i].data);
				alert("Data: " + Ext.util.JSON.encode(_temp));
				this.getStore().commitChanges();

			},
			onInsertButtonClick : function() {
				var _rs = new Ext.data.Record({
							id : '',
							materialId : '',
							materialName : '请选择物料',
							batchNumber : '',
							warehouseId : '',
							unitId : '',
							unitName : '',
							price : 0,
							billCount : '',
							pressworkNo : '',
							remark : ''
						});
				this.inserted.push(_rs);
				this.getStore().add(_rs);
				// 选中加入的行
				this.getSelectionModel().selectRow(this.getStore().getCount()
						- 1);
				this
						.fireEvent('rowclick', this, this.getStore().getCount()
										- 1)
				this.startEditing(this.getStore().getCount() - 1, 0);
			},
			onSaveInsertData : function(_conn, _response) {
				var _xml = _response.responseXML;
				var _root = _xml.documentElement;
				for (var _i = 0; _i < _root.childNodes.length; _i++) {
					this.inserted[_i].set("id", _root.childNodes[_i].text);
				}
				this.inserted = [];
			},
			onRemoveButtonClick : function() {
				var _sm = this.getSelectionModel();
				try {
					if (_sm.getCount() == 0)
						throw Error("尚未选定一条记录");
					Ext.Msg.confirm("系统询问", "你是否确认删除此条记录?",
							this.onRemoveQuestion, this);
				} catch (_err) {
					Ext.Msg.alert("系统提示", _err);
				}
			},
			onRemoveQuestion : function(_btn) {
				if (_btn == "yes") {
					var _rs = this.getSelectionModel().getSelected();
					this.getStore().remove(_rs);
					if (_rs.get("id") != "") {
						this.conn.un("requestcomplete", this.onSaveInsertData,
								this);
						this.conn.request({
							url : org.kyerp.warehouse.OutStockDetail_DELETE_URL,
							params : {
								ids : _rs.get("id")
							}
						});
					} else {
						this.inserted.remove(_rs);
						this.getStore().modified.remove(_rs);
					}
				}
			}
		});
