<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details Page</title>
</head>
<body>
	<div id="root"></div>
    <script type="text/babel">
        let element = <main>
            <div className="row" style={{ marginTop: "120px" }}>
                <div className="col-9">
                    <div className="video-page">
                        <div className="video-content">
                            <video controls style={{ width: "1050px", height: "595px" }} >
                                <source src="https://youtu.be/zJHfXNdCqyI?list=RDzJHfXNdCqyI&t=15" />
                            </video>
                        </div>

                        <div className="video-wrap">
                            <div className="video-title">
                                Titel video
                            </div>
                            <div>
                                <button className="btn-like">
                                    Like
                                </button>
                                <button className="btn-unlike">
                                    Unlike
                                </button>
                            </div>
                        </div>

                        <div className="video-des">
                            Description
                        </div>
                    </div>
                </div>

                <div className="col-3">
                    <div className="contents">
                        <div className="thumnail">
                            <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-2.jpg" className="poster" alt="" />
                            <div className="thumnail-title">
                                <h5>Title</h5>
                                <span>Tac Gia</span>
                            </div>
                        </div>

                        <div className="thumnail">
                            <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-2.jpg" className="poster" alt="" />
                            <div className="thumnail-title">
                                <h5>Title</h5>
                                <span>Tac Gia</span>
                            </div>
                        </div>

                        <div className="thumnail">
                            <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-2.jpg" className="poster" alt="" />
                            <div className="thumnail-title">
                                <h5>Title</h5>
                                <span>Tac Gia</span>
                            </div>
                        </div>


                        <div className="thumnail">
                            <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-2.jpg" className="poster" alt="" />
                            <div className="thumnail-title">
                                <h5>Title</h5>
                                <span>Tac Gia</span>
                            </div>
                        </div>

                        <div className="thumnail">
                            <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-2.jpg" className="poster" alt="" />
                            <div className="thumnail-title">
                                <h5>Title</h5>
                                <span>Tac Gia</span>
                            </div>
                        </div>

                        <div className="thumnail">
                            <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-2.jpg" className="poster" alt="" />
                            <div className="thumnail-title">
                                <h5>Title</h5>
                                <span>Tac Gia</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        ReactDOM.createRoot(document.getElementById("root")).render(element)
    </script>
</body>
</html>