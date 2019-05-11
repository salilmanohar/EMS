angular.module("myApp").controller("employeeCtrl", ['$scope', '$http', '$state', function ($scope, $http, $state) {
    $scope.go = function () {
        $state.go('main.home.dashboard');
    }
    $scope.emp = {};
    $scope.empRoles = [];
    $scope.isFormSubmitted = false;
    $scope.submit = function(form) {
        $scope.isFormSubmitted = true;
        if(form.$valid) {
            $scope.save($scope.emp);
        }
    }
    $scope.save = function (emp) {
            $http.post('admin/save/employee', emp).then(
                function (response) {
                    toastr.success("employee successfully added");
                    $state.go('main.home.screen');
                },
                function (error) {
                    toastr.error("server error occured");
                });
    }

    $scope.filterValue = function($event){
        if(isNaN(String.fromCharCode($event.keyCode))){
            $event.preventDefault();
        }
};

    $scope.clear = function (form) {
        form.$setPristine();
        $scope.isFormSubmitted = false;
        $scope.emp.fullName = "";
        $scope.emp.mobileNo = "";
        $scope.emp.date = "";
        $scope.emp.emailId = "";
        $scope.emp.designation = "";
        $scope.emp.empId = "";
        $scope.emp.bloodGroup = "";
        $scope.emp.description = "";
        $scope.emp.gender = "";
        $scope.emp.age = "";
        $scope.emp.DOB = "";
        $scope.emp.userName = "";
        $scope.emp.role = "";
    }
}]);