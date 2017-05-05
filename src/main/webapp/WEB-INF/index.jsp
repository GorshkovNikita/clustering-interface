<%@ page import="diploma.statistics.MacroClusteringStatistics" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>Hello</h1>
    <c:forEach items="${statistics}" var="entry">
        Key = ${entry.key}
        <c:forEach items="${entry.value}" var="cluster">
            ${cluster.totalProcessedPerTimeUnit}
        </c:forEach>
        <br>
    </c:forEach>
</body>
</html>