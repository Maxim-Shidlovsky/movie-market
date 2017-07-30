<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login in</title>

    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
</head>

<body>

<div class="container" style="width: 300px;>
    <tiles:insertAttribute name="body" />
</div>

</body>
</html>
