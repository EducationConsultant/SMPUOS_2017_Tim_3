angular.module('bioskopApp.BioskopService', [])
    .factory('BioskopService', function($http){
        var factory = {};
        
        factory.findAll = function() {
			return $http.get('/bioskop-service/bioskop/');	
		}
        
        factory.obrisiBioskop = function(bioskopId){
			return $http.delete('/bioskop-service/bioskop/'+bioskopId);	
		}
		
		factory.izmeniBioskop = function(bioskop){
			return $http.put('/bioskop-service/bioskop/'+bioskop.id, bisokop);	
		}

        return factory;
    });