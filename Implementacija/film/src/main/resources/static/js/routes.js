var app = angular.module('filmApp.routes', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/',{
            templateUrl : 'html/pocetna.html'
        })
        .when('/dodavanjeFilma',{
            templateUrl : 'html/dodavanjeFilma.html'
        })
        .when('/pregledFilmova',{
            templateUrl : 'html/pregledFilmova.html'
        })
        
}]);