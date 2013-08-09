<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<div id="navbar-example" class="navbar navbar-static">
	<div class="navbar-inner" style="padding-left:0px;padding-right:0px;">
	    <div class="container" style="width:650px;margin-right:5px;margin-left:5px;height:100%;float:left;" align="left">
	      <!--  <a class="brand pull-right" href="#">Welcome </a> -->
	      <ul class="nav" role="navigation">
		      <c:if test="${menu!=null}">
		      	<c:forEach items="${menu.menuList}" var="menuItem" varStatus="status">
		      		<li class="dropdown">
		      			<a id="drop<c:out value="${status.count}"/>" 
		      				href="<c:out value="${menuItem.url}"/>" 
		      				role="button" class="dropdown-toggle" data-toggle="dropdown">
		      					<fmt:message key="${menuItem.menuName}"/> <b class="caret"></b>
		      			</a>
		      			<c:if test="${menuItem.subMenuList!=null}">
			      			<ul class="dropdown-menu" role="menu" aria-labelledby="drop<c:out value="${status.count}"/>">
			      				<c:forEach items="${menuItem.subMenuList}" var="subMenuItem" varStatus="subStatus">
			      					<li role="presentation">
			      						<a role="menuitem" tabindex="-1" 
			      							href="<c:out value="${subMenuItem.url}"/>">
			      							<fmt:message key="${subMenuItem.menuName}"/>
			      						</a>
			      					</li>
			      				</c:forEach>
			      			</ul>
		      			</c:if>
		      		</li>
		      	</c:forEach>
		      </c:if>
	      </ul>
	    </div> 
	    <div class="container" style="width:140px;margin-left:650px;" >
	    	<span style="line-height:40px;vertical-align:middle;" >
	    		<s:message code="label.welcome"/><a href="<c:url value="/empeinfo/modify?empe_num="/><c:out value="${userPeer.empe_num}"/>"><c:out value="${userPeer.empe_name}"/></a>
	    		&nbsp;&nbsp;&nbsp;<a href="<c:url value="/logout"/>"><s:message code="label.logout"/></a>
	    	</span>
	    </div>
	</div> 
	       
</div>