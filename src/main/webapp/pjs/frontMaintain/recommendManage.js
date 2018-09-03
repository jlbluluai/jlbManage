/*刷新页面逻辑*/
$("#refresh").click(function() {
	window.location.reload();
});

/*数据回显逻辑*/
$(function() {
	$getRecommends();
});

//获取推荐信息
var $getRecommends = function() {
	$.ajax({
		type : "get",
		url : "getRecommends",
		dataType : "json",
		data :{
		},
		async : true,
		success : function(data) {
			console.log(data);
			$("#recommends").empty();
			$("#t1").tmpl(data).appendTo("#recommends");
		},
		error : function() {
			alert("数据获取失败");
		}
	});
}

/*打开模态框逻辑*/
//修改大类模态框
$(document).on('click', '.cchange', function() {
	$("#changeCname").val("");
	var temp = $(this).parent().children(".categoryId").text();
	$("#cchangeid").val(temp);
	var category = $(this).parent().children("span").text();
	$("#cc").text(category);
	$('#modal-form-change-category').modal('show');
});

//修改推荐模态框
$(document).on('click', '.change', function() {
	$("#changeName").val("");
	$("#changeUrl").val("");
	$("#changeTag").val("");
	var temp = $(this).parent().children(".reid").text();
	$("#changeid").val(temp);
	$("#changeName").val($(this).parent().children(".rename").text());
	$("#changeUrl").val($(this).parent().children(".reurl").text());
	$("#changeTag").val($(this).parent().children(".retag").text());
	var category = $(this).parent().parent().parent().parent().children(".dd-handle").children("span").text();
	$("#category").text(category);
	$('#modal-form-change').modal('show');
});

//添加推荐模态框
$(document).on('click', '.add', function() {
	$("#addName").val("");
	$("#addUrl").val("");
	$("#addTag").val("");
	var temp = $(this).parent().children(".categoryId").text();
	$("#cid").val(temp);
	var category = $(this).parent().children("span").text();
	$("#pc").text(category);
	$("#modal-form-add").modal('show');
});

//添加板块模态框
$("#addCategory").click(function(){
	$("#addCname").val("");
	$("#modal-form-add-category").modal('show');
});

/*板块逻辑*/
//添加
$("#cAddSub").click(function() {
	var name = $("#addCname").val();
	if(name == "") {
		layer.msg("请输入板块名");
	} else {
		$.ajax({
			type : "post",
			url : "saveOneRecCat",
			async : true,
			dataType : "json",
			data :{
				name : name
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
});

//修改
$("#cChangeSub").click(function() {
	var id = $("#cchangeid").val();
	console.log(id);
	var name = $("#changeCname").val();
	console.log(name);
	if(name == "") {
		layer.msg("请输入要变更的名称");
	} else {
		$.ajax({
			type : "post",
			url : "changeOneRecCat",
			async : true,
			dataType : "json",
			data :{
				id : id,
				name : name
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
});

//删除
$(document).on('click', '.cdelete', function() {
	var id = $(this).parent().children(".categoryId").text();
	var name = $(this).parent().children("span").text();
	layer.confirm('确认删除该板块:' + name, {
		btn: ['确认', '取消'], //按钮
		shade: false //不显示遮罩
	}, function() {
		$.ajax({
			type : "post",
			url : "cutOneRecCat",
			async : true,
			dataType : "json",
			data :{
				id : id
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
	}, function() {});
});

/*推荐逻辑*/
//添加
$("#addSub").click(function() {
	var id = $("#cid").val();
	var name = $("#addName").val();
	var url = $("#addUrl").val();
	var tag = $("#addTag").val();
	if(name == "") {
		layer.msg("请输入名称");
	} else if(url == "") {
		layer.msg("请输入地址");
	} else if(tag == "") {
		layer.msg("请输入标签");
	} else {
		$.ajax({
			type : "post",
			url : "saveOneRecommend",
			async : true,
			dataType : "json",
			data :{
				name : name,
				url : url,
				tag : tag,
				cid : id
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
});

//修改
$("#changeSub").click(function() {
	var id = $("#changeid").val();
	var name = $("#changeName").val();
	var url = $("#changeUrl").val();
	var tag = $("#changeTag").val();
	if(name == "") {
		layer.msg("请输入名称");
	} else if(url == "") {
		layer.msg("请输入地址");
	} else if(tag == "") {
		layer.msg("请输入标签");
	} else {
		$.ajax({
			type : "post",
			url : "changeOneRecommend",
			async : true,
			dataType : "json",
			data :{
				id : id,
				name : name,
				url : url,
				tag : tag
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
});

//删除
$(document).on('click', '.delete', function() {
	var id = $(this).parent().children(".reid").text();
	var name = $(this).parent().children("span").text();
	layer.confirm('确认删除该推荐:' + name, {
		btn: ['确认', '取消'], //按钮
		shade: false //不显示遮罩
	}, function() {
		$.ajax({
			type : "post",
			url : "cutOneRecommend",
			async : true,
			dataType : "json",
			data :{
				id : id
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
	}, function() {});
});