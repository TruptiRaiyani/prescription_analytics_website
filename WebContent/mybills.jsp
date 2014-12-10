<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@page import="java.util.*"%>
<%@ page import="org.json.JSONArray"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>My Bills</title>
<%@ include file="style_js.jsp" %>

<script type="text/javascript">
	$('document')
			.ready(
					function() {
						var msg = '${runFraudMessage}';
						if(msg == '')
							{
							$('#lblError').hide();
							}
						//	alert('hi');
						var theData = ${userBills};
					
						for ( var i = 0; i < theData.length; i++) {

							var counter = theData[i];
							//	alert(counter.medicalbillname);
							var tr = '<tr style="color:#CF5C3F;">';
							// create a new textInputBox  
							//   var id = 'btnRunFraud' + i;
							var tooltip = 'run fraud';
							var fraudRunLink = '<a style="color:#FF8566;" href="/prescription_analytics/UserServlet?action=RunFraud&billID='
									+ counter.medicalbillid
									+ '"><img src="images/fraudRun.jpg" height="38%" width="25%" /></a>';
							var viewFraudLink = '<a href="/prescription_analytics/UserServlet?action=ViewFraud&billID='
									+ counter.medicalbillid
									+ '"> <img src="images/viewFraud.jpg" height="40%" width="20%" /></a>';
							//    var textInputBox = '<input type="text" id="' + id + '" name="' + id + '" title="' + tooltip + '" />';  
							// create a new Label Text
							tr += '<td>' + counter.medicalbillname + '</td>';
							tr += '<td>' + fraudRunLink + '</td>';
							tr += '<td>' + viewFraudLink + '</td>';
							tr += '</tr>';
							$('#tblMyList').append(tr);

						}

						;
						  $('#tblMyList').dataTable();
					});
</script>


</head>

<body class="body">

	<%@ include file="header.jsp"%>
<%String message=(String)request.getAttribute("runFraudMessage"); %>

	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
					<div class="form-group">

						<div class="col-sm-10">
						<label id="lblError"   style="font-size:20px;color:green;"><%=message %></label></div></div>
				<div class="table-responsive">	<table id="tblMyList"
					class="table table-striped" >
						<thead>
						<tr style="background-color:#CF5C3F;">
						<th>Name</th>
						<th>Run Fraud</th>
						<th>View Fraud Details</th>
						</tr></thead>
					</table></div>
				
			</article>
		</div>
	</div>


	<%@ include file="footer.jsp"%>

</body>
</html>