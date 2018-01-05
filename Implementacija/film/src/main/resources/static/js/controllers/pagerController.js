angular.module('filmApp.PagerController',[])
.controller('PagerController', function ($scope, $location, $rootScope, $mdDialog, FilmoviService, $localStorage, $mdToast) {
	
	$scope.brojStavki=$scope.$parent.brojStavki;
	$scope.trenutnaStranica;
	$scope.ukupanBrojStrana=1;
	
	$scope.slececaStrana=function(){
		$scope.trenutnaStranica = $scope.trenutnaStranica + 1;
		$scope.stavkeZaPrikaz();
	}
	
	$scope.prethodnaStrana=function(){
		$scope.trenutnaStranica = $scope.trenutnaStranica -1;
		$scope.stavkeZaPrikaz();
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
		alert("Prije preracunavanja "+ $scope.ukupanBrojStrana);
		$scope.ukupanBrojStrana=Math.round($scope.$parent.listaFilmova.length/$scope.brojStavki);
		alert("Preracunaj ukupan broj strana "+$scope.ukupanBrojStrana);
		$scope.stavkeZaPrikaz();
	}
	
	$scope.promjenaBrojaStavki=function(brojStavki){
		alert("Broj prikazanih stavki "+brojStavki);
		$scope.brojStavki=brojStavki;
		$scope.preracunajUkupanBrojStrana();
		$scope.stavkeZaPrikaz();
	}
	
	$scope.stavkeZaPrikaz=function(){
		var preuzetaListaFilmova=$scope.$parent.listaFilmova;
		var izabraniPodniz=[];
		var indexFirst=($scope.trenutnaStranica-1)*$scope.brojStavki;
		var indexLast=indexFirst+$scope.brojStavki;
		angular.forEach(preuzetaListaFilmova, function(value, index){
			if(index>indexFirst && index<indexLast){
				izabraniPodniz.push(value);
			}
		});
		
		var value=$scope.title;
		$scope.title="Pager";
		$scope.$parent.listaFilmova=izabraniPodniz;
	}
	
});