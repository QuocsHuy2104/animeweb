<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Kisame</title>
</head>
<body>
	<div id="root"></div>
	<script type="text/babel">
        var element =
            <div className='regis'>
                <div className="regis-header">
                    Registration Kisame
                </div>

                <div className="regis-body">
                    <div className="regis-mp4">
                        <video autoPlay muted loop id="myVideo">
                            <source src="${pageContext.request.contextPath}/template/web/assets/video/register.mp4" type="video/mp4" />
                        </video>
                    </div>

                    <form action="" method="post">
                        <div className="regis-form">
                            <div className="regis-gruop">
                                <input type="text" className='regis-input' />
                                <span> Username </span>
                            </div>

                            <div className="regis-gruop">
                                <input type="password" className='regis-input' />
                                <span> Password </span>
                            </div>

                            <div className="regis-gruop">
                                <input type="text" className='regis-input' />
                                <span> Fullname </span>
                            </div>

                            <div className="regis-gruop">
                                <input type="text" className='regis-input' />
                                <span> Email </span>
                            </div>

                            <div className="regis-submit">
                                <button>
                                    <span>Registration</span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        ReactDOM.createRoot(document.getElementById("root")).render(element)
    </script>
</body>
</html>