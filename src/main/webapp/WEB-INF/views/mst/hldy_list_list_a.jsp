<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<h4><s:message code="page.header.hldy_list_list"></s:message></h4>
<br/>
<table border="0" cellspacing="0" cellpadding="0"  width="400px">
<tr>
	<td class="label" width="50px">
		<s:message code="label.year"></s:message>
	</td>
	<td width="80px">
		<form:input path="searchBean.hldy_year" maxlength="4"/>
	</td>
	<td class="label" width="70px">
		<s:message code="label.holiday.name"></s:message>
	</td>
	<td>
		<form:select path="searchBean.hldy_id" items="${hldyOptions}">
			
		</form:select>
		<c:if test="${!empty hldyOptions}">
			<c:forEach items="${hldyOptions}" var="p"> 
				<c:out value="${p}"></c:out><br/>
			</c:forEach>
		</c:if>
	</td>
</tr>
</table>
<table border="0" cellspacing="0" cellpadding="0"  width="400px">
<tr>
	<td width="150px" class="label">
		<s:message code="label.holiday.start.end.date"></s:message>
	</td>
	<td>
		<form:input path="searchBean.hldy_start" maxlength="10"/>
		<s:message code="label.sign.wave"/>
		<form:input path="searchBean.hldy_end" maxlength="10"/>
	</td>
</tr>
<tr>
	<td width="150px" class="label">
		<s:message code="label.holiday.statistic.start.end.date"></s:message>
	</td>
	<td>
		<form:input path="searchBean.start_date" maxlength="10"/>
		<s:message code="label.sign.wave"/>
		<form:input path="searchBean.end_date" maxlength="10"/>
	</td>
</tr>
</table>