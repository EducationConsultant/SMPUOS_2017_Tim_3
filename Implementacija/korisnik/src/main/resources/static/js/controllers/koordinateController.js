angular.module('korisnikApp.KoordinateController',[])
    .controller('KoordinateController', function ($scope, $location, $rootScope, $mdDialog, KorisniciService, $localStorage, $mdToast) {
    	$scope.korisnici = [];
    	$scope.prikaziKoord = false;
    	
    	$scope.adresa = {geoDuzinaCentar:null, geoSirinaCentar:null, poluprecnik:null};
    	$scope.getKorisniciPoKoordinatama = function(){
    		if($scope.adresa.geoDuzinaCentar == null || $scope.adresa.geoSirinaCentar == null || $scope.adresa.poluprecnik == null){
    			$mdToast.show(
	                $mdToast.simple()
	                    .textContent('Niste uneli sve podatke!')
	                    .hideDelay(3000)
	                    .position('top center')
	                    .theme('error-toast')
	            );
    		}else{
	    		KorisniciService.getKorisniciPoKoordinatama($scope.adresa).success(function (data) {
	                $scope.korisnici = data;
	                $scope.prikaziKoord = true;
                	$mdToast.show(
                            $mdToast.simple()
                                .textContent('Pretraga uspešno završena!')
                                .hideDelay(3000)
                                .position('top center')
                                .theme('success-toast')
                        );
	            });
    		}
    	}
    });