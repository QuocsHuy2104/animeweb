<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/8/2024
  Time: 8:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Manage User</title>
</head>
<body>
<main>
    <div class="bg-primary pt-10 pb-21"></div>
    <div class="px-6 mt-n22 container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-12">
                <div class='d-flex justify-content-between align-items-center'>
                    <div class="mb-2 mb-lg-0">
                        <h3 class="mb-0  text-white">Projects</h3>
                    </div>

                    <div>
                        <a class="btn btn-white" href="/#">Create New Project</a>
                    </div>
                </div>
            </div>

            <div class="mt-5 col-xl-3 col-lg-6 col-md-12 col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <div><h4 class="mb-0">Projects</h4></div>
                            <div class="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18"
                                     fill="currentColor">
                                    <path
                                            d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"></path>
                                </svg>
                            </div>
                        </div>
                        <div>
                            <h1 class="fw-bold">18</h1>
                            <p class="mb-0"><span class="text-dark me-2">2</span> Completed</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-5 col-xl-3 col-lg-6 col-md-12 col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <div><h4 class="mb-0">Projects</h4></div>
                            <div class="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18"
                                     fill="currentColor">
                                    <path
                                            d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"></path>
                                </svg>
                            </div>
                        </div>
                        <div>
                            <h1 class="fw-bold">18</h1>
                            <p class="mb-0"><span class="text-dark me-2">2</span> Completed</p>
                        </div>
                    </div>
                </div>
            </div>


            <div class="mt-5 col-xl-3 col-lg-6 col-md-12 col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <div><h4 class="mb-0">Projects</h4></div>
                            <div class="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18"
                                     fill="currentColor">
                                    <path
                                            d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"></path>
                                </svg>
                            </div>
                        </div>
                        <div>
                            <h1 class="fw-bold">18</h1>
                            <p class="mb-0"><span class="text-dark me-2">2</span> Completed</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-5 col-xl-3 col-lg-6 col-md-12 col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <div><h4 class="mb-0">Projects</h4></div>
                            <div class="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18"
                                     fill="currentColor">
                                    <path
                                            d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"></path>
                                </svg>
                            </div>
                        </div>
                        <div>
                            <h1 class="fw-bold">18</h1>
                            <p class="mb-0"><span class="text-dark me-2">2</span> Completed</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="row mt-5">
            <div class="col-md-12 col-12">
                <div class="card">
                    <div class="bg-white  py-4 card-header rounded-top-3">
                        <h5 class="mb-0">Active Projects</h5>
                    </div>

                    <div class="card-body bg-white">
                        <div>
                            <h6>Video Edition</h6>

                            <div class="row">


                                <div class="col-md-4 col-4 d-flex    justify-content-center align-items-center">
                                    <div class='poster rounded-3'>
                                        <img
                                                src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-1.jpg"
                                                alt=""/>
                                    </div>
                                </div>

                                <div class="col-md-8 col-8">
                                    <form action="/admin-manage/user-index" method='post'>
                                        <div class="mb-3">
                                            <label for="txtId" class='form-label'>Username:</label>
                                            <input type="text" class='form-control' id='txtId' name='userID'
                                                   value="${form.id}"/>
                                        </div>

                                        <div class="mb-3">
                                            <label for="txtPassword" class='form-label'>Password:</label>
                                            <input type="password" class='form-control' id='txtPassword'
                                                   name='userPass' value="${form.passWord}"/>
                                        </div>

                                        <div class="mb-3">
                                            <label for="txtFullname" class='form-label'>FullName:</label>
                                            <input type="text" class='form-control' id='txtFullname'
                                                   name='userFullname' value="${form.fullName}"/>
                                        </div>

                                        <div class="mb-3">
                                            <label for="txtEmail" class='form-label'>Email:</label>
                                            <input type="email" class='form-control' id='txtEmail'
                                                   name='userEmail' value="${form.email}"/>
                                        </div>

                                        <div>
                                            <button class="btn btn-outline-primary"
                                                    formaction='/ASM/admin-manage/user-update'>Update
                                            </button>
                                            <button class="btn btn-outline-primary ms-3"
                                                    formaction='/ASM/admin-manage/user-del'>
                                                Delete
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="mt-5">
                                <div class="table-responsive container-fluid">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">Username</th>
                                            <th scope="col">Password</th>
                                            <th scope="col">Fullname</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Roles</th>
                                            <th scope="col"></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${users}">
                                            <tr>
                                                <td>${item.id}</td>
                                                <td>${item.passWord}</td>
                                                <td>${item.fullName}</td>
                                                <td>${item.email}</td>
                                                <td>${item.admin == true ? "Admin" : "User"}</td>
                                                <td>
                                                    <a href="/ASM/admin-manage/user-edit/${item.id}">Edit</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
