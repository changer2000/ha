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
<div class="container" >
	<div class="row span12">
		<div class="span4"></div>
		<div class="span4">
		<c:if test="${!empty error}">
		       <font color="red"><c:out value="${error}" /></font>
		</c:if>
		<form:form id="loginFrm" action="/login.do" method="post" modelAttribute="user">
			<form:errors cssClass="error"></form:errors>
			<fieldset>
				<legend>Login</legend>
				<form:label path="empe_num"><s:message code="label.login.empe_num"/><br/><form:errors path="empe_num" cssClass="error" delimiter="<br/>"/></form:label>
				<form:input path="empe_num"/>
				<form:label path="pwd"><s:message code="label.login.pwd"/><br/><form:errors path="pwd" cssClass="error" delimiter="<br/>"/></form:label>
				<form:password path="pwd"/>
				<br/>
			</fieldset>
			<table border="1" id="tablea">
			<c:forEach var="peer" items="${user.atdncList}" varStatus="status">
				<c:set var="si" value="${status.index}"/>
				<tr>
					<td>
						<input type="text" name="atdncList[<c:out value="${status.index }" />].id" value="<c:out value="${peer.id}"/>"/>
					</td>
					<td>
						<input type="text" name="atdncList[<c:out value="${status.index }" />].atndnc_name" value="<c:out value="${peer.atndnc_name}"/>"/>
						<% Integer num=(Integer)pageContext.getAttribute("si");
							String fn = "atdncList["+num+"].atndnc_name";
						%>
						<form:errors path="<%=fn%>"/>
					</td>
				</tr>
			</c:forEach>
			</table>
			<br/>
			<div align="center" class="span2">
				<input type="submit" id="login" value="<s:message code="button.login.login"/>" class="btn btn-primary"/>
				<input type="reset" id="reset" value="<s:message code="button.login.reset"/>" class="btn btn-primary"/>
			</div>
		</form:form>
		</div>
		<div class="span4"></div>
	</div>
</div>
<script type="text/javascript">
	function init() {
		if ($('#tablea').find('tr').length>2)
			$('#tablea').find('tr').get(1).remove();
	}
	
	init();
</script>
</body>
</html>