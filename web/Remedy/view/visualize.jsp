<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-head.jsp" />

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