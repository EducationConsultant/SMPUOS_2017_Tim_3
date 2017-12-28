angular.module('rezervacijaApp.RezervacijaService', [])
	.factory('RezervacijaService', function() {
		
		var factory = {};  //factory je jedan od nacina za kreiranje servisa
		
		//factory.metoda1 = function(...){...}
		//u okviru ovih fja pozivamo http.get itd 
		//sintaksa je return $http.post('url', objekat);
		//objekat service dobija kao parametar metode
		
		return factory;
			
	});