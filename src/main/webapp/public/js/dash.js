angular.module("myApp").controller("dashCtrl", ['$scope', '$http', '$state', function ($scope, $http, $state) {
    
    $http.get('admin/get/allEmployee').then(function (response) {
        $scope.employee = [];
        $scope.employee = response.data;
    }, function (error) {
        toastr.error("error response from server");
    });
    
    $scope.view = function (x) { 
        $state.go('main.update',{ id : x.empId ,isUpdated : false});
    }
    $scope.edit = function (x) {
        $state.go('main.update',{ id : x.empId , isUpdated : true });
    }
    $scope.delete = function (x) {
        $http.delete('admin/delete/employee/'+x.empId).then(function (response) {
        toastr.success("Deleted successfully");
        $state.go('main.home.dashboard');
        }, function (error) {
            toastr.error("error response");
        });
    };
    $scope.employees = []
    ,$scope.currentPage = 1
    ,$scope.numPerPage = 10
    ,$scope.maxSize = 5;
  
    $scope.makeTodos = function() {
      $scope.employee = [];
      for (i=1;i<=10;i++) {
         $scope.employee.push({ fullName : "employee"+i, done:false});
      }
    };
    $scope.makeTodos(); 
  
    $scope.$watch("currentPage + numPerPage", function() {
      var begin = (($scope.currentPage - 1) * $scope.numPerPage)
      , end = begin + $scope.numPerPage;
  
      $scope.employees = $scope.employee.slice(begin, end);
      console.log($scope.employee);
    });
 
}]);