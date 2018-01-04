angular.module('rezervacijaApp.ProjekcijaService', [])
	.factory('ProjekcijaService', function($http) {
		
		var factory = {};
		
		factory.getBrojZauzetihMestaPoRedu = function(projekcijaId, red) {
			return $http.get('/rezervacija-service/projekcija/zauzeta/'+projekcijaId+'/'+red);	
		}
		
		factory.getBrojZauzetihMestaPoReduIzmena = function(rezervacijaId, projekcijaId, red) {
			return $http.get('/rezervacija-service/projekcija/zauzetaIzmena/'
					+rezervacijaId+'/'+projekcijaId+'/'+red);	
		}
	
		return factory;
		
	});