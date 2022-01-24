<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page = "/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/guestbook" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 등록 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
					<c:set var = "count" value = "${fn:length(list)}" />
					<c:forEach items = "${list}" var = "vo" varStatus = "status">
						<table width = 510 border = 1>
							<tr>
								<td>${count-status.index}</td>
								<td>${vo.name}</td>
								<td>${vo.reg_date}</td>
							</tr>
							<tr>
								<td colspan = 4>${fn:replace(vo.message, newline, "<br/>")}</td>
							</tr>
							<td><a href="${pageContext.request.contextPath}/guestbook?no=${vo.no}&a=deleteform">삭제</a></td>
							</c:forEach>
						</table>
					</li>
				</ul>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>