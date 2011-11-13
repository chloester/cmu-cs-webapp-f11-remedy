<div id="loginbody">
	<div id="" class=""><!-- Hi Chole, you could define id and class as you wish for css -->
		<form id="loginform" method="post" action="login.do">
			<div id="">welcome to MDTracker</div><!-- just for fun -->
			<div id="loginemail">
				Email Address: <input type="text" name="emailaddress"  value="${loginform.emailaddress}"/>
			</div>
			<div id="password">
				Password : <input type="password" name="password" value="${loginform.password}"/>
			</div>
			<div id="loginbutton">
				<input type="submit" name="button" value="login" />
			</div>
			<div id="registerbutton">
			   <input  type="submit" name="button" value="register"/>
			</div>
		</form>
	</div>
</div>
