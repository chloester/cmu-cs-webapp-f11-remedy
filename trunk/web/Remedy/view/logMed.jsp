<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />
<jsp:include page="template-body.jsp" />

<%@ page import="databeans.User" %>
<%
	User user = (User) session.getAttribute("user");
%>

<div class="container">

	<jsp:include page="error-list.jsp"/>

	<div class="content">
		<div class="page-header">
			<h1>Log Medication</h1>
		</div>
		<div class="row">
			<div class="span11">
				<form method="post" action="logMed.do">
					<fieldset>
						<legend>Log a medication intake</legend>

						<div class="clearfix">
							<label for="med">Which medication did you take?</label>
							<div class="input">
								<select class="large" id="med" name="name" onclick="showMedication()">

									<c:forEach var="medication" items="${medicationlist}">
										<option>${medication.name}</option>
									</c:forEach>

								</select>
								<span class="help-inline">
									<a href="addMed.do">Add a new medication</a>
								</span>
							</div>
						</div>

						<div class="clearfix">
							<label for="datepicker">When did you take it?</label>
							<div class="input">
								<input class="small" id="datepicker" name="date" type="text" value="${logmedform.date}" />
							</div>
						</div>

						<div class="clearfix">
							<label for="startTime">What time did you take it?</label>
							<div class="input">
								<select class="mini" name="timeHr" id="startTime">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
									<option>6</option>
									<option>7</option>
									<option>8</option>
									<option>9</option>
									<option>10</option>
									<option>11</option>
									<option>12</option>
								</select> : 
								<select class="mini" name="timeMin" id="startTimeMin">
									<option>00</option>
									<option>15</option>
									<option>30</option>
									<option>45</option>
								</select>
								<select class="mini" name="timeAMPM" id="startAMPM">
									<option>a.m.</option>
									<option>p.m.</option>
								</select>
							</div>
						</div>

						<div class="actions">
							<input type="submit" class="btn primary" name="button" value="Log medication" />
							<button type="reset" class="btn">Clear form</button>
						</div>

					</fieldset>
				</form>
			</div>

<jsp:include page="sidemenu.jsp" />

		</div>

	</div>
	
<jsp:include page="template-foot.jsp" />