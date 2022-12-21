
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap-3.3.4/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<title>Livres</title>
</head>
<body>
<%@include file="header.jsp" %>
<br/>
<%@include file="menu.jsp" %>


<div class="container">
	<div class="row">
        <div class="col-md-12">
            <div class="panel panel-default"> 
					<div class="panel-heading">
						<h3 class="panel-title">Liste des livres</h3>
					</div>
					<table class="table table-hover" id="dev-table">
						<thead>
							<tr>
								<th>Id</th>
								<th>Titre</th>
								<th>Auteur</th>
								<th>Parution</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="l" items="${livres}" >
							<tr>
								<td><a href="<%=application.getContextPath()%>/spring/jsp/livres/editer?id=${l.id }"> ${l.id }</a></td>
								<td>${l.titre}</td>
								<td>${l.auteur}</td>
								<td>${l.parution}</td>
							</tr>
						</c:forEach>
						<tr align=center><td colspan="4"><a href="<%=application.getContextPath()%>/spring/jsp/livres/editer?id=0" class="btn btn-success btn-block">Nouveau livre</a></td></tr>

						</tbody>
					</table>
				</div>
			</div>
			</div></div>

<hr/>
<%@include file="footer.jsp" %>

</body>
</html>