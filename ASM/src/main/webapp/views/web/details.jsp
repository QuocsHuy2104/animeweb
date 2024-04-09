<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details Page</title>
</head>
<body>
	<main style="margin: 0 100px">
		<div class="row" style="margin-top: 120px">
			<div class="col-9">
				<div class="video-page">
					<iframe width="1058" height="595" src="${url}"
						title="Em Là Kẻ Đáng Thương Lofi ● Những Bản Nhạc Lofi Chill Nhẹ Nhàng - Nhạc Lofi Buồn Hot Nhất Hiện Nay"
						frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
						referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
					<div class="video-wrap">
						<div class="video-title"></div>
						<div>
							<button class="btn-like"  >Like</button>
							<button class="btn-unlike">Unlike</button>
						</div>
					</div>

					<div class="video-des">Description</div>
				</div>
			</div>

			<div class="col-3">
				<div class="contents">
					<c:forEach var='item' items='${videos}'>
						<div class="thumnail">
							<a href="/ASM/detail/?id=${item.link}" style='text-decoration: none'> <img
								src="${pageContext.request.contextPath}/template/web/assets/img/${item.poster}"
								class="poster" alt="" />
								<div class="thumnail-title">
									<h5>${item.title}</h5>
									<span>${item.description}</span>
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