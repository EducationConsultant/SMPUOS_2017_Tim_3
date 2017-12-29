angular.module('rezervacijaApp.RezervacijaService', [])
	.factory('RezervacijaService', function($http) {
		
		var factory = {};
		
		factory.findAll = function() {
			return $http.get('/rezervacija-service/rezervacija/');	
		}
		
		factory.pregledAktivnihRezervacija = function() {
			return $http.get('/rezervacija-service/rezervacija/aktivne/projekcije');	
		}
		
		factory.pregledOtkazanihRezervacija = function() {
			return $http.get('/rezervacija-service/rezervacija/otkazane/projekcije');	
		}
		
		factory.getProjekcije = function(){
			return $http.get('/rezervacija-service/projekcija/');	
		}

		factory.getAktivneRezervacijeZaProjekciju = function(projekcijaId) {
			return $http.get('/rezervacija-service/rezervacija/aktivne/projekcija/'+projekcijaId);	
		}
		
		factory.getOtkazaneRezervacijeZaProjekciju = function(projekcijaId) {
			return $http.get('/rezervacija-service/rezervacija/otkazane/projekcija/'+projekcijaId);	
		}
		
		factory.obrisiRezervaciju = function(rezervacijaId){
			return $http.delete('/rezervacija-service/rezervacija/'+rezervacijaId);	
		}
		
		factory.izmeniRezervaciju = function(rezervacija){
			return $http.put('/rezervacija-service/rezervacija/'+rezervacija.id, rezervacija);	
		}
		
		factory.deaktivirajRezervaciju = function(rezervacijaId){
			return $http.put('/rezervacija-service/rezervacija/deaktivacija/'+rezervacijaId);	
		}
		
		factory.kreirajRezervaciju = function(rezervacija){
			return $http.post('/rezervacija-service/rezervacija', rezervacija);
			
		}
		
		
		return factory;
			
	});