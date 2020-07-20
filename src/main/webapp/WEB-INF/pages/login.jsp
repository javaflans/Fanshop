<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="components/header.jsp" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/login.css' /> " />
<div class="main">
	<div class="title"
		style="background-image: url('${pageContext.request.contextPath}/resources/img/login-title.png');"></div>
	<div class="loginPanel">
		<form:form id="loginForm" action="loginAction" method="POST"
			modelAttribute="member">
			<table>
				<tr>
					<td><label for="userName">使用者帳號<br />(Email)
					</label></td>
					<td><form:input path="userMail" tabindex="1" class="autofocus" /></td>
					<td rowspan="2">
						<kendo:button name="primaryTextButton" class="k-primary submit">
							<kendo:button-content>
								登入
							</kendo:button-content>
						</kendo:button>
					</td>
				</tr>
				<tr>
					<td><label for="userPassword">使用者密碼 </label></td>
					<td><form:password path="userPassword" tabindex="2" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="reg">
							<kendo:dialog name="registDialog" width="600" title="註冊 分享達人 帳號" 
								closable="false" modal="true" visible= "false" >
								<kendo:dialog-actions>
									<kendo:dialog-action text="取消" action="registCancel" />
									<kendo:dialog-action text="註冊" primary="true" action="registSubmit" />
								</kendo:dialog-actions>
							</kendo:dialog>
							<kendo:dialog name="forgetDialog" width="600" title="密碼重設" 
								closable="false" modal="true" visible= "false" >
								<kendo:dialog-actions>
									<kendo:dialog-action text="取消" action="forgetCancel" />
									<kendo:dialog-action text="送出" primary="true" action="forgetSubmit" />
								</kendo:dialog-actions>
							</kendo:dialog>
							<a id="regist" href="#">我要註冊</a> / <a id="forget" href="#">忘記密碼?</a>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div class="message"></div> <input type="text" class="hidden"
						style="display: none;" value="${loginMessage}" />
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>

<div id="registContent" style="display: none;" >
	<form id="registForm">
		<fieldset id="account">
			<legend>帳戶資料</legend>
			<table>
				<tr>
					<td>
						<div class="labelSpace">
							<label for="userMail" class="required">帳號</label>
						</div> <input type="email" id="userMail" name="userMail" class="k-textbox" tabindex="1"
						placeholder="請輸入電子郵件" required validationMessage="請輸入帳號(Email)" />
					</td>
					<td>
						<div class="labelSpace">
							<label for="userPassword" >密碼</label>
						</div> 
						<input type="password" id="userPassword" name="userPassword" required tabindex="2"
						validationMessage="請輸入密碼" /> 
						<span data-for='userPassword' class='k-invalid-msg'></span>
					</td>
				</tr>
				<tr>
					<td>
						<div class="labelSpace">
							<label for="userName">暱稱</label>
						</div> 
						<input type="text" id="userName" name="userName" tabindex="4"
						class="k-textbox" placeholder="請輸入暱稱" required
						validationMessage="請輸入個人暱稱" />
						<span class="k-invalid-msg" data-for="userName"></span>
					</td>
					<td>
						<div class="labelSpace">
							<label for="confirmPassword">確認密碼</label>
						</div> <input id="confirmPassword" name="confirmPassword" tabindex="3"
						type="password" required validationMessage="請重複輸入密碼"
						data-verifyPasswords-field="userPassword"
						data-verifyPasswords-msg='與輸入密碼不一致, 請重新輸入' /> <span
						data-for='confirmPassword' class='k-invalid-msg'></span>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset id="dataInfo">
			<legend>個人資料</legend>
			<table>
				<tr>
					<td>
						<div class="labelSpace">
							<label for="userLocalName">姓名</label>
						</div> <input type="text" id="userLocalName" name="userLocalName" tabindex="5"
						class="k-textbox" placeholder="請輸入姓名" required
						validationMessage="請輸入個人姓名" /> 
						<span class="k-invalid-msg" data-for="userLocalName"></span>
					</td>
					<td>
						<div class="labelSpace">
							<label for="userBirthday">生日</label>
						</div> 
						<input type="text" data-role='datepicker' id="userBirthday" name="userBirthday" data-type="date" required tabindex="6" 
						validationMessage="請輸入個人出生日期"  />
						<span class="k-invalid-msg" data-for="userBirthday"></span>
					</td>
				</tr>
				<tr>
					<td>
						<div class="labelSpace">
							<label for="tel" class="required">行動電話</label>
						</div> 
						<input type="tel" id="userPhone" name="userPhone" class="k-textbox" tabindex="7" 
						pattern="09\d{8}" placeholder="請輸入個人電話" required
						validationMessage="請輸入10位數行動電話號碼"  />
						<span class="k-invalid-msg" data-for="userPhone"></span>
					</td>
					<td colspan="2">
						<div class="labelSpace">
							<label for="userAddress" class="required">郵寄地址</label>
						</div>
						<select id="city_select" name="city_select" tabindex="8"  ></select>
					    <select id="area_select" name="area_select" tabindex="9"  ></select>
						<input type="text" id="userAddress" name="userAddress" class="k-textbox" tabindex="10" 
						placeholder="請輸入地址, e.g. 天祥一路二弄三巷4號" required validationMessage="請正確填寫地址路段資料,以便日後寄送" style="width:99%" />
					</td>
				</tr>
				<tr>
					<td>
						<div class="labelSpace">
							<label for="email" class="required">郵遞區號</label>
						</div>
						<input type="text" id="zipCode" name="zipCode" required validationMessage="請確實選擇郵寄地址城市及區別資料" />
						<span class="k-invalid-msg" data-for="zipCode"></span>
					</td>
				</tr>
			</table>
		</fieldset>
		<span class="registStatus"></span>
	</form>
</div>

<div id="forgetContent" style="display: none;" >
	<form id="forgetForm">
			<table>
				<tr>
					<td>
						<p>若您忘記密碼，您將需要進行重設。</p>
						<p>請輸入您用來註冊網站的電子郵件地址。</p>

						<p>系統會將包含如何重設密碼相關指示的電子郵件傳送至此地址。</p>

						<p>若您忘記所註冊的電子郵件帳戶或尚未註冊帳戶，則請您從註冊新帳戶開始體驗。</p>
					</td>
				</tr>
				<tr>
					<td>
						<div class="labelSpace">
							<label for="userMail" class="required">電郵地址</label>
						</div> 
						<input type="email" id="userMail" name="userMail" class="k-textbox"
						placeholder="請輸入電子郵件" required validationMessage="請輸入帳號(Email)" />
						<span class="k-invalid-msg" data-for="userMail"></span>
					</td>
				</tr>
			</table>
		<span class="forgetStatus"></span>
	</form>
</div>

<a id="milkmidiUrl" href="<c:url value='/resources/xml'/>" style="display:none"></a>
<script src="<c:url value='/resources/js/login.js' />"></script>
<jsp:include page="components/footer.jsp" />