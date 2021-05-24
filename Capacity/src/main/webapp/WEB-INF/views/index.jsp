<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
    <title>Spring Boot</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Link bootstrap-->
    <link rel="stylesheet" href="css/bootstrap.css" />
    <!-- Link font awesome-->
    <link rel="stylesheet" 
    	  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" 
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" 
          crossorigin="anonymous" />
    <!-- Link Css-->
    <link rel="stylesheet" href="css/capacity.css" />
</head>
<body class="capacity-content">
 
		<jsp:include page="header.jsp"></jsp:include>
			<%
            request.setCharacterEncoding("UTF-8");
            String content;
            if(request.getAttribute("content")!=null){
                content = request.getAttribute("content")+".jsp";
            }else{
                content = "Home.jsp";
            }
        %>
        <jsp:include page="<%= content %>"></jsp:include>
</body>
</html>
