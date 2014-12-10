<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Edit Profile</title>
<%@ include file="style_js.jsp" %>
<script>
$('document').ready(function(){
	var q = '${usersecurityqn}';
	
	$('#ddlQue').val(q);
	var msg = '${Message}';
	if(msg == '')
		{
		$('#lblError').hide();
		}

});
</script>
</head>

<body class="body">

<%@ include file="header.jsp" %>
<%
HttpSession usersession = request.getSession(false);
String userName = (String) usersession.getAttribute("userName");
String userEmail = (String) usersession.getAttribute("userEmail");
String Address = (String) usersession.getAttribute("useraddress");
String SQue = (String) usersession.getAttribute("usersecurityqn");
String SAns = (String) usersession.getAttribute("usersecurityans");
String phno = (String) usersession.getAttribute("userphno");
	%>
<%String message=(String)request.getAttribute("Message"); %>

	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form class="form-horizontal" id="" method="post"
					action="/prescription_analytics/UserServlet">
					<input type="hidden" name="action" value="updateUserInfo"></input>
						<div class="form-group">
						<div class="col-sm-10">
						<label id="lblError"   style="font-size:20px;color:green;"><%=message %></label></div></div>
					<div class="form-group">
						<div class="col-sm-2"> User Name :</div>	<div class="col-sm-6">
							<input type="text" name="txtusername" id="txtusername"
						value="<%=userName%>"	required="required"	placeholder="FirstName    LastName">
						</div>
					</div>	
					<div class="form-group">
						<div class="col-sm-2"> Address :</div>	<div class="col-sm-6">
							<input type="text" name="txtaddress" id="txtaddress"
						value="<%=Address%>"	required="required"	placeholder="Address (Street City State Zipcode Country)">
						</div>
					</div>
					<div class="form-group">

					<div class="col-sm-2"> Phone Number :</div>	<div class="col-sm-6">
							<input type="text" name="txtphno" id="txtphno"
						value="<%=phno%>"	required="required"	placeholder="Phone Number">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2"> Security Question :</div>	<div class="col-sm-6">
							<select id="ddlQue" name="ddlQue" >
								<option value="selected" >Security
									Question</option>
								<option value="What is your hometown?">What is your
									hometown?</option>
								<option value="what is your favorite book?">what is
									your favorite book?</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2"> Security Answer :</div>	<div class="col-sm-6">
							<input type="text" name="txtans" id="txtans"
						value="<%=SAns%>"	required="required"	placeholder="Security Answer">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" name="Update" value="Update">						
							<button type="button"
								onclick="javascript:window.location.href='home.jsp'">Cancel</button>
						</div>
					</div>
				</form>
			</article>
		</div>
	</div>
	

	<%@ include file="footer.jsp" %>

</body>
</html>