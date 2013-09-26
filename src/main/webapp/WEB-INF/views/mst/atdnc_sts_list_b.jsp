<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<table id="table1" border="1" cellspacing="0" cellpadding="0"  width="400px">
	<thead>
		<tr class="title">
			<th width="20px" >&nbsp;</th>
			<th width="50px" class="title"><s:message code="label.id"/></th>
			<th width="260px" class="title"><s:message code="label.attendance.status.name"/></th>
			<th width="70px" class="title"><s:message code="label.in.shanghai"/></th>
		</tr>
	</thead>
	<c:if test="${adtnsStsList!=null }">
		<c:forEach var="peer" items="${adtnsStsList}">
			<tr>
				<td align="center">
					<input type="checkbox" name="id" value="<c:out value="${peer.id}"/>"/>
				</td>
				<td align="center">
					<c:out value="${peer.id}"/>
				</td>
				<td>
					<c:out value="${peer.atndnc_name}"/>
				</td>
				<td align="center">
					<c:if test="${peer.in_shanghai == 1 }">
						<s:message code="label.yes"/>
					</c:if>
					<c:if test="${peer.in_shanghai != 1 }">
						<s:message code="label.no"/>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>