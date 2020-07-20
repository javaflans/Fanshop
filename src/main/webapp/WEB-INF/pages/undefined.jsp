<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE center PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<header>
	</header>
	<body>
		<center>
			<div class="message">
				<img src="<c:url value='/resources/img/404.png' />" width="15%" />
				<br/>
				<p>很抱歉, 本頁面正在維護中</p>
				<br/>
				<div class="back">回上一頁</div>
			</div>
		</center>
		
		<style>
			html{
				height: 100%;
			}
			
			body{
				height: 100%;
				margin: 0;
				font-family: Microsoft JhengHei;
			}
			
			.message {
				width: 800px;
				height: 400px;
				background: #f6f6f6;
				position: absolute;
				top: 20%;
				left: 23%;
			}
			
			.back{
				width: 100px;
				height: 30px;
				padding-top: 10px;
				background: #ff8040;
			}
			
			.back:hover{
				cursor: pointer; 
			}
			
			.message img {
				margin-top: 10%;
			}
			
			p{
				color: #ff8040;
				font-size: 32px;
			}
		</style>
		<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
		<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script type="text/javascript">
			$(function(){
				$('.back').click(function(){
					history.go(-1);
				});
			})
		</script>
	</body>
</html>