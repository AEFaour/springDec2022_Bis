
<%@page import="biblio.entity.Emprunt"%>
<%@page import="java.util.List"%>
<%@page import="biblio.entity.Livre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap-3.3.4/dist/css/bootstrap.min.css"/>
<title>Emprunts</title>
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
						<h3 class="panel-title">Liste des emprunts</h3>
					</div>
					<table class="table table-hover" id="dev-table">
						<thead>
							<tr>
								<th>Id emprunt</th>
								<th>Adh�rent</th>
								<th>Livre</th>
								<th>D�but</th>
								<th>Fin</th>
							</tr>
						</thead>
						<tbody>
						<%for( Emprunt emprunt : (List<Emprunt>)request.getAttribute("emprunts"))  { 
							pageContext.setAttribute("emprunt", emprunt);%>
							<tr>
								<td><a href="<%=application.getContextPath()%>/spring/jsp/emprunts/${emprunt.id}"> ${emprunt.id}</a></td>
								<td>${emprunt.adherent.nom} ${emprunt.adherent.prenom}</td>
								<td>${emprunt.livre.titre}</td>
								<td>${emprunt.debut}</td>
								<td>${emprunt.fin}</td>
							</tr>
						<%} %>
						<tr align=center><td colspan="5"><a href="<%=application.getContextPath()%>/spring/jsp/emprunts/0" class="btn btn-success btn-block">Nouvel emprunt</a></td></tr>

						</tbody>
					</table>
				</div>
			</div>
			</div></div>
<hr/>
<%@include file="footer.jsp" %>

</body>
</html>