<div id="header-render"></div>
<script type="text/babel">
let element2 = <header>
            <div className="header-page">
                <div className="header-left">
                    <button className="btn-menu">
                        <i className="bi bi-list"></i>
                    </button>

                    <input type="text" className="inp-search" placeholder="Search" />
                </div>

                <div className="header-right">
                    <div className="avarta">
                        <img src="" alt="" />
                    </div>
                </div>
            </div>
        </header>

ReactDOM.createRoot(document.getElementById("header-render")).render(element2);
</script>