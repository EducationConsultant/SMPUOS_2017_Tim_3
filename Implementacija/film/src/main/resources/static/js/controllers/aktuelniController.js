angular.module('filmApp.AktuelniController',[])
.controller('AktuelniController', function ($scope, $location, $rootScope, $mdDialog, FilmoviService, $localStorage, $mdToast) {
	
	$scope.title="Aktuelni filmovi";
	$scope.pregledAktuelnihFilmova = function() {
		alert("Pregled aktuelnih filmova");
		console.log("Tip prijavljenog korisnika "+$localStorage.tip);
		FilmoviService.pregledAktuelnihFilmova()
			.success(
				function(data) {
					$scope.listaAktuelnihFilmova = data;
					var lista = $scope.listaAktuelnihFilmova;
				
			})
	};
	
	isAdmin=function(){
		$scope.isAdmin= $localStorage.tip =='ADMIN';
		var value=$scope.isAdmin;
	};
	
	isAdmin();
	$scope.pregledAktuelnihFilmova();
	
});