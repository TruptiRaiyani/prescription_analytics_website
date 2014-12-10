<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII" session="false"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Home</title>
<%@ include file="style_js.jsp"%>


</head>

<body class="body">


	<%@ include file="header.jsp"%>


	<div class="maincontent">
		<div class="content">
			<article class="topcontent">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel"  >
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner">
						<div class="item active">
						<center><img src="images/homeFraud.jpg"  alt="..."></center>
							<div class="carousel-caption">
								<h3>Fraud Detection</h3>
							</div>
						</div>
						<div class="item">
					<center>		<img src="images/homeVaccine.jpg" alt="..."></center>
							<div class="carousel-caption">
								<h3>Disease Propagation Analysis and Vaccine Recommendations</h3>
							</div>
						</div>
						<div class="item">
					<center>		<img src="images/homeBP.jpg" alt="..."></center>
							<div class="carousel-caption"  >
								<h3>Blood Pressure Monitoring</h3>
							</div>
						</div>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</div>
			</article>
		</div>
	</div>


	<%@ include file="footer.jsp"%>

</body>
</html>