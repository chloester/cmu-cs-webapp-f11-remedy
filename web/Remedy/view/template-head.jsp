<html>
<head>
	<title>Remedy - Track your medications and side effects</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
	<script src="js/jquery-ui-1.8.16.custom.min.js"></script>
	<script src="js/bootstrap-dropdown.js"></script>
	<script src="js/bootstrap-modal.js"></script>
	<script>
		$(function() {
			var d = new Date();
			var month = d.getMonth();
			var date = d.getDate();
			var year = d.getFullYear();
			console.log(month + " " + date + " " + year);
			$("#datepicker").value = (month<10?"0"+month:month) + "/" + (date<10?"0"+date:date) + "/" + year;
			$("#datepicker").datepicker({maxDate:"+0D"});	
		});
	</script>
	<script>
		console.log(${medication.name});
	</script>
	
	<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css">
	<link rel="stylesheet" href="css/web.css">
	<link rel="stylesheet" href="css/jquery-ui-1.8.16.custom.css">
	
</head>
<body>

<%@ page import="databeans.User" %>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
%>
	<div class="topbar">
		<div class="fill">
			<div class="container">
				<a class="brand" href="/Remedy/">Remedy<sup>+</sup></a>
				<ul class="nav">
					<li class="active"><a href="/Remedy/">Home</a></li>
					<li><a href="#">Learn more</a></li>
				</ul>
				<form class="pull-right" id="loginform" method="post" action="login.do">
					<input class="small" type="text" id="emailaddress" name="emailaddress" placeholder="Email" value="${loginform.emailaddress}">
					<input class="small" type="password" name="password" placeholder="Password" value="${loginform.password}">
					<button class="btn" type="submit" name="button" value="login">Sign in</button>
					<button class="btn" type="submit" name="button" value="register">Register</button>
				</form>
			</div>
		</div>
	</div>
<%
	} else {
%>
	<div class="topbar" data-dropdown="dropdown">
		<div class="fill">
			<div class="container">
				<a class="brand" href="/Remedy/">Remedy<sup>+</sup></a>
				<ul class="nav">
					<li class="active"><a href="/Remedy/">Home</a></li>
					<li><a href="viewMeds.do">My Medications</a></li>
					<li><a href="viewSides.do">My Side Effects</a></li>
				</ul>
				<ul class="nav secondary-nav">
					<li class="menu">
						<a href="#" class="menu"><%=user.getFirstName()%> <%=user.getLastName()%></a>
						<ul class="dropdown-menu menu-dropdown">
							<li><a href="logout.do">Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
<%
	}
%>
