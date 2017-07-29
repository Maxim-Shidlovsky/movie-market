<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Movie-market</title>
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
    <script src="<c:url value="/pages/js/jquery.js" />" type="text/javascript"></script>
</head>

<body>

    <table>
        <tr>
            <td>
                <tiles:insertAttribute name="header" />
            </td>
        </tr>
        <tr>
            <td>
                <tiles:insertAttribute name="body" />
            </td>
        </tr>
        <tr>
            <td>
                <tiles:insertAttribute name="footer" />
            </td>
        </tr>
    </table>

</body>
</html>