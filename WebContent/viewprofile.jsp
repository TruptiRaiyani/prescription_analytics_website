<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>View Profile</title>
<%@ include file="style_js.jsp" %>
</head>

<body class="body">
<%
		HttpSession usersession = request.getSession(false);
		String userName = (String) usersession.getAttribute("userName");
		String userEmail = (String) usersession.getAttribute("userEmail");
		String Address = (String) usersession.getAttribute("useraddress");
		String SQue = (String) usersession.getAttribute("usersecurityqn");
		String SAns = (String) usersession.getAttribute("usersecurityans");
		String phno = (String) usersession.getAttribute("userphno");
	%>
<%@ include file="header.jsp" %>


	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form class="form-horizontal" id="" method="post"
					action="/prescription_analytics/UserServlet">
					<input type="hidden" name="action" value="home"></input>
					<div class="form-group">

					<div class="col-sm-2"> User Name :</div>	<div class="col-sm-6"><%=userName %></div>
					</div>
					<div class="form-group">

						<div class="col-sm-2"> Email ID :</div>	<div class="col-sm-6"><%=userEmail %></div>
					</div>
					<div class="form-group">

						<div class="col-sm-2"> Address :</div>	<div class="col-sm-6"><%=Address %></div>
					</div>
				<!-- 	<div class="form-group">

						<div class="col-sm-4"></div>	<div class="col-sm-6"><%=SQue %></div>
					</div>
					<div class="form-group">

						<div class="col-sm-4"> Security Answer :</div>	<div class="col-sm-6"><%=SAns %></div>
					</div> -->
					<div class="form-group">

						<div class="col-sm-2">Phone Number :</div>	<div class="col-sm-6"><%=phno %></div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							
							
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