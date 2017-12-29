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

		return factory;
			
	});