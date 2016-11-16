<%@ include file="skeleton/tags.jsp"%>

<sb:head />

<sj:head jqueryui="true" />
<div class="container">
	<s:form action="registration" namespace="/" method="post"
		elementCssClass="col-sm-5" theme="bootstrap"
		cssClass="form-horizontal" label="Registration">
		<s:textfield name="user.name" placeholder="First name" required="true" />
		<s:textfield name="user.surname" placeholder="Last name"
			required="true" />
		<s:textfield name="user.email" placeholder="Email" required="true" />
		<s:select headerKey="-1" headerValue="-- Select Account Type --"
			list="#{'Admin':'Admin', 'PM':'PM', 'Developer':'Developer', 'Tester':'Tester'}"
			name="user.accType" value="0" />
		<s:password name="user.password" placeholder="Password"
			required="true" />
		<sx:datetimepicker name="user.birthday" displayFormat="dd-MMM-yyyy"
			requiredLabel="true" />
		<br>
		<br>


		<s:submit cssClass="btn btn-danger" value="Register" />
	</s:form>


</div>
