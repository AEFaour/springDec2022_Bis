
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="biblio.entity.Livre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Emprunt</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap-3.3.4/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<br/>
<%@include file="menu.jsp" %>

<!-- corps de la page courante ... -->
<div class="container">
	<div class="row">
        <div class="col-md-12">
            <div class="panel panel-default"> 
                <div class="panel-heading">
                    <h3 class="panel-title">Edition 
                    		<c:choose >
							<c:when test="${emprunt == null }">d'un nouvel emprunt</c:when>
							<c:otherwise>de l'emprunt N� ${emprunt.id}</c:otherwise>
						</c:choose>
					</h3>
                </div>
                <div class="panel-body">
                    <form action="<%=application.getContextPath()%>/spring/jsp/emprunts/action?id=${emprunt.id }" method="get">
                    <fieldset>
                    <c:choose>
						<c:when test="${emprunt != null }">
                    		<input type="hidden" name="id" value="${emprunt.id }">
                        
                            <div class="form-group">
                                Adherent : <input class="form-control"  type="text" value="${emprunt.adherent.nom } ${emprunt.adherent.prenom }" >
                            </div>
                            <div class="form-group">
                                Livre : <input class="form-control" type="text" value="${emprunt.livre.titre }">
                            </div>
                             <div class="form-group">
                                D�but emprunt : <input class="form-control" type="text" value="${emprunt.debut }">
                            </div>
                             <div class="form-group">
                                Fin emprunt : <input class="form-control" type="text" value="${emprunt.fin }">
                            </div>
                            <c:if test="${emprunt.fin == null }">		
								<input type="hidden" name="idLivre" value="${emprunt.livre.id }">
								<input type="hidden" name="idAdherent" value="${emprunt.adherent.id }">
								<button type="submit" name="action" value="restituer" class="btn btn-success btn-block">Restitution</button>
							</c:if>
							</c:when>
                            <c:otherwise><h3>Pas encore impl�ment� ...</h3></c:otherwise>
                            	</c:choose>
                            <button type="submit" name="action" value="return" class="btn  btn-block" >Retour</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<hr/>
<%@include file="footer.jsp" %>

</body>
</html>