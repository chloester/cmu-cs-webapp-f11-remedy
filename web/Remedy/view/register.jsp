<jsp:include page="template-head.jsp" />

<jsp:include page="error-list.jsp" />

<div id="registerPageBody">
		<form method="post" action="register.do">
		<div id="userregister">New user register here</div>
		<table>
			<tr><td>Email Address:</td><td><div id="email">
				 <input type="text" name="userName" value="${registerform.userName}"/>
			</div></td></tr>
			<tr><td>Password:</td><td><div id="password">
				 <input type="password" name="password" value="${registerform.password}"/>
			</div></td></tr>
			<tr><td>Confirm password: </td><td><div id="confirmpassword">
				<input type="password" name="confirmpassword" value="${registerform.confirmpassword}"/>
			</div></td></tr>
			<tr><td>First Name: </td><td><div id="firstName">
				<input type="text" name="firstName" value="${registerform.firstName}"/>
			</div></td></tr>
			<tr><td>Last Name:</td><td><div id="lastName">
				 <input type="text" name="lastName" value="${registerform.lastName}"/>
			</div></td></tr></table>
			<!-- add more information below -->
			<table id="moreinfo">
					<tr><td>Gender:</td><td><div id="gender">
						 <select name="gender" style="height: 25px; width: 149px">
							<option>Male</option>
							<option>Female</option>
						</select>
					</div></td></tr>
					<tr><td>Country:</td><td><div id="country">
						 <input type="text" name="country" value="${registerform.country}"/>
					</div></td></tr>
					<tr><td>State:</td><td><div id="state">
						 <input type="text" name="state" value="${registerform.state}"/>
					</div></td></tr>
			</table>
			<div id="RegisterButton">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" name="button" value="register" />
			</div>
		</form>
</div>
<jsp:include page="template-foot.jsp" />