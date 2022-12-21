
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="biblio.entity.Livre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap-3.3.4/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<title>livres</title>
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
                    <h3 class="panel-title">Violation d'une r�gle m�tier</h3> 
                </div>
                <div class="panel-body">
                   <form action="<%=application.getContextPath()%>/spring/jsp/home">
                        <fieldset>
                            <div class="form-group">
                            	<div style="color: red;"> ${erreur}</div>
                            </div>
                            <button type="submit" value="return" class="btn  btn-warning btn-block" >Retour</button>
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