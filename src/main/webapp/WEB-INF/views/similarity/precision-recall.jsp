<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../../layout/taglib.jsp" %>

  <div class="content-wrapper">
    <div class="container">

    	<table class="table table-bordered table-hover table-striped">
			<tbody>
				<c:forEach items="${precisionRecall}" var="PR">
					<tr>
						<td>
							<c:out value="${PR.key}" />
						</td>
						<td>
							<c:out value="${PR.value}" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </div>
  </div>