angular.module('rezervacijaApp.RezervacijaController',[])
    .controller('RezervacijaController', function ($scope, $location, $rootScope, $mdDialog, RezervacijaService, $localStorage, $mdToast) {
 
		$scope.statusRezervacije="Aktivne";
		$scope.showOtkazane = false;
		$scope.listaAktivnihIliOtkazanihRezervacija = [];
    
    	$scope.prikaziAktivneRezervacije = function() {
			RezervacijaService.pregledAktivnihRezervacija()
				.success(
					function(data) {
						$scope.listaAktivnihIliOtkazanihRezervacija = data;
				})
		};
				

		$scope.prikaziAktivneRezervacije();
		
		$scope.promenaStatusa = function(){
			if($scope.statusRezervacije == "Aktivne"){
        		RezervacijaService.pregledAktivnihRezervacija().success(function (data) {
                    $scope.listaAktivnihIliOtkazanihRezervacija = data;
                    $scope.showOtkazane = false;
                });
        	}else{
        		RezervacijaService.pregledOtkazanihRezervacija().success(function (data) {
                    $scope.listaAktivnihIliOtkazanihRezervacija = data;
                    $scope.showOtkazane = true;
                });
        	}
        }
    
});