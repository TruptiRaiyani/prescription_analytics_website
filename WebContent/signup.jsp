<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Sign Up</title>
<%@ include file="style_js.jsp"%>
<script src="js/SignUpScript.js"></script>
<script>
	$('document').ready(function() {

		var msg = '${Message}';
		if (msg == '') {
			$('#lblError').hide();
		}

	});
</script>
</head>
<body class="body">
	<%
		String message = (String) request.getAttribute("Message");
	%>
	<header class="mainheader">

		<img class="img-responsive" src="images/logo2.png">


	</header>

	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form name="signUpForm" id="signUpForm"
					action="/prescription_analytics/UserServlet" method="post"
					class="form-horizontal">
					<input type="hidden" name="action" value="signup">
					<div class="form-group">

						<div class="col-sm-10">
							<label id="lblError" style="font-size: 20px; color: red;"><%=message%></label>
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="text" name="txtusername" id="txtusername"
								required="required" placeholder="FirstName    LastName">
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="email" name="txtemail" id="txtemail"
								required="required" placeholder="Email">
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="password" name="txtpwd" id="txtpwd"
								required="required" placeholder="Password">
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="password" name="txtcpwd" id="txtcpwd"
								required="required" placeholder="Confirm Password">
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="text" name="txtaddress" id="txtaddress"
								required="required"
								placeholder="Address (Street City State Zipcode Country)">
						</div>
					</div>

					<div class="form-group">

						<div class="col-sm-10">
							<input type="text" name="txtphno" id="txtphno"
								required="required" placeholder="Phone Number">
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<select id="ddlQue" name="ddlQue">
								<option value="selected" selected="selected">Security
									Question</option>
								<option value="What is your hometown?">What is your
									hometown?</option>
								<option value="what is your favorite book?">what is
									your favorite book?</option>

							</select>
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="text" name="txtans" id="txtans" required="required"
								placeholder="Security Answer">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" name="Submit" value="Submit">
							<button type="button" name="btnSignUpReset" id="btnSignUpReset">Reset</button>
							<button type="button"
								onclick="javascript:window.location.href='signin.jsp'">Cancel</button>
						</div>
					</div>
				</form>
			</article>

		</div>


	</div>



	<%@ include file="footer.jsp"%>
</body>
</html>