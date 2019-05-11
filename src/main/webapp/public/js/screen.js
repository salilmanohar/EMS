angular.module("myApp").controller("screenCtrl",['$scope', '$http', '$state','$stateParams', '$q', function ($scope, $http, $state, $stateParams, $q) {
    $http.get('admin/get/allEmployee').then(function (response) {
        $scope.employee = [];
        $scope.employee = response.data;
        console.log($scope.employee);
        $scope.employee.empRoles = [];
        console.log($scope.employee[0].empRoles[0].role);
        $scope.enable = false;
    }, function (error) {
        toastr.error("error response from server");
    });

    

 $scope.getEmployeeCount = function(role) {
    var deferred = $q.defer();
    $http.get('/employee/getEmployeeCount?role='+role).then(function (response) {
        deferred.resolve(response.data);
    } , function (error) {
        deferred.reject(error);
        toastr.error("error response from server");
    });
    return deferred.promise;
 }

 $scope.getEmployeeCount('HR').then(function(response) {
     $scope.hrCount = response;
 });

 $scope.getEmployeeCount('ADMIN').then(function(response) {
    $scope.adminCount = response;
});

$scope.getEmployeeCount('EMPLOYEE').then(function(response) {
    $scope.employeeCount = response;
});

}]);