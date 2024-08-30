<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/reset.css" />
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/kakaomap2.css" />
<script src="https://kit.fontawesome.com/ef885bd654.js" crossorigin="anonymous"></script>
</head>
<body>
    <header>
      <div class="search_menu">
        <div>
          <form onsubmit="searchLocalPlaces(); return false;">
           <input type="text" id="keyword" size="15" placeholder="장소 검색">
                       <button type="submit" id="bttn">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button> </input>

          </form>
        </div>
      </div>

      <section>
        <button id="button1">&#10094;</button>

        <button id="button2">&#10095;</button>
      </section>
      
      

      <div class="testtesttest">
      <div id="testI">
        <i class="fa-solid fa-location-dot" id="testI1"><span class="testIP">장소</p></a></i><input id="serviceName" placeholder="KH정보교육원"></input><br />
        <i class="fa-solid fa-turn-up" id="testI2"><span class="testIP">위치</p></i><input id="serviceJibun" placeholder="서울시 강남구 테헤란로 14"></input><br />
        <i class="fa-solid fa-phone" id="testI3"><span class="testIP">연락처</p></i><input id="servicePhone" placeholder="1544-9970"></input><br /><hr>
        </div>
        <p class="headerP">
          예약
          <select id="isReservation">
            <option value="N">N</option>
            <option value="Y">Y</option>
          </select>
        </p>
        <p class="headerP">
          시간
          <select id="time">
            <option value="0">0시</option>
            <option value="1">1시</option>
            <option value="2">2시</option>
            <option value="3">3시</option>
            <option value="4">4시</option>
            <option value="5">5시</option>
            <option value="6">6시</option>
            <option value="7">7시</option>
            <option value="8">8시</option>
            <option value="9">9시</option>
            <option value="10">10시</option>
            <option value="11">11시</option>
            <option value="12">12시</option>
            <option value="13">13시</option>
            <option value="14">14시</option>
            <option value="15">15시</option>
            <option value="16">16시</option>
            <option value="17">17시</option>
            <option value="18">18시</option>
            <option value="19">19시</option>
            <option value="20">20시</option>
            <option value="21">21시</option>
            <option value="22">22시</option>
            <option value="23">23시</option>
          </select>
        </p>
		
         <p id="memoP" class="headerP">메모</p>
          <textarea id="memo" class="memo" rows="1"></textarea><hr>
		<button id="gptOpen">선택이 어려우신가요?</button>
        <input type="submit" value="추가하기" id="ssTest" />
      </div>
    </header>
	
	<section id="gptModal">
		          <h2 id=gptH2>궁금하신 걸 질문해보세요!</h2>
          <textarea id="gptTest" class="memo" rows="1"></textarea>
          <input type="submit" value="입력" id="gpt" />
          <div id="gptAsk"></div>
	</section>

<div class="map_wrap">
	<div id="map" style="width:100%;height:100vh;position:relative;overflow:hidden;"></div>
	 <ul id="category">
        <li id="AT4"> 
        	<i class="fa-solid fa-landmark"></i>
           <!-- <span class="category_bg visit"></span> -->
            <span>관광명소</span>
        </li>       
        <li id="FD6"> 
        	<i class="fa-solid fa-utensils"></i>
           <!-- <span class="category_bg food"></span> -->
            <span>음식점</span>
        </li>  
        <li id="CE7"> 
       		<i class="fa-solid fa-mug-saucer"></i>
           <!-- <span class="category_bg cafe"></span> -->
            <span>카페</span>
        </li>  
        <li id="AD5"> 
        	<i class="fa-solid fa-house"></i>
           <!-- <span class="category_bg hotel"></span> -->
            <span>숙박</span>
        </li>      
    </ul>
</div>

<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=416439531d0e4d8f33eb240c9b791ffb&libraries=services"></script>
<script src="${pageContext.request.contextPath}/js/kakaomap2.js"></script>

    <script>
      $("#button1").click(function () {
        $("#category").animate({ marginLeft: "-300px" }, 800);
        $("header").animate({ marginLeft: "-300px" }, 800);
        $("#button1")
          .animate({ marginLeft: "-300px" }, 800)
          .promise()
          .done(function () {
            $("#button1").hide();
            $("#button2").show();
            $("#button2").css("marginLeft", "0px");
          });
      });

      $("#button2").click(function () {
        $("#category").animate({ marginLeft: "0px" }, 800);
        $("header").animate({ marginLeft: "0px" }, 800);
        $("#button2")
          .animate({ marginLeft: "300px" }, 800)
          .promise()
          .done(function () {
            $("#button2").hide();
            $("#button1").show();
            $("#button1").css("marginLeft", "0px");
          });
      });
      
      <%--
      $("#gptOpen").click(function () {
    	  const moving = $("#gptModal").css("display");
    	    if (moving === "none") {
        	  $("#gptModal").css("display", "block")
    	  } else {
    		  $("#gptModal").css("display", "none")
    	  }
      });--%>
      
      $("#gptOpen").click(function () {
    	    const location = $("#gptModal").css("margin-left");
    	    
    	    const locationValue = parseInt(location, 10);
    	    
    	    if (locationValue === 320) {
    	        $("#gptModal").animate({ "margin-left": "-320px" }, 600);
    	    } else {
    	        $("#gptModal").animate({ "margin-left": "320px" }, 400);
    	    }
    	});

      const apiUrl = "https://api.openai.com/v1/chat/completions";
      const apiKey = 'sk-XOqUmArii_dNjTqmEdt0U7FhdfvS2KRrJhh0I3W79GT3BlbkFJTbqKK1RFu1-Q1TlgWGDLm7mx5nWlIWCmKK45XDevgA';
      const gptTest = document.querySelector("#gptTest");
      
      let conversationHistory = []; // 대화 내역을 저장하는 배열
      async function fetchData(apiUrl, apiKey, messages) {
        try {
	    // fetch를 사용하여 API를 호출합니다.
		    const response = await fetch(apiUrl,{
	        method : 'POST',
	        headers : {
	          'Authorization': `Bearer sk-XOqUmArii_dNjTqmEdt0U7FhdfvS2KRrJhh0I3W79GT3BlbkFJTbqKK1RFu1-Q1TlgWGDLm7mx5nWlIWCmKK45XDevgA`, // API 문서에 따라 Authorization 헤더 사용
	          'Content-Type': 'application/json'
	        },
	        body : JSON.stringify({
	          "model": "gpt-4o-mini",
	          "temperature": 0.7,
	          "max_tokens" : 300,
	          "top_p" : 1.0,
	          "messages": messages//[{"role": "user", "content": gptTest.value}]
	        	})
	    	});
		    const data = await response.json();
		    return data;
      	}catch (error) {
    	// 오류를 처리합니다.
	    console.error('Error fetching data:', error);
	    throw error; // 호출자에게 오류를 전달합니다.
  		}
      }
      
      
      $("#gpt").click(() =>{
    	  const userMessage = gptTest.value;
    	  conversationHistory.push({ "role": "user", "content": userMessage });
	      fetchData(apiUrl , apiKey, conversationHistory)
	          .then(data => {
	        	  const assistantText = data.choices[0].message.content;
	        	  conversationHistory.push({ "role": "assistant", "content": assistantText});
	              $("#gptAsk").text(assistantText);
	              gptTest.value = '';
	              })
	              .catch(error => {
	                 console.error('Error:', error);
	              });
	      });
    </script>
</body>
</html>