<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
 body {
 background-color: black;
 color: white;
 }
</style>
</head>
<body>
	<p>${mno}님 안녕하세요</p>
	<c:if test="${!empty preferList}">
		<c:forEach var="preferList" items="${preferList}">
		<p>오늘은 ${preferList.mainDish} 나오는 날입니다:)</p>
		</c:forEach>	
	</c:if>
	<c:if test="${empty preferList }">
	<p> 오늘도 좋은 하루 되세요:) </p>
	</c:if>


	
</body>
</html>