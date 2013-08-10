<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<h4><s:message code="page.header.atdnc_sts_list"></s:message></h4>
<form:form method="post" action="maintain/adtnsSts">
<table id="table1" border="1" cellspacing="0" cellpadding="0"  width="300px">
	<thead>
		<tr>
			<th width="20px">&nbsp;</th>
			<th width="280px"><s:message code="label.attendance.status.name"/></th>
		</tr>
	</thead>
	<c:if test="${adtnsStsList!=null }">
		<c:forEach var="peer" items="${adtnsStsList}">
			<tr>
				<td >
					<input type="checkbox" name="id" value="<c:out value="${peer.id}"/>"/>
				</td>
				<td>
					<c:out value="${peer.atndnc_name}"/>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
</form:form>