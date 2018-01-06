angular.module('bioskopApp.KoordinateController',[])
    .controller('KoordinateController', function ($scope, $location, $rootScope, $mdDialog, BioskopService, $localStorage, $mdToast) {
    	$scope.bioskopi = [];
    	$scope.prikaziKoord = false;
    	
    	$scope.adresa = {geoDuzinaCentar:null, geoSirinaCentar:null, poluprecnik:null};
    	$scope.getBioskopiPoKoordinatama = function(){
    		if($scope.adresa.geoDuzinaCentar == null || $scope.adresa.geoSirinaCentar == null || $scope.adresa.poluprecnik == null){
    			$mdToast.show(
	                $mdToast.simple()
	                    .textContent('Niste uneli sve podatke!')
	                    .hideDelay(3000)
	                    .position('top center')
	                    .theme('error-toast')
	            );
    		}else{
	    		BioskopService.getBioskopiPoKoordinatama($scope.adresa).success(function (data) {
	                $scope.bioskopi = data;
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