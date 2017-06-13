<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
body{background-color: black;}

#pop_top{
	width: 100%;
	height: 150px;
	text-align: center;
	font-size: 20px;
	color: white;
}

#pop_bottom{
	width: 100%;
	height:20px;
	text-align: right;
	color: white;
}
</style>
</head>
<body>
<div id="pop_top">
	<p>${mno}님 안녕하세요</p>
	<c:if test="${!empty preferList}">
		<c:forEach var="preferList" items="${preferList}">
		<p>오늘은 <span style="color:red"> ${preferList.mainDish}</span> 나오는 날입니다:)</p>
		</c:forEach>	
	</c:if>
	<c:if test="${empty preferList }">
		<p>오늘도 좋은 하루 되세요:) </p>
	</c:if>
</div>
<div id="pop_bottom">
<form id="form1" name="form1">
<input type="checkbox" name="nopopup" onClick="javascript:set_nopopup('event','checked',1);"> 하루동안 열지 않기
</form>
</div>	
</body>
<script language="JavaScript">
function gourl1()
  {
   window.opener.location.href = open.jsp;   window.close();
  }

function set_nopopup(name, value, expiredays){
 todayDate = new Date();
 todayDate.setDate(todayDate.getDate() + expiredays);
 if(document.form1.nopopup.checked)
 {
  document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
  window.close();
 }
}
window.moveTo(0, 0);
</script>
</html>