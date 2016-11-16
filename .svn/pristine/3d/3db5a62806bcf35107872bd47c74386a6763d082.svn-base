<%-- <%@taglib uri="/struts-tags" prefix="s"%>

<a href="<s:url action="friendsLink"/>" >Friends</a><br>
<a href="<s:url action="officeLink"/>" >The Office</a><br>
<a href="<s:url action="registerLink"/>" >Friends</a><br> --%>
<%@ include file="tags.jsp"%>


<ul class="nav nav-sidebar">
	<c:forEach var="menuItem" items="${requestScope.menu}">
		<c:url var="url" value="${menuItem.url}" />
		<li role="presentation"><a href="${url}">${menuItem.caption}</a></li>
	</c:forEach>
</ul>
<br>
<c:if test="${login}">
	<div class="panel-group" id="accordion">
		<s:iterator value="project.tasks" >
			<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapse${id}">${nameEscaped}</a>&nbsp;<a href="taskDetails?id=${id}"><span style="float: right;" class="glyphicon glyphicon-search"></span></a> 
				</h4>
			</div>
			<div id="collapse${id}" class="panel-collapse collapse">
				<div class="panel-body">${descriptionEscaped}</div>
			</div>
		</div>
		</s:iterator>
		
		<s:iterator value="task.project.tasks" >
			<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapse${id}">${nameEscaped}</a>&nbsp;<a href="taskDetails?id=${id}"><span style="float: right;" class="glyphicon glyphicon-search"></span></a> 
				</h4>
			</div>
			<div id="collapse${id}" class="panel-collapse collapse">
				<div class="panel-body">${descriptionEscaped}</div>
			</div>
		</div>
		</s:iterator>
		<!-- 
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapse1">Task 1</a>
				</h4>
			</div>
			<div id="collapse1" class="panel-collapse collapse in">
				<div class="panel-body">Lorem ipsum dolor sit amet,
					consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
					labore et dolore magna aliqua. Ut enim ad minim veniam, quis
					nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat.</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapse2">Task 2</a>
				</h4>
			</div>
			<div id="collapse2" class="panel-collapse collapse">
				<div class="panel-body">Lorem ipsum dolor sit amet,
					consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
					labore et dolore magna aliqua. Ut enim ad minim veniam, quis
					nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat.</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapse3">Task 3</a>
				</h4>
			</div>
			<div id="collapse3" class="panel-collapse collapse">
				<div class="panel-body">Lorem ipsum dolor sit amet,
					consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
					labore et dolore magna aliqua. Ut enim ad minim veniam, quis
					nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat.</div>
			</div>
		</div> -->
	</div>
</c:if>

