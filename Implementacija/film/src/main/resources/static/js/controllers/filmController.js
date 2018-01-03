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
	};
	
	$scope.getJezici=function(){
		FilmoviService.jezici()
		.success(
			function(data) {
				$scope.listaJezika = data;
		})
	};
	
	
	$scope.getKategorije=function(){
		
		FilmoviService.kategorije()
		.success(
			function(data) {
				$scope.listaKategorija = data;
		})
	};
	
	$scope.getReditelji=function(){
		FilmoviService.reditelji()
		.success(
			function(data) {
				$scope.listaReditelja = data;
		})
	};
	
	$scope.getGlumci=function(){
		FilmoviService.glumci()
		.success(
			function(data) {
				$scope.listaGlumaca = data.content;
		})
	};
	
	isAdmin=function(){
		$scope.isAdmin= $localStorage.tip =='ADMIN';
		var value=$scope.isAdmin;
	};
	
	$scope.init=function(){
		$scope.getGlumci();
		$scope.getKategorije();
		$scope.getReditelji();
		$scope.getJezici();
	}
	
	isAdmin();
	
	$scope.pregledFilmova();
	
});