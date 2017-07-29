<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div>
<hr>
<table width=100%>
<tr>
    <td>
        <h1>Movie-market</h1>
    </td>

    <sec:authorize access="hasRole('ROLE_CLIENT')">
    <td>
        <a href="http://localhost:8080/movies-catalog">Movies catalog</a>
    </td>
    <td>
        <a href="http://localhost:8080/shopping-cart">Shopping cart</a>
    </td>
    </sec:authorize>

    <td align="right">
        <sec:authorize access="isAuthenticated()">
            <p><span style="font-size:21;x">Hello <b style="color:green;"><sec:authentication property="principal.username" /></b>!</span>
            <a class="btn btn-lg btn-danger" href="/static/j_spring_security_logout">Exit</a></p>
            <input type="text" id="client_username" hidden value="<security:authentication property="principal.username" />"/>
        </sec:authorize>
    </td>

</tr>

</table>
<hr>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<table width=100%>
    <tr align="center">
        <td>
            <a href="http://localhost:8080/movies-catalog">Movies</a>
        </td>
        <td>
            <a href="http://localhost:8080/categories-catalog">Categories</a>
        </td>
        <td>
            <a href="http://localhost:8080/coupons-catalog">Coupons</a>
        </td>
    </tr>
</table>
<hr>
</sec:authorize>


</div>