<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- sy.js -->
	<script src="/resources/js/common.js"></script>

<!-- sy.css -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<link href="<c:url value="/resources/css/sy-style.css" />" rel="stylesheet">    
	<!-- sy.js -->
	<script src="/resources/js/common.js"></script>

  <div id="root">
    <nav class="navbar sy-navbar">
        <div class="nav sy-nav-container">
          <div class="navbar-header">
              <a class="navbar-logo" href="../home.hta"><img src="/resources/images/logo/coduck.ico"/></a>
              <a class="sy-logo" href="../home.hta">CODUCK</a>
          </div>
      
          <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
              <li class="dropdown">
                <a class="dropdown-toggle t-gray" href="../lecture/main.hta" style="font-size: 17px;"> IT강좌</a>
              </li>
              <li class="dropdown">
                <a class="dropdown-toggle t-gray" data-toggle="dropdown" href="#" style="font-size: 17px;"> 커뮤니티 <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="#">게시판</a></li>
                  <li><a href="#">공지사항</a></li>
                </ul>
              </li>
            </ul>

            <form class="navbar-form navbar-left" action="/action_page.php">
              <div class="input-group">
                <input type="text" class="form-control search-box" placeholder="Search" name="search">
                <div class="input-group-btn">
                  <button class="btn btn-default sy-btn-search" type="submit">
                    <i class="glyphicon glyphicon-search"></i>
                  </button>
                </div>
              </div>
            </form>    

            <ul class="nav navbar-nav">
              <li><a href="/user/userprofil.hta" class="t-gray" style="font-size: 16px;">수강생</a></li>
            </ul>  

            <ul class="nav navbar-nav navbar-right">
	            <li><a href="/user/login.hta" class="t-gray"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
	            <li><a href="/user/register.hta" class="t-gray"><i class="fas fa-user-plus"></i> Sign Up</a></li>
	            <ul class="nav navbar-nav navbar-right hidden">
	                <li><a href="#"><span class="glyphicon glyphicon-shopping-cart t-gray"></span></a></li>
	                <li><a href="#"><span class="glyphicon glyphicon-heart-empty t-gray"></span></a></li>
	                <li><a href="#"><span class="glyphicon glyphicon-bullhorn t-gray"></span></a></li>
	                <li><a href="#"><span class="glyphicon glyphicon-log-out t-gray"></span></a></li>
	            </ul>
	        </ul>  
          </div>
        </div>
      </nav>
      <div class="smart-bar">
        <button class="btn btn-smart-bar-close pull-right">X</button>
          <div class="container smart-bar-comment">
            <span><i class="glyphicon glyphicon-leaf smart-bar-logo"></i> </span></span>
            <span><strong>&nbsp&nbsp CODUUK</strong>에 오신걸 환영합니다. 여러분의 지식을 공유해주세요!</span>
          </div>
      </div>