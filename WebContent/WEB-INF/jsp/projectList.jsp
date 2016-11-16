<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="skeleton/tags.jsp"%>


 <sb:head />
 <sj:head jqueryui="true" /> 
 <div class="container">
	<s:actionerror theme="bootstrap" />
	<s:actionmessage theme="bootstrap" />
	<s:fielderror theme="bootstrap" />
	<table class="table table-striped" >
		<tr>
		   <th> Name </th>
		   <th> Description </th>
		   <th> Start date </th>
		   <th> Project PM </th>
		</tr>
		 <%-- <s:url action="projectShow"> --%>
		<s:iterator value="projectList"  >
		<tr>
		   <td width="5%"> 
				<s:url action="projectDetails" var="urlTag">
			    	<s:param name="id">${id}</s:param>
				</s:url>
				<a href="<s:property value="#urlTag" />" ><s:property value="name"/> </a>
			</td>
		
		   <td width="25%" style=""> ${desc}&nbsp;</td>
		   
		   <td width="5%"> <s:date name="startDate" format="dd-MM-yyyy" /> </td>
		   <td width="5%"> <s:property value="projectPM.email"/> </td>
		</tr>
		
	
	   		<%--  <s:textfield label="Name" value="%{#element.name}"></s:textfield>
	   		 <s:textfield label="Description" value="%{#element.desc}"></s:textfield>
	   		 <s:textfield label="Name" value="%{#element.startDate}"></s:textfield>
	   		 <s:textfield label="Name" value="%{#element.projectPM}"></s:textfield>
	   		 </br> --%>
		</s:iterator>
	</table>
</br></br></br>
</div> 
