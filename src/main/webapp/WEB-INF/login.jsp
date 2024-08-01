<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
  </head>
  <body>
    <div class="login">
      <form class="loginsection" method="post">
        <div class="back_to_menu">
          <a href=""><i class="fa-solid fa-xmark"></i></a>
        </div>
        <div class="mainsbj">
          <h1>로그인</h1>
        </div>
        <div class="user_login">
          <i class="fa-regular fa-user"></i>
          <input
            type="text"
            class="user_id_input"
            id="id"
            name="id"
            placeholder="아이디"
          />
        </div>
        <div class="user_login">
          <i class="fa-solid fa-lock"></i>
          <input
            type="text"
            class="user_password_input"
            id="password"
            name="password"
            placeholder="비밀번호"
          />
        </div>
        <div class="login_btn">
          <input type="submit" value="로그인" id="loginBtn" />
        </div>
        <div class="kkt_login_btn">
          <img src="photo/kakao_login_large_wide.png" alt="" />
        </div>
      </form>
    </div>
    <button class="open_login">로그인</button>
    <script src="login.js"></script>
    <script
      src="https://kit.fontawesome.com/ef885bd654.js"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
