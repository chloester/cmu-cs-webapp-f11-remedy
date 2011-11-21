<jsp:include page="template-head.jsp" />

<%@ page import="databeans.User" %>
<%
	User user = (User) session.getAttribute("user");
%>

<div class="container">
	
	<jsp:include page="error-list.jsp"/>
	
	<div class="content">
		<div class="page-header">
			<h1>Log Side Effect</h1>
		</div>
		<div class="row">
			<div class="span16">
				<form method="post" action="logSide.do">
					<fieldset>
						<legend>Log a side effect</legend>
						
						<div class="clearfix">
							<label for="name">Side effect</label>
							<div class="input">
								<select class="medium" id="name" name="name">
									<option></option>
								</select>
								<span class="help-inline">
									<a href="#" data-controls-modal="modal-from-dom-addSide" data-backdrop="true" data-keyboard="true" >Add a new side effect</a>
								</span>
							</div>
						</div>
						
						<div class="clearfix">
							<label for="value">Rating</label>
							<div class="input">
								<select class="mini" id="value" name="value">
									<option>0</option>
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
								</select>
								<span class="help-block">How strong is the side effect? 0 = none, 10 = highest</span>
							</div>
						</div>
						
						<div class="clearfix">
							<label for="datepicker">Date</label>
							<div class="input">
								<input class="small" id="datepicker" name="date" type="text" value="${logsideform.date}" />
							</div>
						</div>
						
						<div class="clearfix">
							<label for="startTime">Time</label>
							<div class="input">
								<select class="mini" name="timeHr" id="timeHr">
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
								<select class="mini" name="timeMin" id="timeMin">
									<option>00</option>
									<option>15</option>
									<option>30</option>
									<option>45</option>
								</select>
								<select class="mini" name="timeAMPM" id="timeAMPM">
									<option>a.m.</option>
									<option>p.m.</option>
								</select>
							</div>
						</div>
						
						<div class="actions">
							<input type="submit" class="btn primary" name="button" value="Add medication" />
							<button type="reset" class="btn">Clear form</button>
						</div>
						
					</fieldset>
				</form>
			</div>
			
			<jsp:include page="addSideModal.jsp"/>
			
		</div>
		
	</div>

<jsp:include page="template-foot.jsp" />