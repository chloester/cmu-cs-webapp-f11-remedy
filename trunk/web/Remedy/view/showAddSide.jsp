<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="error-list.jsp" />
<h2>hi you are in the Side Effects schedule page!</h2>
<c:if test="${!(empty sideeffectslist)}">
	<p style="font-size:medium; color:red">
		<c:forEach var="sideeffects" items="${sideeffectslist}">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${sideeffects.username}
			<br/>
			${sideeffects.name}
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