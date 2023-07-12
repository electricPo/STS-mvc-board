<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 수정</h1>
	<c:set var="b" value="${board}"></c:set>
	<form action="${pageContext.request.contextPath}/board/modifyBoard" method="post">
	<input type="hidden" name="boardNo" value="${b.boardNo}">
		<table>
			<tr>
				<td>board_No</td>
				<td>
					<input type="text" readonly="readonly" name="boardNo" value="${b.boardNo}">
				</td>
			</tr>
			<tr>
				<td>localName</td>
				<td>
					<input type="text" name="localName" value=${b.localName}>
				</td>
			</tr>
			<tr>
				<td>board_title</td>
				<td>
					<input type="text" name="boardTitle" value=${b.boardTitle}>
				</td>
			</tr>
			<tr>
				<td>board_content</td>
				<td>
					<textarea rows="3" cols="50" name="boardContent">${b.boardContent}</textarea>
				</td>
			</tr>
			<tr>
				<td>member_id</td>
				<td>
					<input type="text" readonly="readonly" name="memberId" value="${b.memberId}">
				</td>
			</tr>
		</table>
		<button type="submit">수정</button>	
	</form>
</body>
</html>