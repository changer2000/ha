<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<br/>
<table border="0" cellspacing="1" cellpadding="1"  class="span8">
<tr>
	<td class="span1 label">
		<s:message code="label.year"></s:message>
	</td>
	<td class="span1">
		<form:input path="searchBean.hldy_year" maxlength="4" cssClass="span1"/>
	</td>
	<td class="span2 label">
		<s:message code="label.holiday.name"></s:message>
	</td>
	<td class="span4">
		<form:select path="searchBean.hldy_id" items="<%=(List) pageContext.getAttribute(\"hldyOptions\", pageContext.REQUEST_SCOPE)%>"
			itemLabel="name" itemValue="id" cssClass="span2"
		>	
		</form:select>
	</td>
	<td>
		<input type="submit" class="btn btn-primary span1" name="search" value="<s:message code="button.search"/>"/>
	</td>
</tr>
</table>
<table border="0" cellspacing="0" cellpadding="1"  class="span12 topSpace">
<tr>
	<td class="span1 label">
		<s:message code="label.holiday.start.end.date"></s:message>
	</td>
	<td class="span5">
		<form:input path="searchBean.hldy_start" maxlength="10" cssClass="span2 form_datetime"/>
		<s:message code="label.sign.wave"/>
		<form:input path="searchBean.hldy_end" maxlength="10" cssClass="span2 form_datetime"/>
	</td>
	<td class="span1 label">
		<s:message code="label.holiday.statistic.start.end.date"></s:message>
	</td>
	<td class="span5">
		<form:input path="searchBean.start_date" maxlength="10" cssClass="span2 form_datetime"/>
		<s:message code="label.sign.wave"/>
		<form:input path="searchBean.end_date" maxlength="10" cssClass="span2 form_datetime"/>
	</td>
</tr>
</table>