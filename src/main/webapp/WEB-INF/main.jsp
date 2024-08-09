<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<c:if test="${not empty user}">
		<form action="logout">
			<input type="submit" value="로그아웃">
		</form>
		<input type="button" value="그룹생성창" id="group">
		<br />
		<div>
			<p>
				그룹명 : <input type="text" id="title">
				<span id="successText"></span>
			</p>
			<input type="button" value="그룹생성하기" id="addGroup">
			<div id="groupTable"></div>
		</div>
		<script>
		$(document).ready(function () {
		$.ajax ({
			type : "post",
			url : "/userGroup",
			success : function(list) {
				console.log(list);
				const groupList = list.map((item) => item.bigGroup);
				console.log(groupList);
				const nameList = groupList.map((value) => value.groupName);
				console.log(nameList);
				nameList.forEach((value) => {
					$("#groupTable").append("<p><input type='button' value='"+ value +"'></p>");
				});
			}
		});
			
	});
	</script>
		<script>
	$("#addGroup").click(() => {
		const title = $("#title").val();
		$.ajax ({
			type : "post",
			url : "/addGroup",
			data : "groupName=" + $("#title").val(),
		
			success : function(result) {
				if(result == true) {
					$("#successText").text("생성완료");
					$("#groupTable").append("<p><input type='button' value='"+ title +"'></p>");
				} else {
					$("#successText").text("사용할 수 없는 그룹명입니다.");
				}
			}
		});
	});
	</script>
	</c:if>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>