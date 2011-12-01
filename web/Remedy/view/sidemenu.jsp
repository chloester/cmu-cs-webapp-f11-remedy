<script type="text/javascript">
function clearC(){
	var topbar = $(".top111");
	var num = topbar.length;
	for(var g = 0;g < num; g++){
		topbar[g].setAttribute("class","top111");		
	}
	document.cookie = "";
}
</script>

<div class="span5">
	<h3>What would you like to do?</h3>
	<ul class="unstyled">
		<li><a onclick="clearC()" href="addMed.do">Add a new medication</a></li>
		<li><a onclick="clearC()" href="logMed.do">Log a medication</a></li>
		<li><a onclick="clearC()" href="logSide.do">Log a side effect</a></li>
	</ul>
</div>