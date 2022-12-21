
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="biblio.entity.Livre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap-3.3.4/dist/css/bootstrap.min.css"/>
<title>Livre</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<br/>
<%@include file="menu.jsp" %>
<!-- corps de la page courante ...  -->
 
<div class="container">
	<div class="row">
        <div class="col-md-12">
            <div class="panel panel-default"> 
                <div class="panel-heading">
                    <h3 class="panel-title">Edition <c:choose >
						<c:when test="${livre.id == null }">d'un nouveau livre</c:when>
						<c:otherwise>du livre N� ${livre.id}</c:otherwise>
					</c:choose></h3>
                </div>
                <div class="panel-body">
                    <form action="<%=application.getContextPath()%>/spring/jsp/livres/action" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    		<input type="hidden" name="id" value="${livre.id }">
                        <fieldset>
                            <div class="form-group">
                                Titre &nbsp;&nbsp;: <input class="form-control"  name="titre" type="text" value="${livre.titre }" >
                            </div>
                            <div class="form-group">
                                Auteur &nbsp;: <input class="form-control" name="auteur" type="text" value="${livre.auteur }">
                            </div>
                             <div class="form-group">
                                Parution : <input class="form-control" name="parution" type="text" value="${livre.parution }">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <c:choose>
								<c:when test="${livre.id != null }">
									<button type="submit" name="action" value="update" class="btn btn-success btn-block">Modifier</button>
									<button type="submit" name="action" value="delete" class="btn btn-success btn-block">Supprimer</button>
								</c:when>
								<c:otherwise><button type="submit" name="action" value="create" class="btn btn-success btn-block">Cr�er</button></c:otherwise>
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