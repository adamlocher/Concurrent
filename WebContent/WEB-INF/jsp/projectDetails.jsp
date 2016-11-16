<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="skeleton/tags.jsp"%>
<script src="//cdn.ckeditor.com/4.5.9/full/ckeditor.js"></script>
<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<style>
.class1 {
	width: 465px;
	border: solid 1px #C9C9C9;
	background-color: #EDEDED;
	padding: 5px;
}

.class2 {
	width: 465px;
	border: solid 1px #C9C9C9;
	padding: 5px;
}

div.demo {
	word-wrap: break-word;
}

.tabs {
	width: 100%;
	display: inline-block;
}

.tab-links:after {
	display: block;
	clear: both;
	content: '';
}

.tab-links li {
	margin: 0px 5px;
	float: left;
	list-style: none;
}

.tab-links a {
	padding: 9px 15px;
	display: inline-block;
	background: #7FB5DA;
	font-size: 16px;
	font-weight: 600;
	color: #4c4c4c;
	transition: all linear 0.15s;
}

.tab-links a:hover {
	background: #a7cce5;
	text-decoration: none;
}

li.active a, li.active a:hover {
	background: #fff;
	color: #4c4c4c;
}

/*----- Content of Tabs -----*/
.tab-content {
	padding: 15px;
	background: #fff;
}

.tab {
	display: none;
}

.devForm {
	width: 400px;
}
</style>
<script type="text/javascript">
	var websocket = new WebSocket(
			"ws://localhost:8080/Concurrent/chat/chatroom?${userContext.email}");

	websocket.onmessage = function processMessage(message) {
		var jsonData = JSON.parse(message.data);
		if (jsonData.message != null)
			messagesTextArea.value += jsonData.message + "\n";
	}

	function sendMessage() {
		if (messageText.value != "") {
			websocket.send(messageText.value);
			messageText.value = "";
			document.getElementById("messageText").focus();
		}
	}
</script>
<script>
	jQuery(document).ready(
			function() {
				jQuery('.tabs .tab-links a').on(
						'click',
						function(e) {
							var currentAttrValue = jQuery(this).attr('href');

							// Show/Hide Tabs
							jQuery('.tabs ' + currentAttrValue).show()
									.siblings().hide();

							// Change/remove current tab to active
							jQuery(this).parent('li').addClass('active')
									.siblings().removeClass('active');

							e.preventDefault();
						});
			});
</script>
</head>
<body>

	<div class="tabs">
		<ul class="tab-links">
			<li><a href="#tab1">Project info</a></li>
			<li><a href="#tab2">Project developers</a></li>
			<li><a href="#tab3">Chat</a></li>
		</ul>

		<div class="tab-content">
			<div id="tab1" class="tab active">
				<s:if test="%{!editMode}">
					<h3>Project name</h3>
							${project.name}
							<h3>Description</h3>
							${project.desc}
							<h3>Start date</h3>
					<s:date name="project.startDate" format="dd/MM/yyyy" />
					<h3>Project manager</h3>
					<a href="userDetails?id=${project.projectPM.id}">${project.projectPM.name}
						${project.projectPM.surname}</a>

					</br>

					</br>

					<div class="btn-group">
						<s:if
							test="userContext.permission=='Admin' || userContext.permission=='PM'">
							<a href="projectModify"><button type="button"
									class="btn btn-success">Modify</button></a>
						</s:if>
						<a href="taskAdd?id=${project.id}"><button type="button"
								class="btn btn-success">Add Task</button></a>
					</div>
					<br>
					<br>

				</s:if>

				<s:actionerror theme="bootstrap" />
				<s:actionmessage theme="bootstrap" />
				<s:fielderror theme="bootstrap" />
				<s:if test="%{editMode}">
					<s:form method="post" elementCssClass="col-sm-5" theme="bootstrap"
						cssClass="form-horizontal" label="PROJECT DETAILS">
						<s:label>
							<b>Project title:</b>
						</s:label>
						<s:textfield name="project.name" placeholder="Project title"
							required="true" disabled="%{!editMode}" />

						<b>Description</b>
						<div class="class2">
							<textarea name="project.desc" id="editor1">${project.desc}</textarea>
							<script>
								CKEDITOR.disableAutoInline = true; // CKEDITOR.inline('editor1');
							</script>
						</div>
						</br>
						</br>
						<s:label>
							<b>Project start date:</b>
						</s:label>
						<sx:datetimepicker name="project.startDate"
							displayFormat="dd-MMM-yyyy" requiredLabel="true"
							disabled="%{!editMode}" />
						</br>
						</br>
						<s:label>
							<b>Project PM:</b>
						</s:label>

						<s:select title="Project PM" list="usersPM"
							name="project.projectPMid" disabled="%{!editMode}" />

						</br>
						</br>
						</br>
						<s:submit value="Save" action="projectSave"
							cssClass="btn btn-danger" />

					</s:form>
				</s:if>
			</div>

			<div id="tab2" class="tab">
				<h3>Developers</h3>
				<s:iterator value="projectDevelopers">
					<a href="userDetails?id=${id}">${name} ${surname}</a>
					<br>
				</s:iterator>
				<br>
				<s:form theme="bootstrap" class="devForm" cssClass="form-horizontal"
					label="Add developer" elementCssClass="col-sm-5">
					<s:select list="developers" listValue="toString" listKey="id"
						name="id" />
					<s:submit value="Add" cssClass="btn btn-danger"
						action="projectAdddeveloper" />
				</s:form>
			</div>

			<div id="tab3" class="tab">
				<br> <br>
				<div class="col-sm-5">
					<textarea class="form-control" rows="10" cols="45"
						readonly="readonly" id="messagesTextArea"></textarea>

					<input type="text" class="form-control" id="messageText"
						placeholder="Your message..."
						onkeydown="if (event.keyCode == 13) {document.getElementById('btn').click(); return false;}"></input>
					<s:submit id="btn" theme="bootstrap" cssClass="btn btn-primary"
						value="Send" onclick="sendMessage();" />
				</div>
			</div>

		</div>
	</div>


</body>