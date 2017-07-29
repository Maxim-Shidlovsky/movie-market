<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="mainArea" style="top: 100px;">
    <c:url value="/static/j_spring_security_check" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <h2>Please sign in</h2>
        <input type="text" id="username" name="j_username" required autofocus value="admin" />
        <input type="password" id="password" name="j_password" required value="admin" />
        <input id="remember_me" name="_spring_security_remember_me" type="checkbox" />
        <label for="remember_me">Remember me</label>
        <button type="submit">Sign in</button>
    </form>
</div>