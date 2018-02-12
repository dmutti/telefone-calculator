<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erro - N&atilde;o Autorizado.</title>
</head>
<body>
<h1>Voc&ecirc; n&atilde;o pode acessar esse recurso.</h1><br/>
Clique <a href="<%= UserServiceFactory.getUserService().createLogoutURL("http://google.com.br") %>">aqui</a> para encerrar. 
</body>
</html>