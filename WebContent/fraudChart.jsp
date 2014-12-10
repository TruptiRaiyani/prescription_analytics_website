<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Fraud Chart</title>
<%@ include file="style_js.jsp"%>
<script type="text/javascript">
$('document')
.ready(
		function() {
			
			var count = [];
			var fraudType= [];
			var fCharge = ${UserBillFraudByCharge};
			var fProcedure = ${UserBillFraudByProcedure};
			count[0] = fCharge.length;
			count[1] = fProcedure.length;
			fraudType[0] = 'Fraud On Charge';
			fraudType[1] = 'Fraud On Procedure';
			$('#container').highcharts({
	            chart: {
	                type: 'column'
	            },
	            title: {
	                text: 'Fraud Analytics'
	            },
	            subtitle: {
	                text: 'Source: user medical bill'
	            },
	            xAxis: {
	                categories: [
						fraudType
	              
	                ]
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: 'Counts based on fraud type'
	                }
	            },
	            tooltip: {
	                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
	                footerFormat: '</table>',
	                shared: true,
	                useHTML: true
	            },
	            plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
	           series: [{
	                name: fraudType[0],
	                data: [count[0]]

	            }, {
	                name: fraudType[1],
	                data: [count[1]]

	            }]
	        });
			
		});

</script>
</head>

<body class="body">
<%
if(request.getAttribute("UserBillFraudByCharge") == null && request.getAttribute("UserBillFraudByProcedure") == null) {
%>
<jsp:forward page="/UserServlet?action=fraudChart" /> 
<%
 }
%>

<%@ include file="header.jsp"%>


	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<form class="form-horizontal" id="" method="post"
					action="/prescription_analytics/UserServlet">
					<input type="hidden" name="action" value="fraudChart"></input>
				<!--	<div class="form-group">

					 	<div class="col-sm-4">
							<select id="ddlQue" name="ddlFraudType">
								<option value="Fraud Count" selected="selected">Fraud Count</option>
							 	<option value="Fraud By Charges">Fraud By Charges</option>
								<option value="Fraud By Procedures">Fraud By Procedures</option> 

							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<input type="submit" name="Submit" value="View Chart">
						</div>
					    </div>-->
				  <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
				</form>
			</article>
		</div>
	</div>


	<%@ include file="footer.jsp"%>

</body>
</html>