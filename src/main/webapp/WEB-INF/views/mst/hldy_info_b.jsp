<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<table id="table1" border="0" cellspacing="0" cellpadding="0"  width="600px">
	<tr>
		<td width="100px"><form:label path="name"><s:message code="label.holiday.name"/></form:label></td>
		<td width="200px">
			<form:hidden path="id"/>
			<form:input path="name"/><form:errors path="name" cssClass="error"></form:errors>
		</td>
		<td width="100px">
			<form:label path="order_no"><s:message code="label.holiday.order_no"/></form:label>
		</td>
		<td>
			<form:input path="order_no"/><form:errors path="order_no" cssClass="error"/>
		</td>
	</tr>
</table>