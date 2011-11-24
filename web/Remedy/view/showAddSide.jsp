<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="error-list.jsp" />
<h2>hi you are in the Side Effects schedule page!</h2>
<c:if test="${!(empty sideeffectslist)}">
	<p style="font-size:medium; color:red">
		<c:forEach var="sideeffects" items="${sideeffectslist}">
			<form id="${sideeffects.sideid}" action="delSide.do" method="Post">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
			<br/>
			${sideeffects.owner}
			<br/>
			${sideeffects.name}
			<br/>
			<input type="submit" class="btn primary" name="button" value="Delete Sideeffect" />
			<input type="hidden" name="sideid" value="${sideeffects.sideid}" />
			</form>
		</c:forEach>
	</p>
</c:if>
<c:if test="${!(empty message)}">
	<p style="font-size:medium; color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		${message}
	</p>
</c:if>