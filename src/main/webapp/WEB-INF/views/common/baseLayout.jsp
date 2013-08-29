<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<tiles:useAttribute id="titleKey" name="titleKey"/><!-- 如果没有这一行，下面的title显示就是乱码?????? -->
<tiles:useAttribute id="actionURL" name="action" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="methodType" name="methodType"  classname="java.lang.String" ignore="true"/>
<tiles:useAttribute id="encType" name="encType"  classname="java.lang.String" ignore="true"/>
<tiles:useAttribute id="commandName" name="command"  classname="java.lang.String" ignore="true"/>

<tiles:useAttribute id="aHeight" name="aHeight" classname="java.lang.String" ignore="true"/>
<tiles:useAttribute id="bHeight" name="bHeight" classname="java.lang.String" ignore="true"/>

<title><fmt:message key="${titleKey}"/></title>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>"  rel="stylesheet" media="screen">
<link href="<c:url value="/resources/bootstrap/css/datetimepicker.css"/>"  rel="stylesheet" media="screen">
<link href="<c:url value="/resources/messages/messages.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" media="screen">

<script src="<c:url value="/resources/bootstrap/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap-datetimepicker.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"/>"></script>

<script src="<c:url value="/resources/js/function.js"/>"></script>
</head>
<body onload="loadForm();">
<form:form enctype="<%=encType%>" action="<%=actionURL%>" onsubmit="return formSubmit();" method="<%=methodType%>" modelAttribute="<%=commandName%>">
<div id="headerDiv" style="height:60px">
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
</div>
<div id="menuDiv" style="height:40px">
	<tiles:insertAttribute name="menu"></tiles:insertAttribute>
</div>
<div id="a" align="center" style="height:<tiles:insertAttribute name="aHeight"/>px">
	<tiles:insertAttribute name="a"/>
</div>
<div id="b" align="center" style="height:<tiles:insertAttribute name="bHeight"/>px">
	<tiles:insertAttribute name="messages"></tiles:insertAttribute>
	<tiles:insertAttribute name="b"></tiles:insertAttribute>
</div>
<div id="c" align="center" style="height:60px">
	<tiles:insertAttribute name="c"></tiles:insertAttribute>
</div>
<div id="footerDiv" style="height:20px" align="center">
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</div>
</form:form>
</body>
</html>