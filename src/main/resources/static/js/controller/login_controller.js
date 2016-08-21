App.controller('LoginController',
    function($rootScope, $scope, $http, $window) {
        var authenticate = function (callback) {
            $http({
                method: 'GET',
                url: '/user'
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
                            $window.location.href = '/index.html';
                            $scope.error = false;
                        } else {
                            $scope.error = true;
                        }
                    })
                }, function error(response) {
                    $scope.error = true;
                    $rootScope.authenticated = false;
                })
        }
    });
