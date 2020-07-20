optionalBox = null, selectedBox = null;
$(document).ready(function() {
	// 處理麵包屑及本頁表頭
	$("#breadcrumb").html('<li><a href="javascript:;" onclick="return home_action(this);"><i class="fa fa-home"></i> Home</a></li>'+
			'<li class="active">'+$("#breadCrumbLabel").val()+'</li>');
	$("#page-heading").find('h1').html($("#pageLabel").val());
	
	msgInit();
	
	$("#prodShareSubmit").kendoButton();
	
	
    $("#grid").kendoGrid({
        dataSource: {
        	transport: {
        		read: {
	        		type: "POST",
	                url: "api/getUserDatas",
	                dataType: "json"
        		}
            },
            schema: {
                model: {
                    fields: {
                    	userStatus: { type: "string" },
                    	userLocalName: { type: "string"},
                        userMail: { type: "string"},
                        userName: { type: "string"},
                        userBirthday: { type: "string" },
                        userAddress: { type: "string" },
                        userDateCreated: { type: "string" },
                        userLastDateModify: { type: "string" }
                    }
                }
            },
            pageSize: 55
        },
        toolbar: kendo.template($("#template").html()),
        dataBound: onDataBound,
        
        scrollable: true,
        sortable: true,
        filterable: {
        	mode: "row"
        },
        pageable: {
            input: true,
            numeric: true
        },
        columns: [
        	{ 	field: "userStatus", 
        		title: "<input type='checkbox' class='allCheck' />", 
        		template: "<input type='checkbox' class='checkbox' value='#: data.id #' #if(data.userStatus=='checked'){#checked#}# >",
        		width: "40px", 
        		filterable: false,
        		sortable: false
        	},
        	{ 
        		field: "userLocalName", 
        		title: "姓名",  
        		width: "200px",
	  			filterable: {
                    cell: {
                        operator: "contains",
                        suggestionOperator: "contains"
                    }
                }
        	},
        	{ 
        		field: "userName", 
        		title: "暱稱",  
        		width: "200px",
        		filterable: {
                    cell: {
                        operator: "contains",
                        suggestionOperator: "contains"
                    }
                }
        	},
        	{ 
        		field: "userMail", 
        		title: "電子郵件",  
        		width: "220px",
        		filterable: {
        			cell: {
        				operator: "contains",
        				suggestionOperator: "contains"
        			}
        		}
        	},
        	{ 
        		field: "userBirthday", 
        		title: "生日",  
        		width: "150px",
        		filterable: false
        	},
        	{ 
        		field: "userAddress", 
        		title: "地址",
        		template: "#: zipCode # #: userCity ##: userArea ##: userAddress #",
        		width: "300px",
        		filterable: {
        			cell: {
        				operator: "contains",
        				suggestionOperator: "contains"
        			}
        		}
        	},
        	{ 
        		field: "userDateCreated", 
        		title: "註冊時間",  
        		width: "150px",
        		filterable: false
        	},
        	{ 
        		field: "userLastDateModify", 
        		title: "信箱認證時間",  
        		width: "160px",
        		filterable: false
        	}
        ]
    });
    
	
	grid = $("#grid").data("kendoGrid");
	grid.table.on("click", "tr" , onChange);
	
	$('.allCheck').click(function(){
		var grid = $("#grid").data("kendoGrid"),
		trs = $("#grid").find("tbody").find("tr");
		if(this.checked){
			$.map($(trs),function(tr){
				dataItem = grid.dataItem(tr);
	    		$(tr)
	            .addClass("k-state-selected")
	            .find(".checkbox")
	            .prop("checked",true);
	    		dataItem.userStatus = "checked";
	    		dataItem.set("userStatus","checked");
		    });
		} else {
			$.map($(trs),function(tr){
				dataItem = grid.dataItem(tr);
	    		$(tr)
	            .removeClass("k-state-selected")
	            .find(".checkbox")
	            .prop("checked",false);
	    		dataItem.userStatus = "reject";
	    		dataItem.set("userStatus","reject");
		    });
		}
	});
	
	$(".submitButton").click(function(e){
		var jsonDataArray = new Array();
		
		
		$.map(grid.dataSource.data(),function(data){
			var dataString = JSON.stringify(data);
			var data2 = JSON.parse(dataString);
			data2.id = data.id;
			jsonDataArray.push(data2);
		})
		
		$.ajax({
            type: 'POST',
            contentType : "application/json",
            url: "api/kolDataSubmit",
            data: JSON.stringify(jsonDataArray), 
            dataType: 'json',
            timeout : 100000,
            success: function(data, result) {
            	showMsg(data.message);
            	$("#grid").data("kendoGrid").dataSource.read();
            },
            beforeSend:function(){
                $('.loader').show();
            },
            complete:function(){
                $('.loader').hide();
            },
            error: function(e) {
            	alert(e.tostring());
            }
        });
	});
	
	
});

function onDataBound(e) {
	var grid = $("#grid").data("kendoGrid"),
	trs = $("#grid").find("tbody").find("tr"),
	flag = true;
	$.map($(trs),function(tr){
		dataItem = grid.dataItem(tr);
		if(dataItem.userStatus != "checked") {
			flag = false;
		}
	});
	
	if(flag) {
		$(".allCheck").prop("checked",true);
	} else {
		$(".allCheck").prop("checked",false);
	}
	
    $.map(this.tbody.find("tr"),function(tr){
    	grid = $("#grid").data("kendoGrid"),
    	dataItem = grid.dataItem(tr);
    	if(dataItem.userStatus == "checked"){
    		$(tr)
            .addClass("k-state-selected")
            .find(".checkbox")
            .prop("checked",true);
    	}
    });
}


function onChange(e){
	var row = $(this).closest("tr"),
    grid = $("#grid").data("kendoGrid"),
    dataItem = grid.dataItem(row);
	
	if (dataItem.userStatus == "checked") {
        //-select the row
        row.removeClass("k-state-selected")
        .find(".checkbox")
        .prop("checked",false);
        dataItem.userStatus = "reject";
        dataItem.set("userStatus","reject");
    } else {
        //-remove selection
        row.addClass("k-state-selected")
        .find(".checkbox")
        .prop("checked",true);
        dataItem.userStatus = "checked";
        dataItem.set("userStatus","checked");
    }
	
	var grid = $("#grid").data("kendoGrid"),
	trs = $("#grid").find("tbody").find("tr"),
	flag = true;
	$.map($(trs),function(tr){
		dataItem = grid.dataItem(tr);
		if(dataItem.userStatus != "checked") {
			flag = false;
		}
	});
	
	if(flag) {
		$(".allCheck").prop("checked",true);
	} else {
		$(".allCheck").prop("checked",false);
	}
	
}
