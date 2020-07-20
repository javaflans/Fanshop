<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="components/header.jsp" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/kolverfyresult.css' /> " />
<div class="title">
	<div class="logo">
	</div>
</div>
<div class="main">
	<h1>${title}</h1>
	<div class="msg">
		${message}
	</div>
	<div class="href" onclick="javascript:location.href='login'">
		<input type="button" value="重新登入" />
	</div>
</div>


<script src="<c:url value='/resources/js/kolverfyresult.js' /> "></script>
<jsp:include page="components/footer.jsp" />