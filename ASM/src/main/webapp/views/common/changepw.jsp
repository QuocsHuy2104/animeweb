<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change password Kyuubi</title>
</head>
<body>
	<div id="root"></div>
	<script type="text/babel">
        var element =
            <div className='change-page'>
                
                <div className="change-body">
                    <div className="change-img">
                        <img src="${pageContext.request.contextPath}/template/web/assets/icon/changepw-bg.jpg" alt="" />
                    </div>
                    <div className="change-form">
                        <div className="change-header">
                            Change Password for Kyuubi
                        </div>
                        <form action='${pageContext.request.contextPath}/common-change-page' method='post'>
                            <div className="change-group">
                                <div className="change-title">
                                    Current Password
                                </div>
                                <div className="change-input">
                                    <input type="password" name='txtCurrentPass' required />
                                </div>
                            </div>

                            <div className="change-group">
                                <div className="change-title">
                                    New Password
                                </div>
                                <div className="change-input">
                                    <input type="text" name='txtNewPass' required />
                                </div>
                            </div>

                            <div className="change-group">
                                <div className="change-title">
                                    Confirm Password
                                </div>
                                <div className="change-input">
                                    <input type="text" name='txtConfirmPass' required />
                                </div>
                            </div>

                            <div className="change-submit">
                                <button>
                                    <span>
                                        Submit
                                    </span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        ReactDOM.createRoot(document.getElementById("root")).render(element)
    </script>
</body>
</html>