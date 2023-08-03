<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css파일 -->
	<link href="<%=request.getContextPath() %>/style.css" type="text/css" rel="stylesheet">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<!-- Chart.js cdn -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
</head>
<style>
	.body {
		margin: 0 auto;
        font-size: 1rem; 
        font-weight: 500;
        line-height: inherit;
        color: #212529;
        text-align: center;
        background-color: #fff;
	}
	table {
        margin: 5% auto;
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 8px;
        border: 1px solid #ddd;
    }
    tr:hover {
        background-color: #f2f2f2;
    }

</style>
<body class="body">
	<h1>FANDOM WITH FANS</h1>
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