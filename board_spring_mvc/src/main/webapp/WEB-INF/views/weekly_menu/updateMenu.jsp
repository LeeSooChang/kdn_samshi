<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.blackColor {
		color : black;
	}
</style>
<script type="text/javascript">
	var count = 100;
	$(document).ready(function(){
		$("#addButton").click(function(){
			var html = "<div id = sideDish" + count + "><input type = 'text'  name = 'tempSide' ><input type = 'button' onclick = deleteAttr('sideDish" + count + "') value = '삭제'><br/></div>";
			$("#foodDiv").append(html);
			count++;
		});
	});
	
	function deleteAttr(attr){
		console.log(attr);
		var item = document.getElementById(attr);
		item.remove();
		return false;
	}
	
	function mergeMenu(){
		var items = document.getElementsByName('tempSide');
		var dishes = " ";
		for(var i=0,item; item=items[i]; i++) {
			dishes += (item.value + '<br/>' );
		}
		console.log(dishes);
		document.getElementById('sideDish').value = dishes;
		$('#updateMenufrm').submit();
	}
	
	function addMenuBack(){
		history.back();
	}
</script>
</head>
<body>
	<h2>update weekly menu</h2>
	<form class = "blackColor" id = "updateMenufrm" action = "updateMenu.do" method = "POST">
			<input type = "hidden" name = "sideDish" id = "sideDish">
			<input type ="hidden" name = "dietNo" id = "dietNo" value = "${oneDiet.dietNo }">
		날짜  <input type = "hidden" name = "dietDate" id = "dietDate" color = "white" value = "${oneDiet.dietDate }" ><div>${oneDiet.dietDate }</div><br/>
		식단 번호 <input type = "hidden" name = "scode" id = "scode" color = "white" value = "${oneDiet.scode }" ><div>${oneDiet.scode }</div>
				<c:choose>
					<c:when test = "${oneDiet.scode == 1}">
						아침
					</c:when>
					<c:when test = "${oneDiet.scode == 2}">
						일품
					</c:when>
					<c:when test = "${oneDiet.scode == 3}">
						한식
					</c:when>
					<c:when test = "${oneDiet.scode == 4}">
						저녁
					</c:when>
				</c:choose>
		
			 </div><br/>
		메인 음식 <br/><input type = "text" name = "mainDish" id = "mainDish" value ="${oneDiet.mainDish }"/>	<br/>
			<% int i = 1; %>
		<input type = "button" id = "addButton" value = "추가"><br/>
		<div id = "foodDiv">
		반찬 <c:forTokens var = "item" items="${oneDiet.sideDish}" delims="<br/>">
			 <div id = "sideDish<%=i%>"><input type = "text" name = "tempSide" value ="${item}"/><input type='button' onclick = "deleteAttr('sideDish<%= i %>')" value='삭제'/><br/></div>	
		   	 <% i++; %>
		   </c:forTokens>
		</div>
		<br/><input type = "button" onclick = "mergeMenu()" value = "수정">
			 <input type="button" onclick = "addMenuBack()" value="취소"/>
	</form>
</body>
</html>