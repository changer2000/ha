<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<div class="row span12">
	<div class="label span1">
		<form:label path="id"><s:message code="label.id"/></form:label>
	</div>
	<div class="span1">
		<form:input path="id" cssClass="span1"/><form:errors path="id" cssClass="error"></form:errors>
	</div>
	<div class="label span1">
		<form:label path="atndnc_name"><s:message code="label.attendance.status.name"/></form:label>
	</div>
	<div class="span3">
		<form:input path="atndnc_name" cssClass="span3"/><form:errors path="atndnc_name" cssClass="error"></form:errors>
	</div>
	<div class="label span1">
		<form:label path="in_shanghai"><s:message code="label.in.shanghai"/></form:label>
	</div>
	<div class="span4" align="left">
		<form:radiobutton path="in_shanghai" value="1"/><s:message code="label.yes"/>
		<form:radiobutton path="in_shanghai" value="0"/><s:message code="label.no"/>
	</div>
</div>