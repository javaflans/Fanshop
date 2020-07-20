<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="components/header.jsp" />
<!-- 表頭資料區 -->
<input type="hidden" id="breadCrumbLabel" value="${breadCrumb}"/>
<input type="hidden" id="pageLabel" value="${pageLabel}"/>

<div class="content" >
	<div id="grid"></div>
</div>

<div class="loader">
</div>

<script type="text/x-kendo-template" id="template">
	<div class="prodTitle"> 選擇數量: <span class="chooseAccount" ></span>/<span class="totalAccount"></span> 個 </div>
	<button class="submitButton" >Commit</button> 
</script>


<!-- 引入 本程式的 JS, CSS -->
<script src="resources/js/${menuName}.js" type="text/javascript" ></script>
<link   href="resources/css/${menuName}.css" type='text/css' rel="stylesheet" />
<jsp:include page="components/footer.jsp" />