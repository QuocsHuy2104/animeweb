<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chu</title>
</head>
<body>
	<div id="content-render"></div>
	<script type="text/babel">

	let element3 = <main>
            <div className="banner">
                <video autoPlay muted loop id="myVideo">
                    <source src="${pageContext.request.contextPath}/template/web/assets/video/banner-video.mp4" type="video/mp4" />
                </video>
                <div className="banner-content">
                    <div className="title">Kokushibou</div>
                    <div className="subtitle">New create</div>
                    <button className="btn-learn">
                        Learn More
                    </button>
                </div>

                <div className="special">
                    <div className="special-title">
                        Special for you
                    </div>
                    <div className="row">
                        <div className="col-2">
                            <a href="" className="special-items">
                                <img src="${pageContext.request.contextPath}/template/web/assets/img/special-akatsuki.jpg" alt="" />
                            </a>
                        </div>
                        <div className="col-2">
                            <a href="" className="special-items">
                                <img src="${pageContext.request.contextPath}/template/web/assets/img/special-inosuke.jpg" alt="" />
                            </a>
                        </div>
                        <div className="col-2">
                            <a href="" className="special-items">
                                <img src="${pageContext.request.contextPath}/template/web/assets/img/special-killua.jpg" alt="" />
                            </a>
                        </div>
                        <div className="col-2">
                            <a href="" className="special-items">
                                <img src="${pageContext.request.contextPath}/template/web/assets/img/special-luffy.jpg" alt="" />
                            </a>
                        </div>
                        <div className="col-2">
                            <a href="" className="special-items">
                                <img src="${pageContext.request.contextPath}/template/web/assets/img/sppecial-fairytail.png" alt="" />
                            </a>
                        </div>
                        <div className="col-2">
                            <a href="" className="special-items">
                                <img src="${pageContext.request.contextPath}/template/web/assets/img/special-titan.jpg" alt="" />
                            </a>
                        </div>
                    </div>
                </div>

                <div className="featured container">
                    Featured Collection
                    <div className="row">
                        <div className="col-4">
                            <div className="featured-wrap">
                                <div className="featured-title"> <h5>The Best</h5> </div>
                                <div className="featured-subTitle"> <h6>Mystical anime</h6>  </div>
                                <div className="featured-items">
                                    <img src="${pageContext.request.contextPath}/template/web/assets/img/featured-s1.png" alt="" />
                                    <img src="${pageContext.request.contextPath}/template/web/assets/img/featured-s2.jpg" alt="" />
                                    <img src="${pageContext.request.contextPath}/template/web/assets/img/featured-s3.jpg" alt="" />
                                </div>
                            </div>
                        </div>
                        <div className="col-4">
                            <div className="featured-wrap">
                                <div className="featured-title"><h5>Top 2</h5></div>
                                <div className="featured-subTitle"> <h6>Anime</h6> </div>
                                <div className="featured-items">
                                    <img src="${pageContext.request.contextPath}/template/web/assets/img/featured-s4.jpg" alt="" />
                                    <img src="${pageContext.request.contextPath}/template/web/assets/img/featured-s5.jpg" alt="" />
                                    <img src="${pageContext.request.contextPath}/template/web/assets/img/featured-s6.jpg" alt="" />
                                </div>
                            </div>
                        </div>
                        <div className="col-4">
                            <div className="featured-wrap">
                                <div className="featured-title"><h5>The Best</h5></div>
                                <div className="featured-subTitle"><h6>Classic Anime</h6> </div>
                                <div className="featured-items">
                                    <img src="" alt="" />
                                    <img src="" alt="" />
                                    <img src="" alt="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
   ReactDOM.createRoot(document.getElementById("content-render")).render(element3)

    </script>
</body>
</html>