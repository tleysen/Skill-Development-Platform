var sdp = angular.module('sdp', ['ngRoute']);


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
});

sdp.controller('mainController', function($scope,$http) {
});

sdp.controller('employeesController', function($scope, $http) {


    $scope.getDetails = function(emp_id){
        location.href = '#!/employeedetail/' + emp_id;
    };

    var employee_data;
    $http({
        method: 'GET',
        url: '/req/allusers'
    }).then(function (success) {
        employee_data = success.data;
        $scope.users = employee_data;
        console.log(employee_data);
    }, function (error) {
        employee_data = error;
    });

    var function_data;

    $http({
        method: 'GET',
        url: '/req/allfunctions'
    }).then(function (success) {
        function_data = success.data;
        $scope.functions = function_data;
        console.log(function_data);

    }, function (error) {
        function_data = error;
    });


    $scope.newEmployee = function(){

        var firstname = document.getElementById("inputFirstName").value;
        //console.log(firstname);
        var lastname = document.getElementById("inputLastName").value;
        //console.log(lastname);
        var sex = document.getElementById("inputSex").value;
        //console.log(sex);
        var employee_function = document.getElementById("inputFunction").value;
        //console.log(employee_function);
        var hiring_date = document.getElementById("inputHiring").value;
        //console.log(hiring_date);
        var birth_date = document.getElementById("inputBirth").value;
        //console.log(birth_date);

        $.ajax({
            type: "POST",
            url: "/req/addEmployee",
            data: { name: firstname, lastname: lastname, sex: sex, employee_function: employee_function,
                hiring_date: hiring_date, birth_date: birth_date }
        });

        window.location.reload();

        //DEPRECATED
        //$http({
        //    method: 'GET',
        //    url: '/req/addEmployee?name=' + firstname + '&lastname=' + lastname
        //}).then(function (success) {
        //    console.log(success);
//
        //}, function (error) {
        //    console.log(error);
        //});
    };

    $scope.removeEmployee = function(id){

        $http({
            method: 'GET',
            url: '/test/check/4'
        }).then(function (success) {
            $scope.pass = success.password;
            console.log(password)
        }, function (error) {
            $scope.pass = error;
            console.log(error);
        });

        window.location.reload();
    }
});

sdp.controller('coursesController', function($scope, $http) {

    var courses_data;

    $http({
        method: 'GET',
        url: '/req/allcourses'
    }).then(function (success) {
        courses_data = success.data;
        $scope.courses = courses_data;
        console.log(courses_data);

    }, function (error) {
        courses_data = error;
    });
});

sdp.controller('detailController', function($scope, $http, $routeParams, $location, $q, $timeout) {

    var labelArray;
    var scores_data;

    loadData = function(){

        $http({
            method: 'GET',
            url: '/req/expforempfunc/' + $routeParams.param1 + '/Javadeveloper' //add function('/1')
        }).then(function(succes){
            exp_data = succes.data;
            $scope.expobj = exp_data;
            var progress = exp_data.remainingExp / 10;
            var elem = document.getElementById("progressbar");
            elem.style.width = progress + '%';
        }, function (error) {
            console.log(error);
        });

        $http({
            method: 'GET',
            url: '/req/coursesbyemployee/' + $routeParams.param1
        }).then(function (success) {
            courses_data = success.data;
            $scope.courses = courses_data;
            }, function (error) {
            console.log(error);
        });

        $http({
            method: 'GET',
            url: '/req/functionsforemployee/' + $routeParams.param1
        }).then(function (success) {
            employeefunctions_data = success.data;
            $scope.employeefunctions = employeefunctions_data;
        }, function (error) {
            console.log(error);
        });

        $http({
            method: 'GET',
            url: '/req/userbyid/' + $routeParams.param1
        }).then(function (success) {
            user_data = success.data;
            $scope.user = user_data;
            console.log(user_data);
        }, function (error) {
            console.log(error);
        });

        $http({
            method: 'GET',
            url: '/req/topscoresforemployee/' + $routeParams.param1
        }).then(function (success) {
            scores_data = success.data;
            labelArray = [scores_data.domain1.name.toString(), scores_data.domain2.name, scores_data.domain3.name, scores_data.domain4.name, scores_data.domain5.name];
            $scope.status = success.status;
            $scope.score = success.data;
        }, function (error) {
            console.log(error);
        });

        $scope.getCourse = function() {

            var selected_function = document.getElementById("inputEmployeeFunctions").value;
            $http({
                method: 'GET',
                url: 'req/recommendCourse/' + $routeParams.param1 + '/' + selected_function
            }).then(function (success) {
                console.log($routeParams.param1);
                recommend_data = success.data;
                $scope.recommended = success.data;
                console.log(success.data);
            }, function (error) {
                console.log(error);
            });
        };
    };

    loadData();

    function display(){
        //CHARTING
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

        var labels_polar = ["Eating", "Drinking", "Sleeping", "Designing", "Coding"];

        //line

        var ctxL = document.getElementById("lineChart").getContext('2d');


        var myLineChart = new Chart(ctxL, {
            type: 'line',
            data: {
                labels: labels_polar,
                datasets: [
                    {
                        //label: scores_data[0].domain.name,
                        backgroundColor: color5_light,
                        pointHighlightStroke: color5_dark,
                        data: [0, 0, 0, 1, 1, 1, 1]
                    },{
                        //label: scores_data[1].domain.name,
                        backgroundColor: color4_light,
                        pointHighlightStroke: color4_dark,
                        data: [0, 0, 0, 0, 1, 1, 1]
                    },{
                        //label: scores_data[2].domain.name,
                        backgroundColor: color3_light,
                        pointHighlightStroke: color3_dark,
                        data: [1, 1, 1, 2, 2, 2, 2]
                    },{
                        //label: scores_data[3].domain.name,
                        backgroundColor: color2_light,
                        pointHighlightStroke: color2_dark,
                        data: [1, 2, 2, 3, 3, 3, 3]
                    }, {
                        //label: scores_data[4].domain.name,
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
                labels: labels_polar,
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
    }

    $timeout(display(), 2000);
});

sdp.controller('propertiesController', function($scope,$http) {

    $http({
        method: 'GET',
        url: '/props/getdbpass'
    }).then(function (success) {
        $scope.pass = success.password;
        console.log(password)
    }, function (error) {
        $scope.pass = error;
        console.log(error);
    });


});

sdp.controller('manageController', function($scope,$http) {

    var old_input;

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

    $scope.rowSorter = function(input){
        if(old_input !== input){
            console.log(input);
        }
    }
});