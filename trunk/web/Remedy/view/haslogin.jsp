<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include page="template-head.jsp" />

<%@ page import="databeans.User" %>
<%
	User user = (User) session.getAttribute("user");
%>

<div class="container">
	
	<jsp:include page="error-list.jsp"/>
	
	<div class="content">
		<div class="page-header">
			<h1>Welcome, <%=user.getFirstName()%>!</h1>
		</div>
		<div class="row">
			<div class="span10">
				<h2>Your schedule</h2>
				You currently don't have any medications. How about adding one to the right?
				<div class="row">
					<div class="span10"><h3>Monday</h3></div>
				</div>
				<div class="row">
					<div class="span1">
						9:00a
					</div>
					<div class="span6">
						Ibuprofen<br />
						Multivitamin
					</div>
					<div class="span2">
						<span class="label success">Taken</span><br />
						<span class="label success">Taken</span>
					</div>
				</div>
				<div class="row">
					<div class="span1">
						12:00p
					</div>
					<div class="span6">
						Ibuprofen 
					</div>
					<div class="span2">
						<span class="label important">Missed</span>
					</div>
				</div>
				<div class="row">
					<div class="span1">
						3:00p
					</div>
					<div class="span6">
						Ibuprofen
					</div>
					<div class="span2">
						<span class="label warning">Due</span>
					</div>
				</div>
				<div class="row">
					<div class="span10"><h3>Tuesday</h3></div>
				</div>
				<div class="row">
					<div class="span1">
						9:00a
					</div>
					<div class="span6">
						Multivitamin 
					</div>
					<div class="span2">
						<span class="label">Not yet taken</span>
					</div>
				</div>
				<div class="row">
					<div class="span10"><h3>Wednesday</h3></div>
				</div>
				<div class="row">
					<div class="span1">
						9:00a
					</div>
					<div class="span6">
						Multivitamin
					</div>
					<div class="span2">
						<span class="label">Not yet taken</span>
					</div>
				</div>
				<div class="row">
					<div class="span10"><h3>Thursday</h3></div>
				</div>
				<div class="row">
					<div class="span1">
						9:00a
					</div>
					<div class="span6">
						Multivitamin
					</div>
					<div class="span2">
						<span class="label">Not yet taken</span>
					</div>
				</div>
			</div>
			<div class="span6">
				<h3>What would you like to do?</h3>
				<ul class="unstyled">
					<li><a href="addMed.do">Add a new medication</a></li>
					<li><a href="logMed.do">Log a medication</a></li>
					<li><a href="logSide.do">Log a side effect</a></li>
					<li><a href="#">Visualize interactions</a></li>
				</ul>
			</div>
		</div>
		
		<div class="row" style="margin-bottom:18px"></div>
	</div>

<jsp:include page="template-foot.jsp" />