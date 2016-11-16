<%@ include file="skeleton/tags.jsp"%>
<sb:head />

<div class="container">
	<s:form action="login" namespace="/" method="post" theme="bootstrap"
		cssClass="form-horizontal" label="Login" elementCssClass="col-sm-5">
		<s:actionerror />
		<s:actionmessage />
		<s:textfield name="user.email" placeholder="Email" required="true" />
		<s:password name="user.password" placeholder="Password"
			required="true" />
		<s:submit cssClass="btn btn-primary" value="login" />
	</s:form>
</div>
