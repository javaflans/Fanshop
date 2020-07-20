optionalBox = null, selectedBox = null;
$(document).ready(function() {
	// 處理麵包屑及本頁表頭
	$("#breadcrumb").html('<li><a href="javascript:;" onclick="return home_action(this);"><i class="fa fa-home"></i> Home</a></li>'+
			'<li class="active">'+$("#breadCrumbLabel").val()+'</li>');
	$("#page-heading").find('h1').html($("#pageLabel").val());
	
	msgInit();
	
	
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
                    	userLocalName: { type: "string", editable: false },
                    	userName: { type: "string", editable: false },
                    	level: { type: "string" },
                        userMail: { type: "string", editable: false },
                        userBirthday: { type: "string", editable: false },
                        shareTarget: { type: "string", editable: false },
                        hotTarget: { type: "string", editable: false },
                        transTarget: { type: "string", editable: false },
                        businessTarget: { type: "string", editable: false }
                    }
                }
            },
            pageSize: 55
        },
        
        scrollable: true,
        sortable: true,
        editable: true,
        filterable: {
        	mode: "row"
        },
        pageable: {
            input: true,
            numeric: true
        },
        columns: [
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
        		field: "level", 
        		title: "達人等級",  
        		width: "200px",
        		editor: levelDropDownEditor,
        		template: "#=data.level#",
        		filterable: false
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
        		field: "shareTarget", 
        		title: "分享次數",
        		width: "130px",
        		filterable: false
        	},
        	{ 
        		field: "hotTarget", 
        		title: "人氣指數",
        		width: "130px",
        		filterable: false
        	},
        	{ 
        		field: "transTarget", 
        		title: "成交件數",
        		width: "130px",
        		filterable: false
        	},
        	{ 
        		field: "businessTarget", 
        		title: "合作次數",
        		width: "130px",
        		filterable: false
        	}        	
        ]
    });
    
	
	grid = $("#grid").data("kendoGrid");
	
	
	
});


function levelDropDownEditor(container, options) {
    $('<input required name="' + options.field + '"/>')
	    .appendTo(container)
	    .kendoDropDownList({
	        autoBind: false,
	        dataTextField: "level",
	        dataValueField: "level",
	        change: function(e){
	        	grid = $("#grid").data("kendoGrid");
	            var trDataItem = grid.dataItem(this.element.closest("tr"));
	            
	            $.ajax({
	                type: 'POST',
	                contentType : "application/json",
	                url: "api/setKolDataLevel",
	                data: JSON.stringify(trDataItem), 
	                dataType: 'json',
	                timeout : 100000,
	                success: function(data, result) {
	                	if(data.code == 200){
	                		showMsg(data.message);
	                		$("#grid").data("kendoGrid").dataSource.read();
	                	}
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
	        dataSource: {
	            transport: {
	                read: {
	                	type: "POST",
	                	url: "api/getLevelList",
	                	dataType: "json"
	                }
	            }
	        }
	    });
}

