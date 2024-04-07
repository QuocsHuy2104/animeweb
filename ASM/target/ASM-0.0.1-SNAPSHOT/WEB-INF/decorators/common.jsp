<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><sitemesh:write property="title"/></title>
<!-- Bootstrap CSS v5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<script src="https://unpkg.com/react@18/umd/react.development.js"
	crossorigin></script>
<script
	src="https://unpkg.com/react-dom@18/umd/react-dom.development.js"
	crossorigin></script>
<script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/template/web/css/login.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/template/web/css/regis.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/template/web/css/forgot.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/template/web/css/change.css">
</head>
<body>
	 <sitemesh:write property="body"/>
</body>
</html>