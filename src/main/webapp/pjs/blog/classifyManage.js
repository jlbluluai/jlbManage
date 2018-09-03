/*页面刷新*/
$("#refresh").click(function() {
	window.location.reload();
});

/*nestable_list逻辑*/
$("#nestable2 a").click(function() {
	//alert(1);
});

/*回显数据*/
$(function() {
	$getCategory();
});

//获取分类
var $getCategory = function() {
	$.ajax({
		type : "get",
		url : "getBlogCategorys",
		async : true,
		success : function(data) {
			console.log(data);
			$("#t1").tmpl(data).appendTo("#categorys");
			$("#t2").tmpl(data).appendTo("#pcategory");
		},
		error : function() {
			alert("数据获取失败");
		}
	});
}

/*打开模态框逻辑*/
//修改模态框
$(document).on('click', '.change', function() {
	var cid = $(this).parent().parent().attr("data-id");
	var level = $(this).parent().parent().attr("data-level");
	var cname = $(this).parent().parent().children("div:eq(0)").children("span").text();
	var cpname;
	if(level == "first") {
		cpname = "无";
	} else {
		cpname = $(this).parent().parent().parent().parent().children("div:eq(0)").children("span").text();
	}

	$("#cid").text(cid);
	$("#cname").text(cname);
	$("#cpname").text(cpname);

	$('#modal-form-change').modal('show');
});

//添加模态框
$(document).on('click', '#add', function() {
	$('#modal-form-save').modal('show');
});

/*处理业务逻辑*/
//修改提交
$("#change_submit").click(function() {
	var cid = $("#cid").text();
	var oldname = $("#cname").text();
	var newname = $("#cNewname").val();
	if(newname == "") {
		layer.msg("请填写新名称")
	} else {
		layer.confirm("本次修改为:<br/>请确认信息<br/>原名称：" +
			oldname + "<br/>新名称：" + newname, {
				btn: ['确认提交', '取消'], //按钮
				shade: false //不显示遮罩
			},
			function() {
				//处理业务
				$.ajax({
					type : "post",
					url : "changeOneBlogCategory",
					async : true,
					dataType : "json",
					data :{
						id : cid,
						name : newname
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
			},
			function() {});
	}
});

//添加提交
$("#save_submit").click(function() {
	var pcategory = $("select[name='pcategory']").val();
	var aNewname = $("#aNewname").val();
	if(aNewname == ""){
		layer.msg("请填写名称");
	} else {
		layer.confirm("本次添加为:<br/>请确认信息<br/>名称：" +
			aNewname + "<br/>父类：" + pcategory, {
				btn: ['确认提交', '取消'], //按钮
				shade: false //不显示遮罩
			},
			function() {
				//处理业务
				$.ajax({
					type : "post",
					url : "addOneBlogCategory",
					async : true,
					dataType : "json",
					data :{
						pid : pcategory,
						name : aNewname
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
			},
			function() {});
	}
});