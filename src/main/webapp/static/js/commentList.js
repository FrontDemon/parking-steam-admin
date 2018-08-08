	$(function(){
		$.ajax({
			url:"getComment", 
			type:"GET",
			success:function(data){
				console.log(data); 
				$('#count').html(data.extend.list.length);
				$('#commentBody').empty();
				for(var i = 0;i<data.extend.list.length;i++){
					var tr = $('<tr></tr>');
					$('#commentBody').append(tr);
					
					var td = $("<td>"+(i+1)+"</td>");
					tr.append(td);
					var inp = $("<input type='hidden' value='"+data.extend.list[i].id+"' class='inp'/>");
					td.append(inp);
					
					tr.append($('<td>'+data.extend.list[i].comment+'</td>'));
					tr.append($('<td>'+formatDate(data.extend.list[i].comTime)+'</td>'));
					tr.append($('<td>'+data.extend.list[i].user.name+'</td>'));
					tr.append($('<td>'+data.extend.list[i].comment+'</td>'));
					if(data.extend.list[i].admin == null){
						tr.append($("<td>暂无回复</td>"));
						tr.append($("<td>暂无回复</td>"));
						tr.append($("<td><a class='ml-5' href='javascript:;' style='text-decoration:none' title='回复' onclick='comment_edit(this)'><i class='Hui-iconfont'>&#xe70c;</i></a><a class='ml-5' href='javascript:;' style='text-decoration:none' title='删除' onclick='comment_del(this)'><i class='Hui-iconfont'>&#xe609;</i></a></td>"));
					}
					else{
						tr.append($('<td>'+formatDate(data.extend.list[i].replyTime)+'</td>'));
						tr.append($('<td>'+data.extend.list[i].admin.name+'</td>'));
						tr.append($('<td>'+'<a class="ml-5" href="javascript:;"'+
						'style="text-decoration: none" title="删除" onclick="comment_del(this)"> <i class="Hui-iconfont">&#xe609;</i></a></td>'));
					}
					
				}
			},
			error:function(){
				alert('error in commentList');
			}
		});
		
		$('#btn-search').click(function(){
			var comment = $('#searchinfo').val();
			$.ajax({
				url:"searchComment",
				type:"GET",
				data:{"key":comment},
				success:function(data){
					console.log(data);
					$('#count').html(data.extend.list.length);
					$('#commentBody').empty();
					for(var i = 0;i<data.extend.list.length;i++){
						var tr = $('<tr></tr>');
						$('#commentBody').append(tr);
						var td = $("<td>"+(i+1)+"</td>");
						tr.append(td);
						var inp = $("<input type='hidden' value='"+data.extend.list[i].id+"' class='inp'/>");
						td.append(inp);
						tr.append($('<td>'+data.extend.list[i].comment+'</td>'));
						tr.append($('<td>'+formatDate(data.extend.list[i].comTime)+'</td>'));
						tr.append($('<td>'+data.extend.list[i].user.name+'</td>'));
						tr.append($('<td>'+data.extend.list[i].comment+'</td>'));
						if(data.extend.list[i].admin == null){
							tr.append($("<td>暂无回复</td>"));
							tr.append($("<td>暂无回复</td>"));
							tr.append($("<td><a class='ml-5' href='javascript:;' style='text-decoration:none' title='回复' onclick='comment_edit(this)'><i class='Hui-iconfont'>&#xe70c;</i></a><a class='ml-5' href='javascript:;' style='text-decoration:none' title='删除' onclick='comment_del(this)'><i class='Hui-iconfont'>&#xe609;</i></a></td>"));
						}
						else{
							tr.append($('<td>'+formatDate(data.extend.list[i].replyTime)+'</td>'));
							tr.append($('<td>'+data.extend.list[i].admin.name+'</td>'));
							tr.append($('<td>'+'<a class="ml-5" href="javascript:;"'+
							'style="text-decoration: none" title="删除" onclick="comment_del(this)"> <i class="Hui-iconfont">&#xe609;</i></a></td>'));
						}
					}
				},
				error:function(){
					alert('error in commentList');
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
	
	function comment_del(obj){
		layer.confirm('确认要删除吗？',function(index){
			var commentId = $(obj).parents("tr").find(".inp").val();
			$.ajax({
				type: 'POST',
				url: 'deleteComment',
				data:{"id":commentId},
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
	
	function comment_edit(obj){
		var commentId = $(obj).parents("tr").find(".inp").val();
		var urltmp = "comment-edit?id="+commentId;
		layer_show('回复留言',urltmp,'800','300');
	}