<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  Tell the browser to be responsive to screen width
	  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"> -->
	  <!-- Bootstrap 3.3.6 -->
	  <!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script> -->
	  <script src="<spring:url value="/resources/js/jquery-1.11.3.min.js"/>"></script>
	  <link href="<spring:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" />
	  <!-- Font Awesome -->
	  <link rel="stylesheet" href="<spring:url value="/resources/font-awesome/css/font-awesome.min.css"/>" />
	  <!-- Ionicons -->
	  <link rel="stylesheet" href="<spring:url value="/resources/ionicons/css/ionicons.min.css"/>" />
	  <!-- Theme style -->
	  <link href="<spring:url value="/resources/dist/css/AdminLTE.min.css"/>" rel="stylesheet" />
	  <!-- AdminLTE Skins. Choose a skin from the css/skins
	       folder instead of downloading all of them to reduce the load. -->
	  <link href="<spring:url value="/resources/dist/css/skins/_all-skins.min.css"/>" rel="stylesheet" />
	  <link href="<spring:url value="/resources/css/style.css"/>" rel="stylesheet" />
	  <script src="<spring:url value="/resources/js/filters.js"/>"></script>
	  <script src="<spring:url value="/resources/js/techniques.js"/>"></script>
	  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	  <!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	  <![endif]-->
	<title>
		<tiles:getAsString name="title" />
	</title>
</head>
<body class="hold-transition skin-blue layout-top-nav">
	<div class="wrapper">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
	
	<!-- jQuery 2.2.3 -->
	<script src="<spring:url value="/resources/plugins/jQuery/jquery-2.2.3.min.js"/>"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="<spring:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
	<!-- SlimScroll -->
	<script src="<spring:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js"/>"></script>
	<!-- FastClick -->
	<script src="<spring:url value="/resources/plugins/fastclick/fastclick.js"/>"></script>
	<!-- AdminLTE App -->
	<script src="<spring:url value="/resources/dist/js/app.min.js"/>"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<spring:url value="/resources/dist/js/demo.js"/>"></script>
</body>
</html>