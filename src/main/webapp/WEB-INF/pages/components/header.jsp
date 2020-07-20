<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Fanshopping開團樂</title>
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
		<!-- IE可能不見得有效 -->
		<META HTTP-EQUIV="EXPIRES" CONTENT="0">
		<!-- 設定成馬上就過期 -->
		<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
		<!-- 與第一行是同樣的作用 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">	
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="description" content="Xenon Boostrap Admin Panel" />
		<meta name="author" content="Paralucent International Co. Ltd." />
		
		<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="0">
		
		<link href="<c:url value='/resources/styles/kendo.common-material.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='/resources/styles/kendo.material.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='/resources/styles/kendo.default.mobile.min.css'/>" rel="stylesheet" />
		<link href="<c:url value='/resources/styles/examples-offline.css'/>" rel="stylesheet">
		<link href="<c:url value='/resources/css/common/_header.css'/>" rel="stylesheet">
		<link href="<c:url value='/resources/css/common/primer.css'/>" rel="stylesheet">
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
		
		<script src="https://kendo.cdn.telerik.com/2017.2.504/js/jquery.min.js"></script>
		<script src="<c:url value='/resources/js/kendo/kendo.all.min.js' />"></script>
		
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
		<script type='text/javascript' src="<c:url value='/resources/assets/js/placeholdr.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/js/application.js' />"></script> 
		<script type='text/javascript' src="<c:url value='/resources/assets/demo/demo.js' />"></script>
		<script type='text/javascript' src="<c:url value='/resources/js/milkmidi/milkmidi.CityArea.js' />"></script>
		<!-- Add By Paul End -->	
    </head>
    <body>
	<c:if test="${header_type == 'kendo'}">
    		
		<div id="headerbar">
		    <div class="container">
		        <div class="row">
		            <div class="col-xs-6 col-sm-2">
		                <a href="#" class="shortcut-tiles tiles-brown">
		                    <div class="tiles-body">
		                        <div class="pull-left"><i class="fa fa-pencil"></i></div>
		                    </div>
		                    <div class="tiles-footer">
		                        Create Post
		                    </div>
		                </a>
		            </div>
		            <div class="col-xs-6 col-sm-2">
		                <a href="#" class="shortcut-tiles tiles-grape">
		                    <div class="tiles-body">
		                        <div class="pull-left"><i class="fa fa-group"></i></div>
		                        <div class="pull-right"><span class="badge">2</span></div>
		                    </div>
		                    <div class="tiles-footer">
		                        Contacts
		                    </div>
		                </a>
		            </div>
		            <div class="col-xs-6 col-sm-2">
		                <a href="#" class="shortcut-tiles tiles-primary">
		                    <div class="tiles-body">
		                        <div class="pull-left"><i class="fa fa-envelope-o"></i></div>
		                        <div class="pull-right"><span class="badge">10</span></div>
		                    </div>
		                    <div class="tiles-footer">
		                        Messages
		                    </div>
		                </a>
		            </div>
		            <div class="col-xs-6 col-sm-2">
		                <a href="#" class="shortcut-tiles tiles-inverse">
		                    <div class="tiles-body">
		                        <div class="pull-left"><i class="fa fa-camera"></i></div>
		                        <div class="pull-right"><span class="badge">3</span></div>
		                    </div>
		                    <div class="tiles-footer">
		                        Gallery
		                    </div>
		                </a>
		            </div>
		
		            <div class="col-xs-6 col-sm-2">
		                <a href="#" class="shortcut-tiles tiles-midnightblue">
		                    <div class="tiles-body">
		                        <div class="pull-left"><i class="fa fa-cog"></i></div>
		                    </div>
		                    <div class="tiles-footer">
		                        Settings
		                    </div>
		                </a>
		            </div>
		            <div class="col-xs-6 col-sm-2">
		                <a href="#" class="shortcut-tiles tiles-orange">
		                    <div class="tiles-body">
		                        <div class="pull-left"><i class="fa fa-wrench"></i></div>
		                    </div>
		                    <div class="tiles-footer">
		                        Plugins
		                    </div>
		                </a>
		            </div>
		                        
		        </div>
		    </div>
		</div>
	
	 	<!-- 最上層的使用設置  -->
		<header class="navbar navbar-inverse navbar-fixed-top" role="banner">
		     <a id="leftmenu-trigger" class="tooltips" data-toggle="tooltip" data-placement="right" title="選單收合"></a>
		     <!--
		     	<a id="rightmenu-trigger" class="tooltips" data-toggle="tooltip" data-placement="left" title="右選單"></a>
		     -->
		     
		     <ul class="nav navbar-nav pull-right toolbar">
		     	<li class="dropdown">
		     		<a href="#" class="dropdown-toggle username" data-toggle="dropdown"><span class="hidden-xs" style="font-size: 1.25em; padding-top: 1px; font-family: '微軟正黑體';">${loginAccount.userLocalName} <i class="fa fa-caret-down" style="margin-left:2px;"></i></span><div class='customer-photo bar-photo' style='background-image: url(resources/web/Customers/ALFKI.jpg);'></div></a>
		     		<ul class="dropdown-menu userinfo arrow">
		     			<!-- 用戶大頭貼 -->
		     			<li class="username">
		                     <a href="#">
		     				    <div class="pull-left"><div class='customer-photo' style='background-image: url(resources/web/Customers/ALFKI.jpg);'></div></div>
		     				    <div class="pull-right"><h5>Hello, ${loginAccount.userLocalName}!</h5><small>admin <span>admin</span></small></div>							
		                     </a>
		     			</li>
		     			<!-- dropdown menu -->
		     			<!-- 下拉式系統選單 -->
		     			<li class="userlinks">
		     				<ul class="dropdown-menu">
		     					<li>
		     						<a id=d1 href="javascript:;" onclick="return dropdownMenu_action(this,'action.do');">
		     							<span id=s1 style="font-size: 8pt" class="title">系統設置</span> 
		     							<i class="pull-right fa fa-wrench"></i>
		     						</a>
		     					</li>
					     	 	<li>
		     						<a id=d2 href="javascript:;" onclick="return dropdownMenu_action(this,'action.do');">
		     							<span id=s2 style="font-size: 8pt" class="title">變更密碼</span>
		     							<i class="pull-right fa fa-edit"></i> 
		     						</a>
		     					</li>
		     			     	<li class="divider"></li>
				     			<li>
		     						<a id="logout" href="logout">
		     							<span style="font-size: 8pt" class="title">登出系統</span> 
		     							<i class="pull-right fa fa-lock"></i> 
		     						</a>
				     			</li>			
		     				</ul>
		     			</li>
		     			
		     		</ul>
		     	</li>
		     	
		     	<!-- 訊息 UI Type = 3 -->
		     	<li id="mail" class="dropdown">
		     		<a href="#" class="dropdown-toggle" data-toggle='dropdown'><i class="fa fa-envelope"></i></a>
		     		<ul class="dropdown-menu messages arrow">
		     			<li class="dd-header">
		     			
		     				<span>no msg</span>
		     			</li>
		                 <div id="msg_data" class="scrollthis">
		
		                 </div>
		     			<li class="dd-footer"><a href="#">close</a></li>
		     		</ul>
		     	</li>
		     	<!-- ToolTip -->
		     	<span id="toolTip"></span>
		     	<span id="message"></span>
		     	<!-- 通知 -->
		     	<li id="notification" class="dropdown">
		     		<a href="#" class="dropdown-toggle" data-toggle='dropdown'><i class="fa fa-bell"></i></a>
		     		<ul class="dropdown-menu notifications arrow">
		     			<li class="dd-header">
		     				<span>no notify</span>
		     			</li>
		                 <div id="notiy_data" class="scrollthis">
		 				    
		                 </div>
		     			<li class="dd-footer"><a href="#">close</a></li>
					</ul>
				 </li>
				 <c:if test="${loginRole.roleName == 'Admin'}">
					 <li class="onlineCount">
					       平台線上人數: ${onlineCount} 人
					 </li>
				 </c:if>
		        <!--
		        <li>
		             <a href="#" id="headerbardropdown"><span><i class="fa fa-level-down"></i></span></a>
		        </li>
		        -->
			</ul>
		</header>
	<!-- ================================= -->			
	<!--             主頁面                              -->
	<!-- ================================= -->
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
	    <!-- ================================= -->		
		<!--      BEGIN SIDEBAR 左邊功能選單       -->
		<!-- ================================= -->
        <nav id="page-leftbar" style="position: fixed;" role="navigation">
			<!-- BEGIN SIDEBAR MENU -->
            <ul class="acc-menu"  id="sidebar">
			<c:forEach items="${menus}" var="menu">
				<li id="${menu.name}" class="mainmenu" >
				<c:choose>
					<c:when test="${menu.menus == null}">
						<c:choose>
							<c:when test="${menu.path == 'overview'}">
								<a href="${menu.path}">
					                <i class="fa ${menu.icon}"></i> 
					                <span id="${menu.name}" style="font-size: 12pt"  class="title">${menu.label}</span> 
				                </a>
			                </c:when>
			                <c:otherwise>
			                <a href="#">
					                <i class="fa ${menu.icon}"></i> 
					                <span id="${menu.name}" style="font-size: 12pt"  class="title">${menu.label}</span> 
				                </a>
				                <ul class="acc-menu">   
								<c:forEach items="${menus}" var="childMenu">							    
									<c:choose>
										<c:when test="${childMenu.menus.name == menu.name}">
											<li id="${childMenu.name}" class="submenu">
			                        			<a href="${childMenu.path}">
			                        				<i class="fa ${childMenu.icon}"></i>
			                        				<span id="${childMenu.name}" style="font-size: 10pt" class="title">${childMenu.label}</span>
			                        			</a>
		                        			</li>
										</c:when>
									</c:choose>
								</c:forEach>
								</ul>
							</c:otherwise>
						</c:choose>
					</c:when>
				</c:choose>
				</li>
			</c:forEach>
			</ul>
        <!-- END SIDEBAR MENU -->
        </nav>
        <!-- ================================= -->
        <!--            END SIDEBAR            -->
        <!-- ================================= -->
        
        
        <div id="page-content">
        	<!-- 右上角時鐘 
		    <div id="surface" class="clockContent"></div>
		    -->
		    <!-- 給系統專用 popup 訊息用 -->
		    <span id="notifications"></span>
			<div class="page">
				<div id="page-heading">
		        	<ol id="breadcrumb" class="breadcrumb">              
		                <li class='active'>
		                <a href="javascript:;" onclick="return home_action(this);"><i class="fa fa-home"></i> Home</a>
		                </li>
		            </ol>
	            	<h1></h1>
	        	</div>
    			<div id="example" class="k-content"> 
	    	
	    
    </c:if>