<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
   .hide{
      display:none;
   }
 </style>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script> 
<script type="text/javascript">
    function imgPreview(fileDom,index){     	
    	 var file=fileDom.files[0];	 
    	 var img;
      	 switch (index) {
			case 1:
				img=$("#preview1");
				break;
			case 2:
				img=$("#preview2");
				break;
			case 3:
				img=$("#preview3");
				break;
			default:
				break;
		 }  
    	 if(file!=null){
    		 var objUrl = getObjectURL(fileDom.files[0]) ;
             console.log("objUrl = "+objUrl) ;
             if (objUrl) {      	 
             	img.attr("src", objUrl);
             	img.removeClass("hide");
             }   	  
    	 }else{
    		 img.addClass("hide")
    	 }
    }
    
  //建立一個可存取到該file的url
    function getObjectURL(file) {
        var url = null ;
        if (window.createObjectURL!=undefined) 
        { // basic
            url = window.createObjectURL(file) ;
        }
        else if (window.URL!=undefined) 
        {
            // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } 
        else if (window.webkitURL!=undefined) {
            // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }
</script>
</head>
<body>
<h2>文件上传实例</h2>
<form method="post" action="/HelloWorld/UploadServlet" enctype="multipart/form-data">
	<p style="height: 300px;float: left;margin-right: 10px">选择一个文件:</p>
	<div>
	    <img src="" id="preview1" width="120" class="hide">
		<input type="file" name="uploadFile1" onchange="imgPreview(this,1)"/>
		<br/><br/>
		<img src="" id="preview2" width="120" class="hide">
		<input type="file" name="uploadFile2" onchange="imgPreview(this,2)"/>
		<br/><br/>
		<img src="" id="preview3" width="120" class="hide">
		<input type="file" name="uploadFile2" onchange="imgPreview(this,3)"/>
		<br/><br/>
	</div>
	<input type="submit" value="上传" />
</form>
</body>
</html>