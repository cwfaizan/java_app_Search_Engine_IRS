<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/taglib.jsp" %>

<div id="mytab">
   <% Integer termId=0; %> 
   	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Term Id</th>
				<th>Terms</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${terms}" var="term">
				<tr>
					<td>
						<%= ++termId %>
					</td>
					<td>
						<c:out value="${term}" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>