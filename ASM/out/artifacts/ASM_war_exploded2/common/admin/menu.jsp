<div id="menu-render"></div>
<script type="text/babel">
let element = <div className="menu-page">
            <div className="logo">
                <h1 className="logo-name">Kyuubi</h1>
            </div>

            <div>
                <a href="#" className="menu-items">
                    <i className="bi bi-house-door" />
                    <h6> Home page </h6>
                </a>
            </div>

            <div className="menu-heading">
                Layout & page
            </div>

            <div className="accordion" id="accordionExample">
                <div className="accordion-item">
                    <h2 className="accordion-header">
                        <button className="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            <i className="bi bi-layers" />
                            Pages
                        </button>
                    </h2>
                    <div id="collapseOne" className="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                        <div className="accordion-body">
                            <ul>
                                <li><a href="">Profile</a></li>
                                <li><a href="">Settings</a></li>
                                <li><a href="">Users</a></li>
                                <li><a href="">Video Edit</a></li>
                                <li><a href="">Video List</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div className="accordion-item">
                    <h2 className="accordion-header">
                        <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            <i className="bi bi-lock" />
                            Authentication
                        </button>
                    </h2>
                    <div id="collapseTwo" className="accordion-collapse collapse" data-bs-parent="#accordionExample">
                        <div className="accordion-body">
                            <li><a href="">Sign In</a></li>
                            <li><a href="">Sign Up</a></li>
                            <li><a href="">Forget Password</a></li>
                        </div>
                    </div>
                </div>
            </div>

            <div >
                <a href="#" className="menu-items">
                    <i className="bi bi-layout-text-window-reverse"></i>
                    <h6>Layout</h6>
                </a>
            </div>

        </div>

ReactDOM.createRoot(document.getElementById("menu-render")).render(element);</script>