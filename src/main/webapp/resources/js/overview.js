$(document).ready(function() {
	
	// 處理麵包屑及本頁表頭
	$("#breadcrumb").html('<li><a href="javascript:;" onclick="return home_action(this);"><i class="fa fa-home"></i> Home</a></li>');
	$("#page-heading").find('h1').html($("#pageLabel").val());
	
	
	grid = $("#grid").data("kendoGrid");
	msgInit();

});
