<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>编辑公告</title>
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/static/h-ui/css/H-ui.min.css"/>
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/static/h-ui.admin/css/H-ui.admin.css"/>
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/lib/Hui-iconfont/1.0.8/iconfont.css"/>
	
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/static/h-ui.admin/skin/default/skin.css" id="skin"/>
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/static/h-ui.admin/css/style.css"/>
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" id="form-notice-edit" action="${APP_PATH}/infoManagement/updateAnnouncement" method="post" >
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="hidden" name="account" value="${adminObj.getAccount() }"/>
				<input type="hidden" name="id" value="${id }" />
				<input type="text" class="input-text" placeholder="标题" id="title" name="title" />
			</div>
		</div>
		<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>内容：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="context" id="context" class="textarea"  placeholder="请输入具体内容。。。" ></textarea>
				</div>
			</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;发布&nbsp;&nbsp;"/>
			</div>
		</div>
		</form>
	</article>
	
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="${APP_PATH}/static/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/static/h-ui/js/H-ui.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/static/h-ui.admin/js/H-ui.admin.page.js"></script>
	<!--/_footer /作为公共模版分离出去-->
	
	<script type="text/javascript">
		$(function(){
			$.ajax({
				async:"false",
				url:"${APP_PATH}/infoManagement/getAnnouncement",
				data:{"id":'${id}'},
				success:function(data){
					$("#title").val(data.extend.list[0].title);
					$("#context").html(data.extend.list[0].context);
				}
			});
			
			$('.skin-minimal input').iCheck({
				checkboxClass: 'icheckbox-blue',
				radioClass: 'iradio-blue',
				increaseArea: '20%'
			});
			
			
			$("#form-notice-edit").validate({
				rules:{
					title:{
						required:true
					},
					content:{
						required:true
					}
				},
				onkeyup:false,
				focusCleanup:true,
				success:"valid",
				submitHandler:function(form){
					$(form).ajaxSubmit({
						async : false,
						success: function(data){
							if(data.code==3){
								layer.msg('修改成功!',{icon:1,time:1000});
								setTimeout(function() {
									var index = parent.layer.getFrameIndex(window.name);
									parent.$('#shuaxin').click();
									parent.layer.close(index);
								}, "1000");
							}else{
								layer.msg('修改失败!',{icon: 2,time:1000});
							}
						},
		                error: function(XmlHttpRequest, textStatus, errorThrown){
							layer.msg('error!',{icon:2,time:1000});
						}
					});
				}
			});
		
		});
	</script> 
</body>
</html>