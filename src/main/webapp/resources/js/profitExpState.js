optionalBox = null, selectedBox = null;
$(document).ready(function() {
	// 處理麵包屑及本頁表頭
	$("#breadcrumb").html('<li><a href="javascript:;" onclick="return home_action(this);"><i class="fa fa-home"></i> Home</a></li>'+
			'<li class="active">'+$("#breadCrumbLabel").val()+'</li>');
	$("#page-heading").find('h1').html($("#pageLabel").val());
	
	msgInit();
	
	
	detailGrid = $("#detailGrid");
	detailGrid.kendoGrid({
        dataSource: {
        	transport: {
        		read: {
	        		type: "POST",
	                url: "api/getProfitExpDataState",
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
        
        dataBound: function(){
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
        	{
        		field: "of47",
        		title: "核發狀態",
        		template: "#if(data.of47==2){#<center><div class='mCheck'>已核發</div><div>#=data.of48#</div></center>#}#",
        		width: "120px",
        		filterable: false
        	},
        	{ 
        		field: "of00", 
        		title: "訂單編號",
        		width: "180px",
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
        		width: "130px",
        		filterable: false
        	},
        	{ 
        		field: "sf00", 
        		title: "商品名稱",
        		width: "360px",
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
        		width: "150px",
        		filterable: false
        	},
        	{ 
        		field: "sf06", 
        		title: "商品單價",
        		width: "120px",
        		filterable: false
        	},
        	{ 
        		field: "of40", 
        		title: "訂購數量",
        		width: "120px",
        		filterable: false
        	},
        	{ 
        		field: "of02", 
        		title: "訂購總價",
        		width: "120px",
        		filterable: false
        	},
        	{ 
        		field: "of46", 
        		title: "分潤金額",
        		width: "100px",
        		filterable: false
        	},
        	{ 
        		field: "of04", 
        		title: "付款方式",
        		width: "120px",
        		template: "#if(data.of04 == '1'){#信用卡#}else if(data.of04 == '2'){#WebATM#}else if(data.of04 == '3'){#虛擬帳號#}#",
        		filterable: false
        	},
        	{ 
        		field: "of11", 
        		title: "交易狀態",  
        		width: "120px",
        		template: "#if(data.of04 == '1'){ if(data.of11 == 'S'){#交易成功#}else if(data.of11 == 'F'){#交易失敗#}else{#尚未付款#}} else { if(data.of11=='S'){#交易成功#} else {if(data.of20==undefined){ #尚未付款#} else if( data.of20 == '0' ){#取消訂單#}} }#",
	  			filterable: false
        	},
        	{ 
        		field: "of13", 
        		title: "原因",
        		width: "160px",
        		filterable: false
        	}
        ]
    });
	
	
	detailGridData = $(detailGrid).data("kendoGrid");
	
	
});
