<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>About</title>
<%@ include file="style_js.jsp" %>
<script>
$('document').ready(function(){
	
	
	var userEmail = '${userEmail}';
	if(userEmail == '')
		$('#ahome').hide();

});
</script>
</head>
<body class="body">

	<header class="mainheader">

		<img class="img-responsive" src="images/logo2.png"> 

	</header>


	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<p align="justify">
				Medical billing fraud estimates are increasing year by year. According to the 
				statistics provided by National Health Care Anti-Fraud Association (NHCAA), 
				the health care fraud costs more than $70 billion a year which comprises to 
				about 3% of the total expenditure on national health care. Patients are getting 
				exploited by being charged extra for the services than their standard price 
				specified by the American Medical Association.
				</p >
				<p align="justify"> Our enthusiastic developer team has decieded to work for the walefare of the society.
				 Our excited and enthuastic developer team has come up with the idea of Prescription 
				 Analytics Tool. Our goal is to aware the society against this fraud and that's why we 
				 came up with  a mobile and web application that is useful in a health care field by 
				 providing various facilities to increase the confidence of people in a health care.</p>
				 <p>
				 Below are the features that will help you by using our tool:
				 <ul>
				 <li align="justify"> <b style="font-weight: bolder;"> EHR (Electronic Health Record)</b> system/ Prescription record system
					 Now a days people are used to keep the data electronically rather than 
					 keep it as a hardcopy. Generally it is a very tedious task to get a 
					 print copy and maintain it somewhere. Our system provides the 
					 facility for users to maintain the medical prescriptions electronically, 
					 so users can have it as and when required. This way they can maintain 
					 the documents for the future.
				</li>
				
				<li align="justify"> <b style="font-weight: bolder;"> Fraud Detection system </b>
					 Generally people do not have detailed idea about medical services, and its
					 related charges for particular disease, so people can easily be deceived. 
					 To overcome this issue there should be some online services where user can 
					 detect the fraudulent activities. Our system takes the uploaded prescription
					 and cross validates it with the ICD10 medical data in order to detect any 
					 fraud such as overcharging for any medical service, irrelevant medical service,
					 etc. Thus, user can take actions if there is any fraud detected. By using our
					 system we can minimize medical billing fraud. This will help to increase 
					 patient confidence in the health care industry.
				</li>
				<li  align="justify"> <b style="font-weight: bolder;"> Disease propagation analysis 
				     and  Vaccine recommendation system </b> Disease propagation is now a days 
				     big concern for people, and people have to take precautions to stay healthy. 
				     The system does medical data analytics based on county, and detects the most 
				     frequently occurring disease in that county. If there is any particular disease
				     propagation occurred then system alerts the user about the disease information
				     and recommends the vaccine related to that disease to the users. Thus, 
				     this way people can take precautions for that particular disease.
				 </li>
				 <li  align="justify"> <b style="font-weight: bolder;">Twitter and WHO health data 
				  	 analytics system </b> Big data analytics is a very useful technique to get an
				  	 important/useful data from large amount of websites’ data. Twitter is a
				  	 website where people tweet about their thought, routine life, health, interest,
				  	 and many more. WHO is a health organization that has a	country wise public 
				  	 health data. By using twitter and WHO big data, our application generates 
				  	 the graph or statistical information using the health data for our knowledge.
				 </li>
				 <li  align="justify"> <b style="font-weight: bolder;"> Real time system </b>
					IOT (internet of Things) is a very popular now a days. We use the data of 
					smart devices that stores real time data and gives information about it. 
					Our application takes the blood pressure data from the smart device, and 
					store it into system. This blood pressure data will be displayed in graph
					format by our application for users’ information.
				 </li>
				 </ul>
				 </p>
			</article>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>