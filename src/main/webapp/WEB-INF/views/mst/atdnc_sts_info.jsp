<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<h4><s:message code="page.header.atdnc_sts_info"></s:message></h4>
<table id="table1" border="0" cellspacing="0" cellpadding="0"  width="300px">
	<tr>
		<td width="100px"><form:label path="atndnc_name"><s:message code="label.attendance.status.name"/></form:label></td>
		<td>
			<form:hidden path="id"/>
			<form:input path="atndnc_name"/><form:errors path="atndnc_name" cssClass="error"></form:errors>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
			<input type="submit" name="register" value='<s:message code="button.register"></s:message>'/>
			<input type="submit" name="back" value='<s:message code="button.back"></s:message>'/>
		</td>
	</tr>
</table>