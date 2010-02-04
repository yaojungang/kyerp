<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ExtjsTest</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Library/js/ext/resources/css/ext-all.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/ext/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Library/js/ext/source/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="MPListGridPanel.js">
	
</script>
<style type="text/css">
.red {
	background: red;
}
</style>
<script type="text/javascript">
	Ext.onReady( function() {
		Ext.BLANK_IMAGE_URL = '../Library/js/ext/resources/images/default/s.gif';

		var storeYW = new Ext.data.SimpleStore( {
			url :'../OPE/jsongetAllYW.action',
			fields : [ 'value' ]
		});

		var storeClient = new Ext.data.JsonStore( {
			url :'../OPE/jsongetClientByYW.action',
			fields : [ 'CCCom', 'CCDa', 'clientLm','CCAddress']
		});
		var storeLinkman = new Ext.data.JsonStore( {
			data : [],
			fields : [ 'CLmLinkman', 'CLmTel' ]
		});
		storeYW.load();

		var fp = new Ext.form.FormPanel({

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
								readOnly :true
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
										emptyText :'名片',
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
										name:'af.amount',
										fieldLabel :"印数",
										width :60,
										emptyText :'100',
										blankText :'请填写印数'
									} ]
								},
								{
									baseCls :"x-plain",
									layout :"form",
									defaultType :"textfield",
									items : [ {
										name:'af.number',
										fieldLabel :"份数",
										width :40,
										xtype :"combo",
										store :new Ext.data.SimpleStore( {
											data : [ [ "1" ], [ "2" ], [ "3" ],
													[ "5" ], [ "6" ], [ "8" ],
													[ "10" ], [ "20" ],
													[ "25" ], [ "30" ] ],
											fields : [ "value" ]
										}),
										emptyText :'1',
										mode :'local',
										triggerAction :'all',
										valueField :'value',
										displayField :'value'
									} ]
								}, {
									baseCls :"x-plain",
									layout :"form",
									items : [ {
										xtype :"label",
										text :'盒'
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
										emptyText :'1',
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
										name:'af.colorFront',
										fieldLabel :"正面颜色",
										width :160,
										xtype :"combo",
										store :new Ext.data.SimpleStore( {
											data : [ [ "黑色" ], [ "黑色+校紫" ],
													[ "四色" ] ],
											fields : [ "value" ]
										}),
										emptyText :'黑色',
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
										name:'colorBackN',
										fieldLabel :"背面色数",
										width :40,
										xtype :"combo",
										store :new Ext.data.SimpleStore( {
											data : [ [ "1" ], [ "2" ], [ "3" ],
													[ "4" ] ],
											fields : [ "value" ]
										}),
										emptyText :'1',
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
										name:'af.colorBack',
										fieldLabel :"背面颜色",
										width :160,
										xtype :"combo",
										store :new Ext.data.SimpleStore( {
											data : [ [ "黑色" ], [ "黑色+校紫" ],
													[ "四色" ] ],
											fields : [ "value" ]
										}),
										emptyText :'黑色',
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
								emptyText :'纸张',
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
								name:'af.paperPrice',
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
											value:'1'
										} ]
									} ]

								}, {
									fieldLabel :"送货地址",
									xtype:'textfield',
									name:'af.deliverAddress',
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
									name:'af.planTypeset',
									fieldLabel :"图    文",
									xtype :"datefield",
									value :new Date(),
									format :"Y-m-d"
								}, {
									name:'af.planPm',
									fieldLabel :"制    版",
									xtype :"datefield",
									value :new Date(),
									format :"Y-m-d"
								}, {
									name:'af.planPress',
									fieldLabel :"印    刷",
									xtype :"datefield",
									value :new Date(),
									format :"Y-m-d"
								}, {
									name:'af.planBind',
									fieldLabel :"装    订",
									xtype :"datefield",
									value :new Date(),
									format :"Y-m-d"
								}, {
									name:'af.planDeliver',
									fieldLabel :"发    行",
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
		
		fp.render("form");
		
			});
</script>
</head>
<body>
<div id="form"></div>
</body>
</html>
