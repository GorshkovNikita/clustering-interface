<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Статистика кластеризации</title>
    <link href="style.css" type="text/css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.bundle.min.js" type="text/javascript"></script>
    <script src="https://platform.twitter.com/widgets.js" async=""></script>
</head>
<body>
    <h1>Hello</h1>
    <script>
        var statistics = [];
    </script>
    <c:forEach items="${statistics}" var="entry">
        <script>var statistic = [];</script>
        <c:forEach items="${entry.value}" var="cluster">
            <script>
                statistic.push({
                    clusterId: ${cluster.clusterId},
                    numberOfDocuments: ${cluster.numberOfDocuments},
                    timestamp: ${cluster.timestamp.time},
                    totalProcessedPerTimeUnit: ${cluster.totalProcessedPerTimeUnit},
                    mostRelevantTweetId: '${cluster.mostRelevantTweetId}'
                });
            </script>
        </c:forEach>
        <script>statistics.push(statistic);</script>
    </c:forEach>
    <div class="chart">
        <canvas id="myChart" width="400" height="400"></canvas>
    </div>
    <div id="tweet">

    </div>
    <script src="js.js" type="text/javascript"></script>
</body>
</html>