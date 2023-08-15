<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>home</h1>
	<div>
		<a href="/board/boardList">board</a>
	</div>
	<div>
		<a href="/login">로그인</a>
	</div>
	<div>
		<button type="button" onclick="logout()">로그아웃</button>
	</div>
	<script>
	function logout() {
	    if (localStorage.getItem('rememberedId') !== null) {
	        localStorage.removeItem('rememberedId');

	        // 로컬스토리지 + 세션 정보 삭제
	        fetch('/logout', {
	            method: 'GET',
	            credentials: 'same-origin'
	        }).then(response => {
	            if (response.ok) {
	                alert('로그아웃 완료');
	                location.reload(); // 페이지 새로고침
	            } else {
	                alert('로그아웃 실패');
	            }
	        });
	    } else {
	        alert('로그인하세요!');
	    }
	}
	</script>
</body>
</html>