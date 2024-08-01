<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />

    <script
    src="https://kit.fontawesome.com/ef885bd654.js"
    crossorigin="anonymous"
  ></script>
</head>
<body>
    <div class="registersection">
        <div class="background">
            <video class="video1" autoplay muted loop>
                <source src="${pageContext.request.contextPath}/video/backgroundvideo.mp4">
            </video>
        </div>
        <h1><button onclick="history.go(-1)" id="back"><i class="fa-solid fa-arrow-left"></i></button></h1>
<div class="formlist">
<form action="" method="post" id="regi_form" class="form">
<div class="user_id" id="user_id"><label for="id">
    <i class="fa-regular fa-user"></i>
    <input type="text" name="id" id="id" placeholder="회원 아이디" class="user_id" autocapitalize="off" required>
    <span id="userIdSpan"></span>
</label>
</div>
<div class="user_password" id="user_password"><label for="password">
    <i class="fa-solid fa-lock"></i>
    <input type="password" name="password" id="password" placeholder="회원 비밀번호" class="user_password" autocapitalize="off" required>
    <span id="userPasswordSpan"></span>
</label>
</div>
<div class="user_phone"><label for="phone">
    <i class="fa-solid fa-mobile-screen-button"></i>
    <input type="text" name="phone" id="phone" placeholder="회원 휴대전화번호 (선택사항)" class="user_phone">
</label>
</div>
<div class="user_name"><label for="name">
    <i class="fa-regular fa-user"></i>
    <input type="text" name="name" id="name" placeholder="회원 이름" class="user_name" required>
    <span id="userNameSpan"></span>
</label>
</div>
<div class="user_email" id="user"><label for="email">
    <i class="fa-regular fa-envelope"></i>
    <input type="text" name="email" id="email" placeholder="회원 이메일 (선택사항)" class="user_email" autocapitalize="off">
</label>
</div>
<div class="user_birth" id="user"><label for="birth">
    <i class="fa-solid fa-calendar-days"></i>
    <input type="date" name="birth" id="birth" placeholder="회원 생년월일 (선택사항)" class="user_birth" max="9999-12-31">
</label>
</div>

<div class="submit_btn">
    <input type="submit" name="submit" id="submit" value="회원가입하기!">
</div>
</form>
</div>
<script src="${pageContext.request.contextPath}/js/register.js"></script>
</body>
</html>