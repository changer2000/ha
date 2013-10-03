<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<div class="row">
<div class="row offset4">
	<div class="span1 label"><s:message code="label.login.empe_num"/></div>
	<div class="span3"><form:input path="empe_num" length="50" readonly="true"/></div>
</div>
<div class="row offset4">
	<div class="span1 label"><s:message code="label.empe.name"/></div>
	<div class="span3"><form:input path="empe_name" length="100" readonly="true"/></div>
</div>
<div class="row offset4">
	<div class="span1 label"><s:message code="label.old.password"/></div>
	<div class="span3"><form:password path="old_pwd" length="8"/></div>
	<div class="span2"><form:errors path="old_pwd" cssClass="error"/></div>
</div>
<div class="row offset4">
	<div class="span1 label"><s:message code="label.new.password"/></div>
	<div class="span3"><form:password path="new_pwd" length="8"/></div>
	<div class="span2"><form:errors path="new_pwd" cssClass="error"/></div>
</div>
<div class="row offset4">
	<div class="span1 label"><s:message code="label.new.password.again"/></div>
	<div class="span3"><form:password path="new_pwd_again" length="8"/></div>
	<div class="span2"><form:errors path="new_pwd_again" cssClass="error"/></div>
</div>
</div>