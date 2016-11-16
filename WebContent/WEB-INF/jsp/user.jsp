<%@ include file="skeleton/tags.jsp"%>
<style>
img {
    max-width:256px;
    max-height:256px;
    width:auto;
    height:auto;
}
#borderimg2 { 
    border: 5px solid transparent;
    padding: 0px;
    -webkit-border-image: url(http://www.w3.org/TR/css3-background/border.png) 30 stretch; /* Safari 3.1-5 */
    -o-border-image: url(http://www.w3.org/TR/css3-background/border.png) 30 stretch; /* Opera 11-12.1 */
    border-image: url(http://www.w3.org/TR/css3-background/border.png) 30 stretch;
}
</style>
<!-- autocomplete -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
   <script>
  $(function() {
    var availableTags = [
      "Asp",
      "BASIC",
      "C",
      "C++",
      "Erlang",
      "Fortran",
      "Groovy",
      "Haskell",
      "Java",
      "JavaScript",
      "Perl",
      "PHP",
      "Python",
      "Ruby",
      "Scala",
      "Oracle Database",
      "MySql"
    ];
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#tags" )
      // don't navigate away from the field on tab when selecting an item
      .bind( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
        minLength: 0,
        source: function( request, response ) {
          // delegate back to autocomplete, but extract the last term
          response( $.ui.autocomplete.filter(
            availableTags, extractLast( request.term ) ) );
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
          terms.push( "" );
          this.value = terms.join( ", " );
          return false;
        }
      });
  });
  </script>
  <!-- autocomplete -->
<sb:head />
<%-- <sx:head />
<sj:head /> --%>
<%-- <sj:head jqueryui="true" /> --%>
<div class="container">
	<%-- <s:actionerror theme="bootstrap" />
	<s:actionmessage theme="bootstrap" />
	<s:fielderror theme="bootstrap" /> --%>
	<div class="col-sm-8">
		<s:form action="userModify" namespace="/" method="post"
			elementCssClass="col-sm-5" theme="bootstrap"
			cssClass="form-horizontal" label="Edit">
			<s:label>User name:</s:label>
			<s:textfield name="user.name" placeholder="Firstname" readonly="true" />
			<s:label>User surname:</s:label>
			<s:textfield name="user.surname" placeholder="Lastname" readonly="true" />
			<s:label>User email:</s:label>
			<s:textfield name="user.email" placeholder="Email" required="true" readonly="true" />
			<s:label>User birthday:</s:label>
			<sx:datetimepicker name="user.birthday" displayFormat="dd-MMM-yyyy"  disabled="true"/> </br></br>
			<s:label>Account type:</s:label>
			<s:textfield name="user.accType" placeholder="account type" disabled="true" />
			<s:label>Skills:</s:label>
			<s:if test="%{editMode}">
				<div class="ui-widget">
					<input id="tags" name="user.skills" value="${user.skills}" size="28">
				</div>
			</s:if>
			<s:if test="%{!editMode}">
				
				<s:textfield name="user.skills" placeholder="Skills" readonly="%{!editMode}" />
			</s:if>
			
			<%-- <s:date name="project.startDate" format="dd/MM/yyyy" /> --%>
			<s:label>Phone number:</s:label>
			<s:textfield name="user.phone" placeholder="Phone" readonly="%{!editMode}" />
			<s:label>Skype address:</s:label>
			<s:textfield name="user.skype" placeholder="Skype address" readonly="%{!editMode}" />
			
			</br>
			</br>
			<s:if test="%{editMode}">
				<s:submit value="Save" name="actionName" cssClass="btn btn-danger" />
			</s:if>
			<s:if test="%{!editMode}">
				<s:submit value="Modify" name="actionName" cssClass="btn btn-danger" />
			</s:if>
		</s:form>
	</div>
	<div class="col-sm-4">
		<s:form action="userChangePassword" namespace="/" method="post"
			elementCssClass="col-sm-5" theme="bootstrap"
			cssClass="form-horizontal" label="Change Password">

			<s:password name="user.password" placeholder="Password"
				required="true" />

			<s:submit value="Change" name="actionName" cssClass="btn btn-danger" />

		</s:form></br></br>
		
		<c:if test="${user.logo!=null}">
			<img  id="borderimg2" src="data:image/jpeg;base64,<s:property value="user.image" />">
		</c:if>
		</br></br>
		<s:label>Send logo:</s:label>
		<s:form  namespace="/" method="POST" enctype="multipart/form-data" theme="bootstrap"
			cssClass="form-horizontal" elementCssClass="col-sm-5">
			<s:file name="fileUpload" size="" />
			<s:submit cssClass="btn btn-primary" value="Send" action="userSendFile" />
		</s:form>
	</div>
</div>
