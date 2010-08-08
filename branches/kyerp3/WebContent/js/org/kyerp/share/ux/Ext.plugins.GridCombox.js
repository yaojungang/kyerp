Ext.ns("Ext.plugins.GridCombox");
Ext.plugins.GridCombox = function(a) {
 this.config = Ext.apply({
    width : 320,
    height : 350
   }, a);
};
Ext.extend(Ext.plugins.GridCombox, Ext.util.Observable, {
 init : function(b) {
  Ext.apply(b, {
   initList : b.initList.createInterceptor((function(d) {
      return function() {
       if(!this.list){
        var cls = 'x-combo-list';
                 this.list = new Ext.Layer({shadow:this.shadow, constrain:true, shim:true});
                 var lw = this.listWidth || Math.max(this.wrap.getWidth(), this.minListWidth);
                 this.list.setWidth(lw);
                 this.list.swallowEvent('mousewheel');
                 this.assetHeight = 0;
                 
                 if(this.title){
                     this.header = this.list.createChild({cls:cls+'-hd', html: this.title});
                     this.assetHeight += this.header.getHeight();
                 }
                 
                 this.innerList = this.list.createChild({cls:cls+'-inner'});
                 this.innerList.setWidth(lw - this.list.getFrameWidth('lr'));
                  
                  this.grid = new Ext.grid.GridPanel({
         border:true,
            collapsible: false, 
            loadMask: true ,
            autoScroll:true,
         width : this.listWidth || Math.max(this.wrap.getWidth(), this.minListWidth),
         height : 250,
         store : b.store,
         cm : b.cm,
         sm: new Ext.grid.RowSelectionModel({singleSelect:true}),  
         bbar:new Ext.PagingToolbar({
                   id:'PagingToolbar',
                   pageSize: b.pageSize || 20,
                   store:b.store,
                   displayInfo: false
               })
        });
        this.grid.render(this.innerList);
        this.innerList.appendChild(this.grid.getEl());
        
        this.grid.on("rowclick", function (g, rowIndex, e) {
         var record = b.store.getAt(rowIndex);
         this.onSelect(record, rowIndex);
         this.collapse();
         if (!b.trigger) {
          this.focus();
         }
        }, this);
       }
       return false;
      }
     })(this.config), b),
   onRender : b.onRender.createSequence(function() {
      if (!this.lazyInit) {
       this.initList();
      } else {
       this.on("focus", this.initList, this, {
          single : true
         });
      }
     }, b),
   onTriggerClick : b.onTriggerClick.createInterceptor((function(d) {
      return function() {
       if (!this.disabled) {
        if (this.isExpanded()) {
         this.collapse();
         this.el.focus();
        } else {
         this.onFocus({});
         this.expand();
        // this.store.load();//点击时 grid的store加载数据
        }
       }
       return false;
      };
     })(this.config), b),
   expand : b.expand.createInterceptor((function(d) {
      return function() {
       if (this.isExpanded()) {
        return;
       }
       
       this.list.alignTo(this.wrap, this.listAlign);
       this.list.show();
       if (Ext.isGecko2) {
        this.innerList.setOverflow('auto'); // necessary
       }
       Ext.getDoc().on('mousewheel', this.collapseIf, this);
       Ext.getDoc().on('mousedown', this.collapseIf, this);
       this.fireEvent('expand', this);
      };
     })(this.config), b)
  });
 }
});
/*
var cm = new Ext.grid.ColumnModel([   
  {header:'??',dataIndex:'value',menuDisabled:true},   
  {header:'??',dataIndex:'text',menuDisabled:true},   
  {header:'??',dataIndex:'group',menuDisabled:true},   
  {header:'??',dataIndex:'date',menuDisabled:true}   
 ]); 
    new Ext.form.FormPanel({
        renderTo: Ext.getBody(),
        frame: true,
        items: [{
            xtype: 'combo',
            fieldLabel: 'asd',
            mode: 'local',
            triggerAction: 'all',
            width: 250,
            lazyInit: false,
            lazyRender: true,
            listAlign: 'tr-br',
            title: 'test',
            pageSize: '5',
            store: new Ext.data.SimpleStore({
                id: 1,
                fields: [
                    {name: 'value', header: 'ID', width: 20},
                    {name: 'text', header: 'Texte', width: 200},
                    {name: 'group', header: 'Groupe', width: 120}
                ],
                data: [
                    [1, 'Item 1a', 'Group 1'],
                    [2, 'Item 1b', 'Group 1'],
                    [3, 'Item 1c', 'Group 1'],
                    [4, 'Item 2a', 'Group 2'],
                    [5, 'Item 2b', 'Group 2'],
                    [6, 'Item 3a', 'Group 3'],
                    [7, 'Item 3b', 'Group 3']
                ]
            }),
			cm:cm,
			listClass : 'x-combo-list-small', 
			allowBlank : false,
            onSelect:function(record,rowIndex){   
     this.setValue(record.get("value"));   
     this.setRawValue(record.get("text"));   
	 //alert(record.get("text"));
    },   
    plugins : [new Ext.plugins.GridCombox()]   
        }]
    });
*/