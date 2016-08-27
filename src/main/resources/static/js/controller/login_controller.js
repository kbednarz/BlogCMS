App.controller('LoginController',
    function($rootScope, $scope, $http, $window) {
        var self = this;
        var authenticate = function (callback) {
            $http({
                method: 'GET',
                url: '/rest/user'
            }).then(function success(response) {
                if (response.data.username) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }, function error() {
                $rootScope.authenticated = false;
                callback && callback();
            });
        };
        authenticate();

        $scope.credentials = {};
        $scope.register_credentials = {};

        self.login = function () {
            $http({
                method: 'POST',
                url: '/login',
                data: $.param($scope.credentials),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).then(function success(response) {
                    authenticate(function () {
                        if ($rootScope.authenticated) {
                            self.login_error = false;
                        } else {
                            self.loginCredentials_error = true;
                        }
                    })
                }, function error(response) {
                    self.login_error = true;
                    $rootScope.authenticated = false;
                })
        };

        self.logout = function () {
            $http({
                method: 'POST',
                url: '/logout'
            }).then(function success(response) {
                $rootScope.authenticated = false;
                self.logout_successful = true;
                $window.location.reload();
            }, function error(response) {
                self.logout_error = true;
            })
        };

        self.register = function () {
            $http({
                method: 'POST',
                url: '/rest/user',
                data: $.param($scope.register_credentials),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).then(function success(response) {
                self.register_successful = true;
            }, function error(response) {
                self.register_error = true;
            })
        };
    });
