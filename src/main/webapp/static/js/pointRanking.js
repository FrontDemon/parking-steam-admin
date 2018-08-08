$(function(){
	var adminObj = '${adminObj}';
	if(adminObj == ''){
		window.location.href = "${APP_PATH}/toAdminLogin";
	}
	
		$.ajax({
			url:"getPointRanking", 
			type:"GET",
			success:function(data){
				console.log(data); 
				$('#count').html(data.extend.list.length);
				$('#rankBody').empty();
				for(var i = data.extend.list.length-1;i>=0;i--){
					var tr = $('<tr></tr>');
					$('#rankBody').append(tr);
					tr.append($('<td>'+(data.extend.list.length-i)+'</td>'));
					tr.append($('<td>'+data.extend.list[i].name+'</td>'));
					tr.append($('<td>'+formatDate(data.extend.list[i].registerTime)+'</td>'));
					tr.append($('<td>'+data.extend.list[i].point+'</td>'));
				}
			},
			error:function(){
				alert('error in pointRanking');
			}
		});
		
		$('#btn-search').click(function(){
			var name = $('#searchinfo').val();
			$.ajax({
				url:"searchName",
				type:"GET",
				data:{"key":name},
				success:function(data){
					console.log(data);
					$('#count').html(data.extend.list.length);
					$('#rankBody').empty();
					for(var i = data.extend.list.length-1;i>=0;i--){
						var tr = $('<tr></tr>');
						$('#rankBody').append(tr);
						tr.append($('<td>'+data.extend.list[i].id+'</td>'));
						tr.append($('<td>'+data.extend.list[i].name+'</td>'));
						tr.append($('<td>'+formatDate(data.extend.list[i].registerTime)+'</td>'));
						tr.append($('<td>'+data.extend.list[i].point+'</td>'));
					}
				},
				error:function(){
					alert('error in pointRanking');
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