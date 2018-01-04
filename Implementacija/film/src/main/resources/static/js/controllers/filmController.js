angular.module('filmApp.FilmController',[])
.controller('FilmController', function ($scope, $location, $rootScope, $mdDialog, FilmoviService, $localStorage, $mdToast) {
	
	$scope.title="Filmovi";
	$scope.isAdmin=false;
	$scope.izabraniKriterijum="svi";
	$scope.pregledFilmova = function() {
		
		FilmoviService.pregledFilmova()
			.success(
				function(data) {
					$scope.listaFilmova = data.content;
			})
	};
	
	$scope.obrisiFilm=function(id){
	
		var confirm = $mdDialog.confirm()
        .title('Da li ste sigurni da želite obrisati film?')
        .ok('Da')
        .cancel('Ne');
     $mdDialog.show(confirm).then(function() {
        $scope.status = 'Record deleted successfully!';
        var foundElement=-1;
		angular.forEach($scope.listaFilmova, function(value,index){
			if(value.id==id){
				foundElement=index;
			}
		});
		
		if(foundElement!=-1){
			$scope.listaFilmova.splice(foundElement,1);
		}
     }, function() {
        $scope.status = 'You decided to keep your record.';
     });
	};
	
	$scope.izaberiKriterijum=function(kriterijum){
		
		$scope.izabraniKriterijum=kriterijum;
		if(kriterijum!='aktuelni'){
			$scope.pregledFilmova();
		}else{
			$scope.pregledAktuelnihFilmova();
		}
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
		$scope.izabraniGlumci=[];
		noviFilm.glumci=$scope.izabraniGlumci;
		FilmoviService.dodavanjeFilma(noviFilm)
		.success(
			function(data) {
				var filmId=data.id;
				$scope.noviFilm={};
				$mdDialog.show (
		                  $mdDialog.alert()
		                     .parent(angular.element(document.querySelector('#dialogContainer')))
		                     .clickOutsideToClose(true)
		                     .title('Uspiješno dodavanje filma.')
		                     .ok('Ok!')
		               );
		});
	}
	
	$scope.potvrdaIzmjene=function(film){
		alert("Film datum premijere "+film.datumPremijere );
		FilmoviService.izmjenaFilma(film)
		.success(
			function(data) {
				alert("Uspijesna izmjena filma");
				var filmId=data.id;
				$scope.noviFilm={};
		
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
	
	
	$scope.izmjeniFilm=function(film){
		$scope.noviFilm=film;
		$scope.noviFilm.reditelj=film.reditelj;
		$scope.noviFilm.datumPremijere=new Date(film.datumPremijere);
		$scope.izabraniGlumci=film.glumci;
		$mdDialog.show({
		    scope               : $scope,
		    preserveScope       : true,
		    templateUrl         : 'html/izmjenaFilma.html',
		    
		    clickOutsideToClose : true,
		    fullscreen          : true,
		  
		}); 
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
	
	$scope.pregledPoGlumcu=function(glumac){
		FilmoviService.filmoviPoGlumcu(glumac.ime, glumac.prezime)
		.success(
				function(data) {
					$scope.listaFilmova=data;
			});
	}
	
	$scope.pregledPoOcjeni=function(ocjena){
		alert("Ocjena "+ocjena);
		FilmoviService.filmoviPoOcjeni(ocjena)
		.success(
				function(data) {
					$scope.listaFilmova=data;
			});
	}
	
	
	$scope.pregledPoGodiniPremijere=function(godina){
		FilmoviService.filmoviPoGodiniPremijere(godina)
		.success(
				function(data) {
					$scope.listaFilmova=data;
			});
	}
	$scope.pregledAktuelnihFilmova = function() {
		console.log("Tip prijavljenog korisnika "+$localStorage.tip);
		FilmoviService.pregledAktuelnihFilmova()
			.success(
				function(data) {
					$scope.listaFilmova = data;
					var lista = $scope.listaAktuelnihFilmova;
				
			})
	};
	
	isAdmin();
	$scope.init();
	$scope.pregledFilmova();
	
});