angular.module('rezervacijaApp.RezervacijaService', [])
	.factory('RezervacijaService', function($http) {
		
		var factory = {};  //factory je jedan od nacina za kreiranje servisa
		
		factory.findAll = function() {
			return $http.get('/rezervacija-service/rezervacija/');	
		}
		
		factory.pregledAktivnihRezervacija = function() {
			return $http.get('/rezervacija-service/rezervacija/aktivne/projekcije');	
		}

		//factory.metoda1 = function(...){...}
		//u okviru ovih fja pozivamo http.get itd 
		//sintaksa je return $http.post('url', objekat);
		//objekat service dobija kao parametar metode
		
		return factory;
			
	});