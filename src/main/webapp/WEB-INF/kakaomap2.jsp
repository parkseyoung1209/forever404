<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/kakaomap2.css" />
<script src="https://kit.fontawesome.com/ef885bd654.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="search_menu">
		<div>
			<form onsubmit="searchLocalPlaces(); return false;">
				위치 검색 : <input type="text" id="keyword" size="15">
				<button type="submit" id="bttn"><i class="fa-solid fa-magnifying-glass"></i></button>
			</form>
		</div>
	</div>
	

<div class="map_wrap">
	<div id="map" style="width:900px;height:500px;position:relative;overflow:hidden;"></div>
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


</body>
</html>