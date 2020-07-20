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
	                url: "api/getProdShareConfigData",
	                dataType: "json"
        		}
            },
            schema: {
                model: {
                    fields: {
                    	f98: { type: "string", editable: false },
                        f00: { type: "string", editable: false },
                        f03: { type: "string", editable: false },
                        f06: { type: "number" },
                        f95: { type: "number" },
                        f96: { type: "number" },
                        f97: { type: "number" }
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
        editable: true,
        edit: function(e) {
        	var model = e.model; //reference to the model that is about the be edited

            var container = e.container; //reference to the editor container
            
//            var categoryDropDownList = container.find("[data-role=dropdownlist]").data("kendoDropDownList"); //find widget element and then get the widget instance
//            if (categoryDropDownList) {
//            	alert(categoryDropDownList.value());
//            }
            var priceNumericTextBox = container.find("[data-role=numerictextbox]").data("kendoNumericTextBox"); //find widget element and then the widget instance
//            alert(this.columns[this.cellIndex(e.container)].field);
            if (priceNumericTextBox) {
            	row = container.closest("tr"),
                grid = $("#grid").data("kendoGrid"),
                dataItem = grid.dataItem(row);
            	if(dataItem.f98 == 0){
            		priceNumericTextBox.enable(false);
            	} else {
            		priceNumericTextBox.enable(true);
            	}
            }
            var editFieldName = this.columns[this.cellIndex(e.container)].field;
            container.find("[data-role=numerictextbox]").blur(function(){
            	if( editFieldName == "f06" ){
            		row = container.closest("tr"),
            		grid = $("#grid").data("kendoGrid"),
            		dataItem = grid.dataItem(row);
            		dataItem.set("f97",Math.round(dataItem.f06*0.20));
            		dataItem.set("f96",Math.round(dataItem.f06*0.15));
            		dataItem.set("f95",Math.round(dataItem.f06*0.10));
            	}
            });
        },
        pageable: {
            input: true,
            numeric: true
        },
        columns: [
        	{ field: "f98", 
        		title: "<input type='checkbox' class='allCheck' />", 
        		template: "<input type='checkbox' class='checkbox' value='#: data.id #' #if(data.f98==1){#checked#}# >",
        		width: "40px", 
        		filterable: false,
        		sortable: false
        	},
        	{ 
        		field: "f00", 
        		title: "商品名稱",  
        		width: "300px",
        		template: "<div class='customer-photo' " +
    		  			  "style='background-image: url(http://www.fanshopping.com.tw/uploads/logo/product/#: data.f10 #_320x240);'></div>" +
        				  "<div class='productName'>#: data.f00 #</div>",
	  			filterable: {
                    cell: {
                        operator: "contains",
                        suggestionOperator: "contains"
                    }
                }
        	},
        	{ 
        		field: "f03", 
        		title: "簡介",  
        		width: "300px",
        		filterable: {
                    cell: {
                        operator: "contains",
                        suggestionOperator: "contains"
                    }
                }
        	},
            { field: "f06", title: "售價", format: "{0:c}", width: "80px", filterable: false },
            { field: "f95", title: "Lv1 分潤價格", width: "110px", filterable: false },
            { field: "f96", title: "Lv2 分潤價格", width: "110px", filterable: false },
            { field: "f97", title: "Lv3 分潤價格", width: "110px", filterable: false },
        ]
    });
	
	grid = $("#grid").data("kendoGrid");
	grid.table.on("click", "tr td:nth-child(1),tr td:nth-child(2),tr td:nth-child(3)" , onChange);
	
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
	    		dataItem.f98 = 1;
		    });
			$.map(grid.dataSource.view(),function(dataItem){
				dataItem.set("f98",1);
				dataItem.set("f97",Math.round(dataItem.f06*0.20));
        		dataItem.set("f96",Math.round(dataItem.f06*0.15));
        		dataItem.set("f95",Math.round(dataItem.f06*0.10));
			});
		} else {
			$.map($(trs),function(tr){
				dataItem = grid.dataItem(tr);
	    		$(tr)
	            .removeClass("k-state-selected")
	            .find(".checkbox")
	            .prop("checked",false);
	    		dataItem.f98 = 0;
		    });
			$.map(grid.dataSource.view(),function(dataItem){
				dataItem.set("f98",0);
				dataItem.set("f97",0);
				dataItem.set("f96",0);
				dataItem.set("f95",0);
			});
		}
		sumAccount();
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
            url: "api/prodShareConfigSubmit",
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



//on dataBound event restore previous selected rows:
function onDataBound(e) {
	var grid = $("#grid").data("kendoGrid"),
	trs = $("#grid").find("tbody").find("tr"),
	flag = true;
	$.map($(trs),function(tr){
		dataItem = grid.dataItem(tr);
		if(dataItem.f98 != 1) {
			flag = false;
		}
	});
	
	if(flag) {
		$(".allCheck").prop("checked",true);
	} else {
		$(".allCheck").prop("checked",false);
	}
	sumAccount();
	$(".prodTitle").fadeIn(500);
    $.map(this.tbody.find("tr"),function(tr){
    	grid = $("#grid").data("kendoGrid"),
    	dataItem = grid.dataItem(tr);
    	if(dataItem.f98 == 1){
    		$(tr)
            .addClass("k-state-selected")
            .find(".checkbox")
            .prop("checked",true);
    	}
    });
}

function sumAccount(){
	$(".totalAccount").text(grid.dataSource.data().length);
	var chooseAccount = 0;
	$.map(grid.dataSource.data(),function(data){
		if(data.f98 == 1){
			chooseAccount += 1;
		}
	});
	$(".chooseAccount").text(chooseAccount);
}

function onChange(e){
	var row = $(this).closest("tr"),
    grid = $("#grid").data("kendoGrid"),
    dataItem = grid.dataItem(row);
	
	if (dataItem.f98 == 1) {
        //-select the row
        row.removeClass("k-state-selected")
        .find(".checkbox")
        .prop("checked",false);
        dataItem.f98 = 0;
        dataItem.set("f98",0);
        dataItem.set("f97",0);
        dataItem.set("f96",0);
        dataItem.set("f95",0);
    } else {
        //-remove selection
        row.addClass("k-state-selected")
        .find(".checkbox")
        .prop("checked",true);
        dataItem.f98 = 1;
        dataItem.set("f98",1);
        dataItem.set("f97",Math.round(dataItem.f06*0.20));
		dataItem.set("f96",Math.round(dataItem.f06*0.15));
		dataItem.set("f95",Math.round(dataItem.f06*0.10));
    }
	sumAccount();
	
//	// Get selected data
//	var selectedItems = grid.select();
//	for (var i = 0; i < selectedItems.length; i++) {
//		var selectedItem = grid.dataItem(selectedItems[i]);
//		alert(selectedItem.f00);
//	}
	
//	// Get all data
//	$.map(grid.dataSource.data(),function(data){
//		alert(data.f00);
//	});
}


