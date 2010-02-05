//http://www.busfly.cn/post/ext-grid-json-date.html
/**
 * 将Json中Date长串,转成Js中的时间串
 * 
 * @param {}
 *            value
 * @return {}
 */
function readJsonDate(JsonDateValue, format) {
    var o;
    if (Ext.isEmpty(JsonDateValue))
        return '';
    else if (Ext.isEmpty(JsonDateValue.time))
        o = new Date(JsonDateValue);
    else
        o = new Date(JsonDateValue.time);
    return o.format(format || 'Y-m-d H:i:s');
};
/**
 * 主要用在Grid中
 * 
 * @param {}
 *            format
 * @return {}
 */
function renderDate(format) {
    return function(v) {
        return readJsonDate(v, format);
    };
};

/*在ColumnModel里面这样写
{
        header    : "birthday",
        renderer  : renderDate('Y-m-d'),
        dataIndex : 'birthday',
        align     : 'center'
}
*/
// DateField重写

Ext.override(Ext.form.DateField, {
    setValue : function(date) {
        if (Ext.isEmpty(date)) {
        }
        else if (Ext.isEmpty(date.time)) {
            date = new Date(date);
        }
        else {
            date = new Date(date.time);
        }
        Ext.form.DateField.superclass.setValue.call(this, this.formatDate(this.parseDate(date)));
    }
});