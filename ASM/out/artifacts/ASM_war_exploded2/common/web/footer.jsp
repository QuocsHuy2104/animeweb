<div id="footer-render"></div>

<script type="text/babel">
 let element2 =
<footer>
	<div className="sitemap-footer container pt-4 mb-5">
		<div className="row ">
			<div className="col-3">
				<div className="footer-logo">
					<img src="${pageContext.request.contextPath}/template/web/assets/icon/logo.png" /> Kyuubi
				</div>
			</div>
			<div className="col-2">
				<h6>Navigation</h6>
				<ul>
					<li><a href="#"> Home </a></li>
					<li><a href="#"> Anime </a></li>
					<li><a href="#"> Manga </a></li>
					<li><a href="#"> Profile </a></li>
					<li><a href="#"> Feedback </a></li>
				</ul>
			</div>
			<div className="col-2">
				<h6>Follow US</h6>
				<div className="row">
					<div className="col-4">
						<i className="bi bi-facebook"></i>
					</div>
					<div className="col-4">
						<i className="bi bi-twitter"></i>
					</div>
					<div className="col-4">
						<i className="bi bi-instagram"></i>
					</div>
				</div>
			</div>
			<div className="col-2">
				<h6>Contact US</h6>
			</div>
			<div className="col-3 right">
				<img src="${pageContext.request.contextPath}/template/web/assets/icon/footer-two.png" alt="" />
			</div>
		</div>
	</div>

	<p>Kyuubi Movi 2024 - Quốc Huy Demo</p>
</footer>
        ReactDOM.createRoot(document.getElementById("footer-render")).render(element2)
</script>