optionalBox = null, selectedBox = null, profitExp = null;
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
	                url: "api/getUserDatasChecked",
	                dataType: "json"
        		}
            },
            schema: {
            	model: {
                    fields: {
                    	userLocalName: { type: "string"},
                        userMail: { type: "string"},
                        userName: { type: "string"},
                        userBirthday: { type: "string" },
                        userAddress: { type: "string" },
                        userDateCreated: { type: "string" },
                        userLastDateModify: { type: "string" },
                        koltotalProfitExp: { type: "string" }
                    }
                }
            },
            pageSize: 10
        },
        toolbar: kendo.template($("#gridTemplate").html()),
        dataBound: function(e){
        	var totalPrice = 0;
        	$.map(grid.dataSource.data(),function(data){
        		totalPrice += data.totalProfitExp;
        	});
        	$(".totalProfitExp").text(totalPrice);
        	
        },
        detailTemplate: "<div id='detailGrid'></div>",
        detailInit: function(e){
        	var row = e.detailRow.prev(),
            grid = $("#grid").data("kendoGrid"),
            dataItem = grid.dataItem(row);
        	var dataString = JSON.stringify(dataItem);
        	var data2 = JSON.parse(dataString);
        	data2.id = dataItem.id;
        	
        	$.ajax({
                type: 'POST',
                contentType : "application/json",
                url: "api/sendKolData",
                data: JSON.stringify(data2), 
                dataType: 'json',
                timeout : 100000,
                success: function(data, result) {
                	//Generator Detail Grid
                	detailGrid = e.detailRow.find("#detailGrid");
                	detailGridData = $(detailGrid).data("kendoGrid");
                	detailGrid.kendoGrid({
                        dataSource: {
                        	transport: {
                        		read: {
                	        		type: "POST",
                	                url: "api/getProfitExpData",
                	                dataType: "json"
                        		}
                            },
                            schema: {
                            	model: {
                                    fields: {
                                    	of47: { type: "string"},
                                    	of00: { type: "string"},
                                    	of01: { type: "string"},
                                    	sf00: { type: "string"},
                                    	of03: { type: "string"},
                                    	sf06: { type: "string"},
                                    	of40: { type: "string"},
                                    	of02: { type: "string"},
                                    	of04: { type: "string"},
                                    	of11: { type: "string"},
                                    	of13: { type: "string"},
                                    	sf95: { type: "string"}
                                    }
                                }
                            },
                            pageSize: 10
                        },
                        toolbar: kendo.template($("#template").html()),
                        
                        dataBound: function(){
                        	detailGridData = detailGrid.data("kendoGrid"),
                        	trs = detailGrid.find("tbody").find("tr"),
                        	flag = true;
                        	$.map($(trs),function(tr){
                        		dataItem = detailGridData.dataItem(tr);
                        		if(dataItem.of47 != 1 && dataItem.of47 != 2) {
                        			flag = false;
                        		}
                        	});
                        	
                        	if(flag && trs.length > 0) {
                        		detailGrid.find(".allCheck").prop("checked",true);
                        	} else {
                        		detailGrid.find(".allCheck").prop("checked",false);
                        	}
                            $.map(this.tbody.find("tr"),function(tr){
                            	detailGridData = detailGrid.data("kendoGrid"),
                            	dataItem = detailGridData.dataItem(tr);
                            	if(dataItem.of47 == 1){
                            		$(tr)
                                    .addClass("k-state-selected")
                                    .find(".checkbox")
                                    .prop("checked",true);
                            	}
                            	
                            	if(dataItem.of47 == 2) {
                            		$(tr).addClass("alreadyCheck");
                            	}
                            });
                        },
                        scrollable: true,
                        sortable: true,
                        filterable: {
                        	mode: "row"
                        },
                        editable: false,
                        pageable: {
                            input: true,
                            numeric: true
                        },
                        columns: [
                        	{ 	field: "of47", 
                        		title: "<center> <input type='checkbox' class='allCheck' /> </center>", 
                        		template: "#if(data.of47 != 2 && data.of11 == 'S'){#<center><input type='checkbox' class='checkbox' value='#: data.id #' #if(data.of47==1){#checked#}# /> </center>#}#",
                        		width: "5%",
                        		filterable: false,
                        		sortable: false
                        	},
                        	{
                        		field: "of47",
                        		title: "核發狀態",
                        		width: "10%",
                        		template: "#if(data.of47==2){#<center><div class='mCheck'>已核發</div><div>#=data.of48#</div></center>#}#",
                        		filterable: false
                        	},
                        	{ 
                        		field: "of00", 
                        		title: "訂單編號",
                        		width: "12%",
                	  			filterable: {
                                    cell: {
                                        operator: "contains",
                                        suggestionOperator: "contains"
                                    }
                                }
                        	},
                        	{ 
                        		field: "of01", 
                        		title: "訂單日期",
                        		width: "10%",
                        		filterable: false
                        	},
                        	{ 
                        		field: "sf00", 
                        		title: "商品名稱",
                        		width: "15%",
                	  			filterable: {
                                    cell: {
                                        operator: "contains",
                                        suggestionOperator: "contains"
                                    }
                                }
                        	},
                        	{ 
                        		field: "of03", 
                        		title: "商品規格",
                        		width: "10%",
                        		filterable: false
                        	},
                        	{ 
                        		field: "sf06", 
                        		title: "單價",
                        		width: "6%",
                        		filterable: false
                        	},
                        	{ 
                        		field: "of40", 
                        		title: "數量",
                        		width: "6%",
                        		filterable: false
                        	},
                        	{ 
                        		field: "of02", 
                        		title: "總價",
                        		width: "6%",
                        		filterable: false
                        	},
                        	{ 
                        		field: "of46", 
                        		title: "分潤",
                        		width: "6%",
                        		filterable: false
                        	},
                        	{ 
                        		field: "of04", 
                        		title: "付款方式",
                        		template: "#if(data.of04 == '1'){#信用卡#}else if(data.of04 == '2'){#WebATM#}else if(data.of04 == '3'){#虛擬帳號#}#",
                        		filterable: false
                        	},
                        	{ 
                        		field: "of11", 
                        		title: "交易狀態",  
                        		template: "#if(data.of04 == '1'){ if(data.of11 == 'S'){#交易成功#}else if(data.of11 == 'F'){#交易失敗#}else{#尚未付款#}} else { if(data.of11=='S'){#交易成功#} else {if(data.of20==undefined){ #尚未付款#} else if( data.of20 == '0' ){#取消訂單#}} }#",
                	  			filterable: false
                        	},
                        	{ 
                        		field: "of13", 
                        		title: "原因",
                        		filterable: false
                        	}
                        ]
                    });
                	
                	detailGridData = detailGrid.data("kendoGrid");
                	detailGridData.table.on("click","tr",function(e){
                		var row = $(this).closest("tr"),
                		detailGridData = detailGrid.data("kendoGrid"),
                	    dataItem = detailGridData.dataItem(row);
                		console.log(dataItem.of47)
                		if (dataItem.of47 != 2 && dataItem.of11 == 'S') {
        	        		if (dataItem.of47 == 1) {
        	        	        //-select the row
        	        	        row.removeClass("k-state-selected")
        	        	        .find(".checkbox")
        	        	        .prop("checked",false);
        	        	        dataItem.of47 = 0;
        	        	    } else {
        	        	        //-remove selection
        	        	        row.addClass("k-state-selected")
        	        	        .find(".checkbox")
        	        	        .prop("checked",true);
        	        	        dataItem.of47 = 1;
        	        	    }
                		}
                		
                		detailGridData = detailGrid.data("kendoGrid"),
                    	trs = detailGrid.find("tbody").find("tr"),
                    	flag = true;
                		ofcount = 0;
                    	$.map($(trs),function(tr){
                    		dataItem = detailGridData.dataItem(tr);
                    		if(dataItem.of47 != 1 && dataItem.of47 != 2) {
                    			flag = false;
                    		}else if( dataItem.of47 == 1){
                    			ofcount ++;
                    		}
                    	});
                    	
                    	if(ofcount>0){
                    		$('.submitButton').show();
                    	} else {
                    		$('.submitButton').hide();
                    	}
                    	
                    	if(flag) {
                    		$(".allCheck").prop("checked",true);
                    	} else {
                    		$(".allCheck").prop("checked",false);
                    	}
                    	
                    	sumAccount();
                	});
                	
                	$('.allCheck').click(function(){
                		var detailGridData = detailGrid.data("kendoGrid"),
                		trs = detailGrid.find("tbody").find("tr");
                		
                		if(this.checked){
                			$.map($(trs),function(tr){
                				dataItem = detailGridData.dataItem(tr);
                				if(dataItem.of47 != 2 && dataItem.of11 == 'S'){
                					$(tr)
        	        	            .addClass("k-state-selected")
        	        	            .find(".checkbox")
        	        	            .prop("checked",true);
        	        	    		dataItem.of47 = 1;
                				}
                		    });
                			$('.submitButton').show();
                		} else {
                			$.map($(trs),function(tr){
                				dataItem = detailGridData.dataItem(tr);
                				if(dataItem.of47 != 2 && dataItem.of11 == 'S'){
        	        	    		$(tr)
        	        	            .removeClass("k-state-selected")
        	        	            .find(".checkbox")
        	        	            .prop("checked",false);
        	        	    		dataItem.of47 = 0;
                				}
                		    });
                			$('.submitButton').hide();
                		}
                		
                		sumAccount();
                	});
                	
                	$(".k-detail-cell").hover(function(e){
                		detailGrid = $(this).find("#detailGrid");
                    	detailGridData = $(detailGrid).data("kendoGrid");
                	});
                	
                	
                	$(".submitButton").click(function(e){
                		var jsonDataArray = new Array();
                		
                		$.map(detailGridData.dataSource.data(),function(data){
                			if(data.of47 == 1) {
                				var dataString = JSON.stringify(data);
                				var data2 = JSON.parse(dataString);
                				data2.id = data.id;
                				jsonDataArray.push(data2);
                			}
                		});
                		
                		
                		$.ajax({
                            type: 'POST',
                            contentType : "application/json",
                            url: "api/profitExpSubmit",
                            data: JSON.stringify(jsonDataArray), 
                            dataType: 'json',
                            timeout : 100000,
                            success: function(data, result) {
                            	showMsg(data.message);
                            	$(detailGrid).data("kendoGrid").dataSource.read();
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
        },
        scrollable: true,
        sortable: true,
        filterable: {
        	mode: "row"
        },
        editable: false,
        pageable: {
            input: true,
            numeric: true
        },
        columns: [
        	{ 
        		field: "userLocalName", 
        		title: "姓名",  
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
        		filterable: {
        			cell: {
        				operator: "contains",
        				suggestionOperator: "contains"
        			}
        		}
        	},
        	{
        		field: "koltotalProfitExp",
        		title: "估算總額"
        	},
//        	{ 
//        		field: "userBirthday", 
//        		title: "生日",  
//        		width: "150px",
//        		filterable: false
//        	},
//        	{ 
//        		field: "userAddress", 
//        		title: "地址",
//        		template: "#: zipCode # #: userCity ##: userArea ##: userAddress #",
//        		width: "300px",
//        		filterable: {
//        			cell: {
//        				operator: "contains",
//        				suggestionOperator: "contains"
//        			}
//        		}
//        	},
        	{ 
        		field: "userDateCreated", 
        		title: "註冊時間",  
        		filterable: false
        	}
        ]
    });
	
	grid = $("#grid").data("kendoGrid");
	grid.table.on("click", "tr" , onChange);
	
});


function sumAccount(){
	var profitExp = 0;
	$.map(detailGridData.dataSource.data(),function(data){
		if(data.of47 == 1){
			profitExp += parseInt(data.of46);
		}
	});
	detailGrid.find(".profitExp").text(profitExp);
}


function onChange(e){
	var $target = $(e.target);
	var $this = $(this);
	var $link = $this.find("td.k-hierarchy-cell .k-icon");
	$link.click();  // just fire the click event of the kendo detail row icon
}

