<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%><html xmlns="http://www.w3.org/1999/xhtml" lang="pt-BR">                                                                                                                                         
<head>
<title>Login</title>
</head>

<body>
<%
UserService userService = UserServiceFactory.getUserService();
if (userService.isUserLoggedIn()) {
    request.getRequestDispatcher("/app/index.htm").forward(request, response);
} else {
    response.sendRedirect(userService.createLoginURL("/app/index.htm"));    
}
 
%>
</body>
</html>
