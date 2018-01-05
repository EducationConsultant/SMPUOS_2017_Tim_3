angular.module('filmApp.FilmController',[])
.controller('FilmController', function ($scope, $location, $rootScope, $mdDialog, FilmoviService, $localStorage, $mdToast) {
	
	$scope.ucitaniFilmovi=[];
	$scope.title="Filmovi";
	$scope.isAdmin=false;
	$scope.izabraniKriterijum="svi";
	$scope.izabraniGlumci=[];
	$scope.brojStavki=5;
	$scope.trenutnaStranica = 1;
	$scope.ukupanBrojStrana = 1;
	
	
	$scope.pregledFilmova = function() {
		
		FilmoviService.pregledFilmova()
			.success(
				function(data) {
					$scope.listaFilmova = data.content;
					$scope.ucitaniFilmovi=$scope.listaFilmova;
					$scope.preracunajUkupanBrojStrana();
					$scope.stavkeZaPrikaz();
			})
	};
	
	$scope.ocjeniFilm=function(ocjena){
		alert("Ocjenjivanje "+ ocjena);
	}
	
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
		$scope.trenutnaStranica=1;
		
	}
	
	$scope.getJezici=function(){
		FilmoviService.jezici()
		.success(
			function(data) {
				$scope.listaJezika = data;
		})
	};
	
	
	$scope.getKategorije=function(){
		$scope.listaKategorija=[];
		FilmoviService.kategorije()
		.success(
			function(data) {
				$scope.listaKategorija = data;
		})
	};
	
	$scope.getReditelji=function(){
		$scope.listaReditelja=[];
		FilmoviService.reditelji()
		.success(
			function(data) {
				$scope.listaReditelja = data;
		})
	};
	
	$scope.getGlumci=function(){
		$scope.listaGlumaca=[];
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
		film.glumci=$scope.izabraniGlumci;
		FilmoviService.izmjenaFilma(film)
		.success(
			function(data) {
				alert("Uspijesna izmjena filma");
				var filmId=data.id;
				$scope.noviFilm={};
				$scope.izmjeniDialog.close();
		});
	}
	
	$scope.ocjeniFilm=function(film){
		alert("Ocjena filma "+ film.ocena);
		FilmoviService.ocjeniFilm(film)
			.success(
				function(data){
					alert("Uspjesno ocjenjivanje");
				});
	}
	
	$scope.dodajGlumca=function(glumac){
		alert("Glumac ime "+glumac.ime);
		$scope.izabraniGlumci.push(glumac);
	}
	
	isAdmin=function(){
		$scope.isAdmin= $localStorage.tip =='ADMIN';
		var value=$scope.isAdmin;
	};
	
	idRegUser=function(){
		$scope.isRegular = $localStorage.tip == 'REGKORISNIK';
	}
	
	idRegUser();
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
		$scope.izmjenaFilm=angular.copy(film);
		$scope.init();
		$scope.izmjenaFilm.datumPremijere=new Date(film.datumPremijere);
		$scope.izabraniGlumci = film.glumci;
		$scope.izmjeniDialog=$mdDialog.show({
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
					$scope.ucitaniFilmovi=$scope.listaFilmova;
					$scope.stavkeZaPrikaz();
			});
	}
	
	$scope.pregledPoReditelju=function(reditelj){
		FilmoviService.filmoviPoReditelju(reditelj.ime, reditelj.prezime)
		.success(
				function(data) {
					$scope.listaFilmova=data;
					$scope.ucitaniFilmovi=$scope.listaFilmova;
					$scope.stavkeZaPrikaz();
			});
	}
	
	$scope.pregledPoGlumcu=function(glumac){
		FilmoviService.filmoviPoGlumcu(glumac.ime, glumac.prezime)
		.success(
				function(data) {
					$scope.listaFilmova=data;
					$scope.ucitaniFilmovi=$scope.listaFilmova;
					$scope.stavkeZaPrikaz();
			});
	}
	
	$scope.pregledPoOcjeni=function(ocjena){
		alert("Ocjena "+ocjena);
		FilmoviService.filmoviPoOcjeni(ocjena)
		.success(
				function(data) {
					$scope.listaFilmova=data;
					$scope.ucitaniFilmovi=$scope.listaFilmova;
					$scope.stavkeZaPrikaz();
			});
	}
	
	
	$scope.pregledPoGodiniPremijere=function(godina){
		FilmoviService.filmoviPoGodiniPremijere(godina)
		.success(
				function(data) {
					$scope.listaFilmova=data;
					$scope.ucitaniFilmovi=$scope.listaFilmova;
					$scope.stavkeZaPrikaz();
			});
	}
	
	$scope.pregledAktuelnihFilmova = function() {
		console.log("Tip prijavljenog korisnika "+$localStorage.tip);
		FilmoviService.pregledAktuelnihFilmova()
			.success(
				function(data) {
					$scope.listaFilmova = data;
					$scope.ucitaniFilmovi=$scope.listaFilmova;
					var lista = $scope.listaAktuelnihFilmova;
					$scope.stavkeZaPrikaz();
				
			})
	};
	 
	
	
	isAdmin();
	//$scope.init();
	
		
	$scope.slececaStrana=function(){
		
		if( $scope.trenutnaStranica + 1 <= $scope.ukupanBrojStrana){
			$scope.trenutnaStranica = $scope.trenutnaStranica + 1;
			$scope.stavkeZaPrikaz();
		}
		
	}
	
	$scope.prethodnaStrana=function(){
		
		if($scope.trenutnaStranica>1){
			$scope.trenutnaStranica = $scope.trenutnaStranica -1;
			$scope.stavkeZaPrikaz();
		}
	}
	
	$scope.prvaStrana= function(){
		$scope.trenutnaStranica =1;
		$scope.stavkeZaPrikaz();
	}
	
	$scope.poslednjaStrana= function(){
		
		$scope.trenutnaStranica=$scope.ukupanBrojStrana;
		$scope.stavkeZaPrikaz();
	}
	
	$scope.preracunajUkupanBrojStrana=function(){
		
		$scope.ukupanBrojStrana=Math.ceil($scope.ucitaniFilmovi.length/$scope.brojStavki);
		
		
	}
	
	$scope.promjenaBrojaStavki=function(brojStavki){
		
		$scope.brojStavki=brojStavki;
		$scope.preracunajUkupanBrojStrana();
		$scope.stavkeZaPrikaz();
	}
	
	$scope.stavkeZaPrikaz=function(){
	
		var preuzetaListaFilmova=$scope.ucitaniFilmovi;
		var izabraniPodniz=[];
		if($scope.brojStavki==undefined){
			$scope.brojStavki=5;
		}
		var brStavki=parseInt($scope.brojStavki);
		var indexFirst=($scope.trenutnaStranica-1)*brStavki;
		var indexLast=indexFirst + brStavki;
		angular.forEach(preuzetaListaFilmova, function(value, index){
			if(index>=indexFirst && index<indexLast){
				izabraniPodniz.push(value);
			}
		});
		
		var value=$scope.title;
		$scope.title="Pager";
		$scope.listaFilmova=izabraniPodniz;
	}
	
	 $scope.prikaziIzmjenjenSadrzaj=function(data){
		 var foundIndex=-1;
		 angular.forEach($scope.listaFilmova, function(value, index){
			 
			 if(value.id==data.id){
				 foundIndex=index;
			 }
		 });
		 
		 if(foundIndex!=-1){
			 $scope.listaFilmova.splice(foundIndex,1);
			 $scope.listaFilmova.splice(foundIndex,0, data);
		 }
	 }
});