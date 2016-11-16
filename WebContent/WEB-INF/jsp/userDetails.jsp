<%@ include file="skeleton/tags.jsp"%>
<style>
img {
	max-width: 256px;
	max-height: 256px;
	width: auto;
	height: auto;
}

#borderimg2 {
	border: 5px solid transparent;
	padding: 0px;
	-webkit-border-image:
		url(http://www.w3.org/TR/css3-background/border.png) 30 stretch;
	/* Safari 3.1-5 */
	-o-border-image: url(http://www.w3.org/TR/css3-background/border.png) 30
		stretch; /* Opera 11-12.1 */
	border-image: url(http://www.w3.org/TR/css3-background/border.png) 30
		stretch;
}

th, td {
	border-bottom: 1px solid #ddd;
}
</style>
<sb:head />
<%-- <sx:head />
<sj:head /> --%>
<sj:head jqueryui="true" />
<div class="container">
	<div class="col-sm-8">
		<h2>${user.name} ${user.surname}</h2>
		</br>
		<table>
			<tr>
				<td>
					<h4>Email:</h4>
				</td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td>
					<h4>Birthday:</h4>
				</td>
				<td><s:date name="user.birthday" format="dd/MM/yyyy" /></td>
			</tr>
			<tr>
				<td>
					<h4>Account type:</h4>
				</td>
				<td>${user.accType}</td>
			</tr>
			<tr>
				<td>
					<h4>Phone number:</h4>
				</td>
				<td>${user.phone}</td>
			</tr>
			<tr>
				<td>
					<h4>Skype address:</h4>
				</td>
				<td>${user.skype}</td>
			</tr>
			<tr>
				<td>
					<h4>Skills:</h4>
				</td>
				<td>${user.skills}</td>
			</tr>
		</table>
		<br> <br>
		<div>
			<table class="table table-striped">
				<tr>
					<th>Projects</th>
				</tr>
				<s:iterator value="projectList">
					<tr>
						<td width="5%"><s:url action="projectDetails" var="urlTag">
								<s:param name="id">${id}</s:param>
							</s:url> <a href="<s:property value="#urlTag" />"><s:property
									value="name" /> </a></td>
					</tr>
				</s:iterator>
			</table>
		</div>


	</div>
	<div class="col-sm-4">
		<c:if test="${user.logo!=null}">
			<img id="borderimg2"
				src="data:image/jpeg;base64,<s:property value="user.image" />">
		</c:if>
	</div>


</div>
