angular.module("myApp").controller("homeCtrl",['$scope', '$http', '$state', function ($scope, $http, $state) {

    $scope.dashboard = function() {
        $state.go("main.employee");
    }
}]);