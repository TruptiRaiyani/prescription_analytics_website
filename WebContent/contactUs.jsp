<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Contact Us</title>
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
			<h3>We are here:)</h3>
			<p> Washington Square,
				San José State University
				San Jose, CA 95112</p>	
			<iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=San+Jos%C3%A9+State+University,+Washington+Sq,+San+Jose,+CA&amp;aq=0&amp;oq=san&amp;sll=37.297016,-121.817413&amp;sspn=0.591016,1.352692&amp;ie=UTF8&amp;hq=San+Jos%C3%A9+State+University,+Washington+Sq,+San+Jose,+CA&amp;t=m&amp;ll=37.335839,-121.881251&amp;spn=0.005971,0.00912&amp;z=16&amp;iwloc=A&amp;output=embed"></iframe><br /><small><a href="https://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=San+Jos%C3%A9+State+University,+Washington+Sq,+San+Jose,+CA&amp;aq=0&amp;oq=san&amp;sll=37.297016,-121.817413&amp;sspn=0.591016,1.352692&amp;ie=UTF8&amp;hq=San+Jos%C3%A9+State+University,+Washington+Sq,+San+Jose,+CA&amp;t=m&amp;ll=37.335839,-121.881251&amp;spn=0.005971,0.00912&amp;z=16&amp;iwloc=A" style="color:#0000FF;text-align:left" target="_blank">View Larger Map</a></small>
			</article>
		</div>
	</div>
	

	<%@ include file="footer.jsp" %>
</body>
</html>