<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!(empty errors)}">
	<div class="alert-message warning fade in" data-alert="alert">
	<a class="close" href="#">×</a>
	<p style="font-size:medium; color:red">
	<strong>
		<c:forEach var="error" items="${errors}">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${error}
			<br/>
		</c:forEach>
	</strong>
	</p>
	</div>
</c:if>
<c:if  test="${!(empty message)}">
	<div class="alert-message block-message error fade in" data-alert="alert">
	<a class="close" href="#">×</a>
	<p style="font-size:medium; color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		${message}
	</p>
    </div>
</c:if>
