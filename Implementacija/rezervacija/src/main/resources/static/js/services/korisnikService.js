angular.module('rezervacijaApp.KorisnikService', [])
	.factory('KorisnikService', function($http) {
		var factory = {};
		
		factory.getKorisnici = function() {
			return $http.get('/korisnik-service/korisnik/korisnici');	
		}
		
		factory.getSveRezervacijeKorisnika = function(id) {
			return $http.get('/rezervacija-service/rezervacija/sve/'+id);
		}
		
		factory.getAktivneRezervacijeKorisnika = function(id) {
			return $http.get('/rezervacija-service/rezervacija/aktivne/'+id);
		}
		
		factory.proveriKorisnika = function(id) {
			return $http.get('/rezervacija-service/rezervacija/proveriKorisnika/'+id);
		}
		
		return factory;
});