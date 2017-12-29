angular.module('rezervacijaApp.BioskopService', [])
	.factory('BioskopService', function($http) {
			
		var factory = {};
		
		factory.getBioskopi = function() {
			return $http.get('/bioskop-service/bioskop/');	
		}

		factory.getSaleZaBioskop = function(bioskopId) {
			return $http.get('/bioskop-service/sala/'+bioskopId);	
		}

		return factory;
			
	});
	
