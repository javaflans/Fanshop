jQuery(document).ready(function() {

    $(".demodrop").pulsate({
        color: "#2bbce0",
        repeat: 10
    });

    $("#threads,#comments,#users").niceScroll({horizrailenabled:false,railoffset: {left:0}});

    try {
    //Easy Pie Chart
    $('.easypiechart#returningvisits').easyPieChart({
        barColor: "#85c744",
        trackColor: '#edeef0',
        scaleColor: '#d2d3d6',
        scaleLength: 5,
        lineCap: 'square',
        lineWidth: 2,
        size: 90,
        onStep: function(from, to, percent) {
            $(this.el).find('.percent').text(Math.round(percent));
        }
    });

    $('.easypiechart#newvisitor').easyPieChart({
        barColor: "#f39c12",
        trackColor: '#edeef0',
        scaleColor: '#d2d3d6',
        scaleLength: 5,
        lineCap: 'square',
        lineWidth: 2,
        size: 90,
        onStep: function(from, to, percent) {
            $(this.el).find('.percent').text(Math.round(percent));
        }
    });

    $('.easypiechart#clickrate').easyPieChart({
        barColor: "#e73c3c",
        trackColor: '#edeef0',
        scaleColor: '#d2d3d6',
        scaleLength: 5,
        lineCap: 'square',
        lineWidth: 2,
        size: 90,
        onStep: function(from, to, percent) {
            $(this.el).find('.percent').text(Math.round(percent));
        }
    });
	$('.easypiechart#returningvisits1').easyPieChart({
        barColor: "#85c744",
        trackColor: '#edeef0',
        scaleColor: '#d2d3d6',
        scaleLength: 5,
        lineCap: 'square',
        lineWidth: 2,
        size: 90,
        onStep: function(from, to, percent) {
            $(this.el).find('.percent').text(Math.round(percent));
        }
    });

    $('.easypiechart#newvisitor1').easyPieChart({
        barColor: "#f39c12",
        trackColor: '#edeef0',
        scaleColor: '#d2d3d6',
        scaleLength: 5,
        lineCap: 'square',
        lineWidth: 2,
        size: 90,
        onStep: function(from, to, percent) {
            $(this.el).find('.percent').text(Math.round(percent));
        }
    });
	$('.easypiechart#clickrate1').easyPieChart({
        barColor: "#e73c3c",
        trackColor: '#edeef0',
        scaleColor: '#d2d3d6',
        scaleLength: 5,
        lineCap: 'square',
        lineWidth: 2,
        size: 90,
        onStep: function(from, to, percent) {
            $(this.el).find('.percent').text(Math.round(percent));
        }
    });

    $('#updatePieCharts').on('click', function() {
        $('.easypiechart#returningvisits1').data('easyPieChart').update(Math.random()*100);
        $('.easypiechart#newvisitor1').data('easyPieChart').update(Math.random()*100);
        $('.easypiechart#clickrate1').data('easyPieChart').update(Math.random()*100);
        return false;
    });
    }
    catch(e) {}


    //Date Range Picker
    $('#daterangepicker2').daterangepicker(
        {
          ranges: {
             'Today': [moment(), moment()],
             'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
             'Last 7 Days': [moment().subtract('days', 6), moment()],
             'Last 30 Days': [moment().subtract('days', 29), moment()],
             'This Month': [moment().startOf('month'), moment().endOf('month')],
             'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
          },
          opens: 'left',
          startDate: moment().subtract('days', 29),
          endDate: moment()
        },
        function(start, end) {
            $('#daterangepicker2 span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        }
    );


    //Sparklines

    $("#indexinfocomments").sparkline([12 + randValue(),8 + randValue(),10 + randValue(), 21 + randValue(), 16 + randValue(), 9 + randValue(), 15 + randValue(), 8 + randValue() ,10 + randValue(),19 + randValue()], {
    type: 'bar',
    barColor: '#f1948a',
    height: '45',
    barWidth: 7});

    $("#indexinfolikes").sparkline([120 + randValue(),87 + randValue(),108 + randValue(), 121 + randValue(), 85 + randValue(), 95 + randValue(), 185 + randValue(), 125 + randValue() ,154 + randValue(),109 + randValue()], {
    type: 'bar',
    barColor: '#f5c783',
    height: '45',
    barWidth: 7});



    $("#indexvisits").sparkline([7914 + randValue(),2795 + randValue(),3256 + randValue(), 3018 + randValue(), 2832 + randValue() ,5261 + randValue(),6573 + randValue()], {
    lineWidth: 1.5,
    type: 'line',
    width: '90px',
    height: '45px',
    lineColor: '#556b8d',
    fillColor: 'rgba(85,107,141,0.1)',
    spotColor: false,
    minSpotColor: false,
    highlightLineColor: '#d2d3d6',
    highlightSpotColor: '#556b8d',
    spotRadius: 3,
    maxSpotColor: false});

    $("#indexpageviews").sparkline([8263 + randValue(),6314 + randValue(),10467 + randValue(), 12123 + randValue(), 11125 + randValue() ,13414 + randValue(),15519 + randValue()], {
    lineWidth: 1.5,
    type: 'line',
    width: '90px',
    height: '45px',
    lineColor: '#4f8edc',
    fillColor: 'rgba(79,142,220,0.1)',
    spotColor: false,
    minSpotColor: false,
    highlightLineColor: '#d2d3d6',
    highlightSpotColor: '#4f8edc',
    spotRadius: 3,
    maxSpotColor: false});

    $("#indexpagesvisit").sparkline([7.41 + randValue(),6.12 + randValue(),6.8 + randValue(), 5.21 + randValue(), 6.15 + randValue() ,7.14 + randValue(),6.19 + randValue()], {
    lineWidth: 1.5,
    type: 'line',
    width: '90px',
    height: '45px',
    lineColor: '#a6b0c2',
    fillColor: 'rgba(166,176,194,0.1)',
    spotColor: false,
    minSpotColor: false,
    highlightLineColor: '#d2d3d6',
    highlightSpotColor: '#a6b0c2',
    spotRadius: 3,
    maxSpotColor: false});

    $("#indexavgvisit").sparkline([5.31 + randValue(),2.18 + randValue(),1.06 + randValue(), 3.42 + randValue(), 2.51 + randValue() ,1.45 + randValue(),4.01 + randValue()], {
    lineWidth: 1.5,
    type: 'line',
    width: '90px',
    height: '45px',
    lineColor: '#85c744',
    fillColor: 'rgba(133,199,68,0.1)',
    spotColor: false,
    minSpotColor: false,
    highlightLineColor: '#d2d3d6',
    highlightSpotColor: '#85c744',
    spotRadius: 3,
    maxSpotColor: false});

    $("#indexnewvisits").sparkline([70.14 + randValue(),72.95 + randValue(),77.56 + randValue(), 78.18 + randValue(), 76.32 + randValue() ,73.61 + randValue(),74.73 + randValue()], {
    lineWidth: 1.5,
    type: 'line',
    width: '90px',
    height: '45px',
    lineColor: '#efa131',
    fillColor: 'rgba(239,161,49,0.1)',
    spotColor: false,
    minSpotColor: false,
    highlightLineColor: '#d2d3d6',
    highlightSpotColor: '#efa131',
    spotRadius: 3,
    maxSpotColor: false});

    $("#indexbouncerate").sparkline([29.14 + randValue(),27.95 + randValue(),32.56 + randValue(), 30.18 + randValue(), 28.32 + randValue() ,32.61 + randValue(),35.73 + randValue()], {
    lineWidth: 1.5,
    type: 'line',
    width: '90px',
    height: '45px',
    lineColor: '#e74c3c',
    fillColor: 'rgba(231,76,60,0.1)',
    spotColor: false,
    minSpotColor: false,
    highlightLineColor: '#d2d3d6',
    highlightSpotColor: '#e74c3c',
    spotRadius: 3,
    maxSpotColor: false});



    //Flot

    function randValue() {
        return (Math.floor(Math.random() * (2)));
    }

    var viewcount = [
        [1, 787 + randValue()],
        [2, 740 + randValue()],
        [3, 560 + randValue()],
        [4, 860 + randValue()],
        [5, 750 + randValue()],
        [6, 910 + randValue()],
        [7, 730 + randValue()]
    ];

    var uniqueviews = [
        [1, 179 + randValue()],
        [2, 320 + randValue()],
        [3, 120 + randValue()],
        [4, 400 + randValue()],
        [5, 573 + randValue()],
        [6, 255 + randValue()],
        [7, 366 + randValue()]
    ];

    
    var usercount = [
        [1, 70 + randValue()],
        [2, 260 + randValue()],
        [3, 30 + randValue()],
        [4, 147 + randValue()],
        [5, 333 + randValue()],
        [6, 155 + randValue()],
        [7, 166 + randValue()]
    ];



    var d1 = [
        [1, 29 + randValue()],
        [2, 62 + randValue()],
        [3, 52 + randValue()],
        [4, 41 + randValue()]
    ];
    var d2 = [
        [1, 36 + randValue()],
        [2, 79 + randValue()],
        [3, 66 + randValue()],
        [4, 24 + randValue()]
    ];

    for (var i = 1; i < 5; i++) {
        d1.push([i, parseInt(Math.random() * 1)]);
        d2.push([i, parseInt(Math.random() * 1)]);
    }
 
    var ds = new Array();

    ds.push({
    data:d1,
    label: "Budget",
    bars: {
        show: true,
        barWidth: 0.2,
        order: 1
    }
    });
    ds.push({
        data:d2,
        label: "Actual",
        bars: {
            show: true,
            barWidth: 0.2,
            order: 2,
        }
    });





    var previousPoint = null;
       

    var previousPointBar = null;
        



    function showTooltip(x, y, contents) {
        $('<div id="tooltip" class="tooltip top in"><div class="tooltip-inner">' + contents + '<\/div><\/div>').css({
            display: 'none',
            top: y - 40,
            left: x - 55,
        }).appendTo("body").fadeIn(200);
    }


    // paul mark 2015/07/21
    //var container = $("#server-load");

    // Determine how many data points to keep based on the placeholder's initial size;
    // this gives us a nice high-res plot while avoiding more than one point per pixel.

    //var maximum = container.outerWidth() / 2 || 300;
    var maximum = 300; // paul mdify    
    var data = [];

    function getRandomData() {

        if (data.length) {
            data = data.slice(1);
        }

        while (data.length < maximum) {
            var previous = data.length ? data[data.length - 1] : 50;
            var y = previous + Math.random() * 10 - 5;
            data.push(y < 0 ? 0 : y > 100 ? 100 : y);
        }

        // zip the generated y values with the x values
        var res = [];
        for (var i = 0; i < data.length; ++i) {
            res.push([i, data[i]]);
        }
        return res;
    }

    //

    series = [{
        data: getRandomData()
    }];

    //

    // paul mark 2015/07/21
//    var plot = $.plot(container, series, {
//        series: {
//            lines: {
//                show: true,
//                lineWidth: 1.5,
//                fill: 0.15
//            },
//            shadowSize: 0
//        },
//        grid: {
//            
//            labelMargin: 10,
//            tickColor: "#e6e7e8",
//            borderWidth: 0
//        },
//        colors: ["#f1c40f"],
//        xaxis: {
//            tickFormatter: function() {
//                return "";
//            },
//            tickColor: "transparent"
//        },
//        yaxis: {
//            ticks: 2,
//            min: 0,
//            max: 100,
//            tickFormatter: function (val, axis) {
//                return val + "%";
//            },
//            font: {
//                color: '#8c8c8c',
//                size: 12
//            }
//        },
//        legend: {
//            show: true
//        }
//    });

    // Update the random dataset at 25FPS for a smoothly-animating chart

    // paul mark 2015/07/21
//    setInterval(function updateRandom() {
//        series[0].data = getRandomData();
//        plot.setData(series);
//        plot.draw();
//    }, 40);


});


// Calendar
// If screensize > 1200, render with m/w/d view, if not by default render with just title

renderCalendar({left: 'title',right: 'prev,next'});

enquire.register("screen and (min-width: 1200px)", {
    match : function() {
        $('#calendar-drag').removeData('fullCalendar').empty();
        renderCalendar({left: 'prev,next',center: 'title',right: 'month,basicWeek,basicDay'});
    },
    unmatch : function() {
        $('#calendar-drag').removeData('fullCalendar').empty();
        renderCalendar({left: 'title',right: 'prev,next'});
    }
});

//給外掛 js 呼叫多語系標籤 /resources/assets/demo/demo-index.js
function fun_get_label(label){
	//alert("fun_get_label():"+label);
	var mylabel = "\"" + label +"\""; 
	if(label=="month"){
		//return "<%=label.get("month")%>";
		return "月";
	}else{
		if(label=="week"){
			return "周";	
		}else{
			if(label=="day"){
				return "日";	
			}else{
				if(label=="event_title"){
					return "Event Title";;
				}else{
					return "?";
				}
			}
		}
	}
	
};


// add by paul2015/10/03
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

var initial_calendard = true;
// 初始化時的樣板資料
var eventArr = [];
	
	//var eventArr = [
	//	{
	//		title: 'All Day Event',
	//		start: new Date(y, m, 8),
	//		backgroundColor: '#efa131'
	//	},
	//	{
	//		title: 'Long Event',
	//		start: new Date(y, m, d-5),
	//		end: new Date(y, m, d-2),
	//		backgroundColor: '#7a869c'
	//	},
	//	{
	//		id: 999,
	//		title: 'Repeating Event',
	//		start: new Date(y, m, d-3, 16, 0),
	//		allDay: false,
	//		backgroundColor: '#e74c3c'
	//	},
	//	{
	//		id: 999,
	//		title: 'Repeating Event',
	//		start: new Date(y, m, d+4, 16, 0),
	//		allDay: false,
	//		backgroundColor: '#e74c3c'
	//	},
	//	{
	//		title: 'Meeting',
	//		start: new Date(y, m, d, 10, 30),
	//		allDay: false,
	//		backgroundColor: '#76c4ed'
	//	},
	//	{
	//		title: 'Lunch',
	//		start: new Date(y, m, d, 12, 0),
	//		end: new Date(y, m, d, 14, 0),
	//		allDay: false,
	//		backgroundColor: '#34495e'
	//	},
	//	{
	//		title: 'Birthday Party',
	//		start: new Date(y, m, d+1, 19, 0),
	//		end: new Date(y, m, d+1, 22, 30),
	//		allDay: false,
	//		backgroundColor: '#2bbce0'
	//	},
	//	{
	//		title: 'Click Google',
	//		start: new Date(y, m, 28),
	//		end: new Date(y, m, 29),
	//		url: 'http://google.com/',
	//		backgroundColor: '#f1c40f'
	//	}
    //];
function renderCalendar(headertype) {

    // Demo for FullCalendar with Drag/Drop internal

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
	
	
	
	
/*	
    var calendar = $('#calendar-drag').fullCalendar({
        header: headertype,
        selectable: true,
        selectHelper: true,
        select: function(start, end, allDay) {
            //var title = prompt('input Event Title:');
			var title = prompt(fun_get_label("event_title"));
            if (title) {			
				//alert(formatDate(start));				
                calendar.fullCalendar('renderEvent',
                    {
                        title: title,
                        start: start,
                        end: end,
                        allDay: allDay
                    },
                    true // make the event "stick"
                );
				// call server to add event paul 2015/10/03
				$.ajax({
					method: 'post',	
					url: 'uisvc.do',
					dataType: 'json',
					data: { 
							ui_type: "5", // UI : calender
							event_type: "1", // insert calender event
							char_attr1: "9", // noused
							title: title,
							start: formatDate(start),
							end: formatDate(end),
							allDay: allDay
					},
					statusCode: {
					    404: function() {
					    	respResult=1;
					    	respCode=9;					   					    	
					      	alert( "page not found" );
					    },
					    400: function() {
					    	respResult=1;
					    	respCode=9;					    	
						    alert( "Bad request 400" );
						},
					    500: function() {
					    	respResult=1;
					    	respCode=9;					    	
						    alert( "Bad request 500" );
						}
				    },
					success: function(json){
						//alert("==>"+json.status+":"+json.msg);
						// parse json
						if(json.status=="0"){
							// add new event to eventArr
							eventArr.push({
								title: title,
								start: start,
								end: end,
								backgroundColor: '#2bbce0'
							});			
						}

					}
				});	
            }
            calendar.fullCalendar('unselect');
        },
        editable: true,
        events: eventArr,
        buttonText: {
            prev: '<i class="fa fa-angle-left"></i>',
            next: '<i class="fa fa-angle-right"></i>',
            prevYear: '<i class="fa fa-angle-double-left"></i>',  // <<
            nextYear: '<i class="fa fa-angle-double-right"></i>',  // >>
            today:    'Today',
            month:    fun_get_label("month"),
            week:     fun_get_label("week"),
            day:      fun_get_label("day"),
        }
    });

	if(initial_calendard){
		//alert("init cal");
		// call server to get all events by username(check session attributeuo in action) paul 2015/10/04
		$.ajax({
			method: 'post',	
			url: 'uisvc.do',
			dataType: 'json',
			data: { 
					ui_type: "5", // UI : calender
					event_type: "2", // get all calender event
					char_attr1: "9", // noused
			},
			statusCode: {
				404: function() {
					respResult=1;
					respCode=9;					   					    	
					alert( "page not found" );
				},
				400: function() {
					respResult=1;
					respCode=9;					    	
					alert( "Bad request 400" );
				},
				500: function() {
					respResult=1;
					respCode=9;					    	
					alert( "Bad request 500" );
				}
			},
			success: function(json){
				//alert("==>"+json.status+":"+json.msg);
				// parse json
				if(json.status=="0"){
					// check json array	and push to eventArr
					//alert(json.data.length);
					if(json.data.length>0){
						if(initial_calendard==false){
							//alert(clear);
							eventArr = []; // clear
						}
						$.each(json.data,function(){
							var info1=this['info1'];
							var info2=this['info2'];
							var info3=this['info3'];
							var info4=this['info4']; // All Day 'Y' or 'N'
							
							eventArr.push({
								title: info1,
								start: info2,
								end: info3,
								backgroundColor: '#2bbce0'
							});	
							if(initial_calendard){
								calendar.fullCalendar('renderEvent',
									{
										title: info1,
										start: info2,
										end: info3,
										backgroundColor: '#2bbce0'
									},
									true // make the event "stick"
								);
							}
							//calendar.fullCalendar('renderEvent', eventArr, true);							
						});
						initial_calendard=false;
						//calendar.fullCalendar('renderEvent', eventArr, true);
					}							
				}

			}
		});	
	}
*/	
	
    // Listen for click on toggle checkbox
    $('#select-all').click(function(event) {
        if(this.checked) {
            $('.selects :checkbox').each(function() {
                this.checked = true;
            });
        } else {
            $('.selects :checkbox').each(function() {
                this.checked = false;
            });
        }
    });

    $( ".panel-tasks" ).sortable({placeholder: 'item-placeholder'});
    $('.panel-tasks input[type="checkbox"]').click(function(event) {
        if(this.checked) {
            $(this).next(".task-description").addClass("done");
        } else {
            $(this).next(".task-description").removeClass("done");
        }
    });
    
    

}