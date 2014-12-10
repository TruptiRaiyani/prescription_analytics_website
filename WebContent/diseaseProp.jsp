<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Disease Propagation</title>
<style type="text/css">
.hidden {
	display: none;
}
</style>
<%@ include file="style_js.jsp" %>

<script type="text/javascript">
$('document')
.ready(
		function() {var diseaseName = [];
						var count = [];
						country;
						var twitter = ${twitterData};
						for(var i=0;i<twitter.length;i++){
							diseaseName[i] = twitter[i].diseaseName;
							count[i] = twitter[i].count;
							country = twitter[i].country;					
						}			
						var arrayLength = diseaseName.length;
						//alert(arrayLength);
						$('#container').highcharts({
				            chart: {
				                type: 'column'
				            },
				            title: {
				                text: 'Disease Propagation Analytics'
				            },
				            subtitle: {
				                text: 'Source: Twitter'
				            },
				            xAxis: {
				                categories: [
				                    country
				              
				                ]
				            },
				            yAxis: {
				                min: 0,
				                title: {
				                    text: 'Disease Name Tweet Counts'
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
				                name: diseaseName[0],
				                data: [count[0]]

				            }, {
				                name: diseaseName[1],
				                data: [count[1]]

				            }, {
				                name: diseaseName[2],
				                data: [count[2]]

				            }, {
				                name: diseaseName[3],
				                data: [count[3]]

				            }]
				        });
						
						//get disease having max tweet count
						var maxTweetCount = count[0];
						disease = diseaseName[0];
						for(var k=1; k<count.length;k++){
							if(count[k]>maxTweetCount){
								
								maxTweetCount = count[k];
								//alert(maxTweetCount.toString());
								disease = diseaseName[k];
							}
							
						}
						
					});
						
	</script>
</head>
<body class="body">
<%@ include file="header.jsp" %>


	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
			<form id="form1" method="Post">
    <div class="form-group">
						<div class="col-sm-10">
							<select id="country" name="country" >
								<option value="selected" selected="selected">Choose Country</option>
								<option value="United States">United States</option>
								<option value="Canada">Canada</option>
								<option value="Indonesia">Indonesia</option>
								<option value="Argentina">Argentina</option>
								<option value="Brazil">Brazil</option>
								<option value="Germany">Germany</option>
								<option value="India">India</option>
								<option value="Jamaica">Jamaica</option>
								<option value="Kenya">Kenya</option>
								<option value="Malaysia">Malaysia</option>
								<option value="Netherlands">Netherlands</option>
								<option value="Republic Of Costa Rica">Republic of Costa Rica</option>
								<option value="Republic Of Philippines">Republic of Philippines</option>
								<option value="South Africa">South Africa</option>
								<option value="Sweden">Sweden</option>
								<option value="United Kingdom">United Kingdom</option>
								<option value="Africa">Africa</option>
								<option value="Italy">Italy</option>
								<option value="Republic of Paraguay">Republic of Paraguay</option>
								<option value="UAE">UAE</option>
								<option value="Espana">Espana</option>
							</select>
						</div>
    <br><br>
    <div class="col-sm-offset-2 col-sm-10">
    <input type="submit" name="action" value="Twitter" onclick="form.action='/prescription_analytics/UserServlet';" >
    <input type="submit" name="action" value="Vaccine" onclick="form.action='/prescription_analytics/UserServlet?diseaseName='+disease;">
    <br>
    </div>
     <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
     </div>
</form>

					    <script type="text/javascript">
					   
							var vaccineInfo = ${vaccineData};
							var tr;
							
							//alert(vaccineInfo.vaccineName);
							if(vaccineInfo.vaccineName == "null"){
								$('#container').append("<h4> As per World Health Organization " + vaccineInfo.diseaseName +" is not prominent in this country.<br><br> Hence taking vaccinations is not mandatory. </h4>");
							}
							else{
								$('#container').append("<br><h4> As per World Health Organization "+vaccineInfo.diseaseName+" is prominent in this country.<br><br>So we recommend you to take vaccinations.<br><br> Vaccinations for "+vaccineInfo.diseaseName +" are :</h4>");
								
						    for(var i=0;i<10 && i<vaccineInfo.vaccineName.length;i++){
									tr += '<tr style="color:#CF5C3F;">'+'<td>' + vaccineInfo.vaccineName[i] + '</td>';
									tr += '</tr>';
							}
						    $('#container').append(tr);
							}
						    document.getElementById("vaccineSubmit").style.display = 'none';
					    </script>
			</article>
		</div>
	</div>
	

	<%@ include file="footer.jsp" %>
</body>
</html>


