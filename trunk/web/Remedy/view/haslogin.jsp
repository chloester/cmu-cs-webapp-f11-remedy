<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />
<jsp:include page="template-body.jsp" />

<%@ page import="databeans.User,databeans.ScheduleItem" %>
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
			<div class="span11">
				<h2>Your schedule</h2>
				
				<div class="row">
					<div class="span11">
						Monday<br><br>
						
						<c:if test="${!(empty mondayList)}">
							<c:forEach var="item" items="${mondayList}">
								${item.time} : ${item.name}<br>
							</c:forEach>
						</c:if>
						
						Tuesday<br><br>
						
						<c:if test="${!(empty tuesdayList)}">
							<c:forEach var="item" items="${tuesdayList}">
								${item.time} : ${item.name}<br>
							</c:forEach>
						</c:if>
						
						Wednesday<br><br>
						
						<c:if test="${!(empty wednesdayList)}">
							<c:forEach var="item" items="${wednesdayList}">
								${item.time} : ${item.name}<br>
							</c:forEach>
						</c:if>
						
						Thursday<br><br>
						
						<c:if test="${!(empty thursdayList)}">
							<c:forEach var="item" items="${thursdayList}">
								${item.time} : ${item.name}<br>
							</c:forEach>
						</c:if>
						
						Friday<br><br>
						
						<c:if test="${!(empty fridayList)}">
							<c:forEach var="item" items="${fridayList}">
								${item.time} : ${item.name}<br>
							</c:forEach>
						</c:if>
						
						Saturday<br><br>
						
						<c:if test="${!(empty saturdayList)}">
							<c:forEach var="item" items="${saturdayList}">
								${item.time} : ${item.name}<br>
							</c:forEach>
						</c:if>
						
						Sunday<br><br>
						
						<c:if test="${!(empty sundayList)}">
							<c:forEach var="item" items="${sundayList}">
								${item.time} : ${item.name}<br>
							</c:forEach>
						</c:if>
						
					</div>
				</div>
				
				<div class="row">
					<div class="span11"><h3>Monday</h3></div>
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
					<div class="span11"><h3>Tuesday</h3></div>
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
					<div class="span11"><h3>Wednesday</h3></div>
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
					<div class="span11"><h3>Thursday</h3></div>
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

<jsp:include page="sidemenu.jsp" />

		</div>
		
		<div class="row" style="margin-bottom:18px"></div>
	</div>

<jsp:include page="template-foot.jsp" />