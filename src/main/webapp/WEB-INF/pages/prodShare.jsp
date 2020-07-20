<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String template = "<span class='k-state-default itemPhoto' style='background-image: url(http://www.fanshopping.com.tw/uploads/logo/product/#: data.f10 #_320x240); margin-top:15px;'></span><span class='k-state-default'><table><tr><td class='tcontent'><h3>#: data.f00 #</h3></td><td class='price' rowspan='2'>$ #: data.f05 #</td></tr><tr><td class='tcontent'><p>#: data.f03 #</p></td></tr></table></span>";
%>
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
    <button class="tooltipMsg" data-clipboard-text="http://www.fanshopping.com.tw/kol/index.php?uuid=${loginAccount.userUuid}" title="複製分享達人專區網址">
        <img class="clippy" width="25" src="resources/img/clippy.svg" >
	</button>
    <!--
    <button class="tooltipMsg" data-clipboard-text="http://kol.paralucent.com.tw/kol/index.php?uuid=${loginAccount.userUuid}" title="複製分享達人專區網址">
        <img class="clippy" width="25" src="resources/img/clippy.svg" >
	</button>
    -->
	<button class="submitButton" >Commit</button>
</script>

<input type="text" id="userUuid" value="${userUuid}" style="display: none;" />
<input type="text" id="userMaxProdCount" value="${userMaxProdCount}" style="display: none;" />
<!-- 引入 本程式的 JS, CSS -->
<script src="resources/js/${menuName}.js" type="text/javascript" ></script>
<link   href="resources/css/${menuName}.css" type='text/css' rel="stylesheet" />

<jsp:include page="components/footer.jsp" />

