function settipinfo(info, type) {
	$("#confirmModal_tip").text(info);
	$("#confirmModal_submit").removeAttr('onclick');
	$("#confirmModal_submit").attr("onclick","confirm('" + type + "')");	
}
$('#confirmModal').on('hidden.bs.modal', function () {
	$("#confirmModal_tip").text("确认!");
	$('#confirmModal_submit').show();
	$("#myModalLabel").text("加入确认");
})

