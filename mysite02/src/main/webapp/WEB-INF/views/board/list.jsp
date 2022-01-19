<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					<c:set var = "count" value = "${cnt-((pagenum-1)*5)}" />
					<c:forEach items = "${blist}" var = "vo" varStatus = "status">		
						<tr>
							<td>${count}</td>
							<c:set var = "count" value = "${count - 1}" />
							<td style = "text-align: left; padding-left: ${(vo.depth-1)*20}px">
							<c:if test = "${vo.depth ne 1}">
							<img src = '${pageContext.request.contextPath}/assets/images/reply.png'/>
							</c:if>
							<a href = '${pageContext.servletContext.contextPath}/board?no=${vo.no}&a=select'>
							${vo.title}</a></td>
							<td>${vo.userName}</td>
							<td>${vo.hit}</td>
							<td>${vo.regDate}</td>
							<c:if test = "${authUser.no eq vo.userNo}">
								<td><a href="${pageContext.servletContext.contextPath}/board?a=delete&no=${vo.no}"  class="del" style='background-image: url("${pageContext.servletContext.contextPath }/assets/images/recycle.png")'>삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				
				<div class="pager">
					<ul>
						<c:forEach begin = "1" end = "${newcnt}" step = "1" var = "page">
							<c:if test = "${pagenum eq page}">
								<li class = "selected">
							</c:if>
							<c:if test = "${pagenum ne page}">
								<li>
							</c:if>
							<c:if test = "${keyword eq null}">
								<a href = '${pageContext.servletContext.contextPath}/board?pagenum=${page}'>${page}</a></li>
							</c:if>
							<c:if test = "${keyword ne null}">
								<a href = '${pageContext.servletContext.contextPath}/board?pagenum=${page}&kwd=${keyword}'>${page}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</div>	
				
				<div class="bottom">
					<c:if test = "${not empty authUser.no}">
						<a href="${pageContext.request.contextPath}/board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>