<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>FANDOM WITH FANS</h1>
	<div>
		<c:forEach var="m" items="${localNameList}"> <!--형변환이 자동으로 들어간다  -->
			<a href="">${m.localName}(${m.cnt})</a>
		</c:forEach>
	</div>
</body>
</html>