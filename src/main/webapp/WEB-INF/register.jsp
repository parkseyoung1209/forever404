<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/register.css">
    <script
    src="https://kit.fontawesome.com/ef885bd654.js"
    crossorigin="anonymous"
  ></script>
</head>
<body>
    <div class="registersection">
        <div class="background">
            <video class="video1" autoplay muted loop>
                <source src="/video/29777-377520664_medium.mp4">
            </video>
        </div>
        <h1><a href=""><i class="fa-solid fa-arrow-left"></i></a></h1>
<div class="formlist">
<form action="/register" method="post" id="regi_form" class="form">
<div class="user_id" id="user_id">
    <i class="fa-regular fa-user"></i>
    <input type="text" name="id" id="id" placeholder="회원 아이디" class="user_id" autocapitalize="off">
    <span id="userIdSpan">* 아이디는 무조건 영소문자, 숫자 포함 20자 이하여야합니다.</span>
</div>
<div class="user_password" id="user_password">
    <i class="fa-solid fa-lock"></i>
    <input type="password" name="password" id="password" placeholder="회원 비밀번호" class="user_password" autocapitalize="off">
    <span id="userPasswordSpan">* 비밀번호는 영소문자, 숫자, 특수문자 포함 8자리 이상이여야합니다.</span>
</div>
<div class="user_phone">
    <i class="fa-solid fa-mobile-screen-button"></i>
    <input type="text" name="phone" id="phone" placeholder="회원 휴대전화번호 (선택사항)" class="user_phone">
</div>
<div class="user_name">
    <i class="fa-regular fa-user"></i>
    <input type="text" name="name" id="name" placeholder="회원 이름" class="user_name" required>
    <span id="userNameSpan">* 이름은 필수입니다! (2글자 이상)</span>
</div>
<div class="user_email" id="user">
    <i class="fa-regular fa-envelope"></i>
    <input type="text" name="email" id="email" placeholder="회원 이메일 (선택사항)" class="user_email" autocapitalize="off">
</div>
<div class="user_birth" id="user">
    <i class="fa-solid fa-calendar-days"></i>
    <input type="date" name="birth" id="birth" placeholder="회원 생년월일 (선택사항)" class="user_birth">
</div>
<div class="submit_btn">
    <input type="submit" name="submit" id="submit" value="회원가입하기!">
</div>
</form>
</div>
<script src="register.js"></script>
</body>
</html>