MaterialCategoryTreePanel = Ext.extend(
				Ext.tree.TreePanel,
				{
					constructor : function() {
						MaterialCategoryTreePanel.superclass.constructor
								.call(
										this,
										{
											// renderTo:Ext.getCmp("MaterialCateTreePanel"),
											// width : 200,
											height : 400,
											loader : new Ext.tree.TreeLoader(
													{
														dataUrl : "/kyerp3/warehouse/MaterialCategory/jsonMaterialCategoryList.html"
														,baseAttrs :"ms"
													}),
											root : new Ext.tree.AsyncTreeNode( {
												text : "物料分类",
												id : "0"
											})
										});
					}
				});