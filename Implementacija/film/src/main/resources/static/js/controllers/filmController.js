angular.module('filmApp.FilmController',[])
.controller('FilmController', function ($scope, $location, $rootScope, $mdDialog,  $localStorage, $mdToast) {

	$scope.pregledFilmova = function() {
		alert("**Pregled filmova:");
		FilmoviService.pregledFilmova()
			.success(
				function(data) {
					alert("**Pregled filmova: success");
					$scope.listaFilmova = data;
			})
	};
	
});