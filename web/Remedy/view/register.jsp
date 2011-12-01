<jsp:include page="template-head.jsp" />
<jsp:include page="template-body.jsp" />

<div class="container" style="width:820px;">
	
	<jsp:include page="error-list.jsp" />
	
	<div class="content">
		<div class="page-header">
			<h1>Register</h1>
		</div>
		<div class="row">
			<div class="span14">
				<form method="post" action="register.do">
					<fieldset>
						<legend>Fill out the information below to create a new account</legend>
						
						<div class="clearfix">
							<label for="userName">Email</label>
							<div class="input">
								<input class="large" id="userName" name="userName" type="text" value="${registerform.userName}" />
							</div>
						</div>
						
						<div class="clearfix">
							<label for="password">Password</label>
							<div class="input">
								<input class="large" id="password" name="password" type="password" value="${registerform.password}" />
							</div>
						</div>
						
						<!-- TODO: Use AJAX to check password, highlight text input if do not match -->
						<div class="clearfix">
							<label for="confirmpassword">Confirm password</label>
							<div class="input">
								<input class="large" id="confirmpassword" name="confirmpassword" type="password" value="${registerform.confirmpassword}" />
							</div>
						</div>
						
						<div class="clearfix">
							<label for="firstName">First name</label>
							<div class="input">
								<input class="medium" id="firstName" name="firstName" type="text" value="${registerform.firstName}" />
							</div>
						</div>
						
						<div class="clearfix">
							<label for="lastName">Last name</label>
							<div class="input">
								<input class="medium" id="lastName" name="lastName" type="text" value="${registerform.lastName}" />
							</div>
						</div>
						
						<div class="clearfix">
							<label for="gender">Gender</label>
							<div class="input">
								<select class="medium" id="gender" name="gender" value="${registerform.gender}">
									<option>Male</option>
									<option>Female</option>
									<option>Prefer not to say</option>
								</select>
							</div>
						</div>
						
						<div class="actions">
							<input type="submit" class="btn primary" name="button" value="Register" />
						</div>
						
					</fieldset>
				</form>
			</div>
		</div>
		
	</div>
</div>
<jsp:include page="template-foot.jsp" />