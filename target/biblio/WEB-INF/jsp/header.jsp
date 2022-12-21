
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>

<%
if(session.getAttribute("dateConnexion") == null)
	session.setAttribute("dateConnexion", new Date());
%>

<div class="navbar-wrapper">
    <div class="container-fluid">
        <nav class="navbar navbar-fixed-top">
            <div class="container">
                
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li>Connexion de : <b><sec:authentication property="principal.username" /></b></li> 
                    </ul>
                    
                    <ul class="nav navbar-nav pull-right">
                    		<li>le : <b><fmt:formatDate value="${dateConnexion}" pattern="dd/MM/yyyy HH:mm"/></b></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>
