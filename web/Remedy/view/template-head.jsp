<html>
<head>
	<title>Remedy - Track your medications and side effects</title>

	<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css">
	<link rel="stylesheet" href="css/web.css">

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
					<input class="small" type="text" name="emailaddress" placeholder="Email" value="${loginform.emailaddress}">
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
	<div class="topbar">
		<div class="fill">
			<div class="container">
				<a class="brand" href="#">Remedy<sup>+</sup></a>
				<ul class="nav">
					<li class="active"><a href="#">Home</a></li>
				</ul>
				<form class="pull-right" method="post" action="logout.do">
					Logged in as <a href="#"><%=user.getFirstName()%> <%=user.getLastName()%></a> 
					<button class="btn" type="submit" name="button" value="logout">Logout</button>	
				</form>
			</div>
		</div>
	</div>
<%
	}
%>
