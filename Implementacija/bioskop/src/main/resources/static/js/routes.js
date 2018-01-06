var app = angular.module('bioskopApp.routes', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/',{
            templateUrl : 'html/pocetna.html'
        })
        
        .when('/pregledBioskopa',{
    		templateUrl : 'html/pregledBioskopa.html'
    	})
    	
    	.when('/pregledBioskopaPoImenu',{
    		templateUrl : 'html/pregledBioskopaPoImenu.html'
    	})
    	
    	.when('/pregledBioskopaISalaAdresa',{
            templateUrl : 'html/pregledBioskopaISalaAdresa.html'
        })
    	
    	.when('/pregledSala',{
    		templateUrl : 'html/pregledSala.html'
    	})
    	
   
        
        
        
        
        
        
        
        
        
        
}]);