var loginApp = angular.module('loginApp', []);

loginApp.service('loginBack', function ($rootScope, $http) {
    return {
        authenticate: function (email, password, rememberMe) {
            var userDetails = {
                email: email,
                password: password,
                rememberMe: rememberMe
            };

            $http.post('authenticate', userDetails)
                .then(function (response) {
                    console.log("Logged In Success");
                }, function (reason) {
                    console.log("Logged In With Error");
                });
        }
    };
});

loginApp.controller('loginController', function ($scope, loginBack) {
    $scope.email = undefined;
    $scope.password = undefined;
    $scope.rememberMe = false;
    $scope.login = function () {
        loginBack.authenticate($scope.email, $scope.password, $scope.rememberMe);
    }
});