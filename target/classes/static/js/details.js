
var color1_dark = "rgba(46,38,51,0.7)";
var color2_dark = "rgba(85,81,82,0.7)";
var color3_dark = "rgba(153,23,60,0.7)";
var color4_dark = "rgba(220,233,190,0.7)";
var color5_dark = "rgba(239,255,205,0.7)";

var color1_light = "rgba(46,38,51,0.4)";
var color2_light = "rgba(85,81,82,0.4)";
var color3_light = "rgba(153,23,60,0.4)";
var color4_light = "rgba(220,233,190,0.4)";
var color5_light = "rgba(239,255,205,0.4)";

//line
var ctxL = document.getElementById("lineChart").getContext('2d');
var myLineChart = new Chart(ctxL, {
    type: 'line',
    data: {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
            {
                label: "Angular",
                backgroundColor: color5_light,
                pointHighlightStroke: color5_dark,
                data: [0, 0, 0, 1, 1, 1, 1]
            },{
                label: "JavaScript",
                backgroundColor: color4_light,
                pointHighlightStroke: color4_dark,
                data: [0, 0, 0, 0, 1, 1, 1]
            },{
                label: "CSS",
                backgroundColor: color3_light,
                pointHighlightStroke: color3_dark,
                data: [1, 1, 1, 2, 2, 2, 2]
            },{
                label: "HTML",
                backgroundColor: color2_light,
                pointHighlightStroke: color2_dark,
                data: [1, 2, 2, 3, 3, 3, 3]
            }, {
                label: "Java 8",
                backgroundColor: color1_light,
                pointHighlightStroke: "rgba(153,23,60,0.7)",
                data: [1, 2, 2, 3, 3, 3, 4]
            }
        ]
    },
    options: {
        responsive: true
    }
});

//polar
var ctxPA = document.getElementById("polarChart").getContext('2d');
var myPolarChart = new Chart(ctxPA, {
    type: 'polarArea',
    data: {
        labels: ["Java 8", "HTML", "CSS", "JavaScript", "Angular"],
        datasets: [
            {
                data: [4, 3, 2, 1, 1],
                backgroundColor: [color1_dark, color2_dark, color3_dark, color4_dark, color5_dark],
                hoverBackgroundColor: [color1_light, color2_light, color3_light, color4_light, color5_light]
            }
        ]
    },
    options: {
        responsive: true
    }
});