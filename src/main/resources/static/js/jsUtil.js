/**
 * 封装删除操作
 */
var jsUtil = function () {

    /**
     * 返回已选择的id
     * ids=1&ids=3&ids=4
     */
    var selectIds = function selectIds() {
        var checkeds = $(":checkbox:checked");
        if (checkeds.length == 0) {
            layer.alert('至少选择一项');
        } else {
            var sendData;
            var idArray = [];
            $.each(checkeds, function (i, n) {
                if (this.value != 'on') {
                    idArray.push(this.value);
                }
            });
            sendData = "ids=" + idArray.join("&ids=");
            return sendData;
        }
    }
    /**
     * 批量删除前确认
     */
    var updateStatuses = function updateStatuses(status, url) {
        var checkeds = $(":checkbox:checked");
        if (checkeds.length == 0) {
            layer.alert('至少选择一项');
        } else {
            if (status == -1) {
                layer.confirm("确定删除吗？", function () {
                    updates(status, checkeds, url);
                })
            } else {
                updates(status, checkeds, url);
            }
        }
    }

    /**
     * 单个删除确认
     */
    var updateStatus = function updateStatus(val, status, url) {
        if (status == -1) {
            layer.confirm("确定删除吗？", function () {
                update(val, status, url);
            })
        } else {
            update(val, status, url);
        }
    }

    /**
     * 开始、结束日期选择时判断日期是否合法<结束日期必须大于或等于开始日期>
     */
    var dateQuery = function (start, end) {
        start.datepicker({
            autoclose: true,
            todayHighlight: true,
            format: 'yyyy-mm-dd'
        }).on("hide", function (e) {
            if (start.val() != "" && end.val() != "") {
                if (new Date(start.val()).getTime() > new Date(end.val()).getTime()) {
                    layer.msg("开始时间不能大于结束时间");
                    start.datepicker('setDate', end.datepicker('getDate'));
                }
            }
        });
        end.datepicker({
            autoclose: true,
            todayHighlight: true,
            format: 'yyyy-mm-dd'
        }).on("hide", function (e) {
            if (start.val() != "" && end.val() != "") {
                if (new Date(start.val()).getTime() > new Date(end.val()).getTime()) {
                    layer.msg("结束时间不能小于开始时间");
                    end.datepicker('setDate', start.datepicker('getDate'));
                }
            }
        });
    }

    function updates(status, checkeds, url) {
        var sendData = "";
        var idArray = [];
        $.each(checkeds, function (i, n) {
            if (this.value != 'on') {
                idArray.push(this.value);
            }
        });

        sendData = "ids=" + idArray.join("&ids=") + "&status=" + status;
        $.ajax({
            url: url,
            type: "post",
            data: sendData,
            beforeSend: function () {
                return true;
            },
            success: function (result) {
                refleshTable();
                layer.msg(result.tipMsg);
            }
        });
    }

    function update(val, status, url) {
        $.post(url, {"id": val, "status": status}, function (data) {
            layer.msg(data.tipMsg)
            refleshTable();
        }, "json");
    }

    return {
        selectIds: selectIds,
        updateStatuses: updateStatuses,
        updateStatus: updateStatus,
        dateQuery: dateQuery
    };

}();




