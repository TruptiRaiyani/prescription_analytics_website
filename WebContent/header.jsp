
<header class="mainheader">
	<%
		HttpSession sessionsa = request.getSession(false);
		String user = (String) sessionsa.getAttribute("userName");
	%>
	<img class="img-responsive" src="images/logo2.png"> 

	<nav>
		<ul>
			<li><a href='home.jsp'>Home</a></li>
		</ul>
		<ul class="nav navbar-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">
					Bills <span class="caret"></span>
			</a>
				<ul class="dropdown-menu" role="menu"
					style="background-color: #6CDAFF; border-radius: 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px; line-height: 25px;">
					<li><a href="uploadfile.jsp">Upload Bill</a></li>
					<li><a
						href="/prescription_analytics/UserServlet?action=getmyBills">My
							Bills</a></li>


				</ul></li>
		</ul>
		<ul>
			<li><a href='fraudChart.jsp'>View Fraud Ratio</a></li>
		</ul>
		<ul>
			<li><a href='diseaseProp.jsp'>Disease Propagation</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false"> <%=user%>
					<span class="caret"></span>
			</a>
				<ul class="dropdown-menu" role="menu"
					style="background-color: #6CDAFF; border-radius: 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px; line-height: 25px;">
					<li><a href="viewprofile.jsp">View Profile</a></li>
					<li><a href="editprofile.jsp">Edit Profile</a></li>
					<li><a href="deleteprofile.jsp">Delete Profile</a></li>
					<li><a href="changepassword.jsp">Change Password</a></li>

				</ul></li>
			<li><a href="signin.jsp"
				onclick="return confirm('Are you sure?');" id="aSignOut">SignOut</a></li>
		</ul>
	</nav>


</header>