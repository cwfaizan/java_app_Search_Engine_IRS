<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../layout/taglib.jsp" %>

  <div class="content-wrapper">
    <div class="container">
    	<table id="filtered-terms-table" style="display:none;" class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Term ID</th>
					<th>Terms</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div id="loader">
			<img id="loading-image" src="<spring:url value="/resources/images/loading.gif"/>" alt="Loading..."/>
		</div>
	<script>
	getTokens();
  	</script>
    </div>
  </div>