<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>后台管理系统</title>

    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${request.contextPath}/libs/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/libs/font-awesome/css/font-awesome.min.css"/>

    <!-- page specific plugin styles -->

    <!-- ace styles -->
    <link rel="stylesheet" href="${request.contextPath}/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${request.contextPath}/assets/css/ace-part2.min.css" class="ace-main-stylesheet"/>
    <![endif]-->
    <link rel="stylesheet" href="${request.contextPath}/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${request.contextPath}/assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->
    <link rel="stylesheet" href="${request.contextPath}/libs/bootstrap-table/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/libs/bootstrap-select/css/bootstrap-select.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/libs/ztree/css/zTreeStyle/zTreeStyle.css"/>

    <!-- ace settings handler -->
    <script src="${request.contextPath}/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${request.contextPath}/js/ie8/html5shiv.min.js"></script>
    <script src="${request.contextPath}/js/ie8/respond.min.js"></script>
    <![endif]-->

    <style>
        .fixed-table-container {
            border-radius: 0px !important;
        }

        body .layui-layer-moon .layui-layer-content {
            overflow: visible;
        }
    </style>

</head>

<body class="no-skin">
<div id="navbar" class="navbar navbar-default ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="/" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Ace Admin
                </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">

                <li class="purple dropdown-modal">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                        <span class="badge badge-important">8</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-exclamation-triangle"></i>
                            8 Notifications
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar navbar-pink">
                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">
                                                <i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
                                                New Comments
                                            </span>
                                            <span class="pull-right badge badge-info">+12</span>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <i class="btn btn-xs btn-primary fa fa-user"></i>
                                        Bob just signed up as an editor ...
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">
                                                <i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
                                                New Orders
                                            </span>
                                            <span class="pull-right badge badge-success">+8</span>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">
                                                <i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
                                                Followers
                                            </span>
                                            <span class="pull-right badge badge-info">+11</span>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="#">
                                See all notifications
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="green dropdown-modal">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
                        <span class="badge badge-success">5</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-envelope-o"></i>
                            5 Messages
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar">
                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${request.contextPath}/assets/images/avatars/avatar.png" class="msg-photo"
                                             alt="Alex's Avatar"/>
                                        <span class="msg-body">
                                            <span class="msg-title">
                                                <span class="blue">Alex:</span>
                                                Ciao sociis natoque penatibus et auctor ...
                                            </span>

                                            <span class="msg-time">
                                                <i class="ace-icon fa fa-clock-o"></i>
                                                <span>a moment ago</span>
                                            </span>
                                        </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${request.contextPath}/assets/images/avatars/avatar3.png" class="msg-photo"
                                             alt="Susan's Avatar"/>
                                        <span class="msg-body">
                                            <span class="msg-title">
                                                <span class="blue">Susan:</span>
                                                Vestibulum id ligula porta felis euismod ...
                                            </span>
                                            <span class="msg-time">
                                                <i class="ace-icon fa fa-clock-o"></i>
                                                <span>20 minutes ago</span>
                                            </span>
                                        </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${request.contextPath}/assets/images/avatars/avatar4.png" class="msg-photo"
                                             alt="Bob's Avatar"/>
                                        <span class="msg-body">
                                                    <span class="msg-title">
                                                        <span class="blue">Bob:</span>
                                                        Nullam quis risus eget urna mollis ornare ...
                                                    </span>

                                                    <span class="msg-time">
                                                        <i class="ace-icon fa fa-clock-o"></i>
                                                        <span>3:15 pm</span>
                                                    </span>
                                                </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${request.contextPath}/assets/images/avatars/avatar2.png" class="msg-photo"
                                             alt="Kate's Avatar"/>
                                        <span class="msg-body">
                                                    <span class="msg-title">
                                                        <span class="blue">Kate:</span>
                                                        Ciao sociis natoque eget urna mollis ornare ...
                                                    </span>

                                                    <span class="msg-time">
                                                        <i class="ace-icon fa fa-clock-o"></i>
                                                        <span>1:33 pm</span>
                                                    </span>
                                                </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="${request.contextPath}/assets/images/avatars/avatar5.png" class="msg-photo"
                                             alt="Fred's Avatar"/>
                                        <span class="msg-body">
                                                    <span class="msg-title">
                                                        <span class="blue">Fred:</span>
                                                        Vestibulum id penatibus et auctor  ...
                                                    </span>

                                                    <span class="msg-time">
                                                        <i class="ace-icon fa fa-clock-o"></i>
                                                        <span>10:09 am</span>
                                                    </span>
                                                </span>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="inbox.html">
                                See all messages
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${request.contextPath}/assets/images/avatars/user.jpg" alt="Jason's Photo"/>
                        <span class="user-info">
                                    <small>Welcome,</small>
                        ${realname}
                                </span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-cog"></i>
                                设置
                            </a>
                        </li>

                        <li>
                            <a href="profile.html">
                                <i class="ace-icon fa fa-user"></i>
                                个人信息
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="/logout">
                                <i class="ace-icon fa fa-power-off"></i>
                                注销
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.navbar-container -->
</div>

<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.loadState('main-container')
        } catch (e) {
        }
    </script>

    <div id="sidebar" class="sidebar responsive ace-save-state">
        <script type="text/javascript">
            try {
                ace.settings.loadState('sidebar')
            } catch (e) {
            }
        </script>
        <ul class="nav nav-list" id="menucontent">

        </ul><!-- /.nav-list -->

        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
               data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>
    </div>

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/">首页</a>
                    </li>
                </ul>
            </div>
            <!-- /.breadcrumb -->
            <div class="page-content" style="padding: 8px 20px 0px;">
                <div class="row">
                    <div class="col-xs-12" id="maincontent">
                        <!-- PAGE CONTENT BEGINS -->
                        <!--<div layout:fragment="page_content"></div>-->
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <div class="footer-content">
                        <span class="bigger-120">
                            <span class="blue bolder">沐沐科技</span>
                            mmtech.io &copy; 2016-2018
                        </span>
            </div>
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${request.contextPath}/libs/jquery/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="${request.contextPath}/libs/jquery/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${request.contextPath}/libs/jquery/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>


<script src="${request.contextPath}/libs/bootstrap/js/bootstrap.min.js"></script>


<!-- page specific plugin scripts -->
<script src="${request.contextPath}/libs/layer/layer.js"></script>

<script>
    $.ajaxSetup({
        complete: function (event, xhr, options) {
            if ('error' === xhr) {
                layer.alert(event.responseText, {
                    title: '',
                    icon: 2,
                    skin: 'layui-layer-lan'
                });
                return;
            }
        }
    });
</script>
<script>
    layer.config({
        skin: 'layui-layer-moon'
    })
</script>


<script src="${request.contextPath}/libs/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${request.contextPath}/libs/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${request.contextPath}/libs/bootstrap-table/extensions/export/bootstrap-table-export.min.js"></script>
<script src="${request.contextPath}/libs/bootstrap-table/extensions/export/tableExport.mini.js"></script>
<script src="${request.contextPath}/libs/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="${request.contextPath}/libs/bootstrap-select/js/i18n/defaults-zh_CN.min.js"></script>
<script src="${request.contextPath}/libs/ztree/js/jquery.ztree.all.min.js"></script>

<!-- ace scripts -->
<script src="${request.contextPath}/assets/js/ace-elements.min.js"></script>
<script src="${request.contextPath}/assets/js/ace.min.js"></script>

<script src="${request.contextPath}/js/common.js"></script>
<script src="${request.contextPath}/js/ajax.js"></script>
<script src="${request.contextPath}/js/formatter.js"></script>


<!-- inline scripts related to this page -->
<script>

    $(document).ready(function () {
        $(function () {
            var str1 = "";
            common_ajax.ajaxFunc("${request.contextPath}/sys/user/menus", {}, "json", function (data) {
                if (!data.success) {
                    return;
                }
                var html = forTree(data.data);
                $("#menucontent").html(html);
                $(".menuitem").on("click", function (event) {
                    var data = $(this).children("a").attr("data");
                    if (!$(this).parent().parent().hasClass('menuitem')) {
                        if ($(".open:not(.active)").length != 0 && !$(this).hasClass('open')) {
                            $(".open").removeClass("open");
                            $(".menuitem:not(.active)").find(".submenu").hide();
                        }
                        if ($(this).hasClass('open')) {
                            $(this).removeClass('open');
                            $(this).children("ul").eq(0).hide();
                        } else {
                            $(this).addClass('open');
                            $(this).children("ul").eq(0).show();
                        }
                        if (data != null && data != '' && data != 'null') {
                            $(".menuitem").removeClass("active");
                            $(".menuitem").removeClass("open");
                            $(".submenu").hide();
                            $(this).addClass("active");
                            str1 = "<li id='secBreadcrumb'>" + $(this.getElementsByClassName('dropdown-toggle')[0]).text() + "</li>";
                            document.getElementsByClassName('breadcrumb')[0].innerHTML = "<li><i class='ace-icon fa fa-home home-icon'></i>首页</li>" + str1;
                        }
                    } else {
                        if (data != null && data != '' && data != 'null') {
                            $(".menuitem").removeClass("active");
                            $(".menuitem").removeClass("open");
                            $(".submenu").hide();
                            $(this).addClass("active");
                            str1 = ""
                            pan($(this)[0]);
                            document.getElementsByClassName('breadcrumb')[0].innerHTML = "<li><i class='ace-icon fa fa-home home-icon'></i>首页</li>" + str1;
                        }
                        var flag = false;
                        $(this).find("li").each(function () {
                            if ($(this).hasClass('active')) {
                                flag = true;
                            }
                        });
                        if ($(this).children("ul").eq(0).length > 0) {
                            if ($(this).find("ul")[0].style.display == "none" || $(this).find("ul")[0].style.display == "undefined" || $(this).find("ul")[0].style.display == "") {
                                $(this).children("ul").eq(0).show();
                            } else {
                                $(this).children("ul").eq(0).hide();
                                if (flag) {
                                    $(this).addClass('active').removeClass('open');
                                }
                            }
                        }
                    }

                    if (data != null && data != '' && data != 'null') {
                        common_ajax.ajaxMainPanel('${request.contextPath}' + data);
                    }
                    event.stopPropagation()
                });
            });

            function pan(obj) {
                str1 = "<li>" + $(obj.getElementsByClassName('dropdown-toggle')[0]).text() + "</li>" + str1;
                if (hasCC(obj.parentNode.parentNode, "menuitem")) {
                    addCC(obj.parentNode.parentNode, "open");
                    addCC(obj.parentNode.parentNode, "active");
                    obj.parentNode.style.display = "block";
                    pan(obj.parentNode.parentNode);
                }
            }

            function hasCC(ele, cls) {
                return ele.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
            }

            function addCC(ele, cls) {
                if (!hasCC(ele, cls)) {
                    ele.className += ' ' + cls;
                }
            }
        });
    });

</script>
</body>
</html>
