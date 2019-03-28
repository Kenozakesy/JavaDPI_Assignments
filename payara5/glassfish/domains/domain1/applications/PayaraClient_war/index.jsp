<%@ page import="Classes.person" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Gebruiker
  Date: 4-2-2019
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>JSP Page</title>
    </head>
    <body>

    <%--voor session--%>
    <form METHOD=POST ACTION="SaveName.jsp">
      What's your name? <INPUT TYPE=TEXT NAME=username SIZE=20>
      <p><input TYPE=SUBMIT>
    </form>

    <form action="HomeController" method="post" role="form">
      <div class="input-group margin-bottom">
        <input type="submit" class="btn btn-primary" value="submit">
      </div>

    </form>

    <%
        List<person> persons = (List<person>) request.getAttribute("persons");
        for (person s : persons) {
            out.print("<br/>" + s.getPeronid() + " " + s.getName());
        }
    %>
  </body>
</html>
