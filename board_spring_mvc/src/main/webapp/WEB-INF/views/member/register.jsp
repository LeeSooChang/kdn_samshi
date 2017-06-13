<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<style>
	 #container-register {
		background: url(images/reservation_background.jpg) no-repeat center center fixed;
		-webkit-background-size: cover;
		-moz-background-size: cover;
		-o-background-size: cover;
		background-size: cover;
		background-color: #434343;
		color: #ffffff;
		padding-top: 30px;
		padding-bottom: 90px;
	}
	
	.form-title {
		font-size: 35px;
		color: #ffffff;
	}
</style>
<script>
var array = "";
function checkPrefer(){
		var pre = document.getElementsByName("prefer");
		for(i=0;i < pre.length;i++){
			if(pre[i].checked){
				array += (pre[i].value + ',');
			}
		}
		console.log(array);
		pre.value = array;
		$("#frm").submit();
}	
</script>
</head>
<body>
<div id="container-register">
<div class="container">
<center>
<form class="form-horizontal" id = "frm" action = "registerMember.do" method = "POST">
<fieldset>
	<legend class="form-title">REGISTER</legend><br/>
	<input type = "hidden" id = "prefer" name = "prefer" />
    <div class="form-group">
      <label class="col-lg-2 control-label" for="id">ID</label>
      <div class="col-lg-10">
        <input class="form-control" id="mno" name="mno" type="text" placeholder="ID">
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="password">Password</label>
      <div class="col-lg-10">
        <input class="form-control" id="password" name="password" type="password" placeholder="Password">
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="name">Name</label>
      <div class="col-lg-10">
        <input class="form-control" id="mname" name = "mname" type="text" placeholder="Name">
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="phone">Phone</label>
      <div class="col-lg-10">
        <input class="form-control" id="phone" name = "phone" type="text" placeholder="Phone">
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="email">Email</label>
      <div class="col-lg-10">
        <input class="form-control" id="email" name = "email" type="email" placeholder="Email">
      </div>
    </div>
    <div class="form-group">
      <label class="col-lg-2 control-label" for="checkbox">Menu</label>
      <div class="col-lg-10">
        <input type="checkbox" name="prefer" id="prefer" value="두부조림">두부조림
        <input type="checkbox" name="prefer" id="prefer" value="카레">카레
        <input type="checkbox" name="prefer" id="prefer" value="오징어볶음">오징어볶음
        <input type="checkbox" name="prefer" id="prefer" value="치즈불닭">치즈불닭
        <input type="checkbox" name="prefer" id="prefer" value="계란말이">계란말이
        <input type="checkbox" name="prefer" id="prefer" value="메밀소바">메밀소바
        <input type="checkbox" name="prefer" id="prefer" value="연포탕">연포탕
        <input type="checkbox" name="prefer" id="prefer" value="갈비탕">갈비탕
        <input type="checkbox" name="prefer" id="prefer" value="메추리알장조림">메추리알장조림
        <input type="checkbox" name="prefer" id="prefer" value="짬뽕">짬뽕<br/>
        <input type="checkbox" name="prefer" id="prefer" value="제육볶음">제육볶음
        <input type="checkbox" name="prefer" id="prefer" value="닭감자조림">닭감자조림
        <input type="checkbox" name="prefer" id="prefer" value="삼치구이">삼치구이
        <input type="checkbox" name="prefer" id="prefer" value="돈코츠라멘">돈코츠라멘
        <input type="checkbox" name="prefer" id="prefer" value="백숙">백숙
        <input type="checkbox" name="prefer" id="prefer" value="부대찌개">부대찌개
        <input type="checkbox" name="prefer" id="prefer" value="오징어볶음">오징어볶음
        <input type="checkbox" name="prefer" id="prefer" value="치즈오븐스파게티">치즈오븐스파게티
        <input type="checkbox" name="prefer" id="prefer" value="고등어구이">고등어구이
        <input type="checkbox" name="prefer" id="prefer" value="잔치국수">잔치국수
        <span class="help-block">Please select the menu you want to receive notifications</span>
      </div>
    </div>
    <div class="form-group">
      <div class="col-lg-10 col-lg-offset-2" align="center">
        <button class="btn btn-default" type="reset">Cancel</button>
        <button class="btn btn-primary" type="submit">Submit</button>
      </div>
    </div>
</fieldset>
</form>
</center>
</div>
</div>
</body>
</html>