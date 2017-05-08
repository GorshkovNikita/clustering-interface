<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Статистика кластеризации</title>
    <link href="style.css" type="text/css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.bundle.min.js" type="text/javascript"></script>
    <script src="https://platform.twitter.com/widgets.js" async=""></script>
    <script src="js.js" type="text/javascript"></script>
</head>
<body>
    <div class="container">
        <h1>Статистика кластеризации</h1>
        <c:forEach items="${statistics}" var="entry">
            <div class="buttons">
                <span>${entry.value.numberOfDocuments} твитов</span><br>
                <button onclick="getStat(${entry.value.id})">Кластер №${entry.value.clusterId}</button><br>
            </div>
        </c:forEach>
        <div class="info">
            <div id="tweet"></div>
            <ul class="top-terms"></ul>

            <div class="chart">
                <canvas id="myChart" width="400" height="400"></canvas>
            </div>
        </div>
    </div>
</body>
</html>