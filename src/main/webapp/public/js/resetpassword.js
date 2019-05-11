angular.module("myApp").controller("resetCtrl",['$scope', '$http', '$state', function ($scope, $http, $state) {
  
    $scope.disable = true;
    $scope.error = false;
    $scope.emp ={};
    $scope.isFormSubmitted = false;
    $scope.validatePassword = function(form) {
        if(form.newPassword.$dirty && form.confirmPassword.$dirty) {
            if(($scope.newPassword != $scope.confirmPassword) || ($scope.newPassword ==undefined && $scope.confirmPassword == undefined)) {
                $scope.disable=true;
                $scope.error = true;
            }
            else  {
                $scope.disable = false;
                $scope.error = false;
            }
        }
    }

    $scope.submit = function(form) {
        $scope.isFormSubmitted = true;
        if(form.$valid) {
            $scope.emp.password = $scope.oldPassword;
            $scope.emp.newPassword = $scope.newPassword;
            $http.post('/employee/resetPassword',$scope.emp).then(
                function (response) {
                    toastr.success("Password updated successfully");
                    $state.go('main.home.screen');
                },
                function (error) {
                    toastr.error(error.data.message);
                });
        }
    }
}]);