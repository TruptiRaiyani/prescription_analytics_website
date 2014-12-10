<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Forgot Password</title>
<%@ include file="style_js.jsp"%>
</head>
<body class="body">

	<header class="mainheader">

		<img src="images/logo.png">

	</header>

	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form name="signUpForm" id="signUpForm"
					action="/prescription_analytics/UserServlet" method="post"
					class="form-horizontal">
					<input type="hidden" name="action" value="forgotpassword">

					<div class="form-group">

						<div class="col-sm-10">
							<input type="text" name="txtEmail" id="txtEmail"
								required="required" placeholder="Enter Email ID">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" name="Submit" value="Submit">

							
						</div>
					</div>
				</form>
			</article>

		</div>


	</div>



	<%@ include file="footer.jsp"%>
</body>
</html>