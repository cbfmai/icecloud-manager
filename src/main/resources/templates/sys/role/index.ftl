<div class="row">
    <div class="col-xs-12">
        <button type="button" class="btn btn-sm btn-primary" id="btn_add_role"><i class="ace-icon fa fa-plus"></i>新增角色
        </button>
    </div>
</div>
<div class="space-4"></div>
<table id="table"></table>
<script>
    $(function () {
        $("#btn_add_role").on("click", function () {
            addRole();
        });
    });

    //刷新表格
    function refreshTable() {
        $('#table').bootstrapTable('refresh', {});
    }

    //增加表格查询参数
    function queryParams(params) {
        return params;
    }

    //初始化表格
    var otable = $('#table').bootstrapTable({
        url: "${request.contextPath}/sys/role/list",
        sidePagination: "server",
        pagination: true,
        queryParams: queryParams,
        dataField: "data",
        pageSize: 20,
        pageList: [10, 20, 50, 100],
        queryParamsType: 'limit',
        columns: [
            {field: 'name', align: 'center', title: '名称'},
            {field: 'desc', align: 'center', title: '描述'},
            {field: 'status', align: 'center', title: '状态', formatter: formatStatus},
            {field: 'createdTime', align: 'center', title: '创建时间'},
            {field: 'updatedTime', align: 'center', title: '更新时间'},
            {field: 'id', title: '操作', align: 'center', formatter: operate}
        ]
    });

    function operate(value, row, index) {
        var desc = "";
        if (row.desc != null && row.desc != '') {
            desc = row.desc.replace(/\ +/g, "");
        }
        var html = "<button class='btn btn-xs btn-primary' type='button' onclick=editRole('" + value + "')><i class='ace-icon fa fa-pencil'></i>编辑</button>&nbsp;";
        if (row.status != -1) {
            html += "<span class='space-4'></span><button class='btn btn-xs btn-danger' onclick=changeStatus('" + value + "','-1')><i class='ace-icon fa fa-remove'></i>删除</button>&nbsp;";
        }
        if (row.status == 0 || row.status == -1) {
            html += "<span class='space-4'></span><button class='btn btn-xs btn-success' onclick=changeStatus('" + value + "','1')><i class='ace-icon fa fa-check'></i>启用</button>&nbsp;";
        } else {
            html += "<span class='space-4'></span><button class='btn btn-xs btn-warning' onclick=changeStatus('" + value + "','0')><i class='ace-icon fa fa-ban'></i>禁用</button>&nbsp;";
        }
        if (row.status == 1) {
            html += "<span class='space-4'></span><button class='btn btn-xs btn-success' onclick=setPermission('" + desc + "','" + value + "','-1')><i class='ace-icon fa  fa-cog'></i>设置角色权限</button>&nbsp;";
        }
        return html;
    }

    //增加权限
    function addRole() {
        var index = layer.ajax('增加权限', '${request.contextPath}/sys/role/add', {}, function (index) {
            $.post("${request.contextPath}/sys/role/save", $("#editRoleForm").serialize(), function (data) {
                if (data.success) {
                    refreshTable();
                    layer.close(index);
                }
                layer.msg(data.message)
            }, "json");
        }, {area: ['450px', '']});
    }

    //编辑权限
    function editRole(val) {
        var index = layer.ajax('编辑权限', '${request.contextPath}/sys/role/edit', {"id": val}, function (index) {
            $.post("${request.contextPath}/sys/role/save", $("#editRoleForm").serialize(), function (data) {
                if (data.success) {
                    refleshTable();
                    layer.close(index);
                }
                layer.msg(data.message)
            }, "json");
        }, {area: ['450px', '']});
    }

    function changeStatus(val, status) {
        if (status == -1) {
            layer.confirm("您确定要删除吗？", function () {
                $.post("${request.contextPath}/sys/role/status", {"roleId": val, "status": status}, function (data) {
                    if (data.success) {
                        layer.msg("删除成功");
                        refreshTable();
                    } else {
                        layer.msg(data.message);
                    }
                }, "json");
            });
        } else {
            $.post("${request.contextPath}/sys/role/status", {"roleId": val, "status": status}, function (data) {
                layer.msg(data.message)
                refreshTable();
            }, "json");
        }

    }

    function setPermission(roleName, val, type) {
        var index = layer.ajax('设置<b>【' + roleName + '】</b>的菜单资源', '${request.contextPath}/sys/role/permission/add', {
            "roleId": val,
            "type": type
        }, function (index) {
            var deletedIds = getDeleted();
            var addIds = getAdd();
            $.post("${request.contextPath}/sys/role/permission/save", {
                "roleId": val,
                "deletedIds": deletedIds,
                "addIds": addIds
            }, function (data) {
                if (data.success) {
                    refreshTable();
                    layer.close(index);
                }
                layer.msg(data.message)
            }, "json");
        }, {area: ['580px', '600px']});
    }
</script>