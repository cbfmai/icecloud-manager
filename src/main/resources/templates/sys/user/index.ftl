<div id="page_content">
    <div class="box">
        <form class="form-inline" role="form">
            <div class="form-group" style="margin-right:10px;">
                <label class="" for="username">登录名称</label>
                <input type="text" class="input-large" id="username" placeholder="请输入昵称">
            </div>
            <div class="form-group" style="margin-right:10px;">
                <label class="" for="realname">真实姓名</label>
                <input type="text" class="input-large" id="realname" placeholder="请输入昵称">
            </div>
            <div class="form-group" style="margin-right:10px;">
                <label class="" for="mobile">手机号</label>
                <input type="text" class="form-control" id="mobile" placeholder="请输入手机号">
            </div>
            <div class="form-group">
                <label class="" for="status">状态</label>
                <select name="status" class="selectpicker" id="status">
                    <option value="">全部</option>
                    <option value="1">正常</option>
                    <option value="-1">删除</option>
                    <option value="0">禁用</option>
                </select>
            </div>

            <div class="form-group">
                <button type="button" class="btn btn-sm btn-primary" id="btn_search"><i
                        class="ace-icon fa fa-search"></i>查询
                </button>
            </div>
        </form>
        <div class="space-4"></div>
        <form class="form-inline" role="form">
            <div class="form-group">
                <button type="button" class="btn btn-sm btn-primary"
                        id="btn_add_sys_user">
                    <i class="ace-icon fa fa-plus"></i>新增
                </button>
            </div>
        </form>

        <!-- /.box-header -->
        <div class="box-body" style="padding-top: 5px">
            <table id="table" style="border-radius: 0px"></table>
        </div>
        <!-- /.box-body -->
    </div>
    <!-- /.box -->
</div>


<script>

    $(function () {

        $("#btn_search").on("click",function(){
            $('#table').bootstrapTable('selectPage', 1);
        });

        $("#btn_add_sys_user").on("click", function () {
            addSysUser();
        });
    });

    $('.selectpicker').selectpicker({
        style: 'btn-white',
        width: '80px'
    });

    //刷新表格
    function refreshTable() {
        $('#table').bootstrapTable('refresh', {});
    }

    function editSysUser(id) {
        layer.ajax('编辑菜单', '${request.contextPath}/sys/user/edit', {"id": id}, function (index) {

            $.post("${request.contextPath}/sys/user/save", $("#sys_user_form").serialize(), function (data) {
                layer.msg(data.message)
                if (data.success) {
                    refreshTable();
                    layer.close(index);
                }
            }, "json");
        }, {area: ['580px', '']});
    }

    function addSysUser() {
        layer.ajax('增加菜单', '${request.contextPath}/sys/user/add', {}, function (index) {
            var username = $("#username").val();
            if ($.trim(username) == "") {
                layer.msg("用户名不能为空");
                return;
            }
            $.post("${request.contextPath}/sys/user/save", $("#sys_user_form").serialize(), function (data) {
                layer.msg(data.message)
                if (data.success) {
                    refreshTable();
                    layer.close(index);
                }
            }, "json");
        }, {area: ['580px', '']});
    }

    function resetPassword(val) {
        var index = layer.open({
            type: 1,
            title: "新密码",
            content: '<div class="row-fluid"><div class="col-xs-12 form-group"><div class="space"></div><input type="password" autocomplete="new-password" id="repassword" class="col-xs-12"/></div></div>',
            area: ['380px', ''],
            btn: ['确定', '取消'],
            yes: function () {
                var password = $("#repassword").val();
                $.post("${request.contextPath}/sys/user/password/reset", {"userId": val, "password": password}, function (data) {
                    if (data.success) {
                        layer.close(index);
                    }
                    layer.msg(data.message);
                }, "json");
            }
        });
    }

    //增加表格查询参数
    function queryParams(params) {
        params['username']=$("#username").val();
        params['realname']=$("#realname").val();
        params['mobile']=$("#mobile").val();
        params['status']=$("#status").val();
        return params;
    }

    //初始化表格
    $('#table').bootstrapTable({
        url: "${request.contextPath}/sys/user/list",
        sidePagination: "server",
        pagination: true,
        queryParams: queryParams,
        dataField: "data",
        pageSize: 20,
        pageList: [10, 20, 50, 100],
        queryParamsType: 'limit',
        columns: [
            {field: 'state', align: 'center', checkbox: true},
            {field: 'username', align: 'center', title: '登录名称'},
            {field: 'realname', align: 'center', title: '真实姓名'},
            {field: 'mobile', align: 'center', title: '电话'},
            {field: 'createdTime', align: 'center', title: '创建时间'},
            {field: 'updatedTime', align: 'center', title: '更新时间'},
            {field: 'status', align: 'center', title: '状态', formatter: formatStatus},
            {field: 'id', title: '操作', align: 'center', width: '30%', formatter: formatOperation}
        ]
    });

    function changeSysUser(val, status) {
        if (status == "-1") {
            layer.confirm("您确定要删除吗？", function () {
                $.post("${request.contextPath}/sys/user/status", {"userId": val, "status": status}, function (data) {
                    layer.msg(data.message)
                    refreshTable();
                }, "json");
            });
        } else {
            $.post("${request.contextPath}/sys/user/status", {"userId": val, "status": status}, function (data) {
                layer.msg(data.message)
                refreshTable();
            }, "json");
        }
    }


    function formatOperation(value, row, index) {
        var html = "<button class='btn btn-xs btn-primary' type='button' onclick=editSysUser('" + value + "')><i class='ace-icon fa fa-pencil'></i>编辑</button>&nbsp;";
        if (row.status != -1) {
            html += "<span class='space-4'></span><button class='btn btn-xs btn-danger' onclick=changeSysUser('" + value + "','-1')><i class='ace-icon fa fa-remove'></i>删除</button>&nbsp;";
        }
        if (row.status == 0 || row.status == -1) {
            html += "<span class='space-4'></span><button class='btn btn-xs btn-success' onclick=changeSysUser('" + value + "','1')><i class='ace-icon fa fa-check'></i>启用</button>&nbsp;";
        } else {
            html += "<span class='space-4'></span><button class='btn btn-xs btn-warning' onclick=changeSysUser('" + value + "','0')><i class='ace-icon fa fa-ban'></i>禁用</button>&nbsp;";
        }
        html += "<span class='space-4'></span><button class='btn btn-xs btn-info' onclick=resetPassword('" + value + "')><i class='ace-icon fa fa-key'></i>重置密码</button>&nbsp;";
        return html;
    }
</script>
