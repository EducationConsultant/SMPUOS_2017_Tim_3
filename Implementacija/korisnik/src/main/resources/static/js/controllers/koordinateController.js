angular.module('korisnikApp.KoordinateController',[])
    .controller('KoordinateController', function ($scope, $location, $rootScope, $mdDialog, KorisniciService, $localStorage, $mdToast) {
    	$scope.korisnici = [];
    	
    	$scope.adresa = {geoDuzinaCentar:null, geoSirinaCentar:null, poluprecnik:null};
    	$scope.getKorisniciPoKoordinatama = function(){
    		KorisniciService.getKorisniciPoKoordinatama($scope.adresa).success(function (data) {
                $scope.korisnici = data;
            });
    	}
    });