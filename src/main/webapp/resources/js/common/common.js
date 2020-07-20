$(function(){
	if ($('.hidden').val()){
		$('.message').hide();
		$('.message').text($('.hidden').val());
		$('.message').fadeIn(800).delay(1500).fadeOut(1000);
	}
	$('.autofocus').focus();
});