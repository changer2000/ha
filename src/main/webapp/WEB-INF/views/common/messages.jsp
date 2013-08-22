<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<c:if test="${!empty logicCheckErrors}">
	<div class="errorArea" align="left">
		<c:forEach var="msg" items="${logicCheckErrors}">
			<img src='<c:url value="/resources/messages/error.png"/>' class="icon"/><c:out value="${msg}"/><br/>
		</c:forEach>
	</div>
</c:if>
<c:if test="${!empty logicMessages}">
	<div class="infoArea" align="left">
		<c:forEach var="msg2" items="${logicMessages}">
			<img src='<c:url value="/resources/messages/info.png"/>' class="icon"/><c:out value="${msg2}"/><br/>
		</c:forEach>
	</div>
</c:if>