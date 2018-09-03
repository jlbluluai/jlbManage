/*子页面刷新逻辑*/
$("#refresh").click(function() {
	window.location.reload();
});


function dateFormat(time) {
	var date = new Date(time);

	var str = "";
	str += date.getFullYear() + "-";
	if (date.getMonth() + 1 < 10) {
		str += "0" + (date.getMonth() + 1) + "-";
	} else {
		str += (date.getMonth() + 1) + "-";
	}
	if (date.getDate() < 10) {
		str += "0" + date.getDate() + " ";
	} else {
		str += date.getDate() + " ";
	}

	if (date.getHours() < 10) {
		str += "0" + date.getHours() + ":";
	} else {
		str += date.getHours() + ":";
	}
	if (date.getMinutes() < 10) {
		str += "0" + date.getMinutes();
	} else {
		str += date.getMinutes();
	}

	return str;
}

/*历史版本逻辑*/
$(function() {
	$getVersions();
});

//历史版本信息回显
var $getVersions = function() {
	$.ajax({
		type : "get",
		url : "getVersions",
		dataType : "json",
		data :{
		},
		async : true,
		success : function(data) {
			console.log(data);
			$("#version").empty();
			$("#t1").tmpl(data).appendTo("#version");
		},
		error : function() {
			alert("数据获取失败");
		}
	});
}

/*更新版本逻辑*/
//添加一条记录
$("#addOneNew").click(function() {
	var log = $("#oneNew").val();
	if(log == "") {
		alert("请输入版本");
	} else {
		var str = '<li style="list-style: none;margin-left: -40px;font-size: 16px;">\
									<a href="#" class="deleteLog">\
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>\
									</a>\
									<span>' + log + '</span>\
								</li>';
		$("#versions").append(str);
		$("#oneNew").val("");
	}
});

//删除一条记录
$("#versions").on("click", ".deleteLog", function() {
	var temp = $(this).next().text();

	if(confirm("是否确认删除这条记录：" + temp)) {
		$(this).parent().remove();
	}
});

//生成版本
$("#create").click(function() {
	var content = "";
	var count = 1;
	$('#versions').find('li').each(function() {
		var log = $.trim($(this).children(1).text());
		content = content + count + "、" + log + "<br />";
		count++;
	});

	content = content.substring(0, content.length - 6);

	var versionNum = $("#versionNum").val();

	if(versionNum == "") {
		layer.msg('请输入版本号');
	} else if(content == "") {
		layer.msg('请输入版本信息');
	} else {
		layer.confirm('确认本次提交的版本:' + versionNum + ' ' + content, {
			btn: ['确认', '取消'], //按钮
			shade: false //不显示遮罩
		}, function() {
			$addOneVersion(versionNum, content);
			window.location.reload();
		}, function() {});
	}
});

//添加一条版本
var $addOneVersion = function(versionNum, content) {
	$.ajax({
		type : "post",
		url : "saveOneVersion",
		async : true,
		dataType : "json",
		data :{
			title : versionNum,
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