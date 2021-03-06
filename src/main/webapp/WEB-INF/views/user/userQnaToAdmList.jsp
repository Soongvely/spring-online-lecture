<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>1:1질문하기 목록</title>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style type="text/css">
    	#quest-list tr th,td{
    		text-align: center;
    	}
    	#quest-list	 a:link { color: black; text-decoration: none;}
		#quest-list	 a:visited { color: black; text-decoration: none;}
		#quest-list	 a:hover { color: black; text-decoration: none;}
	 	.pagination {
		  display: inline-block;
		}
		
		.pagination a {
		  color: black;
		  float: left;
		  padding: 8px 16px;
		  text-decoration: none;
		}
		
		.pagination a.active {
		  background-color: #4CAF50;
		  color: white;
		  border-radius: 5px;
		}
		
		.pagination a:hover:not(.active) {
		  background-color: #ddd;
		  border-radius: 5px;
		}
    </style>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<div class="container" style="font-size:17px; width:1440px;">

	<div class="row">
		<div class="col-sm-2">
			<p>내 학습</p>
			<ul style="list-style: none;">
				<li><a href="/user/userlecting.hta">수강중인 강의</a></li>
				<li><a href="/user/mybytestlist.hta">모의고사</a></li>
				<li><a href="/userquestion/myquestionlist.hta">강의 질문</a></li>
			</ul>
			<p>내 결제</p>
			<ul style="list-style: none;">
				<li><a href="/like/likelectlist.hta">위시리스트</a></li>
				<li><a href="/cart/userCartList.hta">장바구니</a></li>
				<li><a href="/user/mycouponlist.hta">내 쿠폰함</a></li>
				<li><a href="/order/userorderlectlist.hta">구매내역</a></li>
			</ul>
			<p>내 강의</p>
			<ul style="list-style: none;">
				<li id="teacher"><a href="/teacher/main.hta">내 강의</a></li>
			</ul>
			<p>관리자 문의</p>
			<ul style="list-style: none;">
				<li id="teacher"><a href="/user/userqueston.hta">문의하기</a></li>
				<li><a href="/userquestion/userqnatoadmlist.hta">문의 내역 조회</a></li>
			</ul>
		</div>

		<div class="col-sm-10">
			<div class="row">
				<div class="col-sm-12">
					<h3>1:1 문의내역</h3>
					<p style="font-size: 14px; margin-top: 10px; margin-bottom: 20px;">> 1:1 문의내역입니다.</p>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<table class="table" id="quest-list">
						<colgroup>
							<col width="10%">
							<col width="*">
							<col width="20%">
							<col width="10%">
						</colgroup>
						<thead>
							<tr style="background-color: #eee;">
								<th>번호</th>
								<th>제목</th>
								<th>작성일</th>
								<th>답변여부</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="q" items="${qnas}" varStatus="loop">
								<tr>
									<td>${loop.count }</td>
									<td style="text-align: left;"><a href="/userquestion/userQnaToAdmDetail.hta?qnaNo=${q.no }">${q.title }</a></td>
									<td><fmt:formatDate value="${q.createDate }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									<td>${q.isAnswered }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 text-center" >
					<div class="pagination" id="pagination">		
						<c:if test="${pagination.pageNo > 1 }">
							<a href=""  data-page-no="${pagination.pageNo-1 }">이전</a>
						</c:if>
						
						<c:forEach var="no" begin="${pagination.beginPage }" end="${pagination.endPage }">
							<a href="" data-page-no="${no }"  class="${param.pageno eq no ? 'active' : ''  }">${no }</a>
						</c:forEach>
						
						<c:if test="${pagination.pageNo < pagination.totalPagesCount }">
							<a href="" class="page-link" data-page-no="${pagination.pageNo+1 }">다음</a>
						</c:if>			  
							      								
					</div>	
				</div>
			</div>
		</div>
	
	</div>	
	
<script type="text/javascript">
	// 페이징 처리하기
	$("#pagination a").click(function(event){
		event.preventDefault();
		var no = $(this).data("page-no");
		location.href = "?pageno=" + no;
	})
	
	
	

</script>
	
<%@ include file="../common/footer.jsp"%>