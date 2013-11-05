<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
<title>Error</title>
</head>
<body>
	<span><c:out value="${errMsgBean.errMessage}" escapeXml="false"/></span>
</body>
</html>
