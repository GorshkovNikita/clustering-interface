var myChart;

var getStat = function(id) {
    $.ajax({
        url: 'http://localhost:7070/stat/' + id,
        dataType: 'json',
        success: function (data) {
            displayTweet(data.statistics.mostRelevantTweetId);
            var list = document.getElementsByClassName('table')[0];
            list.innerHTML = '';

            var tweetHeader = document.getElementsByClassName('tweet-header')[0];
            tweetHeader.innerHTML = 'Кластер №' + data.statistics.clusterId + '. Количество твитов = ' + data.statistics.numberOfDocuments;

            for (var property in data.statistics.topTerms) {
                if (data.statistics.topTerms.hasOwnProperty(property)) {
                    var term = document.createElement('div');
                    term.innerHTML = '<div class="row"><div class="cell">' + property + '</div><div class="cell">' + data.statistics.topTerms[property] + '</div></div>';
                    list.appendChild(term);
                }
            }

            var datasets = [];
            var dataset = {};
            dataset.label = 'cluster' + data.statistics.clusterId;
            dataset.data = [];
            for (var i = 0; i < data.timeAndProcessedPerUnitList.length; i++) {
                dataset.data.push({
                    x: data.timeAndProcessedPerUnitList[i].timestamp,
                    y: data.timeAndProcessedPerUnitList[i].totalProcessedPerTimeUnit
                });
            }
            datasets.push(dataset);
            if (myChart !== undefined)
                myChart.destroy();
            var ctx = document.getElementById("myChart");
            myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    datasets: datasets
                },
                options: {
                    scales: {
                        xAxes: [{
                            type: 'time',
                            position: 'bottom'
                        }]
                    }
                }
            });
        }
    });
};

var displayTweet = function (tweetId) {
    document.getElementById('tweet').innerHTML = '';
    twttr.widgets.createTweet(
        tweetId,
        document.getElementById('tweet')
    );
};

var clusterTweet = function (clusterId) {
    var tweetId = document.getElementById('tweetId').value;
    $.ajax({
        method: 'POST',
        url: 'http://localhost:7070/manual',
        data: {
            tweetId: tweetId,
            clusterId: clusterId
        },
        success: function (data) {
            //window.location.replace('http://localhost:7070/manual');
            console.log('tweet was clustered');
        }
    });
};

var clusterTweetInNewCluster = function () {
    var clusterName = document.getElementById('clusterName').value;
    var tweetId = document.getElementById('tweetId').value;
    $.ajax({
        method: 'POST',
        url: 'http://localhost:7070/manual',
        data: {
            tweetId: tweetId,
            clusterName: clusterName
        },
        success: function (data) {
            //window.location.replace('http://localhost:7070/manual');
            console.log('tweet was clustered');
        }
    });
};