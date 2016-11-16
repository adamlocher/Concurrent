<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="skeleton/tags.jsp"%>
<script src="//cdn.ckeditor.com/4.5.9/full/ckeditor.js"></script>
<style>
.class1{
    width: 550px;
    border: solid 1px #C9C9C9;
    background-color: #EDEDED;
    padding: 5px;
}
.class2{
    width: 550px;
    border: solid 1px #C9C9C9;
    padding: 5px;
}
</style>
 <sb:head />
 <sj:head jqueryui="true" /> 
<div class="container">
	 <div class="col-sm-8" >
	<%-- 	<s:actionerror theme="bootstrap" />
		<s:actionmessage theme="bootstrap" />
		<s:fielderror theme="bootstrap" /> --%>
		
		<s:form  method="post" 	theme="bootstrap" cssClass="form-horizontal" label="TASK">
			<b>Title</b>
			<s:textfield name="task.name" placeholder="Task title" required="true" disabled="%{!editMode}" />
			
			<b>Description</b>	
			<s:if test="%{!editMode}">
				 <div class="class1"> 
					<textarea  name="task.description" id="editor1" disabled="disabled" >${task.description}</textarea>
					<script>
			  			CKEDITOR.disableAutoInline = true;
					    CKEDITOR.inline( 'editor1' );
					</script>
				</div> 
			</s:if>
			<s:if test="%{editMode}">
				 <div class="class2"> 
					<textarea  name="task.description" id="editor1" >${task.description}</textarea>
					<script>
			  			CKEDITOR.disableAutoInline = true;
					    CKEDITOR.inline( 'editor1' );
					</script>
				</div> 
			</s:if>
			</br>
			
			
			<b>Estimated time:</b> [hh]	
			<s:textfield  name="task.estimatedTime" disabled="%{!editMode}" > </s:textfield>
			
		   <%--  <b>Elapsed time:</b>  [hh:mm]
			<s:textfield  name="task.elapsedTime" disabled="%{!editMode}" > </s:textfield>
			 --%>
			 <s:select 
					headerKey="-1" headerValue="-- Select priority --"
					list="#{'Blocker':'Blocker','Critical':'Critical', 'Major':'Major', 'Normal':'Normal', 'Minor':'Minor'}" 
					name="task.priority"  disabled="%{!editMode}"/> 
			<%-- <s:textfield  name="task.status" disabled="true" > </s:textfield> --%>	
			<s:select 
					headerKey="-1" headerValue="-- Select component --"
					list="#{'EJB':'EJB','WEB':'WEB', 'DATABASE':'DATABASE', 'ANALYSIS':'ANALYSIS'}" 
					name="task.component"  disabled="%{!editMode}"/> 
			
			<s:select 
					headerKey="-1" headerValue="-- Select type --"
					list="#{'Bug':'Bug','Improvement':'Improvement', 'Task':'Task'}" 
					name="task.type"  disabled="%{!editMode}"/> 		
			
			<b>Assigned user:</b>    
		    <s:select  title="Assigned user"
			headerKey="-1" headerValue="-- Select User --"
			list="usersDEVProject" 
			name="task.assignedUserId"  disabled="%{!editMode}"
			/> 
			<s:if test="%{editMode}">
			 	<s:submit value="Create"  action="taskCreate" cssClass="btn btn-danger" />
			 </s:if>
	 </s:form> </br></br>
	<a href="projectDetails?id=${projectId}" ><button>Back to project</button></a>
	<c:if test="${!editMode}">
		<a href="taskDetails?id=${task.id}" >
	   		<button>Go to task</button>
		</a>
	</c:if>
	</div>
	
</div>
</br></br>
