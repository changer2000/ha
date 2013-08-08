<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<tiles:useAttribute id="titleKey" name="titleKey"/><!-- 如果没有这一行，下面的title显示就是乱码?????? -->
<title><fmt:message key="${titleKey}"/></title>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>"  rel="stylesheet" media="screen">
<link href="<c:url value="/resources/messages/messages.css"/>" rel="stylesheet" media="screen">
<script src="<c:url value="/resources/bootstrap/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
</head>
<body>
<div id="headerDiv" style="height:60px">
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
</div>
<div id="menuDiv" style="height:40px">
	<tiles:insertAttribute name="menu"></tiles:insertAttribute>
</div>
<div id="contentDiv" style="height:730px">
	<tiles:insertAttribute name="content"></tiles:insertAttribute>
</div>
<div id="footerDiv" style="height:30px" align="center">
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</div>
</body>
</html>