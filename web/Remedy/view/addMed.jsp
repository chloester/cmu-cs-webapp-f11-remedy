<jsp:include page="template-head.jsp" />
<jsp:include page="template-body.jsp" />

<%@ page import="databeans.User" %>
<%
	User user = (User) session.getAttribute("user");
%>
<script type="text/javascript">
 function change(signal){
	 var daycheck = $(".daycheck");
	 var num = $(".daycheck").length;
	 if(signal==0){
	 if(daycheck[0].checked==false){
	 	for (var i=1;i<num;i++){
	 		daycheck[i].checked=false;
	 	}
	 }else{
	 	for (var i=1;i<num;i++){
	 		daycheck[i].checked=true;
	 	}
	 }
	 }
	 if(signal==1){
		 daycheck[0].checked=false;
	 }
 }
</script>

<div class="container">
	
	<jsp:include page="error-list.jsp"/>
	
	<div class="content">
		<div class="page-header">
			<h1>Add Medication</h1>
		</div>
		<div class="row">
			<div class="span11">
				<form method="post" action="addMed.do">
					<fieldset>
						<legend>Add a new medication or supplement</legend>
						
						<div class="clearfix">
							<label for="name">Name</label>
							<div class="input">
								<input class="large" id="name" name="name" type="text" value="${addmedform.name}" onkeyup="ajaxmed(this.value)" />
								<span class="help-block">Name of your medication</span>
								<span style="color:red" id="medhint"></span>
							</div>
						</div>
						
						<div class="clearfix">
							<label for="purpose">Purpose</label>
							<div class="input">
								<input class="large" id="purpose" name="purpose" type="text" value="${addmedform.purpose}" onkeyup="ajaxside(this.value)"/>
								<span class="help-block"><em>e.g.,</em> for headaches</span>
								<span style="color:red" id="sidehint"></span>
							</div>
						</div>
						
						<div class="clearfix">
							<label for="freq1">Frequency</label>
							<div class="input">
								<select class="small" name="freqSelect1" id="freq1">
									<option>Once</option>
									<option>Twice</option>
									<option>3 times</option>
									<option>4 times</option>
									<option>6 times</option>
									<option>8 times</option>
									<option>12 times</option>
								</select>
								a day, every 
								<select class="mini" name="freqSelect2" id="freq2">
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
										<input type="checkbox" class="daycheck" value="Everyday" onclick="change(0)">
										<span >Every day</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" class="daycheck" value="Monday" onclick="change(1)">
										<span>Monday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" class="daycheck" value="Tuesday" onclick="change(1)">
										<span>Tuesday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" class="daycheck" value="Wednesday" onclick="change(1)">
										<span>Wednesday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" class="daycheck" value="Thursday" onclick="change(1)">
										<span>Thursday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" class="daycheck" value="Friday" onclick="change(1)">
										<span>Friday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" class="daycheck" value="Saturday" onclick="change(1)">
										<span>Saturday</span>
									</label></li>
									<li><label>
										<input type="checkbox" name="dayChecks" class="daycheck" value="Sunday" onclick="change(1)">
										<span>Sunday</span>
									</label></li>
								</ul>
							</div>
						</div>
						<div class="clearfix">
							<label for="startTime">Starting at</label>
							<div class="input">
								<select class="mini" name="startTimeHour" id="startTimeHour">
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
								<select class="small" name="dosageUnit" id="dosageUnit">
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
								<span class="help-block">Enter whole number for dosage, <em>e.g.,</em> 5 oz, 2 tbsp</span>
							</div>
						</div>
						
						<div class="actions">
							<input type="submit" class="btn primary" name="button" value="Add medication" />
							<button type="reset" class="btn">Clear form</button>
						</div>
						
					</fieldset>
				</form>
			</div>
<jsp:include page="sidemenu.jsp" />

		</div>
		
	</div>

<jsp:include page="template-foot.jsp" />