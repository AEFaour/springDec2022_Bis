<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>bienvenue</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap-3.3.4/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
</head>
<body>
<%@include file="header.jsp" %>
<br/>
<%@include file="menu.jsp" %>
<!-- corps de la page courante ... -->
<div class="container">
	<div class="row">
        <div class="col-md-12">
            <div class="panel panel-default"> 
                <div class="panel-heading">
                    <h3 class="panel-title">Bienvenue dans votre bibliothèque Web (...) </h3>
                </div>
                <div class="panel-body">
                	<img class="img-responsive"  alt="image de la biblio" src="<%=request.getContextPath()%>/img/biblio.jpg">
                </div>
                </div>
                </div>
                </div>
                </div>

<hr/>
<%@include file="footer.jsp" %>

</body>
</html>