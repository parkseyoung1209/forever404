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
    <c:if test="${not empty user}">
      <jsp:include page="header.jsp" />

      <div>
        <div id="calendar-container">
          <div id="calendar"></div>
        </div>
      </div>

      <!--  
   <div>
    <h1>큰그룹 정보</h1>
			여행 이름 : <input type="text" id="testTitle" name="title"><br/>
			시작 날짜 : <input type="text" name="startDate"><br/>
			종료 날짜 : <input type="text" name="endDate"><br/>
			총 경비 : <input type="text" name="entireMoney"><br/>
			<input type="submit" value="큰그룹정보" id="add">
	</div>
	<h1>작은그룹 정보</h1>
		<form id="frm2">
			메모 : <input type="text" name="memo" id="memo"><br/>
			종류 : <input type="text" name="items" id="items"><br/>
			예약여부 : <input type="text" name="isReservation" id="isReservation"><br/>
			<input type="button" value="작은그룹" id="add2">
		</form>
	-->
      <div id="modal1" class="modal">
        <div class="modalcontent">
          <p class="close">&times</p>
          <h2>그룹 추가</h2>
          <hr />
          <br />
          <p>
            새로운 그룹에 이름을 부여해 <br />
            동료들과 함께해 보세요
          </p>
          <section class="mid">
            <h3>새 그룹 명</h3>
            <br />
            <input type="text" id="textbox" />
            <div class="add">
              <button id="addGroup" class="add2">만들기</button>
              <div id="successText"></div>
              >
            </div>
          </section>
        </div>
      </div>

      <div id="modal2" class="modal">
        <div class="modalcontent">
          <p class="close">&times</p>
          <h2>그룹 참가</h2>
          <hr />
          <br />
          <p>
            아래에 전달받은 그룹코드를 입력해<br />
            그룹에 참여해보세요
          </p>
          <section class="mid">
            <h3>그룹 코드</h3>
            <br />
            <input type="text" placeholder="ex) Forever404" id="inputatt" />
            <div class="add">
              <button id="attend" class="add2">그룹 참가하기</button>
            </div>
          </section>
        </div>
      </div>
     
      <div>
        <c:forEach items="${bsList}" var="bs">
          ${bs.title} ${bs.entireMoney} ${bs.startDate} ${bs.endDate}
        </c:forEach>
      </div>

      <div id="bigModal" style="display: none">
        <div id="modalContent3">
          <header class="mdl-header">
            <p class="head-wrd">일정 추가하기</p>
            <i class="fa-solid fa-xmark" id="X"></i>
          </header>
          <div id="addMemo"></div>
          <div class="modsection"></div>
          <button class="modsection" id="six">추가</button>
        </div>
      </div>

      <div id="detModal" style="display: none">
        <div id="modalContent4">
          <header class="mdl-header2">
            <p class="head-wrd2">세부 일정 추가</p>
            <i class="fa-solid fa-xmark" id="X2"></i>
          </header>
          <div class="inpt-brder" id="title">
            <i class="fa-solid fa-pencil"></i>
            <input
              type="text"
              placeholder="일정 이름"
              class="tripinfo"
              id="title2"
            />
          </div>
          <div class="inpt-brder">
            <i class="fa-solid fa-plane-departure"></i>
            <input
              type="date"
              placeholder="시작 날짜"
              class="tripinfo"
              max="9999-12-31"
              id="startDate"
            />
          </div>
          <div class="inpt-brder">
            <i class="fa-solid fa-plane-arrival"></i>
            <input
              type="date"
              placeholder="종료 날짜"
              class="tripinfo"
              max="9999-12-31"
              id="endDate"
            />
          </div>
          <div class="inpt-brder" id="date">
            <i class="fa-solid fa-coins"></i>
            <input
              type="text"
              placeholder="여행 총 경비"
              class="tripinfo"
              id="entireMoney"
            />
          </div>
          <div><button class="submit" id="final">추가하기</button></div>
        </div>
      </div>

      <script
        src="https://kit.fontawesome.com/ef885bd654.js"
        crossorigin="anonymous"
      ></script>
      <script>
        <%--
        	$("#addgroup").click(function () {
        	  $("#modal1").css("display", "block");
        	});
        	  $(".close").click(function () {
        	     $(".modal").css("display", "none");
        	  });
        	  $(window).click(function (event) {
        	     if ($(event.target).is(".modal")) {
        	       $(".modal").css("display", "none");
        	     }
        	  });
        	$(document).keydown(function (event) {
        	    if (event.keyCode == 27) {
        	       $(".modal").css("display", "none");
        	     }
        	  });
        	  --%>
      </script>

      <script>
        //	$(document).on('click', '.groupButton', function() {
        //       buttonId = $(this).attr('id');
        //       $.ajax({
        //       	type : 'post',
        //       	url : 'selectGroup',
        //      	data : {groupName : buttonId},
        //     		success : function(result) {

        //		}
        //     });
        // });

        $("#final").click(() => {
  
          $.ajax({
            type: "post",
            url: "/scheduleAdd",
            data: {
              title: $("#title2").val(),
              startDate: $("#startDate").val(),
              endDate: $("#endDate").val(),
              entireMoney: $("#entireMoney").val(),
            },
            success: function () {
            	const id = $("#title2").val();
            	$("#addMemo").html("<button>"+id+"</button>");
            },
          });
        });
        $("#add2").click(() => {
          $.ajax({
            type: "post",
            url: "/scheduleAdd2",
            data: {
              groupName: buttonId,
              memo: $("#memo").val(),
              items: $("#items").val(),
              isReservation: $("#isReservation").val(),
            },
            success: function () {},
          });
        });
      </script>

      <script>
        $("#addGroup").click(() => {
          const title = $("#textbox").val();
          $.ajax({
            type: "post",
            url: "/addGroup",
            data: "groupName=" + title,
            // <button><i class="fa-solid fa-plus"></i></button>
            success: function (result) {
              console.log(result);
              if (result == true) {
                $("#group").prepend(
                  "<button type='button' class='groupButton' id='" +
                    title +
                    "'>" +
                    "<i class='fa-solid fa-user-group'></i></button><span>" +
                    title +
                    "</span>"
                );
              } else {
                $("#successText").text("사용할 수 없는 그룹명입니다.");
              }
            },
          });
        });
      </script>
      <script>
      $(document).ready(function(){
    	  let groupName = localStorage.getItem('groupName');
    	 $.ajax({
    		 type: "post",
    	 	 url: "/selectAllSchedule",
    	 	 data : {groupName : groupName},
    	 	success: function() {
    	 		
    	 	}
    	 }) 
      });
      </script>
    </c:if>
    <c:if test="${empty user}">
      <script>
        location.reload();
        window.location.href = "redirect:/";
      </script>
    </c:if>
    <script src="${pageContext.request.contextPath}/js/calander.js"></script>
  </body>
</html>
