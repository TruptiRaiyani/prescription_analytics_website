<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Forgot Password</title>
<%@ include file="style_js.jsp"%>
<script>

		function check() {
			
			var flag= false;
 			var ans = '${securityans}';
 		
	if(document.getElementById("txtans").value == ans)
		{
		flag = true;
		}
	else 
		{
		alert('Security answer is incorrect.');
		flag = false;
		}
	return flag;
	}
</script>
</head>
<body class="body">
<%String securityqn=(String)request.getAttribute("securityqn"); %>
<%String securityans=(String)request.getAttribute("securityans"); %>
	<header class="mainheader">

		<img class="img-responsive" src="images/logo2.png"> 


	</header>

	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form name="signUpForm" id="signUpForm"
					action="/prescription_analytics/UserServlet" method="post"
					class="form-horizontal" onsubmit="return check()">
					<input type="hidden" name="action" value="updatepassword">

				
					<div class="form-group">

						<div class="col-sm-10"><%=securityqn %></div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
						<input type="text" name="txtans" id="txtans"
							required="required"	placeholder="Security Answer">
						</div>
					</div>
					<div class="form-group">

						<div class="col-sm-10">
							<input type="password" name="txtpassword" id="txtpassword"
						 required="required" placeholder="New Password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" name="Submit" value="Submit" >

							
						</div>
					</div>
				</form>
			</article>

		</div>


	</div>



	<%@ include file="footer.jsp"%>
</body>
</html>