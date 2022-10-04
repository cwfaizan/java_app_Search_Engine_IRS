<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@include file="../layout/taglib.jsp" %>
   	<script src="<spring:url value="/resources/js/index.js"/>"></script>
<!-- Full Width Column -->
  <div class="content-wrapper">
    <div class="container">
      <!-- Main content -->
      <section class="content">
       		<div class="row">    
		        <div class="col-xs-8 col-xs-offset-2">
				    <div class="input-group">
		                <div class="input-group-btn search-panel">
		                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
		                    	<span id="search_concept">Filter by</span> <span class="caret"></span>
		                    </button>
		                    <ul id="similarity" class="dropdown-menu" role="menu">
		                      <li id="nnn-atc"><a href="#">Cos(nnn,atc)</a></li>
		                      <li class="divider"></li>
		                      <li id="ntc-atc"><a href="#">Cos(ntc,atc)</a></li>
		                    </ul>
		                </div>
		                <input type="hidden" name="search_param" value="all" id="search_param">         
		                <input type="text" id="query" class="form-control" name="query" placeholder="Search query as 'class label,query'">
		                <span id="search_query" class="input-group-btn">
		                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
		                </span>
		            </div>
		        </div>
			</div>
      </section>
      <table id="index-table" style="display:none;" class="table table-bordered table-hover table-striped">
			<tbody>
			</tbody>
		</table>
		<div id="loader" style="display:none;">
			<img id="loading-image" src="<spring:url value="/resources/images/loading.gif"/>" alt="Loading..."/>
		</div>
    </div>
  </div>
