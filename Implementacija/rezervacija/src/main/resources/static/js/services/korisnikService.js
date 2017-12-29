angular.module('rezervacijaApp.KorisnikService', [])
	.factory('KorisnikService', function($http) {
		
		var factory = {};
		
		factory.getKorisnici = function() {
			return $http.get('/korisnik-service/korisnik/korisnici');	
		}
		
		return factory;
	});