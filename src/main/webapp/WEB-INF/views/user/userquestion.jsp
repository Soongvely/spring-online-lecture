<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>1:1질문하기</title>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style type="text/css">
		#preview p {
		    text-overflow: ellipsis;
		    overflow: hidden;
		}
		.preview-box {
		    padding: 5px;
		    border-radius: 2px;
		    margin-bottom: 10px;
		}
    </style>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<div class="container" style="font-size:17px; width:1440px;">
 
	<div class="row">
		<div class="col-sm-offset-2 col-sm-7">
			<h1>1:1 질문</h1>
		</div>
	</div>
	
<!-- 	<div class="row">
		<form id="uploadForm" class="form-inline" >
			<div class="form-group">
				<label class="control-label col-sm-2">제목</label>
	   			<div class="col-sm-6" id="">
	   				<input id="title" class="form-control" type="text" name="title">
	   			</div>
				<label class="control-label col-sm-2">내용</label>
	   			<div class="col-sm-6" id="">
	   				<textarea id="content" class="form-control"  name="content"></textarea>
	   			</div>
				<label class="control-label col-sm-2" for="uploadInputBox">사진첨부</label>
	   			<div class="col-sm-6" id="">
				    <input id="uploadInputBox" style="display: none" type="file" name="filedata" multiple />
	   			</div>
				첨부 버튼
			    <label class="waves-effect waves-teal btn-flat" for="uploadInputBox">사진첨부</label>
		
				미리보기 영역
		    	<div class="row form-control" id="preview">
		    	</div>
			    
			    <button class="submit"><a href="#" title="등록" class="btnlink">등록</a></button>
			</div>
		</form>
	</div> -->
	
	<div class="row">
		<div class="col-sm-offset-2 col-sm-7">
			<form class="form-horizontal" action="">
			  <div class="form-group">
			    <label class="control-label col-sm-2">제목</label>
			    <div class="col-sm-10">
			      <input id="title" class="form-control" type="text" name="title">
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="control-label col-sm-2">내용</label>
			    <div class="col-sm-10">
			      <textarea class="form-control" id="content" name="content"></textarea>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="control-label col-sm-2">사진첨부</label>
			    <div class="col-sm-10">
			       <input id="uploadInputBox" type="file" name="filedata" multiple />
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-10" id="preview">
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="button" class="btn btn-primary">등록</button>
			    </div>
			  </div>
			</form>
		
		</div>
	</div>
 
</div>
<script type="text/javascript">
        
	//임의의 file object영역
	var files = {};
	var previewIndex = 0;
	
	// image preview 기능 구현
	// input = file object[]
 	function addPreview(input) {
         if (input[0].files) {
             //파일 선택이 여러개였을 시의 대응
             for (var fileIndex = 0; fileIndex < input[0].files.length; fileIndex++) {
                 var file = input[0].files[fileIndex];
                 if(validation(file.name)) continue;
                 setPreviewForm(file);
             }
         } else
             alert('invalid file input'); // 첨부클릭 후 취소시의 대응책은 세우지 않았다.
     }
        
     function setPreviewForm(file, img){
         var reader = new FileReader();
         
         //div id="preview" 내에 동적코드추가.
         //이 부분을 수정해서 이미지 링크 외 파일명, 사이즈 등의 부가설명을 할 수 있을 것이다.
         reader.onload = function(img) {
             var imgNum = previewIndex++;
             $("#preview").append(
            		 "<div class=\"col-sm-2\" style='padding:0px;'>" +
                     "<div class=\"preview-box\" value=\"" + imgNum +"\">" +
                     "<img class=\"thumbnail\" style=\"width:220px; height:200px;\" src=\"" + img.target.result + "\"\/>" +
                     "<p>" + file.name + "</p>" +
                     "<a href=\"#\" value=\"" + imgNum + "\" onclick=\"deletePreview(this)\">" +
                     "삭제" + "</a>"
                     + "</div>"
                     + "</div>"
             );
             files[imgNum] = file;            
         };
         
         reader.readAsDataURL(file);
      }
	
	//preview 영역에서 삭제 버튼 클릭시 해당 미리보기이미지 영역 삭제
	function deletePreview(obj) {
	    var imgNum = obj.attributes['value'].value;
	    delete files[imgNum];
	    $("#preview .preview-box[value=" + imgNum + "]").remove();
	}
	
	//client-side validation
	//always server-side validation required
	function validation(fileName) {
	    fileName = fileName + "";
	    var fileNameExtensionIndex = fileName.lastIndexOf('.') + 1;
	    var fileNameExtension = fileName.toLowerCase().substring(
	            fileNameExtensionIndex, fileName.length);
	    if (!((fileNameExtension === 'jpg')
	            || (fileNameExtension === 'gif') || (fileNameExtension === 'png'))) {
	        alert('jpg, gif, png 확장자만 업로드 가능합니다.');
	        return true;
	    } else {
	        return false;
	    }
	}
	
	$(document).ready(function() {
    	//submit 등록. 실제로 submit type은 아니다.
	    $('.submit a').on('click',function() {                        
	        var form = $('#uploadForm')[0];
	        var formData = new FormData(form);
	
	        for (var index = 0; index < Object.keys(files).length; index++) {
	            //formData 공간에 files라는 이름으로 파일을 추가한다.
	            //동일명으로 계속 추가할 수 있다.
	            formData.append('files',files[index]);
	        }
	
	        //ajax 통신으로 multipart form을 전송한다.
	        $.ajax({
	            type : 'POST',
	            enctype : 'multipart/form-data',
	            processData : false,
	            contentType : false,
	            cache : false,
	            timeout : 600000,
	            url : '/user/questionImageupload.hta',
	            dataType : 'JSON',
	            data : formData,
	            success : function(result) {
	                //이 부분을 수정해서 다양한 행동을 할 수 있으며,
	                //여기서는 데이터를 전송받았다면 순수하게 OK 만을 보내기로 하였다.
	                //-1 = 잘못된 확장자 업로드, -2 = 용량초과, 그외 = 성공(1)
	                if (result === -1) {
	                    alert('jpg, gif, png, bmp 확장자만 업로드 가능합니다.');
	                    // 이후 동작 ...
	                } else if (result === -2) {
	                    alert('파일이 10MB를 초과하였습니다.');
	                    // 이후 동작 ...
	                } else {
	                    alert('이미지 업로드 성공');
	                    // 이후 동작 ...
	                }
	            }
	            //전송실패에대한 핸들링은 고려하지 않음
	        });
	    });
	    // <input type=file> 태그 기능 구현
	    $('#attach input[type=file]').change(function() {
	        addPreview($(this)); //preview form 추가하기
	    });
	});
</script>
 
<%@ include file="../common/footer.jsp" %>

















































