<%@page import="java.util.List" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<ha:sessionget id="gMode"
	subSystem="<%=com.etech.ha.constants.HaConstants.SUB_SYSTEM_MST%>"
	module="<%=com.etech.ha.constants.HaConstants.MODULE_USER_INFO%>"
	key="<%=com.etech.ha.constants.HaConstants.SESSION_KEY_USER_INFO_MODE%>"
	toScope="page" />
<script type="text/javascript">
	var gMode='<c:out value="${gMode}"/>';
</script>
<div class="row">
	<div class="label span1"><s:message code="label.login.empe_num"/></div>
	<div class="span2">
		<form:input path="empe_num" id="empe_num" maxlength="20" class="span2"/>
	</div>
	<div class="span3">
		<form:errors path="empe_num" cssClass="error"></form:errors>
	</div>
</div>
<div class="row">
	<div class="label span1">
		<s:message code="label.empe.name"/>
	</div>
	<div class="span3">
		<form:input path="empe_name" maxlength="100" class="span3"/>
	</div>
	<div class="span2">
		<form:errors path="empe_name" cssClass="error"></form:errors>
	</div>
</div>
<div class="row">
	<div class="label span1">
		<s:message code="label.group.name"></s:message>
	</div>
	<div class="span3">
		<form:select path="group_cd" items="groupOptions"
			itemLabel="group_name" itemValue="group_cd"
		></form:select>
	</div>
	<div class="span2">
		<form:errors path="group_cd" cssClass="error"></form:errors>
	</div>
</div>
<div class="row">
	<div class="label span1">
		<s:message code="label.mobile"/>
	</div>
	<div class="span3">
		<form:input path="mobile" maxlength="11" class="span3"/>
	</div>
	<div class="span2">
		<form:errors path="mobile" cssClass="error"></form:errors>
	</div>
</div>
<div class="row">
	<div class="label span1">
		<s:message code="label.tel_no"/>
	</div>
	<div class="span3">
		<form:input path="tel_no" maxlength="50" class="span3"/>
	</div>
	<div class="span2">
		<form:errors path="tel_no" cssClass="error"></form:errors>
	</div>
</div>
<div class="row">
	<div class="label span1">
		<s:message code="label.email"/>
	</div>
	<div class="span3">
		<form:input path="email" maxlength="50" class="span3"/>
	</div>
	<div class="span2">
		<form:errors path="email" cssClass="error"></form:errors>
	</div>
</div>
<div class="row">
	<div class="label span2">
		<s:message code="label.dflt_atndnc_sts_id"/>
	</div>
	<div class="span3">
		<form:select path="dflt_atndnc_sts_id" items="atndcStsOptions"
			itemLabel="atndnc_name" itemValue="id" cssClass="span2"
		>	
		</form:select>
	</div>
	<div class="span2">
		<form:errors path="dflt_atndnc_sts_id" cssClass="error"></form:errors>
	</div>
</div>