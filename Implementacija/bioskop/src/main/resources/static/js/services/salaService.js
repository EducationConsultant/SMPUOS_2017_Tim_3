angular.module('bioskopApp.SalaService', [])
    .factory('SalaService', function($http){
       
    	var factory = {};
        
    	factory.dodajSalu = function(bioskopId, sala){
			return $http.post('/bioskop-service/sala/'+bioskopId, sala);	
		}
    	
    	factory.obrisiSalu = function(bioskopId, salaId){
			return $http.delete('/bioskop-service/sala/'+bioskopId+'/'+salaId);	
		}
		
    	factory.izmeniSalu = function(bioskopId, sala){
			return $http.put('/bioskop-service/sala/'+bioskopId+'/'+sala.id, sala);	
		}
		
        return factory;
    });