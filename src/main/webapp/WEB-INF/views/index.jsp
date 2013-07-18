<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
</head>
<body>
<c:if test="${!empty error}">
       <font color="red"><c:out value="${error}" /></font>
</c:if>
<form name="loginForm" method="post" action='<c:url value="/login.do"/>'>
Name:<input type="text" name="empe_num" value=""/>
Pwd:<input type="password" name="pwd" value=""/>
<input type="submit" id="login" value="Login"/>
</form>
</body>
</html>