<%-- 
    Document   : index
    Created on : Aug 29, 2017, 9:09:45 PM
    Author     : felix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    request.getSession(false).invalidate();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SIU SOLUTIONS</title>
    </head>
    <body>
        <h1>Welcome</h1>


        <h2>Siu Solutions</h2>
        <form action="/ExpenditureTracker_v0.2/login" method="post">
            <fieldset>
                <legend>LOG IN</legend>
                Username:<br /> <input name="username" type="text" /><br />
                Password:<br /> <input name="password" type="password" /><br />
                
                <input type="submit" value="Continue" /></fieldset>
        </form>
        <p>&nbsp;</p>
        <p>Not yet a member?</p>

        <form action="/ExpenditureTracker_v0.2/createUser" method="post">
            <fieldset>
                <legend>Create Account</legend>
                Username:<br /> <input name="username" type="text" /><br /> 
                Password:<br /> <input name="password" type="password" /><br />
                <input type="submit" value="Submit" />

            </fieldset>
        </form>
        <br><br><br><br>
        <a href="https://www.linkedin.com/in/felix-zhou-38035a133/" target="_blank">About</a>
    </body>
</html>
