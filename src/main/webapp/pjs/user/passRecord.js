/*刷新逻辑*/
$("#refresh").click(function() {
	window.location.reload();
});

/*初始数据*/
var currentPage = 1; //当前页，作为交互必备的参数，设置初值为1
var total; //总记录数
var lastPage; //最后一页标号，即总页数
var pageNum = 10; //每页记录数，作为交互必备的参数，设置初值为设置的pageSizeList的第一位,pageSizeList修改去public.js

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

/*数据回显*/
$(function() {
	$getItems();
});

var $getItems = function() {
	$.ajax({
		type : "get",
		url : "getPagePasschange",
		dataType : "json",
		data :{
			currentPage : currentPage,
			limitNums : pageNum
		},
		async : true,
		success : function(data) {
			console.log(data);
			currentPage = data.currentPage;
			total = data.totalNums;
			pageNum = data.limitNums;
			lastPage = data.totalPages;
			$("#passes").empty();
			$("#t1").tmpl(data.oList).appendTo("#passes");
			$paging();
		},
		error : function() {
			alert("数据获取失败");
		}
	});
}