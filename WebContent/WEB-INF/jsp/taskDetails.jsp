<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="skeleton/tags.jsp"%>
<script src="//cdn.ckeditor.com/4.5.9/full/ckeditor.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<!-- dropdown -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script> -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
		var elem = document.getElementById('demo');
		elem.scrollTop = elem.scrollHeight;
	});
	$(document).ready(function() {
		var elem = document.getElementById('demo2');
		elem.scrollTop = elem.scrollHeight;
	});
	$(function() {
		$("#tabs").tabs();
	});
</script>
<style>
div.demo {
	word-wrap: break-word;
}
</style>
<sb:head />

<div class="col-sm-8">
	<s:form method="post" theme="bootstrap" cssClass="form-horizontal">
		<div class="row">
			<a href="projectDetails?id=${task.projectId}"><span
				class="glyphicon glyphicon-arrow-left">BACK</span></a></br> </br>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<b>Priority:</b>
				<s:select headerKey="-1" headerValue="-- Select priority --"
					list="#{'Blocker':'Blocker','Critical':'Critical', 'Major':'Major', 'Normal':'Normal', 'Minor':'Minor'}"
					name="task.priority" disabled="%{task.status=='Closed'}" />
			</div>
			<div class="col-sm-3">
				<b>Component:</b>
				<s:select headerKey="-1" headerValue="-- Select component --"
					list="#{'EJB':'EJB','WEB':'WEB', 'DATABASE':'DATABASE', 'ANALYSIS':'ANALYSIS'}"
					name="task.component" disabled="%{task.status=='Closed'}" />
			</div>
			<div class="col-sm-3">
				<b>Type:</b>
				<s:select headerKey="-1" headerValue="-- Select type --"
					list="#{'Bug':'Bug','Improvement':'Improvement', 'Task':'Task'}"
					name="task.type" disabled="%{task.status=='Closed'}" />
			</div>
			<div class="col-sm-3">
				<b>Assigned user:</b>
				<s:select title="Assigned user" headerKey="-1"
					headerValue="-- Select user --" list="usersDEVTask"
					name="task.assignedUserId" disabled="%{task.status=='Closed'}" />
			</div>
		</div>
		<hr>
		<h3 style="color: grey;">Title</h3>
		<c:if test="${task.status=='Closed'}">
			<textarea readonly="readonly" name="task.name" id="editor1">${task.name}</textarea>
		</c:if>
		<c:if test="${task.status!='Closed'}">
			<textarea name="task.name" id="editor1">${task.name}</textarea>
		</c:if>
		<script>
			CKEDITOR.disableAutoInline = true;
			CKEDITOR.inline('editor1');
		</script>

		<h3 style="color: grey;">Description</h3>
		<c:if test="${task.status=='Closed'}">
			<textarea readonly="readonly" name="task.description" id="editor2">${task.description}</textarea>
		</c:if>
		<c:if test="${task.status!='Closed'}">
			<textarea name="task.description" id="editor2">${task.description}</textarea>
		</c:if>
		<script>
			CKEDITOR.disableAutoInline = true;
			CKEDITOR.inline('editor2');
		</script>
		</br>
		<s:submit value="Save" action="taskSave" cssClass="btn btn-success" />
	</s:form>
	<hr>
	<h3 style="color: grey;">Repository</h3>


	<div class="tab-content">

		<s:form action="taskUpload" namespace="/" method="POST"
			enctype="multipart/form-data" theme="bootstrap"
			cssClass="form-horizontal" elementCssClass="col-sm-5">
			<s:file name="fileUpload" size="" />
			<s:submit cssClass="btn btn-primary" value="Send" />
		</s:form>


		<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>File name</th>
						<th>File version</th>
						<th>Creation Date</th>
						<th>Options</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="projectdataList">
						<tr>
							<td><s:url action="download" var="urlTag">
									<s:param name="id">${id}</s:param>
								</s:url> <a href="<s:property value="#urlTag" />"><s:property
										value="fileName" /> </a></td>
							<td><s:property value="fileVersion" /></td>
							<td><s:date name="creationDate" format="dd-MM-yyyy" /></td>
							<td width="20%"><s:url action="preview" var="urlPreview">
									<s:param name="id">${id}</s:param>
								</s:url> <a href="<s:property value="#urlPreview" />" target="_blank"><i
									class="glyphicon glyphicon-search" aria-hidden="true"></i></a> <s:url
									action="taskDelete" var="urlRemove">
									<s:param name="id">${id}</s:param>
								</s:url> <a href="<s:property value="#urlRemove" />"><i
									class="glyphicon glyphicon-trash" aria-hidden="true"></i></a></td>
						</tr>

					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	<br>
	<div id="tabs" style="padding: 0px;">
		<ul>
			<li><a href="#tabs-1">All comments</a></li>
			<li><a href="#tabs-2">Only user comments</a></li>
		</ul>
		<div id="tabs-1">
			<div class="demo" id="demo"
				style="height: 200px; width: 1000px; overflow: auto;">
				<s:iterator value="task.comments">
					<b>${user.name} ${user.surname} </b> &nbsp;&nbsp;&nbsp;&nbsp; <s:date
						name="date" />
					</br>
			 		${content}
			 		<hr>
				</s:iterator>
				</br> </br>
			</div>
		</div>
		<div id="tabs-2">
			<div class="demo" id="demo2"
				style="height: 200px; width: 1000px; overflow: auto;">
				<s:iterator value="task.userComments">
					<b>${user.name} ${user.surname} </b> &nbsp;&nbsp;&nbsp;&nbsp; <s:date
						name="date" />
					</br>
			 		${content}
			 		<hr>
				</s:iterator>
				</br>
			</div>
		</div>
	</div>
	<s:form method="post" theme="bootstrap">
		<s:submit value="Send comment" action="taskSendComment"
			cssClass="btn btn-success btn-md" style="float: right" />
		<div style="overflow: hidden; padding-right: .5em;">
			<s:textfield name="comment.content"></s:textfield>
		</div>
	</s:form>

</div>
<div class="col-sm-4">
	<b>Estimated time:</b> ${task.estimatedTime} h </br> </br> <b>Elapsed time:</b>
	${task.formattedElapsedTime}&nbsp;&nbsp;&nbsp; </br> </br> <b>Status:</b>
	${task.status} </br> </br>
	<c:if test="${task.status=='Open'}">
		<div class="btn-group-vertical">
			<a href="taskClose"><button type="button" class="btn btn-danger btn-sm">Close task</button></a>
			<a href="taskStart"><button type="button" class="btn btn-warning btn-sm">Start	work</button></a> </br> </br>
			<div class="dropdown">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="menu1" data-toggle="dropdown">
					Send to tests <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
					<s:iterator value="testers">
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="taskTest?id=${id}">${name} ${surname}</a></li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</c:if>
	<c:if test="${task.status=='Closed'}">
		<a href="taskReopen">
			<button type="button" class="btn btn-info btn-sm">Reopen
				task</button>
		</a>
	</c:if>
	<c:if test="${task.status=='In progress'}">
		<a href="taskStop">
			<button type="button" class="btn btn-danger btn-sm">Stop
				work</button>
		</a>
	</c:if>
	<c:if test="${task.status=='Verification'}">
		<div class="dropdown">
			<button class="btn btn-default dropdown-toggle" type="button"
				id="menu1" data-toggle="dropdown">
				Stop verification as <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="taskStopVerification?result=Fixed">Fixed</a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="taskStopVerification?result=NotFixed">Not fixed</a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="taskStopVerification?result=Verified">Verified</a></li>
			</ul>
		</div>
	</c:if>


</div>




