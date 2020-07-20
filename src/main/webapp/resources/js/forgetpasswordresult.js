forgetVerifyObj = null;
$(function(){
	var forgetForm = $("#forgetForm");
	kendo.init(forgetForm);
	
	forgetVerifyObj = forgetForm.kendoValidator({
		rules: {
            verifyPasswords: function(input){
               var ret = true;
               if (input.is("[name=userPassword]")) {
            	   $('#userPassword').val(input.val());
               }
               if (input.is("[name=confirmPassword]")) {
                   ret = (input.val() === $("#userPassword").val());
               }
               return ret;
            }
        }
    }).data("kendoValidator");
});
function resetPasswordSubmit(){
	if (forgetVerifyObj.validate()) {
		var jsonDataArray = new Array();
		var jsonData = {};
		jsonData["userMail"] = $.trim($('#userMail').val().replace(" ",""));
		jsonData["userPassword"] = $.trim($('#userPassword').val().replace(" ",""));
		jsonDataArray.push(jsonData);
		
		$.ajax({
            type: 'POST',
            contentType : "application/json",
            url: "resetPasswordSubmit",
            data: JSON.stringify(jsonDataArray), 
            dataType: 'json',
            timeout : 100000,
            success: function(data, result) {
            	if(data.code!=200){
            		$('.forgetStatus').html(data.message)
            		.removeClass("valid").addClass("invalid");
            	} else if(data.code == 200){
            		$('.successContent').hide();
            		$('.failContent').show();
            		$('.message').text(data.message);
            	} else {
            		registVerifyObj.hideMessages();
            		$('#forgetForm input').val('');
            		$('.forgetStatus').html('');
            	}
            },
            error: function(e) {
            	alert(e.tostring());
            }
        });
	}
}