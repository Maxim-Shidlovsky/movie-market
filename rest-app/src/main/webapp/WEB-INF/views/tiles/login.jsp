<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="top: 50%">
    <c:url value="/static/j_spring_security_check" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <table>
            <tr>
                <td align="center" colspan=2>
                    <h2>Please sign in</h2>
                </td>
            <tr>
            <tr>
                <td align="right">Login:</td>
                <td><input type="text" id="username" name="j_username" required autofocus
                            value="admin" /></td>
            </tr>
            <tr>
                <td align="right">Password:</td>
                <td><input type="password" id="password" name="j_password"
                            required value="admin" /></td>
            </tr>
            <tr>
                <td align="center" colspan=2>
                    <input id="remember_me" name="_spring_security_remember_me" type="checkbox" />
                    <label for="remember_me">Remember me</label>
                </td>
            </tr>
            <tr>
                <td align="center" colspan=2><button type="submit">Sign in</button></td>
            </tr>
        </table>
    </form>
</div>