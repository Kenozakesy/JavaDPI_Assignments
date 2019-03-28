<%@ page import="Classes.person" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Gebruiker
  Date: 12-2-2019
  Time: 09:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = request.getParameter( "username" );
    session.setAttribute( "theName", name );
%>

<HTML>
    <body>
        Hello, <%= session.getAttribute( "theName" ) %>
    </body>
</HTML>
