angular.module('korisnikApp.KorisniciController',[])
    .controller('KorisniciController', function ($scope, $location, $rootScope, $mdDialog, KorisniciService, $localStorage, $mdToast) {
    	
    	$scope.statusKorisnika = "Aktivirani";
    	$scope.showAktivirani = true;
    	$scope.showDeaktivirani = false;
    	
    	$scope.korisnici = [];
        function pregledAktivnih(){
        	KorisniciService.pregledAktivnih().success(function (data) {
                $scope.korisnici = data;
            });
        }

        pregledAktivnih();
        
        $scope.promenaStatusa = function(){
        	if($scope.statusKorisnika == "Aktivirani"){
        		KorisniciService.pregledAktivnih().success(function (data) {
                    $scope.korisnici = data;
                    $scope.showAktivirani = true;
                    $scope.showDeaktivirani = false;
                });
        	}else{
        		KorisniciService.pregledDeaktiviranih().success(function (data) {
                    $scope.korisnici = data;
                    $scope.showAktivirani = false;
                    $scope.showDeaktivirani = true;
                });
        	}
        }
        
        $scope.aktivacija = function(k){
        	var index = $scope.korisnici.indexOf(k);
        	KorisniciService.aktivacija(k.id).success(function (data) {
        		if(index > -1)
                    $scope.korisnici.splice(index, 1);
            });
        }
        
        $scope.deaktivacija = function(k){
        	var index = $scope.korisnici.indexOf(k);
        	KorisniciService.deaktivacija(k.id).success(function (data) {
        		if(index > -1)
                    $scope.korisnici.splice(index, 1);
            });
        }
    });