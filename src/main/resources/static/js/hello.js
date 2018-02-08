var sdp = angular.module('sdp', ['ngRoute']);

sdp.config(function($routeProvider) {
    $routeProvider

    // route for the home page
        .when('/', {
            templateUrl : 'pages/home.html',
            controller  : 'mainController'
        })

        // route for the about page
        .when('/users', {
            templateUrl : 'pages/users.html',
            controller  : 'usersController'
        })

        .when('/domains', {
            templateUrl : 'pages/domains.html',
            controller  : 'mainController'
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

    sdp.controller('usersController', function($scope,$http) {

        var state;

        // create a message to display in our view
        $scope.message = 'Everyone come and see how good I look!';
        $http({
            method: 'GET',
            url: '/req/allusers'
        }).then(function (success) {
            $scope.users = success.data;

        }, function (error) {
            $scope.users = error;
        });

        $scope.loadDomains = function () {
            window.location.replace("/domains");
        };
});