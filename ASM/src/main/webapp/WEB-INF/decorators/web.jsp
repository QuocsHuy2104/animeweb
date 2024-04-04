<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><sitemesh:write property="title" /></title>

<!-- My Styles  -->


<link rel="stylesheet" style="text/css"
	href="${pageContext.request.contextPath}/template/web/css/profile.css" />

<link rel="stylesheet" style="text/css"
	href="${pageContext.request.contextPath}/template/web/css/details.css" />

<link rel="stylesheet" style="text/css"
	href="${pageContext.request.contextPath}/template/web/css/fav.css" />

<link rel="stylesheet" style="text/css"
	href="${pageContext.request.contextPath}/template/web/css/style.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/template/web/js/app.js"></script>



<!-- Close My Styles -->
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


</head>
<body>


	<%@ include file="/common/web/header.jsp"%>

	<sitemesh:write property="body" />

	<%@ include file="/common/web/footer.jsp"%>


	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>

</body>
</html>