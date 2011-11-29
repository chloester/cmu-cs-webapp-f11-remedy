<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-head.jsp" />
<jsp:include page="visualize-script.jsp" />
<jsp:include page="template-body.jsp" />

<div class="container">

	<jsp:include page="error-list.jsp" />

	<div class="content">
		<div class="page-header">
			<h1>Visualize</h1>
		</div>
		<div class="row">
			<div class="span16">
				<div class="clearfix">
					<form action="visualize.do" method="POST">
						<div class="inline-inputs">
							Show me how 
							<select class="large" id="med" name="med" onclick="showMedication()">
								<c:forEach var="medication" items="${medicationlist}">
									<option value="${medication.name}">${medication.name}</option>
								</c:forEach>
							</select>
							affects my 
							<select class="large" id="side" name="side" onclick="showSideEffect()">
								<c:forEach var="side" items="${sideeffectslist}">
									<option value="${side.name}">${side.name}</option>
								</c:forEach>
							</select>
							<input type="submit" class="btn primary" name="button" value="Go" />
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="span16">
				<c:if test="${!(empty message)}">
					<p>
						${message}
					</p>
				</c:if>
				
				<div id='chart_div' style='width: 700px; height: 300px;'></div>
			</div>
		</div>
		
		<div class="row">
			<div class="span16">
				<h2>Medication Log</h2>

				<c:if test="${!(empty medloglist)}">
					<c:forEach var="medication" items="${medloglist}">
						<p>${medication.name} taken on ${medication.date} at ${medication.timeHr}:<c:if test="${medication.timeMin==0}">00</c:if><c:if test="${medication.timeMin!=0}">${medication.timeMin}</c:if>
							 ${medication.timeAMPM}</p>
					</c:forEach>
				</c:if>

				<c:if test="${(empty medloglist)}">
					You have not logged any entries for ${medname}.
				</c:if>

				<h2>Side Effects Log</h2>

				<c:if test="${!(empty sideloglist)}">
					<c:forEach var="side" items="${sideloglist}">
						<p>${side.name} (${side.value}) on ${side.date} at ${side.timeHr}:<c:if test="${side.timeMin==0}">00</c:if><c:if test="${side.timeMin!=0}">${side.timeMin}</c:if>
							${side.timeAMPM}</p>
					</c:forEach>
				</c:if>

				<c:if test="${(empty sideloglist)}">
					You have not logged any entries for ${sidename}.
				</c:if>

			</div>
		</div>

	</div>

	<jsp:include page="template-foot.jsp" />