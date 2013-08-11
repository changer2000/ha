<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<h4><s:message code="page.header.atdnc_sts_list"></s:message></h4>

<c:if test="${!empty logicCheckErrors}">
	<font color="red"><c:out value="${logicCheckErrors}"/></font>
</c:if>
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

<input type="submit" name="create" value="<s:message code="button.create"/>"/>
<input type="submit" name="modify" value="<s:message code="button.modify"/>"/>
<input type="submit" name="delete" value="<s:message code="button.delete"/>"/>

<script type="text/javascript">
	function formSubmit() {
		
	}
</script>