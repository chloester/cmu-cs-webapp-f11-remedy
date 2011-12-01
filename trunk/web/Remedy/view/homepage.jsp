<jsp:include page="template-head.jsp" />
<jsp:include page="template-body.jsp" />

<div class="container">

	<jsp:include page="error-list.jsp"/>

	<div class="hero-unit">
		<h1 class="brand">Remedy<sup>+</sup></h1>
		<p>Helping you choose better medications.</p>
		<p><a class="btn primary large" href="register.do">Sign up</a></p>
	</div>

	<div class="row">
		<div class="span5">
			<h2>Track</h2>
			<p>Remedy lets you keep track of all of your medications in one place.</p><br>
			<img class="thumbnail" src="images/schedule.jpg">
		</div>
		<div class="span5">
			<h2>Visualize</h2>
			<p>Log side effects and visualize how your medications are making you feel.</p><br>
			<img class="thumbnail" src="images/graph.jpg">
		</div>
		<div class="span5">
			<h2>Heal</h2>
			<p>Keep medications that are making you feel better, change the ones that are making you feel worse.</p>
			<img class="thumbnail" src="images/handpills.jpg">
		</div>
	</div>

	<jsp:include page="template-foot.jsp" />
