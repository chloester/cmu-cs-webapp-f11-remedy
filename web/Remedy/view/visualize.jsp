<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-head.jsp" />

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load('visualization', '1', {packages: ['corechart']});
</script>
<script type="text/javascript">
	function drawVisualization() {
	// Some raw data (not necessarily accurate)
	var data = google.visualization.arrayToDataTable([
	['Month', 'Bolivia', 'Ecuador', 'Madagascar', 'Papua  Guinea','Rwanda', 'Average'],
	['2004/05', 165, 938, 522, 998, 450, 614.6],
	['2005/06', 135, 1120, 599, 1268, 288, 682],
	['2006/07', 157, 1167, 587, 807, 397, 623],
	['2007/08', 139, 1110, 615, 968, 215, 609.4],
	['2008/09', 136, 691, 629, 1026, 366, 569.6]
	]);

	// Create and draw the visualization.
	var comboChart = new google.visualization.ComboChart(document.getElementById('chart_div'));
	comboChart.draw(data, {
	title : 'Monthly Coffee Production by Country',
	vAxis: {title: "Cups"},
	hAxis: {title: "Month"},
	seriesType: "bars",
	series: {5: {type: "line"}}
	});
	}
	google.setOnLoadCallback(drawVisualization);
</script>

<jsp:include page="template-body.jsp" />

<div class="container">

	<jsp:include page="error-list.jsp" />

	<div class="content">
		<div class="page-header">
			<h1>Visualize</h1>
		</div>
		<div class="row">
			<div class="span16">

				<c:if test="${!(empty message)}">
					<p>
						${message}
					</p>
				</c:if>

				<div id='chart_div' style='width: 700px; height: 400px;'></div>
			</div>
		</div>

		<div class="clearfix">&nbsp;</div>

		<div class="row">
			<div class="span16">
				<h2>Medication Log</h2>

				<c:if test="${!(empty medicationlist)}">
					<c:forEach var="medication" items="${medicationlist}">
						<p>${medication.name} taken on ${medication.date} at ${medication.timeHr}:${medication.timeMin} ${medication.timeAMPM}</p>
					</c:forEach>
				</c:if>

				<c:if test="${(empty medicationlist)}">
					You have not logged any medications.
				</c:if>

				<h2>Side Effects Log</h2>

				<c:if test="${!(empty sideeffectslist)}">
					<c:forEach var="side" items="${sideeffectslist}">
						<p>${side.name} (${side.value}) on ${side.date} at ${side.timeHr}:${side.timeMin} ${side.timeAMPM}</p>
					</c:forEach>
				</c:if>

				<c:if test="${(empty sideeffectslist)}">
					You have not logged any side effects.
				</c:if>

			</div>
		</div>

	</div>

	<jsp:include page="template-foot.jsp" />