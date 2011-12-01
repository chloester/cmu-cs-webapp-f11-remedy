<html>
<head>
	<title>Remedy - Track your medications and side effects</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
	<script src="js/jquery-ui-1.8.16.custom.min.js"></script>
	<script src="js/bootstrap-dropdown.js"></script>
	<script src="js/bootstrap-modal.js"></script>
	<script src="js/bootstrap-alerts.js"></script>
	<script src="js/medication-name.js"></script>
	<script src="js/sideeffect-name.js"></script>
	<script>
		$(function() {
			var d = new Date();
			var month = d.getMonth();
			var date = d.getDate();
			var year = d.getFullYear();
			console.log(month + " " + date + " " + year);
			$("#datepicker").value = (month<10?"0"+month:month) + "/" + (date<10?"0"+date:date) + "/" + year;
			$("#datepicker").datepicker({maxDate:"+0D"});	
		});
	</script>
	
	<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css">
	<link rel="stylesheet" href="css/web.css">
	<link rel="stylesheet" href="css/jquery-ui-1.8.16.custom.css">
	
