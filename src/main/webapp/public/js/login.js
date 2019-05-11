angular.module("myApp").controller("loginCtrl", ['$scope', '$http', '$state', function ($scope, $http, $state) {

    $scope.login = function () {
        $scope.username=$scope.username;
        $scope.password=$scope.password;
        $scope.invalidCredentials = false;

        var headers = {
            "Authorization": "Basic " + btoa($scope.username + ":" + $scope.password)
        };
        $http.get('getLoggedInUser', {
            headers: headers
        }).then(function (response) {
            $scope.invalidCredentials = false;
            $scope.details = response.data;
                $state.go('main.home.screen', {
                });
        }, function () {
            $scope.invalidCredentials = true;
        });
    }


}]);