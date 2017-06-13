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
			var html = "<div id = sideDish" + count + "><input type = 'text'  name = 'tempSide' required><a href = 'javascript:void(0);' class='btn btn-sm btn-primary btn-create' onclick = deleteAttr('sideDish" + count + "')>삭제</a><br/></div>";
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
	<div class="col-md-4 col-md-offset-4">
      <form class="form-horizontal" action = "addWeeklyMenu.do" id = "frm" method = "POST">
        <fieldset>
		  <input type = "hidden" id = "sideDish" name = "sideDish"/>
          <!-- Form Name -->
          <legend style = "color : white;">Insert Weekly Menu</legend>

          <!-- Date input-->
          <div class="form-group">
            <label class="col-sm-2 control-label" for="textinput">날 짜</label>
            <div class="col-sm-10  blackColor" style = "float : left ;">
              	<input type = "date" name = "dietDate" id = "dietDate" required>
            </div>
          </div>

		  <!-- foodTime input-->
		    <div class="form-group">
            <label class="col-sm-2 control-label" for="textinput">시 간</label>
            <div class="col-sm-10  blackColor" style = "float : left ;">
              	<select	id = "scode" name = "scode">
              		<option value = '1'>아 침</option>
              		<option value = '2'>일 품</option>
              		<option value = '3'>한 식</option>
              		<option value = '4'>저 녁 </option>
              	</select>
            </div>
          </div>
          
          <!-- mainDish input-->
          <div class="form-group">
            <label class="col-sm-2 control-label" for="textinput">메 인</label>
            <div class="col-sm-10 blackColor" style = "float : left ;">
              <input type = "text"  name = "mainDish" id = "mainDish" required>
            </div>
          </div>
          

          <!-- sideDish input-->
          <div class="form-group" >
            <label class="col-sm-2 control-label" for="textinput">반 찬</label>
            <div class="col-sm-10 blackColor" id = "foodDiv" style = "float : left ;">
              <input type = 'text'  name = 'tempSide' required><a href = "javascript:void(0);" class = "btn btn-sm btn-primary btn-create" id = "addButton" >추가</a>
            </div>
          </div>


          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <div class = "blackColor">
                <a href = "javascript:void(0);" class = "btn btn-sm btn-primary btn-create" onclick = "mergeMenu()">작성</a>
                <a href = "javascript:void(0);" class="btn btn-danger btn-filter"  onclick = "addMenuBack()" >취소</a>
              </div>
            </div>
          </div>

        </fieldset>
      </form>
    </div>
</body>
</html>