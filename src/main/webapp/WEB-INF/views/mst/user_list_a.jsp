<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<script src='<c:url value="/resources/js/mst/user_list.js"/>'></script>
<br/>
<table border="0" cellspacing="1" cellpadding="1"  class="span12">
<tr>
	<td class="label span1">
		<s:message code="label.login.empe_num"/>
	</td>
	<td class="span2">
		<form:input path="searchBean.empe_num" maxlength="" cssClass="span2"/>
		<form:errors path="searchBean.empe_num" cssClass="error"/>
	</td>
	<td class="label span1">
		<s:message code="label.email"/>
	</td>
	<td class="span2">
		<form:input path="searchBean.email" maxlength="50" cssClass="span2"/>
		<form:errors path="searchBean.email" cssClass="error"/>
	</td>
	<td class="label span1">
		<s:message code="label.mobile"/>
	</td>
	<td class="span2">
		<form:input path="searchBean.mobile" maxlength="11" cssClass="span2"/>
		<form:errors path="searchBean.mobile" cssClass="error"/>
	</td>
	<td class="label span1">
		<s:message code="label.tel_no"/>
	</td>
	<td class="span2">
		<form:input path="searchBean.tel_no" maxlength="50" cssClass="span2"/>
		<form:errors path="searchBean.tel_no" cssClass="error"/>
	</td>
</tr>
</table>
<table border="0" cellspacing="1" cellpadding="1"  class="span12 topSpace">
<tr>
	<td class="label span1">
		<s:message code="label.empe.name"/>
	</td>
	<td class="span3">
		<form:input path="searchBean.empe_name" maxlength="100" cssClass="span2"/>
		<form:errors path="searchBean.empe_name" cssClass="error"/>
	</td>
	<td class="span8">
		<input type="submit" class="btn btn-primary span1" name="search" value="<s:message code="button.search"/>"/>
	</td>
</tr>
</table>