<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Login Page - Ace Admin</title>

    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${request.contextPath}/libs/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${request.contextPath}/libs/font-awesome/css/font-awesome.min.css"/>

    <!-- text fonts -->
    <!--<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />-->

    <!-- ace styles -->
    <link rel="stylesheet" href="${request.contextPath}/assets/css/ace.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${request.contextPath}/assets/css/ace-part2.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="${request.contextPath}/assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${request.contextPath}assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${request.contextPath}/js/ie8/html5shiv.min.js"></script>
    <script src="${request.contextPath}/js/ie8/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-layout light-login">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-leaf green"></i>
                            <span class="red">Ace</span>
                            <span class="white" id="id-text2">Application</span>
                        </h1>
                        <h4 class="blue" id="id-company-text">&copy; 沐沐科技有限公司</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i>
                                        请输入用户名密码
                                    </h4>
                                    <div class="space-6"></div>

                                    <form th:action="@{/login}" method="post">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名"
                                                                   name="username" id="username"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码"
                                                                   name="password" id="password"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace" name="remember"/>
                                                    <span class="lbl"> 记住我</span>
                                                </label>

                                                <button type="submit" class="width-35 pull-right btn btn-sm btn-primary"
                                                        id="btn-login">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110">登录</span>
                                                </button>
                                            </div>

                                            <div class="space-4">

                                            </div>
                                        </fieldset>
                                    </form>
                                    <h6><span style="color: red" >${errorMsg!}</span></h6>
                                </div><!-- /.widget-main -->
                            </div><!-- /.widget-body -->
                        </div><!-- /.login-box -->
                    </div><!-- /.position-relative -->
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
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

<!-- inline scripts related to this page -->
<script type="text/javascript">
  jQuery(function ($) {
    $(document).on('click', '.toolbar a[data-target]', function (e) {
      e.preventDefault();
      var target = $(this).data('target');
      $('.widget-box.visible').removeClass('visible');//hide others
      $(target).addClass('visible');//show target
    });
  });
</script>
</body>
</html>
