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

        .when('/employeedetail', {
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
        .when('/contact', {
            templateUrl : 'pages/contact.html',
            controller  : 'contactController'
        });
});

sdp.controller('mainController', function($scope,$http) {

    var state;

    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
    $http({
        method: 'GET',
        url: '/req/alldomains'
    }).then(function (success){
        $scope.domains = success.data;
        console.log(success.data);

    },function (error){
        $scope.domains = error;
    });

    $scope.nextType = function() {
        state += 1;
        cycleTypes(state);
    }

    $scope.prevType = function(){
        state -= 1;
        cycleTypes(state);
    }

    function cycleTypes(state){
        if(state % 2){
            //nextType
            $scope.type = "Atlassian";
        }else{
            //prevType
            $scope.type = "Others";
        }
    }
});

sdp.controller('employeesController', function($scope, $http) {

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