<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>1:1 문의내역 목록</title>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style type="text/css">
    	#quest-detail {
    		border: 1px solid #f7f7f7;
    	}
    	
    	#quest-detail th {
			background-color: #f7f7f7;  
    	}	
    	
    	#quest-detail th,td {
			padding-left: 30px;	
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
			<br>
			<br>
			<p style="font-size: 25px;">1:1 문의내역</p>
		</div>
		
		<div class="col-sm-10">
			<div class="row">
				<div class="col-sm-12">
					<p style="font-size: 14px; margin-top: 10px;">> 1:1 문의내역입니다.</p>
					<br>
					<table class="table" id="quest-detail">
						<colgroup>
							<col width="20%">
							<col width="*">
						</colgroup>
						<tbody>
							<tr>
								<th>제목</th>
								<td style="padding-left: 30px;">${map.qna.title }</td>
							</tr>
							<tr>
								<th>작성일</th>
								<td style="padding-left: 30px;"><fmt:formatDate value="${map.qna.createDate }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
							</tr>
						</tbody>
					</table>
					
					<br>
					<p>${map.qna.content }</p>
					<br>
					<br>
				</div>
				<div class="col-sm-12">
					<br>
					<br>
					<c:forEach var="img" items="${map.images}" >
						<div class="col-sm-2">
							<img alt="" src="/resources/images/uploadFileByUser/${img.fileName }" style="width: 100%; height: 200px;">
						</div>
					</c:forEach>
				</div>
			</div>
			
			<div class="row" style="background-color: #f7f7f7; margin-top: 30px; margin-bottom: 30px;">
				<div class="col-sm-2">
					<p style="margin: 10px">${map.ans.userName }</p>
				</div>
				<div class="col-sm-10">
					<p style="margin: 10px">${map.ans.content }</p>
				</div>
			</div>
		</div>
	</div>	
	
<%@ include file="../common/footer.jsp"%>