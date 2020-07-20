tooltipConf = {
	filter : "tr", // this filter selects the second column's cells
	position : "top",
	autoHide : true,
	show : function() {
		var thisHeight = $(this.popup.wrapper).height() + 5;
		var thisWidth = $(this.popup.wrapper).width() / 2;
		$(this.popup.wrapper).css({
			top : lastMouseY - thisHeight,
			left : lastMouseX - thisWidth
		});
	},
	width : "auto",
	height : "auto",
	content : function(e) {
		var data = $("#grid").data("kendoGrid")
				.dataItem(e.target.closest("tr"));
		var dataInfo = '<table class="gridTooltip">';
		dataInfo += "<tr><td nowrap='nowrap'>Contact Name</td><td nowrap='nowrap'>"
				+ data.contactName + "</td></tr>";
		dataInfo += "<tr><td nowrap='nowrap'>Contact Title</td><td nowrap='nowrap'>"
				+ data.contactTitle + "</td></tr>";
		dataInfo += "<tr><td nowrap='nowrap'>Company Name</td><td nowrap='nowrap'>"
				+ data.companyName + "</td></tr>";
		dataInfo += "<tr><td nowrap='nowrap'>Country</td><td nowrap='nowrap'>"
				+ data.country + "</td></tr>";
		dataInfo += "</table>";
		var content = dataInfo;
		return content;
	}
}

messageConf = {
	height : "auto",
	width : "auto",
	autoHideAfter : 5000,
	animation : {
		open : {
			effects : "slideIn:left"
		},
		close : {
			effects : "slideIn:left",
			reverse : true
		}
	},
	position : {
		pinned : false,
		top : 0,
		left : null,
		bottom : null,
		right : 0
	}
}


getSingleChartConf = function(filePath) {
	var source = $.getJSON(filePath);
	
}

singleChartConf = {   	
	chartArea: {
		background:"transparent",// chartArea CSS 的這段設定是能疊上背景圖的關鍵!!
		height: "480.0", // 須調整直到放置圖檔的path width x heigh
							// 與群組的那張圖的該區塊一致,這樣座標與比例才可互通
		width:  "800.0", 
		opacity: "0.2",	
	},
	title: {
        text: "即時定位- 單組"
    },
    dataSource: {
        transport: {
            read: {
                url: "resources/data/performance.json",
                dataType: "json"
            }
        },
        sort: {
            field: "year",
            dir: "asc"
        }
    },
    legend: {
        visible: false
    },
    xAxis: {
    	visible: true,
        max: 900,
        labels: {
        	visible: true,
            format: "{0}"
        },
        title: {
        	visible: false,
            text: "Price"
        }
    },
    yAxis: {
    	max: 600,
    	visible: true,
        labels: {
        	visible: true,
            format: "{0}"
        },
        title: {
        	visible: false,
            text: "Performance Ratio"
        }
    },
    
    seriesDefaults: {
        type: "scatter"
    },
    series: [{       	            
        markers:{
        		type: function (e) {
        			   // alert(e.value.x +" : "+ e.dataItem.family);
        			   if(e.dataItem.family == "Pentium"){
        				   // alert(111);
        				   return "triangle";
        			   }
        			   if(e.dataItem.family == "Core 2 Duo"){
        				  // alert(222);
        				   return "square";
        			   }
        			   // alert(333);
        			   return "circle";           		
            	},
        		size: 8    
        	},
        xField: "price",
        yField: "performance",
        color: function (e) {
 			   // alert(e.value.x +" : "+ e.dataItem.family);
 			   if(e.dataItem.family == "Pentium"){	     				  
 				   return "red";
 			   }
 			   if(e.dataItem.family == "Core 2 Duo"){	     				   
 				   return "blue";
 			   }
 			   return "green";           		
        },
    }],       
    tooltip: {
        visible: true,
        template: "#= '<b>(' + value.x + ' , ' + value.y + ')</b>' #"
    },
}

multipleChartConf = {

}

//Clock = kendo.Class.extend({
//    init: function(container, options) {
//        this.options = $.extend({}, this.options, options);
//        this._center = Point.create(this.options.center);
//        this._render(container);
//        this.set(new Date());
//    },
//
//    options: {
//        center: [0, 0],
//        size: 100,
//        offset: 0,
//    },
//
//    set: function(date) {
//        var center = this._center;
//
//        var seconds = date.getMilliseconds() / 1000;
//        seconds += date.getSeconds();
//        seconds += date.getMinutes() * 60;
//        seconds += (this.options.offset + date.getHours()) * 60 * 60;
//
//        var angle = (360 / 60) * seconds;
//        this._seconds.transform(geom.transform().rotate(angle, center));
//
//        angle = (360 / (60 * 60)) * seconds;
//        this._minutes.transform(geom.transform().rotate(angle, center));
//
//        angle = (360 / (12 * 60 * 60)) * seconds;
//        this._hours.transform(geom.transform().rotate(angle, center));
//    },
//
//    _render: function(container) {
//        // Stacking order matches element order
//        var root = new Group();
//        root.append(
//            this._renderFrame(),
//            this._renderTicks(),
//            this._renderHands(),
//            this._renderLabels(),
//            this._renderTitle()
//        );
//
//        container.append(root);
//    },
//
//    _renderHands: function() {
//        var center = this._center;
//        this._seconds = new Path()
//            .stroke("red", 1)
//            .moveTo(center).lineTo(center.x, this._fromTop(0.05));
//
//        this._minutes = new Path({
//            stroke: {
//                color: "black",
//                width: 3,
//                lineCap: "round",
//                opacity: 0.6
//            }
//        }).moveTo(center).lineTo(center.x, this._fromTop(0.04));
//
//        this._hours = new Path({
//            stroke: {
//                color: "black",
//                width: 6,
//                lineCap: "round",
//                opacity: 0.6
//            }
//        }).moveTo(center).lineTo(center.x, this._fromTop(0.25));
//
//        var hands = new Group();
//        hands.append(this._seconds, this._minutes, this._hours);
//
//        return hands;
//    },
//
//    _renderTicks: function () {
//        var center = this._center;
//        var ticks = new Group();
//
//        for (var i = 0; i < 360; i += 30) {
//            var p1 = new Point(center.x, this._fromTop(0.03)).rotate(i, center);
//            var p2 = new Point(center.x, this._fromTop(0.055)).rotate(i, center);
//            ticks.append(new Path({
//                stroke: {
//                    color: "#333",
//                    width: 1
//                }
//            }).moveTo(p1).lineTo(p2));
//        }
//
//        return ticks;
//    },
//
//    _renderLabels: function() {
//        var center = this._center;
//        var labels = new Group();
//        var font = "bold " + this.options.size * 0.08 + "px arial, helvetica, sans-serif";
//
//        var hours = 12;
//        for (var i = 0; i < 360; i += 90) {
//            var pos = new Point(center.x, this._fromTop(0.12)).rotate(i, center);
//            var text = new Text(hours, pos, {
//                font: font,
//                fill: {
//                    color: "#333"
//                }
//            });
//
//            this._centerText(text);
//
//            hours = (hours + 3) % 12;
//            labels.append(text);
//        }
//
//        return labels;
//    },
//
//    _renderFrame: function() {
//        var frame = new Group();
//        var size = this.options.size;
//
//        var outerCircle = new geom.Circle(this._center, (size / 2) - 4);
//        var innerCircle = new geom.Circle(this._center, size * 0.01);
//
//        frame.append(
//            new Circle(outerCircle, {
//                stroke: {
//                    color: "#333",
//                    width: 1
//                }
//            }),
//            new Circle(innerCircle).fill("black")
//        );
//
//        return frame;
//    },
//
//    _renderTitle: function() {
//        var group = new Group();
//        var title = this.options.title;
//
//        if (title) {
//            var pos = [this._center.x, this._fromTop(-0.1)];
//            var text = new Text(title, pos, {
//                font: "bold 16px arial",
//                fill: {
//                    color: "#333"
//                }
//            });
//
//            this._centerText(text);
//            group.append(text);
//        }
//
//        return group;
//    },
//
//    _centerText: function(text) {
//        var bbox = text.bbox();
//        text.position().translate(-bbox.width() / 2, -bbox.height() / 2);
//    },
//
//    _fromTop: function(ratio) {
//        var size = this.options.size;
//        var top = this._center.y - size / 2;
//        return top + size * ratio;
//    }
//});