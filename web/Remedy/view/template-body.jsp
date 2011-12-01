</head>
<body onload="changepage()">

<script type="text/javascript">
function changelink(signal){
	document.cookie = signal;
}
function changepage(){
	var signal;
	var topbar = $(".top111");
	signal = document.cookie;
	topbar[signal-1].setAttribute("class","active");
	//alert($(".top111")[1].value);
}
function clearCookie(){
	alert(1);
	//var topbar = $(".top111");
	//var num = topbar.length;
	//for(var i = 0; i <num; i++ ){
	//	topbar[i].setAttribute("class","top111");
	//}
	document.cookie = "";
}
</script>
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
						<input id="pageid" class="" type="hidden">
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
					<li onclick="changelink(1)"  class="top111"  value="1"><a href="/Remedy/" >Schedule</a></li>
					<li onclick="changelink(2)"  class="top111"  value="2"><a href="viewMeds.do?">Medications</a></li>
					<li onclick="changelink(3)"  class="top111"  value="3"><a href="viewSides.do?">Side Effects</a></li>
					<li onclick="changelink(4)"  class="top111"  value="4"><a href="visualize.do?">Visualize</a></li>
				</ul>
				<ul class="nav secondary-nav">
					<li class="menu">
						<a href="#" class="menu"><%=user.getFirstName()%> <%=user.getLastName()%></a>
						<ul class="dropdown-menu menu-dropdown">
							<li onclick="clearCookie()"><a href="logout.do">Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
<%
	}
%>