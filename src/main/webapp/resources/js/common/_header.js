
msgInit = function() {
	$('#notifications').kendoNotification(messageConf);
}
showMsg = function(message) {
	$('#notifications').data("kendoNotification").show(message);
}


