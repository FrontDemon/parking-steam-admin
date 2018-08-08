	$(function(){
		/*
		 * 加载用户列表
		 */
		$.ajax({
			url:"getUserList", 
			type:"GET",
			success:handleData,
			error:function(){
				alert('error in userList');
			}
		});
		
		/*
		 * 搜索按钮点击事件
		 */
		$('#btn-search').click(function(){
			var name = $('#searchinfo').val();
			$.ajax({
				url:"searchUser",
				type:"GET",
				data:{"key":name},
				success:handleData,
				error:function(){
					alert('error in userList');
				}
			});
		});

	})
	
	/*
	*格式化时间
	*@author Zm
	*/
	function formatDate(date){
		var newDate = new Date(date);
		year = newDate.getFullYear();
		month = newDate.getMonth()+1;
		day = newDate.getDate();
		hour = newDate.getHours();
		minute = newDate.getMinutes();
		second = newDate.getSeconds();
		
		var formatDate = year+'-'+addZero(month)+'-'+addZero(day)+'  '+addZero(hour)+':'+addZero(minute)+':'+addZero(second);
		return formatDate;
	}	
	
	/*
	*给日期补0
	*@author Zm
	*/
	function addZero(num){
		if(parseInt(num)<10){
			num = '0' + num;
		}
		return num;
	}
	
	/*
	* 处理用户列表ajax请求
	*/
	function handleData(data){
		console.log(data); 
		$('#count').html(data.extend.list.length);
		$('#userBody').empty();
		for(var i = 0;i<data.extend.list.length;i++){
			var tr = $('<tr></tr>');
			$('#userBody').append(tr);
			var td = $("<td>"+(i+1)+"</td>");
			tr.append(td);
			var inp = $("<input type='hidden' value='"+data.extend.list[i].id+"' class='inp'/>");
			td.append(inp);
			tr.append($('<td>'+data.extend.list[i].name+'</td>'));
			tr.append($('<td>'+data.extend.list[i].phone+'</td>'));
			tr.append($('<td>'+data.extend.list[i].licenseNum+'</td>'));
			tr.append($('<td>'+formatDate(data.extend.list[i].registerTime)+'</td>'));
			if(data.extend.list[i].status == 0){
				tr.append($('<td><span class="label label-success radius">已启用</span></td>'));
				tr.append($('<td><span class="ml-5" style="text-decoration:none" title="停用">'+
				'<i class="Hui-iconfont btn-stop" onclick="stop_btn(this)">&#xe631;</i></span></td>')); 
			}else{ 
				tr.append($('<td><span class="label label-default radius">已停用</span></td>'));
				tr.append($('<td><span class="ml-5" style="text-decoration:none" title="启用">'+
				'<i class="Hui-iconfont btn-start" onclick="start_btn(this)">&#xe615;</i></span></td>')); 
			} 

		}
	}
	
	function stop_btn(obj){
		layer.confirm('确认要停用吗？',function(index){
			var id = $(obj).parents("tr").find(".inp").val();
			var status = 1;
			$.ajax({
				type: 'POST',
				url: 'updateUser',
				data:{"id":id,"status":status},
				dataType: 'json',
				success: function(data){
					if(data.code == 3){
						$(obj).parents("tr").remove();
						layer.msg('已停用!',{icon:1,time:1000});
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
	
	function start_btn(obj){
		layer.confirm('确认要启用吗？',function(index){
			var id = $(obj).parents("tr").find(".inp").val();
			var status = 0;
			$.ajax({
				type: 'POST',
				url: 'updateUser',
				data:{"id":id,"status":status},
				dataType: 'json',
				success: function(data){
					if(data.code == 3){
						$(obj).parents("tr").remove();
						layer.msg('已启用!',{icon:1,time:1000});
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