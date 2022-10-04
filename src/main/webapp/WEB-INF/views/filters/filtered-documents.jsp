<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../layout/taglib.jsp" %>
  <div class="content-wrapper">
    <div class="container">
    	<table id="filtered-documents-table" style="display:none;" class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Document</th>
					<th>Label</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
  		<div id="loader">
			<img id="loading-image" src="<spring:url value="/resources/images/loading.gif"/>" alt="Loading..."/>
		</div>
	<script>
	getFilteredDocuments();
  	</script>
    </div>
  </div>