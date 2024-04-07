<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile Kyuubi</title>
</head>
<body>
	<div id="root"></div>
    <script type="text/babel">
        var element =
            <div className='profile-page container'>
                <div className="row">
                    <div className="col-4">
                        <div className="wrap">
                            <div className="profile-img">
                                <img src="${pageContext.request.contextPath}/template/web/assets/icon/change-pw.png" alt="profile" />
                            </div>
                        </div>
                        <span className="profile-name">Quocs Huy</span>
                        <div style={{ margin: "20px 0 0 155px" }}>
                            <label htmlFor="files" className="btn-files">Edit Profile</label>
                            <input id="files" style={{ visibility: "hidden" }} type="file" />
                        </div>
                    </div>
                    <div className="col-8">
                        <div className="profile-form">
                            <form action="">
                                <div className="profile-group">
                                    <div className="profile-title">
                                        Username
                                    </div>
                                    <input type="text" className="profile-input" required />
                                </div>

                                <div className="profile-group">
                                    <div className="profile-title">
                                        FullName
                                    </div>
                                    <input type="text" className="profile-input" required />
                                </div>

                                <div className="profile-group">
                                    <div className="profile-title">
                                        Email
                                    </div>
                                    <input type="email" className="profile-input" required />
                                </div>

                                <div className="profile-submit">
                                    <button className="btn btn-success">
                                        <span>
                                            Save
                                        </span>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        ReactDOM.createRoot(document.getElementById("root")).render(element)
    </script>
</body>
</html>