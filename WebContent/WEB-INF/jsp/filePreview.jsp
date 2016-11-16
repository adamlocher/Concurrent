<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="skeleton/tags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="fileName" /></title>
</head>
<body>

	<s:if
		test="extension == 'jpg' || extension == 'png' || extension == 'gif' || extension == 'jpeg'">
		<img
			src="data:image/<s:property value="extension" />;base64,<s:property value="fileBase64" />">
	</s:if>
	<s:elseif test="extension == 'pdf'">
		<embed
			src="data:application/<s:property value="extension" />;base64,<s:property value="fileBase64" />"
			width="100%" height="2100px">
	</s:elseif>
	<s:else>
		<iframe src="data:text/plain;base64,<s:property value="fileBase64" />"
			width="100%" height="1000px"></iframe>
	</s:else>



</body>
</html>