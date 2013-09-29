<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<br/>
<table id="tablea" border="1" width="800px">
<thead>
	<tr class="title">
		<th width="20px">&nbsp;</th>
		<th width="80px"><s:message code="label.login.empe_num"/></th>
		<th width="150px"><s:message code="label.empe.name"/></th>
		<th width="150px"><s:message code="label.mobile"/></th>
		<th width="150px"><s:message code="label.tel_no"/></th>
		<th width="250px"><s:message code="label.email"/></th>
	</tr>
</thead>
</table>
<div style="width:820px;height:300px">
<table id="tableb" border="1" cellspacing="0" cellpadding="0" width="800px">
<c:if test="${command.userList != null }">
	<c:forEach var="peer" items="${command.userList}" varStatus="status">
		<tr>
			<td width="22px" align="center">
				<input type="checkbox" name="selKey" value="<c:out value="${peer.empe_num}"/>"/>
			</td>
			<td width="80px">
				<c:out value="${peer.empe_num}"/>
			</td>
			<td width="148px">
				<c:out value="${peer.empe_name}"/>
			</td>
			<td width="148px">
				<c:out value="${peer.mobile}"/>
			</td>
			<td width="149px">
				<c:out value="${peer.tel_no }"/>
			</td>
			<td>
				<c:out value="${peer.email}"/>
			</td>
		</tr>
	</c:forEach>
</c:if>
</table>
</div>