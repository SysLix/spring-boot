/**
 * 数据表格
 * @author watts
 * @date 2017/08/15
 * @version v0.0.1
 */
const PublicTable = function (id, url, columns, dataSrc) {
    this.tableId = id;
    this.url = url;
    this.columns = columns;
    this.dataSrc = dataSrc ? dataSrc : '';
    this.serverSide = false;
    //table实例,返回的是API对象
    this.table = null;
    this.datas = {};
    this.pageSize = 5;
    this.ajaxType = 'post';
    this.lengthMenu = [
        [5, 10, 20, 50],
        [5, 10, 20, 50]
    ];
    //显示风格
    this.dom = null;
    this.ordering = true;
    this.notOrderByColumn = null;
};


//加载表格
PublicTable.prototype.init = function () {
    const self = this;
    this.table = $('#' + this.tableId).DataTable({
        ajax: {
            url: this.url,
            data: function (d) {
                $.extend(d, self.datas);
                return JSON.stringify(d);
            },
            dataSrc: this.dataSrc,
            type: this.ajaxType,
            contentType: "application/json;charset=utf-8",
            dataType: 'json'
        },
        dom: this.dom ? this.dom : "<'row'<'col-md-12 col-sm-12'f>r>t<'row'<'col-md-2 col-sm-3'l><'col-md-3 col-sm-3'i><'col-md-7 col-sm-6'p>>",
        bServerSide: this.serverSide,
        //自定义属性列
        columns: this.columns,
        //每页数,有需要可作为参数传入
        lengthMenu: this.lengthMenu,
        //是否开启全表搜索
        filter: false,
        //上下滚动条
        // "sScrollY" : 450,
        //是否显示每页记录下拉
        // "bLengthChange": false,
        //表开始行
        // "iDisplayStart": 20,
        displayLength: this.pageSize,
        //排序
        ordering: this.ordering,
        destroy: true,
        processing: true,
        scrollX: true,
        aoColumnDefs: this.notOrderByColumn
    });
    return this;
};
/**
 * 支持多选行记录
 */
PublicTable.prototype.supportSelect = function () {
    $('#' + this.tableId).find('tbody').off('click').on('click', 'tr', function () {
        $(this).toggleClass('selected');
    });
    return this;
};
/**
 * 支持单选行记录
 */
PublicTable.prototype.supportSingleSelect = function () {
    $('#' + this.tableId).find('tbody').off('click').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            //去掉其他行的选中样式
            $(this).siblings('tr').removeClass('selected');
            $(this).addClass('selected');
        }
    });
    return this;
};
/**
 * 获取当前表格选中记录数
 */
PublicTable.prototype.getSelectedRows = function () {
    return this.table.rows('.selected').data().length;
};
/**
 * 获取当前表格选中记录数据
 */
PublicTable.prototype.getSelectedRowsData = function () {
    return this.table.rows('.selected').data();
};
/**
 * 获取当前表格当前页的记录数
 */
PublicTable.prototype.getPageRows = function () {
    return this.table.rows().data().length;
};
/**
 * 获取当前表格当前页数据
 */
PublicTable.prototype.getPageRowsData = function () {
    return this.table.rows().data();
};
/**
 * 重新加载表格数据
 * @returns {PublicTable}
 */
PublicTable.prototype.reload = function () {
    this.table.ajax.reload();
    return this;
};
/**
 * 放入查询参数
 * @param data
 * @returns {PublicTable}
 */
PublicTable.prototype.setData = function (data) {
    this.datas = data;
    return this;
};
PublicTable.prototype.addData = function (key, value) {
    this.datas[key] = value || $("#" + key).val();
    return this;
};
PublicTable.prototype.getData = function () {
    return this.datas;
};
/**
 * 设置每页显示多少
 * @param size
 * @returns {PublicTable}
 */
PublicTable.prototype.setPageSize = function (size) {
    this.pageSize = size;
    return this;
};
/**
 * 设置请求类型
 * @param type
 * @returns {PublicTable}
 */
PublicTable.prototype.setAjaxType = function (type) {
    this.type = type;
    return this;
};
/**
 * 设置开启服务器分页
 * @param open
 * @returns {PublicTable}
 */
PublicTable.prototype.setServerSide = function (open) {
    this.serverSide = open;
    return this;
};
/**
 * 设置每页数量下拉列表
 * @param lengthMenu
 * @returns {PublicTable}
 */
PublicTable.prototype.setLengthMenu = function (lengthMenu) {
    this.lengthMenu = lengthMenu;
    return this;
};
PublicTable.prototype.setDom = function (dom) {
    this.dom = dom;
    return this;
};
PublicTable.prototype.closeOrdering = function () {
    this.ordering = false;
    return this;
};
/**
 * 支持列显示切换
 */
PublicTable.prototype.supportToggleVisCol = function (entity) {
    //先遍历列表
    var htmls = [];
    $.each($('#' + this.tableId).find('thead tr th'), function (i, o) {
        htmls.push('<label data-column="' + i + '" class="toggle-vis"><input type="checkbox" checked>' + $(o).text() + '</label>');
    });
    var toggleList = $('#toggle-vislist');
    toggleList.html(htmls.join(''));
    Metronic.reloadHandleUniform();
    //监听事件
    toggleList.find('.toggle-vis').on('click', function (e) {
        var target = e.target.tagName;
        if (target === 'LABEL') {
            e.preventDefault();
            const column = entity.table.table.column($(this).attr('data-column'));
            column.visible(!column.visible());
            const checkb = $(this).find('input');
            $.uniform.update($(checkb).prop('checked', !$(checkb).prop('checked')));
        } else {
            const column = entity.table.table.column($(this).closest("label").attr('data-column'));
            column.visible(!column.visible());
            $.uniform.update($(this).prop('checked', !$(this).prop('checked')));
            // $(this).prop('checked',!$(checkb).prop('checked'));
        }

    });
};
/**
 * 单选记录时展开子菜单
 */
PublicTable.prototype.loadSubSingleSelect = function () {
    const self = this.table;
    $('#' + this.tableId).find('tbody').on('click', 'tr', function () {
        var row = self.row($(this));
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            row.child.hide();
        } else {
            //去掉其他行的选中样式
            $(this).siblings('tr').removeClass('selected');
            $(this).addClass('selected');
            row.child(fnFormatDetails()).show();
        }
    });
};
PublicTable.prototype.setDisableOrderingColumn = function (arr) {
    this.notOrderByColumn = [{"bSortable": false, "aTargets": arr || []}];
    return this;
};
