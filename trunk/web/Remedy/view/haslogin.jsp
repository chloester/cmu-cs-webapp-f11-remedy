<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include page="template-head.jsp" />
<jsp:include page="error-list.jsp"/>

<%@ page import="databeans.User" %>
<%
	User user = (User) session.getAttribute("user");
%>

<div class="container">
	
	<div class="content">
		<div class="page-header">
			<h1>Welcome, <%=user.getFirstName()%>!</h1>
		</div>
		<div class="row">
			<div class="span10">
				<h2>Your medications</h2>
				You currently don't have any medications. How about adding one to the right?
			</div>
			<div class="span6">
				<h3>What would you like to do?</h3>
				<ul class="unstyled">
					<li><a href="#">Add a new medication</a></li>
					<li><a href="#">Log a medication</a></li>
					<li><a href="#">Add a new side effect</a></li>
					<li><a href="#">Log a side effect</a></li>
					<li><a href="#">Visualize interactions</a></li>
				</ul>
			</div>
		</div>
	</div>

<jsp:include page="template-foot.jsp" />