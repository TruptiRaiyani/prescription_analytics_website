<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"  %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Sign In</title>
<%@ include file="style_js.jsp" %>
<script>
$('document').ready(function(){

	var msg = '${Message}';
	if(msg == '')
		{
		$('#lblError').hide();
		}
	$('#ahome').hide();
	
});
</script>
</head>
<body class="body">
<%String message=(String)request.getAttribute("Message"); %>
	<header class="mainheader">

		<img class="img-responsive" src="images/logo2.png"> 

	</header>

	<div class="maincontent">
		<div class="signincontent" >
			<article class="topcontent" >
				<form class="form-horizontal" id="fmsignin" method="post" action="/prescription_analytics/UserServlet">
				<input type="hidden" name="action" value="signIN"></input>
					<div class="form-group">

						<div class="col-sm-10">
						<label id="lblError"   style="font-size:20px;color:red;"><%=message %></label></div></div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="email" name="txtemail" id="txtemail"
							value="raiyani.trupti@gmail.com" title="raiyani.trupti@gmail.com"	required="required" placeholder="Email">
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="password" name="txtpassword" id="txtpassword"
								 required="required" placeholder="Password">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" name="SIGNIN" value="SIGN IN"></input>
						</div>
					</div>

					<div class="form-group">

						<a href="signup.jsp" title="SignUp">SignUp </a> here if you are
						not registered user.

					</div>
					<div class="form-group">

						<a href="forgotpassword.jsp" title="forgotPwd">Forgot
							Password</a>

					</div>
				</form>
			</article>

		</div>


	</div>
	<aside class="sidebar">
		<article>
		<center><h3>Features  </h3></center><br/><br/>
			<p> 
				<marquee direction="up" height="112" scrollamount="3">
				<ul>
					<li>Fraud Detection System</li>
					<li>Disease Propagation Analysis</li>
					<li>Vaccine Recommendation System</li>
					<li>Twitter and WHO Health Data Analytics System</li>
				</ul>
				</marquee>
			</p>
		</article>
	</aside>
<%@ include file="footer.jsp" %>
</body>
</html>