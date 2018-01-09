angular.module('bioskopApp.BioskopService', [])
    .factory('BioskopService', function($http){
        var factory = {};
        
        factory.findAll = function() {
			return $http.get('/bioskop-service/bioskop/');	
		}
        
        factory.dodajBioskop = function(bioskop) {
        	return $http.post('/bioskop-service/bioskop', bioskop);
        }
        
        factory.obrisiBioskop = function(id){
			return $http.delete('/bioskop-service/bioskop/'+id);	
		}
		
		factory.izmeniBioskop = function(bioskop){
			return $http.put('/bioskop-service/bioskop/'+bioskop.id, bioskop);	
		}
		
		factory.getBioskopiPoKoordinatama = function (adresa) {
            return $http.put('/bioskop-service/bioskop/koordinate', adresa);
        }
		
		factory.ocjeniBioskop= function(bioskop, korisnickoIme){
			return $http.put('/bioskop-service/bioskop/ocena/'+bioskop.id+ "/"+korisnickoIme, bioskop);
		}
		
        return factory;
    });