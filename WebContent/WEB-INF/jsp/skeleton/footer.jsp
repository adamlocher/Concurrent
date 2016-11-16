<%-- <%@page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.text.*,java.util.*"%>
<head>

</head>
<center>
	<%
		// Page will be auto refresh after 1 seconds
		//response.setIntHeader("Refresh", 60);

		// Get Current Time

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Calendar cal = Calendar.getInstance();
		out.println(dateFormat.format(cal.getTime()));
	%>

</center> --%>