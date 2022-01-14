<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.poscoict.mysite.dao.GuestbookDao"%>
<%@page import="com.poscoict.mysite.vo.GuestbookVo"%>
<% 
	List<GuestbookVo> list = (List<GuestbookVo>)request.getAttribute("list");
	GuestbookDao dao = new GuestbookDao();
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%= request.getContextPath() %>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page = "/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath() %>/guestbook" method="post">
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
	<%
		int count = dao.selectCnt();
		for(GuestbookVo vo : list) {
	%>
				<ul>
					<li>
						<table>
							<tr>
								<td><%= count %></td>  
								<td><%= vo.getName() %></td>
								<td><%= vo.getReg_date() %></td>
							</tr>
							<tr>
								<td colspan = 4><%= vo.getMessage().replace("\n", "<br>") %></td>
							</tr>
							<td><a href="<%=request.getContextPath()%>/guestbook?no=<%= vo.getNo() %>&a=deleteform">삭제</a></td>
							</table>
	<%
		count--;
		}
	%>
					</li>
				</ul>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>