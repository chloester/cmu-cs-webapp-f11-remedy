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
						<h3>Monday</h3>
					</div>
				</div>
				
				<c:if test="${!(empty mondayList)}">
					<c:forEach var="item" items="${mondayList}">
						<div class="row">
							<div class="span1">
								${item.time}
							</div>
							<div class="span8">
								${item.name}
							</div>
						</div>
					</c:forEach>
				</c:if>
		
				<div class="row">
					<div class="span11">
						<h3>Tuesday</h3>
					</div>
				</div>
		
				<c:if test="${!(empty tuesdayList)}">
					<c:forEach var="item" items="${tuesdayList}">
						<div class="row">
							<div class="span1">
								${item.time}
							</div>
							<div class="span8">
								${item.name}
							</div>
						</div>
					</c:forEach>
				</c:if>
		
				<div class="row">
					<div class="span11">
						<h3>Wednesday</h3>
					</div>
				</div>
		
				<c:if test="${!(empty wednesdayList)}">
					<c:forEach var="item" items="${wednesdayList}">
						<div class="row">
							<div class="span1">
								${item.time}
							</div>
							<div class="span8">
								${item.name}
							</div>
						</div>
					</c:forEach>
				</c:if>
		
				<div class="row">
					<div class="span11">
						<h3>Thursday</h3>
					</div>
				</div>
		
				<c:if test="${!(empty thursdayList)}">
					<c:forEach var="item" items="${thursdayList}">
						<div class="row">
							<div class="span1">
								${item.time}
							</div>
							<div class="span8">
								${item.name}
							</div>
						</div>
					</c:forEach>
				</c:if>
		
				<div class="row">
					<div class="span11">
						<h3>Friday</h3>
					</div>
				</div>
		
				<c:if test="${!(empty fridayList)}">
					<c:forEach var="item" items="${fridayList}">
						<div class="row">
							<div class="span1">
								${item.time}
							</div>
							<div class="span8">
								${item.name}
							</div>
						</div>
					</c:forEach>
				</c:if>
		
				<div class="row">
					<div class="span11">
						<h3>Saturday</h3>
					</div>
				</div>
		
				<c:if test="${!(empty saturdayList)}">
					<c:forEach var="item" items="${saturdayList}">
						<div class="row">
							<div class="span1">
								${item.time}
							</div>
							<div class="span8">
								${item.name}
							</div>
						</div>
					</c:forEach>
				</c:if>
		
				<div class="row">
					<div class="span11">
						<h3>Sunday</h3>
					</div>
				</div>
		
				<c:if test="${!(empty sundayList)}">
					<c:forEach var="item" items="${sundayList}">
						<div class="row">
							<div class="span1">
								${item.time}
							</div>
							<div class="span8">
								${item.name}
							</div>
						</div>
					</c:forEach>
				</c:if>

			</div>
			
			<jsp:include page="sidemenu.jsp" />
		</div>
		
		<div class="row" style="margin-bottom:18px"></div>

<jsp:include page="template-foot.jsp" />