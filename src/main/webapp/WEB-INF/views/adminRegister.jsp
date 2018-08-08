<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员注册页面</title>
	<link rel="icon" href="${APP_PATH}/static/img/stop.ico" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css"/>
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/login.css"/>
</head>
<body>
	<section class="login mainbox gray clearfix"
		style="height: 850px;background: url(${APP_PATH}/static/img/loginback.jpg);width: 100%;background-size: cover;">
		<div class="title"></div>
		<div class="l_m">
			<div class="logincontent">
				<div class="login_box">
					<h4>管理员注册</h4>
					<form name="adminRegisterForm" action="" method="post"
						id="adminRegisterForm">
						<ul>
							<li style="margin-top: 0px;">
								<input type="text" placeholder="用户名" name="account" id="account"
									class="username" autocomplete="off">
							</li>
							<li>
								<input type="password" name="passWord" placeholder="密码" id="passWord" 
									class="password" autocomplete="off">
							</li>
							<li>
								<input type="text" placeholder="姓名" name="name" id="name"
									class="username" autocomplete="off">
							</li>
							<li>
								<input name="Button2" value="注册" class="btn-login" type="submit">
							</li>
						</ul>
					</form>
				</div>
	
			</div>
		</div>
	</section>
</body>
</html>