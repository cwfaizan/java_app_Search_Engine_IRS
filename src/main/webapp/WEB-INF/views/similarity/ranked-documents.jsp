<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../../layout/taglib.jsp" %>

  <div class="content-wrapper">
    <div class="container">

    	<table class="table table-bordered table-hover table-striped">
			<tbody>
			<c:forEach items="${rankedDocuments}" var="rankedDocument">
			<c:set var="document" value="${fn:split(rankedDocument, ',')}" />
					<tr>
						<td>
							<c:out value="${document[2]}" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </div>
  </div>