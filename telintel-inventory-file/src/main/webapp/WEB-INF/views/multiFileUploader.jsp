<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Spring 4 MVC File Multi Upload Example</title>
	<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet" type="text/css"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet" type="text/css"></link>
	
 


<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//blueimp.github.io/Gallery/css/blueimp-gallery.min.css">
<link rel="stylesheet" href="https://blueimp.github.io/jQuery-File-Upload/css/jquery.fileupload.css">
<link rel="stylesheet" href="https://blueimp.github.io/jQuery-File-Upload/css/jquery.fileupload-ui.css">


</head>
<body> 

	 
	 
	 
	<div class="form-container">
		<h1>Spring 4 MVC Multi File Upload Example </h1>
		<form:form method="POST" modelAttribute="multiFileBucket" enctype="multipart/form-data" class="form-horizontal">
		
			<c:forEach var="v" varStatus="vs" items="${multiFileBucket.files}">
				<form:input type="file" path="files[${vs.index}].file" id="files[${vs.index}].file" class="form-control input-sm"/>
				<div class="has-error">
					<form:errors path="files[${vs.index}].file" class="help-inline"/>
				</div>
				
				     <span class="btn btn-success fileinput-button" ng-class="{disabled: disabled}">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="file" multiple ng-disabled="disabled">
                    
                </span>
                
			</c:forEach>
			<br/>
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Upload" class="btn btn-primary btn-sm">
				</div>
			</div>
		</form:form>
		
		<br/>
		<a href="<c:url value='/welcome' />">Home</a>
	</div>
	 
	 
		
		<div class="uploadFIles">
		  
		   <!-- The file upload form used as target for the file upload widget -->
    <form id="fileupload" modelAttribute="fileBucket"  method="POST" enctype="multipart/form-data" data-ng-app="demo"  data-ng-controller="DemoFileUploadController" data-file-upload="options" data-ng-class="{'fileupload-processing': processing() || loadingFiles}">
     
     
     
     
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button" ng-class="{disabled: disabled}">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="file" multiple ng-disabled="disabled">
                    
                </span>
                <button type="button" class="btn btn-primary start" data-ng-click="submit()">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start upload {{queue.length}}</span>
                </button>
                <button type="button" class="btn btn-warning cancel" data-ng-click="cancel()">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel upload</span>
                </button>
                <!-- The global file processing state -->
                <span class="fileupload-process"></span>
            </div>
            <!-- The global progress state -->
            <div class="col-lg-5 fade" data-ng-class="{in: active()}">
                <!-- The global progress bar -->
                <div class="progress progress-striped active" data-file-upload-progress="progress()"><div class="progress-bar progress-bar-success" data-ng-style="{width: num + '%'}"></div></div>
                <!-- The extended global progress state -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The table listing the files available for upload/download -->
        <table class="table table-striped files ng-cloak">
            <tr data-ng-repeat="file in queue" data-ng-class="{'processing': file.$processing()}">
   
                <td>
                    <p class="name" data-ng-switch data-on="!!file.url">
                        <span data-ng-switch-default>{{file.name}}  {{file.url}} </span>
                    </p>
                    <strong data-ng-show="file.error" class="error text-danger">{{file.error}}</strong>
                </td>
                <td>
                    <p class="size">{{file.size | formatFileSize}}</p>
                    <div class="progress progress-striped active fade" data-ng-class="{pending: 'in'}[file.$state()]" data-file-upload-progress="file.$progress()"><div class="progress-bar progress-bar-success" data-ng-style="{width: num + '%'}"></div></div>
                </td>
                <td>
                  
                    <button type="button" class="btn btn-warning cancel" data-ng-click="file.$cancel()" data-ng-hide="!file.$cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel</span>
                    </button>
                    <button data-ng-controller="FileDestroyController" type="button" class="btn btn-danger destroy" data-ng-click="file.$destroy()" data-ng-hide="!file.$destroy">
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>
                </td>
            </tr>
        </table>
    </form>
    
	
		
	</div>  
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="https://blueimp.github.io/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="//blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script> 
 
<!-- The basic File Upload plugin -->
<script src="https://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="https://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload-process.js"></script>
<!-- The File Upload validation plugin -->
<!-- The File Upload Angular JS module -->
<script src="https://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload-angular.js"></script>
<!-- The main application script -->
<script src="app/app.js"></script>

</body>
</html>