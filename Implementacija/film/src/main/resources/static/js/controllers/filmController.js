angular.module('filmApp.FilmController',[])
.controller('FilmController', function ($scope, $location, $rootScope, $mdDialog, FilmoviService, $localStorage, $mdToast) {
	
	$scope.title="Filmovi";
	$scope.pregledFilmova = function() {
		
		FilmoviService.pregledFilmova()
			.success(
				function(data) {
					$scope.listaFilmova = data.content;
					angular.forEach($scope.listaFilmova, function(index, value){
						console.log(value.naziv);
					})
			})
	};
	
	$scope.pregledFilmova();
	
});