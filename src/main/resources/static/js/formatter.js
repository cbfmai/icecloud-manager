function formatStatus(value, row, index) {
    if (value == 1) {
        return "<span class=\"label label-sm label-success\">启用</span>"
    } else if (value == 0) {
        return "<span class=\"label label-sm label-warning\">停用</span>"
    } else if (value == -1) {
        return "<span class=\"label label-sm label-inverse arrowed-in\">删除</span>"
    }
}

