<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>관리자 - 로그인</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<!-- Custom fonts for this template-->
	<link href="../../../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
	<!-- Custom styles for this template-->
	<link href="../../../resources/css/sb-admin-2.min.css" rel="stylesheet">	
</head>
<body id="bg-gradient-primary">
<div class="container">

    <!-- Outer Row -->
	<div class="row justify-content-center">
    	<div class="col-xl-10 col-lg-12 col-md-9">
        	<div class="card o-hidden border-0 shadow-lg my-5">
            	<div class="card-body p-0">
                	<!-- Nested Row within Card Body -->
            		<div class="row">
                		<div class="col-lg-6 d-none d-lg-block"><img src="../../../resources/images/admin/coduck.gif"></div>
             			<div class="col-lg-6">
               				<div class="p-5">
               					<div class="text-center">
                   					<h1 class="h4 text-gray-900 mb-4">관리자 로그인</h1>
               	 				</div>
                 					
               					<c:if test="${param.error eq 'fail' }">
									<div class="row">
								  		<div class="col-sm-12">
								  			<div class="alert alert-danger">
								  				<strong>로그인 실패</strong> 아이디 혹은 비밀번호가 올바르지 않습니다.
								  			</div>
								  		</div>
								  	</div>
							  	</c:if>
								  	
							  	<c:if test="${param.error eq 'required' }">
								  	<div class="row">
								  		<div class="col-sm-12">
								  			<div class="alert alert-danger">
								  				<strong>로그인 필요</strong> 해당 서비스는 로그인 후 사용 가능합니다.
								  			</div>
								  		</div>
								  	</div>
							  	</c:if>
                 			
                 					
            					<form class="admin-login-form" method="post" action="login.hta">
                   				<div class="form-group">
                     				<input type="text" class="form-control form-control-admin" id="admin-login-id"  name="id" placeholder="아이디를 입력하세요.">
                   				</div>
                   				<div class="form-group">
                     				<input type="password" class="form-control form-control-admin" id="admin-login-pwd" name="pwd" placeholder="비밀번호를 입력하세요.">
                   				</div>
                  					<button type="submit" class="btn btn-primary btn-user btn-block">로그인</button>
                  				</form>
                				<hr>
                					
                				<div class="text-center">
                  					<a href="../user/login.hta">일반 로그인</a>
                				</div>
                				<div class="text-center">
                  					<a href="../home.hta">홈페이지</a>
               					</div>
         					</div>
     					</div>
      				</div>
  				</div>
  			</div>
		</div>
	</div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="../../../resources/vendor/jquery/jquery.min.js"></script>
<script src="../../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="../../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="../../../resources/js/sb-admin-2.min.js"></script>
<!-- Page level plugins -->
<script src="../../../resources/vendor/chart.js/Chart.min.js"></script>
<!-- Page level custom scripts -->
</body>
</html>