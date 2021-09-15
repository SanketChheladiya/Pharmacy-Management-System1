<%-- 
    Document   : Logout
    Created on : 13 Apr, 2021, 10:15:18 AM
    Author     : Nihar Markana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession hs;
            hs = request.getSession();
            hs.invalidate();
            
            RequestDispatcher rd = request.getRequestDispatcher("Login.html");
            rd.forward(request,response);
        %>
    </body>
</html>
