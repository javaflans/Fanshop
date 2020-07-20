<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="components/header.jsp" />
<!-- 表頭資料區 -->
<input type="hidden" id="pageLabel" value="${menus.get(0).getLabel()}"/>
	<table class="dataInfo">
		<tr>
			<td> ${loginRole.roleDesc} ID</td>
			<td>${loginData.userName}</td>
			<c:if test="${loginRole.roleName == 'KOL'}">
			<td rowspan="5"> </td>
				<td>達人等級</td>
				<td>${loginData.level}</td>
			</c:if>
		</tr>
		<tr>
			<td>姓名</td>
			<td>${loginData.userLocalName}</td>
			<c:if test="${loginRole.roleName == 'KOL'}">
				<td>分享指標</td>
				<td>${loginData.shareTarget}</td>
			</c:if>
		</tr>
		<tr>
			<td>生日</td>
			<td>${fn:replace(loginData.userBirthday.toString(),'-','/')}</td>
			<c:if test="${loginRole.roleName == 'KOL'}">
				<td>人氣指標</td>
				<td>${loginData.hotTarget}</td>
			</c:if>
		</tr>
		<tr>
			<td>電話</td>
			<td>${loginData.userPhone}</td>
			<c:if test="${loginRole.roleName == 'KOL'}">
				<td>成交指標</td>
				<td>${loginData.transTarget}</td>
			</c:if>
		</tr>
		<tr>
			<td>地址</td>
			<td>${loginData.userCity}${loginAccount.userArea}${loginData.userAddress}</td>
			<c:if test="${loginRole.roleName == 'KOL'}">
				<td>合作指標</td>
				<td>${loginData.businessTarget}</td>
			</c:if>
		</tr>
	</table>

<!-- 引入 本程式的 JS, CSS -->

<script src="<c:url value='/resources/js/${menuName}.js'/>" type="text/javascript" ></script>
<link   href="<c:url value='/resources/css/${menuName}.css' />" type='text/css' rel="stylesheet" />

<jsp:include page="components/footer.jsp" />

