<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 입력</h1>
	<form action="${pageContext.request.contextPath}/board/addBoard" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>player</th>
				<td>
					<input type="text" name="localName">
				</td>
			</tr>
			<tr>
				<th>boardTitle</th>
				<td>
					<input type="text" name="boardTitle">
				</td>
			</tr>
			<tr>
				<th>boardContent</th>
				<td>
					<textarea row="3" cols="50" name="boardContent"></textarea>
				</td>
			</tr>
			<!-- fileUpload -->
			<tr>
				<td>파일 업로드</td>
				<td><input type="file" name="multipartFile"></td>
			</tr>
				<th>memberId</th>
				<td>
					<input type="text" name="memberId">
				</td>
			</tr>
		</table>
		<button type="submit">추가</button>
	</form>
</body>
</html>