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
			var html = "<div id = sideDish" + count + "><input type = 'text'  name = 'tempSide' ><input type = 'button' onclick = deleteAttr('sideDish" + count + "') value = '삭제'><br/></div>";
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
		$("#frm").submit();
	}
	
	function addMenuBack(){
		history.back();
	}
</script>
</head>
<body>
	
	<div style = "width : 1000px; float : left;">
	<h2 align = "center">insert weekly menu</h2>
		<form id = "frm" action = "addWeeklyMenu.do" method = "POST">
			<input type = "hidden" id = "sideDish" name = "sideDish"/>
			<table width = "800px" >
				<tr>
					<td>달력</td>
					<td colspan = "2" class = "blackColor">
						<input type = "date" name = "dietDate" id = "dietDate">
					</td>
				</tr>
				<tr>
					<td >식단</td>
					<td colspan = "2" class = "blackColor">
						<select name="scode" id="scode">
						    <option value="1">아침</option>
						    <option value="2">일품</option>
						    <option value="3">한식</option>
						    <option value="4">저녁</option>
						</select>
					</td>
				</tr>
				<tr>
					<td >메인 메뉴</td>
					<td colspan = "2" class = "blackColor"><input type = "text"  name = "mainDish" id = "mainDish"></td>
				</tr>
				<tr>
					<td>반찬</td>
					<td colspan = "2" class = "blackColor" id = "foodDiv">
						<input type = 'text'  name = 'tempSide'><input type = "button" id = "addButton" value = "추가">
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="button" onclick = "mergeMenu()" value="작성"/>
						<input type="button" onclick = "addMenuBack()" value="취소"/>
					</td>
				</tr>
				</table>
			</form>
	</div>
</body>
</html>