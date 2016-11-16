<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="skeleton/tags.jsp"%>
<script src="//cdn.ckeditor.com/4.5.9/full/ckeditor.js"></script>
<style>
.class1{
    width: 950px;
    border: solid 1px #C9C9C9;
    background-color: #EDEDED;
    padding: 5px;
}
.class2{
    width: 950px;
    border: solid 1px #C9C9C9;
    padding: 5px;
    
}
</style>

 <sb:head />
 <sj:head jqueryui="true" /> 
 <div class="container" >
	<s:actionerror theme="bootstrap" />
	<s:actionmessage theme="bootstrap" />
	<s:fielderror theme="bootstrap" />
	
	<s:form  method="post" elementCssClass="col-sm-10"
		theme="bootstrap" cssClass="form-horizontal" label="PROJECT">
		<b>Title</b>
		<s:textfield name="project.name" placeholder="Project title" required="true"
			disabled="%{!editMode}" />
		
		<b>Description</b>	
		<s:if test="%{!editMode}">
			 <div class="class1"> 
				<textarea  name="project.desc" id="editor1" disabled="disabled" >${project.desc}</textarea>
				<script>
		  			CKEDITOR.disableAutoInline = true;
				    CKEDITOR.inline( 'editor1' );
				</script>
			</div> 
		</s:if>
		<s:if test="%{editMode}">
			 <div class="class2"> 
				<textarea  name="project.desc" id="editor1" >${project.desc}</textarea>
				<script>
		  			CKEDITOR.disableAutoInline = true;
				    CKEDITOR.inline( 'editor1' );
				</script>
			</div> 
		</s:if>
		 <!-- <script>CKEDITOR.replace( 'editor1' );	</script>	 -->
         </br>   
        <b>Project PM:</b>    
	    <s:select  title="Project PM"
		headerKey="-1" headerValue="-- Select Project PM --"
		list="usersPM" 
		name="project.projectPMid"  disabled="%{!editMode}"
		/> 
		
		<s:label><b>Project start date:</b></s:label>	 
		<sx:datetimepicker name="project.startDate" displayFormat="dd-MMM-yyyy" 
				requiredLabel="true" disabled="%{!editMode}" />
			</br>	</br>	</br>
 <c:choose>
   <c:when test="${editMode && project.id==null}">
  		 <s:submit value="Create"  method="Create" cssClass="btn btn-danger" />
   </c:when>
</c:choose>	
</s:form>
<c:if test="${!editMode}">
	<a href="projectDetails?id=${project.id}" ><button>Go to project</button></a>
</c:if>
   		
</div>

