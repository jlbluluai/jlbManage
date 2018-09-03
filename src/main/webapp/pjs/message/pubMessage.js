/*页面刷新*/
$("#refresh").click(function() {
	window.location.reload();
});

/*发送逻辑*/
$(".pbu").click(function() {

	var obj = $("select[name='sender']").val();
	var temp;
	if(obj == "1") {
		temp = "所有用户";
	} else {
		temp = "所有博主";
	}
	var subject = $.trim($("input[name='subject']").val());
	var content = $.trim($("textarea[name='content']").val());
	if(subject == "") {
		layer.msg('请输入主题');
	} else if(content == "") {
		layer.msg('请输入内容');
	} else {
		layer.confirm('请确认本次的发送内容 <br/>发送对象：' + temp + '<br/>主题：' + subject + "<br/>内容：" + content, {
			btn: ['确认', '再过会'], //按钮
			shade: false //不显示遮罩
		}, function() {
			$sendMessages(subject, content, obj);
		}, function() {});
	}

});

//发送
var $sendMessages = function(subject, content, objects) {
	$.ajax({
		type : "post",
		url : "savePubMesssage",
		async : true,
		dataType : "json",
		data :{
			subject : subject,
			content : content,
			object : objects
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