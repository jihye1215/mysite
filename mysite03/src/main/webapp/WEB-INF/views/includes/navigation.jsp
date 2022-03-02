<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div id="navigation">
			<ul>
				<c:choose>
					<c:when test = "${param.menu == 'gallery' }">
						<li><a href="${pageContext.request.contextPath}">김지혜</a></li>
						<li><a href="${pageContext.request.contextPath}/guestbook/index">방명록</a></li>
						<li><a href="${pageContext.request.contextPath}/guestbook/index/spa">방명록(SPA)</a></li>
						<li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
						<li class = "selected"><a href="${pageContext.request.contextPath}/gallery">갤러리</a></li>
					</c:when>
						<c:when test = "${param.menu == 'guestbook-spa' }">
						<li><a href="${pageContext.request.contextPath}">김지혜</a></li>
						<li><a href="${pageContext.request.contextPath}/guestbook/index">방명록</a></li>
						<li class = "selected"><a href="${pageContext.request.contextPath}/guestbook/index/spa">방명록(SPA)</a></li>
						<li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
						<li class = "selected"><a href="${pageContext.request.contextPath}/gallery">갤러리</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}">김지혜</a></li>
						<li><a href="${pageContext.request.contextPath}/guestbook/index">방명록</a></li>
						<li><a href="${pageContext.request.contextPath}/guestbook/index/spa">방명록(SPA)</a></li>
						<li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
						<li><a href="${pageContext.request.contextPath}/gallery">갤러리</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>