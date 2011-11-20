<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

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
			<div class="span14">
				<form method="post" action="logmed.do">
					<fieldset>
						<legend>Log a medication intake</legend>
						
						<div class="clearfix">
							<label for="med">Which medication did you take?</label>
							<div class="input">
								<select class="large" id="med" name="med">
								
									<c:forEach var="medication" items="${medicationlist}">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<option>${medication.username}</option>
									</c:forEach>
								
								</select>
								<span class="help-inline">
									<a href="addmed.do">Add a new medication</a>
								</span>
							</div>
						</div>

						<div class="clearfix">
							<label for="datepicker">When did you take it?</label>
							<div class="input">
								<input class="small" id="datepicker" name="date" type="text" value="${logmedform.date}" />
								<span class="help-block">What date did you take this medication?</span>
							</div>
						</div>
						
						<div class="clearfix">
							<label for="startTime">What time did you take it?</label>
							<div class="input">
								<select class="mini" name="startTime" id="startTime">
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
								</select>
								<select class="mini" name="startTimeMin" id="startTimeMin">
									<option>00</option>
									<option>15</option>
									<option>30</option>
									<option>45</option>
								</select>
								<select class="mini" name="startAMPM" id="startAMPM">
									<option>a.m.</option>
									<option>p.m.</option>
								</select>
							</div>
						</div>
						
						<div class="clearfix">
							<label for="dosage">Dosage</label>
							<div class="input">
								<input class="mini" id="dosage" name="dosage" type="text" value="${addmedform.dosage}" />
								<select class="mini" name="dosageUnit" id="dosageUnit">
									<option>tablet</option>
									<option>pill</option>
									<option>oz</option>
									<option>fl.oz</option>
									<option>tsp</option>
									<option>tbsp</option>
									<option>g</option>
									<option>mg</option>
									<option>mcg</option>
									<option>cl</option>
									<option>ml</option>
									<option>drop</option>
								</select>
								<span class="help-block">Enter whole number for dosage, e.g., 5 oz, 2 tbsp</span>
							</div>
						</div>
						
						<div class="actions">
							<input type="submit" class="btn primary" name="button" value="Add medication" />
							<button type="reset" class="btn">Clear form</button>
						</div>
						
					</fieldset>
				</form>
			</div>
		</div>
		
	</div>

<jsp:include page="template-foot.jsp" />