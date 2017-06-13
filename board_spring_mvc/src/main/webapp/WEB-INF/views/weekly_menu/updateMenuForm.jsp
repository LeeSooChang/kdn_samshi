<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	var count = 1;
	$(document).ready(function(){
		$("#addButton").click(function(){
			var html = "<div id = sideDish" + count + "><input type = 'text'  name = 'tempSide' ><span>&nbsp&nbsp</span><button onclick = deleteAttr('sideDish" + count + "')>삭제</button><br/></div>";
			$("#foodDiv").append(html);
			count++;
		});
	});
	
	function deleteAttr(attr){
		console.log(attr);
		var item = document.getElementById(attr);
		item.remove();
	}
	
	function mergeMenu(){
		var items = document.getElementsByName('tempSide');
		var dishes = " ";
		for(var i=0, item; item=items[i]; i++) {
			dishes += (item.value + '<br/>' );
		}
		
		document.getElementById('sideDish').value = dishes;
		console.log(dishes);
		frm.submit();
	}
	
	function addMenuBack(){
		history.back();
	}
</script>
</head>
<body>
	<h2>update weekly menu</h2>
	<form class = "blackColor" id = "updateFrm" action = "searchUpdateMenu.do">
		날짜  <input type = "date" name = "dietDate" id = "dietDate">
		<br/>식단 번호 <select name = "scode" id = "scode">
				<option value = 1> 아침</option>
				<option value = 2> 일품</option>
				<option value = 3> 한식</option>
				<option value = 4> 저녁</option>	
			 </select>
		<br/>
		<button type = "submit" >검색</button>
	</form>
</body>
</html>