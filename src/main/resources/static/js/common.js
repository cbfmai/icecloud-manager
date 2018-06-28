;
/* 递归实现获取无级树数据并生成DOM结构 */
var str = "";
var forTree = function (o) {
    for (var j = 0; j < o.length; j++) {
        var child = o[j].children;
        var urlstr;
        var isChild = o[j].parentId == 0 ? false : true;
        urlstr = "<li class='menuitem'>";
        urlstr += "<a href='javascript:void(0)' data='" + o[j].path + "' isparent='" + !isChild + "' class='dropdown-toggle'>";
        /* 设置图标 */
        if (isChild) {
            urlstr += '<i class="menu-icon fa fa-caret-right"></i>';
        } else {
            urlstr += '<i class="menu-icon fa fa-navicon"></i>';
        }
        if (isChild) {
            urlstr += o[j].name;
        } else {
            urlstr += "<span class='menu-text'>" + o[j].name + "</span>";
        }

        if (child != null) {
            urlstr += "<b class='arrow fa fa-angle-down'></b>";
        }
        urlstr += "</a>";
        str += urlstr;
        str += "<b class='arrow'></b>";
        if (child != null) {
            str += "<ul class='submenu'>";
            forTree(child);
            str += "</ul>";
        }
        str += "</li>";
    }
    return str;
};
