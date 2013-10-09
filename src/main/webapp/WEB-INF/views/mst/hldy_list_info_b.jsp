<%@page import="java.util.List" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<br/>
<table border='0' cellspacing='1' cellpadding='1' class='span12'>
<tr>
	<td class='span1 label'>
		<s:message code="label.year"/>
	</td>
	<td class='span1'>
		<form:hidden path="id"/>
		<form:input path="hldy_year" maxlength='4' cssClass='span1'/><form:errors path="hldy_year" cssClass="error"/>
	</td>
	<td class='span1 label'>
		<s:message code="label.holiday.name"/>
	</td>
	<td class='span2'>
		<form:select path="holidayPeerId" items="hldyOptions"
			itemLabel="name" itemValue="id" cssClass="span2"/><form:errors path="holidayPeerId" cssClass="error"/>
	</td>
	<td class='span2 label'>
		<s:message code="label.holiday.statistic.start.end.date"></s:message>
	</td>
	<td>
		<form:input path="start_dt" maxlength='10' cssClass="span2 form_datetime"/><form:errors path="start_dt" cssClass="error"/>
		<s:message code="label.sign.wave"/>
		<form:input path="end_dt" maxlength='10' cssClass="span2 form_datetime"/><form:errors path="end_dt" cssClass="error"/>
	</td>
</tr>
</table>
<table style="width:300px">
<tr>
	<td width="140px">&nbsp;</td>
	<td>
		<a class="btn btn-small" href="#" onclick="return line_add();"><i class="icon-plus"></i> <s:message code="button.line.add"/></a>
		<a class="btn btn-small" href="#" onclick="return line_del('tableb','selKey');"><i class="icon-minus"></i> <s:message code="button.line.delete"/></a>
	</td>
</tr>
</table>
<table id="tablea" border="1" cellspacing="0" cellpadding="0" width="300px">
<tr class="title">
	<th width="20px">&nbsp;</th>
	<th width="140px"><s:message code="label.holiday.start.date"/></th>
	<th><s:message code="label.holiday.end.date"/></th>
</tr>
</table>
<div style="width:320px;height:300px;">
<table id="tableb" border="1" cellspacing="0" cellpadding="0" width="300px">
<tbody id="tableb_body">
<c:if test="${command.hldyListDtlList != null}">
	<c:forEach items="${command.hldyListDtlList}" var="peer" varStatus="status">
		<c:set var="si" scope="page" value="${status.index}"/>
		<tr>
			<td width="20px">
				<input type="checkbox" name="selKey" value="<c:out value="${peer.id}"/>"/>
				<form:hidden path="hldyListDtlList[si].id"/>
			</td>
			<td width="140px">
				<form:input path="hldyListDtlList[si].hldy_start" maxlength="10" cssClass="form_datetime" cssStyle="width:120px"/>
				<form:errors path="hldyListDtlList[si].hldy_start" cssClass="error"/>	
			</td>
			<td>
				<form:input path="hldyListDtlList[si].hldy_end" maxlength="10" cssClass="form_datetime" cssStyle="width:120px"/>
				<form:errors path="hldyListDtlList[si].hldy_end" cssClass="error"/>
			</td>
		</tr>
	</c:forEach>
</c:if>
</tbody>
</table>
</div>
<table id="dtl_sample" >
<tbody>
<tr>
	<td width="20px">
		<input type="checkbox" name="selKey" value=""/>
	</td>
	<td width="140px">
		<input id="hldyListDtlList##.hldy_start" name="hldyListDtlList[##].hldy_start" class="form_datetime" style="width:120px" type="text" value="" maxlength="10"/>
	</td>
	<td>
		<input id="hldyListDtlList##.hldy_end" name="hldyListDtlList[##].hldy_end" class="form_datetime" style="width:120px" type="text" value="" maxlength="10"/>
	</td>
</tr>
</tbody>
</table>