<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Home</title>
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
	<%@ include file="header.jsp"%>


	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form class="form-horizontal" id="frmuploadbill" method="post" enctype="multipart/form-data"
					action="/prescription_analytics/UserServlet">
<!-- 					<input type="hidden" name="action" value="uploadbill"></input> -->
					<p>
						Select medical bill to upload to server: <input id="MyFile"
							type="file" name="MyFile" size="40">
					</p>
					
					<div class="form-group">

						<div class="col-sm-10">
						<label id="lblError"   style="font-size:20px;color:green;"><%=message %></label></div></div>
						
						<div class="form-group">

						<div class="col-sm-10">
							<input type="text" name="billname" id="billname"
							 required="required" placeholder="Enter Medical Bill Name">
						</div>
						
						
						<div class="col-sm-10">
							<input type="submit"  id="uploadbill"
							value="Upload File" onclick="form.action='/prescription_analytics/UserServlet?action=uploadbill';">
						</div>
						
						
					</div>
					
				</form>
			</article>
		</div>
	</div>


	<%@ include file="footer.jsp"%>

</body>
</html>