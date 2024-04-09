<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Favourite Page</title>
</head>
<body>
	 <div id="root"></div>
    <script type="text/babel">
        var element = <main className="container" style={{ marginTop: "120px"}}>
            <div className="row">
                <div className="col-5">
                    <video autoPlay muted loop id="myVideo">
                        <source src="${pageContext.request.contextPath}/template/web/assets/video/register.mp4" type="video/mp4" />
                    </video>
                </div>
                <div className="col-7">
                    <c:forEach var="item" items="${likes}">

                    <div className="thumnail">
                        <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-3.jpg" alt="" className="poster" />
                        <div>
                            <h5 className="title">
                                ${item.title}
                            </h5>
                            <span className="sub-title">
                                ${item.description}
                            </span>
                            <button formAction="">Unlike</button>
                            <button formAction="/ASM/share-page">Share</button>
                        </div>
                    </div>

                        </c:forEach>
                </div>
            </div>
        </main>
        ReactDOM.createRoot(document.getElementById("root")).render(element)
    </script>
</body>
</html>