<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>"  rel="stylesheet" media="screen">
<link href="<c:url value="/resources/messages/messages.css"/>" rel="stylesheet" media="screen">
<script src="<c:url value="/resources/bootstrap/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
</head>
<body>
<br/>
<br/>
<br/>
<div class="container" style="border-style:solid;border-width:1px;">
	<div class="row span12">
		<div class="span4"></div>
		<div class="span4">
		<c:if test="${!empty error}">
		       <font color="red"><c:out value="${error}" /></font>
		</c:if>
		<form:form id="loginFrm" action="/login.do" method="post" modelAttribute="user">
		<fieldset>
			<legend>Login</legend>
			<form:label path="empe_num">Name:<br/><form:errors path="empe_num" cssClass="error" delimiter="<br/>"/></form:label>
			<form:input path="empe_num"/>
			<form:label path="pwd">Pwd:<br/><form:errors path="pwd" cssClass="error" delimiter="<br/>"/></form:label>
			<form:password path="pwd"/>
			<br/>
			<input type="submit" id="login" value="Login"/>
		</fieldset>
		</form:form>
		</div>
		<div class="span4"></div>
	</div>
</div>
</body>
</html>