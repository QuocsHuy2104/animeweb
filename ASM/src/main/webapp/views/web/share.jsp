<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/8/2024
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Share Page</title>
</head>
<body>
<main>
    <div class="row" style="margin-top: 220px;
                            margin-right: 0px !important;
                            margin-left: 0px !important;">
        <div class="col-md-3">
        </div>

        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h5>Send Video your friend</h5>
                </div>

                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/share-page" method="post">
                        <div class="mb-3">
                            <label class="form-label" for="email">Your friend email</label>
                            <input type="email" class="form-control" name="email" id="email" />
                        </div>

                        <button class="btn btn-outline-primary">
                            Send
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-3"></div>
    </div>

</main>
</body>
</html>
