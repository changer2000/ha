<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
<tiles:useAttribute id="titleKey" name="titleKey"/>
<title><fmt:message key="${titleKey}"/></title>
</head>
<body>
<div id="headerDiv" style="height:30px">
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
</div>
<div id="menuDiv" style="height:30px">
	<tiles:insertAttribute name="menu"></tiles:insertAttribute>
</div>
<div id="contentDiv" style="height:400px">
	<tiles:insertAttribute name="content"></tiles:insertAttribute>
</div>
<div id="footerDiv" style="height:30px">
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</div>
</body>
</html>