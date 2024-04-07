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
        var element = <main className="container">
            <div className="row">
                <div className="col-5">
                    <video autoPlay muted loop id="myVideo">
                        <source src="${pageContext.request.contextPath}/template/web/assets/video/register.mp4" type="video/mp4" />
                    </video>
                </div>
                <div className="col-7">
                    <div className="thumnail">
                        <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-3.jpg" alt="" className="poster" />
                        <div>
                            <h5 className="title">
                                Title
                            </h5>
                            <span className="sub-title">
                                subtitle
                            </span>

                            <button>Unlike</button>
                            <button>Share</button>
                        </div>
                    </div>

                    <div className="thumnail">
                        <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-3.jpg" alt="" className="poster" />
                        <div>
                            <h5 className="title">
                                Title
                            </h5>
                            <span className="sub-title">
                                subtitle
                            </span>

                            <button>Unlike</button>
                            <button>Share</button>
                        </div>
                    </div>

                    <div className="thumnail">
                        <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-3.jpg" alt="" className="poster" />
                        <div>
                            <h5 className="title">
                                Title
                            </h5>
                            <span className="sub-title">
                                subtitle
                            </span>

                            <button>Unlike</button>
                            <button>Share</button>
                        </div>
                    </div>


                    <div className="thumnail">
                        <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-3.jpg" alt="" className="poster" />
                        <div>
                            <h5 className="title">
                                Title
                            </h5>
                            <span className="sub-title">
                                subtitle
                            </span>

                            <button>Unlike</button>
                            <button>Share</button>
                        </div>
                    </div>


                    <div className="thumnail">
                        <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-3.jpg" alt="" className="poster" />
                        <div>
                            <h5 className="title">
                                Title
                            </h5>
                            <span className="sub-title">
                                subtitle
                            </span>

                            <button>Unlike</button>
                            <button>Share</button>
                        </div>
                    </div>

                    <div className="thumnail">
                        <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-3.jpg" alt="" className="poster" />
                        <div>
                            <h5 className="title">
                                Title
                            </h5>
                            <span className="sub-title">
                                subtitle
                            </span>

                            <button>Unlike</button>
                            <button>Share</button>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        ReactDOM.createRoot(document.getElementById("root")).render(element)
    </script>
</body>
</html>