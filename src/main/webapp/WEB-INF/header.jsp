<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/reset.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}css/calander.css"
    />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}js/index.global.min.js"></script>
  </head>
  <body>
    <header>
      <div class="addgroup">
        <button id="addgroup"><i class="fa-solid fa-plus"></i></button>
      </div>

      <div class="modalgroup">
        <button id="groupmake" class="addgroup2">
          <i class="fa-solid fa-plus"></i>
          <p>그룹 생성</p>
        </button>
        
        

        <button id="grouppart" class="addgroup2">
          <i class="fa-solid fa-plus"></i>
          <p>그룹 참가</p>
        </button>
      </div>

      <div class="mymodal">
        <button type="button" onclick="location.href='/logout'" id="logout">
          로그아웃
        </button>
      </div>

      <div class="group" id="group"></div>
      <div class="user">
        <i class="fa-regular fa-user"></i>
      </div>
    </header>
    <script>
      $(document).ready(function () {
        $.ajax({
          type: "post",
          url: "/userGroup",
          success: function (list) {
            const groupList = list.map((item) => item.bigGroup);
            const nameList = groupList.map((value) => value.groupName);
            nameList.forEach((value) => {
              $("#group").append(
                "<button type='button' data-code='" +
                  value +
                  "'class='groupButton' id='" +
                  value +
                  "'>" +
                  "<i class='fa-solid fa-user-group'></i></button><span>" +
                  value +
                  "</span>"
              );
            });
            const button = document.querySelectorAll(".groupButton");
            button.forEach((e) => {
              e.addEventListener("click", () => {
                const code = e.getAttribute("data-code");
                localStorage.setItem("groupName", code);
                window.location.href = "/" + code;
              });
            });
          },
        });
      });
    </script>
  </body>
</html>
