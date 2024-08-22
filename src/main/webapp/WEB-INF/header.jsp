<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <c:if test="${empty token}">
          <button type="button" id="logout">로그아웃</button>
        </c:if>
        <c:if test="${not empty token}">
          <button type="button" id="logout2">로그아웃</button>
        </c:if>
      </div>
      <div class="group" id="group"></div>
      <div class="user">
        <i class="fa-regular fa-user"></i>
      </div>
    </header>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
      /* $(document).ready(function () {
        Kakao.init("416439531d0e4d8f33eb240c9b791ffb");*/
      $("#logout2").click(function () {
        $.ajax({
          type: "post",
          url: "/logout",
        });
      });
    </script>
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
