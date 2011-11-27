<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-head.jsp" />

<div class="container">

	<jsp:include page="error-list.jsp" />

	<div class="content">
		<div class="page-header">
			<h1>My Medications</h1>
		</div>
		<div class="row">
			<div class="span11">

				<c:if test="${!(empty message)}">
					<p>
						${message}
					</p>
				</c:if>
				<c:if test="${!(empty medicationlist)}">

					<c:forEach var="medication" items="${medicationlist}">
						<form id="delmed" action="delMed.do" method="Post">
							<div class="clearfix">
								<h3>${medication.name} (${medication.purpose})</h3>
								<p>
									Take ${medication.freqSelect1} a day
									<c:if test="${medication.freqSelect2==''}">
										at any time
									</c:if>
									<c:if test="${medication.freqSelect2!=''}">
										every ${medication.freqSelect2} hour(s).
									</c:if>

									<br />
									On ${medication.dayChecks}
									<br />
									starting at ${medication.startTimeHour}:${medication.startTimeMin} ${medication.startAMPM}
									<br />
									${medication.dosage} ${medication.dosageUnit}
								</p>
								<input type="submit" class="btn small danger" name="button" value="Delete Medication" />
								<input type="hidden" name="medid" value="${medication.medid}" />
							</div>
						</form>
					</c:forEach>

				</c:if>
				<c:if test="${(empty medicationlist)}">
					You currently don't have any medications. How about adding one to the right?
				</c:if>

			</div>

			<jsp:include page="sidemenu.jsp" />
		</div>
		
	</div>

		<jsp:include page="template-foot.jsp" />