<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员首页</title>

<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" href="${APP_PATH}/static/img/stop.ico" type="image/x-icon">
<link rel="Bookmark" href="favicon.ico">
<link rel="Shortcut Icon" href="favicon.ico" />
<!--[if lt IE 9]>
    <script type="text/javascript" src="${APP_PATH}/static/lib/html5.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/lib/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css"
	href="${APP_PATH}/static/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${APP_PATH}/static/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="${APP_PATH}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />

<link rel="stylesheet" type="text/css"
	href="${APP_PATH}/static/static/h-ui.admin/skin/default/skin.css"
	id="skin" />
<link rel="stylesheet" type="text/css"
	href="${APP_PATH}/static/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script><![endif]-->
<!--/meta 作为公共模版分离出去-->

<meta name="keywords"
	content="H-ui.admin v3.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<!--_header 作为公共模版分离出去-->
	<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl">
			<a class="logo navbar-logo f-l mr-10 hidden-xs"
				href="${APP_PATH}/adminIndex">什么冬梅停车系统后台管理</a> <a
				class="logo navbar-logo-m f-l mr-10 visible-xs"
				href="/aboutHui.shtml">H-ui</a> <span
				class="logo navbar-slogan f-l mr-10 hidden-xs">v3.0</span> <a
				aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs"
				href="javascript:;">&#xe667;</a>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>超级管理员</li>
				<li class="dropDown dropDown_hover"><a href="#"
					class="dropDown_A">${adminObj.getAccount() }<i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onclick="myselfinfo()">个人信息</a></li>
						<li><a href="${APP_PATH}/adminSwitch">切换账户</a></li>
						<li><a href="${APP_PATH}/adminLogout">退出</a></li>
					</ul></li>
				<li id="Hui-msg"><a href="#" title="消息"><span
						class="badge badge-danger">1</span><i class="Hui-iconfont"
						style="font-size: 18px">&#xe68a;</i></a></li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"><a
					href="javascript:;" class="dropDown_A" title="换肤"><i
						class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul></li>
			</ul>
			</nav>
		</div>
	</div>
	</header>
	<!--/_header 作为公共模版分离出去-->

	<!--_menu 作为公共模版分离出去-->
	<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-article">
			<dt class="selected">
				<i class="Hui-iconfont">&#xe616;</i> 信息发布管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li class="current"><a href="${APP_PATH}/infoManagement/announcement" title="公告管理">公告管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-picture">
			<dt>
				<i class="Hui-iconfont">&#xe613;</i> 车位管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a href="${APP_PATH}/parkingManagement/parkingList" title="车位列表">车位列表</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-product">
			<dt>
				<i class="Hui-iconfont">&#xe620;</i> 订单管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a href="${APP_PATH}/OrderManagement/orderList" title="订单列表">订单列表</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-comments">
			<dt>
				<i class="Hui-iconfont">&#xe622;</i> 积分管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a href="${APP_PATH}/PointManagement/pointRanking" title="积分排行">积分排行</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-member">
			<dt>
				<i class="Hui-iconfont">&#xe60d;</i> 投诉建议<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a href="${APP_PATH}/CommentManagement/commentList" title="留言列表">留言列表</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-admin">
			<dt class="selected">
				<i class="Hui-iconfont">&#xe62d;</i> 系统管理<i
					class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a href="${APP_PATH}/systemManagement/adminList" title="管理员列表">管理员列表</a></li>
					<li><a href="${APP_PATH}/systemManagement/userList" title="用户列表">用户列表</a></li>
				</ul>
			</dd>
		</dl>
	</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<!--/_menu 作为公共模版分离出去-->

	<section class="Hui-article-box"> <nav class="breadcrumb">
	<i class="Hui-iconfont"></i> <a href="${APP_PATH}/adminIndex" class="maincolor">首页</a> <span
		class="c-999 en">&gt;</span>
	<span class="c-666">系统管理</span> <span class="c-999 en">&gt;</span>
	<span class="c-666">管理员列表</span> <a class="btn btn-success radius r"
		href="javascript:location.replace(location.href);"
		style="line-height: 1.6em; margin-top: 3px" title="刷新"> <i
		class="Hui-iconfont" id="shuaxin"></i></a> </nav>
	<div class="Hui-article">
		<article class="cl pd-20"> <!--此处开始为内容物-->
		<p class="f-20 text-success">欢迎使用后台管理系统
			<span class="f-14">v1.0</span>
		</p>
		<table class="table table-border table-bordered table-bg mt-20">
			<thead>
				<tr>
					<th colspan="2" scope="col">服务器信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="30%">服务器计算机名</th>
					<td><span id="lbServerName">阿里云服务器CentOS</span></td>
				</tr>
				<tr>
					<td>服务器IP地址</td>
					<td>120.79.230.101</td>
				</tr>
				<tr>
					<td>服务器域名</td>
					<td>maweitao.top</td>
				</tr>
				<tr>
					<td>服务器端口</td>
					<td>8080</td>
				</tr>
				<tr>
					<td>服务器IIS版本</td>
					<td>Microsoft-IIS/6.0</td>
				</tr>
				<tr>
					<td>服务器操作系统</td>
					<td>Linux</td>
				</tr>
				<tr>
					<td>服务器的语言种类</td>
					<td>Chinese (People's Republic of China)</td>
				</tr>
				<tr>
					<td>Java版本</td>
					<td>1.8.0_91</td>
				</tr>
			</tbody>
		</table>
		</article>
		<footer class="footer mt-20">
		<div class="container-fluid">
			<nav> <a href="#" target="_blank">关于我们</a> <span class="pipe">|</span>
			<a href="#" target="_blank">联系我们</a> <span class="pipe">|</span> <a
				href="#" target="_blank">法律声明</a> </nav>
			<p>
				Copyright &copy;2018 什么冬梅停车系统后台管理. <br> <a
					href="http://www.miitbeian.gov.cn/" target="_blank" rel="nofollow">京ICP备1234567号</a><br>
			</p>
		</div>
		</footer>
	</div>

	</section>
	
	<div id="selfInfo">
		<p>管理员账号：${adminObj.getAccount() }</p>
		<p>管理员姓名：${adminObj.getName() }</p>
	</div>
	
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="${APP_PATH}/static/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="${APP_PATH}/static/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="${APP_PATH}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="${APP_PATH}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="${APP_PATH}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript"
		src="${APP_PATH}/static/static/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="${APP_PATH}/static/static/h-ui.admin/js/H-ui.admin.page.js"></script>
	<!--/_footer /作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
		$(function(){
			var adminObj = '${adminObj}';
			if(adminObj == ''){
				window.location.href = "toAdminLogin";
			}
		});
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>