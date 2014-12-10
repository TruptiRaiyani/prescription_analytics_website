<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Forgot Password</title>
<%@ include file="style_js.jsp"%>
<script>
$('document').ready(function(){
	
	var msg = '${Message}';
	if(msg == '')
		{
		$('#lblError').hide();
		}

});
</script>
</head>
<body class="body">
<%String message=(String)request.getAttribute("Message"); %>
	<header class="mainheader">

	<img class="img-responsive" src="images/logo2.png"> 


	</header>
<div >
		
		
			
	</div>
	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form name="signUpForm" id="signUpForm"
					action="/prescription_analytics/UserServlet" method="post"
					class="form-horizontal">
					<input type="hidden" name="action" value="forgotpassword">
					<div class="form-group">

						<div class="col-sm-10">
						<label id="lblError"   style="font-size:20px;color:red;"><%=message %></label></div></div>
					<div class="form-group">

						<div class="col-sm-10">
							<p>Enter your email address.</p>
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="text" name="txtEmail" id="txtEmail"
								required="required" placeholder="Enter Email ID">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" name="Submit" value="Submit">

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