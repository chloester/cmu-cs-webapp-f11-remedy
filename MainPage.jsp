<jsp:include page="template-head.jsp" />
<!-- jsp:include page="error-list.jsp" /-->

<%@ page import="databeans.Photo"%>

<%
	Photo[] luckyalbum = (Photo[]) request.getAttribute("luckyalbum");
%>
<div id="PageBody">
	<div id="Sidebar" class="chala-corner">
 	
		<form id="loginform" method="post" action="login.do">
			<div id="userlogin">User Login Here</div>
			<div id="UserName">
				User Name: <input type="text" name="userName" />
			</div>
			<div id="PassWord">
				Password : <input type="password" name="password" />
			</div>
			<div id="LoginButton">
				<input type="submit" name="button" value="Login" />
			</div>
			<div id="userregister">
				New user please <a href="register.do" colour="red">REGISTER</a>
				here.
			</div>
		</form>
	</div>
	<div id="MainBody">
		<div id="memo"> &nbsp &nbsp The photos in this album are all collected by me. 
		Even you dont want to register, please just enjoy them.
		                                                              -Hua Liang</div>
		                          
		<div id="gallery" class="ad-gallery">
			<div class="ad-image-wrapper"></div>
			<div class="ad-controls"></div>
			<div class="ad-nav">
				<div class="ad-thumbs">
					<ul class="ad-thumb-list">
						<li><a href="images/1.jpg"> <img
								src="images/thumbs/t1.jpg" class="image0"> </a></li>
						<li><a href="images/10.jpg"> <img
								src="images/thumbs/t10.jpg" title="A title for 10.jpg"
								
								class="image1"> </a></li>
						<li><a href="images/11.jpg"> <img
								src="images/thumbs/t11.jpg" title="A title for 11.jpg"
								longdesc="http://coffeescripter.com"
								
								class="image2"> </a></li>
						<li><a href="images/12.jpg"> <img
								src="images/thumbs/t12.jpg" title="A title for 12.jpg"
								
								class="image3"> </a></li>
						<li><a href="images/13.jpg"> <img
								src="images/thumbs/t13.jpg" title="A title for 13.jpg"
								
								class="image4"> </a></li>
						<li><a href="images/14.jpg"> <img
								src="images/thumbs/t14.jpg" title="A title for 14.jpg"
								
								class="image5"> </a></li>
						<li><a href="images/2.jpg"> <img
								src="images/thumbs/t2.jpg" title="A title for 2.jpg"
								
								class="image6"> </a></li>
						<li><a href="images/3.jpg"> <img
								src="images/thumbs/t3.jpg" title="A title for 3.jpg"
								
								class="image7"> </a></li>
						<li><a href="images/4.jpg"> <img
								src="images/thumbs/t4.jpg" title="A title for 4.jpg"
								
								class="image8"> </a></li>
						<li><a href="images/5.jpg"> <img
								src="images/thumbs/t5.jpg" title="A title for 5.jpg"
								
								class="image9"> </a></li>
						<li><a href="images/6.jpg"> <img
								src="images/thumbs/t6.jpg" title="A title for 6.jpg"
								
								class="image10"> </a></li>
						<li><a href="images/7.jpg"> <img
								src="images/thumbs/t7.jpg" title="A title for 7.jpg"
								
								class="image11"> </a></li>
						<li><a href="images/8.jpg"> <img
								src="images/thumbs/t8.jpg" title="A title for 8.jpg"
								
								class="image12"> </a></li>
						<li><a href="images/9.jpg"> <img
								src="images/thumbs/t9.jpg" title="A title for 9.jpg"
				
								class="image13"> </a></li>
					</ul>
				</div>
			</div>
		</div>

	</div>


</div>

<jsp:include page="template-foot.jsp" />
