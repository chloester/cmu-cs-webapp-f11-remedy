<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	table{width:auto;margin-bottom:0px;padding:0;font-size:13px;border-collapse:collapse;}
	table th,table td{padding:0px !important;line-height:normal !important;text-align:left;}
	table th{padding-top:0px !important;font-weight:bold;vertical-align:middle;}
	table td{vertical-align:top;border-top:0px !important;}
	table tbody th{border-top:0px !important;vertical-align:top;}
	div #chart_div{margin-top:0px;margin-bottom:18px;}
	h3 {margin-top:18px;}
</style>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load('visualization', '1', {packages: ['corechart']});
</script>

<script type='text/javascript'>
google.load('visualization', '1', {'packages':['annotatedtimeline']});
google.setOnLoadCallback(drawChart);
function drawChart() {
	var rawdata = new Array();
	
	<c:if test="${!(empty medloglist)}">
		<c:forEach var="medication" items="${medloglist}">
			var datestring = "${medication.date}";
			var firstLoc = datestring.indexOf("/");
			var lastLoc = datestring.lastIndexOf("/");
			var month = datestring.slice(0,firstLoc);
			var day = datestring.slice(firstLoc+1,lastLoc);
			var year = datestring.slice(lastLoc+1);
			var hour;
			<c:if test="${medication.timeAMPM=='a.m.'}">
				hour = ${medication.timeHr} == 12 ? 0 : ${medication.timeHr};
			</c:if>
			<c:if test="${medication.timeAMPM=='p.m.'}">
				hour = ${medication.timeHr} == 12 ? 12 : (${medication.timeHr}+12);
			</c:if>
			rawdata.push([new Date(year, month-1, day, hour, ${medication.timeMin}), 1, "${medication.name}", "${medication.name}", null, undefined, undefined]);
		</c:forEach>
	</c:if>
	<c:if test="${!(empty sideloglist)}">
		<c:forEach var="side" items="${sideloglist}">
			var datestring = "${side.date}";
			var firstLoc = datestring.indexOf("/");
			var lastLoc = datestring.lastIndexOf("/");
			var month = datestring.slice(0,firstLoc);
			var day = datestring.slice(firstLoc+1,lastLoc);
			var year = datestring.slice(lastLoc+1);
			var hour;
			<c:if test="${side.timeAMPM=='a.m.'}">
				hour = ${side.timeHr} == 12 ? 0 : ${side.timeHr};
			</c:if>
			<c:if test="${side.timeAMPM=='p.m.'}">
				hour = ${side.timeHr} == 12 ? 12 : (${side.timeHr}+12);
			</c:if>
			rawdata.push([new Date(year, month-1, day, hour, ${side.timeMin}), 0, undefined, undefined, ${side.value}, undefined, undefined]);
		</c:forEach>
	</c:if>

	var data = new google.visualization.DataTable();
	data.addColumn('date', 'Date');
	data.addColumn('number', '${medname}');
	data.addColumn('string', 'title1');
	data.addColumn('string', 'text1');
	data.addColumn('number', '${sidename}');
	data.addColumn('string', 'title2');
	data.addColumn('string', 'text2');
	data.addRows(rawdata);

	var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('chart_div'));
	chart.draw(data, {displayAnnotations: true, max:10, min:0, dateFormat:'HH:mm MMMM dd, yyyy'});
	}
</script>