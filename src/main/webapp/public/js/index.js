var app = angular.module("myApp", ["ui.router"]);

app.config(function ($stateProvider, $urlRouterProvider,$httpProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('login', {
            url: '/',
            templateUrl: 'public/login.html'
        })
        .state('main', {
            url: '/main',
            templateUrl: 'public/main.html'
        })
        .state('main.home', {
            url: '/home',
            templateUrl: 'public/home.html'
        })
        .state('main.home.screen', {
            url: '/screen',
            templateUrl: 'public/screen.html'
        })
        .state('main.home.dashboard', {
            url: '/dashboard',
            templateUrl: 'public/dash.html'
        })
        .state('main.update', {
            url: '/updateEmployee/:id',
            templateUrl: 'public/update.html',
             params : {'isUpdated': false}
        })
        .state('main.home.reset', {
            url: '/resetPassword',
            templateUrl: 'public/resetpassword.html'       
        })
        .state('main.employee', {
            url: '/addEmployee',
            templateUrl: 'public/employee.html'       
        });
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});