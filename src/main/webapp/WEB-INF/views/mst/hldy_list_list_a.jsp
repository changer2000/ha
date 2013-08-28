<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<h4><s:message code="page.header.hldy_list_list"></s:message></h4>
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
	<td>
		<%List myList = (List) pageContext.getAttribute("hldyOptions", pageContext.REQUEST_SCOPE); %>
		<form:select path="searchBean.hldy_id" items="<%=(List) pageContext.getAttribute(\"hldyOptions\", pageContext.REQUEST_SCOPE)%>"
			itemLabel="name" itemValue="id" cssClass="span2"
		>	
		</form:select>
	</td>
</tr>
</table>
<table border="0" cellspacing="0" cellpadding="1"  class="span8 topSpace">
<tr>
	<td class="span2 label">
		<s:message code="label.holiday.start.end.date"></s:message>
	</td>
	<td>
		<form:input path="searchBean.hldy_start" maxlength="10" cssClass="span2"/>
		<s:message code="label.sign.wave"/>
		<form:input path="searchBean.hldy_end" maxlength="10" cssClass="span2"/>
	</td>
</tr>
</table>
<table border="0" cellspacing="0" cellpadding="1"  class="span8 topSpace">
<tr>
	<td class="span2 label">
		<s:message code="label.holiday.statistic.start.end.date"></s:message>
	</td>
	<td>
		<form:input path="searchBean.start_date" maxlength="10" cssClass="span2"/>
		<s:message code="label.sign.wave"/>
		<form:input path="searchBean.end_date" maxlength="10" cssClass="span2"/>
	</td>
</tr>
</table>