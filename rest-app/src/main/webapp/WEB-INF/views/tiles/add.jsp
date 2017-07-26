<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<div>
<h2>Create an account</h2>

        <sf:form method="POST" modelAttribute="client">
            <fieldset>
            <table cellspacing="0">
                <tr>
                    <th><label for="user_name">Login:</label></th>
                    <td><sf:input path="username" size="15" id="user_name"/></td>
                </tr>
                <tr>
                    <th><label for="user_password">Password:</label></th>
                    <td><sf:input path="password" size="15" maxlength="15" id="user_password"/></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit"
                     value="I accept. Create my account." /></td>
                </tr>
            </table>
            </fieldset>
        </sf:form>
</div>