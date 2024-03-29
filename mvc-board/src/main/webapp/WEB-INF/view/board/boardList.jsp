<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FANDOM WITH FANS</title>
<jsp:include page="/layout/cdn.jsp"></jsp:include>
</head>

<body class="body">
	<h1>FANDOM WITH FANS</h1>
	<div>
		<a href="/home">home</a>
	</div>
	<div>
		<a href="/board/addBoard">게시글 입력</a>
	</div>
	
	<div>
		<c:forEach var="m" items="${localNameList}"> <!--형변환이 자동으로 들어간다  -->
			<a href="/board/boardList?localName=${m.localName}">${m.localName}(${m.cnt})</a>
		</c:forEach>
	</div>
	<div class="container">
		<table>
			<tr>
				<th>player</th>
				<th>boardTitle</th>
				<th>memberId</th>
				<th>createdate</th>
			</tr>
				<c:forEach var="b" items="${boardList}">
					<tr>
						<td>${b.localName}</td>
						<td><a href="/board/boardOne?boardNo=${b.boardNo}">${b.boardTitle}</a></td>
						<td>${b.memberId}</td>
						<td>${b.createdate}</td>
					</tr>
				</c:forEach>
		</table>
	</div>
	<c:if test="${currentPage > 1}">
		<a href="/board/boardList?currentPage=${currentPage-1}">이전</a>
	</c:if>
	<span>${currentPage}</span>
	<c:if test="${currentPage < lastPage}">
		<a href="/board/boardList?currentPage=${currentPage+1}">다음</a>
	</c:if>
</body>
</html>