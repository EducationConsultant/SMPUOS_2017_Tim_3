angular.module('filmApp.FilmController',[])
.controller('FilmController', function ($scope, $location, $rootScope, $mdDialog, FilmoviService, $localStorage, $mdToast) {
	
	$scope.title="Filmovi";
	$scope.isAdmin=false;
	$scope.pregledFilmova = function() {
		
		FilmoviService.pregledFilmova()
			.success(
				function(data) {
					$scope.listaFilmova = data.content;
			})
	};
	
	$scope.obrisiFilm=function(id){
		alert("Brisanje filma "+ id);
		FilmoviService.brisanje(id);
	}
	
	isAdmin=function(){
		$scope.isAdmin= $localStorage.tip =='ADMIN';
		var value=$scope.isAdmin;
	};
	
	isAdmin();
	
	$scope.pregledFilmova();
	
});