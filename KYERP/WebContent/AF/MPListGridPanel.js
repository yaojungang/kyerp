/** ****************************************************************************** */

MPInputFormPanel = Ext.extend(Ext.form.FormPanel, {
	constructor : function(_cfg) {
		var storeYW = new Ext.data.SimpleStore( {
			url :'../OPE/jsongetAllYW.action',
			fields : [ 'value' ]
		});

		var storeClient = new Ext.data.JsonStore( {
			url :'../Client/jsongetAllClient.action',
			fields : [ 'CCCom', 'CCDa', 'clientLm', 'CCAddress' ]
		});
		var storeLinkman = new Ext.data.JsonStore( {
			data : [],
			fields : [ 'CLmLinkman', 'CLmTel' ]
		});
		storeYW.load();
		storeClient.load();

		Ext.apply(this, _cfg);
		MPInputFormPanel.superclass.constructor.call(this, {
			labelWidth :60,
			labelAlign :"right",
			defaultType :'textfield',
			baseCls :"x-plain",
			layout :"form",
			defaults : {
				anchor :'100%'
			},
			items : [
					{
						xtype :'hidden',
						fieldLabel :"afId",
						name :'af.afId'
					},
					{
						xtype :'hidden',
						fieldLabel :"afNo",
						name :'af.afNo'
					},
					{
						xtype :'hidden',
						fieldLabel :"类型",
						name :'af.iso',
						value :'FP'
					},
					{
						xtype :'hidden',
						fieldLabel :"类型",
						name :'af.afType',
						value :'MP'
					},
					{
						xtype :"panel",
						baseCls :"x-plain",
						layout :"column",
						items : [ {
							// columnWidth :.5,
							baseCls :"x-plain",
							layout :"form",
							defaultType :"textfield",
							items : [ {
								name :'af.cp',
								fieldLabel :"接洽人",
								id :'comboYW',
								xtype :"combo",
								store :storeYW,
								emptyText :'请选择接洽人',
								allowBlank :false,
								blankText :'必须选择业务员',
								// autoWidth:true,
								width :160,
								mode :'local',
								triggerAction :'all',
								valueField :'value',
								displayField :'value',
								readOnly :true
							} ]
						}, {
							// columnWidth :.28,
							baseCls :"x-plain",
							layout :"form",
							defaultType :"textfield",
							items : [ {
								name :'af.timeRank',
								fieldLabel :"级别",
								width :60,
								xtype :"combo",
								store :new Ext.data.SimpleStore( {
									data : [ [ "一般" ], [ "紧急" ] ],
									fields : [ "value" ]
								}),
								emptyText :'级别',
								value :'一般',
								blankText :'请选择级别',
								mode :'local',
								triggerAction :'all',
								valueField :'value',
								displayField :'value',
								readOnly :true
							} ]

						}, {
							baseCls :"x-plain",
							layout :"form",
							defaultType :"textfield",
							items : [ {
								name :'af.ad',
								fieldLabel :"接洽时间",
								xtype :"datefield",
								width :145,
								value :new Date(),
								format :"Y-m-d H:m:s",
								readOnly :true,
								disabled :true
							} ]
						} ]
					},
					{
						xtype :"panel",
						baseCls :"x-plain",
						layout :"column",
						items : [
								{
									baseCls :"x-plain",
									layout :"form",
									defaultType :"textfield",
									items : [ {
										name :'af.presswork',
										fieldLabel :"印品名称",
										xtype :"combo",
										anchor :'100%',
										store :new Ext.data.SimpleStore( {
											data : [ [ "名片" ], [ "重印名片" ] ],
											fields : [ "value" ]
										}),
										value :'名片',
										blankText :'必须填写印品名称',
										mode :'local',
										triggerAction :'all',
										valueField :'value',
										displayField :'value',
										readOnly :false
									} ]
								},
								{
									baseCls :"x-plain",
									layout :"form",
									defaultType :"textfield",
									items : [ {
										name :'af.number',
										id :'comboAfNumber',
										fieldLabel :"份数(盒)",
										width :40,
										xtype :"combo",
										store :new Ext.data.SimpleStore( {
											data : [ [ "1" ], [ "2" ], [ "3" ],
													[ "5" ], [ "6" ], [ "8" ],
													[ "10" ], [ "20" ],
													[ "25" ], [ "30" ] ],
											fields : [ "value" ]
										}),
										value :'1',
										mode :'local',
										triggerAction :'all',
										valueField :'value',
										displayField :'value'
									} ]
								}, {
									baseCls :"x-plain",
									layout :"form",
									items : [ {
										xtype:'textfield',
										name :'af.amount',
										fieldLabel :"印数",
										id :'textfieldAfAmount',
										width :60,
										value :'100',
										blankText :'请填写印数'
									} ]
								} ]
					},
					{

						xtype :"panel",
						baseCls :"x-plain",
						layout :"column",
						items : [
								{
									// columnWidth :.5,
									baseCls :"x-plain",
									layout :"form",
									defaultType :"textfield",
									items : [ {
										name :'af.colorFrontN',
										fieldLabel :"正面色数",
										width :40,
										xtype :"combo",
										store :new Ext.data.SimpleStore( {
											data : [ [ "1" ], [ "2" ], [ "3" ],
													[ "4" ] ],
											fields : [ "value" ]
										}),
										value :'1',
										mode :'local',
										triggerAction :'all',
										valueField :'value',
										displayField :'value'
									} ]
								},
								{
									baseCls :"x-plain",
									// columnWidth:.7,
									layout :"form",
									defaultType :"textfield",
									items : [ {
										name :'af.colorFront',
										fieldLabel :"正面颜色",
										width :160,
										xtype :"combo",
										store :new Ext.data.SimpleStore( {
											data : [ [ "黑色" ], [ "黑色+校紫" ],
													[ "四色" ] ],
											fields : [ "value" ]
										}),
										value :'黑色',
										mode :'local',
										triggerAction :'all',
										valueField :'value',
										displayField :'value'
									} ]
								},
								{
									baseCls :"x-plain",
									layout :"form",
									defaultType :"textfield",
									items : [ {
										name :'af.colorBackN',
										fieldLabel :"背面色数",
										width :40,
										xtype :"combo",
										store :new Ext.data.SimpleStore( {
											data : [ [ "1" ], [ "2" ], [ "3" ],
													[ "4" ] ],
											fields : [ "value" ]
										}),
										value :'1',
										mode :'local',
										triggerAction :'all',
										valueField :'value',
										displayField :'value'
									} ]
								},
								{
									baseCls :"x-plain",
									layout :"form",
									defaultType :"textfield",
									items : [ {
										name :'af.colorBack',
										fieldLabel :"背面颜色",
										width :160,
										xtype :"combo",
										store :new Ext.data.SimpleStore( {
											data : [ [ "黑色" ], [ "黑色+校紫" ],
													[ "四色" ] ],
											fields : [ "value" ]
										}),
										value :'黑色',
										mode :'local',
										triggerAction :'all',
										valueField :'value',
										displayField :'value'
									} ]
								} ]
					}, {
						xtype :"panel",
						baseCls :"x-plain",
						layout :"column",
						items : [ {
							columnWidth :.8,
							baseCls :"x-plain",
							layout :"form",
							defaultType :"textfield",
							items : [ {
								name :'af.paper',
								fieldLabel :"纸张",
								xtype :"combo",
								anchor :'100%',
								store :new Ext.data.SimpleStore( {
									data : [ [ "白卡" ], [ "黄卡" ] ],
									fields : [ "value" ]
								}),
								value :'白卡',
								allowBlank :false,
								blankText :'必须填写纸张',
								mode :'local',
								triggerAction :'all',
								valueField :'value',
								displayField :'value',
								readOnly :true
							} ]
						}, {
							baseCls :"x-plain",
							// columnWidth:.7,
							layout :"form",
							defaultType :"textfield",
							items : [ {
								name :'af.paperPrice',
								fieldLabel :"纸价",
								width :60,
								xtype :"textfield"
							} ]
						} ]

					}, {
						xtype :"panel",
						baseCls :"x-plain",
						layout :"column",
						items : [ {
							columnWidth :.7,
							baseCls :"x-plain",
							style :"padding:4px",
							layout :"form",
							defaultType :"radio",
							items : [ {
								xtype :"fieldset",
								title :"客户资料",
								collapsible :true,
								collapsed :false,
								autoHeight :true,
								defaults : {
									anchor :'100%'
								},
								items : [ {
									fieldLabel :"委印单位",
									name :'af.client',
									id :'comboClient',
									xtype :"combo",
									store :storeClient,
									emptyText :'请选择委印单位',
									allowBlank :true,
									blankText :'必须选择一个系统中已有的委印单位',
									mode :'local',
									triggerAction :'all',
									valueField :'CCCom',
									displayField :'CCCom',
									// readOnly: true,
									resizable :true,
									minListWidth :380
								}, {
									fieldLabel :"联系人",
									id :'comboLinkman',
									name :'af.linkman',
									xtype :"combo",
									store :storeLinkman,
									emptyText :'请选择联系人',
									mode :'local',
									triggerAction :'all',
									valueField :'CLmLinkman',
									displayField :'CLmLinkman',
									readOnly :false
								}, {
									xtype :"panel",
									baseCls :"x-plain",
									layout :"column",
									items : [ {
										// columnWidth :.75,
										baseCls :"x-plain",
										layout :"form",
										defaultType :"textfield",
										items : [ {
											fieldLabel :"电    话",
											xtype :"textfield",
											name :'af.tel',
											id :'textfieldTel'
										} ]
									}, {
										baseCls :"x-plain",
										// columnWidth:.7,
										layout :"form",
										defaultType :"textfield",
										items : [ {
											fieldLabel :"手  机",
											xtype :"textfield",
											name :'af.CLmMobile',
											id :'textfieldMobile'
										} ]
									} ]

								}, {
									xtype :"panel",
									baseCls :"x-plain",
									layout :"column",
									items : [ {
										// columnWidth :.78,
										baseCls :"x-plain",
										layout :"form",
										defaultType :"textfield",
										items : [ {
											fieldLabel :"电子邮件",
											xtype :"textfield",
											name :'af.email',
											id :'textfieldEmail'
										} ]
									}, {
										baseCls :"x-plain",
										// columnWidth:.7,
										layout :"form",
										items : [ {
											fieldLabel :"邮件提醒",
											xtype :"checkbox",
											name :'af.emailRemind',
											id :'checkboxEmailRemind',
											value :'1'
										} ]
									} ]

								}, {
									fieldLabel :"送货地址",
									xtype :'textfield',
									name :'af.deliverAddress',
									width :280,
									id :'textfieldDeliverAddress'
								} ]

							} ]
						}, {
							columnWidth :.3,
							baseCls :"x-plain",
							layout :"form",
							style :"padding:4px",
							defaultType :"radio",
							defaults : {
								anchor :'100%'
							},
							items : [ {
								xtype :"fieldset",
								title :"进度要求",
								collapsible :true,
								collapsed :false,
								autoHeight :true,
								items : [ {
									name :'af.planPress',
									fieldLabel :"印刷日期",
									xtype :"datefield",
									value :new Date(),
									format :"Y-m-d"
								}, {
									name :'af.planDeliver',
									fieldLabel :"完成日期",
									xtype :"datefield",
									value :new Date(),
									format :"Y-m-d"
								} ]

							} ]
						} ]
					}, {
						fieldLabel :"备注",
						xtype :"textarea",
						name :'af.remark',
						style :"padding:4px 4px 4px 4px"
					} ]
		});
		this.addEvents('submit');

		Ext.getCmp('comboClient').on('select', function(comboBox, record) {
			var value = comboBox.getValue();
			storeLinkman.loadData(record.get('clientLm'));
			Ext.get('textfieldDeliverAddress').set( {
				value :record.get('CCAddress')
			});
		});
		Ext.getCmp('comboLinkman').on('select',
				function(comboBox, record, indexOfStore) {
					var value = comboBox.getValue();
					Ext.get('textfieldTel').set( {
						value :record.get('CLmTel')
					});
				});
		Ext.getCmp('comboAfNumber').on('select',
				function(comboBox) {
			alert(Ext.get('textfieldAfAmount').getValue());
			var value = comboBox.getValue();
			Ext.get('textfieldAfAmount').set( {
				value :'value'
			});
			alert(this.value * 100);
		});

	},
	submit : function() {
		try {
			if (this.url != "")
				this.getForm().submit( {
					url :this.url,
					success :this.onSubmit,
					waitTitle :'提交数据',
					waitMsg :'数据提交中,请稍候~~',
					scope :this
				});
		} catch (_err) {
			Ext.alert("系统提示", '提交数据出错了!');
		}
	},
	getValues : function() {
		if (this.getForm().isValid())
			return new Ext.data.Record(this.getForm().getValues());
		else
			throw Errow("表单验证未通过!");
	},
	setValues : function(_r) {
		this.getForm().loadRecord(_r);
	},
	reset : function() {
		this.getForm().reset();
	},
	onSubmit : function(_form, _action) {
		this.fireEvent("submit", this, _action, this.getValues());
	}
});

/** ****************************************************************************** */
MPInputWindow = Ext.extend(Ext.Window, {
	form :null,
	url :'',
	constructor : function(_cfg) {
		Ext.apply(this, _cfg);
		this.form = new MPInputFormPanel( {
			url :this.url
		});
		MPInputWindow.superclass.constructor.call(this, {
			plain :true,
			closeAction :'hide',
			collapsible :true,
			width :680,
			y :10,
			modal :true,
			items :this.form,
			buttons : [ {
				text :'保存',
				handler :this.onSubmitClick,
				scope :this
			}, {
				text :'取消',
				handler :this.onCanelClick,
				scope :this
			} ]
		});

		this.addEvents("submit");

		this.form.on('submit', this.onSubmit, this);
	},
	close : function() {
		this.form.reset();
		this.hide();
	},
	onSubmitClick : function() {
		this.form.submit();
	},
	onCanelClick : function() {
		this.close();
	},
	onSubmit : function(_form, _action, _values) {
		// alert();
	// alert(Ext.util.JSON.encode(this.getValues());
	try {
		this.fireEvent('submit', this, _values);
	} catch (_err) {
		return;
	}
	this.close();
}
});

/** ****************************************************************************** */
MPInputWindowInsert = Ext.extend(MPInputWindow, {
	title :"添加名片",
	url :'../test/addMP.action',
	onSubmit : function(_form, _action, _values) {

		var _data = _values.data;
		Ext.apply(_data, {
			id :_action.result.afId,
			'af.afId' :_action.result.afId,
			'af.ad' :_action.result.ad,
			'af.afNo' :_action.result.afNo,
			'af.tel' :_action.result.tel
		});

		try {
			this.fireEvent("submit", this, new Ext.data.Record(_data));

		} catch (_err) {

			return;
		}

		this.close();
	}
});
/** ****************************************************************************** */
MPInputWindowUpdate = Ext.extend(MPInputWindow, {
	title :"修改名片",
	url :'../test/JsonTest.action',
	load : function(_r) {
		this.form.setValues(_r);
	},
	onSubmit : function(_form, _action, _values) {

		var _data = _values.data;
		Ext.apply(_data, {
			'af.ad' :_action.result.ad,
			'af.afNo' :_action.result.afNo,
			'af.tel' :_action.result.tel
		});

		try {
			this.fireEvent("submit", this, new Ext.data.Record(_data));

		} catch (_err) {

			return;
		}

		this.close();
	}
});
/** ****************************************************************************** */

MPListGridPanel = Ext
		.extend(
				Ext.grid.GridPanel,
				{
					insertWin :new MPInputWindowInsert(),
					updateWin :new MPInputWindowUpdate(),
					renderLink : function(value, p, record) {
						return String
								.format(
										'<b><a href="../AF/AFInfo.action?afId={1}" target="_blank">{0}</a></b>',
										value, record.id, record.data.forumid);
					},
					getAfNo : function(value, p, record) {
						return String.format('{1}{0}', value, record
								.get('af.iso'));
					},
					constructor : function() {
						var store_AFMP = new Ext.data.JsonStore( {
							url :'../test/ListAF.action',
							id :'afId',
							fields : [ {
								mapping :'afId',
								name :'af.afId'
							}, {
								mapping :'ad',
								name :'af.ad'
							}, {
								mapping :'cp',
								name :'af.cp'
							}, {
								mapping :'iso',
								name :'af.iso'
							}, {
								mapping :'afNo',
								name :'af.afNo'
							}, {
								mapping :'client',
								name :'af.client'
							}, {
								mapping :'presswork',
								name :'af.presswork'
							}, {
								mapping :'linkman',
								name :'af.linkman'
							}, {
								mapping :'tel',
								name :'af.tel'
							} ]
						});
						store_AFMP.load();
						MPListGridPanel.superclass.constructor
								.call(
										this,
										{
											applyTo :'divMPListGridPanel',
											title :'名片任务列表',
											// height :500,
											autoHeight :true,
											autoExpandColumn :5,
											stripeRows :true,
											anchor :'98%',
											loadMask : true,// 载入遮罩动画
											tbar : [
													{
														text :'查    看',
														handler : function() {
															try {
																Ext.Msg
																		.alert(
																				"查看JSON数据",
																				Ext.util.JSON
																						.encode(this
																								.getSelected().data));
															} catch (_err) {
																Ext.Msg
																		.alert(
																				"系统提示",
																				_err.description);
															}
														},
														scope :this
													},
													'-',
													{
														text :'添    加',
														handler : function() {
															this.insertWin
																	.show();
														},
														scope :this
													},
													'-',
													{
														text :'修    改',
														handler : function() {
															this.updateWin
																	.show();
															try {
																this.updateWin
																		.load(this
																				.getSelected());
															} catch (_err) {
																Ext.Msg
																		.alert(
																				"系统提示",
																				_err.description);
																this.updateWin
																		.close();
															}
														},
														scope :this
													},
													'-',
													{
														text :'删    除',
														handler : function() {
															Ext.Msg
																	.confirm(
																			"系统提示",
																			"您确定删除这条记录吗?",
																			this.onRemove,
																			this);
															// this.remove();
														},
														scope :this
													} ],
											colModel :new Ext.grid.ColumnModel(
													[
															{
																header :"开单日期",
																dataIndex :'af.ad',
																name :'af.ad',
																format :'Y-m-d',
																align :"left",
																width :120
															},
															{
																header :"任务单号",
																dataIndex :'af.afNo',
																width :70,
																name :'af.afNo',
																align :"left",
																renderer :this.getAfNo
															},
															{
																header :"委印单位",
																dataIndex :'af.client',
																width :100,
																name :'af.clent',
																align :"left"
															},
															{
																header :"联系人",
																width :70,
																name :'af.linkman',
																dataIndex :'af.linkman',
																align :"center"
															},
															{
																header :"印品名称",
																name :'af.presswork',
																dataIndex :'af.presswork',
																width :300,
																align :"center",
																renderer :this.renderLink
															},
															{
																header :"联系电话",
																name :'af.tel',
																width :100,
																dataIndex :'af.tel',
																align :"center"
															},
															{
																header :"是否完成",
																name :'af.tel',
																width :100,
																dataIndex :'af.tel',
																align :"center"
															} ]),
											store :store_AFMP,
											bbar :new Ext.PagingToolbar( {
												store :store_AFMP
											})
										});
						this.insertWin.on("submit", this.onInsertWinSubmit,
								this);
						this.updateWin.on("submit", this.onUpdateWinSubmit,
								this);
						this.addEvents("rowselect");

					},

					getSelected : function() {

						var _sm = this.getSelectionModel();
						if (_sm.getCount() == 0)
							throw Error("请先选定一条记录!");
						return _sm.getSelected();
					},
					insert : function(_r) {
						// Ext.Msg.alert("grid panel",
					// Ext.util.JSON.encode(_r));
					this.getStore().insert(0, _r);
				},
				update : function(_r) {
					try {
						var _sr = this.getSelected();
						var _data = _sr.data;
						for ( var _i in _data) {
							_sr.set(_i, _r.get(_i));
							_sr.commit();
						}
					} catch (_err) {
						Ext.Msg.alert("系统提示", _err.description);
					}
				},
				remove : function() {
					try {
						var _sr = this.getSelected();

						Ext.Ajax.request( {
							url :"../test/AF_remove.action",
							params : {
								afId :_sr.get("af.afId")
							}
						});

						this.getStore().remove(_sr);
					} catch (_err) {
						Ext.Msg.alert("系统提示", _err.description);
					}
				},
				onInsertWinSubmit : function(_win, _r) {
					this.insert(_r);
				},
				onUpdateWinSubmit : function(_win, _r) {
					this.update(_r);

				},
				onRemove : function(_btn) {
					if (_btn == 'yes')
						this.remove();
				}
				});

/** ****************************************************************************** */
