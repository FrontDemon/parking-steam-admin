	$(function(){
		/*
		 * 加载管理员列表
		 */
		$.ajax({
			url:"getAdminList", 
			type:"GET",
			success:getAdminList,
			error:function(){
				alert('error in pointRanking'); 
			}
		});
		
		/*
		 * 搜索按钮点击事件
		 */
		$('#btn-search').click(function(){
			var name = $('#searchinfo').val();
			$.ajax({
				url:"searchAdmin",
				type:"GET",
				data:{"key":name},
				success:getAdminList,
				error:function(){
					alert('error in pointRanking');
				}
			});
		});
		
		/*
		 * 添加管理员按钮监听事件
		 */
		$('#btn-add').click(function(){
			$("#modal-demo").modal("show");
		});
		
		/*
		 * 确认添加按钮监听事件
		 */
		$('#btn-confirm').click(function(){
			var account = $('#add-account').val();
			var password1 = $('#add-psw').val();
			var password2 = $('#confirm-psw').val();
			var name = $('#add-name').val();
			$.ajax({
				url:"addAdmin",
				type:"POST",
				data:{"account":account,"name":name,"password":password1,"password2":password2},
				success:function(data){
					console.log(data);
					$("#modal-demo").modal("hide");
					layer.msg('添加成功!',{icon:1,time:1000});
					setTimeout(function() {
						var index = parent.layer.getFrameIndex(window.name);
						parent.$('#shuaxin').click();
						parent.layer.close(index);
					}, "1000");
				},
				error:function(){
					alert('error in addAdminList');
				}
			});
		});
		
	})
	
	/*
	 * 处理ajax请求
	 */
	function getAdminList(data){
		console.log(data); 
		$('#count').html(data.extend.list.length);
		$('#adminBody').empty();
		for(var i = 0;i<data.extend.list.length;i++){
			var tr = $('<tr></tr>');
			$('#adminBody').append(tr);
			var td = $("<td>"+(i+1)+"</td>");
			tr.append(td);
			var inp = $("<input type='hidden' value='"+data.extend.list[i].id+"' class='inp'/>");
			td.append(inp);
			tr.append($('<td>'+data.extend.list[i].account+'</td>'));
			tr.append($('<td>'+data.extend.list[i].password+'</td>'));
			tr.append($('<td>'+data.extend.list[i].name+'</td>'));
			tr.append($('<td>'+'<a class="ml-5" href="javascript:;"'+
					'style="text-decoration: none" title="删除" onclick="admin_del(this)"><i class="Hui-iconfont">&#xe609;</i></a></td>'));
		}
	}
	
	function admin_del(obj){
		layer.confirm('确认要删除吗？',function(index){
			var adminId = $(obj).parents("tr").find(".inp").val();
			$.ajax({
				type: 'POST',
				url: 'deleteAdmin',
				data:{"id":adminId},
				dataType: 'json',
				success: function(data){
					if(data.code==2){
						$(obj).parents("tr").remove();
						layer.msg('已删除!',{icon:1,time:1000});
						setTimeout(function() {
							var index = parent.layer.getFrameIndex(window.name);
							parent.$('#shuaxin').click();
							parent.layer.close(index);
						}, "1000");
					}else{
						layer.msg('操作失败!',{icon: 2,time:1000});
					}
				}
			});		
		});
	}