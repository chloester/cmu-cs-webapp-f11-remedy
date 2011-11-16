<jsp:include page="template-head.jsp" />

<%@ page import="databeans.User" %>
<%
	User user = (User) session.getAttribute("user");
%>

<div class="container">
	
	<jsp:include page="error-list.jsp"/>
	
	<div class="content">
		<div class="page-header">
			<h1>Add Medication</h1>
		</div>
		<div class="row">
			<div class="span14">
				<form method="post" action="addMed.do">
					<fieldset>
						<legend>Add a new medication or supplement</legend>
						
						<div class="clearfix">
							<label for="name">Name</label>
							<div class="input">
								<input class="large" id="name" name="name" type="text" value="${addmedform.name}" />
								<span class="help-block">Name of your medication</span>
							</div>
						</div>
						
						<div class="clearfix">
							<label for="purpose">Purpose</label>
							<div class="input">
								<input class="large" id="purpose" name="purpose" type="text" value="${addmedform.purpose}" />
								<span class="help-block">e.g. for headaches</span>
							</div>
						</div>
						
						<div class="clearfix">
							<label for="frequency">Frequency</label>
							<div class="input">
								<select class="small" name="freqSelect1" id="freqSelect1">
									<option>Once</option>
									<option>Twice</option>
									<option>3 times</option>
									<option>4 times</option>
									<option>6 times</option>
									<option>8 times</option>
									<option>12 times</option>
								</select>
								a day, every 
								<select class="mini" name="freqSelect2" id="freqSelect2">
									<option></option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>6</option>
									<option>8</option>
									<option>12</option>
								</select>
								 hour(s)
							</div>
						</div>

						<div class="clearfix">
							<label for="days">Days</label>
							<div class="input">
								<ul class="inputs-list">
									<li><label>
										<input type="checkbox" name="dayChecks" value="Monday">
										<span>Monday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" value="Tuesday">
										<span>Tuesday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" value="Wednesday">
										<span>Wednesday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" value="Thursday">
										<span>Thursday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" value="Friday">
										<span>Friday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" value="Saturday">
										<span>Saturday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" value="Sunday">
										<span>Sunday</span>
									</label></li>
								</ul>
							</div>
						</div>
						
						<div class="clearfix">
							<label for="startTime">Starting at</label>
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
									<option>oz</option>
									<option>fl.oz</option>
									<option>tsp</option>
									<option>tbsp</option>
									<option>tablet</option>
									<option>g</option>
									<option>mg</option>
									<option>mcg</option>
									<option>cl</option>
									<option>ml</option>
									<option>drop</option>
								</select>
								<span class="help-block">Enter whole number for dosage, e.g., 5 mg, 2 tbps</span>
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