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

//getStat(20421);

//$(function () {
//    for (var i = 0; i < statistics.length; i++) {
//        twttr.widgets.createTweet(
//            statistics[i][statistics[i].length - 1].mostRelevantTweetId,
//            document.getElementById('tweet')
//        ).then(function (el) {
//            var list = document.createElement('ul');
//            for (var j = 0; j < statistics[i][statistics[i].length - 1].topTerms.length; j++) {
//                var term = document.createElement('li');
//                term.innerHTML = statistics[i][statistics[i].length - 1].topTerms[j].term;
//                list.appendChild(term);
//            }
//            document.getElementById('tweet').appendChild(list);
//        });
//
//    }
//});

// https://publish.twitter.com/oembed?url=https://twitter.com/Interior/status/463440424141459460&callback=jQuery32107641807255600463_1494073507714&_=1494073507715
// https://publish.twitter.com/oembed?url=https://twitter.com/Interior/status/463440424141459456&callback=jQuery32109392063963222899_1494073586040&_=1494073586041
