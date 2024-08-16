<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Calendar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}css/reset.css" />
   <link rel="stylesheet" href="${pageContext.request.contextPath}css/calander.css" />
    <script src="${pageContext.request.contextPath}js/index.global.js"></script>
  </head>
  <body>
    <header>
      <div class="addgroup">
        <button class="btn-add"><i class="fa-solid fa-plus"></i></button>
      </div>
      <div class="group" id="group1">
        <i class="fa-solid fa-user-group"></i>
        <span>그룹1</span>
      </div>
      <div class="group" id="group2">
        <i class="fa-solid fa-user-group"></i>
        <span>그룹2</span>
      </div>
      <div class="group" id="group3">
        <i class="fa-solid fa-user-group"></i>
        <span>그룹3</span>
      </div>
      <div class="group" id="group4">
        <i class="fa-solid fa-user-group"></i>
        <span>그룹4</span>
      </div>
      <div class="user">
        <i class="fa-regular fa-user"></i>
      </div>
    </header>
    <div id="calendar-container">
      <div id="calendar"></div>
    </div>

    <div id="myModal" style="display: none">
      <div id="modalContent">
        <header class="mdl-header">
          <p class="head-wrd">일정 추가하기</p>
          <i class="fa-solid fa-xmark" id="X"></i>
        </header>
        <button class="section" id="one"></button>
        <button class="section" id="two"></button>
        <button class="section" id="three"></button>
        <button class="section" id="four"></button>
        <button class="section" id="five"></button>
        <button class="section" id="six">추가</button>
      </div>
    </div>

    <div id="modal2" style="display: none">
      <div id="modalContent2">
        <header class="mdl-header2">
          <p class="head-wrd2">세부 일정 추가</p>
          <i class="fa-solid fa-xmark" id="X2"></i>
        </header>
        <div class="inpt-brder" id="title">
          <i class="fa-solid fa-pencil"></i>
          <input type="text" placeholder="일정 이름" class="tripinfo" />
        </div>
        <div class="inpt-brder">
          <i class="fa-solid fa-plane-departure"></i>
          <input
            type="date"
            placeholder="시작 날짜"
            class="tripinfo"
            max="9999-12-31"
          />
        </div>
        <div class="inpt-brder">
          <i class="fa-solid fa-plane-arrival"></i>
          <input
            type="date"
            placeholder="종료 날짜"
            class="tripinfo"
            max="9999-12-31"
          />
        </div>
        <div class="inpt-brder" id="date">
          <i class="fa-solid fa-coins"></i>
          <input type="text" placeholder="여행 총 경비" class="tripinfo" />
        </div>
        <div class="inpt-brder" id="date">
          <i class="fa-solid fa-helmet-safety"></i>
          <input type="text" placeholder="추후 추가예정" class="tripinfo" />
        </div>
        <div><button class="submit" id="final">추가하기</button></div>
      </div>
    </div>

    <script
      src="https://kit.fontawesome.com/ef885bd654.js"
      crossorigin="anonymous"
    ></script>
    <script src="/team project/calander.js"></script>
  </body>
</html>
