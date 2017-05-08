var myChart;

var getStat = function(id) {
    $.ajax({
        url: 'http://localhost:7070/stat/' + id,
        dataType: 'json',
        success: function (data) {
            console.log(data.statistics);
            document.getElementById('tweet').innerHTML = '';
            twttr.widgets.createTweet(
                data.statistics.mostRelevantTweetId,
                document.getElementById('tweet')
            );
            var list = document.getElementsByClassName('top-terms')[0];
            list.innerHTML = '';
            for (var property in data.statistics.topTerms) {
                if (data.statistics.topTerms.hasOwnProperty(property)) {
                    var term = document.createElement('li');
                    term.innerHTML = property;
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