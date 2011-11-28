<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load('visualization', '1', {packages: ['corechart']});
</script>

<script type="text/javascript">
function drawVisualization() {

	var rawdata = new Array(${arraysize});
	var data = google.visualization.arrayToDataTable([
		['Month', 'Bolivia', 'Ecuador', 'Madagascar', 'Papua  Guinea','Rwanda', 'Average'],
		['2004/05', 165, 938, 522, 998, 450, 614.6],
		['2005/06', 135, 1120, 599, 1268, 288, 682],
		['2006/07', 157, 1167, 587, 807, 397, 623],
		['2007/08', 139, 1110, 615, 968, 215, 609.4],
		['2008/09', 136, 691, 629, 1026, 366, 569.6]
		]);

		var medname = "${medname}";
		var sidename = "${sidename}";
		var titlename = "Effects of " + medname + " on " + sidename;
		// Create and draw the visualization.
		var comboChart = new google.visualization.ComboChart(document.getElementById('chart_div'));
		comboChart.draw(data, {
			title : titlename,
			vAxis: {title: "Cups"},
			hAxis: {title: "Month"},
			seriesType: "bars",
			series: {5: {type: "line"}}
		});
	}
	google.setOnLoadCallback(drawVisualization);
</script>