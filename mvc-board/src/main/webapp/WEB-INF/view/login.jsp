<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	*{
	    padding: 0;
	    margin: 0;
	    border: none;
	}
	body{
	    font-size: 14px;
	    font-family: 'Roboto', sans-serif;
	}
		login-wrapper{
	    width: 400px;
	    height: 350px;
	    padding: 40px;
	    box-sizing: border-box;
	}
	
	.login-wrapper > h2{
	    font-size: 24px;
	    color: #6A24FE;
	    margin-bottom: 20px;
	}
	#login-form > input{
	    width: 100%;
	    height: 48px;
	    padding: 0 10px;
	    box-sizing: border-box;
	    margin-bottom: 16px;
	    border-radius: 6px;
	    background-color: #F8F8F8;
	}
	#login-form > input::placeholder{
	    color: #D2D2D2;
	}
	#login-form > input[type="submit"]{
	    color: #fff;
	    font-size: 16px;
	    background-color: #6A24FE;
	    margin-top: 20px;
	}
	#login-form > input[type="checkbox"]{
	    display: none;
	}
	#login-form > label{
	    color: #999999;
	}
	#login-form input[type="checkbox"] + label{
	    cursor: pointer;
	    padding-left: 26px;
	    background-image: url("checkbox.png");
	    background-repeat: no-repeat;
	    background-size: contain;
	}
	#login-form input[type="checkbox"]:checked + label{
	    background-image: url("checkbox-active.png");
	    background-repeat: no-repeat;
	    background-size: contain;
	}
</style>


<body>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const rememberCheck = document.getElementById("remember-check");
        const idInput = document.querySelector("input[name='id']");

        //로컬스토리지를 이용해 아이디 기억하기
        const rememberedId = localStorage.getItem("rememberedId");
        if (rememberedId) {
            idInput.value = rememberedId;
            rememberCheck.checked = true;
        }

        
        
        
		rememberCheck.addEventListener("change", function () {
        	
            if (rememberCheck.checked) {
                localStorage.setItem("rememberedId", idInput.value);
            } else {
                localStorage.removeItem("rememberedId");
            }
        });
        
		// 로그인 버튼 클릭 시 AJAX 요청을 보내는 부분
		// 로컬스토리지 아이디 저장 체크박스
        // 로그인 버튼 클릭시 ajax로 아이디와 패스워드를 보내 db와 비교(매퍼, 서비스) 다시 불러와서(id pw비교) 로그인성공시 로컬 스토리지에 저장(success) 처리  
        const loginBtn = document.getElementById("loginBtn");
        loginBtn.addEventListener("click", function () {
            const id = idInput.value;
            const pw = pwInput.value;
            
            // AJAX 요청 보내기
            fetch("/login", {
                method: "POST",
                body: JSON.stringify({ id: id, pw: pw }),
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    localStorage.setItem("rememberedId", id);
                    window.location.href = "/home"; // 로그인 성공 시 홈으로 이동
                } else {
                    alert("로그인 실패");
                }
            })
            .catch(error => {
                console.error("로그인 요청 에러:", error);
            });
        });
    });

</script>

    <div class=" container login-wrapper">
        <h2>Login</h2>
        <form method="post" action="/login">
            <input type="text" name="id" placeholder="id" value="${loginId}">
            <input type="password" name="pw" placeholder="pw">
            <label for="remember-check">
            	
                <input type="checkbox" id="remember-check" name="remember-check" value="on" checked="checked">
            </label>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</body>
</html>