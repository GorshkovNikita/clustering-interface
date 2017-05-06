var ctx = document.getElementById("myChart");
console.log(statistics);
var datasets = [];
for (var i = 0; i < 10; i++) {
    var dataset = {};
    dataset.label = 'cluster' + statistics[i][0].clusterId;
    dataset.data = [];
    for (var j = 0; j < statistics[i].length; j++) {
        console.log(statistics[i][j].timestamp);
        dataset.data.push({
            x: statistics[i][j].timestamp,
            y: statistics[i][j].totalProcessedPerTimeUnit
        });
    }
    datasets.push(dataset);
}
var myChart = new Chart(ctx, {
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

var getTweet = function (tweetId)  {
    console.log(tweetId);
    var url = 'https://publish.twitter.com/oembed?url=https://twitter.com/anyuser/status/' + tweetId;
    console.log(url);
    $.ajax({
        //url: 'https://publish.twitter.com/oembed?url=https://twitter.com/Interior/status/463440424141459456',
        url: url,
        dataType: "jsonp",
        success: function(data) {
            document.getElementById("tweet").innerHTML = data.html;
        }
    });
};

//$(function() {
//    getTweet(statistics[0][0].mostRelevantTweetId);
//});

$(function () {
    for (var i = 0; i < statistics.length; i++) {
        twttr.widgets.createTweet(
            statistics[i][0].mostRelevantTweetId,
            document.getElementById('tweet')
        )
    }
});

// https://publish.twitter.com/oembed?url=https://twitter.com/Interior/status/463440424141459460&callback=jQuery32107641807255600463_1494073507714&_=1494073507715
// https://publish.twitter.com/oembed?url=https://twitter.com/Interior/status/463440424141459456&callback=jQuery32109392063963222899_1494073586040&_=1494073586041
