App.controller('LoginController',
    function($rootScope, $scope, $http, $window) {

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
        $scope.login = function () {
            $http({
                method: 'POST',
                url: '/login',
                data: $.param($scope.credentials),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).then(function success(response) {
                    authenticate(function () {
                        if ($rootScope.authenticated) {
                            $scope.login_error = false;
                        } else {
                            $scope.login_error = true;
                        }
                    })
                }, function error(response) {
                    $scope.login_error = true;
                    $rootScope.authenticated = false;
                })
        };

        $scope.logout = function () {
            $http({
                method: 'POST',
                url: '/logout'
            }).then(function success(response) {
                $rootScope.authenticated = false;
                $scope.logout_successful = true;
            }, function error(response) {
                $scope.logout_error = true;
            })
        };

    });
