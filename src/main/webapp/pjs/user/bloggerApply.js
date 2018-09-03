var currentPage = 1; //当前页，作为交互必备的参数，设置初值为1
var total; //总记录数
var lastPage; //最后一页标号，即总页数
var pageNum = 10; //每页记录数，作为交互必备的参数，设置初值为设置的pageSizeList的第一位,pageSizeList修改去public.js

var begin_time = "";
var end_time = "";
var status = "100";
var isBlogger = "0";

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


$(function() {
	$getItems();
});

var $getItems = function() {
	$.ajax({
		type : "get",
		url : "getPageBloggerApplys",
		dataType : "json",
		data :{
			currentPage : currentPage,
			limitNums : pageNum,
			beginTime : begin_time,
			endTime : end_time,
			status : status,
			isBlogger : isBlogger
		},
		async : true,
		success : function(data) {
			console.log(data);
			currentPage = data.currentPage;
			total = data.totalNums;
			pageNum = data.limitNums;
			lastPage = data.totalPages;
			$("#applys").empty();
			$("#t1").tmpl(data.oList).appendTo("#applys");
			$paging();
		},
		error : function() {
			alert("数据获取失败");
		}
	});
}

$("#refresh").click(function() {
	window.location.reload();
});

/*多条件查询逻辑*/
$("#query").click(function() {
	var queryStatus = $("select[name='queryStatus']").val();
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	console.log("状态：" + queryStatus + " 开始：" + beginTime + " 结束：" + endTime);
	if(beginTime == "" && endTime != "" || beginTime != "" && endTime == "") {
		layer.msg('请确保起止时间要么都填要么都不填');
	} else if(beginTime > endTime) {
		layer.msg('开始时间不能大于结束时间');
	} else {
		begin_time = beginTime;
		end_time = endTime;
		status = queryStatus;
		$getItems();
	}
});

/*模态框-审批逻辑*/
$("input[name='auditRadios']").click(function() {
	var result = $(this).val();
	if(result == 1) {
		$("#rejectReason").remove();
	} else if(result == 2) {
		var rejectReason = '<tr id="rejectReason">\
											<td>\
												<label class="control-label">驳回理由：</label>\
											</td>\
											<td>\
												<textarea id="reject_reason" class="form-control"></textarea>\
											</td>\
										</tr>';
		$("#modal-form-approve table tbody").append(rejectReason);
	}
});

$("#audit_submit").click(function() {
	var appId = $("#appId").text();
	var userid = $("#userid").text();
	var username = $("#appUsername").text();
	var applyreason = $("#appApplyreason").text();
	var temp = $("input[name='auditRadios']:checked").val();
	var result;
	var rejectReason = $("#reject_reason").val();
	if(temp == 1) {
		//询问框
		layer.confirm("本次审批结果为:批准<br/>请确认信息<br/>用户：" +
			username + "<br/>申请理由:" + applyreason, {
				btn: ['确认提交', '再审核一会'], //按钮
				shade: false //不显示遮罩
			},
			function() {
				//处理业务
				$handleOneApply(appId , 1, "");
			},
			function() {});
	} else if(temp == 2) {
		if(rejectReason != "") {
			//询问框
		 	layer.confirm("本次审批结果为:驳回<br/>请确认信息<br/>用户：" +
				username + "<br/>申请理由:" + applyreason +
				"<br/>驳回理由：" + rejectReason, {
					btn: ['确认提交', '再审核一会'], //按钮
					shade: false //不显示遮罩
				},
				function() {
					//处理业务
					$handleOneApply(appId , 2, rejectReason);
				},
				function() {});
		} else {
			layer.msg('请输入驳回理由');
		}
	}
});

//提交
var $handleOneApply = function(appId, status, reason) {
	$.ajax({
		type : "post",
		url : "handleOneApplyBlogger",
		async : true,
		dataType : "json",
		data :{
			id : appId,
			status : status,
			reason : reason
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

/*打开模态框逻辑*/
//审批模态框
$(document).on('click', '.approve', function() {
	var appId = $(this).parent().parent().children("td:eq(0)").text();
	var appUser = $(this).parent().parent().children("td:eq(1)").text();
	var appTime = $(this).parent().parent().children("td:eq(2)").text();
	var appReason = $(this).parent().parent().children("td:eq(4)").text();

	$("#appId").text(appId);
	$("#appUsername").text(appUser);
	$("#appTime").text(appTime);
	$("#appApplyreason").text(appReason);

	$('#modal-form-approve').modal('show');
});

//查看模态框
$(document).on('click', '.examine', function() {
	var exaId = $(this).parent().parent().children("td:eq(0)").text();
	var exaUser = $(this).parent().parent().children("td:eq(1)").text();
	var exaTime = $(this).parent().parent().children("td:eq(2)").text();
	var exaReason = $(this).parent().parent().children("td:eq(4)").text();
	var exaStatus = $(this).parent().children("span").text();
	
	$("#exaId").text(exaId);
	$("#exaUsername").text(exaUser);
	$("#exaTime").text(exaTime);
	$("#exaApplyReason").text(exaReason);
	$("#exaStatus").text(exaStatus);

	$('#modal-form-examine').modal('show');
});