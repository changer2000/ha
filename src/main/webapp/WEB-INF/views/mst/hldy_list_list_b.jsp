<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<br/>
<table id="tablea" border="1" cellspacing="0" cellpadding="0" width="600px">
<thead>
	<tr class="title">
		<th width="20px">&nbsp;</th>
		<th width="40px"><s:message code="label.year"></s:message></th>
		<th width="240px"><s:message code="label.holiday.name"></s:message></th>
		<th width="110px"><s:message code="label.holiday.statistic.start.date"></s:message></th>
		<th width="110px"><s:message code="label.holiday.statistic.end.date"></s:message></th>
		<th><s:message code="label.initial.flag"></s:message></th>
	</tr>
</thead>
</table>
<div style="width:620px;height:300px">
<c:if test="${command.list!=null}">
<table id="tableb" border="1" cellspacing="0" cellpadding="0" width="600px">
	<c:forEach items="${command.list}" var="peer" varStatus="status">
		<tr>
			<td width="20px">
				<input type="checkbox" name="selKey" value="<c:out value="${peer.id}"></c:out>"/>
			</td>
			<td width="40px">
				<c:out value="${peer.hldy_year}"/>
			</td>
			<td width="240px">
				<c:out value="${peer.holidayPeer.name}"/>
			</td>
			<td width="110px">
				<c:out value="${peer.start_dt}"/>
			</td>
			<td width="110px">
				<c:out value="${peer.end_dt}"/>
			</td>
			<td>
				<c:if test="${peer.init_flg eq 1}">
					<s:message code="label.initialized"/>
				</c:if>
				<c:if test="${peer.init_flg != 1}">
					<s:message code="label.uninitialized"/>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</c:if>
</div>