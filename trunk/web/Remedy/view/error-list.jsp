<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="alert-message warning fade in" data-alert="alert">
<a class="close" href="#">×</a>
<c:if test="${!(empty errors)}">
	<p style="font-size:medium; color:red">
	<strong>
		<c:forEach var="error" items="${errors}">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${error}
			<br/>
		</c:forEach>
	</strong>
	</p>
</c:if>
<c:if  test="${!(empty message)}">
	<p style="font-size:medium; color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		${message}
	</p>
</c:if>
</div>