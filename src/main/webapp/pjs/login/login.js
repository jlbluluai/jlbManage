/*登录逻辑*/
$("#login").click(function() {
	var username = $.trim($("#username").val());
	var password = $.trim($("#password").val());
	
	if(username != "" && password != ""){
		$.ajax({
			type : "post",
			url : "goLogin",
			async : true,
			dataType : "json",
			data : {
				username : username,
				password : password
			},
			success : function(data) {
				console.log(data);
				if(data.message == "yes"){
					window.location.href = "index";
				} else{
					alert(data.message);
				}
			},
			error : function() {
				alert("数据获取失败");
			}
		});
	}

});