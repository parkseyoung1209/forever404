<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/developer.css" />
    <script
      src="https://kit.fontawesome.com/ef885bd654.js"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <header id="header">
      <a href="semi.html">
        <h1 class="nav-link">
          Forever <span class="red">4</span><span class="white">0</span
          ><span class="red">4</span>
        </h1></a
      >
      <nav id="a2">
        <button
          type="button"
          class="nav-link"
          onclick="location.href='/developer'"
        >
          개발자
        </button>
        <button type="button" class="nav-link" id="login">로그인</button>
        <button
          type="button"
          class="nav-link"
          onclick="location.href='/register'"
        >
          회원가입
        </button>
      </nav>
    </header>

    <div class="main">
      <section id="section1">
        <h1>Forever 404는 협업하며</h1>
        <h1>성장하는 유망주들입니다.</h1>
        <br /><br />
        <p>따뜻한 분위기와, 열정 가득한 팀워크,</p>
        <p>그리고 소통하는 협업으로 지금도 성장하고있습니다.</p>
      </section>

      <section id="section2"></section>
    </div>
    <div class="profile">
      <section id="back-end">
        <h1>back-end Members</h1>
        <section class="members">
          <section id="member1">
            <img src="${pageContext.request.contextPath}/image/family1.jpg" alt="" />
            <h2>윤유진(팀장)</h2>
            <ul>
              <li>한일1 : (사용한언어)</li>
              <li>한일2 : (구축한 코드)</li>
              <li>한일3 : (구축한 코드)</li>
              <li>한일4 : (구축한 코드)</li>
              <li>한일5 : (구축한 코드)</li>
            </ul>
          </section>
          <section id="member2">
            <img src="${pageContext.request.contextPath}/image/family2.jpg"  alt="" />
            <h2>박세영</h2>
            <ul>
              <li>한일1 : (사용한언어)</li>
              <li>한일2 : (구축한 코드)</li>
              <li>한일3 : (구축한 코드)</li>
              <li>한일4 : (구축한 코드)</li>
              <li>한일5 : (구축한 코드)</li>
            </ul>
          </section>
          <section id="member3">
            <img src="${pageContext.request.contextPath}/image/family3.jpg"  alt="" />
            <h2>김진주</h2>
            <ul>
              <li>한일1 : (사용한언어)</li>
              <li>한일2 : (구축한 코드)</li>
              <li>한일3 : (구축한 코드)</li>
              <li>한일4 : (구축한 코드)</li>
              <li>한일5 : (구축한 코드)</li>
            </ul>
          </section>
        </section>
      </section>

      <section id="front-end">
        <h1>front-end Members</h1>
        <section class="members">
          <section id="member4">
            <img src="${pageContext.request.contextPath}/image/family1.jpg" alt="" />
            <h2>장영태</h2>
            <ul>
              <li>한일1 : (사용한언어)</li>
              <li>한일2 : (구축한 코드)</li>
              <li>한일3 : (구축한 코드)</li>
              <li>한일4 : (구축한 코드)</li>
              <li>한일5 : (구축한 코드)</li>
            </ul>
          </section>
          <section id="member5">
            <img src="${pageContext.request.contextPath}/image/family2.jpg"  alt="" />
            <h2>배영운</h2>
            <ul>
              <li>한일1 : (사용한언어)</li>
              <li>한일2 : (구축한 코드)</li>
              <li>한일3 : (구축한 코드)</li>
              <li>한일4 : (구축한 코드)</li>
              <li>한일5 : (구축한 코드)</li>
            </ul>
          </section>
        </section>
        <section id="blank">
          <h1>　　　　　</h1>
        </section>
      </section>
    </div>

    <div class="modal">
      <div class="modal_body">
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
            required
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
            required
          />
        </div>
        <div class="login_btn">
          <input type="submit" value="로그인" />
        </div>
        <div class="kkt_login_btn">
          <img src="semiResources/kakao_login_large_wide.png" alt="" />
        </div>
      </div>
    </div>

    <script>
      document.addEventListener("DOMContentLoaded", () => {
        const observerOptions = {
          root: null,
          rootMargin: "0px",
          threshold: 0.1,
        };

        const observer = new IntersectionObserver((entries) => {
          entries.forEach((entry) => {
            if (entry.isIntersecting) {
              entry.target.classList.add("fade-in");
              entry.target.classList.remove("fade-out");
            } else {
              entry.target.classList.add("fade-out");
              entry.target.classList.remove("fade-in");
            }
          });
        }, observerOptions);

        const elements = document.querySelectorAll(
          "#back-end .members section"
        );
        elements.forEach((element) => {
          observer.observe(element);
        });

        const elements2 = document.querySelectorAll(
          "#front-end .members section"
        );
        elements2.forEach((element) => {
          observer.observe(element);
        });
      });

      const modal = document.querySelector(".modal");
      const btnOpenModal = document.querySelector("#login");

      btnOpenModal.addEventListener("click", () => {
        modal.style.display = "flex";
      });

      document.addEventListener("keydown", (e) => {
        if (e.key === "Escape" && modal.style.display === "flex") {
          modal.style.display = "none";
        }
      });
    </script>
  </body>
</html>