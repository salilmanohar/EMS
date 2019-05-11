angular.module("myApp").controller("mainCtrl", ['$scope', '$http', '$state', function ($scope, $http, $state) {
    $http.get('getLoggedInUser').then(function (response) {
        $scope.details = response.data;
    }, function () {
        toastr.error("username not found");
    });
}]);    