<%@ include file="skeleton/tags.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<div class="container">


	
	<div class="col-sm-8">
	<h4>USERS</h4>
<%-- <s:form  method="post" elementCssClass="col-sm-10" action="approveSave"
		theme="bootstrap" cssClass="form-horizontal" label="Users to approved:"> --%>
		<table class="table table-striped" >
		<tr>
		    <th> </th> 
		   <th> Name </th>
		   <th> Surname </th>
		    <th> Email </th>
		   <th> Account type </th>
		</tr>
		 <s:iterator value="userList">
		 	<s:if test="%{!approved}"> <tr style="color: red;"></s:if>
		 	<s:if test="%{approved}"> <tr></s:if>
		 	   <td>  <a href="userDetails?id=${id}"><span class="glyphicon glyphicon-download"></span></a></td>
			   <td> ${name}  	</td>
				<td>${surname}</td>
			   	<td>${email}</td>
			   <td>${accType}</td>
			 
			</tr>
		</s:iterator>
		</table>
		<%--  <s:submit value="Save"  method="Save" cssClass="btn btn-danger" /> 
</s:form>--%>
<br><br>
<s:if test="%{userContext.permission=='Admin'}">
	<h4>USERS TO APPROVE</h4>
	<s:form action="approveSave" namespace="/">
		<s:checkboxlist label="USERS TO APPROVE" list="usersNotActive" listValue="toString" listKey="id" theme="vertical-checkbox" name="usersId" /> <br>
		<s:submit value="Approve" />
	</s:form>
</s:if>
</div>

</div>

 <br><br><br>
  
	