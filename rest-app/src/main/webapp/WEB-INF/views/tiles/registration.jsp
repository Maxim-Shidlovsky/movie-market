<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="mainArea">
    <h2>Create an account</h2>
    <sf:form method="POST" modelAttribute="client">
        <fieldset>
            <table>
                <tr>
                    <td align="right"><label for="username">Login:</label></td>
                    <td><sf:input path="username" size="15" id="username"/></td>
                </tr>
                <tr>
                    <td align="right"><label for="password">Password:</label></td>
                    <td><sf:password path="password" size="15" id="password"/>
                </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit" value="Sign up" /></td>
                </tr>
            </table>
        </fieldset>
    </sf:form>
</div>