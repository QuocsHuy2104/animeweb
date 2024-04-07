<div id="header-render"></div>
<script type="text/babel">
        let element = <header>
            <div className="header-body">
                <div className="header-logo">
                    <img src="${pageContext.request.contextPath}/template/web/assets/icon/logo.png" alt="" /> Kyuubi
                </div>

                <div className="header-items">
                    <div className='item-anime'>
                        <a href="#">
                            Anime
                        </a>
                        <span></span>
                    </div>

                    <div className="item-manga">
                        <a href="#" className='text-decoration-none'>
                            Manga
                        </a>
                        <span></span>
                    </div>
                </div>

                <div className="header-search">
                    <input type="text" className='search-input' placeholder='Search here' />
                    <i className='bi bi-search-heart'></i>
                </div>

                <div className="header-list">
					<c:choose>
						<c:when test="${!empty sessionScope.user}"></c:when>
						<c:otherwise>
			 				<a href="${pageContext.request.contextPath}/common-login-page">
                        		<button className='btn-login'>
                           			 <span>Login</span>
                        		</button>
                    		</a>

						</c:otherwise>
					</c:choose>


					
                    <button className='btn-logout'>
                  		<span>
							<a href="/ASM/common-logout-page">Logout </a>
						</span>
                    </button>


                    <div className="list d-inline-block" onClick={showMenu}>
                        <i className="bi bi-list"></i>
                        <ul className='list-items d-none' id='menu-item'>
                            <li><a href="/ASM/profile"> Profile </a></li>
                            <li><a href="#"> Help </a></li>
                            <li><a href="#"> Feedback </a></li>
                            <li><a href="/ASM/common-change-page"> Change Password </a></li>
                            <li><a href="#"> Favtory </a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        ReactDOM.createRoot(document.getElementById("header-render")).render(element)
    </script>

<script type="text/javascript">
	var cnt = 1
	var showMenu = function() {
		if (cnt % 2 != 0)
			document.getElementById("menu-item").classList.remove("d-none");
		else
			document.getElementById("menu-item").classList.add("d-none")
		cnt++
	}
</script>