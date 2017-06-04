<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <fmt:formatNumber var="duration" value="${generalStatistics.duration / (60 * 1000)}" maxFractionDigits="1" />
        <h1>Статистика кластеризации</h1>
        <p>
            Начало кластеризации: ${generalStatistics.startTime}.
            Конец кластеризации: ${generalStatistics.endTime}.
            Продолжительность: ${duration} минут.
            Твитов обработано: ${generalStatistics.numberOfTweets}.
            Количество найденных кластеров: ${numberOfClusters}.
            Количество кластеризованных документов: ${numberOfClusteredTweets}.
        </p>
        <c:forEach items="${statistics}" var="entry">
            <c:if test="${entry.value.clusterId != 0}">
                <div class="button">
                    <span>${entry.value.numberOfDocuments} твитов</span><br>
                    <button onclick="getStat(${entry.value.id})">Кластер №${entry.value.clusterId}</button><br>
                </div>
            </c:if>
        </c:forEach>
        <div class="info">
            <h2 class="tweet-header"></h2>
            <div id="tweet"></div>
            <div class="table"></div>
            <div class="chart">
                <canvas id="myChart" width="500" height="200"></canvas>
            </div>
        </div>
    </div>
</body>
</html>