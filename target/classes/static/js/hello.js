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
        .when('/contact', {
            templateUrl : 'pages/contact.html',
            controller  : 'contactController'
        });
});

sdp.controller('mainController', function($scope,$http) {

    var state;

    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
    //$http({
    //    method: 'GET',
    //    url: '/req/alldomains'
    //}).then(function (success){
    //    $scope.domains = success.data;
    //    console.log(success.data);
//
    //},function (error){
    //    $scope.domains = error;
    //});

    $scope.nextType = function() {
        state += 1;
        cycleTypes(state);
    };

    $scope.prevType = function(){
        state -= 1;
        cycleTypes(state);
    };

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


    $scope.getDetails = function(emp_id){
        location.href = '#!/employeedetail/' + emp_id;
    }

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
        })

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

sdp.controller('detailController', function($scope, $http, $routeParams, $location) {

    var courses_data;

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
        url: '/req/userbyid/' + $routeParams.param1
    }).then(function (success) {
        user_data = success.data;
        $scope.user = user_data;
        console.log(user_data);
    }, function (error) {
        console.log(error);
    });
});