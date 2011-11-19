<jsp:include page="template-head.jsp" />

<%@ page import="databeans.User" %>
<%
	User user = (User) session.getAttribute("user");
%>

<div class="container">
	
	<jsp:include page="error-list.jsp"/>
	
	<div class="content">
		<div class="page-header">
			<h1>Add Side Effect</h1>
		</div>
		<div class="row">
			<div class="span14">
				<form method="post" action="addSide.do">
					<fieldset>
						<legend>Add a new side effect</legend>
						
						<div class="clearfix">
							<label for="name">Name</label>
							<div class="input">
								<input class="large" id="name" name="name" type="text" value="${addsideform.name}" />
								<span class="help-block">e.g., Dizziness, Energy</span>
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