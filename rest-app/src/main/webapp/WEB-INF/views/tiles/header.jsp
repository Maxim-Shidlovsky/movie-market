<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<div>
<hr>
<table width=100%>
<tr>
    <td>
        <h1>Movie-market</h1>
    </td>

    <td>
        <sec:authorize access="hasRole('ROLE_CLIENT')">
            <a href="http://localhost:8080/orders">Shopping cart</a>
        </sec:authorize>
    </td>

    <td>
        <sec:authorize access="isAuthenticated()">
            <h2>Hello <security:authentication property="principal.username" />!</h2>
            <input type="text" id="client_username" hidden value="<security:authentication property="principal.username" />"/>

            <p><a class="btn btn-lg btn-danger" href="<c:url value="/static/j_spring_security_logout" />" role="button">Выйти</a></p>
        </sec:authorize>
    </td>
</tr>

</table>
<hr>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<table>
    <tr>
        <td>
            <a href="http://localhost:8080/movies">Movies</a>
        </td>
        <td>
            <a href="http://localhost:8080/categories">Categories</a>
        </td>
        <td>
            <a href="http://localhost:8080/coupons">Coupons</a>
        </td>
    </tr>
</table>
<hr>
</sec:authorize>


</div>