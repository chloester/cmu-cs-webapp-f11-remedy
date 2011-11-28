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
					<li class="active"><a href="/Remedy/">Schedule</a></li>
					<li><a href="viewMeds.do">Medications</a></li>
					<li><a href="viewSides.do">Side Effects</a></li>
					<li><a href="visualize.do">Visualize</a></li>
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
