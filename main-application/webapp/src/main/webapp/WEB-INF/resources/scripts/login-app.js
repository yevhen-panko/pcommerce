var loginApp = angular.module('loginApp', []);

loginApp.service('loginBack', function ($rootScope, $http) {
    return {
        authenticate: function (email, password, rememberMe, success, error) {
            var userDetails = {
                email: email,
                password: password,
                rememberMe: rememberMe
            };

            $http.post('login/authenticate', userDetails)
                .then(function (response) {
                    success(response);
                }, function (reason) {
                    error(reason);
                });
        },
        logout: function (success, error) {
            $http.get('login/logout')
                .then(function (response) {
                    success(response);
                }, function (reason) {
                    error(reason);
                });
        },
        status: function (success, error) {
            $http.get('login/status')
                .then(function (response) {
                    success(response);
                }, function (reason) {
                    error(reason);
                });
        }
    };
});

loginApp.controller('loginController', function ($scope, loginBack) {
    $scope.email = undefined;
    $scope.password = undefined;
    $scope.rememberMe = false;
    $scope.loginStatus = undefined;

    $scope.login = function () {
        loginBack.authenticate(
            $scope.email,
            $scope.password,
            $scope.rememberMe,
            function (response) {
                $scope.loginStatus = response.data.value;
            });
    };

    $scope.logout = function () {
        loginBack.logout(function (response) {
            $scope.loginStatus = response.data.value;
        })
    };

    loginBack.status(function (response) {
        $scope.loginStatus = response.data.value;
    });
});