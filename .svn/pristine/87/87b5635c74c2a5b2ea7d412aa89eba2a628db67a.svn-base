<%@ include file="tags.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<button href="#menu-toggle" class="slidebar-toggle" id="menu-toggle">
			<span class="sr-only">Toggle sidebar</span> MENU
		</button>
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>

	</div>

	<div class="navbar-collapse collapse">
		<ul class="nav navbar-nav" style="padding-left: 30px">
			<c:url var="url" value="welcome" />
			<li><a href="${url}"><i class="glyphicon glyphicon-home"
					aria-hidden="true"></i> Main page</a></li>
		</ul>
		<div class="container-fluid">
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${!login }">
					<c:url var="url" value="login" />
					<c:url var="urlReg" value="registration" />
					<li><a href="${urlReg}"><i
							class="glyphicon glyphicon-plus" aria-hidden="true"></i> Register</a></li>
					<li><a href="${url}"><i class="glyphicon glyphicon-log-in"
							aria-hidden="true"></i> Login</a></li>
				</c:if>
				<c:if test="${login }">
					<c:url var="url" value="logOut" />
					<li><a href="${url}"><i
							class="glyphicon glyphicon-log-out" aria-hidden="true"></i>
							Logout</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>

