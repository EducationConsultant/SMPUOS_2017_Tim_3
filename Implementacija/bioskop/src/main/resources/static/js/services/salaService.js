angular.module('bioskopApp.SalaService', [])
    .factory('SalaService', function($http){
       
    	var factory = {};
        
    	factory.obrisiSalu = function(bioskopId, salaId){
			return $http.delete('/bioskop-service/sala/'+bioskopId+'/'+salaId);	
		}
		
        return factory;
    });