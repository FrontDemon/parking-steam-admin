<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员登录页面</title>
	<link rel="icon" href="${APP_PATH}/static/img/stop.ico" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css"/>
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/login.css"/>
	<!-- 
		2018.8.4
		马伟涛
		完成管理员登录验证
	 -->
	<script src="${APP_PATH}/static/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#loginBtn").on("click",function(){
				var account = $("#account").val();
				var password = $("#password").val();
				$.ajax({
					url:"adminLogin",
					type:"GET",
					data:{"account":account,"password":password},
					success:function(data){
						console.log(data);
						if(data.code == 5){
							alert(data.msg);
							window.location.href = "adminIndex";
						}
						else{
							alert(data.msg);
						}
					},
					error:function(){
						alert("验证失败！");
					}
				});
			});
		});
	</script>
</head>
<body>
	<section class="login mainbox gray clearfix"
		style="height: 850px;background: url(${APP_PATH}/static/img/loginback.jpg);width: 100%;background-size: cover;">
	<div class="title"></div>
	<div class="l_m">
		<div class="logincontent">
			<div class="login_box">
				<h4>管理员登录</h4>
				<form name="adminLoginForm" action="" method="post"
					id="adminLoginForm">
					<ul>
						<li style="margin-top: 0px;">
							<input type="text" placeholder="用户名" name="account" id="account"
								class="username" autocomplete="off">
						</li>
						<li>
							<input type="password" name="password" placeholder="密码" id="password" 
								class="password" autocomplete="off">
						</li>
						<li>
							<input name="loginBtn" id="loginBtn" value="立即登录" class="btn-login" type="button">
						</li>
					</ul>
				</form>
			</div>

		</div>
	</div>
	</section>
</body>
</html>