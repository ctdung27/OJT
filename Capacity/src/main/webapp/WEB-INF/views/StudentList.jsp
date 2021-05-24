<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Link bootstrap-->
<link rel="stylesheet" href="css/bootstrap.css" />
<!-- Link font awesome-->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />

<!-- Link js -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tagsinput/0.8.0/bootstrap-tagsinput.css">

<link
	href="https://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<!-- Link Css-->
<link rel="stylesheet" href="css/capacity.css" />
<link rel="stylesheet" href="css/student.css" />
</head>
<body class="capacity-content">

	<jsp:include page="header.jsp"></jsp:include>
	<div class="cp-content">
		<%
			request.setCharacterEncoding("UTF-8");
			String content;
			if (request.getAttribute("content") != null) {
				content = request.getAttribute("content") + ".jsp";
			} else {
				content = "Home.jsp";
			}
		%>
		<jsp:include page="<%=content%>"></jsp:include>
	</div>
	<script type="text/javascript" src="js/StudentList.js"></script>
	<script src="/js/cookie.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tagsinput/0.8.0/bootstrap-tagsinput.min.js"></script>
</body>
</html>