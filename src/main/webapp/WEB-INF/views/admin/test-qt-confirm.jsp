<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>등록된 모의고사 문제 확인</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<!-- Custom fonts for this template-->
	<link href="../../../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
	<!-- Custom styles for this template-->
	<link href="../../../resources/css/sb-admin-2.min.css" rel="stylesheet">	
	<!-- Custom styles for this page -->
    <link href="../../../resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
	<style type="text/css">
	.xxx {
		border: 1px solid #eee;
	}
	</style>
</head>
<body id="page-top">
<%@ include file="../common/tag.jsp" %>
<div id="wrapper">
<%@include file="common/admin-side.jsp" %>
	<div id="content-wrapper" class="d-flex flex-column">
	<%@include file="common/admin-nav.jsp" %>
		<div class="container xxx">
		<div class="row xxx" style="margin: 10px 0px 10px 0px">
			<div class="col-sm-8">
				<h2 style="margin-top: 12px; padding: 10px;">
					${testQtDtos[0].name } 필기 <span style="font-size: 25px;">(${testQtDtos[0].ep } 기출문제)</span>
				</h2>
				<h4 style="padding: 10px;">총점 : <span id="span-totalscore">100</span>점</h4>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12" id="div-qt">
					<c:forEach var="qt" items="${testQtDtos }" varStatus="loop"> 
						<c:if test="${loop.count % 5 == 1 }">
							<div class="row" data-subj-no="${qt.subjNo }" id="qt-no-${loop.count }">
								<div class="col-sm-6" style="border-right: 1px solid black; 
								margin-top: 15px; margin-bottom: 15px; padding-right: 5px; padding-left: 2px;">
						</c:if>
						<c:if test="${loop.count % qt.subjQtCnt == 1 }">
							<div class="col-sm-12 xxx" style="padding: 5px; margin-bottom: 10px; margin-left: 2px; margin-right: 5px;">
								<div class="col-sm-12 xxx text-center" style="background-color: #cccccc;">
									<h4 style="font-weight: bold; margin: 5px;">${qt.subjLv }과목 : ${qt.subjName }</h4>
								</div>
							</div>
						</c:if>
						<div id="qtNo-${qt.qtNo }" class="col-sm-12" style="padding-left: 5px; padding-right: 5px;" data-qt-no="${qt.qtNum }">
							<p class="p0" style="margin: 0	"><span class="span0">${qt.qtNum }.</span> ${qt.contents }</p>
							<p class="text-right p-score" style="margin: 0">배점 : <span class="span-score">${qt.score }</span></p>
							<p class="text-right p-ans">정답 : <span class="span-ans">${qt.ans }</span></p>
							<c:if test="${not empty qt.img }">
								<p class="p1"><img class="img1" src="../../resources/images/test/qt-img/${qt.img}" style="width:100%" height="240px;"></p>
							</c:if>
							<p class="p2"><img src="/resources/images/test/marking-img/1.gif" alt="1" id="qt-${qt.qtNum }-1"/> ${qt.v1 }</p>
							<p class="p3"><img src="/resources/images/test/marking-img/2.gif" alt="2" id="qt-${qt.qtNum }-2"/> ${qt.v2 }</p>
							<p class="p4"><img src="/resources/images/test/marking-img/3.gif" alt="3" id="qt-${qt.qtNum }-3"/> ${qt.v3 }</p>
							<p class="p5"><img src="/resources/images/test/marking-img/4.gif" alt="4" id="qt-${qt.qtNum }-4"/> ${qt.v4 }</p>
							<p class="text-right p6">
								<button type="button" class="btn btn-warning btn-sm btn-modify" data-qtno="${qt.qtNo }" >수정하기</button>
							</p>
							<br/>
						</div>
						<c:if test="${loop.count % 5 == 3 }">
							</div><!-- 3문제 출력된 부분 닫기 -->
							<div class="col-sm-6" style="margin-top: 15px; margin-bottom: 15px; padding-right: 0px;"><!-- 2문제 출력된 부분 -->
						</c:if>
						
						<c:if test="${loop.count % 5 == 0 }">
							</div><!-- 2문제 출력된 부분 닫기 -->
							</div><!-- row 닫기 -->
						</c:if>
					</c:forEach>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12 text-center" style="margin-bottom: 20px;">
				<br>
				<button class="btn btn-default xxx" id="btn-prev" style="margin-right: 10px;">이전</button>
				<button class="btn btn-default xxx" id="btn-next" style="margin-left: 10px;">다음</button>
			</div>
		</div>
	</div>
	
	<!-- 수정 모달창 -->
	<div class="modal fade" id="modal-modify" role="dialog">
	  <div class="modal-dialog">
	  
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-body">
	      	<form action="" method="get" id="form-modify">
	      		<input type="hidden" name="qtNo">
	      		<input type="hidden" name="qtNum">
	      		<div class="form-group">
	      			<label class="control-label col-sm-2">문제명</label>
	      			<div class="col-sm-12" id="div-content">
	      				<input id="content" class="form-control" type="text" name="content">
	      			</div>
	      			<label class="control-label col-sm-5" style="margin-top: 10px;">기존 이미지</label>
	      			<div class="col-sm-12" id="div-img">
	      				<img src="" id="img" style="margin-bottom: 10px; width: 100%; height: 300px;"/>
	      				<p style="display: none;"id="p-img">없음</p>
	      			</div>
	      			<div class="col-sm-12">
	      				<button id="btn-del-img" type="button" class="btn btn-danger btn-sm">이미지 삭제</button>
	      			</div>
	      			<label class="control-label col-sm-5" style="margin-top: 10px;">대체 이미지</label>
	      			<div class="col-sm-12" id="div-file">
	      				<input id="filename" class="form-control" type="hidden" name="img">
	      			</div>
	      			<label class="control-label col-sm-2" style="margin-top: 10px;">배점</label>
	      			<div class="col-sm-3">
					    <input id="score" class="form-control" type="text" name="score" numberOnly>
	      			</div>
	      			<label class="control-label col-sm-2" style="margin-top: 10px;">보기1</label>
	      			<div class="col-sm-12">
	      				<input id="v1" class="form-control" type="text" name="v1">
	      			</div>
	      			<label class="control-label col-sm-2" style="margin-top: 10px;">보기2</label>
	      			<div class="col-sm-12">
	      				<input id="v2" class="form-control" type="text" name="v2">
	      			</div>
	      			<label class="control-label col-sm-2" style="margin-top: 10px;">보기3</label>
	      			<div class="col-sm-12">
	      				<input id="v3" class="form-control" type="text" name="v3">
	      			</div>
	      			<label class="control-label col-sm-2" style="margin-top: 10px;">보기4</label>
	      			<div class="col-sm-12">
	      				<input id="v4" class="form-control" type="text" name="v4">
	      			</div>
	      			<label class="control-label col-sm-2" style="margin-top: 10px;">정답</label>
	      			<div class="col-sm-12">
	      				<input id="ans" class="form-control" type="text" name="ans" numberOnly>
	      			</div>
	      		</div>
	      	</form>
	        
	      </div>
	        <p style="text-align: right; margin-right: 20px; margin-bottom: 20px;">
	       		<button type="button" class="btn btn-primary" data-dismiss="modal" id="btn-modify-submit">수정</button>
	       		<button type="button" class="btn btn-default xxx" data-dismiss="modal">취소</button>
	        </p>
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
<script src="../../../resources/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="../../../resources/vendor/chart.js/Chart.min.js"></script>
<!-- Page level custom scripts -->
<script src="../../../resources/js/demo/datatables-demo.js"></script>
<script type="text/javascript">
	var isDeleted = false;
	var isChanged = false;
	var qtNo,score;
	
	var changedFileName, img;
	
	//이미지 삭제할 때
	$("#btn-del-img").click(function(){
		var status = confirm("정말로 이미지를 삭제하시겠습니까?");
		if(status){
			isDeleted = true;
			isChanged = false;
			$("#img").css("display", "none");
			$("#p-img").css("display", "block");
			$("#btn-del-img").css("display","none");
		}
	})
	
	//사진 변경여부 확인
	$("#form-modify").on("change","#changed-img", function(){
		isChanged = true;
		console.log($(this).val());
		changedFileName = fileCheck();
		console.log("changedFileName", changedFileName);
		$("#filename").val(changedFileName);
		console.log($("#filename").val());
	})
	
	//업로드한 파일명+확장자 추출(fakepath땜시)
	function fileCheck() {
		//input file 태그.
		var file = document.getElementById('changed-img');
		//파일 경로.
		var filePath = file.value;
		//전체경로를 \ 나눔.
		var filePathSplit = filePath.split('\\'); 
		//전체경로를 \로 나눈 길이.
		var filePathLength = filePathSplit.length;
		//마지막 경로를 .으로 나눔.
		var fileNameSplit = filePathSplit[filePathLength-1].split('.');
		//파일명 : .으로 나눈 앞부분
		var fileName = fileNameSplit[0];
		//파일 확장자 : .으로 나눈 뒷부분
		var fileExt = fileNameSplit[1];
		
		console.log('파일 경로 : ' + filePath);
		console.log('파일명 : ' + fileName);
		console.log('파일 확장자 : ' + fileExt);
		
		return fileName+"."+fileExt;
	}

	//가격 입력값 숫자만
	$("input:text[numberOnly]").on("keyup", function() {
	    $(this).val($(this).val().replace(/[^0-9]/g,""));
	});
	
	//수정폼 제출할 때 
	$("#btn-modify-submit").click(function(){
		//사진이 있었지만 바뀌진 않았을 때
/* 		if(img != null && !isChanged){
			console.log("사진이 있었지만 바뀌진 않았을 때");
			$("#filename").val(img);
			console.log("제출할 filename", $("#filename").val());
		} else if (isChanged){
			console.log("사진 있거나 없거나 관계없이 +사진이 변경됐을 때")
			console.log("업로드한 경로와 파일 이름", $("#filename").val());
			console.log("제출할 filename", $("#filename").val());
		} else if(!isChanged){
			console.log("사진 원래 없었고, 사진 변경x");
			$("#filename").val("");
			console.log("제출할 filename", $("#filename").val());
		} */
		
		if(isDeleted && isChanged){
			console.log("삭제했고, 이미지 수정했을 때 : ", $("#filename").val());
		} else if(isDeleted && !isChanged){
			$("#filename").val("");
			console.log("삭제했고, 이미지 수정 안했을 때 : ", $("#filename").val());
		} else if(!isDeleted && isChanged){
			console.log("삭제 안했고, 이미지 수정했을 때 : ", $("#filename").val());
		} else {
			$("#filename").val(img);
			console.log("삭제 안했고, 이미지 수정 안했을 때 : ", $("#filename").val());
		}
		
		var formData = $("#form-modify").serialize();
		var changedScore;
		var testNo;
		
		$.getJSON("/admin/modifyQt.hta", formData, function(result){
			
			//var $p0 = $("#qtNo-" + qtNo).find(".p0");
			var $div = $("#qtNo-" + qtNo);
			$div.find(".p0").text(result.num + ". " + result.content);
			$div.find(".span-ans").text(result.ans);
			$div.find(".span-score").text(result.score);
			$div.find(".p1").remove();
			if(result.img != null){
				$div.find(".p-ans")
					.after('<p class="p1"><img class="img1" src="../../resources/images/test/qt-img/' + result.img + '" style="width:100%" height="240px;"></p>');
				
				$div.find(".img1").attr("src", "/resources/images/test/qt-img/" + result.img);
			}
			$div.find(".p2").html('<img src="/resources/images/test/marking-img/1.gif" alt="1" id="qt-'
									+ result.num + '-1"/> ' + result.v1 + '</p>');
			$div.find(".p3").html('<img src="/resources/images/test/marking-img/1.gif" alt="1" id="qt-'
									+ result.num + '-2"/> ' + result.v2 + '</p>');
			$div.find(".p4").html('<img src="/resources/images/test/marking-img/1.gif" alt="1" id="qt-'
									+ result.num + '-3"/> ' + result.v3 + '</p>');
			$div.find(".p5").html('<img src="/resources/images/test/marking-img/1.gif" alt="1" id="qt-'
									+ result.num + '-4"/> ' + result.v4 + '</p>');
			changedScore = result.score;
			
			var totalScore = $("#span-totalscore").text();
			testNo = result.testNo;
			
			$.getJSON("/admin/getTotalScore.hta", {testNo:testNo, score:score,changedScore:changedScore}, function(result){
				$("#span-totalscore").text(result);
				if($("#span-totalscore").text() != 100){
					alert("총점이 100점이 아닙니다. 배점을 적절하게 수정해주세요.");
				}
			})
		})
		
		
		
		
	})
	
	//문제 수정하기 클릭(수정폼 띄우기)
	$(".btn-modify").click(function(){
		
		var jsonData;
		qtNo = $(this).data("qtno");
		var $content = $("#form-modify :input[name='content']");
		var inputNames = $("#form-modify :input");
		isChanged = false;
		isDeleted = false;
		
		
		//파일 업로드 지우기
		$("#changed-img").detach();
		$("#div-file").append('<input id="changed-img" class="form-control be-removed" type="file" name="file">');
		
		$("#form-modify :input[name='qtNo']").val(qtNo);
		
		$.getJSON("/admin/getTestQt.hta", {qtNo:qtNo}, function(result){
			$("#content").val(result.content);
			img = result.img;
			if(result.img != null){
				$("#btn-del-img").css("display","block");
				$("#img").css("display", "block").attr("src", "/resources/images/test/qt-img/"+ result.img);
				$("#div-img p").css("display", "none");
			} else {
				$("#btn-del-img").css("display","none");
				$("#img").attr("src", "").css("display", "none");
				$("#div-img p").css("display", "block");
			}

			$("#score").val(result.score);
			score = $("#score").val();
			console.log("기본 배점",score);
			$("#v1").val(result.v1);
			$("#v2").val(result.v2);
			$("#v3").val(result.v3);
			$("#v4").val(result.v4);
			$("#ans").val(result.ans);
		}) 
		$("#modal-modify").modal("show");
	})
	
	//업로드 하는 파일의 파일명 조회
	function getFileName(val){
		var pathHeader , pathMiddle, pathEnd, allFilename, fileName, extName;
		if (val != "") {
			pathHeader = val.lastIndexOf("\\");
			pathMiddle = val.lastIndexOf(".");
			pathEnd = val.length;
			fileName = val.substring(pathHeader+1, pathMiddle);
			extName = val.substring(pathMiddle+1, pathEnd);
			allFilename = fileName+"."+extName;
	
			return fileName; // 확장자 포함 파일명
	  	} 
	}	

	//문제 영역 첫번째만 보이게
	$("#div-qt > div").hide();
	$("#div-qt > div:first-child").show();
	
	//이전버튼 클릭 설정
	$("#btn-prev").click(function() {
		if ($("#div-qt > div:visible").index() <= 0) {
			alert("첫 페이지입니다.");
			return;
		}
		//문제 영역 변경
		$("#div-qt > div:visible").hide().prev().show();
	});

	//다음버튼 클릭 설정
	$("#btn-next").click(function() {
		//문제 영역 변경
		var len = $("#div-qt > div").length;
		if ($("#div-qt > div:visible").index() < len - 1) {
			$("#div-qt > div:visible").hide().next().show();
		} else {
			alert("마지막 페이지입니다.");
		}
	});
</script>
</body>
</html>