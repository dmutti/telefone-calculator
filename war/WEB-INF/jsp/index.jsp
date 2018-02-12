<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@page import="java.util.List"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Telefone Calculator</title>
</head>
<body>

<c:choose>
	<c:when test="${not empty CONTAS}">
	<p>Ver divisão das contas</p>
	   <ul>
	    <c:forEach var="conta" items="${CONTAS}">
	        <c:url value="/app/display.htm" var="url">
	            <c:param name="MES_ANO_REF" value="${conta}"/>
	        </c:url>
	        <li><a href="${url}">${conta.mes}/${conta.ano}</a></li>
	    </c:forEach>
	    </ul>
	</c:when>
    <c:otherwise>
        <b>Nenhuma conta foi encontrada.</b>
    </c:otherwise>
</c:choose>

<br/>Para inserir uma nova conta:<br>
<form action="/app/upload.htm" enctype="multipart/form-data" method="post"><input type="file" name="file" size="30">
<input type="submit" value="Transferir"></form>


<p>Clique <a href="${LOGOUT}">aqui</a> para encerrar.</p>
</body>
</html>