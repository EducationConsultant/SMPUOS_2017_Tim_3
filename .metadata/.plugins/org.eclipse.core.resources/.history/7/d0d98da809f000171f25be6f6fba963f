angular.module('bioskopApp.BioskopService', [])
    .factory('BioskopService', function($http){
        var factory = {};
        
        factory.findAll = function() {
			return $http.get('/bioskop-service/bioskop/');	
		}
        
       

        return factory;
    });