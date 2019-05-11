angular.module("myApp").controller("updateCtrl",['$scope', '$http', '$state','$stateParams', function ($scope, $http, $state, $stateParams) {
    $scope.go = function () {
        $state.go('main.home.dashboard');
    }
    $scope.isUpdated= $stateParams.isUpdated;
    $scope.enable=true;
    $scope.isFormSubmitted = false;
    $scope.empId=$stateParams.id;
    $http.get('get/employee/'+$scope.empId).then(
        function (response) {
           $scope.emp = response.data;
        },
        function (error) {
            toastr.error("server error occured");
        });
    $scope.edit = function () {
        $scope.enable=false;
    };
    $scope.save = function (employee) {
        $http.post('admin/save/employee',employee).then(
            function (response) {
                toastr.success("employee Details added");
                $state.go('main.home.screen ');
            },
            function (error) {
                toastr.error("server error occured");
            });
    };

    $scope.filterValue = function($event){
        if(isNaN(String.fromCharCode($event.keyCode))){
            $event.preventDefault();
        }
};

    $scope.clear = function () {
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
    }
 }]);