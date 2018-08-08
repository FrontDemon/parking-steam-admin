//封装函数  
       //address:输入的地址;  
       //id:地图渲染的容器;  
       //key:APIkey;  
$(function(){
	window.onload = function(){
    	$('#btn').trigger('click'); 
    }
    //点击事件  
    var div1=document.getElementById('allmap');  
    $('#btn').on('click',function() {  
         //调用函数  
         getAddress($('#text').val(),div1,'QM4BZ-NBQHS-DDPOH-6UF53-DQ5YZ-S2FQU');  
       });  
});

 function getAddress(address,id,key){  
         $.ajax({  
           type:'get',  
           url:'http://apis.map.qq.com/ws/geocoder/v1/?address='+address+'&key='+key+'&output=jsonp',  
           dataType:'jsonp',  
           jsonp:'callback',  
           success:function(data) {  
             var posi=data.result.location;  
             console.log(posi);  
             var center = new qq.maps.LatLng(posi.lat,posi.lng);  
             var map = new qq.maps.Map(id,{  
               center: center,  
               zoom: 20  
             });  
             //创建marker 标记 
             var marker = new qq.maps.Marker({  
               position: center,  
               map: map  
             });  
           },  
           error:function() {  
             alert('无法加载地图，请检查网络是否连接！');  
           }  
         });  
       }  
         