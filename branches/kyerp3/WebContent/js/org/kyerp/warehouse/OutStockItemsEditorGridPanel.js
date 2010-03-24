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
								// alert(Ext.encode(rec.data));
								win.hide();
								// var _win = Ext.WindowMgr.getActive();
								var _detailsGrid = Ext.WindowMgr.getActive().form.detailsGrid;
								var _rs = new Ext.data.Record({
											id : '',
											materialId : '',
											warehouseId : 2,
											batchNumber : '',
											unitId : '',
											unitName : '',
											price : 0,
											billCount : 1,
											remark : ''
										});
								_rs.set("materialId", rec.data.id);
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
												- 1)
								_detailsGrid.startEditing(_detailsGrid
												.getStore().getCount()
												- 1, 0);

								// Ext.WindowMgr.getActive().form.form
								// .findField('supplierId').setValue(rec.data.id);
							}
						});
				this.materialCombo = new Ext.form.ComboBox({
							hiddenName : 'materialId',
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
							store : org.kyerp.warehouse.materialStore,
							allQuery:'all',
							loadingText:'正在载入数据,请稍候！',
							minChars:2,
							queryDelay:300,
							queryParam:'searchKey',
							listeners : {
								select : function(comboBox) {
									var value = comboBox.getValue();
									var _rs = this.getSelectionModel()
											.getSelected();
									_data = comboBox.store.getById(value).data;
									// alert(Ext.util.JSON.encode(_data));
									_rs.set('unitName', _data.unitName);
									_rs.set('price', _data.price);
									_rs.set('warehouseId',_data.warehouseId);
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
								header : '品名型号',
								width : 150,
								dataIndex : "materialId",
								renderer : Ext.ux.renderer
										.Combo(this.materialCombo),
								editor : this.materialCombo
							}, {
								header : '库房',
								width : 150,
								dataIndex : "warehouseId",
								renderer :Ext.ux.renderer.Combo(this.warehouseCombo),
								// editor : this.warehouseCombo
								editor : new Ext.ux.form.TreeComboBox({
									fieldLabel : "默认仓库",
									name : 'warehouseId',
									hiddenName : 'warehouseId',
									xtype : 'treecombobox',
									editable : false,
									mode : 'local',
									displayField : 'name',
									valueField : 'id',
									triggerAction : 'all',
									allowBlank : false,
									treeUrl : org.kyerp.warehouse.WarehousePanel_TREE_URL,
									rootText : 'root',
									rootId : '0',
									forceSelection : true,
									rootVisible : false
								})
							}, {
								header : '单位',
								width : 40,
								dataIndex : "unitName"
							}, {
								header : "数量",
								width : 70,
								dataIndex : "billCount",
								editor : new Ext.form.NumberField({
											allowBlank : false
										})
							}, {
								header : "单价",
								width : 70,
								dataIndex : "price",
								editor : new Ext.form.NumberField({
											allowBlank : false,
											minValue : 0
										})
							}, {
								header : "金额",
								width : 80,
								dataIndex : "billCost"
							}, {
								header : "批次号",
								width : 100,
								dataIndex : 'batchNumber',
								editor : new Ext.form.TextField()
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
							batchNumber : '',
							warehouseId : 2,
							unitId : '',
							unitName : '',
							price : 0,
							billCount : 1,
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
