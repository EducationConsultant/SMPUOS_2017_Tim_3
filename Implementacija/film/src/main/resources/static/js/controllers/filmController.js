angular.module('filmApp.FilmController',[])
.controller('FilmController', function ($scope, $location, $rootScope, $mdDialog, FilmoviService, $localStorage, $mdToast) {
	
	$scope.title="Filmovi";
	$scope.isAdmin=false;
	$scope.izabraniGlumci=[];
	$scope.izabraniKriterijum="svi";
	$scope.pregledFilmova = function() {
		
		FilmoviService.pregledFilmova()
			.success(
				function(data) {
					$scope.listaFilmova = data.content;
			})
	};
	
	$scope.obrisiFilm=function(id){
		FilmoviService.brisanje(id);
		var foundElement=-1;
		angular.forEach($scope.listaFilmova, function(value,index){
			if(value.id==id){
				foundElement=index;
			}
		});
		
		if(foundElement!=-1){
			$scope.listaFilmova.splice(foundElement,1);
		}
	};
	
	$scope.izaberiKriterijum=function(kriterijum){
		alert("Izabrani kriterijum je "+ kriterijum);
		$scope.izabraniKriterijum=kriterijum;
	}
	
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
				$scope.listaGlumaca = data;
		});
	};
	
	$scope.dodajNoviFilm = function(noviFilm){
		
		var film=noviFilm;
		noviFilm.glumci=$scope.izabraniGlumci;
		FilmoviService.dodavanjeFilma(noviFilm)
		.success(
			function(data) {
				var filmId=data.id;
		});
	}
	
	$scope.dodajGlumca=function(glumac){
		$scope.izabraniGlumci.push(glumac);
	}
	
	isAdmin=function(){
		$scope.isAdmin= $localStorage.tip =='ADMIN';
		var value=$scope.isAdmin;
	};
	
	$scope.ukloniGlumca=function(glumac){
		//alert("Ukloni glumca "+ glumac.ime);
		var foundElement=-1;
		angular.forEach($scope.izabraniGlumci, function(value,index){
			if(value.id==glumac.id){
				foundElement=index;
			}
		});
		
		if(foundElement!=-1){
			$scope.izabraniGlumci.splice(foundElement,1);
		}
	}
	
	
	$scope.init=function(){
		$scope.getGlumci();
		$scope.getKategorije();
		$scope.getReditelji();
		$scope.getJezici();
	}
	
	$scope.pregledPoKategoriji=function(kategorija){
		var naziv=kategorija.naziv;
		FilmoviService.filmoviPoKategorijama(naziv)
		.success(
				function(data) {
					$scope.listaFilmova=data;
			});
	}
	
	$scope.pregledPoReditelju=function(reditelj){
		FilmoviService.filmoviPoReditelju(reditelj.ime, reditelj.prezime)
		.success(
				function(data) {
					$scope.listaFilmova=data;
			});
	}
	
	isAdmin();
	$scope.init();
	$scope.pregledFilmova();
	
});