<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Change Password</title>
<%@ include file="style_js.jsp" %>
<script>

		function check() {
			
			var flag= false;
 			var ans = '${usersecurityans}';
 		
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

<%@ include file="header.jsp" %>
	<%
		HttpSession sessionCP = request.getSession(false);
		String securityqn = (String) sessionCP.getAttribute("usersecurityqn");
	%>

<%String securityans=(String)request.getAttribute("usersecurityans"); %>

	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form class="form-horizontal" id="" method="post" onsubmit="return check()"
					action="/prescription_analytics/UserServlet">
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
	

	<%@ include file="footer.jsp" %>

</body>
</html>