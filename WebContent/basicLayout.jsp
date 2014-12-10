<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Home</title>
<%@ include file="style_js.jsp" %>
</head>

<body class="body">

<%@ include file="header.jsp" %>


	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form class="form-horizontal" id="" method="post"
					action="/prescription_analytics/UserServlet">
					<input type="hidden" name="action" value="home"></input>
				</form>
			</article>
		</div>
	</div>
	

	<%@ include file="footer.jsp" %>

</body>
</html>