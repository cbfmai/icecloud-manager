<div class="row">
    <div class="col-xs-12">
        <button type="button" class="btn btn-sm btn-primary" id="btn_addmenu"><i class="ace-icon fa fa-plus"></i>新增菜单
        </button>
        <button type="button" class="btn btn-sm btn-primary" id="btn_prev">
            <i class="ace-icon fa fa-mail-reply"></i>
            返回上一级
        </button>
    </div>
</div>
<input type="hidden" id="parentId" value="0"/>
<div class="space-4"></div>
<table id="table"></table>
<script>
    $(function () {
        $("#btn_prev").on("click", function () {
            findMenuByPid($("#pid").val(), "1");
        });
        $("#btn_addmenu").on("click", function () {
            addMenu();
        });
    });

    //刷新表格
    function refleshTable() {
        $('#table').bootstrapTable('refresh', {});
    }

    //增加表格查询参数
    function queryParams(params) {
        params['parentId'] = $("#parentId").val();
        return params;
    }

    //初始化表格
    var otable = $('#table').bootstrapTable({
        url: "${request.contextPath}/sys/menu/list",
        sidePagination: "server",
        pagination: true,
        queryParams: queryParams,
        dataField: "data",
        pageSize: 20,
        pageList: [10, 20, 50, 100],
        queryParamsType: 'limit',
        columns: [
            {field: 'name', align: 'center', title: '名称', formatter: formatName},
            {field: 'type', align: 'center', title: '类型', formatter: typeFormatter},
            {field: 'path', align: 'center', title: 'URL'},
            {field: 'createdTime', align: 'center', title: '创建时间'},
            {field: 'updatedTime', align: 'center', title: '创建时间'},
            {field: 'id', align: 'center', title: '操作', formatter: operate}
        ],
        onLoadSuccess: function (data) {
            if (data != null && data.length > 0) {
                $("#parentId").val(data[0].parentId);
            }
        }
    });

    function typeFormatter(val) {
        if ('menu' === val) {
            return '菜单'
        } else if ('button' === val) {
            return '按钮'
        }

    }

    function formatName(val, row, index) {
        var html = "<button class='btn btn-link' type='button' onclick=findMenuByPid('" + row.id + "')>" + val + "</button>&nbsp;";
        return html;
    }

    function findMenuByPid(val, type) {
        if (val == "") {
            layer.msg("没有上一级菜单");
            return;
        }
        $.post("${request.contextPath}/menu/getMenuByPid", {pid: val, isPre: type}, function (data) {
            if (data != null && data.length > 0) {
                $('#table').bootstrapTable('load', data);
                $("#pid").val(data[0].source_pid);
            } else {
                layer.msg("没有下一级菜单");
            }
        }, "json");
    }

    function operate(value, row, index) {
        var html = "<@shiro.hasPermission name='sys:menu:edit'><button class='btn btn-xs btn-primary' type='button' onclick=editmenu('" + value + "')><i class='ace-icon fa fa-pencil'></i>编辑</button> </@shiro.hasPermission>&nbsp;";
        html += "<span class='space-4'></span><button class='btn btn-xs btn-danger' onclick=delMenu('" + value + "','-1')><i class='ace-icon fa fa-remove'></i>删除</button>&nbsp;";
        return html;
    }

    //增加菜单
    function addMenu() {
        var index = layer.ajax('增加菜单', '${request.contextPath}/sys/menu/add', {}, function (index) {
            var name = $("#name").val();
            if ($.trim(name) == "") {
                layer.msg("名称不能为空");
                return;
            }
            $.post("${request.contextPath}/sys/menu/save", $("#editMenuForm").serialize(), function (data) {
                layer.msg(data.message)
                if (data.success) {
                    refleshTable();
                    layer.close(index);
                }
            }, "json");
        }, {area: ['580px', '']});

    }

    //编辑菜单
    function editmenu(val) {
        var index = layer.ajax('编辑菜单', '${request.contextPath}/menu/edit', {"id": val}, function (index) {
            var name = $("#name").val();
            if ($.trim(name) == "") {
                layer.msg("名称不能为空");
                return;
            }
            $.post("${request.contextPath}/menu/save", $("#editMenuForm").serialize(), function (data) {
                layer.msg(data.tipMsg)
                if (data.success) {
                    refleshTable();
                    layer.close(index);
                }
            }, "json");
        }, {area: ['580px', '']});
    }

    function delMenu(val) {
        layer.confirm("您确定要删除吗？", function () {
            $.post("${request.contextPath}/sys/menu/del", {"id": val}, function (data) {
                if (data) {
                    layer.msg("删除成功");
                    if ($("#pid").val() != '') {
                        findMenuByPid($("#pid").val(), "1");
                    } else {
                        refleshTable();
                    }
                } else {
                    layer.msg("删除失败");
                }
            }, "json");
        });
    }
</script>
