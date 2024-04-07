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

	<div id='content'></div>

	<script type='text/babel'>
		let elementContent = <main>
            <div className="bg-primary pt-10 pb-21"></div>
            <div className="px-6 mt-n22 container-fluid">
                <div className="row">
                    <div className="col-lg-12 col-md-12 col-12">
                        <div className='d-flex justify-content-between align-items-center'>
                            <div className="mb-2 mb-lg-0">
                                <h3 className="mb-0  text-white">Projects</h3>
                            </div>

                            <div>
                                <a className="btn btn-white" href="/#">Create New Project</a>
                            </div>
                        </div>
                    </div>

                    <div className="mt-5 col-xl-3 col-lg-6 col-md-12 col-12">
                        <div className="card">
                            <div className="card-body">
                                <div className="d-flex justify-content-between align-items-center mb-3">
                                    <div><h4 className="mb-0">Projects</h4></div>
                                    <div className="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18" fill="currentColor">
                                            <path d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"></path>
                                        </svg>
                                    </div>
                                </div>
                                <div>
                                    <h1 className="fw-bold">18</h1>
                                    <p className="mb-0"><span className="text-dark me-2">2</span> Completed</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="mt-5 col-xl-3 col-lg-6 col-md-12 col-12">
                        <div className="card">
                            <div className="card-body">
                                <div className="d-flex justify-content-between align-items-center mb-3">
                                    <div><h4 className="mb-0">Projects</h4></div>
                                    <div className="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18" fill="currentColor">
                                            <path d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"></path>
                                        </svg>
                                    </div>
                                </div>
                                <div>
                                    <h1 className="fw-bold">18</h1>
                                    <p className="mb-0"><span className="text-dark me-2">2</span> Completed</p>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div className="mt-5 col-xl-3 col-lg-6 col-md-12 col-12">
                        <div className="card">
                            <div className="card-body">
                                <div className="d-flex justify-content-between align-items-center mb-3">
                                    <div><h4 className="mb-0">Projects</h4></div>
                                    <div className="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18" fill="currentColor">
                                            <path d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"></path>
                                        </svg>
                                    </div>
                                </div>
                                <div>
                                    <h1 className="fw-bold">18</h1>
                                    <p className="mb-0"><span className="text-dark me-2">2</span> Completed</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="mt-5 col-xl-3 col-lg-6 col-md-12 col-12">
                        <div className="card">
                            <div className="card-body">
                                <div className="d-flex justify-content-between align-items-center mb-3">
                                    <div><h4 className="mb-0">Projects</h4></div>
                                    <div className="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18" fill="currentColor">
                                            <path d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"></path>
                                        </svg>
                                    </div>
                                </div>
                                <div>
                                    <h1 className="fw-bold">18</h1>
                                    <p className="mb-0"><span className="text-dark me-2">2</span> Completed</p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div className="row mt-5">
                    <div className="col-md-12 col-12">
                        <div className="card">
                            <div className="bg-white  py-4 card-header rounded-top-3">
                                <h5 className="mb-0">Active Projects</h5>
                            </div>

                            <div className="card-body bg-white">
                                <div>
                                    <h6>Video Edition</h6>

                                    <div className="row">




                                        <div className="col-md-4 col-4 d-flex    justify-content-center align-items-center">
                                            <div className='poster rounded-3'>
                                                <img src="${pageContext.request.contextPath}/template/web/assets/img/slide-s-1.jpg" alt="" />
                                            </div>
                                        </div>

                                        <div className="col-md-8 col-8">
                                            <form method='post'>
                                                <div className="mb-3">
                                                    <label htmlFor="txtId" className='form-label'>Youtobe ID:</label>
                                                    <input type="text" className='form-control' id='txtId' name='ytbId' />
                                                </div>

                                                <div className="mb-3">
                                                    <label htmlFor="txtTitle" className='form-label'>Youtobe Title:</label>
                                                    <input type="text" className='form-control' id='txtTitle' name='ytbTitle' />
                                                </div>

                                                <div className="mb-3">
                                                    <label htmlFor="txtViews" className='form-label'>Views Count:</label>
                                                    <input type="number" className='form-control' id='txtViews' name='ytbViews' />
                                                </div>

                                                <div className="mb-3">
                                                    <label className='form-label'></label>
                                                    <div className="form-check form-check-inline">
                                                        <input
                                                            className="form-check-input"
                                                            type="checkbox"
                                                            value="true"
                                                        />
                                                        <label className="form-check-label">Active</label>
                                                    </div>
                                                    <div className="form-check form-check-inline">
                                                        <input
                                                            className="form-check-input"
                                                            type="checkbox"
                                                            value="false"
                                                        />
                                                        <label className="form-check-label">InActive</label>
                                                    </div>
                                                </div>

                                                <div className="form-floating mb-3">
                                                    <textarea className="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style={{ height: "100px" }}></textarea>
                                                    <label htmlFor="floatingTextarea2">Description</label>
                                                </div>

                                                <div>
                                                    <button className="btn btn-outline-primary" formAction=''>Create</button>
                                                    <button className="btn btn-outline-primary ms-3" formAction=''>Update</button>
                                                    <button className="btn btn-outline-primary ms-3" formAction=''>Delete</button>
                                                    <button className="btn btn-outline-primary ms-3" formAction=''>Reset</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>

                                    <div className="mt-5">
                                        <div className="table-responsive container-fluid">
                                            <table className="table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">Youtobe ID</th>
                                                        <th scope="col">Youtobe Title</th>
                                                        <th scope="col">Views Count</th>
                                                        <th scope="col">Status</th>
                                                        <th scope="col"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr className="">
                                                        <td scope="row">R1C1</td>
                                                        <td>R1C2</td>
                                                        <td>R1C3</td>
                                                        <td>R1C2</td>
                                                        <td>R1C3</td>
                                                    </tr>
                                                    <tr className="">
                                                        <td scope="row">Item</td>
                                                        <td>Item</td>
                                                        <td>Item</td>
                                                        <td>R1C2</td>
                                                        <td>R1C3</td>
                                                    </tr>
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
        </main >


        ReactDOM.createRoot(document.getElementById("content")).render(elementContent);
	</script>

</body>
</html>