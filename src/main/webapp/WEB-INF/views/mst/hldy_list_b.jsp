<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<c:if test="${!empty logicCheckErrors}">
	<font color="red"><c:out value="${logicCheckErrors}"/></font>
</c:if>
<table id="table1" border="1" cellspacing="0" cellpadding="0"  width="300px">
	<thead>
		<tr>
			<th width="20px">&nbsp;</th>
			<th width="280px"><s:message code="label.holiday.name"/></th>
		</tr>
	</thead>
	<c:if test="${list!=null }">
		<c:forEach var="peer" items="${list}">
			<tr>
				<td >
					<input type="checkbox" name="id" value="<c:out value="${peer.id}"/>" style="margin-top:0px;"/>
				</td>
				<td>
					<c:out value="${peer.name}"/>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>