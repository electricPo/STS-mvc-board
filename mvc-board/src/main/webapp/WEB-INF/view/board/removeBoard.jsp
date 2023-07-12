<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 삭제</h1>
	<c:set var="b" value="${board}"></c:set>
	<form action="${pageContext.request.contextPath}/board/removeBoard" method="post">
	<input type="hidden" name="boardNo" value="${b.boardNo}">
		<table>
			<tr>
				<td>board_No</td>
				<td>
					${b.boardNo}
				</td>
			</tr>
			<tr>
				<td>localName</td>
				<td>
					${b.localName}
				</td>
			</tr>
			<tr>
				<td>board_title</td>
				<td>
					${b.boardTitle}
				</td>
			</tr>
			<tr>
				<td>board_content</td>
				<td>
					${b.boardContent}
				</td>
			</tr>
			<tr>
				<td>member_id</td>
				<td>
					<input type="text" name="memberId" value="아이디를 입력하세요">
				</td>
			</tr>
		</table>
		<button type="submit">삭제</button>	
	</form>
</body>
</html>