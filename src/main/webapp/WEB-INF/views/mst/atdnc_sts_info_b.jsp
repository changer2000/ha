<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<table id="table1" border="0" cellspacing="0" cellpadding="0"  width="300px">
	<tr>
		<td width="100px"><form:label path="atndnc_name"><s:message code="label.attendance.status.name"/></form:label></td>
		<td>
			<form:hidden path="id"/>
			<form:input path="atndnc_name"/><form:errors path="atndnc_name" cssClass="error"></form:errors>
		</td>
	</tr>
</table>