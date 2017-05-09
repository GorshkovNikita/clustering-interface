<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Статистика кластеризации</title>
    <link href="style.css" type="text/css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.bundle.min.js" type="text/javascript"></script>
    <%--<script src="https://platform.twitter.com/widgets.js" async=""></script>--%>
    <script>window.twttr = (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0],
            t = window.twttr || {};
    if (d.getElementById(id)) return t;
    js = d.createElement(s);
    js.id = id;
    js.src = "https://platform.twitter.com/widgets.js";
    fjs.parentNode.insertBefore(js, fjs);

    t._e = [];
    t.ready = function(f) {
        t._e.push(f);
    };

    return t;
}(document, "script", "twitter-wjs"));</script>
    <script src="js.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <h1>Ручная кластеризации</h1>
    <form>
        <input id="tweetId" type="hidden" name="tweetId" value="${tweetId}"/>
        <div id="tweet"></div>
        <label>Имя нового кластера: <input id="clusterName" type="text" name="clusterName"/></label>
        <button onclick="clusterTweetInNewCluster()">Создать новый кластер и добавить в него твит</button>
        Существующий кластер:
        <c:forEach items="${manualClusters}" var="entry">
            <button onclick="clusterTweet(${entry.key})">${entry.value}</button>
        </c:forEach>
    </form>
    <script>
        twttr.ready(
            function (twttr) {
                displayTweet('${tweetId}')
            }
        );
    </script>
</div>
</body>
</html>