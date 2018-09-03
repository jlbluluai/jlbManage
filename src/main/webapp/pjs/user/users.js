/*初始化数据*/
var currentPage = 1; //当前页，作为交互必备的参数，设置初值为1
var total; //总记录数
var lastPage; //最后一页标号，即总页数
var pageNum = 10; //每页记录数，作为交互必备的参数，设置初值为设置的pageSizeList的第一位,pageSizeList修改去public.js


//转换时间
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

	return str;
}

var begin_time = "";
var end_time = "";
var user_id = "";
var user_name = "";
var nick_name = "";
var e_mail = "";

/*数据回显*/
$(function() {
	$getItems();
});

var $getItems = function() {
	$.ajax({
		type : "get",
		url : "getPageUsers",
		dataType : "json",
		data :{
			currentPage : currentPage,
			limitNums : pageNum,
			beginTime : begin_time,
			endTime : end_time,
			userId : user_id,
			username : user_name,
			nickname : nick_name,
			email : e_mail
		},
		async : true,
		success : function(data) {
			console.log(data);
			currentPage = data.currentPage;
			total = data.totalNums;
			pageNum = data.limitNums;
			lastPage = data.totalPages;
			$("#users").empty();
			$("#t1").tmpl(data.oList).appendTo("#users");
			$paging();
		},
		error : function() {
			alert("数据获取失败");
		}
	});
}

/*刷新逻辑*/
$("#refresh").click(function() {
	window.location.reload();
});

/*多条件查询逻辑*/
$("#query").click(function() {
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var userid = $("#userId").val();
	var username = $("#userName").val();
	var nickname = $("#nickName").val();
	var email = $("#eMail").val();
	console.log(" 开始：" + beginTime + " 结束：" + endTime + " 用户id：" + userid + " 用户名：" + username +
		" 昵称：" + nickname + " 邮箱：" + email);
	if(beginTime == "" && endTime != "" || beginTime != "" && endTime == "") {
		layer.msg('请确保起止时间要么都填要么都不填');
	} else if(beginTime > endTime) {
		layer.msg('开始时间不能大于结束时间');
	} else {
		begin_time = beginTime;
		end_time = endTime;
		user_id = userid;
		user_name = username;
		nick_name = nickname;
		e_mail = email;
		$getItems();
	}
});

/*打开模态框逻辑*/
$(document).on('click', '.examine', function() {
	var id = $(this).parent().parent().children("td:eq(0)").text();
	$getTheUser(id);
	$('#modal-form-examine').modal('show');
});

var $getTheUser = function(id) {
	$.ajax({
		type : "get",
		url : "getOneUserById",
		dataType : "json",
		data :{
			id : id,
		},
		async : true,
		success : function(data) {
			console.log(data);
			$("#userid").text(data.id);
			$("#username").text(data.username);
			$("#nickname").text(data.nickname);
			$("#email").text(data.email);
			$("#makeday").text(dateFormat(data.makeDay));
			$("#truename").text(data.userInfo.truename);
			if(data.userInfo.sex == 1){
				$("#sex").text("男");
			}else{
				$("#sex").text("女");
			}
			$("#birthday").text(dateFormat(data.userInfo.birthday));
			$("#address").text(data.userInfo.address);
			$("#qq").text(data.userInfo.qq);
			$("#description").text(data.userInfo.description);
		},
		error : function() {
			alert("数据获取失败");
		}
	});
}

