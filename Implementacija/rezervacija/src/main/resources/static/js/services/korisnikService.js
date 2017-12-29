angular.module('rezervacijaApp.KorisnikService', [])
	.factory('KorisnikService', function($http) {
		
		var factory = {};
		
		factory.getKorisnici = function() {
			return $http.get('/korisnik-service/korisnik/korisnici');	
		}
		
		factory.getAktivneRezervacijeKorisnika = function(id) {
			return $http.get('/rezervacija-service/rezervacija/aktivne/'+id);
		}
		
		factory.getSveRezervacijeKorisnika = function(id) {
			return $http.get('/rezervacija-service/rezervacija/sve/'+id);
		}
		
		return factory;
	});