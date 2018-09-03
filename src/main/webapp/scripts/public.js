var $paging = function() {
			$('#pageToolbar').empty();
			$('#pageToolbar').Paging({
				pagesize: pageNum,
				count: total,
				current: currentPage,
				toolbar: true,
				pageSizeList: [10, 20, 30],
				callback: function(current, pagesize, pages, count) {
					$('.ui-pager').on("click", function() {
						if(!$(this).hasClass("ui-pager-disabled")) {
							var curr = $.trim($(this).text());
							var focus = $.trim($("#pageToolbar").find(".focus").text());
							if(curr != focus) {
								if(curr == "首页") {
									currentPage = 1;
								} else if(curr == "上一页") {
									focus = parseInt(focus);
									currentPage = --focus;
								} else if(curr == "末页") {
									currentPage = lastPage;
								} else if(curr == "下一页") {
									currentPage = ++focus;
								} else {
									currentPage = parseInt(curr);
								}
								$getItems();
							}
						}

					});

					$("#go").on("click", function() {
						var goPage = $.trim($(".ui-paging-count").val());
						if(goPage != currentPage) {
							currentPage = parseInt(goPage);
							$getItems();
						}
					});

				}
			});

			$('.ui-pager').on("click", function() {
				if(!$(this).hasClass("ui-pager-disabled")) {
					var curr = $.trim($(this).text());
					var focus = $.trim($("#pageToolbar").find(".focus").text());
					if(curr != focus) {
						if(curr == "首页") {
							currentPage = 1;
						} else if(curr == "上一页") {
							focus = parseInt(focus);
							currentPage = --focus;
						} else if(curr == "末页") {
							currentPage = lastPage;
						} else if(curr == "下一页") {
							focus = parseInt(focus);
							currentPage = ++focus;
						} else {
							currentPage = parseInt(curr);
						}
						$getItems();
					}
				}
			});

			$("#go").on("click", function() {
				var goPage = $.trim($(".ui-paging-count").val());
				if(goPage != currentPage) {
					currentPage = parseInt(goPage);
					$getItems();
				}
			});

			$(".ui-select-pagesize").on("change", function() {
				pageNum = parseInt($(".ui-select-pagesize").val());
				currentPage = 1;
				$getItems();
			});
		}
