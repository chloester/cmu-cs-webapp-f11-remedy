<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-head.jsp" />

<div class="container">

	<jsp:include page="error-list.jsp" />

	<div class="content">
		<div class="page-header">
			<h1>My Side Effects</h1>
		</div>
		<div class="row">
			<div class="span11">

				<c:if test="${!(empty message)}">
					<p>
						${message}
					</p>
				</c:if>
				<c:if test="${!(empty sideeffectslist)}">
					
					<p><a href="#" data-controls-modal="modal-from-dom-addSide" data-backdrop="true" data-keyboard="true" >Add a new side effect</a></p>

					<c:forEach var="sideeffects" items="${sideeffectslist}">
						<form id="delmed" action="delSide.do" method="Post">
							<div class="row">
								<div class="span9">
									<h3>${sideeffects.name}</h3>
								</div>
								<div class="span2">
									<input type="submit" class="btn small danger" name="button" value="Delete" />
									<input type="hidden" name="sideid" value="${sideeffects.sideid}" />
								</div>
							</div>
						</form>
					</c:forEach>

				</c:if>
				<c:if test="${(empty sideeffectslist)}">
					You currently don't have any side effects. How about adding one to the right?
				</c:if>

			</div>

			<jsp:include page="sidemenu.jsp" />
			<jsp:include page="addSideModal.jsp"/>
		</div>

	</div>

	<jsp:include page="template-foot.jsp" />