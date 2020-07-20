<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<c:if test="${header_type == 'kendo' }">
					</div>
				</div>
			</div>
		   	<!-- ================================= -->			
			<!--               頁尾                             -->
			<!-- ================================= -->
		    <footer role="contentinfo">
		        <div class="clearfix">
		            <ul class="list-unstyled list-inline pull-left">
		                <li>丞易國際有限公司&copy; 2017</li>
		            </ul>
		            <!-- 
		            <button class="pull-right btn btn-inverse-alt btn-xs hidden-print" id="back-to-top"><i class="fa fa-arrow-up"></i></button>
		            -->
		        </div>
		    </footer>		
		   	</div>
		</c:if>	
		
		<c:if test="${header_type != 'kendo' }">
		<div class="footer" >
			<footer role="contentinfo">
		        <div class="clearfix">
		            <ul class="list-unstyled list-inline pull-left">
		                <li>丞易國際有限公司&copy; 2017</li>
		            </ul>
		        </div>
		    </footer>
		</div>
		</c:if>	
		<script>
		 $(function() {
		        prettyPrint();

		        $(".source a").click(false);
		    });		
		</script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/1.6.1/clipboard.min.js"></script>
		<script src="<c:url value='/resources/js/common/tooltips.js'/>" ></script>
		<script src="<c:url value='/resources/js/common/common.js'/>" ></script>
		<script src="<c:url value='/resources/js/common/_header.js'/>"></script>
		<script src="<c:url value='/resources/js/common/_config.js'/>"></script>
		
		<script type="text/javascript">
			$(document).ready(resizeContainers);
			$(window).resize(resizeContainers);		
			function resizeContainers() {
			    var headerHeight = $("#header").height(),
			        searchWrapper = $("#example-search-wrapper").outerHeight(),
			        navbarWrapper = $("#example-nav-bar").height(),
			        htmlHeight = (kendo.support.mobileOS) ? parseFloat(window.innerHeight) : $('html').height();
			        sidebarHeight = htmlHeight - headerHeight,
			        borderSize = 2,
			        navHeight = sidebarHeight - (searchWrapper + navbarWrapper + borderSize);

			    $("#example-sidebar").height(sidebarHeight);
			    $("#nav-wrapper").height(navHeight);
			    $("#main").height(sidebarHeight);
			}
		</script>
		
		<style>
			.loader {
				display: none;
				position: absolute;     /*絕對位置*/
			    top: 50%;               /*從上面開始算，下推 50% (一半) 的位置*/
			    left: 50%;              /*從左邊開始算，右推 50% (一半) 的位置*/
			    margin-top: -100px;    /*寬度的一半*/
			    margin-left: -100px;    /*寬度的一半*/
				z-index: 3;
				height: 100px;
				width: 100px;
				border: 16px solid #fff;
				border-right-color: #c30;
				border-top-color: #c30;
				border-radius: 100%;
				-webkit-animation: spin 1000ms infinite linear;
				        animation: spin 1000ms infinite linear;
			}
			
			
			@-webkit-keyframes "spin" {
				from {
				  -webkit-transform: rotate(0deg);
				          transform: rotate(0deg);
				}
				to {
				  -webkit-transform: rotate(359deg);
				          transform: rotate(359deg);
				}
			}
			
			@keyframes "spin" {
				from {
				  -webkit-transform: rotate(0deg);
				          transform: rotate(0deg);
				}
				to {
				  -webkit-transform: rotate(359deg);
				          transform: rotate(359deg);
				}
			}
			
		</style>
	</body>
</html>