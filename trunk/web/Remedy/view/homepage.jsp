<jsp:include page="template-head.jsp" />
<h3>MDTracker Homepage</h3>
<jsp:include page="error-list.jsp"/><!--Hi Chloe, you could modify the position of jsp pages as you wish.-->
<jsp:include page="login.jsp"/>
<!-- additional html body or jsp files you want -->
<!--%@ page import="databeans.Photo"%--><!-- maybe we could use photos to decorate our HomePage.-->
<!--%
	Photo[] luckyalbum = (Photo[]) request.getAttribute("luckyalbum");
%-->
<jsp:include page="template-foot.jsp" />
