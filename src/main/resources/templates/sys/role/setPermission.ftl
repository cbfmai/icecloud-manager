<ul id="tree" class="ztree" style="width:100%;height:600px; overflow:auto;"></ul>
<script>
    var setting = {
        check: {
            enable: true,
            chkboxType: {"Y": "ps", "N": "s"}
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: "0",
                url: ""
            }
        },
        callback: {
            onClick: onClick
        }
    };
    var zTree;
    var oldSelect = new Array();

    function inArray(arr, val) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    function onClick(event, treeId, treeNode, clickFlag) {
        zTree.checkNode(treeNode, null, true, false);
        return false;
    }

    $(function () {
        $.get("${request.contextPath}/sys/menu/getAllPermissions", {"roleId": "${roleId}", "type": "menu"}, function (data) {
            if (data.success) {
                zTree = $.fn.zTree.init($("#tree"), setting, data.data);
                var oldSelectNodes = zTree.getCheckedNodes(true);
                for (var i in oldSelectNodes) {
                    oldSelect.push(oldSelectNodes[i].id);
                }
            } else {
                layer.msg(data.message);
            }
        }, "json");
    });

    function getDeleted() {
        var deleted = new Array();
        var nodes = zTree.getNodesByParam("checked", false, null);
        for (var i = 0; i < nodes.length; i++) {
            if (inArray(oldSelect, nodes[i].id) >= 0) {
                deleted.push(nodes[i].id)
            }
        }
        return deleted.join(",");
    }

    function getAdd() {
        var selected = new Array();
        var nodes = zTree.getCheckedNodes(true);
        for (var i = 0; i < nodes.length; i++) {
            if (inArray(oldSelect, nodes[i].id) < 0) {
                selected.push(nodes[i].id)
            }
        }
        return selected.join(",");
    }

    function getSelected() {
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getCheckedNodes(true);
        var v = "";
        for (var i = 0; i < nodes.length; i++) {
            v += nodes[i].id;
            if (i != nodes.length - 1) {
                v += ",";
            }

        }
        return v;
    }
</script>