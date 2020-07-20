<%response.sendRedirect("login");%>
<!DOCTYPE html>
<html>
<head>
    <base href="http://demos.telerik.com/kendo-ui/validator/index">
    <style>html { font-size: 14px; font-family: Arial, Helvetica, sans-serif; }</style>
    <title></title>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.common-material.min.css" />
	    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.min.css" />
	    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.mobile.min.css" />
		<link href="<c:url value='/resources/styles/examples-offline.css'/>" rel="stylesheet" />
		<link href="<c:url value='/resources/css/common/_header.css'/>" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common/common.css'/>" />
		
		<!-- Add By Paul Begin -->
		<c:if test="${header_type == 'kendo'}">		
			<link rel="stylesheet" href="<c:url value='/resources/assets/css/styles.css?=140'/>" >
    	</c:if> 
    	<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600' rel='stylesheet' type='text/css'>
    	<link href="<c:url value='/resources/assets/demo/variations/default.css'/>" rel='stylesheet' type='text/css' media='all' id='styleswitcher'> 
    	<link href="<c:url value='/resources/assets/demo/variations/default.css'/>" rel='stylesheet' type='text/css' media='all' id='headerswitcher'>
		<link rel='stylesheet' type='text/css' href="<c:url value='/resources/assets/plugins/form-daterangepicker/daterangepicker-bs3.css'/>" /> 
		<link rel='stylesheet' type='text/css' href="<c:url value='/resources/assets/plugins/fullcalendar/fullcalendar.css'/>" /> 
		<link rel='stylesheet' type='text/css' href="<c:url value='/resources/assets/plugins/form-markdown/css/bootstrap-markdown.min.css'/>" /> 
		<link rel='stylesheet' type='text/css' href="<c:url value='/resources/assets/plugins/codeprettifier/prettify.css'/>" /> 
		<link rel='stylesheet' type='text/css' href="<c:url value='/resources/assets/plugins/form-toggle/toggles.css'/>" /> 
		<link rel='stylesheet' type='text/css' href="<c:url value='/resources/assets/plugins/codeprettifier/prettify.css'/>" /> 
		<link rel='stylesheet' type='text/css' href="<c:url value='/resources/assets/plugins/form-toggle/toggles.css'/>" /> 
		<!-- Add By Paul End -->	
		
		
		<script src="https://kendo.cdn.telerik.com/2017.1.223/js/jquery.min.js"></script>
    	<script src="https://kendo.cdn.telerik.com/2017.1.223/js/kendo.all.min.js"></script>
		<script src="<c:url value='/resources/js/kendo/kendo.timezones.min.js' />"></script>
		<script src="<c:url value='/resources/js/kendo/console.js'/>"></script>
		<script src="<c:url value='/resources/js/kendo/prettify.js'/>"></script>
		<script src="<c:url value='/resources/js/common/_header.js'/>"></script>
		<script src="<c:url value='/resources/js/common/_config.js'/>"></script>
		
		<!-- Remarked by Paul -->
		<!--  <script src="<c:url value='/resources/js/common/jQuery.pulsate.min.js'/>"></script> -->
		<!--  -->
		<!-- Add By Paul Begin -->		
		<script type='text/javascript' src="<c:url value='/resources/assets/js/bootstrap.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/js/enquire.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/js/jquery.cookie.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/js/jquery.nicescroll.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/codeprettifier/prettify.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/easypiechart/jquery.easypiechart.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/sparklines/jquery.sparklines.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/form-toggle/toggle.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/fullcalendar/fullcalendar.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/form-daterangepicker/daterangepicker.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/form-daterangepicker/moment.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/charts-flot/jquery.flot.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/charts-flot/jquery.flot.resize.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/charts-flot/jquery.flot.orderBars.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/pulsate/jQuery.pulsate.min.js' />"></script>
		<script type='text/javascript' src="<c:url value='/resources/assets/plugins/form-toggle/toggle.min.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/demo/demo-index.js' />"></script>		
		<script type='text/javascript' src="<c:url value='/resources/assets/js/placeholdr.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/js/application.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/demo/demo.js' />"></script> 
		<!-- Add By Paul End -->	
</head>
<body>

        <div id="example">
        <div class="demo-section k-content">
        <div id="tickets">
            <form id="ticketsForm">
                <ul id="fieldlist">
                    <li>
                        <label for="fullname" class="required">Your Name</label>
                        <input type="text" id="fullname" name="fullname" class="k-textbox" placeholder="Full name" required validationMessage="Enter {0}" style="width: 220px;" />
                    </li>
                    <li>
                        <label for="search" class="required">Movie</label>
                        <input type="search" id="search" name="search" required validationMessage="Select movie" style="width: 220px;" /><span class="k-invalid-msg" data-for="search"></span>
                    </li>
                    <li>
                        <label for="time">Start time</label>
                        <select name="time" id="time" required data-required-msg="Select start time" style="width: 220px;" >
                            <option>14:00</option>
                            <option>15:30</option>
                            <option>17:00</option>
                            <option>20:15</option>
                        </select>
                        <span class="k-invalid-msg" data-for="time"></span>
                    </li>
                    <li>
                        <label for="amount">Amount</label>
                        <input id="amount" name="Amount" type="text" min="1" max="10" value="1" required data-max-msg="Enter value between 1 and 10" style="width: 220px;" />
                        <span class="k-invalid-msg" data-for="Amount"></span>
                    </li>
                    <li>
                        <label for="email" class="required">Email</label>
                        <input type="email" id="email" name="Email" class="k-textbox" placeholder="e.g. myname@example.net"  required data-email-msg="Email format is not valid"  style="width: 220px;"/>
                    </li>
                    <li>
                        <label for="tel" class="required">Phone</label>
                        <input type="tel" id="tel" name="tel" class="k-textbox" pattern="\d{10}" placeholder="Enter a ten digit number" required
                            validationMessage="Enter at least ten digits" style="width: 220px;" />
                    </li>
                    <li  class="accept">
                        <label>Terms of Service</label>
                        <input type="checkbox" name="Accept" required validationMessage="Acceptance is required" /> <p>I accept the terms of service.</p>
                    </li>
                    <li class="confirm">
                        <button class="k-button k-primary" type="submit">Submit</button>
                    </li>
                    <li class="status">
                    </li>
                </ul>
            </form>
        </div>
        </div>

            <style>
                #fieldlist {
                    margin: 0;
                    padding: 0;
                }

                #fieldlist li {
                    list-style: none;
                    padding-bottom: .7em;
                    text-align: left;
                }

                #fieldlist label {
                    display: block;
                    padding-bottom: .3em;
                    font-weight: bold;
                    text-transform: uppercase;
                    font-size: 12px;
                    color: #444;
                }

                #fieldlist li.status {
                    text-align: center;
                }

                #fieldlist li .k-widget:not(.k-tooltip),
                #fieldlist li .k-textbox {
                    margin: 0 5px 5px 0;
                }

                .confirm {
                    padding-top: 1em;
                }

                .valid {
                    color: green;
                }

                .invalid {
                    color: red;
                }

                #fieldlist li input[type="checkbox"] {
                    margin: 0 5px 0 0;
                }

                span.k-widget.k-tooltip-validation {
                    display; inline-block;
                    width: 160px;
                    text-align: left;
                    border: 0;
                    padding: 0;
                    margin: 0;
                    background: none;
                    box-shadow: none;
                    color: red;
                }

                .k-tooltip-validation .k-warning {
                    display: none;
                }
            </style>

            <script>
                $(document).ready(function() {
                    var data = [
                    "12 Angry Men",
                    "Il buono, il brutto, il cattivo.",
                    "Inception",
                    "One Flew Over the Cuckoo's Nest",
                    "Pulp Fiction",
                    "Schindler's List",
                    "The Dark Knight",
                    "The Godfather",
                    "The Godfather: Part II",
                    "The Shawshank Redemption"
                    ];

                    $("#search").kendoAutoComplete({
                        dataSource: data,
                        separator: ", "
                    });

                    $("#time").kendoDropDownList({
                        optionLabel: "--Start time--"
                    });

                    $("#amount").kendoNumericTextBox();

                    var validator = $("#ticketsForm").kendoValidator().data("kendoValidator"),
                    status = $(".status");

                    $("form").submit(function(event) {
                        event.preventDefault();
                        if (validator.validate()) {
                            status.text("Hooray! Your tickets has been booked!")
                                .removeClass("invalid")
                                .addClass("valid");
                        } else {
                            status.text("Oops! There is invalid data in the form.")
                                .removeClass("valid")
                                .addClass("invalid");
                        }
                    });
                });
            </script>
        </div>


</body>
</html>
