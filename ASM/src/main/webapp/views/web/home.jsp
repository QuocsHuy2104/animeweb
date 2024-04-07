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
	<main>
		<div class="banner">
			<video autoPlay muted loop>
				<source
					src="${pageContext.request.contextPath}/template/web/assets/video/banner-video.mp4"
					type="video/mp4" />
			</video>
			<div class="banner-content">
				<div class="title">Kokushibou</div>
				<div class="subtitle">New create</div>
				<button class="btn-learn">Learn More</button>
			</div>

			<div class="special">
				<div class="special-title">Special for you</div>
				<div class="row">
					<div class="col-2">
						<a href="" class="special-items"> <img
							src="${pageContext.request.contextPath}/template/web/assets/img/special-akatsuki.jpg"
							alt="" />
						</a>
					</div>
					<div class="col-2">
						<a href="" class="special-items"> <img
							src="${pageContext.request.contextPath}/template/web/assets/img/special-inosuke.jpg"
							alt="" />
						</a>
					</div>
					<div class="col-2">
						<a href="" class="special-items"> <img
							src="${pageContext.request.contextPath}/template/web/assets/img/special-killua.jpg"
							alt="" />
						</a>
					</div>
					<div class="col-2">
						<a href="" class="special-items"> <img
							src="${pageContext.request.contextPath}/template/web/assets/img/special-luffy.jpg"
							alt="" />
						</a>
					</div>
					<div class="col-2">
						<a href="" class="special-items"> <img
							src="${pageContext.request.contextPath}/template/web/assets/img/sppecial-fairytail.png"
							alt="" />
						</a>
					</div>
					<div class="col-2">
						<a href="" class="special-items"> <img
							src="${pageContext.request.contextPath}/template/web/assets/img/special-titan.jpg"
							alt="" />
						</a>
					</div>
				</div>
			</div>

			<div class="featured container">
				Featured Collection
				<div class="row">
					<div class="col-4">
						<div class="featured-wrap">
							<div class="featured-title">
								<h5>The Best</h5>
							</div>
							<div class="featured-subTitle">
								<h6>Mystical anime</h6>
							</div>
							<div class="featured-items">
								<img
									src="${pageContext.request.contextPath}/template/web/assets/img/featured-s1.png"
									alt="" /> <img
									src="${pageContext.request.contextPath}/template/web/assets/img/featured-s2.jpg"
									alt="" /> <img
									src="${pageContext.request.contextPath}/template/web/assets/img/featured-s3.jpg"
									alt="" />
							</div>
						</div>
					</div>
					<div class="col-4">
						<div class="featured-wrap">
							<div class="featured-title">
								<h5>Top 2</h5>
							</div>
							<div class="featured-subTitle">
								<h6>Anime</h6>
							</div>
							<div class="featured-items">
								<img
									src="${pageContext.request.contextPath}/template/web/assets/img/featured-s4.jpg"
									alt="" /> <img
									src="${pageContext.request.contextPath}/template/web/assets/img/featured-s5.jpg"
									alt="" /> <img
									src="${pageContext.request.contextPath}/template/web/assets/img/featured-s6.jpg"
									alt="" />
							</div>
						</div>
					</div>
					<div class="col-4">
						<div class="featured-wrap">
							<div class="featured-title">
								<h5>The Best</h5>
							</div>
							<div class="featured-subTitle">
								<h6>Classic Anime</h6>
							</div>
							<div class="featured-items">
								<img src="" alt="" /> <img src="" alt="" /> <img src="" alt="" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class='content-video container mt-5'>
				<div class="row">

					<c:forEach var='item' items='${videos}'>
						<div class="col-md-3">
							<a href="/ASM/detail/?id=${item.link}">
								<div class="card">
									<img
										src="${pageContext.request.contextPath}/template/web/assets/img/${item.poster}"
										alt="" class='poster-img' />
									<h5 class='title'>Title</h5>
									<div class='row'>
										<div class="col-md-5">
											<button>Like</button>
										</div>
										<div class="col-md-5">
											<button>Share</button>
										</div>
									</div>
								</div>
							</a>
						</div>
					</c:forEach>

				</div>
			</div>

		</div>
	</main>
</body>
</html>