<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="components/header.jsp" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/forgetpasswordresult.css' /> " />
<div class="title">
	<div class="logo"></div>
</div>
<div class="main">

	<h1>${title}</h1>

	<c:if test="${code==200}">
		<div class="successContent msg">${message}</div>
		<div class="successContent content">
			<form id="forgetForm">
				<table>
					<tr>
						<td>
							<div class="labelSpace">
								<label for="userPassword">密碼</label>
							</div> <input type="password" id="userPassword" name="userPassword"
							required validationMessage="請輸入密碼" />
						</td>
					</tr>
					<tr>
						<td>
							<div class="labelSpace">
								<label for="confirmPassword">確認密碼</label>
							</div> <input id="confirmPassword" name="confirmPassword"
							type="password" required validationMessage="請重複輸入密碼"
							data-verifyPasswords-field="userPassword"
							data-verifyPasswords-msg='與輸入密碼不一致, 請重新輸入' />
							<input type="text" id="userMail" name="userMail" style="display:none;" value="${member.userMail}" />
						</td>
						
					</tr>
				</table>
				<span class="forgetStatus"></span>
			</form>
		</div>
	
		<div class="successContent href" onclick="resetPasswordSubmit()">
			<input type="button" value="確認" />
		</div>
	</c:if>
	<c:if test="${code!=200}">
		<div class="alertmsg">${message}</div>
		<div class="alerthref" onclick="javascript:location.href='login'">
			<input type="button" value="重新登入" />
		</div>
	</c:if>
	<div class="failContent message"></div>
	<div class="failContent alerthref" onclick="javascript:location.href='login'">
		<input type="button" value="重新登入" />
	</div>
</div>


<script src="<c:url value='/resources/js/forgetpasswordresult.js' /> "></script>
<jsp:include page="components/footer.jsp" />