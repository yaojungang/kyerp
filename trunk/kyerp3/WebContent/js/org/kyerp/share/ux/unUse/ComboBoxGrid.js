/**
 * 下拉ComboBoxGrid
 * 
 * @xtype 'combogrid'
 * @author chengbao_zhu
 */
ComboBoxGrid = Ext.extend(Ext.form.ComboBox, {
	gridHeight : 180,
	listAlign : 'tr-br',
	listWidth : 300,
	store : new Ext.data.SimpleStore({
		fields : [],
		data : [[]]
	}),
	resizable : false,
	// Default
	editable : false, 
	mode : 'local',
	triggerAction : 'all',
	maxHeight : 500,
	selectedClass : '',
	onSelect : Ext.emptyFn,
	emptyText : '\u8bf7\u9009\u62e9...',


	/**
	 * ---------------------------------- 
	 * 单击GRID事件
	 * ----------------------------------
	 */
	gridClk : function(grid, rowIndex, e) {
		
		this.setRawValue(grid.getRecord(rowIndex).data[this.displayField]);
		this.setValue(grid.getRecord(rowIndex).data[this.valueField]);
		this.collapse();

		 this.fireEvent('gridselected', grid.getRecord(rowIndex));

	},

	initLayout : function(){
		this.grid.autoScroll = true;
		this.grid.height = this.gridHeight;
		this.grid.containerScroll = false;
		this.grid.border=false;
		
		this.listWidth = this.grid.width+3;
	},
	/**
	 * Init
	 */
	initComponent : function() {
		ComboBoxGrid.superclass.initComponent.call(this);
		this.initLayout();
		this.tplId = Ext.id();
		// overflow:auto"
		this.tpl = '<div id="' + this.tplId + '" style="height:' + this.gridHeight
				+ ';overflow:hidden;"></div>';
		
		//Add Event
		 this.addEvents('gridselected');
	},

	/**
	 * ------------------ 
	 *  Listener 
	 * ------------------
	 */
	listeners : {
		'expand' : {
			fn : function() {
				if (!this.grid.rendered && this.tplId) {
					//this.initComponent();
					this.initLayout();
					
					this.grid.render(this.tplId);
					this.store = this.grid.store;
					
					this.store.reload();
					if(this.store.getCount()==0){
						this.store.add(new Ext.data.Record([{}]));	
					}
					//this.grid.store.reload();
					this.grid.on('rowclick', this.gridClk, this);
				}
				
				this.grid.show();
			}
			//single : true
		},

		'render' : {
			fn : function() {
				 
			}
		},
		'beforedestroy' : {
			fn : function(cmp) {
				this.purgeListeners();
				this.grid.purgeListeners();
			}
		},
		'collapse' : {
			fn : function(cmp) {
				/**
				 *  防止当store的记录为0时不出现下拉的状况
				 */
				if(this.grid.store.getCount()==0){
					this.store.add(new Ext.data.Record([{}]));
				}
			}
		}
	}

});

/**
 * --------------------------------- 
 * register:'combotree' 
 * ---------------------------------
 */
Ext.reg('combogrid', ComboBoxGrid);