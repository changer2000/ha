<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<table id="table1" border="0" cellspacing="0" cellpadding="0"  width="300px">
	<tr>
		<td width="100px"><form:label path="name"><s:message code="label.holiday.name"/></form:label></td>
		<td>
			<form:hidden path="id"/>
			<form:input path="name"/><form:errors path="name" cssClass="error"></form:errors>
		</td>
	</tr>
</table>