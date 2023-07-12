<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="b" value="${board}"></c:set>
	<h1>게시글 상세 보기</h1>
	<a href="">수정</a>
	<a href="">삭제</a>
	
	<table>
		<tr>
			<td>localName</td>
			<td>${b.localName}</td>
		</tr>
		<tr>
			<td>board_title</td>
			<td>${b.boardTitle}</td>
		</tr>
		<tr>
			<td>board_content</td>
			<td>${b.boardContent}</td>
		</tr>
		<tr>
			<td>member_id</td>
			<td>${b.memberId}</td>
		</tr>
		<tr>
			<td>createdate</td>
			<td>${b.createdate}</td>
		</tr>
		<tr>
			<td>updatedate</td>
			<td>${b.updatedate}</td>
		</tr>
	</table>
	
	
</body>
</html>