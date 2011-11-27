<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!(empty errors)}">
	<div class="alert-message error fade in" data-alert="alert">
	<a class="close" href="#">×</a>
	<p>
	<strong>
		<c:forEach var="error" items="${errors}">
			${error}
			<br/>
		</c:forEach>
	</strong>
	</p>
	</div>
</c:if>
<c:if  test="${!(empty message)}">
	<div class="alert-message success fade in" data-alert="alert">
	<a class="close" href="#">×</a>
	<p>
		${message}
	</p>
    </div>
</c:if>
