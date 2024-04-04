<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password Kisame</title>
</head>
<body>

	<div id="root"></div>
	<script type="text/babel">
        var element =
            <div className='forgot-page'>
                <div className="forgot-body">
                    <div className="forgot-img">
                        <img src="${pageContext.request.contextPath}/template/web/assets/icon/forgot.png" alt="" />
                    </div>

                    <div className="forgot-form">
                        <div className="form-header">
                            Forgot Password
                        </div>

                        <div className='send-mail'>
                            <input onBlur={submit} type="email" placeholder='Email' id='txtEmail' name='txtEmail' />
                        </div>

                        <div className="wrap">
                            <form action="" method="post">
                                <div className="form-group">
                                    <input onBlur={submit} type="password" placeholder='New Password' id='txtNewPass' name='txtNewPass' />
                                </div>

                                <div className="form-group">
                                    <input onBlur={submit} type="password" placeholder='Authen Password' id='txtAuthen' name='txtAuthen' />
                                </div>

                                <div className="form-submit">
                                    <button>
                                        <span>Submit</span>
                                    </button>
                                </div>
                            </form>

                            <div className='form-icon'>
                                <img src="${pageContext.request.contextPath}/template/web/assets/icon/forgot-icon.png" />
                            </div>

                            <div className="my-otp d-none" id='my-otp'>
                                <input type="text" placeholder='OTP' name='txtOTP' />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        ReactDOM.createRoot(document.getElementById("root")).render(element)
    </script>

	<script type="text/javascript">
		var submit = function() {
			let email = document.getElementById('txtEmail').value
			let newPw = document.getElementById('txtNewPass').value
			let authen = document.getElementById('txtAuthen').value

			if (email != '' && newPw != '' && authen != '')
				document.getElementById('my-otp').classList.remove('d-none')
		}
	</script>

</body>
</html>