var sdp = angular.module('sdp', ['ngRoute', 'ngMaterial']);

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

var color_array_light = ["rgba(230,25,75, 0.4)", "rgba(60, 180, 75, 0.4)", "rgba(255, 225, 25, 0.4)", "rgba(0, 130, 200, 0.4)", "rgba(245, 130, 48, 0.4)", "rgba(145, 30, 180, 0.4)", "rgba(70, 240, 240, 0.4)", "rgba(240, 50, 230, 0.4)", "rgba(210, 245, 60, 0.4)","rgba(250, 190, 190, 0.4)", "rgba(0, 128, 128, 0.4)", "rgba(0, 128, 128, 0.4)", "rgba(230, 190, 255, 0.4)", "rgba(170, 110, 40, 0.4)", "rgba(255, 250, 200, 0.4)", "rgba(128, 0, 0, 0.4)", "rgba(170, 255, 195, 0.4)", "rgba(128, 128, 0, 0.4)", "rgba(255, 215, 180, 0.4)", "rgba(0, 0, 128, 0.4)", "rgba(128, 128, 128, 0.4)", "rgba(255, 255, 255, 0.4)", "rgba(0, 0, 0, 0.4)"];
var color_array_dark = ["rgba(230,25,75, 0.7)", "rgba(60, 180, 75, 0.7)", "rgba(255, 225, 25, 0.7)", "rgba(0, 130, 200, 0.7)", "rgba(245, 130, 48, 0.7)", "rgba(145, 30, 180, 0.7)", "rgba(70, 240, 240, 0.7)", "rgba(240, 50, 230, 0.7)", "rgba(210, 245, 60, 0.7)","rgba(250, 190, 190, 0.7)", "rgba(0, 128, 128, 0.7)", "rgba(0, 128, 128, 0.7)", "rgba(230, 190, 255, 0.7)", "rgba(170, 110, 40, 0.7)", "rgba(255, 250, 200, 0.7)", "rgba(128, 0, 0, 0.7)", "rgba(170, 255, 195, 0.7)", "rgba(128, 128, 0, 0.7)", "rgba(255, 215, 180, 0.7)", "rgba(0, 0, 128, 0.7)", "rgba(128, 128, 128, 0.7)", "rgba(255, 255, 255, 0.7)", "rgba(0, 0, 0, 0.7)"];

sdp.config(function($routeProvider) {
    $routeProvider

    // route for the home page
        .when('/', {
            templateUrl : 'pages/home.html',
            controller  : 'mainController'
        })

        // route for the about page
        .when('/employees', {
            templateUrl : 'pages/employees.html',
            controller  : 'employeesController'
        })

        .when('/employeedetail/:param1', {
            templateUrl : 'pages/employeedetail.html',
            controller  : 'detailController'
        })
        .when('/employeefunctiondetail/:emp_id/:func_id', {
            templateUrl : 'pages/employeefunctiondetail.html',
            controller  : 'employeeFunctionDetailController'
        })

        .when('/domains', {
            templateUrl : 'pages/domains.html',
            controller  : 'mainController'
        })

        .when('/courses', {
            templateUrl: 'pages/courses.html',
            controller: 'coursesController'
        })

        // route for the contact page
        .when('/manage', {
            templateUrl : 'pages/manage.html',
            controller  : 'manageController'
        })

        .when('/properties', {
            templateUrl: 'pages/properties.html',
            controller: 'propertiesController'
        })

        .when('/functiondetail/:func_id', {
            templateUrl: 'pages/functiondetail.html',
            controller: 'functionDetailController'
        })
});

sdp.controller('mainController', function($scope,$http) {
});

sdp.controller('employeesController', function($scope, $http, getService) {

    var getEmployees = getService.promiseGet('/req/allusers');
    getEmployees.then(function (data) {
        $scope.users = data.data;
    }, function (reason) {
        // fail, do something with reason
    });

    $scope.getDetails = function(emp_id){
        location.href = '#!/employeedetail/' + emp_id;
    };

    $http({
        method: 'GET',
        url: '/req/allfunctions'
    }).then(function (success) {
        $scope.functions = success.data;
    }, function (error) {
    });

    $scope.newEmployee = function(){

        var firstname = document.getElementById("inputFirstName").value;
        var lastname = document.getElementById("inputLastName").value;
        var sex = document.getElementById("inputSex").value;
        var employee_function = document.getElementById("inputFunction").value;
        var hiring_date = document.getElementById("inputHiring").value;
        var birth_date = document.getElementById("inputBirth").value;

        $.ajax({
            type: "POST",
            url: "/req/addEmployee",
            data: {
                name: firstname,
                lastname: lastname,
                sex: sex,
                employee_function: employee_function,
                hiring_date: hiring_date,
                birth_date: birth_date
            }
        });
        window.location.reload();
    };

    $scope.removeEmployee = function(id){

        $http({
            method: 'GET',
            url: '/req/deleteemployeebyid/' + id
        }).then(function (success) {
        }, function (error) {
            console.log(error);
        });
        window.location.reload();
    }
});

sdp.controller('coursesController', function($scope, $http) {

    $http({
        method: 'GET',
        url: '/req/allcourses'
    }).then(function (success) {
        $scope.courses = success.data;
    }, function (error) {
    });
});

sdp.controller('detailController', function($scope, $http, $routeParams, $location, $q, $timeout, getService) {
    var getTopScores = getService.promiseGet('/req/topscoresforemployee/' + $routeParams.param1);
    getTopScores.then(function (data) {
        var labels_polar = [];
        var scores_data = [];

        if(data.data.domain1){
            labels_polar.push(data.data.domain1.name);
            scores_data.push(data.data.score1.points)
        }
        if(data.data.domain2){
            labels_polar.push(data.data.domain2.name);
            scores_data.push(data.data.score2.points)
        }
        if(data.data.domain3){
            labels_polar.push(data.data.domain3.name);
            scores_data.push(data.data.score3.points)
        }
        if(data.data.domain4){
            labels_polar.push(data.data.domain4.name);
            scores_data.push(data.data.score4.points)
        }
        if(data.data.domain5){
            labels_polar.push(data.data.domain5.name);
            scores_data.push(data.data.score5.points)
        }

        var ctxPA = document.getElementById("polarChart").getContext('2d');
        var myPolarChart = new Chart(ctxPA, {
            type: 'polarArea',
            data: {
                labels: labels_polar,
                datasets: [
                    {
                        data: scores_data,
                        backgroundColor: [color1_dark, color2_dark, color3_dark, color4_dark, color5_dark],
                        hoverBackgroundColor: [color1_light, color2_light, color3_light, color4_light, color5_light]
                    }
                ]
            },
            options: {
                responsive: true
            }
        });
    }, function (reason) {
        // fail, do something with reason
    });

    loadData = function(){

        $http({
            method: 'GET',
            url: '/req/expforemp/' + $routeParams.param1
        }).then(function(succes){
            $scope.expobj = succes.data;
            var progress = $scope.expobj.remainingExp / $scope.expobj.requiredExp * 100;
            var elem = document.getElementById("progressbar");
            elem.style.width = progress + '%';
        }, function (error){
            console.log(error);
        });

        $http({
            method: 'GET',
            url: '/req/functionsforemployee/' + $routeParams.param1
        }).then(function (success) {
            $scope.employeefunctions =  success.data;
        }, function (error) {
            console.log(error);
        });

        $http({
            method: 'GET',
            url: '/req/userbyid/' + $routeParams.param1
        }).then(function (success) {
            $scope.user = success.data;
        }, function (error) {
            console.log(error);
        });
    };

    loadData();

    $scope.editEmployee = function(){

        var id = $routeParams.param1;
        //if(document.getElementById("inputFirstName").value){
            var firstname = document.getElementById("inputFirstName").value;
        //}
        //if(document.getElementById("inputLastName").value){
            var lastname = document.getElementById("inputLastName").value;
        //}
        //if(document.getElementById("inputSex").value){
            var sex = document.getElementById("inputSex").value;
        //}
        //if(document.getElementById("inputHiring").value){
            var hiring_date = document.getElementById("inputHiring").value;
        //}
        //if(document.getElementById("inputBirth").value){
            var birth_date = document.getElementById("inputBirth").value;
        //}

        $.ajax({
            type: "POST",
            url: "/req/modifyEmployee",
            data: {
                id: id,
                name: firstname,
                lastname: lastname,
                sex: sex,
                hiring_date: hiring_date,
                birth_date: birth_date
            }
        });
        window.location.reload();
    }
});

sdp.controller('propertiesController', function($scope,$http) {

    $scope.changeExp = function(){

        var juniorMedior = document.getElementById("inputJuniorMedior").value;
        var medior = document.getElementById("inputMedior").value;
        var mediorSenior = document.getElementById("inputMediorSenior").value;
        var senior = document.getElementById("inputSenior").value;
        var factor = document.getElementById("inputFactor").value;

        $.ajax({
            type: "POST",
            url: "/props/changeExp",
            data: { juniorMedior: juniorMedior, medior: medior, mediorSenior: mediorSenior, senior: senior,
                factor: factor}
        });
    };
});

sdp.controller('manageController', function($scope,$http) {

    $http({
        method: 'GET',
        url: '/req/alldomains'
    }).then(function (success) {
        $scope.domains = success.data;
    }, function (error) {
        $scope.domains = error;
    });

    $http({
        method: 'GET',
        url: '/req/allfunctions'
    }).then(function (success) {
        $scope.functions = success.data;

    }, function (error) {
        $scope.functions = error;
    });

    $scope.newDomain = function(){
        var name = document.getElementById("inputDomainName").value;
        $.ajax({
            type: "POST",
            url: "/req/addDomain",
            data: { name: name }
        });
        window.location.reload();
    };

    $scope.removeDomain = function(id){

        $http({
            method: 'GET',
            url: '/req/deletedomainbyid/' + id
        }).then(function (success) {
        }, function (error) {
            console.log(error);
        });

        window.location.reload();
    };

    $scope.newFunction = function(){
        var name = document.getElementById("inputFunctionName").value;
        $.ajax({
            type: "POST",
            url: "/req/addFunction",
            data: { name: name }
        });
        window.location.reload();
    };

    $scope.removeFunction = function(id){

        $http({
            method: 'GET',
            url: '/req/deletefunctionbyid/' + id
        }).then(function (success) {
        }, function (error) {
            console.log(error);
        });

        window.location.reload();
    }

    $scope.getDetails = function(func_id){
        location.href = '#!/functiondetail/' + func_id;
    };

});

sdp.controller('employeeFunctionDetailController', function($scope, $http, getService, $routeParams) {

    delete $scope.courses;
    $scope.courses = "";
    var today = new Date();
    const monthNames = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];
    // LINECHART//
    var getChartData = getService.promiseGet('/req/timetracking/' + $routeParams.emp_id + '/' + $routeParams.func_id);
    getChartData.then(function (returned_data) {
        var line_x_labels = [];
        var month;
        var dataset_linechart = [];
        var more_data = true;
        var counter = 0;
        //Fill up the moth array so the graph will display the correct month names
        for (var i = 12; i > 0; i--) {
            month = today.getMonth() + i;
            if (month > 11) {
                month -= 12;
            }
            line_x_labels[i - 1] = monthNames[month];
        }
        while (more_data) {
            if (returned_data.data.datalabels[counter]) {

                dataset_linechart.push(
                    {
                        label: returned_data.data.datalabels[counter],
                        backgroundColor: color_array_light[counter],
                        pointHighLightStroke: color_array_dark[counter],
                        data: returned_data.data.datasets[counter]
                    }
                );
                counter++;
            }
            else {
                more_data = false
            }
        }

        var ctxL = document.getElementById("lineChart").getContext('2d');
        var myLineChart = new Chart(ctxL, {
            type: 'line',
            data: {
                labels: line_x_labels,
                datasets: dataset_linechart
            },
            options: {
                responsive: true
            }
        });

    }, function (reason) {
        // fail, do something with reason
    });


    $http({
        method: 'GET',
        url: '/req/expforempfunc/' + $routeParams.emp_id + '/' + $routeParams.func_id
    }).then(function(succes){
        exp_data = succes.data;
        $scope.expobj = exp_data;
        var progress = exp_data.remainingExp / exp_data.requiredExp * 100;
        var elem = document.getElementById("progressbar");
        elem.style.width = progress + '%';
    }, function (error){
        console.log(error);
    });

    $http({
        method: 'GET',
        url: '/req/userbyid/' + $routeParams.emp_id
    }).then(function (success) {
        $scope.user = success.data;
    }, function (error) {
        console.log(error);
    });

    $http({
        method: 'GET',
        url: '/req/functionbyid/' + $routeParams.func_id
    }).then(function (success) {
        $scope.function = success.data;
    }, function (error) {
        console.log(error);
    });

    $http({
        method: 'GET',
        url: '/req/coursesbyemployee/' + $routeParams.emp_id
    }).then(function (success) {
        $scope.courses = success.data;
    }, function (error) {
        console.log(error);
    });

    $scope.getCourse = function() {

        $http({
            method: 'GET',
            url: 'req/recommendCourse/' + $routeParams.emp_id + '/' + $routeParams.func_id
        }).then(function (success) {
            $scope.recommended = success.data;
        }, function (error) {
            console.log(error);
        });
    };

    $scope.back = function(id){
        location.href='#!/employeedetail/' + id;
    }
});

sdp.controller('functionDetailController', function($scope, $http, $routeParams, getService, $timeout){

    $http({
        method: 'GET',
        url: '/req/alldomains'
    }).then(function (success) {
        $scope.domains = success.data
    }, function (error) {
        $scope.domains = error;
    });

    $http({
        method: 'GET',
        url: '/req/functionbyid/' + $routeParams.func_id
    }).then(function (success) {
        $scope.function = success.data;
    }, function (error) {
        $scope.function = error;
    });

    var getDomainsByFunction = getService.promiseGet('/req/domainsbyfunction/' + $routeParams.func_id);
    var checked_domains = [];

    getDomainsByFunction.then(function(data){
        data.data.forEach(function(entry){
            checked_domains.push(entry.domain.name)
        });
        $scope.loadCheckboxes();
    });

    $scope.loadCheckboxes = function () {
        $timeout( function(){
            checked_domains.forEach(function(entry){
                if(document.getElementById(entry)){
                    document.getElementById(entry).checked = true;
                }
            });
        }, 1 );

    };

    $scope.changeCheckboxes = function(name) {
        $timeout(function () {
            if (document.getElementById(name).checked) {
                checked_domains.push(name);
            }
            else {
                position = checked_domains.indexOf(name);
                checked_domains.splice(position, 1);
            }
        }, 10);
    };

    $scope.saveFunction = function () {
        $.ajax({
            type: "POST",
            url: "/req/modifyFunction",
            data: {
                'func_id': $scope.function.id,
                'domains': checked_domains.toString()
            }
        });
        $('html, body').animate({ scrollTop: 0 }, 'fast');
        $scope.alert = true;
        $scope.loading = ".";
        for(i = 0; i < 3; i++){
            $timeout(function () {
                $scope.loading = $scope.loading + ".";
            }, 1000)
        }

        $timeout(function () {
            $scope.alert = false;
            location.href = '#!/manage/';
        }, 3000);
    }
});