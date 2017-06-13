<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.kdn.model.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="reviewPageBean"
	class="com.kdn.model.domain.ReviewPageBean" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="css/kdn-table.css" rel="stylesheet">
<title>Insert title here</title>
<script type="text/javascript">
	//조건 검색, 페이지 번호로 게시글 요청을 위한 메서드  
	function reviewPagelist(cpage) {
		//input 양식의 hidden으로 선언된 page에 요청된 페이지 정보 셋팅 
		document.getElementById("reviewPageNo").value = cpage;
		var rfrm = document.getElementById("rfrm");
		rfrm.action = "listReview.do";
		rfrm.submit();
	}
	//게시글 번호나 타이틀을 클릭하면 해당 게시글 요청을 위한 메서드 
	function getReivewBoard(rno) {
		//input 양식의 hidden으로 선언된 no(게시글 번호)에 요청된 게시글 번호를 셋팅
		document.getElementById("rno").value = rno;
		var rfrm = document.getElementById("rfrm");
		rfrm.action = "searchReview.do";
		rfrm.submit();
	}
	
 	function removeForm(rno){
		$('#rno').val(rno);	
		$('#checkForm').modal();
	} 
 	
 	
	function updateform(urno, umno, ufname, uspoint, ucomments){
		console.log('updateForm');
		console.log('rno:'+urno);
		console.log('mno:'+umno);
		console.log('fname:'+ufname);
		console.log('spoint:'+uspoint);
		console.log('comments:'+ucomments);
		$("#urno").val(urno);
		$("#umno").val(umno);
		$("#ufname").val(ufname);
		$("#uspoint").val(uspoint);
		$("#ucomments").val(ucomments);
		$('#updateForm').modal();
		
	}
</script>
<link rel="stylesheet" type="text/css" href="css/basic.css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
	rel='stylesheet' type='text/css'>
<style type="text/css">
</style>
</head>
<body>
	<div style="overflow: hidden" id="updateForm" class="modal fade"
		tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 style="color: blue">
						<span class="glyphicon glyphicon-exclamation-sign"></span><label
							id="modal-title">수정 포멧</label>
					</h4>
				</div>
				<div class="modal-body" id="modal-body">

					<form method="post" action="updateReview.do"
						enctype="multipart/form-data">
						
						<table align="center" width="300">
							<tr>
								<th colspan="2">게시글 작성</th>
							</tr>
							<tr height="50">
								<td><label for="title">번호</label></td>
								<td><input type="hidden" name="rno" id="urno"
									value="${urno }" /></td>
							</tr>
							<tr height="50">
								<td><label for="title">아이디</label></td>
								<td><input type="hidden" name="mno" id="mno"
									value="${mno }" /></td>
							</tr>
							<tr height="50">
								<td><label for="title">별점</label></td>
								<td><input type="text" name="spoint" id="spoint" /></td>
							</tr>
							<tr height="50">
								<td><label for="title">음식이름</label></td>
								<td><input type="text" name="fname" id="fname" /></td>
							</tr>
							<tr>
								<td colspan="2"><label for="contents">코멘트</label></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><textarea name="comments"
										id="comments" cols="30" rows="5"></textarea></td>
							</tr>
							<tr>
								<button type="submit" class="btn btn-default btn-success">
									<span class="glyphicon glyphicon-ok"></span> 수정
								</button>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="overflow: hidden" id="checkForm" class="modal fade"
		tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 style="color: blue">
						<span class="glyphicon glyphicon-exclamation-sign"></span><label
							id="modal-title">삭제 경고</label>
					</h4>
				</div>
				<div class="modal-body" id="modal-body">
					<form role="form" method="post" action="deleteReview.do">
						정말 삭제하시겠습니까? <input type="hidden" id="rno" name="rno" value="" />
						<button type="submit" class="btn btn-default btn-success">
							<span class="glyphicon glyphicon-ok"></span> 삭제
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	<div class="main">
		<form id="rfrm">
			<input type="hidden" id="reviewPageNo" name="reviewPageNo" value="1" />
			<input type="hidden" id="no" name="no" />

			<div class="container">
				<div class="row">

					<div class="col-md-10 col-md-offset-1">

						<div class="panel panel-default panel-table">
							<div class="panel-heading">
								<div class="row">
									<div class="col col-xs-6">
										<select name="key" id="key">
											<option value="all">-----all-----</option>
											<option value="rno" <%=reviewPageBean.getKey("rno")%>>리뷰번호</option>
											<option value="mno" <%=reviewPageBean.getKey("mno")%>>사원번호</option>
											<option value="fname" <%=reviewPageBean.getKey("fname")%>>음식이름</option>
											<option value="spoint" <%=reviewPageBean.getKey("spoint")%>>별점</option>
											<option value="comments"
												<%=reviewPageBean.getKey("comments")%>>코멘트</option>
										</select> <input type="text" id="word" name="word"
											value="${reviewPageBean.word}"> <a href="#"
											onclick="reviewPagelist(1)" class="btn btn-danger btn-filter">검색</a>

									</div>
									<div class="col col-xs-6 text-right">
										<c:if test="${ !empty mno }">
											<a href="#" class="btn btn-sm btn-primary btn-create"
												data-toggle="modal" data-target="#reviewModal">글쓰기</a>
										</c:if>
									</div>
								</div>
							</div>



							<div class="panel-body">
								<table class="table table-striped table-bordered table-list">
									<thead>
										<tr>
											<th><em class="fa fa-cog"></em></th>
											<th class="hidden-xs">리뷰번호</th>
											<th>사원번호</th>
											<th>음식이름</th>
											<th>별점</th>
											<th>코멘트</th>
											<th>등록일</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="reviewBoard" items="${list}">
											<tr id=${reviewBoard.rno }>

												<td align="center"><c:if
														test="${ mno == reviewBoard.mno }">
														<a href="#" class="btn btn-default" data-toggle="modal"
															data-target="#updateReviewModal"
															onclick="updateform(${reviewBoard.rno },${reviewBoard.mno },'${reviewBoard.fname }', ${reviewBoard.spoint },'${reviewBoard.comments}')">
															<em class="fa fa-pencil"></em>
														</a>
														<a href="#" class="btn btn-danger"
															onclick="removeForm(${reviewBoard.rno})"><em
															class="fa fa-trash"></em></a>
													</c:if></td>

												<td class="hidden-xs">${reviewBoard.rno}</td>
												<td>${reviewBoard.mno }</td>
												<td>${reviewBoard.fname }</td>
												<td>${reviewBoard.spoint }</td>
												<td>${reviewBoard.comments}</td>
												<td>${reviewBoard.regdate }</td>

												<td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</div>



							<div class="panel-footer">
								<div class="row">
									<center>${reviewPageBean.pagelink }</center>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>




			<%-- <table align="center">
				<tr align="center">
					<td colspan="3" height="100" align="center">
					<select
						name="key" id="key" class="btn btn-success btn-filter">
							<option value="all">-----all-----</option>
							<option value="rno" <%=pageBean.getKey("rno")%>>아이디</option>
							<option value="spoint" <%=pageBean.getKey("spoint")%>>별점</option>
							<option value="fname" <%=pageBean.getKey("fname")%>>음식</option>
							<option value="comments" <%=pageBean.getKey("comments")%>>내용</option>
					</select> <input type="text" id="word" name="word" value="${reviewPageBean.word}"
						class="btn btn-default btn-filter"> <a href="#"
						onclick="pagelist(1)" class="btn btn-danger btn-filter">검색</a> 
						<c:if test="${ !empty mno }">
						<a href="insertReviewForm.do" class="btn btn-warning btn-filter">글쓰기</a>
						<a href="insert.do" class="btn btn-warning btn-filter">삭제</a></td>
						</c:if>
				</tr>
				<tr align="center">
					<div class="table-container">
						<table id="r-table" class="table table-filter">
							<tbody>
								<tr data-status="pagado">
									<c:forEach var="reviewBoard" items="${list}">
										<div class="col-md-2 text-center">
											<p class="">
												<span class="glyphicon glyphicon-calendar"
													aria-hidden="true"></span>
											</p>
										</div>
										<div class="col-md-10 text-justify">
											<p>${reviewBoard.rno}</p>
											<p>${reviewBoard.mno }</p>
											<p>${reviewBoard.spoint }</p>
											<p>${reviewBoard.fno }</p>
											<p>${reviewBoard.fname }</p>
											<p>${reviewBoard.comments}</p>
											<p>${reviewBoard.regdate }</p>
										</div>
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</div>
				</tr>
			</table>
			<div class="bottom">
				<center>${reviewPageBean.pagelink }</center>
			</div> --%>
		</form>
	</div>



	<script type="text/javascript">
		$(document).ready(
				function() {

					$('.star').on('click', function() {
						$(this).toggleClass('star-checked');
					});

					$('.ckbox label').on('click', function() {
						$(this).parents('tr').toggleClass('selected');
					});

					$('.btn-filter').on(
							'click',
							function() {
								var $target = $(this).data('target');
								if ($target != 'all') {
									$('.table tr').css('display', 'none');
									$(
											'.table tr[data-status="' + $target
													+ '"]').fadeIn('slow');
								} else {
									$('.table tr').css('display', 'none')
											.fadeIn('slow');
								}
							});

				});
	</script>
</body>
</html>













