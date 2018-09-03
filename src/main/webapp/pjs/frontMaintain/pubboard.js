/*页面刷新*/
$("#refresh").click(function() {
	window.location.reload();
});

/*回显当前公板信息逻辑*/
$(function() {
	$getThePubboard();
});

//获取当前的公板信息
var $getThePubboard = function() {
	$.ajax({
		type : "get",
		url : "getThePubboard",
		dataType : "json",
		data :{
		},
		async : true,
		success : function(data) {
			console.log(data);
			$("#show_title").text(data.title);
			$("#show_content").text(data.content);
		},
		error : function() {
			alert("数据获取失败");
		}
	});
}

/*提交新公板信息逻辑*/
$("button").click(function() {
	var title = $.trim($("input[name='title']").val());
	var content = $.trim($("textarea[name='content']").val());
	if(title == "") {
		layer.msg('请输入标题');
	} else if(content == "") {
		layer.msg('请输入内容');
	} else {
		layer.confirm('请确认本次的提交内容 <br/>标题：' + title + '<br/>内容：' + content, {
			btn: ['确认', '再过会'], //按钮
			shade: false //不显示遮罩
		}, function() {
			$addOnePubboard(title, content);
		}, function() {});
	}
});

//提交新的公板信息
var $addOnePubboard = function(title, content) {
	$.ajax({
		type : "post",
		url : "saveOnePubboard",
		async : true,
		dataType : "json",
		data :{
			title : title,
			content : content
		},
		success : function(data) {
			console.log(data);
			if(data.message == "yes"){
				window.location.reload();
			}else{
				layer.msg(data.message);
			}
		},
		error : function() {
			alert("数据获取失败");
		}
	});
}