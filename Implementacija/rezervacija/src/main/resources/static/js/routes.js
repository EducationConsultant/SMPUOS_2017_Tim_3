var app = angular.module('rezervacijaApp.routes', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/',{
            templateUrl : 'html/pocetna.html'
        })
    
    	.when('/pregled',{
    		templateUrl : 'html/pregledRezervacija.html'
    	})
    	
    	.when('/pregledAktivnih',{
    		templateUrl : 'html/pregledAktivnihOtkazanih.html'
    	})
    	
    	.when('/pregledPoProjekcijama',{
    		templateUrl : 'html/pregledPoProjekcijama.html'
    	});
    
    
}]);