var app = angular.module('korisnikApp.routes', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/',{
            templateUrl : 'html/pocetna.html'
        })
        .when('/pregledKorisnika',{
            templateUrl : 'html/pregledKorisnika.html'
        })
        .when('/pregledPoKoordinatama',{
            templateUrl : 'html/pregledPoKoordinatama.html'
        })
        .when('/AktDeakt',{
            templateUrl : 'html/aktDeakt.html'
        });
}]);