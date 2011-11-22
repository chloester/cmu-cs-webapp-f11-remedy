<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="error-list.jsp" />
<h2>hi you are in the medication schedule page!</h2>
<c:if test="${!(empty medicationlist)}">
	<p style="font-size:medium; color:red">
		<c:forEach var="medication" items="${medicationlist}">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${medication.username}
			<br/>
			${medication.name}
			<br/>
			${medication.purpose}
			<br/>
			${medication.freqSelect1}
			<br/>
			${medication.freqSelect2}
			<br/>
			${medication.dayChecks}
			<br/>
			${medication.startTimeHour}
			<br/>
			${medication.startTimeMin}
			<br/>
			${medication.startAMPM}
			<br/>
			${medication.dosage}
			<br/>
			${medication.dosageUnit}
			<br/>
		</c:forEach>
	</p>
</c:if>
<c:if test="${!(empty message)}">
	<p style="font-size:medium; color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		${message}
	</p>
</c:if>