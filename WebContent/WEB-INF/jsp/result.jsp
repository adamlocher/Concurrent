<%@ include file="skeleton/tags.jsp"%>

<s:form action="result" namespace="/" method="POST"
	enctype="multipart/form-data" theme="bootstrap"
	cssClass="form-horizontal" label="Upload File"
	elementCssClass="col-sm-5">

	<s:file name="fileUpload" size="" />

	<s:submit cssClass="btn btn-primary" value="Send" />

</s:form>


File Name :
<s:property value="fileUploadFileName" />
<br>

Content Type :
<s:property value="fileUploadContentType" />
<br>

File :
<s:property value="fileUpload" />
<br>


<div class="container">
	<s:actionerror theme="bootstrap" />
	<s:actionmessage theme="bootstrap" />
	<s:fielderror theme="bootstrap" />
	<table style="width: 100%">
		<tr>
			<th>File Name</th>
			<th>Creation Date</th>
			<th>File</th>
		</tr>

		<s:iterator value="projectdataList">
			<tr>
				<td><s:property value="projectdataList.fileName" /></td>
				<td><s:date name="projectdataList.creationDate"
						format="dd-MM-yyyy" /></td>
				<td><s:property value="projectdataList.file" /></td>
			</tr>

		</s:iterator>
	</table>

</div>