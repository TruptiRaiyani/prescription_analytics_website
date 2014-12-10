<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>View Fraud</title>
<%@ include file="style_js.jsp"%>
<script type="text/javascript">
	$('document')
			.ready(
					function() {
						//alert('view fraud');
						var bill = ${billByBillID};
						var FraudByCharge = ${UserBillFraudByCharge};
						var FraudByProcedure = ${UserBillFraudByProcedure};
						//var theData = bill[0];
					//	alert("bill id :"  + bill.medicalbillid);
						
						for ( var i = 0; i < bill.icd.length; i++) {

							var tr = '';
							tr += ' <div class="col-md-1">'
									+ bill.icd[i].icdcode + '</div>';
							tr += ' <div class="col-md-4">'
									+ bill.icd[i].descriptionicd + '</div>';
						
							if (bill.icd[i].cpts.length > 0)
								tr += '<div class="col-md-7">';
							var innertr = '';
							
							for ( var j = 0; j < bill.icd[i].cpts.length; j++) {
							
								/*check for fraud by charge*/
								
								var IsFradByProcedure = FraudByProcedure.filter(function (el) {
								  return el.billid == bill.medicalbillid &&
								         el.icdcode == bill.icd[i].icdcode &&
								         el.cptcode == bill.icd[i].cpts[j].cptcode;
								});
								var IsFradByCharge = FraudByCharge.filter(function (el) {
									  return el.billid == bill.medicalbillid &&
									         el.icdcode == bill.icd[i].icdcode &&
									         el.cptcode == bill.icd[i].cpts[j].cptcode;
									});
							
								var backColorStyle= '';
								var backColorStyleForExtraCharge= '';
								if(IsFradByProcedure.length > 0)
									backColorStyle = "style='background-color:#FFCC00;'";
								else if (IsFradByCharge.length > 0)
										{
							if(IsFradByCharge[0].fraudlevel == "major")
								{
								backColorStyle = "style='background-color:#AD3333;'";
								backColorStyleForExtraCharge = "style='background-color:#AD3333;'";
								}
							else if(IsFradByCharge[0].fraudlevel == "moderate")
								{
								backColorStyle = "style='background-color:#FF8533;'";
								backColorStyleForExtraCharge = "style='background-color:#FF8533;'";
								}
							else if(IsFradByCharge[0].fraudlevel == "minor")
								{
								backColorStyle = "style='background-color:#FFB870;'";
								backColorStyleForExtraCharge = "style='background-color:#FFB870;'";
								}
									//	alert(IsFradByCharge[0].fraudlevel);
										//alert(IsFradByCharge[0].amountdiff);
										}
								innertr += '<div class="row" >';
								innertr += '<div class="col-md-2" '+backColorStyle+'>'
										+ bill.icd[i].cpts[j].cptcode
										+ '</div>';
								innertr += '<div class="col-md-6">'
										+ bill.icd[i].cpts[j].cptdescription
										+ '</div>';
								innertr += '<div class="col-md-2"'+backColorStyleForExtraCharge+'>' + "$"
										+ bill.icd[i].cpts[j].charge + '</div>';
							if (IsFradByCharge.length > 0)
											innertr += '<div class="col-md-2"'+backColorStyleForExtraCharge+'>' + "$"
											+ IsFradByCharge[0].originalamount + '</div>';
								innertr += '</div>';
								//alert(j + 'done');
							}
							;
							innertr += '</div>';
							//	alert(innertr);
							tr += innertr;
							//tr += '</div>';
							$('#divmain').append(tr);
						}
						;
					});
</script>
</head>

<body class="body">

	<%@ include file="header.jsp"%>


	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<div class="row" id="divmain" style="border:1px solid; margin: 10px;">
				<div class="col-md-12" >
				<div class="col-md-3" ><img src="images/yellow.png" height="15%" width="15%" /> Irrelevant CPT</div>
			
				<div class="col-md-3" ><img src="images/major.png" height="15%" width="15%" /> Fraud Level- Major
				</div>
				<div class="col-md-3" ><img src="images/moderate.png" height="15%" width="15%" /> Fraud Level- Moderate
				</div>
				<div class="col-md-3" ><img src="images/minor.png" height="15%" width="15%" /> Fraud Level- Minor
				</div>
				</div>
				<div class="col-md-12" ></div>
				<div class="col-md-12" ></div>
				<div class="col-md-12" ></div>
				<div class="col-md-12"  style="background-color:#CF5C3F;">
				<div class="col-md-1" >ICD</div>
				<div class="col-md-4" >ICD Description</div>
				<div class="col-md-7" >
					<div class="row">
						<div class="col-md-2">CPT</div>
						<div class="col-md-6" >CPT Description</div>
						<div class="col-md-2" >Amount Charged</div>
						<div class="col-md-2" >Original Amount</div>
					</div>
				</div>
				</div>
				<div class="col-md-12" ></div>
				<div class="col-md-12" ></div>
				<div class="col-md-12" ></div>
				</div>
				
			</article>
		</div>
	</div>


	<%@ include file="footer.jsp"%>

</body>
</html>