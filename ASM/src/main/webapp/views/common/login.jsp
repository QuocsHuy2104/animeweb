<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Kisame</title>

</head>
<body>

	<div id="root"></div>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
	<script type="text/babel">
        var element =
            <div className='login-page'>
                <div className="login-header text-center">Login Kisame</div>

                <div className="login-body">

                    <img src="${pageContext.request.contextPath}/template/web/assets/icon/kokushibou.png" className='login-img' />

                    <div className='login-form'>
                        <form action="/common-login-page" method="post">
                            <div className="login-group">
                                <input type="text" className="" name='txtUsername'/>
                                <span style={{ marginTop: "40px" }}> Username or email </span>
                            </div>

                            <div className="login-group">
                                <input type="password" className="" name='txtPassword' />
                                <span > Password </span>
                            </div>

                            <div className="login-submit">
                                <button>
                                    <span>Login</span>
                                </button>
                            </div>

                            <div className="row mx-5">
                                <div className="col-6">
                                    <input type="checkbox" className="" id="remember" /> <label htmlFor="remember" className='text-white'>Remember password</label>
                                </div>
                                <div className="col-6">
                                    <a href="${pageContext.request.contextPath}/common-forgot-page" className="float-end"> <i>Forgot Passwrod?</i></a>
                                </div>
                            </div>
                        </form>

                        <div className="login-line">
                            <p>OR</p>
                            <span className='line'></span>
                        </div>

                        <div className='text-white text-center'>
                            Don't have an account? <a href="${pageContext.request.contextPath}/common-regis-page">Registration</a>
                        </div>

                    </div>
                </div>
            </div>
        ReactDOM.createRoot(document.getElementById("root")).render(element)
    </script>

</body>
</html>